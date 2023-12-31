package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents list of restaurants that we have been and we have not been
public class Restaurants implements Writable {
    private List<Restaurant> restaurantsHaveBeen;
    private List<Restaurant> restaurantsHaveNotBeen;

    // EFFECTS : constructs an empty list of restaurants that we have been and have not been
    public Restaurants() {
        restaurantsHaveBeen = new ArrayList<>();
        restaurantsHaveNotBeen = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds restaurants based on whether we have been or we have not been
    public void addRestaurant(Restaurant restaurant) {

        if (restaurant.isHaveBeen()) {
            restaurantsHaveBeen.add(restaurant);
        } else {
            restaurantsHaveNotBeen.add(restaurant);
        }
        EventLog.getInstance().logEvent(new Event("Restaurant " + restaurant.getRestaurantName()
                + " has been added."));

    }

    //EFFECTS: return the list of restaurants we have been
    public List<Restaurant> getRestaurantsHaveBeen() {
        return restaurantsHaveBeen;
    }

    //EFFECTS: return the list of restaurants we have not been
    public List<Restaurant> getRestaurantsHaveNotBeen() {
        return restaurantsHaveNotBeen;
    }

    public List<Restaurant> getRestaurantsForGui() {
        EventLog.getInstance().logEvent(new Event("Viewed all restaurants in the list."));
        return getAllRestaurants();
    }

    //MODIFIES: this
    //EFFECTS: return the list of all restaurants
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.addAll(restaurantsHaveBeen);
        restaurants.addAll(restaurantsHaveNotBeen);
        //EventLog.getInstance().logEvent(new Event("Viewed all restaurants in the list."));
        return restaurants;
    }

    //EFFECTS: count the number of restaurants total in the list
    public int getNumRestaurants() {
        return restaurantsHaveBeen.size() + restaurantsHaveNotBeen.size();
    }

    //EFFECTS: count the number of restaurants have been total in the list
    public int getNumRestaurantsHaveBeenTo() {
        return restaurantsHaveBeen.size();
    }

    //EFFECTS: count the number of restaurants have not been total in the list
    public int getNumRestaurantsHaveNotBeenTo() {
        return restaurantsHaveNotBeen.size();
    }


    //REQUIRES: there must be an element in the list
    //MODIFIES: this
    //EFFECTS: filters restaurant based on the criteria, such as: rating, cuisine, location and whether
    //we have been or not

    public List<Restaurant> restaurantsCriteria(double minimumRating, String cuisineType, String location, boolean
            haveBeen) {

        EventLog.getInstance().logEvent(new Event("Filters have been applied."));

        if (haveBeen) {
            return selectRestaurant(minimumRating, cuisineType, location, restaurantsHaveBeen);

        } else {
            return selectRestaurant(minimumRating, cuisineType, location, restaurantsHaveNotBeen);
        }





    }

    //REQUIRES: rating > 0 and list cannot be empty
    //MODIFIES: this
    //EFFECTS: a helper method based on the list that we work on for restaurantsCriteria

    private List<Restaurant> selectRestaurant(double minimumRating, String cuisineType, String location,
                                              List<Restaurant> resto) {

        List<Restaurant> fitsCriteria = new ArrayList<>();

        for (Restaurant restaurant : resto) {
            double restaurantRating = restaurant.getRestaurantRating();
            String restaurantCuisine = restaurant.getRestaurantCuisine();
            String restaurantLocation = restaurant.getRestaurantLocation();

            boolean cond1 = restaurantRating >= minimumRating;
            boolean cond2 = cuisineType.equals(restaurantCuisine);
            boolean cond3 = location.equals(restaurantLocation);
            if (cond1 && cond2 && cond3) {
                fitsCriteria.add(restaurant);
            } else {
                fitsCriteria.remove(restaurant);
            }
        }

        return fitsCriteria;


    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("been", restaurantsHaveBeenToJson());
        json.put("notbeen", restaurantsHaveNotBeenToJson());
        return json;
    }

    // EFFECTS: returns restaurants in list of restaurants that we have been as a JSON array
    private JSONArray restaurantsHaveBeenToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant r : restaurantsHaveBeen) {
            jsonArray.put(r.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns restaurants in list of restaurants that we have not been as a JSON array
    private JSONArray restaurantsHaveNotBeenToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant r : restaurantsHaveNotBeen) {
            jsonArray.put(r.toJson());
        }
        return jsonArray;
    }

}
