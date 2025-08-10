import Services.AudioThread;
import Services.LyricsThread;
import utilities.LrcParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your music name: ");

        String name = sc.nextLine();

        Thread audioThread = new Thread(new AudioThread(name));
        List<LyricsThread.LyricLine> lyrics = new ArrayList<>(LrcParser.startParsing(name));

        Thread lyricsThread = new Thread(new LyricsThread(lyrics));

        audioThread.start();
        lyricsThread.start();
    }
}
