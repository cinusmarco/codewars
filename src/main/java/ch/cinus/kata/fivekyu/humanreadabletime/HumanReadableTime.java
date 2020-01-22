package ch.cinus.kata.fivekyu.humanreadabletime;

public class HumanReadableTime {
  public static String makeReadable(int totalTime) {

    int seconds = totalTime % 60;
    int hoursAndMinutes = totalTime / 60;
    int minutes = hoursAndMinutes % 60;
    int hours = hoursAndMinutes / 60;

    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }
}
