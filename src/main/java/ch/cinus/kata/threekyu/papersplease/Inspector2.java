package ch.cinus.kata.threekyu.papersplease;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Inspector2 {

  private static final String ARSTOTZKA_POSITIVE = "Glory to Arstotzka.";
  private static final String FOREIGNER_POSITIVE = "Cause no trouble.";
  private static final String DENIED_MISSING_DOCUMENT = "Entry denied: missing required %s.";
  private static final String DETAINMENT_CRIMINAL = "Detainment: Entrant is a wanted criminal.";

  private static final List<String> COUNTRIES =
      List.of("Arstotzka", "Antegria", "Impor", "Kolechia", "Obristan", "Republia", "United Federation");

  private Map<String, Boolean> entranceAllowed;

  private Map<String, String> requiredDocuments;

  private String wantedCriminal;

  public Inspector2() {
    entranceAllowed = COUNTRIES.stream().collect(Collectors.toMap(s -> s, s -> Boolean.TRUE));
    requiredDocuments = new HashMap<>();
  }

  public void receiveBulletin(String bulletin) {
    extractWantedCriminal(bulletin);
    extractRequiredDocuments(bulletin);
  }

  public String inspect(Map<String, String> person) {

    String passport = person.getOrDefault("passport", "");

    final Pattern pattern = Pattern.compile(".*NAME: (\\w+), (\\w+).*");
    final Matcher matcher = pattern.matcher(passport);
    String name = null;
    if (matcher.find()) {
      name = String.format("%s %s", matcher.group(2), matcher.group(1));
    }

    if (isWantedCriminal(name)) {
      return DETAINMENT_CRIMINAL;
    }

    // check for required documents
    String answer = "Error in checking documents";

    for (Map.Entry<String, String> entry : requiredDocuments.entrySet()) {
      final String nation = entry.getKey();
      final String document = entry.getValue();
      answer = person.containsKey(document) ? ARSTOTZKA_POSITIVE : String.format(DENIED_MISSING_DOCUMENT, document);
    }

    return answer;
  }

  private boolean isWantedCriminal(String name) {
    return wantedCriminal != null && Objects.equals(name, wantedCriminal);
  }

  private void extractWantedCriminal(String bulletin) {
    final Pattern pattern = Pattern.compile(".*Wanted by the State: (\\w+ \\w+)");
    final Matcher matcher = pattern.matcher(bulletin);
    if (matcher.find()) {
      wantedCriminal = matcher.group(1);
    } else {
      wantedCriminal = null;
    }
  }

  private void extractRequiredDocuments(String bulletin) {
    final Pattern pattern = Pattern.compile(".*(Entrants|Foreigners) require (passport|access permit)\\n?.*");
    final Matcher matcher = pattern.matcher(bulletin);
    if (matcher.find()) {
      String document = matcher.group(2);
      String who = matcher.group(1);

      switch (who) {
        case "Entrants":
          requiredDocuments = COUNTRIES.stream().collect(Collectors.toMap(s -> s, s -> document));
          break;
        case "Foreigners":
          requiredDocuments =
              COUNTRIES.stream().filter(s -> !"Arstotzka".equals(s)).collect(Collectors.toMap(s -> s, s -> document));
          break;
      }
    }
  }

  // create class person which holds all the information about someone who wants to enter.
  private static class Person {
    private String name;
    private String nation;
  }

  private static class Pair<U, V> {
    private final U first;
    private final V second;

    public Pair(U first, V second) {
      this.first = first;
      this.second = second;
    }

    public Pair(Pair<U, V> other) {
      this.first = other.first;
      this.second = other.second;
    }

    public U getFirst() {
      return first;
    }

    public V getSecond() {
      return second;
    }
  }
}
