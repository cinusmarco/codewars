package ch.cinus.kata.fourkyu.decodethemorsecodeadvanced;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MorseCodeDecoder {
  private static Map<String, String> morseTable =
      Map.ofEntries(
          Map.entry(".-", "A"),
          Map.entry("-...", "B"),
          Map.entry("-.-.", "C"),
          Map.entry("-..", "D"),
          Map.entry(".", "E"),
          Map.entry("..-.", "F"),
          Map.entry("--.", "G"),
          Map.entry("....", "H"),
          Map.entry("..", "I"),
          Map.entry(".---", "J"),
          Map.entry("-.-", "K"),
          Map.entry(".-..", "L"),
          Map.entry("--", "M"),
          Map.entry("-.", "N"),
          Map.entry("---", "O"),
          Map.entry(".--.", "P"),
          Map.entry("--.-", "Q"),
          Map.entry(".-.", "R"),
          Map.entry("...", "S"),
          Map.entry("-", "T"),
          Map.entry("..-", "U"),
          Map.entry("...-", "V"),
          Map.entry(".--", "W"),
          Map.entry("-..-", "X"),
          Map.entry("-.--", "Y"),
          Map.entry("--..", "Z"),
          Map.entry(".----", "1"),
          Map.entry("..---", "2"),
          Map.entry("...--", "3"),
          Map.entry("....-", "4"),
          Map.entry(".....", "5"),
          Map.entry("-....", "6"),
          Map.entry("--...", "7"),
          Map.entry("---..", "8"),
          Map.entry("----.", "9"),
          Map.entry("-----", "0"),
          Map.entry("--..--", ","),
          Map.entry(".-.-.-", "."),
          Map.entry("..--..", "?"),
          Map.entry("-.-.--", "!"),
          Map.entry("...---...", "SOS"));

  public static String decodeBits(String sentenceBits) {
    System.err.println(sentenceBits);
    // filter out leading 0s
    final var significantBits = sentenceBits.replaceFirst("^0*", "").replaceFirst("0*$", "");
    final var timeUnitLengthZeros = detectTimeUnitLengthZeros(significantBits);
    final var timeUnitLengthOnes = detectTimeUnitLengthOnes(significantBits);
    // split sequence into words - pause between words is 7 TU
    final var bitWords = significantBits.split("0".repeat(timeUnitLengthZeros * 7));

    return Arrays.stream(bitWords)
        .map(word -> word.split("0".repeat(timeUnitLengthZeros * 3)))
        .map(
            letter ->
                Arrays.stream(letter)
                    .map(k -> bitsToDotAndDashes(k, timeUnitLengthZeros, timeUnitLengthOnes))
                    .collect(Collectors.joining(" ")))
        .collect(Collectors.joining("   "));
  }

  public static String decodeMorse(String morseCode) {
    return Arrays.stream(morseCode.trim().split("\\s{3}"))
        .map(
            morseWord ->
                Arrays.stream(morseWord.split("\\s"))
                    .map(morseChar -> morseTable.get(morseChar))
                    .collect(Collectors.joining("")))
        .collect(Collectors.joining(" "));
  }

  private static int detectTimeUnitLengthZeros(String bits) {
    final var patternZeros = Pattern.compile("1+(0+)1+");
    final var matcherZeros = patternZeros.matcher(bits);
    if (matcherZeros.find()) {
      return matcherZeros.group(1).length();
    }
    return 1;
  }

  private static int detectTimeUnitLengthOnes(String bits) {
    final var patternOnes = Pattern.compile("0*(1+)0*");
    final var matcherOnes = patternOnes.matcher(bits);
    if (matcherOnes.find()) {
      return matcherOnes.group(1).length();
    }
    return 1;
  }

  private static String bitsToDotAndDashes(String input, int timeUnitZeros, int timeUnitOnes) {
    return input
        .replaceAll("111".repeat(timeUnitOnes), "-")
        .replaceAll("1".repeat(timeUnitOnes), ".")
        .replaceAll("000".repeat(timeUnitZeros), " ")
        .replaceAll("0".repeat(timeUnitZeros), "");
  }
}
