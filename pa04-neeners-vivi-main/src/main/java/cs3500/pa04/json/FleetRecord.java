package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a record representation of a fleet.
 *
 * @param fleet a fleet which is a list of ShipRecords
 */
public record FleetRecord(
    @JsonProperty("fleet") List<ShipRecord> fleet) {
}