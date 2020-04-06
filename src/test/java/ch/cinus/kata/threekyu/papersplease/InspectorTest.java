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
}
