package ch.cinus.kata.sixkyu.stopgninnipsmysdrow;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SpinWords {

  public String spinWords(String sentence) {
    return Arrays.stream(sentence.split("\\s+"))
            .map(
                    str -> {
                      if (str.length() >= 5) {
                        return new StringBuilder(str).reverse().toString();
                      } else {
                        return str;
                      }
                    })
            .collect(Collectors.joining(" "));
  }
}
