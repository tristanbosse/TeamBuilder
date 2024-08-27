package model;

import org.json.JSONObject;
import persistence.Writable;
// Object representing individual players and their information

public class Player implements Writable {

    private String playerName;
    private String playerPosition;
    private String playerNumber;
    private int gamesPlayed;
    private int goalsScored;

    // Requires: Valid name, position, and number information
    // Effects: Constructs a player object with given values
    public Player(String name, String position, String number) {
        this.playerName = name;
        this.playerPosition = position;
        this.playerNumber = number;
        this.gamesPlayed = 0;
        this.goalsScored = 0;
    }

    public Player(String name, String position, String number, int gamesPlayed, int goalsScored) {
        this.playerName = name;
        this.playerPosition = position;
        this.playerNumber = number;
        this.gamesPlayed = gamesPlayed;
        this.goalsScored = goalsScored;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    // Modifies: this
    // Effects: Adds 1 to a players' goal tally
    public void scoredGoal() {
        this.goalsScored += 1;
    }

    // Modifies: this
    // Effects: Adds 1 to a players' game tally
    public void playedGame() {
        this.gamesPlayed += 1;
    }

    // Effects: Returns a player's information in a one-line string
    public String displayPlayer() {
        return playerName + "  |  Position: " + playerPosition + "  |  Number: " + String.valueOf(playerNumber)
                + "  |  Games Played: " + String.valueOf(gamesPlayed);
    }

    // EFFECTS: returns player in the roster as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", playerName);
        json.put("position", playerPosition);
        json.put("number", playerNumber);
        json.put("games played", gamesPlayed);
        json.put("goals scored", goalsScored);
        return json;
    }

}