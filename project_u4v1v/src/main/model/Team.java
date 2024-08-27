package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import model.EventLog;
import model.Event;
import java.util.ArrayList;

// Object representing the team
public class Team {

    private String teamName;
    private ArrayList<Player> teamRoster;
    private ArrayList<Game> gameLog;
    private int rosterSize;
    private int gamesPlayed;


    // Effects: Initializes an object of type team with an empty roster and game log
    public Team(String name) {
        this.teamName = name;
        this.teamRoster = new ArrayList<>();
        this.gameLog = new ArrayList<>();
        rosterSize = 0;
        EventLog.getInstance().clear();

    }

    // Requires: Player Object
    // Modifies: this
    // Effects: Adds player object to team roster
    public void addPlayer(Player p) {
        this.teamRoster.add(p);
        this.rosterSize += 1;
        EventLog.getInstance().logEvent(new Event("A Player was added"));

    }

    // Requires: Game object
    // Modifies: this
    // Effects: Adds game object to game log
    public void addGame(Game g) {
        this.gameLog.add(g);
        this.gamesPlayed += 1;
        EventLog.getInstance().logEvent(new Event("A game was played"));
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getRosterSize() {
        return this.rosterSize;
    }

    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    public ArrayList<Player> getTeamRoster() {
        return this.teamRoster;
    }

    public ArrayList<Game> getGameLog() {
        return this.gameLog;
    }



    // EFFECTS: returns the team as a JSONObject
    public JSONObject toJson() {
        JSONObject jsonTeam = new JSONObject();
        jsonTeam.put("Team Name", teamName);
        jsonTeam.put("Games Played", gamesPlayed);
        jsonTeam.put("Roster Size", rosterSize);
        jsonTeam.put("Team Roster", rosterToJson());
        jsonTeam.put("Game Log", gameLogToJson());

        return jsonTeam;
    }

    // EFFECTS: returns team roster a JSONArray
    private JSONArray rosterToJson() {
        JSONArray jsonRoster = new JSONArray();

        for (Player p : teamRoster) {
            jsonRoster.put(p.toJson());
        }

        return jsonRoster;
    }

    // EFFECTS: returns team gameLog as a JSONArray
    private JSONArray gameLogToJson() {
        JSONArray jsonGameLog = new JSONArray();

        for (Game g : gameLog) {
            jsonGameLog.put(g.toJson());
        }

        return jsonGameLog;
    }




}
