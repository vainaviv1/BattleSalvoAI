package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.json.BoardInfoRecord;
import cs3500.pa04.json.CoordRecord;
import cs3500.pa04.json.VolleyRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the JsonUtils class.
 */
class JsonUtilsTest {
  BoardInfoRecord b1;
  Map<ShipType, Integer> shipInfo1;
  VolleyRecord v1;
  CoordRecord c1;
  CoordRecord c2;
  CoordRecord c3;
  CoordRecord c4;
  CoordRecord c5;
  List<CoordRecord> coordRecords1;

  /**
   * Initialize a BoardInfoRecord to use for tests.
   */
  @BeforeEach
  public void initializeBoardInfoRecord() {
    shipInfo1 = new TreeMap<>();
    shipInfo1.put(ShipType.CARRIER, 1);
    shipInfo1.put(ShipType.BATTLESHIP, 3);
    shipInfo1.put(ShipType.DESTROYER, 2);
    shipInfo1.put(ShipType.SUBMARINE, 2);
    b1 = new BoardInfoRecord(13, 9, shipInfo1);
  }

  /**
   * Initialize a CoordRecord to use for tests.
   */
  @BeforeEach
  public void initializeCoordRecord() {
    c1 = new CoordRecord(3, 2);
  }

  /**
   * Initialize Va olleyRecord along with the CoordRecords needed to construct the
   * VolleyRecord to use for tests.
   */
  @BeforeEach
  public void initializeVolleyRecord1() {
    c1 = new CoordRecord(3, 2);
    c2 = new CoordRecord(12, 4);
    c3 = new CoordRecord(0, 9);
    c4 = new CoordRecord(2, 0);
    c5 = new CoordRecord(5, 6);
    coordRecords1 = new ArrayList<>();
    coordRecords1.add(c1);
    coordRecords1.add(c2);
    coordRecords1.add(c3);
    coordRecords1.add(c4);
    coordRecords1.add(c5);
    v1 = new VolleyRecord(coordRecords1);
  }

  /**
   * Tests that serializeRecord converts the given record into a JsonNode.
   */
  @Test
  public void testSerializeRecord() {
    assertEquals(true, b1 instanceof BoardInfoRecord);
    assertEquals(true, c1 instanceof CoordRecord);
    assertEquals(true, v1 instanceof VolleyRecord);

    assertEquals(true, JsonUtils.serializeRecord(b1) instanceof JsonNode);
    assertEquals(true, JsonUtils.serializeRecord(c1) instanceof JsonNode);
    assertEquals(true, JsonUtils.serializeRecord(v1) instanceof JsonNode);
  }
}