import Services.AudioThread;
import Services.LyricsThread;
import Services.Menu;
import Utilities.LrcParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter your song: ");
            String music = sc.nextLine();
            if (music.isEmpty()) {
                System.out.println("Please enter a valid music name.");
                continue;
            }

            List<LyricsThread.LyricLine> lyrics = new ArrayList<>(LrcParser.startParsing(music));
            Thread lyricsPlayer = new Thread(new LyricsThread(lyrics));

            Menu.MainMenu();
            int choice = Integer.parseInt(sc.nextLine());

            Thread audioPlayer = new Thread(new AudioThread(music, choice));

            switch (choice) {
                case 0 -> {
                    System.out.println("You choose to quit!");
                }
                case 1, 2 -> {
                    System.out.println("The song is playing: " + music);
                    Menu.clearScreen();

                    audioPlayer.start();
                    lyricsPlayer.start();

                    audioPlayer.join();
                    lyricsPlayer.join();
                }
                default -> {
                    System.out.println("Invalid choice.");
                }
            }

            System.out.print("Another song? (Y/N): ");
            String next = sc.nextLine();
            if (next.equalsIgnoreCase("Y")) {
                continue;
            } else {
                break;
            }
        } while (true);
    }
}
