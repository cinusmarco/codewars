package ch.cinus.kata.sevenkyu.alternativecapitalization;

import java.util.stream.Collectors;

public class Solution {
  public static String[] capitalize(String s) {
    final int[] pos = {0};
    final String first =
        s.chars()
            .mapToObj(c -> (char) c)
            .map(c -> String.valueOf(pos[0]++ % 2 == 0 ? Character.toUpperCase(c) : c))
            .collect(Collectors.joining(""));
    pos[0] = 0;
    final String second =
        s.chars()
            .mapToObj(c -> (char) c)
            .map(c -> String.valueOf(pos[0]++ % 2 == 1 ? Character.toUpperCase(c) : c))
            .collect(Collectors.joining(""));

    return new String[] {first, second};
  }
}
