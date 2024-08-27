package persistence;

import org.json.JSONObject;

// Much code inspired by JsonSerializationDemo on course GitHub
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
