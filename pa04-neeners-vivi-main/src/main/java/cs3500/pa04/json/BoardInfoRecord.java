package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa03.model.ShipType;
import java.util.Map;

/**
 * Represents a record representation of Board Information which contains information about the game
 * board's width, height, and fleet.
 *
 * @param width the width information of the board
 * @param height the height information of the board
 * @param fleet a map of shiptypes to the number of each shiptype for the board
 */
public record BoardInfoRecord(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") Map<ShipType, Integer> fleet) {
}