package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a record representation of a coord with an x and y value.
 *
 * @param x the x position of the coord.
 * @param y the y position of the coord.
 */
public record CoordRecord(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {
}