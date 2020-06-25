package ch.cinus.kata.fourkyu.humanreadabledurationformat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeFormatterV2 {

  private TimeFormatterV2() {}

  public static String formatDuration(int seconds) {
    return seconds == 0
        ? "now"
        : Stream.of(
                formatTime("year", (seconds / 31536000)),
                formatTime("day", (seconds / 86400) % 365),
                formatTime("hour", (seconds / 3600) % 24),
                formatTime("minute", (seconds / 60) % 60),
                formatTime("second", (seconds) % 60))
            .filter(e -> !e.equals(""))
            .collect(Collectors.joining(", "))
            .replaceAll(", (?!.+,)", " and ");
  }

  public static String formatTime(String s, int time) {
    return time == 0 ? "" : time + " " + s + (time == 1 ? "" : "s");
  }
}
