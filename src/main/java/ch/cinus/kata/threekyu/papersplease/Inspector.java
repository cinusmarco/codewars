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

  public static final String ARSTOTZKA = "Arstotzka";

  private static final List<String> COUNTRIES =
      List.of(ARSTOTZKA, "Antegria", "Impor", "Kolechia", "Obristan", "Republia", "United Federation");
  public static final String ENTRY_DENIED_MISSING_REQUIRED_DOCUMENT = "Entry denied: missing required %s.";
  public static final String FOREIGNERS = "Foreigners";
  public static final String ENTRANTS = "Entrants";
  public static final String CITIZENS = "Citizens of Arstotzka";

  private final List<String> allowedEntrance = new ArrayList<>();
  private final Map<String, List<String>> requiredDocuments = new HashMap<>();

  private String wantedCriminal;

  public void receiveBulletin(String bulletin) {
    System.err.println(bulletin);
    allowedEntrance.addAll(extractAllowedEntrance(bulletin));
    wantedCriminal = extractWantedCriminal(bulletin);
    extractRequiredDocuments(bulletin);
  }

  public String inspect(Map<String, String> personMap) {
    System.err.println(personMap);
    Person p = new Person();

    personMap.entrySet().stream().forEach(entry -> p.addDocument(entry.getKey(), entry.getValue()));

    if (isWantedCriminal(p.getNames())) {
      return "Detainment: Entrant is a wanted criminal.";
    }
    final String informationMismatch = checkInformationMismatch(p);
    if (informationMismatch != null) return informationMismatch;

    // check documents required
    final List<String> requiredDocs = checkRequiredDocuments(p);
    if (!requiredDocs.isEmpty()) {
      return String.format(ENTRY_DENIED_MISSING_REQUIRED_DOCUMENT, requiredDocs.get(0).replace("_", " "));
    }

    // check if he can enter
    if (!p.getNation().equals(ARSTOTZKA) && !allowedEntrance.contains(p.getNation())) {
      return "Entry denied: citizen of banned nation.";
    }

    // check for expired documents
    final String expiredDocument = checkExpiredDocuments(p);
    if (expiredDocument != null) return expiredDocument;

    return p.getNation().equals(ARSTOTZKA) ? "Glory to Arstotzka." : "Cause no trouble.";
  }

  private List<String> checkRequiredDocuments(Person p) {
    final List<String> requiredDocs =
        new ArrayList<>(requiredDocuments.getOrDefault(ENTRANTS, Collections.emptyList()));
    requiredDocs.removeAll(p.documents().map(Person.Document::getType).collect(Collectors.toList()));
    if (!requiredDocs.isEmpty()) {
      return requiredDocs;
    }
    if (p.getNation().equals(ARSTOTZKA)) {
      return checkRequiredDocumentsForCitizens(p);
    } else {
      return checkRequiredDocumentsForForeigner(p);
    }
  }

  private String checkExpiredDocuments(Person p) {
    final String expiredDocument =
        p.documents()
            .filter(Person.Document::isExpired)
            .findFirst()
            .map(document -> String.format("Entry denied: %s expired.", document))
            .orElse("");
    if (!expiredDocument.isEmpty()) {
      return expiredDocument;
    }
    return null;
  }

  private String checkInformationMismatch(Person p) {
    // check for mismatches inside the documents
    if (p.documents()
            .filter(document -> document.getId() != null)
            .collect(Collectors.groupingBy(Person.Document::getId))
            .size()
        > 1) {
      return "Detainment: ID number mismatch.";
    }

    if (p.documents()
            .filter(document -> Objects.nonNull(document.getNation()))
            .collect(Collectors.groupingBy(Person.Document::getNation))
            .size()
        > 1) {
      return "Detainment: nationality mismatch.";
    }

    if (p.documents()
            .filter(document -> Objects.nonNull(document.getName()))
            .collect(Collectors.groupingBy(Person.Document::getName))
            .size()
        > 1) {
      return "Detainment: name mismatch.";
    }
    return null;
  }

  private List<String> checkRequiredDocumentsForForeigner(Person p) {

    final List<String> requiredDocs =
        new ArrayList<>(requiredDocuments.getOrDefault(FOREIGNERS, Collections.emptyList()));
    requiredDocs.removeAll(p.documents().map(Person.Document::getType).collect(Collectors.toList()));

    if (requiredDocs.contains(Person.Document.ACCESS_PERMIT)) {
      if (p.documents().anyMatch(this::testAlternativeToAccessPermit)) {
        return Collections.emptyList();
      }
    }
    return requiredDocs;
  }

  private boolean isDocumentValid(Person.Document document) {
    return true;
  }

  private boolean testAlternativeToAccessPermit(Person.Document document) {
    return (document instanceof Person.Document.AccessPermit)
        || (document instanceof Person.Document.GrantOfAyslum)
        || (document instanceof Person.Document.DiplomaticAutorization);
  }

  private List<String> checkRequiredDocumentsForCitizens(Person p) {
    final List<String> requiredDocs =
        new ArrayList<>(requiredDocuments.getOrDefault(CITIZENS, Collections.emptyList()));
    requiredDocs.removeAll(p.documents().map(Person.Document::getType).collect(Collectors.toList()));
    return requiredDocs;
  }

  private boolean isWantedCriminal(List<String> names) {
    return wantedCriminal != null && names.stream().anyMatch(n -> Objects.equals(wantedCriminal, n));
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
    final Pattern pattern =
        Pattern.compile(".*(Entrants|Foreigners|Citizens of Arstotzka) require (passport|access permit|ID card)\\n?.*");
    final Matcher matcher = pattern.matcher(bulletin);
    if (matcher.find()) {
      String document = matcher.group(2).replace(" ", "_");
      String who = matcher.group(1);

      if (ENTRANTS.equals(who)) {
        final List<String> entrants = requiredDocuments.computeIfAbsent(ENTRANTS, k -> new ArrayList<>());
        entrants.add(document);
      } else if (FOREIGNERS.equals(who)) {
        final List<String> foreigners = requiredDocuments.computeIfAbsent(FOREIGNERS, k -> new ArrayList<>());
        foreigners.add(document);
      } else if (CITIZENS.equals(who)) {
        final List<String> citizens = requiredDocuments.computeIfAbsent(CITIZENS, k -> new ArrayList<>());
        citizens.add(document);
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

  private final Map<String, Document> documents = new HashMap<>();

  void addDocument(String type, String documentString) {
    Document document = Document.parseDocument(type, documentString);
    documents.put(type, document);
  }

  List<String> getNames() {
    return documents.values().stream().map(Document::getName).distinct().collect(Collectors.toList());
  }

  String getNation() {
    final List<String> nations =
        documents.values().stream()
            .filter(Objects::nonNull)
            .map(Document::getNation)
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
    return nations.stream().findAny().orElse(null);
  }

  public Stream<Document> documents() {
    return documents.values().stream();
  }

  abstract static class Document {
    static final String PASSPORT = "passport";
    static final String ACCESS_PERMIT = "access_permit";
    static final String GRANT_OF_ASYLUM = "grant_of_asylum";
    static final String WORK_PASS = "work_pass";
    static final String DIPLOMATIC_AUTHORIZATION = "diplomatic_authorization";
    static final String CERTIFICATE_OF_VACCINATION = "certificate_of_vaccination";
    static final String ID_CARD = "ID_card";
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
        case PASSPORT:
          return Passport.parse(content);
        case ACCESS_PERMIT:
          return AccessPermit.parse(content);
        case GRANT_OF_ASYLUM:
          return GrantOfAyslum.parse(content);
        case WORK_PASS:
          return WorkPass.parse(content);
        case DIPLOMATIC_AUTHORIZATION:
          return DiplomaticAutorization.parse(content);
        case CERTIFICATE_OF_VACCINATION:
          return CertificateOfVaccination.parse(content);
        case ID_CARD:
          return IDCard.parse(content);
        default:
          throw new IllegalArgumentException("Unkown type: " + type);
      }
    }

    @Override
    public String toString() {
      return type.replace("_", " ");
    }

    public String getType() {
      return type;
    }

    public String getName() {
      return name;
    }

    public boolean isExpired() {
      return Objects.nonNull(exp) && exp.isBefore(LocalDate.of(1982, 11, 22));
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

    static class Passport extends Document {
      private Date dob;

      private Passport(String content) {
        super(PASSPORT, content);
      }

      public static Document parse(String content) {
        return new Passport(content);
      }
    }

    static class AccessPermit extends Document {

      protected AccessPermit(String content) {
        super(ACCESS_PERMIT, content);
      }

      public static Document parse(String content) {
        return new AccessPermit(content);
      }
    }

    static class GrantOfAyslum extends Document {

      protected GrantOfAyslum(String content) {
        super(GRANT_OF_ASYLUM, content);
      }

      public static Document parse(String content) {
        return new GrantOfAyslum(content);
      }
    }

    static class WorkPass extends Document {

      protected WorkPass(String content) {
        super(WORK_PASS, content);
      }

      public static Document parse(String content) {
        return new WorkPass(content);
      }
    }

    static class DiplomaticAutorization extends Document {
      protected DiplomaticAutorization(String content) {
        super(DIPLOMATIC_AUTHORIZATION, content);
      }

      public static Document parse(String content) {
        return new DiplomaticAutorization(content);
      }
    }

    static class CertificateOfVaccination extends Document {
      List<String> vaccines = new ArrayList<>();

      protected CertificateOfVaccination(String content) {
        super(CERTIFICATE_OF_VACCINATION, content);
      }

      public static Document parse(String content) {
        return new CertificateOfVaccination(content);
      }

      boolean isVaccinated(String vaccine) {
        return vaccines.contains(vaccine);
      }
    }

    static class IDCard extends Document {
      protected IDCard(String content) {
        super(ID_CARD, content);
      }

      public static Document parse(String content) {
        return new IDCard(content);
      }
    }
  }

  static class InformationMismatchException extends Exception {
    private final String mismatchedField;

    public InformationMismatchException(String mismatchedField) {
      this.mismatchedField = mismatchedField;
    }

    public String getMismatchedField() {
      return mismatchedField;
    }
  }
}
