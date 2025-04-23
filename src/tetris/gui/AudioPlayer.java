package tetris.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Objects;

public class AudioPlayer {
    private final String filePath;
    private final boolean infiniteLoop;
    private Clip clip;
    private Thread playThread;

    public AudioPlayer(String filename, boolean infiniteLoop) {
        this.filePath = filename;
        this.infiniteLoop = infiniteLoop;
    }

    public Thread getPlayThread() {
        return playThread;
    }

    public void stop() {
        clip.stop();
    }

    public void playMusic() {
        playThread = new Thread(() -> {
            try {
                final AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(this.filePath));
                this.clip = AudioSystem.getClip();
                clip.open(audioStream);
                if (infiniteLoop) clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        playThread.start();
    }

    public void playSound(String soundFile) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(getClass().getResource("/sounds/" + soundFile)));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if (clip != null) {
            clip.stop();
        }
    }
}

