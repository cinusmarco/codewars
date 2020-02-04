package ch.cinus.kata.sevenkyu.pokerhandisflush;

import java.util.Arrays;

public class Kata {
  public static boolean CheckIfFlush(String[] cards) {
    return Arrays.stream(cards).map(s -> s.substring(s.length() - 1)).distinct().count() == 1;
  }
}
