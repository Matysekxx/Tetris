package tetris.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EndPanel extends JPanel {
    private final Random rand = new Random();
    private final Color[] endingColors;
    private final String text;
    private final AudioPlayer audio;
    private final Timer timer;

    public EndPanel(String text, Color[] endingColors, String path, boolean infiniteLoop) {
        super(new BorderLayout());
        this.text = text;
        this.setOpaque(true);
        this.endingColors = endingColors;
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 90));
        audio = new AudioPlayer(path, infiniteLoop);
        timer = new Timer(100, e -> repaint());
        this.setPreferredSize(new Dimension(1000, 1000));
    }

    public void play() {
        timer.start();
        audio.playMusic();
    }

    public void stop() {
        audio.stop();
        timer.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                if ((rand.nextBoolean())) {
                    g.setColor(endingColors[rand.nextInt(endingColors.length)]);
                    g.drawRect(x * 50, y * 50, 50, 50);
                }
            }
        }

        g.setFont(getFont());
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        g.drawString(text, (getWidth() - textWidth) / 2, 300);
    }
}