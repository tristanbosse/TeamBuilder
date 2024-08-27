package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import persistence.JsonReader;
import persistence.JsonWriter;


public class GuiMainFrame extends JFrame implements ActionListener {

    // Initializing variables
    static final String JSON_STORE = "./data/team.json";
    JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    JsonReader jsonReader = new JsonReader(JSON_STORE);
    Team team;

    // Initializing JPanels
    JPanel homeScreenPanel = new JPanel();
    JPanel commandScreenPanel = new JPanel();
    JPanel rosterDisplay = new JPanel();
    JPanel gameLogDisplay = new JPanel();
    JPanel addPlayerPanel = new JPanel();
    JPanel addGameDisplay = new JPanel();

    // Home Screen
    JButton newTeamButton = new JButton("Create New Team");
    JButton loadTeamButton = new JButton("Load Saved Team");
    JButton saveTeam = new JButton("Save Team");
    JButton loadTeam = new JButton("Load Team");

    // Command Screen
    JButton addPlayer = new JButton("Add Player");
    JButton addGame = new JButton("Add Game");
    JButton seeRoster = new JButton("Roster");
    JButton seeGameLog = new JButton("Game Log");

    // Display Roster Screen
    JLabel rosterLabel = new JLabel();
    JButton return2 = new JButton("Return");

    // Game Log Screen
    JLabel gameLogLabel = new JLabel();
    JButton return3 = new JButton("Return");

    // Add Player
    JLabel nameLabel = new JLabel("Name :");
    JLabel positionLabel = new JLabel("Position (F, M, D, GK):");
    JLabel numberLabel = new JLabel("Number:");
    JTextField nameInput = new JTextField();
    JTextField positionInput = new JTextField();
    JTextField numberInput = new JTextField();
    JButton savePlayerButton = new JButton("Save Player to Roster");
    JButton return1 = new JButton("Return");

    // Add Game
    JTextField opponentInput = new JTextField();
    JTextField teamScoreInput = new JTextField();
    JTextField opponentScoreInput = new JTextField();
    JLabel opponentNameLabel = new JLabel("Opponent:");
    JLabel teamScoreLabel = new JLabel("Team Score:");
    JLabel opponentScoreLabel = new JLabel("Opponent Score:");
    JButton saveGameButton = new JButton("Save Game");
    JButton return4 = new JButton("Return");

    // EFFECTS: Initializes frame and all panels
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public GuiMainFrame() {

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(800, 800);
        setVisible(true);
        setLayout(null);

        // HomeScreenPanel
        homeScreenPanel.setBounds(0,0,800,800);
        homeScreenPanel.setLayout(null);
        newTeamButton.setBounds(460,420,200,70);
        loadTeamButton.setBounds(140,420,200,70);
        newTeamButton.addActionListener(this);
        loadTeamButton.addActionListener(this);
        homeScreenPanel.add(newTeamButton);
        homeScreenPanel.add(loadTeamButton);
        ImageIcon image1 = new ImageIcon("soccer ball.png");
        JLabel imageLabel = new JLabel(image1);
        imageLabel.setBounds(200,0,400,400);
        homeScreenPanel.add(imageLabel);
        add(homeScreenPanel);

        // Commands Panel
        commandScreenPanel.setBounds(0,0,800,800);
        commandScreenPanel.setLayout(null);
        seeRoster.setBounds(140, 100, 520,140);
        seeRoster.addActionListener(this);
        seeGameLog.setBounds(140, 260, 520,140);
        seeGameLog.addActionListener(this);
        addPlayer.setBounds(140, 420,520,140);
        addPlayer.addActionListener(this);
        addGame.setBounds(140,580,520,140);
        addGame.addActionListener(this);
        loadTeam.setBounds(700,0,100,40);
        loadTeam.addActionListener(this);
        saveTeam.setBounds(0,0,100,40);
        saveTeam.addActionListener(this);
        commandScreenPanel.add(loadTeam);
        commandScreenPanel.add(seeRoster);
        commandScreenPanel.add(seeGameLog);
        commandScreenPanel.add(addPlayer);
        commandScreenPanel.add(addGame);
        commandScreenPanel.add(saveTeam);
        add(commandScreenPanel);

        // Add Player
        addPlayerPanel.setBounds(0,0,800,800);
        addPlayerPanel.setLayout(null);
        nameInput.setBounds(140, 160, 520,50);
        positionInput.setBounds(140, 260, 520,50);
        numberInput.setBounds(140, 360, 520,50);
        savePlayerButton.setBounds(140, 580, 520,120);
        nameLabel.setBounds(140,110,520,50);
        positionLabel.setBounds(140, 210, 520, 50);
        numberLabel.setBounds(140,310,520,50);
        savePlayerButton.addActionListener(this);
        return1.setBounds(700,0,100,40);
        return1.addActionListener(this);

        addPlayerPanel.add(nameLabel);
        addPlayerPanel.add(positionLabel);
        addPlayerPanel.add(numberLabel);
        addPlayerPanel.add(nameInput);
        addPlayerPanel.add(positionInput);
        addPlayerPanel.add(numberInput);
        addPlayerPanel.add(savePlayerButton);
        addPlayerPanel.add(return1);
        add(addPlayerPanel);

        // add game
        addGameDisplay.setBounds(0,0,800,800);
        addGameDisplay.setLayout(null);
        opponentInput.setBounds(140, 160, 520,50);
        teamScoreInput.setBounds(140, 260, 520,50);
        opponentScoreInput.setBounds(140, 360, 520,50);
        saveGameButton.setBounds(140, 580, 520,120);
        opponentNameLabel.setBounds(140,110,520,50);
        teamScoreLabel.setBounds(140, 210, 520, 50);
        opponentScoreLabel.setBounds(140,310,520,50);
        return4.setBounds(700,0,100,40);
        return4.addActionListener(this);
        addGameDisplay.add(return4);
        saveGameButton.addActionListener(this);
        addGameDisplay.add(opponentInput);
        addGameDisplay.add(opponentNameLabel);
        addGameDisplay.add(teamScoreInput);
        addGameDisplay.add(teamScoreLabel);
        addGameDisplay.add(opponentScoreInput);
        addGameDisplay.add(opponentScoreLabel);
        addGameDisplay.add(saveGameButton);
        add(addGameDisplay);

        // roster display
        rosterDisplay.setBounds(0,0,800,800);
        rosterDisplay.setLayout(null);
        rosterLabel.setText(" ");
        rosterLabel.setBounds(100,100,600,600);
        rosterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rosterLabel.setVerticalAlignment(SwingConstants.TOP);
        rosterDisplay.add(rosterLabel);
        return2.setBounds(700,0,100,40);
        return2.addActionListener(this);
        rosterDisplay.add(return2);
        JLabel rosterTitle = new JLabel("Team Roster");
        rosterTitle.setBounds(100,50,600,600);
        rosterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rosterTitle.setVerticalAlignment(SwingConstants.TOP);
        rosterDisplay.add(rosterTitle);
        add(rosterDisplay);

        // Game log
        gameLogDisplay.setBounds(0,0,800,800);
        gameLogDisplay.setLayout(null);
        gameLogLabel.setBounds(100,100,600,600);
        gameLogLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameLogLabel.setVerticalAlignment(SwingConstants.TOP);
        rosterDisplay.add(rosterLabel);
        return3.setBounds(700,0,100,40);
        return3.addActionListener(this);
        gameLogDisplay.add(return3);
        JLabel gameLogTitle = new JLabel("Game Log");
        gameLogTitle.setBounds(100,50,600,600);
        gameLogTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameLogTitle.setVerticalAlignment(SwingConstants.TOP);
        gameLogDisplay.add(gameLogTitle);
        gameLogDisplay.add(gameLogLabel);
        add(gameLogDisplay);

        addWindowListener(new WindowAdapter() {

            @Override

            public void windowClosing(WindowEvent e) {
                for (Iterator<Event> it = EventLog.getInstance().iterator(); it.hasNext(); ) {
                    Event ev = it.next();
                    System.out.println(ev.toString());
                }
                System.exit(0);

            }

        });






        // Setting panel visibilities
        homeScreenPanel.setVisible(true);
        commandScreenPanel.setVisible(false);
        rosterDisplay.setVisible(false);
        addPlayerPanel.setVisible(false);
        addGameDisplay.setVisible(false);
        gameLogDisplay.setVisible(false);



    }

    // REQUIRES: Valid action event e
    // MODIFIES: this, Team object
    // EFFECTS: Takes action events as input and modifies the frame and team accordingly
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newTeamButton) {
            team = new Team("Team");
            homeScreenPanel.setVisible(false);
            commandScreenPanel.setVisible(true);
        } else if (e.getSource() == loadTeamButton) {
            loadWorkRoom();
            EventLog.getInstance().clear();
            homeScreenPanel.setVisible(false);
            commandScreenPanel.setVisible(true);
        } else if (e.getSource() == seeRoster) {
            String rosterString = generateRosterString();
            rosterLabel.setText(rosterString);
            commandScreenPanel.setVisible(false);
            rosterDisplay.setVisible(true);
        } else if (e.getSource() == addPlayer) {
            commandScreenPanel.setVisible(false);
            addPlayerPanel.setVisible(true);
        } else if (e.getSource() == return1) {
            returnToCommandScreen();
        } else if (e.getSource() == savePlayerButton) {
            String name = nameInput.getText();
            String position = positionInput.getText();
            String number = numberInput.getText();
            team.addPlayer(new Player(name, position, number));
            nameInput.setText("");
            positionInput.setText("");
            numberInput.setText("");
            addPlayerPanel.setVisible(false);
            commandScreenPanel.setVisible(true);
        } else if (e.getSource() == saveTeam) {
            saveWorkRoom();
        } else if (e.getSource() == addGame) {
            commandScreenPanel.setVisible(false);
            addGameDisplay.setVisible(true);
        } else if (e.getSource() == saveGameButton) {
            String opponentName = opponentInput.getText();
            int teamScore = Integer.valueOf(teamScoreInput.getText());
            int opponentScore = Integer.valueOf(opponentScoreInput.getText());
            team.addGame(new Game(opponentName, teamScore, opponentScore));
            for (Player player : team.getTeamRoster()) {
                player.playedGame();
            }
            opponentInput.setText("");
            teamScoreInput.setText("");
            opponentScoreInput.setText("");
            addGameDisplay.setVisible(false);
            commandScreenPanel.setVisible(true);
        } else if (e.getSource() == seeGameLog) {
            String gameLogString = generateGameLogString();
            gameLogLabel.setText(gameLogString);
            commandScreenPanel.setVisible(false);
            gameLogDisplay.setVisible(true);
        } else if (e.getSource() == return2) {
            returnToCommandScreen();
        } else if (e.getSource() == return3) {
            returnToCommandScreen();
        } else if (e.getSource() == return4) {
            returnToCommandScreen();
        } else if (e.getSource() == loadTeam) {
            loadWorkRoom();
        }

    }

    // MODIFES: Team
    // EFFECTS: Loads Workroom from JSON Data
    private void loadWorkRoom() {
        try {
            team = jsonReader.read();
            System.out.println("Loaded " + team.getTeamName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Saves workroom to JSON Data
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

    public static void main(String[] args) {
        new GuiMainFrame();
    }

    // EFFECTS: Generates player information as a String
    public String generateRosterString() {
        int i;
        String rosterString = "<html>";
        for (i = 0; i < team.getRosterSize(); i++) {
            rosterString = rosterString + team.getTeamRoster().get(i).displayPlayer() + "<br/>";

        }
        rosterString = rosterString + "</html>";
        return rosterString;

    }

    // EFFECTS: Generates game information as a string
    public String generateGameLogString() {
        int i;
        String gameLogString = "<html>";
        for (i = 0; i < team.getGamesPlayed(); i++) {
            gameLogString = gameLogString + team.getGameLog().get(i).displayGame() + "<br/>";

        }
        gameLogString = gameLogString + "</html>";
        return gameLogString;

    }

    // EFFECTS: hides all other panels except for command panel
    public void returnToCommandScreen() {
        homeScreenPanel.setVisible(false);
        commandScreenPanel.setVisible(true);
        rosterDisplay.setVisible(false);
        addPlayerPanel.setVisible(false);
        addGameDisplay.setVisible(false);
        gameLogDisplay.setVisible(false);
    }



}