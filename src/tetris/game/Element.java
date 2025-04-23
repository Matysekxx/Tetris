package tetris.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Element {
    public static final Color[] colors = {
            new Color(102, 255, 102),
            new Color(102, 153, 255),
            new Color(255, 102, 102),
            new Color(102, 255, 255),
            new Color(204, 102, 255),
            new Color(255, 153, 204),
            new Color(153, 204, 255),
            new Color(204, 204, 255),
            new Color(255, 204, 102),
            new Color(255, 102, 204),
            new Color(102, 255, 153),
            new Color(204, 255, 102),
            new Color(102, 204, 255),
            new Color(255, 102, 153),
            new Color(153, 102, 255)
    };

    private final static Random rand = new Random();
    private static final Unit[] S = {new Unit(0, -1), new Unit(0, 0), new Unit(1, 0), new Unit(1, 1)};
    private static final Unit[] SX = {new Unit(0, 1), new Unit(0, 0), new Unit(1, 0), new Unit(1, -1)};
    private static final Unit[] I = {new Unit(0, -1), new Unit(0, 0), new Unit(0, 1), new Unit(0, 2)};
    private static final Unit[] L = {new Unit(0, -1), new Unit(0, 0), new Unit(0, 1), new Unit(1, 1)};
    private static final Unit[] LX = {new Unit(0, 1), new Unit(0, 0), new Unit(0, -1), new Unit(1, -1)};
    private static final Unit[] O = {new Unit(-1, -1), new Unit(-1, 0), new Unit(0, -1), new Unit(0, 0)};
    private static final Unit[] o = {new Unit(0, 0)};
    private static final Unit[] v = {new Unit(-1, -1), new Unit(-1, 0), new Unit(0, 0)};
    private static final Unit[] T = {new Unit(0, 0), new Unit(0, 1), new Unit(0, 2), new Unit(-1, 1)};
    private static final Unit[] U = {new Unit(0, 0), new Unit(0, 1), new Unit(0, 2), new Unit(-1, 0), new Unit(-1, 2)};
    private static final Unit[] i = {new Unit(0, 0), new Unit(0, 1), new Unit(0, 2)};
    public static final ArrayList<Unit[]> elements = new ArrayList<>(List.of(I, L, O, S, LX, SX, o, v, T, U, i));
    private int row;
    private int col;
    private Unit[] type;

    public Element() {
        reset();
    }

    public void reset() {
        this.row = 1;
        this.col = 9;
        this.type = elements.get(rand.nextInt(elements.size()));
    }

    public int getRow() {
        return row;
    }

    public void addRow(int row) {
        this.row += row;
    }

    public int getCol() {
        return col;
    }

    public void addCol(int col) {
        this.col += col;
    }

    public Unit[] getType() {
        return type;
    }

}
