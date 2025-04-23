package tetris.game;

import tetris.gui.AudioPlayer;
import tetris.gui.GamePanel;
import tetris.gui.TetrisGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisController extends KeyAdapter implements ActionListener {
    private final TetrisModel tetrisModel;
    private final TetrisGUI tetrisGUI;
    private final GamePanel gamePanel;
    private final Timer timer = new Timer(600, this);
    private final AudioPlayer audioPlayer;

    public TetrisController(TetrisGUI tetrisGUI, GamePanel gamePanel, TetrisModel tetrisModel, AudioPlayer audioPlayer) {
        super();
        this.tetrisGUI = tetrisGUI;
        this.gamePanel = gamePanel;
        this.tetrisModel = tetrisModel;
        this.audioPlayer = audioPlayer;
        this.timer.start();
    }

    /**
     * akce pouzita v timer
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //tetrisModel.printGrid();
        if (tetrisModel.elementMove(1, 0)) {
            if (tetrisModel.getLastScore() < tetrisModel.getScore()) {
                tetrisModel.setLastScore(tetrisModel.getScore());
                tetrisGUI.setScore(tetrisModel.getScore());
                timer.setDelay(Math.max(timer.getDelay() - 10, 70));
            }
            gamePanel.repaint();
            if (tetrisModel.isWin()) {
                timer.stop();
                stopThread();
                tetrisGUI.showWin();
            }
        } else if (tetrisModel.isGameOver()) {
            timer.stop();
            this.audioPlayer.stop();
            stopThread();
            tetrisGUI.showGameOver();
        }
    }

    public void stopThread() {
        Thread playThread = this.audioPlayer.getPlayThread();
        if (playThread != null) {
            playThread.interrupt();
        }
    }

    /**
     * zaregistrovava klavesy od uzivatele
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (!timer.isRunning() || tetrisModel.isGameOver()) return;
        boolean needsRepaint = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (tetrisModel.elementMove(0, -1)) needsRepaint = true;
            }
            case KeyEvent.VK_RIGHT -> {
                if (tetrisModel.elementMove(0, 1)) needsRepaint = true;
            }
            case KeyEvent.VK_DOWN -> {
                tetrisModel.dropElement();
                needsRepaint = true;
                if (tetrisModel.isGameOver()) {
                    timer.stop();
                    tetrisGUI.showGameOver();
                } else {
                    timer.restart();
                }
            }
            case KeyEvent.VK_UP -> {
                if (tetrisModel.rotate()) needsRepaint = true;
            }
        }
        if (needsRepaint) {
            gamePanel.repaint();
        }
    }
}