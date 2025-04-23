package tetris.gui;

import tetris.game.Element;
import tetris.game.TetrisModel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final TetrisModel tetrisModel;

    public GamePanel(TetrisModel tetrisModel, Color background) {
        this.tetrisModel = tetrisModel;
        this.setBackground(background);
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setFocusable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int[][] grid = tetrisModel.getGrid();
        final int squareSize = 50;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] > 0) {
                    g.setColor(Element.colors[grid[row][col] % Element.colors.length]);
                    g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
                }
            }
        }
    }
}
