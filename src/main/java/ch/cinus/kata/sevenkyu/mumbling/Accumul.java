package ch.cinus.kata.sevenkyu.mumbling;

import java.util.stream.Collectors;

public class Accumul {

  public static String accum(String s) {
    final int[] i = {0};
    return s.codePoints()
        .mapToObj(c -> String.valueOf((char) c))
        .map(s1 -> s1.toUpperCase() + s1.toLowerCase().repeat(i[0]++))
        .collect(Collectors.joining("-"));
  }
}
