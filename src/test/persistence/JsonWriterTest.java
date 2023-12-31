package persistence;

import model.Restaurant;
import model.Restaurants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Restaurants r = new Restaurants();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRestaurant() {
        try {
            Restaurants r = new Restaurants();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRestaurant.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRestaurant.json");
            r = reader.read();
            assertEquals(0, r.getNumRestaurantsHaveNotBeenTo());
            assertEquals(0, r.getNumRestaurants());
            assertEquals(0, r.getNumRestaurantsHaveBeenTo());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneral() {
        try {
            Restaurants r = new Restaurants();
            r.addRestaurant(new Restaurant("ABC Restaurant", 4,
                    true, "Asian", "Vancouver"));
            r.addRestaurant(new Restaurant("CDE Restaurant", 3, false,
                    "Western", "Burnaby"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneral.json");
            r = reader.read();
            List<Restaurant> restos = r.getAllRestaurants();
            assertEquals(2, restos.size());
            checkRestaurant("ABC Restaurant", 4, true,
                    "Asian", "Vancouver", restos.get(0));
            checkRestaurant("CDE Restaurant", 3, false,
                    "Western", "Burnaby", restos.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
