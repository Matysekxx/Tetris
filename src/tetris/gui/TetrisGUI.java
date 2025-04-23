package tetris.gui;

import tetris.game.TetrisController;
import tetris.game.TetrisModel;

import javax.swing.*;
import java.awt.*;


public class TetrisGUI extends JFrame {
    private final TetrisModel tetrisModel = new TetrisModel();
    private final ScoreLabel scoreLabel = new ScoreLabel();
    private final AudioPlayer audioPlayer = new AudioPlayer("resource/03. A-Type Music (Korobeiniki).wav", true);
    private final GamePanel gamePanel;

    private final EndPanel loserPanel = new EndPanel("You Lost", new Color[]{Color.BLACK, Color.RED},
            "resource/18. Game Over.wav", false);
    private final EndPanel winnerPanel = new EndPanel("You Won", new Color[]{Color.YELLOW},
            "resource/16. True Ending (Level 9, High 5, 100000 Points).wav", false);

    public TetrisGUI(Color background) {
        this.setTitle("Tetris");
        this.setSize(1000, 1000);
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel(tetrisModel, background);

        this.add(gamePanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(scoreLabel);
        this.add(topPanel, BorderLayout.NORTH);

        loserPanel.setBackground(background);
        loserPanel.setVisible(false);

        winnerPanel.setBackground(background);
        winnerPanel.setVisible(false);

        final TetrisController tetrisController = new TetrisController(this, gamePanel, tetrisModel, audioPlayer);
        this.addKeyListener(tetrisController);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.audioPlayer.playMusic();

        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void showGameOver() {
        this.audioPlayer.stop();
        this.add(loserPanel);
        loserPanel.setVisible(true);
        loserPanel.play();
        this.revalidate();
        this.repaint();
    }

    public void showWin() {
        this.audioPlayer.stop();
        this.add(winnerPanel);
        winnerPanel.setVisible(true);
        winnerPanel.play();
        this.revalidate();
        this.repaint();
    }

    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }
}
