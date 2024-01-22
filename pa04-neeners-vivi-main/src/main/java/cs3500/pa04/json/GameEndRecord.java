package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.GameResult;

/**
 * Represents a record representation of a game result for BattleSalvo.
 *
 * @param result the result for the game, either "WIN", "LOSE", or "DRAW".
 * @param reason the reason for the game end.
 */
public record GameEndRecord(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {
}