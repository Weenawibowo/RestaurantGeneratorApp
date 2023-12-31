package ui.gui;

import model.Restaurant;
import model.Restaurants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRestaurant extends JFrame implements ActionListener, ScreenAdjustment {
    private Restaurants resto;
    private JPanel panel;
    private JTextField nameBox = new JTextField(1);
    private JTextField cuisineBox = new JTextField(1);
    private JTextField locationBox = new JTextField(1);
    private JLabel invalidName = new JLabel();
    private JLabel invalidCuisine = new JLabel();
    private JLabel invalidLocation = new JLabel();
    private SpinnerNumberModel spinnerModel;
    private JSpinner rating;
    private JButton backButton;
    private JButton okButton;
    private JRadioButton yesbeenhere = new JRadioButton();
    private JRadioButton nohavenotbeenhere = new JRadioButton();
    private ButtonGroup groupbutton = new ButtonGroup();

    //MODIFIES: this
    public AddRestaurant(Restaurants resto) {
        this.resto = resto;
        panel = new JPanel();
        this.setSize(800, 800);
        add(panel);
        panel.setBackground(Color.DARK_GRAY);
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(20, 2));
        pane.setBackground(Color.DARK_GRAY);
        panel.setBackground(Color.DARK_GRAY);

        popUp(panel, this, 800, 800);

        printScreen(pane);

    }

    //MODIFIES: this
    //EFFECTS: print all components to the screen
    public void printScreen(Container pane) {
        nameHelper(pane);

        cuisineHelper(pane);

        locationHelper(pane);

        ratingHelper(pane);

        beenOrNotBeen(pane);


        okButton = new JButton("Ok");
        okButton.addActionListener(this);
        pane.add(okButton);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        pane.add(backButton);
        setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: beenorNotBeen helper button based on user input
    private void beenOrNotBeen(Container pane) {
        JLabel restaurantHaveBeen = new JLabel("Have you been here? ");
        restaurantHaveBeen.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantHaveBeen);
        pane.add(restaurantHaveBeen);

        yesbeenhere.setText("True");
        nohavenotbeenhere.setText("False");
        yesbeenhere.setBounds(120, 30, 120, 50);
        nohavenotbeenhere.setBounds(250, 30, 80, 50);
        restaurantHaveBeen.setBounds(20, 30, 150, 50);
        groupbutton.add(yesbeenhere);
        groupbutton.add(nohavenotbeenhere);
        pane.add(yesbeenhere);
        pane.add(nohavenotbeenhere);
    }

    //MODIFIES: this
    //EFFECTS: gives rating based on user input
    private void ratingHelper(Container pane) {
        JLabel restaurantRating = new JLabel("Restaurant rating: ");
        restaurantRating.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantRating);
        pane.add(restaurantRating);

        spinnerModel = new SpinnerNumberModel(1, 1, 5, 0.1);
        rating = new JSpinner(spinnerModel);
        pane.add(rating);
        pane.add(new JLabel());
    }

    //MODIFIES: this
    //EFFECTS: location based on user input
    private void locationHelper(Container pane) {
        JLabel restaurantLocation = new JLabel("Restaurant location: ");
        restaurantLocation.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantLocation);
        pane.add(restaurantLocation);

        locationBox = new JTextField(1);
        pane.add(locationBox);
        pane.add(invalidLocation);
    }

    //MODIFIES: this
    //EFFECTS: cuisine based on user input
    private void cuisineHelper(Container pane) {
        JLabel restaurantCuisine = new JLabel("Restaurant cuisine: ");
        restaurantCuisine.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantCuisine);
        pane.add(restaurantCuisine);

        cuisineBox = new JTextField(1);
        pane.add(cuisineBox);
        pane.add(invalidCuisine);
    }

    //MODIFIES: this
    //EFFECTS: restaurant name based on user input
    private void nameHelper(Container pane) {
        JLabel restaurantName = new JLabel("Restaurant name: ");
        restaurantName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantName);
        pane.add(restaurantName);

        nameBox = new JTextField(1);
        pane.add(nameBox);
        pane.add(invalidName);
    }

    // EFFECTS: Runs new JFrame windows based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            addNewRestaurant();
            this.dispose();
            new Homepage(resto);
        }

        if (e.getSource() == backButton) {
            this.dispose();
            new Homepage(resto);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates new restaurant
    private void addNewRestaurant() {
        String restaurantName = nameBox.getText();
        double restaurantRating = (double) rating.getValue();
        String restaurantLocation = locationBox.getText();
        String restaurantCuisine = cuisineBox.getText();
        boolean restaurantHaveBeen = yesbeenhere.isSelected();
        checkValid(restaurantName, restaurantLocation, restaurantCuisine);

        if (yesbeenhere.isSelected() && !restaurantName.isEmpty() && !restaurantLocation.isEmpty()
                && !restaurantCuisine.isEmpty()) {
            Restaurant thisrestohasbeen = new Restaurant(restaurantName, restaurantRating, restaurantHaveBeen,
                    restaurantCuisine, restaurantLocation);
            resto.addRestaurant(thisrestohasbeen);
        } else if (nohavenotbeenhere.isSelected() && !restaurantName.isEmpty() && !restaurantLocation.isEmpty()
                && !restaurantCuisine.isEmpty()) {
            Restaurant thisrestohasnotbeen = new Restaurant(restaurantName, restaurantRating, restaurantHaveBeen,
                    restaurantCuisine, restaurantLocation);
            resto.addRestaurant(thisrestohasnotbeen);
        }



    }

    // MODIFIES: this
    // EFFECTS: checks whether input is valid
    private void checkValid(String name, String location, String cuisine) {
        if (name.isEmpty() || location.isEmpty() || cuisine.isEmpty()) {
            invalidName.setText("Input is invalid");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidName.setForeground(Color.RED);
        }
    }
}




