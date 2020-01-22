package ch.cinus.kata.sixkyu.findodd;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FindOdd {
  public static int findIt(int[] a) {
    return Arrays.stream(a)
        .boxed()
        .collect(Collectors.groupingBy(Integer::intValue))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue().size() % 2 == 1)
        .findAny()
        .orElseThrow(ArithmeticException::new)
        .getKey();
  }

  public static int findItClever(int[] a) {
    return Arrays.stream(a).reduce(0, (i, j) -> i ^ j);
  }
}
