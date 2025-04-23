package tetris.game;

import java.util.Arrays;


public class TetrisModel {
    private static final Element element = new Element();
    private final int MAX_ROW = 19;
    private final int MAX_COL = 20;
    private final int[][] grid = new int[MAX_ROW][MAX_COL];
    private final int WIN_SCORE = 200;
    private int score = 0;
    private int lastScore = -1;

    public TetrisModel() {
        for (int[] ints : grid) {
            Arrays.fill(ints, 0);
        }
    }

    public static Element getElement() {
        return element;
    }

    private boolean isPositionValid(int row, int col) {
        return row >= 0 && row < MAX_ROW && col >= 0 && col < MAX_COL && grid[row][col] == 0;
    }

    public boolean elementMove(int deltaRow, int deltaCol) {
        if (deltaRow == 1) {
            boolean fix = false;
            for (Unit u : element.getType()) {
                int absRow = element.getRow() + u.row();
                int absCol = element.getCol() + u.col();
                if (absRow + 1 >= MAX_ROW || (absRow + 1 >= 0 && absCol >= 0 && absCol < MAX_COL && grid[absRow + 1][absCol] > 0)) {
                    fix = true;
                    break;
                }
            }
            if (fix) {
                setGrid(grid);
                compress();
                element.reset();
                return false;
            }
        }
        for (Unit u : element.getType()) {
            int newRow = element.getRow() + u.row() + deltaRow;
            int newCol = element.getCol() + u.col() + deltaCol;
            if (!isPositionValid(newRow, newCol)) {
                return false;
            }
        }
        element.addRow(deltaRow);
        element.addCol(deltaCol);
        return true;
    }

    /**
     * automaticky da element na posledni volny misto ve sloupci
     */
    public void dropElement() {
        while (elementMove(1, 0)) ;
    }

    /**
     * otoci element o 90 stupnu
     *
     * @return
     */
    public boolean rotate() {
        for (Unit u : element.getType()) {
            int newRow = element.getRow() - u.col();
            int newCol = element.getCol() + u.row();
            if (!isPositionValid(newRow, newCol)) {
                return false;
            }
        }
        for (Unit u : element.getType()) {
            int tmp = u.row();
            u.setRow(-u.col());
            u.setCol(tmp);
        }
        return true;
    }

    /**
     * posune vsechny radky nad prazdnou radkou dolu
     */
    private void compress() {
        byte linesCleared = 0;
        for (int row = MAX_ROW - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                for (int i = row; i > 0; i--) {
                    grid[i] = Arrays.copyOf(grid[i - 1], MAX_COL);
                }
                Arrays.fill(grid[0], 0);
                linesCleared++;
            }
        }
        addScore(linesCleared * 10);
    }

    /**
     * kontroluje jestli element nezaplnil nejakou radku
     *
     * @param row
     * @return
     */
    private boolean isRowFull(int row) {
        for (int col = 0; col < MAX_COL; col++) {
            if (grid[row][col] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * kontroluje jestli nema element v poli index 0 jakmile se lockne
     */
    public boolean isGameOver() {
        for (int value : grid[0]) {
            if (value > 0) return true;
        }
        return false;
    }

    /**
     * vypise obsah pole grid
     * slouzi pro testovaci uceli
     */
    public void printGrid() {
        for (int[] row : getGrid()) {
            for (int cell : row) {
                System.out.print(cell > 0 ? "X " : "- ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * okopiruje grid a vrati jeho kopii do tridy MyFrame
     *
     * @return gridCopy
     */
    public int[][] getGrid() {
        int[][] gridCopy = new int[MAX_ROW][MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            System.arraycopy(grid[i], 0, gridCopy[i], 0, MAX_COL);
        }
        setGrid(gridCopy);
        return gridCopy;
    }

    private void setGrid(int[][] gridCopy) {
        for (Unit u : element.getType()) {
            int absRow = element.getRow() + u.row();
            int absCol = element.getCol() + u.col();
            if (absRow >= 0 && absRow < MAX_ROW && absCol >= 0 && absCol < MAX_COL) {
                gridCopy[absRow][absCol] = Element.elements.indexOf(element.getType()) + 1;
            }
        }
    }

    public boolean isLocationValid() {
        for (Unit u : element.getType()) {
            int absRow = element.getRow() + u.row();
            int absCol = element.getCol() + u.col();
            if (!isPositionValid(absRow, absCol)) {
                return false;
            }
        }
        return true;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }

    public boolean isWin() {
        return score == WIN_SCORE;
    }
}