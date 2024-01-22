package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a record representation for ShipInfo.
 *
 * @param numCarriers the number of Carriers represented in this record of ship info.
 * @param numBattleships the number of Battleships represented in this record of ship info.
 * @param numDestroyers the number of Carriers represented in this record of ship info.
 * @param numSubmarines the number of Submarines represented in this record of ship info.
 */
public record ShipInfoRecord(
    @JsonProperty("CARRIER") int numCarriers,
    @JsonProperty("BATTLESHIP") int numBattleships,
    @JsonProperty("DESTROYER") int numDestroyers,
    @JsonProperty("SUBMARINE") int numSubmarines) {
}