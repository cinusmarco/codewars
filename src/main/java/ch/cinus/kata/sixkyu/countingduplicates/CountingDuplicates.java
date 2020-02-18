package ch.cinus.kata.sixkyu.countingduplicates;

import java.util.stream.Collectors;

public class CountingDuplicates {
  public static int duplicateCount(String text) {
    return (int)
        text.toLowerCase().chars().mapToObj(i -> (char) i)
            .collect(Collectors.groupingBy(character -> character)).values().stream()
            .filter(characters -> characters.size() > 1)
            .count();
  }
}
