package ui.gui;

import model.Restaurants;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

//start the generator app
public class RunProgram extends JFrame implements ActionListener, ScreenAdjustment {
    private JFrame startPage;
    private JLabel welcome;
    private JLabel question;
    private JButton reload;
    private JButton newdata;
    private ImageIcon icon;
    private Image scaledicon;
    private Restaurants currentdata;

    //MODIFIES: this
    //EFFECTS: runs the first window that is on screen
    public RunProgram() {
        currentdata = new Restaurants();
        printWelcome();
        displayButton();

        JPanel screen = new JPanel();
        screen.add(welcome);
        screen.add(question);
        screen.add(reload);
        screen.add(newdata);
        screen.setBackground(Color.DARK_GRAY);
        screen.setAlignmentY(Component.CENTER_ALIGNMENT);

        startPage = new JFrame();
        screenAdjustment(screen, startPage);
        setUpExit();
    }


    public void setUpExit() {
        startPage.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("I am Exiting");
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Displays the welcome page
    private void printWelcome() {
        icon = new ImageIcon("data/img3.png");
        scaledicon = icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon logo = new ImageIcon(scaledicon);
        welcome = new JLabel(logo);
        welcome.setText("Hello! Let's find a restaurant!");
        welcome.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 20));
        textAdjustments(welcome);
        question = new JLabel("<html><br>Reload existing data?");
        question.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 15));
        textAdjustments(question);
    }

    // MODIFIES: this
    // EFFECTS: Display options as buttons to load saved data
    private void displayButton() {
        reload = new JButton("<html> Yes, I want to reload existing data");
        reload.setBounds(250, 320, 80, 50);
        reload.addActionListener(this);

        newdata = new JButton("<html> No, I want to make a new data");
        newdata.setBounds(250, 320, 80, 50);
        newdata.addActionListener(this);

    }

    // EFFECTS: Goes to next JFrame page when button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reload) {
            JsonReader profileReader = new JsonReader("./data/ReloadData.json");

            if (profileReader != null) {
                try {
                    currentdata = profileReader.read();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

            startPage.dispose();
            new Homepage(currentdata);
        }

        if (e.getSource() == newdata) {
            startPage.dispose();
            new Homepage(currentdata);
        }

    }




}
