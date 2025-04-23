package tetris.gui;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

    public ScoreLabel() {
        super("Score: 0");
        this.setBackground(Color.WHITE);
        this.setFont(new Font("Dialog", Font.ITALIC, 30));
        this.setLayout(new BorderLayout());
        this.setBounds(10, 10, 200, 50);
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.LEFT);
    }

    @Override
    public void setText(String text) {
        super.setText("Score: " + text);
        revalidate();
        repaint();
    }
}
