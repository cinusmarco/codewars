package ch.cinus.kata.eightkyu.findsmallestintinarray;

import java.util.Arrays;

public class SmallestIntegerFinder {

  public static int findSmallestInt(int[] ints) {
    return Arrays.stream(ints).min().getAsInt();
  }
}
