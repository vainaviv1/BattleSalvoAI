package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import cs3500.pa03.model.GameResult;
import cs3500.pa04.JsonUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the MessageJsonRecord class.
 */
class MessageJsonRecordTest {
  CoordRecord c1;
  CoordRecord c2;
  CoordRecord c3;
  CoordRecord c4;
  CoordRecord c5;
  CoordRecord c6;
  CoordRecord c7;
  CoordRecord c8;
  CoordRecord c9;
  List<CoordRecord> coordRecords1;
  List<CoordRecord> coordRecords2;
  VolleyRecord v1;
  VolleyRecord v2;
  MessageJsonRecord mj1;
  MessageJsonRecord mj2;
  MessageJsonRecord mj3;
  MessageJsonRecord mj4;
  GameEndRecord ge1;

  /**
   * Initializes the first MessageJsonRecord to use for tests.
   */
  @BeforeEach
  public void initializeMessageJsonRecord1() {
    JsonNode node = JsonNodeFactory.instance.objectNode();
    mj1 = new MessageJsonRecord("join", node);
  }

  /**
   * Initializes the second MessageJsonRecord to use for tests.
   */
  @BeforeEach
  public void initializeMessageJsonRecord2() {
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
    mj2 = new MessageJsonRecord("report-damage", JsonUtils.serializeRecord(v1));
  }

  /**
   * Initializes the third MessageJsonRecord to use for tests.
   */
  @BeforeEach
  public void initializeMessageJsonRecord3() {
    c6 = new CoordRecord(5, 6);
    c7 = new CoordRecord(1, 11);
    c8 = new CoordRecord(9, 3);
    c9 = new CoordRecord(13, 1);
    coordRecords2 = new ArrayList<>();
    coordRecords2.add(c6);
    coordRecords2.add(c7);
    coordRecords2.add(c8);
    coordRecords2.add(c9);
    v2 = new VolleyRecord(coordRecords2);
    mj3 = new MessageJsonRecord("successful-hits", JsonUtils.serializeRecord(v2));
  }

  /**
   * Initializes the fourth MessageJsonRecord to use for tests.
   */
  @BeforeEach
  public void initializeMessageJsonRecord4() {
    ge1 = new GameEndRecord(GameResult.WIN,
        "Your player sunk all the server player's ships!");
    mj4 = new MessageJsonRecord("end-game", JsonUtils.serializeRecord(ge1));
  }

  /**
   * Tests that messageName() gets the messageName of the MessageJsonRecord.
   */
  @Test
  public void testMessageJsonMessageName() {
    assertEquals("join", mj1.messageName());
    assertEquals("report-damage", mj2.messageName());
    assertEquals("successful-hits", mj3.messageName());
    assertEquals("end-game", mj4.messageName());
  }

  /**
   * Tests that arguments() gets the JsonNode arguments of the MessageJsonRecord.
   */
  @Test
  public void testArguments() {
    assertEquals(JsonNodeFactory.instance.objectNode(), mj1.arguments());
    assertEquals(JsonUtils.serializeRecord(v1), mj2.arguments());
    assertEquals(JsonUtils.serializeRecord(v2), mj3.arguments());
    assertEquals(JsonUtils.serializeRecord(ge1), mj4.arguments());
  }
}