package cs3500.pa04.json;

import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for CoordinatesJson
 */
public class CoordinatesJsonTest {

  /**
   * Tests the getCoordinates method for the Jsonified coord
   */
  @Test
  public void testGetCoordinates() {
    List<CoordJson> coords = new ArrayList<>();
    coords.add(new CoordJson(1, 2));
    coords.add(new CoordJson(3, 4));
    coords.add(new CoordJson(5, 6));

    CoordinatesJson coordinatesJson = new CoordinatesJson(coords);
    final List<Coord> retrievedCoords = coordinatesJson.getCoordinates();

    List<Coord> expectedCoords = new ArrayList<>();
    expectedCoords.add(new Coord(1, 2));
    expectedCoords.add(new Coord(3, 4));
    expectedCoords.add(new Coord(5, 6));

    Assertions.assertEquals(expectedCoords, retrievedCoords);
  }

  /**
   * Tests the getCoordinates method to get the non-Jsonified coord
   */
  @Test
  public void testGetCoordsJson() {
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(1, 2));
    coords.add(new Coord(3, 4));
    coords.add(new Coord(5, 6));

    CoordinatesJson coordinatesJson = new CoordinatesJson(new ArrayList<>());
    final List<CoordJson> retrievedCoordJsonList = coordinatesJson.getCoordsJson(coords);

    List<CoordJson> expectedCoordJsonList = new ArrayList<>();
    expectedCoordJsonList.add(new CoordJson(1, 2));
    expectedCoordJsonList.add(new CoordJson(3, 4));
    expectedCoordJsonList.add(new CoordJson(5, 6));

    Assertions.assertEquals(expectedCoordJsonList, retrievedCoordJsonList);
  }

}
