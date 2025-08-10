package Services;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import Utilities.DirectoryHelper;


public class AudioThread implements Runnable {
    private String fileName;

    public AudioThread(String fileName, int choice) {
        if (choice == 1) {
            this.fileName = DirectoryHelper.getMusicDir(fileName);
        } else if (choice == 2) {
            this.fileName = DirectoryHelper.getInstrumentDir(fileName);
        }
    }

    @Override
    public void run() {
        // Create stream to read the audio file
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            Player player = new Player(bis);
            player.play();
        } catch (JavaLayerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
