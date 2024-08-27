package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Tests for the Player Class
public class PlayerTest {

    @Test
    public void testConstructor(){
        Player testPlayer = new Player("Tristan", "M", "8");
        assertEquals("Tristan", testPlayer.getPlayerName());
        assertEquals("M", testPlayer.getPlayerPosition());
        assertEquals("8", testPlayer.getPlayerNumber());
        assertEquals(0, testPlayer.getGamesPlayed());
        assertEquals(0, testPlayer.getGoalsScored());
    }

    @Test
    public void testGoalAdd(){
        Player testPlayer = new Player("Tristan", "M", "8");
        testPlayer.scoredGoal();
        assertEquals(1, testPlayer.getGoalsScored());

    }
    @Test
    public void testGameAdd(){
        Player testPlayer = new Player("Tristan", "M", "8");
        testPlayer.playedGame();
        assertEquals(1, testPlayer.getGamesPlayed());

    }

    @Test
    public void testPlayerDisplay() {
        Player testPlayer = new Player("Tristan", "M", "8");
        assertEquals("Tristan - Position: M - Number: 8 - Games Played: 0 - Goals Scored: 0", testPlayer.displayPlayer());
    }

}
