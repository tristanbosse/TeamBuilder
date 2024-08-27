package model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

// Tests for the Team Class
public class TeamTest {

    @Test
    public void testConstructor(){
        Team testTeam = new Team("TFC");
        assertEquals("TFC", testTeam.getTeamName());
        assertEquals(0, testTeam.getRosterSize());
        assertEquals(new ArrayList<Player>(), testTeam.getTeamRoster());
        assertEquals(new ArrayList<Game>(), testTeam.getGameLog());
    }

    @Test
    public void testAddPlayer(){
        Team testTeam = new Team("TFC");
        testTeam.addPlayer(new Player("Tristan", "M", "8"));
        assertEquals(1, testTeam.getRosterSize());
        assertEquals("Tristan", testTeam.getTeamRoster().get(0).getPlayerName());
        assertEquals("M", testTeam.getTeamRoster().get(0).getPlayerPosition());
        assertEquals(8, testTeam.getTeamRoster().get(0).getPlayerNumber());
    }

    @Test
    public void testAddGame(){
        Team testTeam = new Team("TFC");
        testTeam.addGame(new Game("Chelsea", 3, 2));
        assertEquals("Chelsea", testTeam.getGameLog().get(0).getOpponentName());
        assertEquals(3, testTeam.getGameLog().get(0).getTeamScore());
        assertEquals(2, testTeam.getGameLog().get(0).getOpponentScore());
    }
}
