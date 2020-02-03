package ch.cinus.kata.sevenkyu.predictyourage;

import java.util.List;

public class Solution {
  public static int predictAge(
      int age1, int age2, int age3, int age4, int age5, int age6, int age7, int age8) {
    return (int)
            Math.sqrt(
                List.of(age1, age2, age3, age4, age5, age6, age7, age8).stream()
                    .mapToInt(i -> i * i)
                    .sum())
        / 2;
  }
}
