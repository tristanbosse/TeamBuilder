package persistence;

import model.Player;
import model.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Much code inspired by JsonSerializationDemo on course GitHub
public class JsonTest {

    protected void checkPlayer(String name, String position, String number, int gamesPlayed, int goalsScored, Player player) {
        assertEquals(name, player.getPlayerName());
        assertEquals(position, player.getPlayerPosition());
        assertEquals(number, player.getPlayerNumber());
        assertEquals(gamesPlayed, player.getGamesPlayed());
        assertEquals(goalsScored, player.getGoalsScored());
    }

    protected void checkGame(String opponent, int teamScore, int opponentScore, String result, Game game) {
        assertEquals(opponent, game.getOpponentName());
        assertEquals(teamScore, game.getTeamScore());
        assertEquals(opponentScore, game.getOpponentScore());
        assertEquals(result, game.getResult());
    }

}
