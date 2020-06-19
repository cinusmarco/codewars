package ch.cinus.kata.sixkyu.deleteoccurrencesifmorethann;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EnoughIsEnough {
  public static int[] deleteNth(int[] elements, int maxOccurrences) {
    Map<Integer, Integer> seen = new HashMap<>();
    return Arrays.stream(elements)
        .filter(
            value -> {
              final Integer timesSeen = seen.getOrDefault(value, 0);
              seen.put(value, timesSeen+1);
              return timesSeen < maxOccurrences;
            })
        .toArray();
  }
}
