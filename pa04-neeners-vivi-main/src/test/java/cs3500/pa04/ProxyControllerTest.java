package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.json.BoardInfoRecord;
import cs3500.pa04.json.CoordRecord;
import cs3500.pa04.json.FleetRecord;
import cs3500.pa04.json.GameEndRecord;
import cs3500.pa04.json.GameInfoRecord;
import cs3500.pa04.json.MessageJsonRecord;
import cs3500.pa04.json.VolleyRecord;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test correct responses for different requests from the socket using a Mock Socket (mocket)
 */
public class ProxyControllerTest {
  private ByteArrayOutputStream testLog;
  private ProxyController controller;
  Map<ShipType, Integer> shipInfo1;
  Player player;
  Player seededPlayer;

  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  /**
   * Initialize the player for tests.
   */
  @BeforeEach
  public void initializePlayer() {
    shipInfo1 = new TreeMap<>();
    shipInfo1.put(ShipType.CARRIER, 1);
    shipInfo1.put(ShipType.BATTLESHIP, 3);
    shipInfo1.put(ShipType.DESTROYER, 2);
    shipInfo1.put(ShipType.SUBMARINE, 2);

    player = new AiPlayer("nileenajohn", new Random());

    // call setup so player's board is not null
    player.setup(9, 12, shipInfo1);
  }

  /**
   * Initialize the seeded player for tests.
   */
  @BeforeEach
  public void initializeSeededPlayer() {
    shipInfo1 = new TreeMap<>();
    shipInfo1.put(ShipType.CARRIER, 1);
    shipInfo1.put(ShipType.BATTLESHIP, 3);
    shipInfo1.put(ShipType.DESTROYER, 2);
    shipInfo1.put(ShipType.SUBMARINE, 2);

    seededPlayer = new AiPlayer("nileenajohn", new Random(6));

    // call setup so player's board is not null
    seededPlayer.setup(9, 12, shipInfo1);
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Try converting the current test log to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);

      // No error thrown when parsing, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }

  /**
   * Try converting the current given object to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClassArguments(Object o,
                                    @SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(o.toString());
      jsonParser.readValueAs(classRef);

      // No error thrown when parsing, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName name of the type of message; ex. "setup", "report-damage", etc
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJsonRecord messageJson = new MessageJsonRecord(messageName,
        JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }

  /**
   * Check that the delegateMessage method throws an exception when the server sends an invalid
   * request.
   */
  @Test
  public void testDelegateMessageForInvalidMessage() throws IOException {
    // Create sample join request (mimic server join request)
    JsonNode jsonNode = JsonUtils.serializeRecord(new MessageJsonRecord("invalid "
        + "request",
        JsonNodeFactory.instance.objectNode()));

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    Throwable exception = assertThrows(IllegalStateException.class, () -> this.controller.run());
    assertEquals("Invalid message name", exception.getMessage());
  }

  /**
   * Check that the ProxyController returns a valid response to the server's join request with the
   * player's username and preferred GameType.
   */
  @Test
  public void testHandleJoinForJoinRequest() throws IOException {
    // Create sample join request (mimic server join request)
    JsonNode jsonNode = JsonUtils.serializeRecord(new MessageJsonRecord("join",
        JsonNodeFactory.instance.objectNode()));

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    // check that in response to the join request from the server, handleJoin returns a MessageJson
    responseToClass(MessageJsonRecord.class);

    // check that the arguments of the MessageJson is a GameInfo (which contains a username and
    // gametype)
    JsonParser jsonParser = new ObjectMapper().createParser(logToString());
    MessageJsonRecord mjr = jsonParser.readValueAs(MessageJsonRecord.class);
    responseToClassArguments(mjr.arguments(), GameInfoRecord.class);
  }

  /**
   * Check that the ProxyController's response to the server's join request is the players username
   * and preferred GameType.
   */
  @Test
  public void testHandleJoinOutput() {
    // Create sample join request (mimic server join request)
    JsonNode jsonNode = JsonUtils.serializeRecord(new MessageJsonRecord("join",
        JsonNodeFactory.instance.objectNode()));

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    assertEquals("{\"method-name\":\"join\",\"arguments\":{\"name\":\"nileenajohn"
            + "\",\"game-type\":\"SINGLE\"}}\n", logToString());
  }

  /**
   * Check that the ProxyController returns a valid response to the server's setup request with the
   * player's fleet as arguments.
   */
  @Test
  public void testHandleSetupForSetupRequest() throws IOException {
    BoardInfoRecord b1 = new BoardInfoRecord(13, 9, shipInfo1);;

    // Create sample setup request (mimic server setup request)
    JsonNode jsonNode = createSampleMessage("setup", b1);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    // check that in response to the setup request from the server, handleSetup returns a
    // MessageJson
    responseToClass(MessageJsonRecord.class);

    // check that the arguments of the MessageJson is a FleetRecord (which contains a List of Ship
    // Records)
    JsonParser jsonParser = new ObjectMapper().createParser(logToString());
    MessageJsonRecord mjr = jsonParser.readValueAs(MessageJsonRecord.class);
    responseToClassArguments(mjr.arguments(), FleetRecord.class);
  }

  /**
   * Check that the ProxyController's response to the server's setup request is the player's fleet
   * as arguments which follows the given specifications from the server.
   */
  @Test
  public void testHandleSetupOutput() {
    BoardInfoRecord b1 = new BoardInfoRecord(13, 9, shipInfo1);;

    // Create sample setup request (mimic server setup request)
    JsonNode jsonNode = createSampleMessage("setup", b1);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, seededPlayer);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    // check that the output contains the ships according to the specifications of the server
    assertEquals(true, logToString().contains("{\"coord\":{\"x\":5,\"y\":3},\"length\":6,"
        + "\"direction\":\"VERTICAL\"}"));
    assertEquals(true, logToString().contains("{\"coord\":{\"x\":6,\"y\":1},\"length\":5,"
        + "\"direction\":\"VERTICAL\"}"));
    assertEquals(true, logToString().contains("{\"coord\":{\"x\":0,\"y\":1},\"length\":5,"
        + "\"direction\":\"VERTICAL\"}"));
    assertEquals(true, logToString().contains("{\"coord\":{\"x\":12,\"y\":3},\"length\":5,"
        + "\"direction\":\"VERTICAL\"}"));
    assertEquals(true, logToString().contains("{\"coord\":{\"x\":1,\"y\":8},\"length\":4,"
        + "\"direction\":\"HORIZONTAL\"}"));
    assertEquals(true, logToString().contains("{\"coord\":{\"x\":2,\"y\":1},\"length\":4,"
        + "\"direction\":\"HORIZONTAL\"}"));
    assertEquals(true, logToString().contains("{\"coord\":{\"x\":2,\"y\":5},\"length\":3,"
        + "\"direction\":\"HORIZONTAL\"}"));
    assertEquals(true, logToString().contains("{\"coord\":{\"x\":10,\"y\":4},\"length\":3,"
        + "\"direction\":\"VERTICAL\"}"));

    assertEquals("{\"method-name\":\"setup\",\"arguments\":{\"fleet\":[{\"coord\":{\""
        + "x\":5,\"y\":3},\"length\":6,\"direction\":\"VERTICAL\"},{\"coord\":{\"x\":6,\"y\":1},\""
        + "length\":5,\"direction\":\"VERTICAL\"},{\"coord\":{\"x\":0,\"y\":1},\"length\":5,\""
        + "direction\":\"VERTICAL\"},{\"coord\":{\"x\":12,\"y\":3},\"length\":5,\"direction\":\""
        + "VERTICAL\"},{\"coord\":{\"x\":1,\"y\":8},\"length\":4,\"direction\":\"HORIZONTAL\"},{\""
        + "coord\":{\"x\":2,\"y\":1},\"length\":4,\"direction\":\"HORIZONTAL\"},{\"coord\":{\"x\""
        + ":2,\"y\":5},\"length\":3,\"direction\":\"HORIZONTAL\"},{\"coord\":{\"x\":10,\"y\":4},\""
        + "length\":3,\"direction\":\"VERTICAL\"}]}}\n", logToString());
  }

  /**
   * Check that the ProxyController returns a valid response to the server's take-shots request with
   * the player's volley of shots as arguments.
   */
  @Test
  public void testHandleTakeShotsForTakeShotsRequest() throws IOException {
    // Create sample take-shots request (mimic server take-shots request)
    JsonNode jsonNode = JsonUtils.serializeRecord(new MessageJsonRecord("take-shots",
        JsonNodeFactory.instance.objectNode()));

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));


    // Create a controller
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    // check that in response to the setup request from the server, handleSetup returns a
    // MessageJson
    responseToClass(MessageJsonRecord.class);

    // check that the arguments of the MessageJson is a FleetRecord (which contains a List of Ship
    // Records)
    JsonParser jsonParser = new ObjectMapper().createParser(logToString());
    MessageJsonRecord mjr = jsonParser.readValueAs(MessageJsonRecord.class);
    responseToClassArguments(mjr.arguments(), VolleyRecord.class);
  }

  /**
   * Check that the ProxyController's response to the server's take-shots request is the player's
   * volley of shots as arguments.
   */
  @Test
  public void testHandleTakeShotsOutput() {
    // Create sample take-shots request (mimic server take-shots request)
    JsonNode jsonNode = JsonUtils.serializeRecord(new MessageJsonRecord("take-shots",
        JsonNodeFactory.instance.objectNode()));

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));


    // Create a controller
    try {
      this.controller = new ProxyController(socket, seededPlayer);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    assertEquals("{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\""
        + ":[{\"x\":10,\"y\":5},{\"x\":4,\"y\":1},{\"x\":9,\"y\":8},{\"x\":9,\"y\":1},{\"x\":11,\""
        + "y\":3},{\"x\":1,\"y\":1},{\"x\":8,\"y\":1},{\"x\":10,\"y\":0}]}}\n", logToString());
  }

  /**
   * Check that the ProxyController returns a valid response to the server's report-damage request
   * with the player's volley of damaged coordinates as arguments.
   */
  @Test
  public void testHandleReportDamageForReportDamageRequest() throws IOException {
    CoordRecord c1 = new CoordRecord(3, 2);
    CoordRecord c2 = new CoordRecord(11, 4);
    CoordRecord c3 = new CoordRecord(0, 8);
    CoordRecord c4 = new CoordRecord(2, 0);
    CoordRecord c5 = new CoordRecord(5, 6);
    List<CoordRecord> coordRecords = new ArrayList<>();
    coordRecords.add(c1);
    coordRecords.add(c2);
    coordRecords.add(c3);
    coordRecords.add(c4);
    coordRecords.add(c5);
    VolleyRecord volley = new VolleyRecord(coordRecords);

    // Create sample report-damage request (mimic server report-damage request)
    JsonNode jsonNode = createSampleMessage("report-damage", volley);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    // check that in response to the setup request from the server, handleSetup returns a
    // MessageJson
    responseToClass(MessageJsonRecord.class);

    // check that the arguments of the MessageJson is a FleetRecord (which contains a List of Ship
    // Records)
    JsonParser jsonParser = new ObjectMapper().createParser(logToString());
    MessageJsonRecord mjr = jsonParser.readValueAs(MessageJsonRecord.class);
    responseToClassArguments(mjr.arguments(), VolleyRecord.class);
  }

  /**
   * Check that the ProxyController's reponse to the server's report-damage request is the player's
   * volley of damaged coordinates as arguments.
   */
  @Test
  public void testHandleReportDamageOutput() {
    CoordRecord c1 = new CoordRecord(3, 2);
    CoordRecord c2 = new CoordRecord(11, 4);
    CoordRecord c3 = new CoordRecord(0, 8);
    CoordRecord c4 = new CoordRecord(2, 0);
    CoordRecord c5 = new CoordRecord(5, 6);
    List<CoordRecord> coordRecords = new ArrayList<>();
    coordRecords.add(c1);
    coordRecords.add(c2);
    coordRecords.add(c3);
    coordRecords.add(c4);
    coordRecords.add(c5);
    VolleyRecord volley = new VolleyRecord(coordRecords);

    // Create sample report-damage request (mimic server report-damage request)
    JsonNode jsonNode = createSampleMessage("report-damage", volley);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, seededPlayer);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();
    assertEquals("{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\""
        + ":[{\"x\":0,\"y\":8},{\"x\":2,\"y\":0}]}}\n", logToString());
  }

  /**
   * Check that the ProxyController returns a valid response to the server's successful-hits request
   * with no arguments.
   */
  @Test
  public void testHandleSuccessfulHitsOutput() throws IOException {
    CoordRecord c1 = new CoordRecord(3, 2);
    CoordRecord c2 = new CoordRecord(11, 4);
    CoordRecord c3 = new CoordRecord(0, 8);
    CoordRecord c4 = new CoordRecord(2, 0);
    CoordRecord c5 = new CoordRecord(5, 6);
    List<CoordRecord> coordRecords = new ArrayList<>();
    coordRecords.add(c1);
    coordRecords.add(c2);
    coordRecords.add(c3);
    coordRecords.add(c4);
    coordRecords.add(c5);
    VolleyRecord volley = new VolleyRecord(coordRecords);

    // Create sample successful-hits request (mimic server successful-hits request)
    JsonNode jsonNode = createSampleMessage("successful-hits", volley);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    // check that in response to the setup request from the server, handleSetup returns a
    // MessageJson
    responseToClass(MessageJsonRecord.class);

    // Since no arguments are expected, check that the arguments are empty when converted to a
    // string
    JsonParser jsonParser = new ObjectMapper().createParser(logToString());
    MessageJsonRecord mjr = jsonParser.readValueAs(MessageJsonRecord.class);
    assertEquals("{}", mjr.arguments().toString());
  }

  /**
   * Check that the ProxyController returns a valid response to the server's end-game request with
   * no arguments.
   */
  @Test
  public void testHandleEndGameOutput() throws IOException {
    GameEndRecord ge = new GameEndRecord(GameResult.WIN, "Player 1 hit all ships!");
    // Create sample end-game request (mimic server end-game request)
    JsonNode jsonNode = createSampleMessage("end-game", ge);

    // Create socket with sample input
    Mocket socket = new Mocket(this.testLog, List.of(jsonNode.toString()));

    // Create a controller
    try {
      this.controller = new ProxyController(socket, player);
    } catch (IOException e) {
      fail(); // fail if the controller can't be created
    }

    // Run controller and verify response.
    this.controller.run();

    // check that in response to the setup request from the server, handleSetup returns a
    // MessageJson
    responseToClass(MessageJsonRecord.class);

    // Since no arguments are expected, check that the arguments are empty when converted to a
    // string
    JsonParser jsonParser = new ObjectMapper().createParser(logToString());
    MessageJsonRecord mjr = jsonParser.readValueAs(MessageJsonRecord.class);
    assertEquals("{}", mjr.arguments().toString());
  }
}