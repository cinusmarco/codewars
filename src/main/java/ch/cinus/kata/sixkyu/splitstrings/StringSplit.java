package ch.cinus.kata.sixkyu.splitstrings;

import java.util.stream.Stream;

public class StringSplit {

  public static String[] solution(String input) {
    if (input.length() == 0) {
      return new String[0];
    } else if (input.length() == 1) {
      return new String[]{input + "_"};
    } else if (input.length() == 2) {
      return new String[]{input};
    } else {
      final var s = input.substring(0, 2);
      final var rest = input.substring(2);
      return Stream.of(new String[]{s}, solution(rest)).flatMap(Stream::of).toArray(String[]::new);
    }
  }
}
