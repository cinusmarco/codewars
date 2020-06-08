package ch.cinus.kata.threekyu.papersplease;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inspector {

  public static final String ARSTOTZKA = "Arstotzka";
  public static final String ANTEGRIA = "Antegria";
  public static final String IMPOR = "Impor";
  public static final String KOLECHIA = "Kolechia";
  public static final String OBRISTAN = "Obristan";
  public static final String REPUBLIA = "Republia";
  public static final String UNITED_FEDERATION = "United Federation";

  private static final List<String> FOREIGN_COUNTRIES =
      List.of(ANTEGRIA, IMPOR, KOLECHIA, OBRISTAN, REPUBLIA, UNITED_FEDERATION);
  private static final List<String> ALL_COUNTRIES =
      Stream.concat(Stream.of(ARSTOTZKA), FOREIGN_COUNTRIES.stream()).collect(Collectors.toList());

  public static final String ENTRY_DENIED_MISSING_REQUIRED_DOCUMENT = "Entry denied: missing required %s.";
  public static final String FOREIGNERS = "Foreigners";
  public static final String ENTRANTS = "Entrants";
  public static final String CITIZENS = "Citizens of Arstotzka";

  private final List<String> allowedEntrance = new ArrayList<>();
  private final Map<String, Set<String>> requiredDocuments = new HashMap<>();
  private final Map<String, Set<String>> requiredVaccinations = new HashMap<>();

  private String wantedCriminal;
  private boolean workpassRequired;

  public void receiveBulletin(String bulletin) {
    System.err.println(bulletin);
    String expandedBulletin = expand(bulletin);
    allowedEntrance.addAll(extractAllowedEntrance(expandedBulletin));
    allowedEntrance.removeAll(extractDeniedEntrance(expandedBulletin));
    wantedCriminal = extractWantedCriminal(expandedBulletin);
    extractRequiredDocuments(expandedBulletin);
    updateRequiredVaccinations(expandedBulletin);
  }

  private String expand(String bulletin) {
    final String format = "Citizens of %s";
    return bulletin
        .replaceAll(ENTRANTS, String.format(format, ALL_COUNTRIES))
        .replaceAll(FOREIGNERS, String.format(format, FOREIGN_COUNTRIES))
        .replaceAll(CITIZENS, String.format(format, ARSTOTZKA))
        .replaceAll("\\[", "")
        .replaceAll("\\]", "");
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

    // check if all the documents are valid
    final List<Person.Document> invalidDocuments =
        p.documents().filter(document -> !document.isValid()).collect(Collectors.toList());
    if (!invalidDocuments.isEmpty()) {
      return String.format("Entry denied: invalid %s.", invalidDocuments.get(0));
    }

    // check for expired documents
    final String expiredDocument = checkExpiredDocuments(p);
    if (expiredDocument != null) return expiredDocument;

    // check documents required
    final List<String> requiredDocs = checkRequiredDocuments(p);
    if (!requiredDocs.isEmpty()) {
      return String.format(ENTRY_DENIED_MISSING_REQUIRED_DOCUMENT, requiredDocs.get(0).replace("_", " "));
    }

    // check for vaccinations
    final boolean isVaccinated = checkVaccinations(p);
    if (!isVaccinated) {
      return "Entry denied: missing required vaccination.";
    }

    // check if he can enter
    if (!(ARSTOTZKA.equals(p.getNation()) || allowedEntrance.contains(p.getNation()))) {
      return "Entry denied: citizen of banned nation.";
    }

    return p.getNation().equals(ARSTOTZKA) ? "Glory to Arstotzka." : "Cause no trouble.";
  }

  private boolean checkVaccinations(Person p) {
    if (requiredDocuments.get(p.getNation()).stream()
        .anyMatch(s -> s.equals(Person.Document.CERTIFICATE_OF_VACCINATION))) {

      final List<String> tmp =
          requiredVaccinations.entrySet().stream()
              .filter(entry -> entry.getKey().equals(p.getNation()))
              .map(Map.Entry::getValue)
              .flatMap(Collection::stream)
              .collect(Collectors.toList());
      return tmp.stream()
          .allMatch(
              rv ->
                  p.getCertificateOfVaccination()
                      .map(certificateOfVaccination -> certificateOfVaccination.isVaccinated(rv))
                      .orElse(false));
    } else {
      return true; // no vaccines required
    }
  }

  private List<String> checkRequiredDocuments(Person p) {

    if (ARSTOTZKA.equals(p.getNation())) {
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

    List<String> requiredDocs =
        new ArrayList<>(requiredDocuments.getOrDefault(p.getNation(), Set.of(Person.Document.PASSPORT)));
    if (workpassRequired && p.isWorker()) {
      requiredDocs.add(Person.Document.WORK_PASS);
    }
    requiredDocs.removeAll(p.documents().map(Person.Document::getType).collect(Collectors.toList()));

    // Check for alternatives to ACCESS_PERMIT
    if (requiredDocs.size() == 1
        && requiredDocs.contains(Person.Document.ACCESS_PERMIT)
        && p.documents().anyMatch(this::testAlternativeToAccessPermit)) {
      requiredDocs = Collections.emptyList();
    }

    return requiredDocs;
  }

  private boolean testAlternativeToAccessPermit(Person.Document document) {
    return (document instanceof Person.Document.AccessPermit)
        || (document instanceof Person.Document.GrantOfAyslum)
        || (document instanceof Person.Document.DiplomaticAuthorization);
  }

  private List<String> checkRequiredDocumentsForCitizens(Person p) {
    final List<String> requiredDocs =
        new ArrayList<>(requiredDocuments.getOrDefault(ARSTOTZKA, Collections.emptySet()));
    requiredDocs.removeAll(p.documents().map(Person.Document::getType).collect(Collectors.toList()));
    return requiredDocs;
  }

  private boolean isWantedCriminal(List<String> names) {
    return wantedCriminal != null && names.stream().anyMatch(n -> Objects.equals(wantedCriminal, n));
  }

  private List<String> extractAllowedEntrance(String bulletin) {
    final Pattern pattern = Pattern.compile(".*Allow citizens of (.*)");
    return parseEntrance(bulletin, pattern);
  }

  private Collection<String> extractDeniedEntrance(String bulletin) {
    final Pattern pattern = Pattern.compile(".*Deny citizens of (.*)");
    return parseEntrance(bulletin, pattern);
  }

  private List<String> parseEntrance(String bulletin, Pattern pattern) {
    final Matcher matcher = pattern.matcher(bulletin);
    if (matcher.find()) {
      return Arrays.stream(matcher.group(1).split(",")).map(String::trim).collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  private void extractRequiredDocuments(String bulletin) {
    final Pattern pattern = Pattern.compile(".*Citizens of (.+) require (passport|access permit|ID card)\\n?.*");
    final Matcher matcher = pattern.matcher(bulletin);
    if (matcher.find()) {
      String document = matcher.group(2).replace(" ", "_");
      List<String> whoList = Arrays.stream(matcher.group(1).split(",")).map(String::trim).collect(Collectors.toList());
      for (String who : whoList) {
        final Set<String> entrants = requiredDocuments.computeIfAbsent(who, k -> new HashSet<>());
        entrants.add(document);
      }
    }

    // check if workpass is needed
    if (bulletin.contains("Workers require work pass")) {
      workpassRequired = true;
    }
  }

  private void updateRequiredVaccinations(String bulletin) {
    final Map<String, Set<String>> newRequiredVaccinations = extractRequiredVaccinations(bulletin);
    newRequiredVaccinations
        .entrySet()
        .forEach(
            entry ->
                requiredVaccinations.merge(
                    entry.getKey(),
                    entry.getValue(),
                    (oldList, newList) -> {
                      Set<String> merged = new HashSet<>(oldList);
                      merged.addAll(newList);
                      return new HashSet<>(merged);
                    }));

    final Map<String, Set<String>> noLongerRequiredVaccinations = extractNoLongerRequiredVaccinations(bulletin);
    noLongerRequiredVaccinations
        .entrySet()
        .forEach(
            entry ->
                requiredVaccinations.merge(
                    entry.getKey(),
                    entry.getValue(),
                    (oldList, newList) -> {
                      Set<String> merged = new HashSet<>(oldList);
                      merged.removeAll(newList);
                      return merged;
                    }));

    // update also the required documents
    ALL_COUNTRIES.stream()
        .forEach(
            key -> {
              if (requiredVaccinations.get(key) != null
                  && requiredVaccinations.get(key).isEmpty()
                  && requiredDocuments.containsKey(key))
                requiredDocuments.get(key).remove(Person.Document.CERTIFICATE_OF_VACCINATION);
            });
  }

  private Map<String, Set<String>> extractRequiredVaccinations(String bulletin) {
    final Pattern pattern = Pattern.compile(".*Citizens of (.+) require (\\w+) vaccination\\n?.*");
    return doExtractVaccinations(bulletin, pattern);
  }

  private Map<String, Set<String>> extractNoLongerRequiredVaccinations(String bulletin) {
    final Pattern pattern = Pattern.compile(".*Citizens of (.+) no longer require (\\w+) vaccination\\n?.*");
    return doExtractVaccinations(bulletin, pattern);
  }

  private Map<String, Set<String>> doExtractVaccinations(String bulletin, Pattern pattern) {
    // TODO: Multiple entries for vaccinations are possible
    final Matcher matcher = pattern.matcher(bulletin);
    Map<String, Set<String>> newMap = new HashMap<>();
    while (matcher.find()) {
      Set<String> vaccines = Arrays.stream(matcher.group(2).split(",")).map(String::trim).collect(Collectors.toSet());
      List<String> whoList = Arrays.stream(matcher.group(1).split(",")).map(String::trim).collect(Collectors.toList());
      for (String who : whoList) {
        who = who.replaceAll("no longer", "").replaceAll("\\s+", " ").trim();
        Set<String> docs = requiredDocuments.computeIfAbsent(who, k -> new HashSet<>());
        docs.add(Person.Document.CERTIFICATE_OF_VACCINATION);
        newMap.put(who, vaccines);
      }
    }
    return newMap;
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

  public Optional<Document.CertificateOfVaccination> getCertificateOfVaccination() {
    return Optional.ofNullable((Document.CertificateOfVaccination) documents.get(Document.CERTIFICATE_OF_VACCINATION));
  }

  public boolean isWorker() {
    return documents.containsKey(Document.ACCESS_PERMIT)
        && "WORK".equals(((Document.AccessPermit) documents.get(Document.ACCESS_PERMIT)).getPurpose());
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
          return DiplomaticAuthorization.parse(content);
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

    public boolean isValid() {
      return true;
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

      private String purpose;

      protected AccessPermit(String content) {
        super(ACCESS_PERMIT, content);
        purpose = extractPurpose(content);
      }

      private String extractPurpose(String content) {

        Pattern pattern = Pattern.compile(".*PURPOSE: (.+)");
        final Matcher matcher = pattern.matcher(content);
        return matcher.find() ? matcher.group(1) : null;
      }

      public static Document parse(String content) {
        return new AccessPermit(content);
      }

      public String getPurpose() {
        return purpose;
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

    static class DiplomaticAuthorization extends Document {
      private List<String> autorizedNations;

      protected DiplomaticAuthorization(String content) {
        super(DIPLOMATIC_AUTHORIZATION, content);
        autorizedNations = extractAuthorizedNations(content);
      }

      public static Document parse(String content) {
        return new DiplomaticAuthorization(content);
      }

      private List<String> extractAuthorizedNations(String content) {
        Pattern pattern = Pattern.compile(".*ACCESS: (.+)");
        final Matcher matcher = pattern.matcher(content);
        return matcher.find()
            ? Arrays.stream(matcher.group(1).split(",")).map(String::trim).collect(Collectors.toList())
            : Collections.emptyList();
      }

      @Override
      public boolean isValid() {
        return autorizedNations.contains(Inspector.ARSTOTZKA);
      }
    }

    static class CertificateOfVaccination extends Document {
      List<String> vaccines;

      protected CertificateOfVaccination(String content) {
        super(CERTIFICATE_OF_VACCINATION, content);
        // extract the list of vaccines
        vaccines = extractVaccines(content);
      }

      private List<String> extractVaccines(String content) {
        Pattern pattern = Pattern.compile(".*VACCINES: (.+)");
        final Matcher matcher = pattern.matcher(content);
        return matcher.find()
            ? Arrays.stream(matcher.group(1).split(",")).map(String::trim).collect(Collectors.toList())
            : Collections.emptyList();
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
}
