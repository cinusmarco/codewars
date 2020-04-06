package ch.cinus.kata.threekyu.papersplease;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inspector {

  private static final List<String> COUNTRIES =
      List.of("Arstotzka", "Antegria", "Impor", "Kolechia", "Obristan", "Republia", "United Federation");

  private List<String> allowedEntrance;
  private Map<String, List<String>> requiredDocuments = new HashMap<>();
  private String wantedCriminal;

  public void receiveBulletin(String bulletin) {
    System.err.println(bulletin);
    allowedEntrance = extractAllowedEntrance(bulletin);
    wantedCriminal = extractWantedCriminal(bulletin);
    extractRequiredDocuments(bulletin);
  }

  public String inspect(Map<String, String> personMap) {
    System.err.println(personMap);
    Person p = new Person();

    personMap.entrySet().stream().forEach(entry -> p.addDocument(entry.getKey(), entry.getValue()));

    if (isWantedCriminal(p.getName())) {
      return "Detainment: Entrant is a wanted criminal.";
    }

    // check for expired documents
    final String expiredDocument =
        p.documents()
            .filter(Person.Document::isExpired)
            .findFirst()
            .map(document -> String.format("Entry denied: %s expired.", document.type))
            .orElse("");
    if (!expiredDocument.isEmpty()) {
      return expiredDocument;
    }

    // check for mismatches inside the documents
    if (p.documents().collect(Collectors.groupingBy(Person.Document::getId)).size() > 1) {
      return "Detainment: ID number mismatch.";
    }

    final List<String> requiredDocs = new ArrayList<>(requiredDocuments.get(p.getNation()));
    requiredDocs.removeAll(p.documents().map(Person.Document::getType).collect(Collectors.toList()));
    if (!requiredDocs.isEmpty()) {
      return String.format("Entry denied: missing required %s.", requiredDocs.get(0));
    }

    String answer;
    if (p.getNation().equals("Arstotzka")) {
      answer = inspectLocal(p);
    } else {
      answer = inspectForeigner(p);
    }
    return answer;
  }

  private String inspectForeigner(Person p) {
    if (allowedEntrance.contains(p.getNation())) {
      return "Cause no trouble.";
    } else {
      return "Entry denied: citizen of banned nation.";
    }
  }

  private String inspectLocal(Person p) {

    return "Glory to Arstotzka.";
  }

  private boolean isWantedCriminal(String name) {
    return wantedCriminal != null && Objects.equals(name, wantedCriminal);
  }

  private List<String> extractAllowedEntrance(String bulletin) {
    final Pattern pattern = Pattern.compile(".*Allow citizens of (.*)");
    final Matcher matcher = pattern.matcher(bulletin);
    if (matcher.find()) {
      return Arrays.stream(matcher.group(1).split(",")).map(String::trim).collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  private void extractRequiredDocuments(String bulletin) {
    final Pattern pattern = Pattern.compile(".*(Entrants|Foreigners) require (passport|access permit)\\n?.*");
    final Matcher matcher = pattern.matcher(bulletin);
    if (matcher.find()) {
      String document = matcher.group(2);
      String who = matcher.group(1);

      if ("Entrants".equals(who)) {
        COUNTRIES.stream()
            .map(country -> requiredDocuments.computeIfAbsent(country, k -> new ArrayList<>()))
            .forEach(docs -> docs.add(document));
      } else if ("Foreigners".equals(who)) {
        COUNTRIES.stream()
            .filter(s -> !"Arstotzka".equals(s))
            .map(country -> requiredDocuments.computeIfAbsent(country, k -> new ArrayList<>()))
            .forEach(docs -> docs.add(document));
      }
    }
  }

  private String extractWantedCriminal(String bulletin) {
    final Pattern pattern = Pattern.compile(".*Wanted by the State: (\\w+ \\w+)");
    final Matcher matcher = pattern.matcher(bulletin);
    return matcher.find() ? matcher.group(1) : null;
  }
}

class Person {

  private List<Document> documents = new ArrayList<>();

  void addDocument(String type, String documentString) {
    Document document = Document.parseDocument(type, documentString);
    documents.add(document);
  }

  public boolean hasInformationMismatch() {
    return false;
  }

  String getName() {
    return documents.get(0).getName();
  }

  String getNation() {
    return documents.get(0).getNation();
  }

  public Stream<Document> documents() {
    return documents.stream();
  }

  abstract static class Document {
    String type;
    String name;
    LocalDate exp;
    String id;
    String nation;

    protected Document(String type, String content) {
      this.type = type;
      name = extractName(content);
      nation = extractNation(content);
      exp = extractExpDate(content);
      id = extractId(content);
    }

    public static Document parseDocument(String type, String content) {
      switch (type) {
        case "passport":
          return Passport.parse(content);
        case "access_permit":
          return AccessPermit.parse(content);
        case "grant_of_asylum":
          return GrantOfAyslum.parse(content);
        default:
          throw new IllegalArgumentException("Unkown type: " + type);
      }
    }

    public String getType() {
      return type;
    }

    public String getName() {
      return name;
    }

    public boolean isExpired() {
      return exp.isBefore(LocalDate.of(1982, 11, 22));
    }

    public String getId() {
      return id;
    }

    public String getNation() {
      return nation;
    }

    private String extractName(String content) {
      final Pattern pattern = Pattern.compile(".*NAME: (\\w+), (\\w+).*");
      final Matcher matcher = pattern.matcher(content);

      return matcher.find() ? String.format("%s %s", matcher.group(2), matcher.group(1)) : null;
    }

    private String extractNation(String content) {
      final Pattern pattern = Pattern.compile(".*NATION: (\\w+\\s?\\w*)\n.*");
      final Matcher matcher = pattern.matcher(content);

      return matcher.find() ? String.format("%s", matcher.group(1)) : null;
    }

    private LocalDate extractExpDate(String content) {
      final Pattern pattern = Pattern.compile(".*EXP: (\\d{4})\\.(\\d{2})\\.(\\d{2}).*");
      final Matcher matcher = pattern.matcher(content);

      return matcher.find()
          ? LocalDate.of(
              Integer.parseInt(matcher.group(1)),
              Integer.parseInt(matcher.group(2)),
              Integer.parseInt(matcher.group(3)))
          : null;
    }

    private String extractId(String content) {
      final Pattern pattern = Pattern.compile(".*ID#: (\\w{5}-\\w{5})\n.*");
      final Matcher matcher = pattern.matcher(content);

      return matcher.find() ? String.format("%s", matcher.group(1)) : null;
    }

    private static class Passport extends Document {
      private Date dob;

      private Passport(String content) {
        super("passport", content);
      }

      public static Document parse(String content) {
        Passport passport = new Passport(content);
        return passport;
      }
    }

    private static class AccessPermit extends Document {

      protected AccessPermit(String content) {
        super("access_permit", content);
      }

      public static Document parse(String content) {
        AccessPermit accessPermit = new AccessPermit(content);
        return accessPermit;
      }
    }

    private static class GrantOfAyslum extends Document {

      protected GrantOfAyslum(String content) {
        super("grant_of_asylum", content);
      }

      public static Document parse(String content) {
        GrantOfAyslum grantOfAyslum = new GrantOfAyslum(content);
        return grantOfAyslum;
      }
    }
  }
}
