package ch.cinus.kata.fivekyu.simplepiglatin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PigLatin {
  public static String pigIt(String str) {
    return Arrays.stream(str.split(" "))
        .map(
            s -> {
              if (s.chars().allMatch(Character::isAlphabetic)) {
                return s.substring(1) + String.valueOf(s.charAt(0)) + "ay";
              } else {
                return s;
              }
            })
        .collect(Collectors.joining(" "));
  }
}
