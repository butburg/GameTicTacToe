package tictactoe;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public interface TicTacToe {
    /**
     * Pick a symbol
     *
     * @param username     username for the player
     * @param wantedSymbol user asks for using this symbol, can be a race condition (who picks first the symbol!)
     * @return the selected symbol
     * @throws GameException wenn bereits zwei Benutzer angemeldet sind
     * @throws StatusException can occure when game hasn't started yet
     */
    TTTSymbol pick(String username, TTTSymbol wantedSymbol) throws GameException, StatusException;
}
