package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Tests for the Game class
public class GameTest {

    @Test
    public void testConstructor(){
        Game testGame = new Game("Chelsea", 2, 3);
        assertEquals("Chelsea", testGame.getOpponentName());
        assertEquals(2, testGame.getTeamScore());
        assertEquals(3, testGame.getOpponentScore());
    }

    @Test
    public void testResult(){
        Game loss = new Game("opp1", 0, 1);
        Game draw = new Game("opp2", 1, 1);
        Game win = new Game("opp3", 1, 0);
        assertEquals("Loss", loss.determineResult());
        assertEquals("Draw", draw.determineResult());
        assertEquals("Win", win.determineResult());
    }

    @Test
    public void testDisplayGame(){
        Game testGame = new Game("Chelsea", 2, 3);
        assertEquals("Loss vs Chelsea | Final Score: 2 - 3", testGame.displayGame());
    }


}
