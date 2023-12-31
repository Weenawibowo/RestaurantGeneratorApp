package ui.gui;

import model.Restaurant;
import model.Restaurants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChooseRestaurant extends JFrame implements ActionListener, ScreenAdjustment {
    private Restaurants resto;
    private JPanel panel;
    private JTextField cuisineBox = new JTextField(1);
    private JTextField locationBox = new JTextField(1);
    private JLabel invalidCuisine = new JLabel();
    private JLabel invalidLocation = new JLabel();
    private SpinnerNumberModel spinnerModel;
    private JLabel invalidName = new JLabel();
    private JSpinner rating;
    private JButton resetButton;
    private JButton findButton;
    private JButton backButton;
    private JRadioButton yesbeenhere = new JRadioButton();
    private JRadioButton nohavenotbeenhere = new JRadioButton();
    private ButtonGroup groupbutton = new ButtonGroup();
    private JTable table;

    //MODIFIES: this
    public ChooseRestaurant(Restaurants resto) {
        this.resto = resto;

        //table = new JTable(Restaurant resto, columnNames);

        panel = new JPanel();
        this.setSize(1000, 1000);
        add(panel);
        panel.setBackground(Color.DARK_GRAY);
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(22, 2));
        pane.setBackground(Color.DARK_GRAY);
        panel.setBackground(Color.DARK_GRAY);
        popUp(panel, this, 1000, 1000);
        table = new JTable();
        setVisible(true);

        printScreen(pane);

    }

    //MODIFIES: this
    //EFFECTS: print components to the screen
    public void printScreen(Container pane) {
        cousinHelper(pane);

        locationHelper(pane);

        ratingHelper(pane);

        beenorNotBeenHelper(pane);


        findButton = new JButton("Find me a restaurant");
        findButton.addActionListener(this);
        pane.add(findButton);
        resetButton = new JButton("Generate a new restaurant");
        resetButton.addActionListener(this);
        pane.add(resetButton);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        pane.add(backButton);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        panel.add(table);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        //scrollPane.setPreferredSize(new Dimension(800, 500));
        setVisible(true);
        revalidate();
        repaint();



    }

    //MODIFIES: this
    //EFFECTS: beenorNotBeen helper button based on user input
    private void beenorNotBeenHelper(Container pane) {
        JLabel restaurantHaveBeen = new JLabel("Would you like to go somewhere you have been?");
        restaurantHaveBeen.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantHaveBeen);
        pane.add(restaurantHaveBeen);

        yesbeenhere.setText("Yes");
        nohavenotbeenhere.setText("No");
        yesbeenhere.setBounds(120, 30, 120, 50);
        nohavenotbeenhere.setBounds(250, 30, 80, 50);
        restaurantHaveBeen.setBounds(20, 30, 150, 50);
        groupbutton.add(yesbeenhere);
        groupbutton.add(nohavenotbeenhere);
        pane.add(yesbeenhere);
        pane.add(nohavenotbeenhere);
    }

    //MODIFIES: this
    //EFFECTS: choose minimum rating based on user input
    private void ratingHelper(Container pane) {
        JLabel restaurantRating = new JLabel("What is the minimum rating? ");
        restaurantRating.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantRating);
        pane.add(restaurantRating);

        spinnerModel = new SpinnerNumberModel(1, 1, 5, 0.1);
        rating = new JSpinner(spinnerModel);
        pane.add(rating);
        pane.add(new JLabel());
    }

    //MODIFIES: this
    //EFFECTS: choose location based on user input
    private void locationHelper(Container pane) {
        JLabel restaurantLocation = new JLabel("Which city would you like to go today? ");
        restaurantLocation.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantLocation);
        pane.add(restaurantLocation);

        locationBox = new JTextField(1);
        pane.add(locationBox);
        pane.add(invalidLocation);
    }

    //MODIFIES: this
    //EFFECTS: choose cuisine based on user input
    private void cousinHelper(Container pane) {
        JLabel restaurantCuisine = new JLabel("What cuisine are you feeling? ");
        restaurantCuisine.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(restaurantCuisine);
        pane.add(restaurantCuisine);

        cuisineBox = new JTextField(1);
        pane.add(cuisineBox);
        pane.add(invalidCuisine);
    }

    // EFFECTS: Runs new JFrame windows based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findButton) {
            findRestaurant();
        }

        if (e.getSource() == resetButton) {
            super.getContentPane().removeAll();
            printScreen(getContentPane());
            revalidate();
            repaint();
        }

        if (e.getSource() == backButton) {
            this.dispose();
            new Homepage(resto);
        }
    }

    //MODIFIES: this
    //EFFECTS: find a restaurant based on all the criterias from user input
    public void findRestaurant() {
        String restaurantLocation = locationBox.getText();
        String restaurantCuisine = cuisineBox.getText();
        double restaurantRating = (double) rating.getValue();
        boolean restaurantHaveBeen = yesbeenhere.isSelected();
        checkValid(restaurantLocation, restaurantCuisine);
        List<Restaurant>  fitscriteria = resto.restaurantsCriteria(restaurantRating,
                restaurantCuisine, restaurantLocation, restaurantHaveBeen);
        String[] cols = new String[]{"Restaurant Name", "Restaurant Location", "Restaurant Cuisine",
                "Restaurant Rating", "Have we been here before?"};
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.setColumnIdentifiers(cols);
        for (Restaurant resto : fitscriteria) {
            Object[] o = new Object[5];
            o[0] = resto.getRestaurantName();
            o[1] = resto.getRestaurantLocation();
            o[2] = resto.getRestaurantCuisine();
            o[3] = resto.getRestaurantRating();
            o[4] = resto.isHaveBeen();
            model.addRow(o);

        }


    }

    // MODIFIES: this
    // EFFECTS: checks whether input is valid
    private void checkValid(String location, String cuisine) {
        if (location.isEmpty() || cuisine.isEmpty()) {
            invalidName.setText("Input is invalid");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidName.setForeground(Color.RED);
        }
    }


}
