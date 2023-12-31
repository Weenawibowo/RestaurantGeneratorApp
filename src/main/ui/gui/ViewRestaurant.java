package ui.gui;

import model.Restaurants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//EFFECTS: view all the restaurant saved in JSON so far
public class ViewRestaurant extends JFrame implements ActionListener, ScreenAdjustment {
    private JPanel panel;
    private CustomModel model;
    private Restaurants resto;
    private JTable table;
    private JButton backbutton;

    public ViewRestaurant(Restaurants resto) {
        this.resto = resto;
        model = new CustomModel(resto);
        panel = new JPanel();
        this.add(panel);
        this.setSize(800, 500);
        panel.setBackground(Color.DARK_GRAY);
        table = new JTable(model);
        backbutton = new JButton();
        this.setTitle("All Restaurants:");
        backbutton = new JButton("Back");
        backbutton.addActionListener(this);
        panel.add(backbutton);
        panel.add(table);
        panel.setLayout(new GridLayout(2, 1)); // 2 rows, 1 column
        setVisible(true);
        panel.add(new JScrollPane(table));

    }


    // EFFECTS: Runs new JFrame windows based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backbutton) {
            this.dispose();
            new Homepage(resto);
        }
    }

}

