package tictactoe;

/**
 * @author Edwin W (570900) on Okt 2020
 */
class BoardPosition {

    private int xCoord;
    private int yCoord;

    /**
     * @param xCoord
     * @param yCoord
     */
    public BoardPosition(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }
}
