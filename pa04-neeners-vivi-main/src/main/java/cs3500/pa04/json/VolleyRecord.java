package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.json.CoordRecord;
import java.util.List;

/**
 * Represents a record representation of a volley.
 *
 * @param coordinates A List of CoordRecords representing coordinates in a volley.
 */
public record VolleyRecord(
    @JsonProperty("coordinates") List<CoordRecord> coordinates) {
}
