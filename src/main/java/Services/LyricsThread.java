package Services;

import Utilities.Animation;

import java.util.List;

public class LyricsThread implements Runnable {
    public static class LyricLine {
        private long timestamp; // milliseconds
        private String text;

        public LyricLine(long timestamp, String text) {
            this.timestamp = timestamp;
            this.text = text;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    private final List<LyricLine> lyrics;

    public LyricsThread(List<LyricLine> lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        for (LyricLine lyricLine : lyrics) {
            long now = System.currentTimeMillis();
            long interval = now - startTime;
            long waitTime = lyricLine.getTimestamp() - interval - 200;
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Animation.printTypeWriting(lyricLine.getText());
        }
    }
}
