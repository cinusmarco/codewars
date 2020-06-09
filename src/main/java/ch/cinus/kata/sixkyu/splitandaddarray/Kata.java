package ch.cinus.kata.sixkyu.splitandaddarray;

import java.util.Arrays;

public class Kata {
  public static int[] splitAndAdd(int[] numbers, int n) {
    if (n > 0) {
      int len = numbers.length;
      int[] a = Arrays.copyOfRange(numbers, 0, len / 2);
      int[] b = Arrays.copyOfRange(numbers, (len / 2), len);
      int[] newRes = new int[b.length];
      for (int i = b.length - 1, j = a.length - 1; i >= 0; i--, j--) {
        newRes[i] = j >= 0 ? a[j] + b[i] : b[i];
      }
      return splitAndAdd(newRes, n - 1);
    }
    return numbers;
  }
}
