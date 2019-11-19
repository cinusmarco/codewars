package ch.cinus.kata.eight_kyu.findsmallestintinarray;

import java.util.Arrays;

public class SmallestIntegerFinder {

  public static int findSmallestInt(int[] ints) {
    return Arrays.stream(ints).min().getAsInt();
  }
}
