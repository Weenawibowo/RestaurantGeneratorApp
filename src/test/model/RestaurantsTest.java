package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// unit tests for Restaurants class along with different type of individual restaurant
class RestaurantsTest {
    private Restaurants testRestaurants;
    private Restaurants testRestaurants2;
    private Restaurant hotpot;
    private Restaurant sushi;
    private Restaurant taiwanese;
    private Restaurant jbbq;
    private Restaurant italianRestaurantVancouver;
    private Restaurant italianRestaurantVancouver2;
    private Restaurant italianRestaurantBurnaby;
    private Restaurant middleeasternRestaurantVancouver;
    private Restaurant indianRestaurantBurnaby;
    private Restaurant indianRestaurantSurrey;
    private Restaurant westernRestaurantVancouver;
    private Restaurant westernRestaurantNorthVancouver;
    private Restaurant dessert;





    @BeforeEach
    void runBefore() {
        this.testRestaurants = new Restaurants();
        this.testRestaurants2 = new Restaurants();
        hotpot = new Restaurant("Dolar Shop", 4.5, true,
                "Asian", "Richmond");
        sushi = new Restaurant("Matsuyama", 3, true,
                "Asian", "Richmond");
        taiwanese = new Restaurant("Memory Corner", 3.5, false,
                "Asian", "Richmond");
        jbbq = new Restaurant("Gyukaku", 4, false,
                "Asian", "Richmond");

        italianRestaurantVancouver = new Restaurant("Savio Volpe", 4.8, true,
                "Italian", "Vancouver");
        italianRestaurantVancouver2 = new Restaurant("Ask For Luigi", 4, true,
                "Italian", "Vancouver");
        italianRestaurantBurnaby = new Restaurant("Trattoria", 3, false,
                "Italian", "Burnaby");


        middleeasternRestaurantVancouver = new Restaurant("Donair King", 4.2, false,
                "Middle Eastern", "Vancouver");

        indianRestaurantBurnaby = new Restaurant("Saffron", 3.5, true,
                "Indian", "Burnaby");
        indianRestaurantSurrey = new Restaurant("Green Indian Cuisine", 2, false,
                "Indian", "Surrey");

        westernRestaurantVancouver = new Restaurant("Glowbal", 3, false,
                "Western", "Vancouver");
        westernRestaurantNorthVancouver = new Restaurant("Burgoo", 3.8, true,
                "Western", "North Vancouver");

        dessert = new Restaurant("Thierry", 4.2, true,
                "Dessert", "Vancouver");



        testRestaurants.addRestaurant(hotpot);
        testRestaurants.addRestaurant(sushi);
        testRestaurants.addRestaurant(taiwanese);
        testRestaurants.addRestaurant(jbbq);
        testRestaurants.addRestaurant(italianRestaurantVancouver);
        testRestaurants.addRestaurant(italianRestaurantVancouver2);
        testRestaurants.addRestaurant(italianRestaurantBurnaby);
        testRestaurants.addRestaurant(middleeasternRestaurantVancouver);
        testRestaurants.addRestaurant(indianRestaurantBurnaby);
        testRestaurants.addRestaurant(indianRestaurantSurrey);
        testRestaurants.addRestaurant(westernRestaurantVancouver);
        testRestaurants.addRestaurant(westernRestaurantNorthVancouver);
        testRestaurants.addRestaurant(dessert);


    }

    @Test
    void testRestaurantsConstructor() {
        assertEquals(testRestaurants.getNumRestaurants(), 13);
        assertEquals(testRestaurants.getNumRestaurantsHaveNotBeenTo(), 6);
        assertEquals(testRestaurants.getNumRestaurantsHaveBeenTo(), 7);
        assertEquals(testRestaurants.getRestaurantsHaveBeen().get(0), hotpot);
        assertEquals(testRestaurants.getRestaurantsHaveNotBeen().get(0), taiwanese);
        List<Restaurant> allRestos = testRestaurants.getAllRestaurants();
        assertEquals(13, allRestos.size());

        // List<Restaurant> emptyRestaurantList = new ArrayList<>();
        // assertEquals((testRestaurants.restaurantsCriteria(4, "Asian",
        //       "Richmond", false)), emptyRestaurantList);

        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(5, "Asian",
                "Richmond", false);
        assertEquals(0, fitsCriteria.size());


    }

    @Test
    void testRestaurants2Constructor() {
        assertEquals(testRestaurants2.getNumRestaurants(), 0);
        assertEquals(testRestaurants2.getNumRestaurantsHaveNotBeenTo(), 0);
        assertEquals(testRestaurants2.getNumRestaurantsHaveBeenTo(), 0);
        List<Restaurant> allRestos = testRestaurants2.getAllRestaurants();
        assertEquals(0, allRestos.size());
        testRestaurants2.addRestaurant(dessert);
        assertEquals(testRestaurants2.getNumRestaurants(), 1);
        assertEquals(testRestaurants2.getNumRestaurantsHaveNotBeenTo(), 0);
        assertEquals(testRestaurants2.getNumRestaurantsHaveBeenTo(), 1);
        assertEquals(testRestaurants2.getRestaurantsHaveBeen().get(0), dessert);


        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(5, "Asian",
                "Richmond", false);
        assertEquals(0, fitsCriteria2.size());

    }

    @Test
    void testRestaurants2AddedRestaurant() {
        testRestaurants2.addRestaurant(hotpot);
        assertEquals(testRestaurants2.getNumRestaurants(), 1);
        testRestaurants2.addRestaurant(sushi);
        assertEquals(testRestaurants2.getNumRestaurants(), 2);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(5, "Asian",
                "Richmond", false);
        assertEquals(testRestaurants2.getNumRestaurantsHaveBeenTo(), 2);
        assertEquals(testRestaurants2.getNumRestaurantsHaveNotBeenTo(), 0);
        assertEquals(0, fitsCriteria2.size());


    }

    @Test
    void testRestaurants2OneRestaurant() {
        testRestaurants2.addRestaurant(hotpot);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(5, "Asian",
                "Richmond", false);
        assertEquals(0, fitsCriteria2.size());

    }

    @Test
    void testRestaurantsRatingHaveNotBeen() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(jbbq);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(3.5, "Asian",
                "Richmond", false);
        assertEquals(2, fitsCriteria2.size());

    }

    @Test
    void testRestaurantsRatingHaveBeen() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(jbbq);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(3.5, "Asian",
                "Richmond", true);
        assertEquals(0, fitsCriteria2.size());
        List<Restaurant> allRestos = testRestaurants2.getAllRestaurants();
        assertEquals(2, allRestos.size());

    }

    @Test
    void testRestaurantsWrongCuisineHaveBeen() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(jbbq);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(3, "Italian",
                "Richmond", true);
        assertEquals(0, fitsCriteria2.size());

    }

    @Test
    void testRestaurantsWrongCuisineHaveNotBeen() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(jbbq);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(3, "Italian",
                "Richmond", false);
        assertEquals(0, fitsCriteria2.size());

    }

    @Test
    void testRestaurantsWrongLocationHaveBeen() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(jbbq);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(3.5, "Asian",
                "North Vancouver", true);
        assertEquals(0, fitsCriteria2.size());

    }

    @Test
    void testRestaurantsWrongLocationHaveNotBeen() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(jbbq);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(3.5, "Asian",
                "North Vancouver", false);
        assertEquals(0, fitsCriteria2.size());

    }


    @Test
    void testRestaurants2OnlyOne() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(jbbq);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(4, "Asian",
                "Richmond", false);
        assertEquals(1, fitsCriteria2.size());

    }





    @Test
    void testRestaurants2AddedThreeRestaurant() {
        testRestaurants2.addRestaurant(hotpot);
        assertEquals(testRestaurants2.getNumRestaurants(), 1);
        testRestaurants2.addRestaurant(sushi);
        assertEquals(testRestaurants2.getNumRestaurants(), 2);
        testRestaurants2.addRestaurant(taiwanese);
        List<Restaurant> fitsCriteria2 = testRestaurants2.restaurantsCriteria(3, "Asian",
                "Richmond", false);
        assertEquals(1, fitsCriteria2.size());
        assertEquals(taiwanese, fitsCriteria2.get(0));


    }


    @Test
    void testOneRestaurantMeetsCriteria() {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(4.5, "Asian",
                "Richmond", true);
        assertEquals(1, fitsCriteria.size());

    }

    @Test
    void testTwoRestaurantsMeetsCriteria() {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(4, "Italian",
                "Vancouver", true);
        assertEquals(2, fitsCriteria.size());
        assertEquals(italianRestaurantVancouver, fitsCriteria.get(0));
        assertEquals(italianRestaurantVancouver2, fitsCriteria.get(1));

    }

    @Test
    void testTwoRestaurantRatingBoundary() {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(4, "Italian",
                "Vancouver", true);
        assertEquals(2, fitsCriteria.size());
        assertEquals(italianRestaurantVancouver, fitsCriteria.get(0));
        assertEquals(italianRestaurantVancouver2, fitsCriteria.get(1));


    }

    @Test
    void testRestaurantsSameButNeverBeen() {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(4, "Middle Eastern",
                "Vancouver", false);
        assertEquals(1, fitsCriteria.size());
        assertEquals(middleeasternRestaurantVancouver, fitsCriteria.get(0));

    }

    @Test
    void allRestaurantsInAreaHaveBeen () {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(0, "Asian",
                "Richmond", true);
        assertEquals(2, fitsCriteria.size());
        assertEquals(hotpot, fitsCriteria.get(0));
        assertEquals(sushi, fitsCriteria.get(1));


    }

    @Test
    void allRestaurantsInAreaHaveNotBeen () {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(0, "Asian",
                "Richmond", false);
        assertEquals(2, fitsCriteria.size());
        assertEquals(taiwanese, fitsCriteria.get(0));
        assertEquals(jbbq, fitsCriteria.get(1));


    }

    @Test
    void foodNotInArea() {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(3, "Indian",
                "Coquitlam", false);
        assertEquals(0, fitsCriteria.size());


    }

    @Test
    void cuisineNotAvailable() {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(3, "Canadian",
                "Vancouver", false);
        assertEquals(0, fitsCriteria.size());

    }

    @Test
    void wantDesserts() {
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(3, "Dessert",
                "Vancouver", true);
        assertEquals(1, fitsCriteria.size());
        assertEquals(dessert, fitsCriteria.get(0));


    }

    @Test
    void addOneRestaurantThenRemove() {
        testRestaurants2.addRestaurant(taiwanese);
        assertEquals(1, testRestaurants2.getNumRestaurants());

    }

    @Test
    void addTwoRestaurantThenRemove() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(sushi);
        assertEquals(2, testRestaurants2.getNumRestaurants());

    }

    @Test
    void addTwoRestaurantThenRemoveNoMatch() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(sushi);
        assertEquals(2, testRestaurants2.getNumRestaurants());
        List<Restaurant> fitsCriteria = testRestaurants.restaurantsCriteria(3, "Asian",
                "Richmond", true);
        assertEquals(2, fitsCriteria.size());
        assertTrue(fitsCriteria.contains(sushi));


    }

    @Test
    void addTwoRestaurantThenRemoveAll() {
        testRestaurants2.addRestaurant(taiwanese);
        testRestaurants2.addRestaurant(sushi);
        assertEquals(2, testRestaurants2.getNumRestaurants());
        List<Restaurant> allRestos = testRestaurants2.getAllRestaurants();

        assertEquals(2, allRestos.size());
        assertEquals(sushi, allRestos.get(0));



    }








}

