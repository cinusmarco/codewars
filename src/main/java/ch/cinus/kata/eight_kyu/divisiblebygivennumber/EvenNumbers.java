package ch.cinus.kata.eight_kyu.divisiblebygivennumber;

import java.util.Arrays;

public class EvenNumbers {
  public static int[] divisibleBy(final int[] numbers, final int divider) {
    return Arrays.stream(numbers).filter(i -> i % divider == 0).toArray();
  }
}
