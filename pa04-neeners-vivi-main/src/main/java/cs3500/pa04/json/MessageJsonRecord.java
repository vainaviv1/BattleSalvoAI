package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a record representation of a Json message which contains a method name and arguments
 * as a JSON node.
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "method-name": "method name",
 *   "arguments": {}
 * }
 * </code>
 * </p>
 *
 * @param messageName the name of the server method request
 * @param arguments   the arguments passed along with the message formatted as a Json object
 */
public record MessageJsonRecord(
    @JsonProperty("method-name") String messageName,
    @JsonProperty("arguments") JsonNode arguments) {
}