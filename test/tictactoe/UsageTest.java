package tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class UsageTest {


    private static final String P1 = "Bertha";
    private static final String P2 = "Hans";
    private static final String P3 = "Kevin";

    private TicTacToe getTicTacToe() {
        return null;
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
        TTTSymbol p2Symbol = ttt.pick(P2, TTTSymbol.O);
        assertEquals(TTTSymbol.X, p1Symbol);
        assertEquals(TTTSymbol.O, p2Symbol);
    }

    /*----------------good tests-------------------------*/
    /*----------------exception tests-------------------------*/

    @Test()
    public void failurePickSymbol3Times() {
        TicTacToe ttt = this.getTicTacToe();
        assertThrows(GameException.class, () ->{
            ttt.pick(P1, TTTSymbol.X);
            ttt.pick(P2, TTTSymbol.X);
            ttt.pick(P3, TTTSymbol.X);
        });
    }

    /*----------------exception tests-------------------------*/


}
