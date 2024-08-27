package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;


public class TeamApp {

    private static final String JSON_STORE = "./data/team.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Team team;
    private Scanner input;


    public TeamApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        String loadStatus = null;
        System.out.println("- Input 'n' to create new team ");
        System.out.println("- Input 'l' to load previous team on file ");
        loadStatus = input.next();
        if (loadStatus.equals("n")) {
            System.out.println("Enter your team name: ");
            String teamName = input.next();
            team = new Team(teamName);
        } else if (loadStatus.equals("l")) {
            loadWorkRoom();
        }
        runApp();
    }

    // Requires: User to follow input instructions
    // Effects: Main method that runs the user interaction
    private void runApp() {
        input = new Scanner(System.in);
        String command = null;
        boolean runProgram = true;

        while (runProgram) {
            displayCommand();
            command = input.next();

            if (command.equals("q")) {
                System.out.println("Would you like to save your current team info (Input Y for yes or N for no)");
                String saveOrNot = null;
                saveOrNot = input.next();
                if (saveOrNot.equals("Y")) {
                    saveWorkRoom();
                }
                for (Iterator<Event> it = EventLog.getInstance().iterator(); it.hasNext(); ) {
                    Event e = it.next();
                    System.out.println(e.toString());
                }
                runProgram = false;
            } else {
                processCommand(command);
            }


        }
    }


    private void processCommand(String command) {
        if (command.equals("p")) {
            addPlayerInfo();
        } else if (command.equals("g")) {
            addGameInfo();
        } else if (command.equals("r")) {
            displayRoster();
        } else if (command.equals("l")) {
            displayGameLog();
        } else if (command.equals("s")) {
            saveWorkRoom();
        }

    }

    private void displayCommand() {
        System.out.println("- Input 'p' to add player ");
        System.out.println("- Input 'g' to add game ");
        System.out.println("- Input 'r' to display roster ");
        System.out.println("- Input 'l' to display game log ");
        System.out.println("- Input 's' to save team to file ");
        System.out.println("- Input 'q' to quit this program ");
    }


    // Requires: Initialized Team Object
    // Modifies: Team
    // Effects: Adds a new Player to the team roster given user input information
    private void addPlayerInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player Name: ");
        String playerName = sc.nextLine();
        System.out.println("Player Position (GK, D, M, F): ");
        String playerPosition = sc.nextLine();
        System.out.println("Player Number: ");
        String playerNumber = sc.nextLine();
        team.addPlayer(new Player(playerName, playerPosition, playerNumber));
        System.out.println("Player Added Successfully");
    }

    // Requires: Initialized Team Object, Scores >= 0
    // Modifies: Team
    // Effects: Creates an object of type Game and adds it to the team's game log
    private void addGameInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Opponent Name: ");
        String opponentName = sc.nextLine();
        System.out.println("Team Score: ");
        int teamScore = sc.nextInt();
        System.out.println("Opponent Score: ");
        int opponentScore = sc.nextInt();
        ArrayList<String> scorers = new ArrayList<>();
        if (teamScore > 0) {
            for (int i = 0; i < teamScore; i++) {
                System.out.println("Scorer of goal #" + String.valueOf(i + 1));
                String scorer = sc.next();
                scorers.add(scorer);
            }
        }
        Game temp = new Game(opponentName, teamScore, opponentScore);
        temp.setGoalScorers(scorers);
        updateTeamInfo(team, temp);
        team.addGame(temp);
        System.out.println("Game added successfully");
    }

    // Requires: Initialized Team object with atleast one player
    // Effects: Prints out the team's roster to the display
    private void displayRoster() {
        if (team.getRosterSize() == 0) {
            System.out.println("No players on the roster currently");
            return;
        }
        System.out.println(team.getTeamName() + " Roster:");
        for (int i = 0; i < team.getRosterSize(); i++) {
            System.out.println(team.getTeamRoster().get(i).displayPlayer());
        }
        System.out.println("\n");
    }

    // Requires: Initialized Team object with atleast one game
    // Effects: Prints out the team's game log to the display
    private void displayGameLog() {
        if (team.getGamesPlayed() == 0) {
            System.out.println("No games played yet");
            return;
        }
        System.out.println(team.getTeamName() + " GameLog: ");
        for (int i = 0; i < team.getGamesPlayed(); i++) {
            System.out.println(team.getGameLog().get(i).displayGame());
        }
        System.out.println("\n");
    }

    // Requires: Initialized Team and game objects
    // Modifies: Player, Team
    // Effects: Updates the individual statistics of each player based on the info of a game
    private void updateTeamInfo(Team team, Game game) {
        for (int i = 0; i < team.getRosterSize(); i++) {
            team.getTeamRoster().get(i).playedGame();
        }
        for (int i = 0; i < game.getGoalScorers().size(); i++) {
            for (int j = 0; j < team.getRosterSize(); j++) {
                if (game.getGoalScorers().get(i).equals(team.getTeamRoster().get(j).getPlayerName())) {
                    team.getTeamRoster().get(j).scoredGoal();
                }
            }

        }
    }


    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();
            System.out.println("Saved " + team.getTeamName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadWorkRoom() {
        try {
            team = jsonReader.read();
            System.out.println("Loaded " + team.getTeamName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

