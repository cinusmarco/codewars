package ch.cinus.kata.seven_kyu.complementarydna;

import java.util.stream.Collectors;

public class DnaStrand {
  public static String makeComplement(String dna) {
    return dna.chars().mapToObj(c -> complement((char) c)).collect(Collectors.joining());
  }

  private static String complement(Character c) {
    switch (c) {
      case 'A':
        return "T";
      case 'T':
        return "A";
      case 'C':
        return "G";
      case 'G':
        return "C";
      default:
        return "";
    }
  }
}
