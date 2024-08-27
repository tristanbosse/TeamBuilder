package persistence;

import model.Team;
import model.Player;
import model.Game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Much code inspired by JsonSerializationDemo on course GitHub
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Team read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("Team Name");
        Team team = new Team(name);
        addRoster(team, jsonObject);
        addGameLog(team, jsonObject);
        return team;
    }

    // MODIFIES: team
    // EFFECTS: parses a roster from a JSONArray and adds the roster to the team
    private void addRoster(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Team Roster");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(team, nextPlayer);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses a game log for a JSONArray and adds the game log to the team
    private void addGameLog(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Game Log");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(team, nextGame);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses player from JSON object and adds it to the team roster
    private void addPlayer(Team team, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String position = jsonObject.getString("position");
        String number = jsonObject.getString("number");
        int gamesPlayed = jsonObject.getInt("games played");
        int goalsScored = jsonObject.getInt("goals scored");
        Player player = new Player(name, position, number, gamesPlayed, goalsScored);
        team.addPlayer(player);
    }

    // MODIFIES: team
    // EFFECTS: parses a game from JSONObject and adds it to the team
    private void addGame(Team team, JSONObject jsonObject) {
        String name = jsonObject.getString("Opponent");
        int teamScore = jsonObject.getInt("Team Score");
        int opponentScore = jsonObject.getInt("Opponent Score");
        String result = jsonObject.getString("Result");
        Game game = new Game(name, teamScore, opponentScore, result);
        team.addGame(game);
    }
}

