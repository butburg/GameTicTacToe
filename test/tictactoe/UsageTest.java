package tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class UsageTest {


    private static final String P1 = "Bertha";
    private static final String P2 = "Hans";
    private static final String P3 = "Kevin";

    private TicTacToe getTicTacToe() {
        return new TicTacToeImpl();
    }

    /*----------------good tests-------------------------*/

    @Test
    public void goodPickSymbolSingle() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.O);
        assertEquals(TTTSymbol.O, p1Symbol);
    }

    @Test
    public void goodPickSymbolBoth() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.O);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.X);
        assertEquals(TTTSymbol.O, p1Symbol);
        assertEquals(TTTSymbol.X, p2Symbol);
    }

    @Test
    public void goodPickSymbolBothContest1() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.O);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);
        assertEquals(TTTSymbol.O, p1Symbol);
        assertEquals(TTTSymbol.X, p2Symbol);
    }

    @Test
    public void goodPickSymbolBothContest2() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.X);
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        assertEquals(TTTSymbol.X, p2Symbol);
        assertEquals(TTTSymbol.O, p1Symbol);
    }

    @Test
    public void goodPickSymbolAgain() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.O);
        //reconsidered (==think over)
        p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.X);
        assertEquals(TTTSymbol.X, p1Symbol);
        assertEquals(TTTSymbol.O, p2Symbol);
    }

    /*----------------good tests-------------------------*/
    /*----------------exception tests-------------------------*/

    @Test()
    public void failurePickSymbol3Times() {
        TicTacToe ttt = this.getTicTacToe();
        assertThrows(StatusException.class, () -> {
            ttt.pick(P1, TTTSymbol.X);
            ttt.pick(P2, TTTSymbol.X);
            ttt.pick(P3, TTTSymbol.X);
        });
    }

    /*----------------exception tests-------------------------*/

    /*----------------set tests-------------------------*/
    /*----------------good tests-------------------------*/

    @Test
    public void good1() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);

        BoardPosition pos = new BoardPosition(0, 1);
        assertFalse(ttt.set(p1Symbol, pos));
    }

    @Test()
    public void edgeSet() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);

        BoardPosition pos = new BoardPosition(2, 2);
        assertFalse(ttt.set(p1Symbol, pos));
    }

    /*----------------good tests-------------------------*/
    /*----------------exception tests-------------------------*/
    @Test()
    public void failureSet1() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);

        BoardPosition pos = new BoardPosition(1, 3);
        assertThrows(GameException.class, () -> {
            assertFalse(ttt.set(p1Symbol, pos));
        });
    }

    @Test()
    public void failureSet2() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);

        BoardPosition pos = new BoardPosition(3, 0);
        assertThrows(GameException.class, () -> {
            assertFalse(ttt.set(p1Symbol, pos));
        });
    }

    @Test()
    public void failureSet3() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();

        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);

        BoardPosition pos = new BoardPosition(-1, 1);
        assertThrows(GameException.class, () -> {
            assertFalse(ttt.set(p1Symbol, pos));
        });
    }

    @Test()
    public void failureSet4() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();
        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);

        BoardPosition pos = new BoardPosition(0, -1);

        assertThrows(GameException.class, () -> {
            assertFalse(ttt.set(p1Symbol, pos));
        });
    }

    /*----------------exception tests-------------------------*/
    @Test()
    public void failurePickLate() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();

        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);

        BoardPosition pos = new BoardPosition(1, 1);
        ttt.set(p1Symbol, pos);
        assertThrows(StatusException.class, () -> {
            ttt.pick(P1, TTTSymbol.O);
        });
    }

    @Test()
    public void failureSetSamePosition() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();

        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);

        BoardPosition pos = new BoardPosition(1, 1);
        ttt.set(p1Symbol, pos);
        //pick same field again
        assertThrows(GameException.class, () -> {
            ttt.set(p2Symbol, pos);
        });
    }

    @Test()
    public void goodWin() throws GameException, StatusException {
        TicTacToe ttt = this.getTicTacToe();

        TTTSymbol p1Symbol = ttt.pick(P1, TTTSymbol.X);
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.X);

        ttt.set(p1Symbol, new BoardPosition(0, 0));
        ttt.set(p2Symbol, new BoardPosition(0, 1));
        ttt.set(p1Symbol, new BoardPosition(1, 1));
        ttt.set(p2Symbol, new BoardPosition(1, 0));
        assertEquals(true,ttt.set(p1Symbol, new BoardPosition(2, 2)));

    }
}
