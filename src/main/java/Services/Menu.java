package Services;

public class Menu {
    public static void MainMenu() {
        System.out.println("----------------------------");
        System.out.println("|    Welcome to the Menu   |");
        System.out.println("----------------------------");
        System.out.println("| 1. Audio Player          |");
        System.out.println("| 2. Karaoke               |");
        System.out.println("| 0. Quit                  |");
        System.out.println("----------------------------");
        System.out.print("Your choice: ");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
