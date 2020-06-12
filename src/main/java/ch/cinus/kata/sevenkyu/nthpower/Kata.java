package ch.cinus.kata.sevenkyu.nthpower;

import java.util.Arrays;

public class Kata {
  public static int modifiedSum(int[] array, int power) {
    return (int) (Arrays.stream(array).mapToDouble(e -> Math.pow(e, power)).sum() - Arrays.stream(array).sum());
  }
}
