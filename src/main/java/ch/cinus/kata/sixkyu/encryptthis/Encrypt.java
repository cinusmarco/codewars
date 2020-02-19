package ch.cinus.kata.sixkyu.encryptthis;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Encrypt {
  public static String encryptThis(String text) {
    return Arrays.stream(text.split("\\s"))
        .map(Encrypt::encryptWord)
        .collect(Collectors.joining(" "));
  }

  private static String encryptWord(String s) {
    // first letter to ASCII, swap second with last
    Matcher regexMatcher = Pattern.compile("^(\\w)(\\w)(.*)(\\w)$").matcher(s);
    if (regexMatcher.find()) {
      return regexMatcher.replaceFirst((int) regexMatcher.group(1).charAt(0) + "$4$3$2");
    }
    //first letter to ASCII
    regexMatcher = Pattern.compile("^(\\w)").matcher(s);
    if (regexMatcher.find()) {
      return regexMatcher.replaceFirst((int) regexMatcher.group(1).charAt(0) + "");
    }
    return s;
  }
}
