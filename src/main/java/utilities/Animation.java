package utilities;

public class Animation {
    static final long delayPerChar = 50;

    public static void printTypewriting(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep(delayPerChar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
