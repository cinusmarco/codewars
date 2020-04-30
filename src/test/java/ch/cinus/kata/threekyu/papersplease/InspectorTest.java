/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.threekyu.papersplease;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InspectorTest {

  @Test
  void preliminaryTraining() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin("Entrants require passport\nAllow citizens of Arstotzka, Obristan");

    Map<String, String> josef = new HashMap<>();
    josef.put(
        "passport",
        "ID#: GC07D-FU8AR\nNATION: Arstotzka\nNAME: Costanza, Josef\nDOB: 1933.11.28\nSEX: M\nISS: East Grestin\nEXP: 1983.03.15");

    Map<String, String> guyovich = new HashMap<>();
    guyovich.put(
        "access_permit",
        "NAME: Guyovich, Russian\nNATION: Obristan\nID#: TE8M1-V3N7R\nPURPOSE: TRANSIT\nDURATION: 14 DAYS\nHEIGHT: 159cm\nWEIGHT: 60kg\nEXP: 1983.07.13");

    Map<String, String> roman = new HashMap<>();
    roman.put(
        "passport",
        "ID#: WK9XA-LKM0Q\nNATION: United Federation\nNAME: Dolanski, Roman\nDOB: 1933.01.01\nSEX: M\nISS: Shingleton\nEXP: 1983.05.12");
    roman.put(
        "grant_of_asylum",
        "NAME: Dolanski, Roman\nNATION: United Federation\nID#: Y3MNC-TPWQ2\nDOB: 1933.01.01\nHEIGHT: 176cm\nWEIGHT: 71kg\nEXP: 1983.09.20");

    assertEquals("Glory to Arstotzka.", inspector.inspect(josef));
    assertEquals("Entry denied: missing required passport.", inspector.inspect(guyovich));
    assertEquals("Detainment: ID number mismatch.", inspector.inspect(roman));
  }

  @Test
  void inspector_deniesCriminal() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Entrants require passport\n" + "Allow citizens of Arstotzka\n" + "Wanted by the State: Katrina Pejic");

    final Map<String, String> peijc =
        Map.of(
            "passport",
            "NATION: Kolechia\n"
                + "DOB: 1949.03.23\n"
                + "SEX: F\n"
                + "ISS: Vedor\n"
                + "ID#: YS8HR-G3L0D\n"
                + "EXP: 1985.04.22\n"
                + "NAME: Pejic, Katrina");

    assertThat(inspector.inspect(peijc)).isEqualTo("Detainment: Entrant is a wanted criminal.");
  }

  @Test
  void denied_passportExpired() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Entrants require passport\n" + "Allow citizens of Arstotzka\n" + "Wanted by the State: Katrina Pejic");

    final Map<String, String> ortiz =
        Map.of(
            "passport",
            "NATION: Arstotzka\n"
                + "DOB: 1935.06.19\n"
                + "SEX: M\n"
                + "ISS: East Grestin\n"
                + "ID#: DEQF3-IKMEF\n"
                + "EXP: 1982.01.18\n"
                + "NAME: Ortiz, Konstantine");

    assertThat(inspector.inspect(ortiz)).isEqualTo("Entry denied: passport expired.");
  }

  @Test
  void incrementalTest() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Entrants require passport\n" + "Allow citizens of Arstotzka\n" + "Wanted by the State: Jan Jager");

    // Put first checks here
    final Map<String, String> ibrahimovic =
        Map.of(
            "passport",
            "NATION: United Federation\n"
                + "DOB: 1947.03.26\n"
                + "SEX: F\n"
                + "ISS: Korista City\n"
                + "ID#: LXZ8T-BTRT4\n"
                + "EXP: 1985.12.03\n"
                + "NAME: Ibrahimovic, Ana");

    assertThat(inspector.inspect(ibrahimovic)).isEqualTo("Entry denied: citizen of banned nation.");

    final Map<String, String> jager =
        Map.of(
            "passport",
            "NATION: Impor\n"
                + "DOB: 1924.07.01\n"
                + "SEX: M\n"
                + "ISS: Tsunkeido\n"
                + "ID#: WW3YZ-YB20W\n"
                + "EXP: 1983.04.20\n"
                + "NAME: Jager, Jan");

    assertThat(inspector.inspect(jager)).isEqualTo("Detainment: Entrant is a wanted criminal.");

    final Map<String, String> kowalska =
        Map.of(
            "passport",
            "NATION: Antegria\n"
                + "DOB: 1944.09.12\n"
                + "SEX: M\n"
                + "ISS: Glorian\n"
                + "ID#: ROUPK-RD23I\n"
                + "EXP: 1985.02.17\n"
                + "NAME: Kowalska, Michael");

    inspector.receiveBulletin(
        "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n"
            + "Wanted by the State: Ibrahim Spektor");

    assertThat(inspector.inspect(jager)).isEqualTo("Cause no trouble.");
    assertThat(inspector.inspect(kowalska)).isEqualTo("Cause no trouble.");

    inspector.receiveBulletin("Foreigners require access permit\n" + "Wanted by the State: Erik Ibrahimovic");

    assertThat(inspector.inspect(kowalska)).isEqualTo("Entry denied: missing required access permit.");

    final Map<String, String> weiss =
        Map.of(
            "work_pass",
            "NAME: Weiss, Gregory\n" + "FIELD: Fine arts\n" + "EXP: 1984.10.20",
            "passport",
            "NATION: Republia\n"
                + "DOB: 1915.09.23\n"
                + "SEX: M\n"
                + "ISS: Lesrenadi\n"
                + "ID#: Y577R-JAN09\n"
                + "EXP: 1985.01.29\n"
                + "NAME: Weiss, Gregory",
            "access_permit",
            "NAME: Weiss, Gregory\n"
                + "NATION: Republia\n"
                + "ID#: Y577R-JAN09\n"
                + "PURPOSE: WORK\n"
                + "DURATION: 2 MONTHS\n"
                + "HEIGHT: 174.0cm\n"
                + "WEIGHT: 82.0kg\n"
                + "EXP: 1985.01.12");

    assertThat(inspector.inspect(weiss)).isEqualTo("Cause no trouble.");
  }

  @Test
  void accessPermit_canBeAvoided_whenGrantOfAsylumOrDiplomatic() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n"
            + "Wanted by the State: Ibrahim Spektor");
    inspector.receiveBulletin("Foreigners require access permit\n" + "Wanted by the State: Erik Ibrahimovic");

    final Map<String, String> muller =
        Map.of(
            "passport",
            "NATION: Antegria\n"
                + "DOB: 1927.10.30\n"
                + "SEX: F\n"
                + "ISS: Glorian\n"
                + "ID#: Z3BP6-MVGL6\n"
                + "EXP: 1985.01.10\n"
                + "NAME: Muller, Kristina",
            "grant_of_asylum",
            "NAME: Muller, Kristina\n"
                + "NATION: Antegria\n"
                + "ID#: Z3BP6-MVGL6\n"
                + "DOB: 1927.10.30\n"
                + "HEIGHT: 157.0cm\n"
                + "WEIGHT: 58.0kg\n"
                + "EXP: 1983.11.29}");

    assertThat(inspector.inspect(muller)).isEqualTo("Cause no trouble.");
  }

  @Test
  void entrants_withMismatchInDocuments_shouldBeDetained() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n"
            + "Wanted by the State: Ibrahim Spektor");
    inspector.receiveBulletin("Foreigners require access permit\n" + "Wanted by the State: Erik Ibrahimovic");

    final Map<String, String> stolichnaya =
        Map.of(
            "work_pass",
            "NAME: Stolichnaya, Erika\n"
                + "FIELD: Construction\n"
                + "EXP: 1984.09.29, passport=NATION: Impor\n"
                + "DOB: 1952.12.23\n"
                + "SEX: F\n"
                + "ISS: Haihan\n"
                + "ID#: UULUP-LA689\n"
                + "EXP: 1985.11.08\n"
                + "NAME: Stolichnaya, Erika",
            "access_permit",
            "NAME: Stolichnaya, Erika\n"
                + "NATION: Republia\n"
                + "ID#: UULUP-LA689\n"
                + "PURPOSE: WORK\n"
                + "DURATION: 3 MONTHS\n"
                + "HEIGHT: 146.0cm\n"
                + "WEIGHT: 41.0kg\n"
                + "EXP: 1983.10.17");

    assertThat(inspector.inspect(stolichnaya)).isEqualTo("Detainment: nationality mismatch.");
  }

  @Test
  void entrants_withDiplomaticAutorization_shouldBeAllowed_whenDAIsValid() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n"
            + "Wanted by the State: Ibrahim Spektor");
    inspector.receiveBulletin("Foreigners require access permit\n" + "Wanted by the State: Erik Ibrahimovic");
    inspector.receiveBulletin(
        "Citizens of Arstotzka require ID card\n"
            + "Deny citizens of Obristan\n"
            + "Wanted by the State: Anastasia Lindberg");

    final Map<String, String> muller =
        Map.of(
            "passport",
            "NATION: Impor\n"
                + "DOB: 1925.02.15\n"
                + "SEX: M\n"
                + "ISS: Tsunkeido\n"
                + "ID#: TLI5M-ROXXN\n"
                + "EXP: 1983.03.04\n"
                + "NAME: Muller, Giovanni",
            "diplomatic_authorization",
            "NATION: Impor\n" + "NAME: Muller, Giovanni\n" + "ID#: TLI5M-ROXXN\n" + "ACCESS: Arstotzka, Antegria");

    assertThat(inspector.inspect(muller)).isEqualTo("Cause no trouble.");
  }

  @Test
  void certificateOfVaccinationTest() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n"
            + "Wanted by the State: Ibrahim Spektor");
    inspector.receiveBulletin("Foreigners require access permit\n" + "Wanted by the State: Erik Ibrahimovic");
    inspector.receiveBulletin(
        "Citizens of Arstotzka require ID card\n"
            + "Deny citizens of Obristan\n"
            + "Citizens of Antegria, Impor, Kolechia require HPV vaccination\n"
            + "Wanted by the State: Joanna Costa");

    final Map<String, String> muller =
        Map.of(
            "passport",
            "NATION: Impor\n"
                + "DOB: 1960.01.21\n"
                + "SEX: M\n"
                + "ISS: Tsunkeido\n"
                + "ID#: AKUNR-KW24K\n"
                + "EXP: 1984.04.09\n"
                + "NAME: Owsianka, Niel",
            "access_permit",
            "NAME: Owsianka, Niel\n"
                + "NATION: Impor\n"
                + "ID#: AKUNR-KW24K\n"
                + "PURPOSE: WORK\n"
                + "DURATION: 3 MONTHS\n"
                + "HEIGHT: 180.0cm\n"
                + "WEIGHT: 91.0kg\n"
                + "EXP: 1982.06.01",
            "certificate_of_vaccination",
            "NAME: Owsianka, Niel\n" + "ID#: AKUNR-KW24K\n" + "VACCINES: tetanus, polio, cholera");

    assertThat(inspector.inspect(muller)).isEqualTo("Entry denied: access permit expired.");
  }

  @Test
  void citizen_require_idCard() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n"
            + "Wanted by the State: Ibrahim Spektor");
    inspector.receiveBulletin("Foreigners require access permit\n" + "Wanted by the State: Erik Ibrahimovic");
    inspector.receiveBulletin(
        "Citizens of Arstotzka require ID card\n" + "Deny citizens of Impor\n" + "Wanted by the State: Sergei Weiss");

    final Map<String, String> muller =
        Map.of(
            "passport",
            "NATION: Arstotzka\n"
                + "DOB: 1920.08.26\n"
                + "SEX: M\n"
                + "ISS: Paradizna\n"
                + "ID#: ZACYE-YP3PC\n"
                + "EXP: 1985.05.14\n"
                + "NAME: Jacobs, Hugo",
            "ID_card",
            "NAME: Jacobs, Hugo\n" + "DOB: 1920.08.26\n" + "HEIGHT: 163.0cm\n" + "WEIGHT: 66.0kg");

    assertThat(inspector.inspect(muller)).isEqualTo("Glory to Arstotzka.");
  }

  @Test
  void detainment_whenNameMismatch() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Entrants require passport\n" + "Allow citizens of Arstotzka\n" + "Wanted by the State: Zera Graham");
    inspector.receiveBulletin(
        "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n"
            + "Wanted by the State: Ibrahim Spektor");
    inspector.receiveBulletin("Foreigners require access permit\n" + "Wanted by the State: Erik Ibrahimovic");
    inspector.receiveBulletin(
        "Deny citizens of Impor\n"
            + "Entrants require tetanus vaccination\n"
            + "Workers require work pass\n"
            + "Wanted by the State: Kristina Seczek");

    final Map<String, String> chernovski =
        Map.of(
            "passport",
            "NATION: Arstotzka\n"
                + "DOB: 1914.04.21\n"
                + "SEX: M\n"
                + "ISS: East Grestin\n"
                + "ID#: RSRHT-SNKSZ\n"
                + "EXP: 1984.06.23\n"
                + "NAME: Praskovic, Otto",
            "ID_card",
            "NAME: Chernovski, Giovanni\n" + "DOB: 1914.04.21\n" + "HEIGHT: 186.0cm\n" + "WEIGHT: 99.0kg",
            "certificate_of_vaccination",
            "NAME: Chernovski, Giovanni\n" + "ID#: RSRHT-SNKSZ\n" + "VACCINES: tetanus, polio, HPV");

    assertThat(inspector.inspect(chernovski)).isEqualTo("Detainment: name mismatch.");
  }

  @Test
  void entryDenied_missingPassport() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Entrants require passport\n" + "Allow citizens of Arstotzka\n" + "Wanted by the State: Zera Graham");
    inspector.receiveBulletin(
        "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n"
            + "Wanted by the State: Ibrahim Spektor");
    inspector.receiveBulletin("Foreigners require access permit\n" + "Wanted by the State: Erik Ibrahimovic");
    inspector.receiveBulletin(
        "Deny citizens of Impor\n"
            + "Entrants require tetanus vaccination\n"
            + "Workers require work pass\n"
            + "Wanted by the State: Kristina Seczek");

    final Map<String, String> yankov =
        Map.of(
            "ID_card",
            "NAME: Yankov, Vasily\n" + "DOB: 1960.11.22\n" + "HEIGHT: 149.0cm\n" + "WEIGHT: 45.0kg",
            "certificate_of_vaccination",
            "NAME: Yankov, Vasily\n" + "ID#: GJ7L8-IBUET\n" + "VACCINES: HPV, tetanus, tuberculosis");

    assertThat(inspector.inspect(yankov)).isEqualTo("Entry denied: missing required passport.");
  }
}
