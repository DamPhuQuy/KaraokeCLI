import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.util.List;
import javazoom.jl.player.Player;


public class AudioPlayer {
    public static void start(String fileName) throws Exception {
        // remove space character and convert to lowercase
        List<String> splitString = List.of(fileName.split("\\s+"));
        StringBuilder sb = new StringBuilder();

        for (String s : splitString)
            sb.append(s.toLowerCase());

        // Check that file exists or not
        String path = "data/music/" + sb.toString() + ".mp3";
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + file.getAbsolutePath());
        }

        // Create stream to read the audio file
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            Player player = new Player(bis);
            player.play();
        }
    }
}
