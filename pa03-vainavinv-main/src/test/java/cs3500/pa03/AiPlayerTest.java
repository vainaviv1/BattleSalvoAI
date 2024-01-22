//package cs3500.pa03;
//
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
///**
// * tests the AiPlayer class
// */
//public class AiPlayerTest {
//
//  /**
//   * Tests the AiPlayer setUp method
//   */
////  @Test
////  public void testSetup() {
////    AiPlayer aiPlayer = new AiPlayer("AI Player");
////    int height = 5;
////    int width = 5;
////    Map<ShipType, Integer> specifications = new HashMap<>();
////    specifications.put(ShipType.CARRIER, 1);
////    specifications.put(ShipType.BATTLESHIP, 2);
////    specifications.put(ShipType.DESTROYER, 1);
////    specifications.put(ShipType.SUBMARINE, 1);
////
////    ArrayList<Ship> ships = aiPlayer.setup(height, width, specifications);
////
////
////    int totalCount = specifications.values().stream().mapToInt(Integer::intValue).sum();
////    assertEquals(totalCount, ships.size());
////
////
////    for (Ship ship : ships) {
////      ShipType type = ship.getShipType();
////      assertEquals(type.getShipSize(), ship.getCoords().size());
////    }
////  }
//
//  /**
//   * Tests the AiPlayer reportDamage method
//   */
//  @Test
//  public void testReportDamage() {
//    AiPlayer aiPlayer = new AiPlayer("AI Player");
//
//
//    int height = 5;
//    int width = 5;
//    Map<ShipType, Integer> specifications = new HashMap<>();
//    specifications.put(ShipType.CARRIER, 1);
//    specifications.put(ShipType.BATTLESHIP, 2);
//    specifications.put(ShipType.DESTROYER, 1);
//    specifications.put(ShipType.SUBMARINE, 1);
//    ArrayList<Ship> ships = aiPlayer.setup(height, width, specifications);
//
//
//    List<Coord> opponentShots = new ArrayList<>();
//    for (Ship ship : ships) {
//      opponentShots.addAll(ship.getCoords());
//    }
//
//
//    List<Coord> hitsByPlayer = aiPlayer.reportDamage(opponentShots);
//
//    assertEquals(opponentShots.size(), hitsByPlayer.size());
//  }
//
//  /**
//   * Tests the AiPlayer TakeShots method
//   */
//  @Test
//  public void testTakeShots() {
//    AiPlayer aiPlayer = new AiPlayer("AI Player");
//
//
//    int height = 5;
//    int width = 5;
//    Map<ShipType, Integer> specifications = new HashMap<>();
//    specifications.put(ShipType.CARRIER, 1);
//    specifications.put(ShipType.BATTLESHIP, 2);
//    specifications.put(ShipType.DESTROYER, 1);
//    specifications.put(ShipType.SUBMARINE, 1);
//    ArrayList<Ship> ships = aiPlayer.setup(height, width, specifications);
//
//
//    List<Coord> shots = aiPlayer.takeShots();
//
//
//    assertEquals(ships.size(), shots.size());
//
//    for (Coord shot : shots) {
//      assertTrue(shot.getX() >= 0 && shot.getX() < height);
//      assertTrue(shot.getY() >= 0 && shot.getY() < width);
//    }
//  }
//}
//
//
//
