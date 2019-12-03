package ch.cinus.kata.six_kyu.findtheparityoutlier;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindOutlier {
  static int find(int[] integers) {
    return Arrays.stream(integers)
        .boxed()
        .collect(Collectors.partitioningBy(integer -> integer % 2 == 0))
        .values()
        .stream()
        .min(Comparator.comparingInt(List::size))
        .map(list -> list.get(0))
        .orElse(0);
  }
}
