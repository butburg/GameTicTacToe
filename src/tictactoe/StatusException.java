package tictactoe;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class StatusException extends Exception {
    public StatusException() {
        super();
    }

    public StatusException(String msg) {
        super(msg);
    }

    public StatusException(String msg, Throwable t) {
        super(msg, t);
    }
}
