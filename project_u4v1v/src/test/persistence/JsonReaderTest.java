package persistence;

import model.Game;
import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Much code inspired by JsonSerializationDemo on course GitHub
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Team team = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Team team = reader.read();
            assertEquals("TestTeam", team.getTeamName());
            assertEquals(0, team.getGamesPlayed());
            assertEquals(0, team.getRosterSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Team team = reader.read();
            assertEquals("TestTeam", team.getTeamName());
            assertEquals(2, team.getRosterSize());
            assertEquals(2, team.getGamesPlayed());
            ArrayList<Player> teamRoster = team.getTeamRoster();
            ArrayList<Game> gameLog = team.getGameLog();
            checkPlayer("Player1", "M", "10", 2, 1, teamRoster.get(0));
            checkPlayer("Player2", "D", "4", 2, 1, teamRoster.get(1));
            checkGame("Opposition", 2, 1, "Win", gameLog.get(0));
            checkGame("Opposition2", 0, 2, "Loss", gameLog.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}