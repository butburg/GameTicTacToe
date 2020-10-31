package tictactoe;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class GameException extends Exception {
    public GameException() {
        super();
    }

    public GameException(String msg) {
        super(msg);
    }

    public GameException(String msg, Throwable t) {
        super(msg, t);
    }
}
