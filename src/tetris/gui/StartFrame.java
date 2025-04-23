package tetris.gui;

import tetris.game.Element;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    private final EndPanel startPanel = new EndPanel("Tetris", Element.colors, "resource/01. Title.wav", true);

    public StartFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tetris");
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(startPanel);

        final JPanel panel2 = new JPanel();

        final Checkbox switchButton = new Checkbox("Dark Mode: ");
        switchButton.addItemListener(_ -> startPanel.setBackground(switchButton.getState() ? Color.BLACK : Color.WHITE));
        panel2.add(switchButton);

        final JButton startButton = new JButton("Start");
        startButton.addActionListener(_ -> {
            this.dispose();
            startPanel.stop();
            new TetrisGUI(switchButton.getState() ? Color.BLACK : Color.WHITE);
        });
        panel2.add(startButton);

        this.add(panel2, BorderLayout.SOUTH);

        this.setVisible(true);
        startPanel.play();
    }
}
