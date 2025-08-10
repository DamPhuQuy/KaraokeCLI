package Utilities;

public class DirectoryHelper {
    private static final String LYRIC_DIR = "data/lyrics/";
    private static final String MUSIC_DIR = "data/music/";

    private static String normalizeFileName(String fileName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileName.length(); i++) {
            char c = fileName.charAt(i);
            if (c != ' ') {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    public static String getMusicDir(String fileName) {
        return MUSIC_DIR + normalizeFileName(fileName) + ".mp3";
    }

    public static String getInstrumentDir(String filename) { return MUSIC_DIR + normalizeFileName(filename) + "_instrument.mp3"; }

    public static String getLyricDir(String fileName) {
        return LYRIC_DIR + normalizeFileName(fileName) + ".lrc";
    }

}
