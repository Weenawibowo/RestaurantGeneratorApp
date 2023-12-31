package ui;


import model.Event;
import model.EventLog;
import model.Restaurant;
import model.Restaurants;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;



// Restaurant Generator application
public class RestaurantGenerator {
    private static final String JSON_STORE = "./data/restaurant.json";
    private Restaurant restaurant;
    private Restaurants restaurantList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs the list of restaurant and runs the application
    public RestaurantGenerator() throws FileNotFoundException {
        restaurantList = new Restaurants();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGenerator();

    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runGenerator() {
        boolean keepgoing = true;

        init();

        while (keepgoing) {

            displayMenu();

            input = new Scanner(System.in);
            String command = input.next();
            command = command.toLowerCase();
            input.nextLine();

            if (command.equals("yes")) {
                keepgoing = false;

            } else {
                processCommand(command);

            }

        }
        //printEvents();

        System.out.println("See you next time!");

    }

    //MODIFIES: this
    //EFFECTS: initialize
    private void init() {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    //EFFECTS: display menu of options to user
    private void displayMenu() {
        System.out.println("Please select your option :)");
        System.out.println("Add a restaurant -> add ");
        System.out.println("Select a restaurant -> select ");
        System.out.println("View all the restaurants options -> view");
        System.out.println("Save your work -> save");
        System.out.println("Load your work -> load");
        System.out.println("Quit? (yes/no) ");

    }

    //MODIFIES: this
    //EFFECTS : process user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            doAdd();
        } else if (command.equals("select")) {
            doSelect();
        } else if (command.equals("view")) {
            doView();
        } else if (command.equals("save")) {
            saveRestaurants();
        } else if (command.equals("load")) {
            loadRestaurants();
        } else {
            System.out.println("Input is invalid! Please try again");
        }
    }

    //MODIFIES: this
    //EFFECTS: Adding a restaurant to the list
    private void doAdd() {

        System.out.println("What is the name of the restaurant? ");
        String name = input.nextLine();
        System.out.println("Where is the restaurant located? ");
        String location = input.nextLine();
        System.out.println("What is the type of cuisine? ");
        String cuisine = input.nextLine();
        System.out.println("What is the rating of the restaurant? ");
        double rating = input.nextDouble();
        System.out.println("Have we been here before? (True/False) ");
        boolean haveBeen = input.nextBoolean();


        restaurant = new Restaurant(name, rating, haveBeen, cuisine, location);

        restaurantList.addRestaurant(restaurant);

        System.out.println("You have successfully added a restaurant! ");


    }


    //MODIFIES: this
    //EFFECTS: Select a restaurant based on input
    private void doSelect() {

        if (restaurantList.getNumRestaurants() == 0) {
            System.out.println("No available restaurants to pick!");
            return;

        }

        System.out.println("Where would you like to go today: ");
        String restaurantLocation = input.next();
        System.out.println("What type of cuisine are you feeling? ");
        String restaurantCuisine = input.next();
        System.out.println("What is the minimum rating for the restaurant? ");
        double restaurantRating = input.nextDouble();
        System.out.println("Would you like to go somewhere you have been? (True/False) ");
        boolean haveBeen = input.nextBoolean();

        List<Restaurant> listOfRest = restaurantList.restaurantsCriteria(restaurantRating,
                restaurantCuisine, restaurantLocation,
                haveBeen);

        if (listOfRest.size() == 0) {
            System.out.println("We found no match for you! Please try again");
            return;
        }

        for (Restaurant r : listOfRest) {


            System.out.println("Let's go to " + r.getRestaurantName()
                    + " that is located in " + r.getRestaurantLocation());
        }

    }


    //MODIFIES: this
    //EFFECTS: view all restaurants that are added to the list
    private void doView() {

        if (restaurantList.getAllRestaurants().isEmpty()) {
            System.out.println("No restaurants available!");

        } else {
            System.out.println("List of all restaurants available: ");
            for (Restaurant r : restaurantList.getAllRestaurants()) {
                System.out.println("Restaurant name is: " + r.getRestaurantName()
                        + ", Restaurant location is " + r.getRestaurantLocation()
                        + ", Restaurant cuisine is " + r.getRestaurantCuisine()
                        + ", Restaurant rating is " + r.getRestaurantRating());

            }
        }


    }

    // EFFECTS: saves the restaurants to file
    private void saveRestaurants() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurantList);
            jsonWriter.close();
            System.out.println("Saved data to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads restaurants from file
    private void loadRestaurants() {
        try {
            restaurantList = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: print all the events to the console
    private void printEvents() {
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                int confirmedPane = JOptionPane.showConfirmDialog(null,
//                        "Are you sure you want to exit?", "Restaurant Generator",
//                        JOptionPane.YES_NO_OPTION);
//                if (confirmedPane == JOptionPane.YES_OPTION || confirmedPane == JOptionPane.NO_OPTION) {
//                    saveRestaurants();
//                }
//
//                for (Event events : EventLog.getInstance()) {
//                    System.out.println(events.toString() + "\n");
//                }
//                System.exit(0);
//            }
//        });
    }
}








