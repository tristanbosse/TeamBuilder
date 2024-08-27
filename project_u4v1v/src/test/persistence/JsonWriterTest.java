package persistence;

import model.Game;
import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Much code inspired by JsonSerializationDemo on course GitHub
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Team team = new Team("Test Team");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Team team = new Team("TestTeam");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            team = reader.read();
            assertEquals("TestTeam", team.getTeamName());
            assertEquals(0, team.getRosterSize());
            assertEquals(0, team.getGamesPlayed());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Team team = new Team("TestTeam");
            team.addPlayer(new Player("Player1", "M", "10"));
            team.addPlayer(new Player("Player2", "D", "4"));
            team.addGame(new Game("Opposition1", 2, 1, "Win"));
            team.addGame(new Game("Opposition2", 0, 2, "Loss"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkRoom.json");
            Team newTeam = reader.read();
            assertEquals("TestTeam", newTeam.getTeamName());
            assertEquals(2, newTeam.getRosterSize());
            assertEquals(2, newTeam.getGamesPlayed());
            ArrayList<Player> teamRoster = newTeam.getTeamRoster();
            ArrayList<Game> gameLog = newTeam.getGameLog();
            checkPlayer("Player1", "M", "10", 0, 0, teamRoster.get(0));
            checkPlayer("Player2", "D", "4", 0, 0, teamRoster.get(1));
            checkGame("Opposition1", 2, 1, "Win", gameLog.get(0));
            checkGame("Opposition2", 0, 2, "Loss", gameLog.get(1));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}