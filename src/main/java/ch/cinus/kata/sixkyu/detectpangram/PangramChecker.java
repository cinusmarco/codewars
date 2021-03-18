package ch.cinus.kata.sixkyu.detectpangram;

import java.util.stream.Collectors;

public class PangramChecker {
  public boolean check(String sentence) {
    if (sentence == null) return false;
    return sentence
            .toLowerCase()
            .replaceAll("[\\W|\\d]", "")
            .chars()
            .mapToObj(i -> (char) i)
            .collect(Collectors.groupingBy(character -> character))
            .size()
        == 26;
  }

  public boolean check2(String sentence) {
    return sentence.chars().map(Character::toLowerCase).filter(Character::isAlphabetic).distinct().count() == 26;
  }

  public boolean check3(String sentence) {
    //solution by Andrea
    return sentence.toLowerCase().replaceAll("[^a-z]", "").chars().distinct().count() == 26;
  }
}
