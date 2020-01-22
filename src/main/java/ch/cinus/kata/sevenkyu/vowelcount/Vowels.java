package ch.cinus.kata.sevenkyu.vowelcount;

import java.util.stream.Stream;

public class Vowels {

  public static int getCount(String str) {
    if (str == null) return 0;
    return Stream.of('a', 'e', 'i', 'o', 'u')
        .mapToInt(ch -> (int) str.chars().filter(i -> i == ch).count())
        .sum();
  }
}
