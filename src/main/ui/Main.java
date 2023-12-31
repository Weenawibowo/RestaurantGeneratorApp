package ui;

import java.io.FileNotFoundException;

// code is inspired by :
// https://stackoverflow.com/questions/61401974/windowclosing-method-is-not-getting-called
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

public class Main {
    public static void main(String[] args) {
        try {
            new RestaurantGenerator();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

