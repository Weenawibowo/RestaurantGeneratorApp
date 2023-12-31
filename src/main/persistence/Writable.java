package persistence;

import org.json.JSONObject;

// Code is heavily influenced by the JsonSerizalizationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


// returns to JSON object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
