package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.json.CoordRecord;

/**
 * Represents a record representation of a ship with a coordinate, ship length, and ship direction.
 *
 * @param c the coordinate of the ship head
 * @param length the length of the ship
 * @param direction the direction of the ship, either "HORIZONTAL" or "VERTICAL"
 */
public record ShipRecord(
    @JsonProperty("coord") CoordRecord c,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction) {
}