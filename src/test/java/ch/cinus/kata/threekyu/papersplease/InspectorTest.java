/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.threekyu.papersplease;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

class InspectorTest {

  @ParameterizedTest
  @MethodSource(value = "allow_access_provider")
  void inspector_inspect_shouldReturnTheCorrectString(Map<String, String> person, String expectedGreeting) {
    Inspector inspector = new Inspector();

    assertThat(inspector.inspect(person)).isEqualTo(expectedGreeting);
  }

  @Test
  void inspector_allowNonCriminal() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Entrants require passport\nAllow citizens of Arstotzka, Obristan\nWanted by the State: Hubert Popovic");

    Map<String, String> josef = new HashMap<>();
    josef.put(
        "passport",
        "ID#: GC07D-FU8AR\nNATION: Arstotzka\nNAME: Costanza, Josef\nDOB: 1933.11.28\nSEX: M\nISS: East Grestin\nEXP: 1983.03.15");

    assertThat(inspector.inspect(josef)).isEqualTo("Glory to Arstotzka.");
  }

  @Test
  void inspector_deniesCriminal() {
    Inspector inspector = new Inspector();
    inspector.receiveBulletin(
        "Entrants require passport\nAllow citizens of Arstotzka, Obristan\nWanted by the State: Hubert Popovic");

    Map<String, String> hubert = new HashMap<>();
    hubert.put(
        "passport",
        "ID#: GC07D-FU8AR\nNATION: Arstotzka\nNAME: Popovic, Hubert\nDOB: 1933.11.28\nSEX: M\nISS: East Grestin\nEXP: 1983.03.15");

    assertThat(inspector.inspect(hubert)).isEqualTo("Detainment: Entrant is a wanted criminal.");
  }

  private static Stream<Arguments> allow_access_provider() {
    return Stream.of(
        Arguments.of(
            Map.of(
                "passport",
                "ID#: GC07D-FU8AR\nNATION: Arstotzka\nNAME: Costanza, Josef\nDOB: 1933.11.28\nSEX: M\nISS: East Grestin\nEXP: 1983.03.15"),
            "Glory to Arstotzka."),
        Arguments.of(
            Map.of(
                "access_permit",
                "NAME: Guyovich, Russian\nNATION: Obristan\nID#: TE8M1-V3N7R\nPURPOSE: TRANSIT\nDURATION: 14 DAYS\nHEIGHT: 159cm\nWEIGHT: 60kg\nEXP: 1983.07.13"),
            "Cause no trouble."));
  }

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
}
