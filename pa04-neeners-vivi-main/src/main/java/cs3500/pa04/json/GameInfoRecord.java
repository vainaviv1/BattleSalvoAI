package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.GameType;

/**
 * Represents a record for a GameInfo which holds a username and gametype information.
 *
 * @param username the username for the player in the game.
 * @param gameType the gametype the player wants for the game.
 */
public record GameInfoRecord(
    @JsonProperty("name") String username,
    @JsonProperty("game-type") GameType gameType) {
}
