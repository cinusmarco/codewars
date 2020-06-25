package ch.cinus.kata.fourkyu.humanreadabledurationformat;

public class TimeFormatter {

  private static final int[] SECONDS = {365 * 24 * 60 * 60, 24 * 60 * 60, 60 * 60, 60, 1};
  private static final String[] NAMES = {"year", "day", "hour", "minute", "second"};

  private TimeFormatter() {}

  public static String formatDuration(int duration) {
    if (duration == 0) return "now";
    StringBuilder stringBuilder = new StringBuilder();
    int pos = 0;
    while (duration > 0) {
      int amount = duration / SECONDS[pos];
      if (amount > 0) {
        stringBuilder
            .append(" ")
            .append(amount)
            .append(" ")
            .append(amount > 1 ? NAMES[pos] + "s" : NAMES[pos])
            .append(",");
        duration = duration - amount * SECONDS[pos];
      }
      pos++;
    }
    return beautify(stringBuilder.toString());
  }

  private static String beautify(String uglyResult) {
    // remove trailing ,
    String s = uglyResult.substring(0, uglyResult.length() - 1).trim();
    // change last , with and
    final int index = s.lastIndexOf(',');
    return index != -1 ? s.substring(0, index) + " and" + s.substring(index + 1) : s;
  }
}
