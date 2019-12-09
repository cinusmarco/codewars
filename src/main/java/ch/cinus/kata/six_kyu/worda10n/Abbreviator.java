package ch.cinus.kata.six_kyu.worda10n;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Abbreviator {
  public String abbreviate(String string) {
    if (string.isEmpty() || string.isBlank()) {
      return "";
    }
    String charsRegex = "\\b[a-zA-Z]+";
    Pattern pattern = Pattern.compile(charsRegex);
    Matcher charsMatcher = pattern.matcher(string);

    if (charsMatcher.find()) {
      String firstWord = charsMatcher.group(0);
      int charEnd = charsMatcher.end(0);
      if (charEnd == string.length()) { // Single word
        return abbreviateSingleWord(firstWord);
      }
      // multiple words, so there's a delimiter of variable length which has to be extracted
      String rest = string.substring(charEnd);
      String delimiterRegex = "(\\W|_|\\d)+";
      Pattern delimiterPattern = Pattern.compile(delimiterRegex);
      Matcher delimiterMatcher = delimiterPattern.matcher(rest);
      if (delimiterMatcher.find()) {
        String delimiter = delimiterMatcher.group(0);
        int delimiterEnd = delimiterMatcher.end(0);
        return abbreviateSingleWord(firstWord)
            + delimiter
            + abbreviate(rest.substring(delimiterEnd));
      }
    }
    return "";
  }

  private String abbreviateSingleWord(String word) {
    if (word.length() <= 3) {
      return word;
    }
    StringBuilder stringBuilder = new StringBuilder();
    char[] chars = word.toCharArray();
    stringBuilder.append(chars[0]).append(chars.length - 2).append(chars[chars.length - 1]);
    return stringBuilder.toString();
  }
}
