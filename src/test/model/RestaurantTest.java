package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//A unit test for a restaurant
public class RestaurantTest {
    private Restaurant testRestaurant;

    @BeforeEach
    void RunBefore() {
        testRestaurant = new Restaurant("ABC", 4, false,
                "Asian", "Vancouver");
    }

    @Test
    void testConstructor() {
        assertEquals("ABC", testRestaurant.getRestaurantName());
        assertEquals("Vancouver", testRestaurant.getRestaurantLocation());
        assertEquals("Asian", testRestaurant.getRestaurantCuisine());
        assertEquals(4, testRestaurant.getRestaurantRating());

    }

}

