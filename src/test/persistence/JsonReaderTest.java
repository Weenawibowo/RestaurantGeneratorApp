package persistence;

import model.Restaurant;
import model.Restaurants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Restaurants r = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurants() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRestaurants.json");
        try {
            Restaurants r = reader.read();
            assertEquals(0, r.getNumRestaurants());
            assertEquals(0, r.getNumRestaurantsHaveBeenTo());
            assertEquals(0, r.getNumRestaurantsHaveNotBeenTo());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
        try {
            Restaurants r = reader.read();
            List<Restaurant> restos = r.getAllRestaurants();
            assertEquals(2, restos.size());
            checkRestaurant("ABC Restaurant", 4, true,
                    "Asian", "Vancouver", restos.get(0));
            checkRestaurant("CDE Restaurant", 3, false,
                    "Western", "Burnaby", restos.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
