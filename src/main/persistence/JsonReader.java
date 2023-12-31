package persistence;

import model.Restaurant;
import model.Restaurants;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the restaurant list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of restaurants from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Restaurants read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurant(jsonObject);
    }



    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses restaurant from JSON object and returns it
    private Restaurants parseRestaurant(JSONObject jsonObject) {
        Restaurants listOfRestaurants = new Restaurants();
        JSONArray been = jsonObject.getJSONArray("been");
        JSONArray notBeen = jsonObject.getJSONArray("notbeen");
        addRestaurants(listOfRestaurants, been);
        addRestaurants(listOfRestaurants, notBeen);
        return listOfRestaurants;
    }

    // MODIFIES: restaurants
    // EFFECTS: parses thingies from JSON object and adds them to list of restaurants
    private void addRestaurants(Restaurants r, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            addRestaurant(r, nextRestaurant);
        }
    }

    // MODIFIES: restaurants
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addRestaurant(Restaurants r, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double rating = jsonObject.getDouble("rating");
        boolean haveBeen = jsonObject.getBoolean("haveBeen");
        String cuisine = jsonObject.getString("cuisine");
        String location = jsonObject.getString("location");
        Restaurant resto = new Restaurant(name, rating, haveBeen, cuisine, location);
        r.addRestaurant(resto);
    }


}
