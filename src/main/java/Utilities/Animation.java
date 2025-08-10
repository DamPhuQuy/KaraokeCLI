package Utilities;

public class Animation {
    static final long delayPerChar = 100;

    public static void printTypeWriting(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            System.out.print("|");
            System.out.flush();
            try {
                Thread.sleep(delayPerChar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("\b");
        }
        System.out.println();
    }
}
