package persistence;

import model.Restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRestaurant(String name, double rating, boolean haveBeen,
                                   String cuisine, String location, Restaurant resto) {
        assertEquals(name, resto.getRestaurantName());
        assertEquals(rating, resto.getRestaurantRating());
        assertEquals(haveBeen, resto.isHaveBeen());
        assertEquals(cuisine, resto.getRestaurantCuisine());
        assertEquals(location, resto.getRestaurantLocation());
    }
}