package ui.gui;

import model.Event;
import model.EventLog;
import model.Restaurants;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Homepage extends JFrame implements ActionListener, ScreenAdjustment {
    private JFrame home;
    private JPanel panel;
    private Container pane;
    private JLabel greetings;
    private JButton addRestaurantButton;
    private JButton viewRestaurantButton;
    private JButton chooseRestaurantButton;
    private JPanel saveData;
    private JButton saveButton;
    private Restaurants allrestos;


    //EFFECTS: runs the homepage
    public Homepage(Restaurants allrestos) {
        this.allrestos = allrestos;
        home = new JFrame();
        panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridLayout(0, 1));
        pane = this.getContentPane();
        pane.setLayout(new GridLayout(0, 1));
        pane.setBackground(Color.DARK_GRAY);
        printScreen(panel, pane);
        //this.add(pane);
        setVisible(true);
        setUpExit();
    }







    public void setUpExit() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                for (Event events : EventLog.getInstance()) {
                    System.out.println(events.toString() + "\n");
                }
                System.exit(0);

            }
        });
    }



    //MODIFIES: this
    //EFFECTS: print components
    public void printScreen(JPanel panel, Container pane) {
        greetings = new JLabel("Hello there !");
        greetings.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 20));
        textAdjustments(greetings);
        pane.add(greetings);

        addRestaurantButton = new JButton("Add a restaurant");
        addRestaurantButton.addActionListener(this);
        pane.add(addRestaurantButton);

        viewRestaurantButton = new JButton("View all restaurants");
        viewRestaurantButton.addActionListener(this);
        pane.add(viewRestaurantButton);

        chooseRestaurantButton = new JButton("Choose a restaurant");
        chooseRestaurantButton.addActionListener(this);
        pane.add(chooseRestaurantButton);

        saveFunction();

        pane.add(saveData);
        panel.add(pane);
        screenAdjustment(panel, home);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollBar(scrollPane, home);

    }

    // MODIFIES: this
    // EFFECTS: writes data into JSON file or exit based on user input
    private void saveFunction() {
        saveData = new JPanel(new GridLayout(2, 1));
        saveData.setBackground(Color.DARK_GRAY);
        JLabel save = new JLabel("Save data before exit?");
        textAdjustments(save);
        saveData.add(save);

        saveButton = new JButton("Yes, save my progress");
        saveButton.addActionListener(this);
        saveData.add(saveButton);
    }

    //MODIFIES: this
    // EFFECTS: goes to new JFrame page based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addRestaurantButton) {
            this.dispose();
            setVisible(false);
            new AddRestaurant(allrestos);
        }

        if (e.getSource() == viewRestaurantButton) {
            this.dispose();
            new ViewRestaurant(allrestos);
        }

        if (e.getSource() == chooseRestaurantButton) {
            this.dispose();
            new ChooseRestaurant(allrestos);
        }

        if (e.getSource() == saveButton) {
            JsonWriter jsonWriter = new JsonWriter("./data/ReloadData.json");
            try {
                jsonWriter.open();
                jsonWriter.write(allrestos);
                jsonWriter.close();
            } catch (IOException ex) {
                System.out.println("invalid");
            }
        }
    }

}
