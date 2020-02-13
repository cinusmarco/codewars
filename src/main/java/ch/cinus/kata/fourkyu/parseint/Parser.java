package ch.cinus.kata.fourkyu.parseint;

import java.util.Map;

public class Parser {

  private static final String THOUSAND_STRING = "thousand";

  public static int parseInt(String numStr) {
    int answer = 0;
    String[] splits = numStr.split(THOUSAND_STRING);
    if (numStr.contains(THOUSAND_STRING)) {
      answer = parseLessThan1000(splits[0]) * 1000;
      if (splits.length > 1) {
        answer += parseLessThan1000(splits[1]);
      }
    } else {
      answer += parseLessThan1000(splits[0]);
    }
    return answer;
  }

  private static int parseLessThan1000(String numStr) {
    int answer = 0;
    String[] splits = numStr.split(" ");
    for (String split : splits) {
      switch (whatIs(split)) {
        case NOTHING:
          continue;
        case NUMBER:
          answer += number(split);
          break;
        case MULTIPLIER:
          answer *= multiplierTable.getOrDefault(split, 1);
          break;
      }
    }
    return answer;
  }

  private static int number(String numStr) {
    if (numStr.contains("-")) {
      String[] splits = numStr.split("-");
      return parse(splits[0]) + parse(splits[1]);
    } else {
      return parse(numStr);
    }
  }

  private static TYPE whatIs(String numStr) {
    if (multiplierTable.containsKey(numStr)) {
      return TYPE.MULTIPLIER;
    } else if (numberTable.containsKey(numStr) || numStr.contains("-")) {
      return TYPE.NUMBER;
    } else {
      return TYPE.NOTHING;
    }
  }

  private static int parse(String toBeTranslated) {
    return numberTable.getOrDefault(toBeTranslated, 0);
  }

  enum TYPE {
    NUMBER,
    MULTIPLIER,
    NOTHING
  }

  private static Map<String, Integer> multiplierTable = Map.of("hundred", 100, "million", 1000000);

  private static Map<String, Integer> numberTable =
      Map.ofEntries(
          Map.entry("zero", 0),
          Map.entry("a", 1),
          Map.entry("one", 1),
          Map.entry("two", 2),
          Map.entry("three", 3),
          Map.entry("four", 4),
          Map.entry("five", 5),
          Map.entry("six", 6),
          Map.entry("seven", 7),
          Map.entry("eight", 8),
          Map.entry("nine", 9),
          Map.entry("ten", 10),
          Map.entry("eleven", 11),
          Map.entry("twelve", 12),
          Map.entry("thirteen", 13),
          Map.entry("fourteen", 14),
          Map.entry("fifteen", 15),
          Map.entry("sixteen", 16),
          Map.entry("seventeen", 17),
          Map.entry("eighteen", 18),
          Map.entry("nineteen", 19),
          Map.entry("twenty", 20),
          Map.entry("thirty", 30),
          Map.entry("forty", 40),
          Map.entry("fifty", 50),
          Map.entry("sixty", 60),
          Map.entry("seventy", 70),
          Map.entry("eighty", 80),
          Map.entry("ninety", 90));
}
