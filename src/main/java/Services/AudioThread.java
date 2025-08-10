package Services;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import utilities.DirectoryHelper;


public class AudioThread implements Runnable {
    private final String fileName;

    public AudioThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        String path = DirectoryHelper.getMusicDir(fileName);

        // Check that file exists or not
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + file.getAbsolutePath());
        }

        // Create stream to read the audio file
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            Player player = new Player(bis);
            player.play();
        } catch (JavaLayerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
