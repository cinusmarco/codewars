package ch.cinus.kata.threekyu.papersplease;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inspector {

  private static final String ARSTOTZKA_POSITIVE = "Glory to Arstotzka.";
  private static final String FOREIGNER_POSITIVE = "Cause no trouble.";
  private static final String DETAINMENT_CRIMINAL = "Detainment: Entrant is a wanted criminal.";

  private Map<String, Boolean> entranceAllowed =
      Map.of(
          "Arstotzka",
          Boolean.TRUE,
          "Antegria",
          Boolean.TRUE,
          "Impor",
          Boolean.TRUE,
          "Kolechia",
          Boolean.TRUE,
          "Obristan",
          Boolean.TRUE,
          "Republia",
          Boolean.TRUE,
          "United Federation",
          Boolean.TRUE);

  private String wantedCriminal;

  public void receiveBulletin(String bulletin) {
    extractWantedCriminal(bulletin);
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
    return person.containsKey("passport") ? ARSTOTZKA_POSITIVE : FOREIGNER_POSITIVE;
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
}
