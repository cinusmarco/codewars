package ch.cinus.kata.beta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Converter {
  private Map<Character, String> dictionary;
  private int value;
  private int increase;

  public Converter() {
    dictionary = new HashMap<>();
    value = 0;
    increase = 2;
  }

  public static long convert(String word) {
    if (word.isEmpty() || word.isBlank()) {
      return 0L;
    }
    Converter converter = new Converter();
    char[] chars = converter.init(word);
    String result = converter.convert(chars);
    return Long.parseLong(result);
  }

  private char[] init(String word) {
    char[] chars = word.toCharArray();
    dictionary.put(chars[0], "1");
    return chars;
  }

  private String convert(char[] chars) {
    if (chars.length == 1) {
      return convertSingleChar(chars[0]);
    } else {
      return convertSingleChar(chars[0]) + convert(Arrays.copyOfRange(chars, 1, chars.length));
    }
  }

  private String convertSingleChar(char c) {
    if (!dictionary.containsKey(c)) {
      dictionary.put(c, String.valueOf(value));
      value += increase;
      increase = 1;
    }
    return dictionary.get(c);
  }
}
