package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.Ship;
import cs3500.pa04.json.BoardInfoRecord;
import cs3500.pa04.json.CoordRecord;
import cs3500.pa04.json.FleetRecord;
import cs3500.pa04.json.GameEndRecord;
import cs3500.pa04.json.GameInfoRecord;
import cs3500.pa04.json.MessageJsonRecord;
import cs3500.pa04.json.ShipRecord;
import cs3500.pa04.json.VolleyRecord;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
* Responsible for handling receiving and responding to server requests by delegating to a player.
*/
public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final Player player;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
  * Represents a constructor for a ProxyController.
  *
  * @param server the server to receive message and respond back to
  * @param player the player to play the BattleSalvo game to delegate server requests to
  * @throws IOException when input from the server cannot be handles or cannot output to server
  */
  public ProxyController(Socket server, Player player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
  }

  /**
  * Listens for messages from the server as JSON in the format of a MessageJSONRecord. When a
  * complete message is sent by the server, the message is parsed and then delegated to the
  * corresponding helper method for each message. This method stops when the connection to the
  * server is closed or an IOException is thrown from parsing malformed JSON.
  */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJsonRecord message = parser.readValueAs(MessageJsonRecord.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }
  }

  /**
  * Separates the Json message from the server by the name and arguments and calls the appropriate
  * method to handle the message giving it the corresponding arguments
  * (for join, setup, take-shots, report-damage, successful-hits, and end-game).
  *
  * @param message the message from the server as a MessageJsonRecord
  */
  private void delegateMessage(MessageJsonRecord message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();
    if ("join".equals(name)) {
      this.out.println(handleJoin());
    } else if ("setup".equals(name)) {
      this.out.println(handleSetup(arguments));
    } else if ("take-shots".equals(name)) {
      this.out.println(handleTakeShots());
    } else if ("report-damage".equals(name)) {
      this.out.println(handleReportDamage(arguments));
    } else if ("successful-hits".equals(name)) {
      this.out.println(handleSuccessfulHits(arguments));
    } else if ("end-game".equals(name)) {
      this.out.println(handleEndGame(arguments));
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }

  /**
  * Produces the response to the handle request from the server. Returns github username and the
  * gametype (either SINGLE or MULTI).
  *
  * @return a JsonNode containing the join response with github username and gametype as arguments.
  */
  private JsonNode handleJoin() {
    GameInfoRecord response = new GameInfoRecord(player.name(), GameType.SINGLE);
    return JsonUtils.serializeRecord(new MessageJsonRecord("join",
        JsonUtils.serializeRecord(response)));
  }

  /**
  * Produces the response to the setup request from the server. Receives width, height, and fleet
  * specifications as JsonNode and calls the setup method on the player with the given information.
  * Returns the player's fleet information (with coordinates, length, and direction) received from
  * the setup method as a JsonNode.
  *
  * @return a JsonNode containing the setup response with the player's fleet as arguments.
  */
  private JsonNode handleSetup(JsonNode arguments) {
    BoardInfoRecord boardArgs = this.mapper.convertValue(arguments, BoardInfoRecord.class);

    List<Ship> playerShips = player.setup(boardArgs.height(), boardArgs.width(), boardArgs.fleet());
    List<ShipRecord> recordShips = convertShipsToRecord(playerShips);

    FleetRecord fleet = new FleetRecord(recordShips);
    return JsonUtils.serializeRecord(new MessageJsonRecord("setup",
        JsonUtils.serializeRecord(fleet)));
  }

  /**
   * Converts a list of ships into a list of ShipRecords.
   *
   * @param ships the list of ships to convert into a list of ShipRecords
   * @return the list of given ships converted into a list of ShipRecords.
   */
  private List<ShipRecord> convertShipsToRecord(List<Ship> ships) {
    List<ShipRecord> recordShips = new ArrayList<>();
    for (Ship s : ships) {
      Coord pa3Coord = s.getHeadCoordinate();
      CoordRecord c = new CoordRecord(pa3Coord.getX(), pa3Coord.getY());
      recordShips.add(new ShipRecord(c, s.getCoordinates().size(), s.getDirection()));
    }
    return recordShips;
  }

  /**
   * Produces the response to the take-shots request from the server. Calls the takeShots method on
   * the player with the given information. Returns the player's shots as a list of coordinates
   * converted to a JsonNode.
   *
   * @return a JsonNode containing the take-shots response with player's volley as arguments.
   */
  private JsonNode handleTakeShots() {
    List<Coord> pa3Coords = player.takeShots();
    List<CoordRecord> coordinates = convertCoordToRecord(pa3Coords);

    VolleyRecord volley = new VolleyRecord(coordinates);
    return JsonUtils.serializeRecord(new MessageJsonRecord("take-shots",
        JsonUtils.serializeRecord(volley)));
  }

  /**
   * Converts a list of Coords into a list of CoordRecords.
   *
   * @param pa3Coords the list of coords to convert into a list of CoordRecords
   * @return the list of given coords converted into a list of CoordRecords.
   */
  private List<CoordRecord> convertCoordToRecord(List<Coord> pa3Coords) {
    List<CoordRecord> coordinates = new ArrayList<>();
    for (Coord c : pa3Coords) {
      CoordRecord coord = new CoordRecord(c.getX(), c.getY());
      coordinates.add(coord);
    }
    return coordinates;
  }

  /**
  * Produces the response to the report-damage request from the server. Receives a volley of
  * coordinates as a JsonNode and converts it into a list of coords. Calls reportDamage() on player
  * giving it the list of coordinates. Receives the coordinates output from the reportDamage call
  * and converts it into a Volley. Returns the player's coordinates of damage as a JsonNode.
  *
  * @return JsonNode containing report-damage response with volley of player's damage as arguments.
  */
  private JsonNode handleReportDamage(JsonNode arguments) {
    VolleyRecord volleyArgs = this.mapper.convertValue(arguments, VolleyRecord.class);
    List<Coord> pa3Coords = convertVolleyToCoords(volleyArgs);
    List<Coord> pa3Damage = player.reportDamage(pa3Coords);

    List<CoordRecord> output = convertCoordToRecord(pa3Damage);
    VolleyRecord volley = new VolleyRecord(output);

    return JsonUtils.serializeRecord(new MessageJsonRecord("report-damage",
        JsonUtils.serializeRecord(volley)));
  }

  /**
   * Converts a VolleyRecord into a list of Coords.
   *
   * @param volley the Volley to convert into a list of Coords
   * @return the given volley converted into a list of Coords.
   */
  private List<Coord> convertVolleyToCoords(VolleyRecord volley) {
    List<Coord> pa3Coords = new ArrayList<>();
    for (CoordRecord c : volley.coordinates()) {
      Coord coord = new Coord(c.x(), c.y());
      pa3Coords.add(coord);
    }
    return pa3Coords;
  }

  /**
   * Produces the response to the successful-hits request from the server. Receives a volley of
   * coordinates as a JsonNode and converts it into a list of coords. Calls succesfulHits() on
   * player giving it the list of coordinates.
   *
   * @return a JsonNode containing the successful-hits response with no arguments.
   */
  private JsonNode handleSuccessfulHits(JsonNode arguments) {
    VolleyRecord volleyArgs = this.mapper.convertValue(arguments, VolleyRecord.class);
    List<Coord> pa3Coords = convertVolleyToCoords(volleyArgs);
    player.successfulHits(pa3Coords);

    JsonNode node = JsonNodeFactory.instance.objectNode();
    return JsonUtils.serializeRecord(new MessageJsonRecord("successful-hits", node));
  }

  /**
   * Produces the response to the end-game request from the server. Receives a result and reason as
   * JsonNode arguments. Calls endGame of player giving it the result and reason.
   *
   * @return a JsonNode containing the end-game response with no arguments.
   */
  private JsonNode handleEndGame(JsonNode arguments) {
    GameEndRecord gameEnd = this.mapper.convertValue(arguments, GameEndRecord.class);
    player.endGame(gameEnd.result(), gameEnd.reason());
    JsonNode node = JsonNodeFactory.instance.objectNode();
    return JsonUtils.serializeRecord(new MessageJsonRecord("end-game", node));
  }
}
