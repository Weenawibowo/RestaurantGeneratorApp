package model;

import org.json.JSONObject;
import persistence.Writable;

// restaurant has its own name, rating, type of cuisine, location and whether I have been or not
public class Restaurant implements Writable {
    private String restaurantName;
    private double restaurantRating;
    private boolean haveBeen;
    private String restaurantCuisine;
    private String restaurantLocation;

    //REQUIRES: rating > 0
    //EFFECTS: construct a restaurant with its name, rating, cuisine, location
    //and whether we have been

    public Restaurant(String restaurantName, double restaurantRating, boolean haveBeen,
                      String restaurantCuisine, String restaurantLocation) {
        this.restaurantName = restaurantName;
        this.restaurantRating = restaurantRating;
        this.haveBeen = haveBeen;
        this.restaurantCuisine = restaurantCuisine;
        this.restaurantLocation = restaurantLocation;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public double getRestaurantRating() {
        return restaurantRating;
    }

    public boolean isHaveBeen() {
        return haveBeen;
    }

    public String getRestaurantCuisine() {
        return restaurantCuisine;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", restaurantName);
        json.put("rating", restaurantRating);
        json.put("haveBeen", haveBeen);
        json.put("cuisine", restaurantCuisine);
        json.put("location", restaurantLocation);
        return json;
    }

}
