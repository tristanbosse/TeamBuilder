package model;

import java.util.List;

import org.json.JSONObject;
import persistence.Writable;

// Object representing an individual Game's result, opponent, and goalscorers.
public class Game implements Writable {

    private String opponentName;
    private int teamScore;
    private int opponentScore;
    private List<String> goalScorers;
    private String result;

    // Requires: Valid oppName, teamScore, oppScore, and goalScorer values
    // Effects: Constructs an object of type Game with the given information
    public Game(String oppName, int teamScore, int oppScore) {
        this.opponentName = oppName;
        this.teamScore = teamScore;
        this.opponentScore = oppScore;
        this.result = this.determineResult();
    }

    public Game(String oppName, int teamScore, int oppScore, String result) {
        this.opponentName = oppName;
        this.teamScore = teamScore;
        this.opponentScore = oppScore;
        this.result = result;
    }

    public Game(String oppName, int teamScore, int oppScore, String result, List<String> goalScorers) {
        this.opponentName = oppName;
        this.teamScore = teamScore;
        this.opponentScore = oppScore;
        this.result = result;
        this.goalScorers = goalScorers;
    }


    public String getOpponentName() {
        return opponentName;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public String getResult() {
        return result;
    }

    // Requires: oppScore >= 0 and teamScore >= 0
    // Modifies: this
    // Effects: Determines whether the game was a win, loss, or draw depending on the score
    public String determineResult() {
        if (this.opponentScore > this.teamScore) {
            return ("Loss");
        } else if (this.opponentScore == this.teamScore) {
            return ("Draw");
        } else {
            return ("Win");
        }
    }

    // Requires: List of goalscorer names
    // Modifies: this
    // Effects: Takes a list of names and assigns them to the goalScorers variable
    public void setGoalScorers(List<String> goalScorers) {
        this.goalScorers = goalScorers;
    }

    public List<String> getGoalScorers() {
        return this.goalScorers;
    }

    //Effects: Displays the results of a game in a String
    public String displayGame() {
        return this.determineResult() + " vs " + this.getOpponentName()
                + " | Final Score: " + String.valueOf(getTeamScore()) + " - " + String.valueOf(getOpponentScore());
    }

    // EFFECTS: returns game in team game log as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Opponent", opponentName);
        json.put("Team Score", teamScore);
        json.put("Opponent Score", opponentScore);
        json.put("Goal Scorers", goalScorers);
        json.put("Result", result);
        return json;
    }


}
