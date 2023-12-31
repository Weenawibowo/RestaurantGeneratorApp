package ui.gui;

import model.Restaurant;
import model.Restaurants;

import javax.swing.table.AbstractTableModel;

//Custom table model based on user input
public class CustomModel extends AbstractTableModel {
    private String[] cols;
    private Restaurants resto;

    public CustomModel(Restaurants resto) {
        this.resto = resto;
        cols = new String[]{"Restaurant Name", "Restaurant Location", "Restaurant Cuisine",
                "Restaurant Rating", "Have we been here before?"};

    }

    //EFFECTS: get total number of restaurants
    @Override
    public int getRowCount() {
        return resto.getNumRestaurants();
    }

    //EFFECTS: get total number of columns
    @Override
    public int getColumnCount() {
        return cols.length;
    }

    //EFFECTS: get the value for each row
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Restaurant allrestos = resto.getRestaurantsForGui().get(rowIndex);
        switch (columnIndex) {
            case 0: return allrestos.getRestaurantName();
            case 1: return allrestos.getRestaurantLocation();
            case 2: return allrestos.getRestaurantCuisine();
            case 3: return allrestos.getRestaurantRating();
            case 4: return allrestos.isHaveBeen();
        }
        return null;
    }

    //EFFECTS: get column name
    @Override
    public String getColumnName(int column) {
        return cols[column];
    }


}
