package utilities;

import Services.LyricsThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LrcParser {
    public static List<LyricsThread.LyricLine> startParsing(String musicFile) throws IOException {
        String path = DirectoryHelper.getLyricDir(musicFile);

        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + file.getAbsolutePath());
        }

        BufferedReader br = new BufferedReader(new FileReader(path));

        List<LyricsThread.LyricLine> lines = new ArrayList<>();

        // format: [xx:xx.xx] (text) or [xx:xx.xxx] (text)
        // xx : centisecond (cs = 10^-2 s) or milliseconds (ms = 10^-3 s)
        String format = "\\[(\\d{2}):(\\d{2})\\.(\\d{2,3})](.*)"; // "\\" double escape to avoid special character
        // Each (...) is a group
        // In original regex: need `\` to escape
        // In string of programming language: each `\` need to replace with `\\`

        Pattern p = Pattern.compile(format);

        String line;
        while ((line = br.readLine()) != null) {
            Matcher m = p.matcher(line);
            if (m.matches()) {
                LyricsThread.LyricLine lyricLine = getLyricLine(m);

                lines.add(lyricLine);
            }
        }

        return lines;
    }

    private static LyricsThread.LyricLine getLyricLine(Matcher m) {
        int minutes = Integer.parseInt(m.group(1));
        int seconds = Integer.parseInt(m.group(2));
        int milliseconds = (m.group(3).length() == 3) ? Integer.parseInt(m.group(3)) : Integer.parseInt(m.group(3)) * 10;

        String lyricText = m.group(4).trim();

        long timestamp = (long) ((minutes * 60L + seconds) * Math.pow(10, 3) + milliseconds);

        return new LyricsThread.LyricLine(timestamp, lyricText);
    }
}
