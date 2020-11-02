package tictactoe;

import java.util.HashMap;

/**
 * @author Edwin W (570900) on Okt 2020
 */
public class TicTacToeImpl implements TicTacToe {

    private static final int SIZEX = 3, SIZEY = 3;
    private Status status = Status.START;
    //saves the symbol for every user
    HashMap<String, TTTSymbol> player = new HashMap<>();
    private TTTSymbol[][] board = new TTTSymbol[SIZEX][SIZEY];

    @Override
    public TTTSymbol pick(String username, TTTSymbol wantedSymbol) throws GameException, StatusException {
        if (this.status != Status.START && this.status != Status.ONE_PICKED) {
            throw new StatusException("Can't pick anymore");
        }

        TTTSymbol pickedSymbol = wantedSymbol;

        if (status == Status.START) {
            //if Start of game, the first player pics the symbol
            player.put(username, wantedSymbol);
            //Status anpassen
            status = Status.ONE_PICKED;
        } else {
            if (player.containsKey(username)) {
                //if the same (first) player pics again, he is allowed to do so
                player.put(username, wantedSymbol);
                //the second player wants to pick, he can choose but it won't make any effect
                //it will pick the not taken symbol! (but have to pick to start the game)
            } else if (player.containsValue(TTTSymbol.O)) {
                //Symbol O was picked, take the other symbol
                player.put(username, TTTSymbol.X);
                pickedSymbol = TTTSymbol.X;
                //the first pick will also start with set()
                status = Status.ACTIVE_O;
            } else {
                //Symbol O was not picked, so X was picked first and can start with set()
                player.put(username, TTTSymbol.O);
                pickedSymbol = TTTSymbol.O;
                status = Status.ACTIVE_X;
            }
        }
        return pickedSymbol;
    }


    @Override
    public boolean set(TTTSymbol symbol, BoardPosition position) throws StatusException, GameException {
        if (this.status != Status.ACTIVE_O && this.status != Status.ACTIVE_X) {
            throw new StatusException("Can't set field. Wrong status!");
        }

        int xCoord = checkCoordinate(position.getxCoord());
        int yCoord = checkCoordinate(position.getyCoord());

        if (this.board[xCoord][yCoord] != null) {
            throw new GameException("Board position already occupied");
        }
        this.board[xCoord][yCoord] = symbol;
        return this.hasWon(symbol);
    }

    private boolean hasWon(TTTSymbol symbol) {
        System.out.println("Board:");
        System.out.println(board[0][0] + " " + board[1][0] + " " + board[2][0]);
        System.out.println(board[0][1] + " " + board[1][1] + " " + board[2][1]);
        System.out.println(board[0][2] + " " + board[1][2] + " " + board[2][2]);

        System.out.println("Check horizontal line");
        //search for horizontal line ->
        if (searchRow(symbol, 1, 0)) return true;
        System.out.println("Check vertical line");
        //search for vertical line |
        if (searchRow(symbol, 0, 1)) return true;
        System.out.println("Check diagonal line");
        //search for diagonal line \
        if (searchRow(symbol, 1, 1)) return true;
        System.out.println("Check other diagonal line");
        //search for other diagonal line /
        if (searchRow(symbol, 1, 1, true)) return true;

        return false;
    }


    private boolean searchRow(TTTSymbol symbol, int incx, int incy, boolean invert) {

        int count = 0;
        int x = 0, y = 0;
        int invertX = SIZEX - 1;
        int invertY = SIZEY - 1;
        TTTSymbol tempSymbol;

        for (; x < SIZEX && y < SIZEY; ) {
            if (invert) {

                System.out.println("[" + (invertX - x) + "][" + (invertY - y) + "]");
                tempSymbol = this.board[invertX - x][invertY - y];
            } else {
                System.out.println("[" + x + "][" + y + "]");
                tempSymbol = this.board[x][y];
            }
            if (tempSymbol == symbol) {
                System.out.println("(... found symbol:" + symbol + ")");
                count++;
                if (count == 3) {
                    return true;
                }
                x += incx;
                y += incy;
            } else {
                count = 0;
                if (incx == 0) {
                    x++;
                    y = 0;
                } else if (incy == 0) {
                    y++;
                    x = 0;
                } else return false;
            }

        }
        return false;
    }

    private boolean searchRow(TTTSymbol symbol, int incx, int incy) {
        return this.searchRow(symbol, incx, incy, false);
    }

    private int checkCoordinate(int coordinate) throws GameException {
        if (coordinate < 0 || 2 < coordinate) throw new GameException("Coordinate not in valid range!");
        return coordinate;
    }

}
