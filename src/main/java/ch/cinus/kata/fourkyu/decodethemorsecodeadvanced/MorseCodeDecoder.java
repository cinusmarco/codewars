package ch.cinus.kata.fourkyu.decodethemorsecodeadvanced;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MorseCodeDecoder {

  public static final int PAUSE_WORDS = 7;
  public static final int PAUSE_LETTERS = 3;
  private static final Map<String, String> morseTable =
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
    // filter out leading and trailing 0s
    final var significantBits = sentenceBits.replaceFirst("^0*", "").replaceFirst("0*$", "");

    final var tu =
            Math.min(
                    detectTimeUnitLengthZeros(significantBits), detectTimeUnitLengthOnes(significantBits));
    // split sequence into words - pause between words is 7 TU
    final var bitWords = extractWords(significantBits, tu);

    final var letters =
            Arrays.stream(bitWords).map(word -> extractLetters(word, tu)).collect(Collectors.toList());

    return letters.stream()
            .map(
                    letter ->
                            Arrays.stream(letter)
                                    .map(k -> bitsToDotAndDashes(k, tu))
                    .collect(Collectors.joining(" ")))
        .collect(Collectors.joining("   "));
  }

  private static String[] extractWords(String significantBits, int timeUnitLengthZeros) {
    return significantBits.split("0".repeat(timeUnitLengthZeros * PAUSE_WORDS));
  }

  private static String[] extractLetters(String wordBits, int timeUnitLengthZeros) {
    return wordBits.split("0".repeat(timeUnitLengthZeros * PAUSE_LETTERS));
  }

  public static String decodeMorse(String morseCode) {
    return Arrays.stream(morseCode.trim().split("\\s{3}"))
        .map(
                morseWord ->
                        Arrays.stream(morseWord.split("\\s"))
                                .map(morseTable::get)
                                .collect(Collectors.joining("")))
        .collect(Collectors.joining(" "));
  }

  private static int detectTimeUnitLengthZeros(String bits) {
    final var patternZeros = Pattern.compile("1+(0+)1+");
    final var matcherZeros = patternZeros.matcher(bits);
    var tuPause = Integer.MAX_VALUE;
    while (matcherZeros.find()) {
      tuPause = Math.min(matcherZeros.group(1).length(), tuPause);
    }
    return tuPause;
  }

  private static int detectTimeUnitLengthOnes(String bits) {

    final var patternOnes = Pattern.compile("0*(1+)0*");
    final var matcherOnes = patternOnes.matcher(bits);
    var shortestSignalLength = Integer.MAX_VALUE;
    while (matcherOnes.find()) {
      shortestSignalLength = Math.min(matcherOnes.group(1).length(), shortestSignalLength);
    }
    return shortestSignalLength;
  }

  private static String bitsToDotAndDashes(String input, int tu) {
    return input
            .replaceAll("111".repeat(tu), "-")
            .replaceAll("1".repeat(tu), ".")
            .replaceAll("000".repeat(tu), " ")
            .replaceAll("0".repeat(tu), "");
  }
}
