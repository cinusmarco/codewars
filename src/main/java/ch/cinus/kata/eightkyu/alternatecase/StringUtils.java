/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.eightkyu.alternatecase;

import java.util.stream.Collectors;

public class StringUtils {
  public static String toAlternativeString(String string) {
    return string
        .codePoints()
        .mapToObj(c -> (char) c)
        .map(
            c -> {
              if (Character.isLetter(c) && Character.isLowerCase(c)) {
                return Character.toUpperCase(c);
              } else if (Character.isLetter(c) && Character.isUpperCase(c)) {
                return Character.toLowerCase(c);
              } else {
                return c;
              }
            })
        .map(Object::toString)
        .collect(Collectors.joining(""));
  }
}
