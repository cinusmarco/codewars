package ch.cinus.kata.fourkyu.decodethemorsecodeadvanced;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*TODO: Legare il tuSignal con il tuPause. Se l'operatore è abbastanza bravo da fare delle vere pause di 1 è anche in
    grado di mandare il segnale con la stessa frequenza. Stesso discorso se è in grado di mandare il segnale con
    tempi 1*/

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
    System.err.println(sentenceBits);
    // filter out leading 0s
    final var significantBits = sentenceBits.replaceFirst("^0*", "").replaceFirst("0*$", "");
    final var tuPause = detectTimeUnitLengthZeros(significantBits);
    final var tuSignal = detectTimeUnitLengthOnes(significantBits);
    // split sequence into words - pause between words is 7 TU
    final var bitWords = extractWords(significantBits, tuPause);

    final var letters =
        Arrays.stream(bitWords)
            .map(word -> extractLetters(word, tuPause))
            .collect(Collectors.toList());

    return letters.stream()
        .map(
            letter ->
                Arrays.stream(letter)
                    .map(k -> bitsToDotAndDashes(k, tuPause, tuSignal))
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
    return tuPause == Integer.MAX_VALUE ? 1 : tuPause;
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

  private static String bitsToDotAndDashes(String input, int timeUnitZeros, int timeUnitOnes) {
    return input
        .replaceAll("111".repeat(timeUnitOnes), "-")
        .replaceAll("1".repeat(timeUnitOnes), ".")
        .replaceAll("000".repeat(timeUnitZeros), " ")
        .replaceAll("0".repeat(timeUnitZeros), "");
  }
}
