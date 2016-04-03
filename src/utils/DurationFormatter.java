package utils;

import javafx.util.Duration;

/**
 * A class that manipulates a duration of time
 * @see javafx.util.Duration
 */
public class DurationFormatter {
    private DurationFormatter() {
    }

    /**
     *
     * @param duration defines a duration of time
     * @return a formatted form of duration (hour:min:sec or min:sec, for ex: 01:46:34 or 09:31)
     */
    public static String getTimeFormatted(Duration duration) {
        String time;
        int hours = (int) duration.toHours();
        int minutes = (int) duration.toMinutes() - hours * 60;
        int seconds = (int) duration.toSeconds() - hours * 60 * 60 - minutes * 60;

        if (hours > 0 & minutes > 0 & seconds > 0) {
            time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            time = String.format("%02d:%02d", minutes, seconds);
        }
        return time;
    }
}
