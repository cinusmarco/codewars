package ch.cinus.kata.seven_kyu.complementarydna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DnaStrandTest {
  @Test
  void test01() {
    assertEquals("TTTT", DnaStrand.makeComplement("AAAA"));
  }
  @Test
  void test02() {
    assertEquals("TAACG", DnaStrand.makeComplement("ATTGC"));
  }
  @Test
  void test03() {
    assertEquals("CATA", DnaStrand.makeComplement("GTAT"));
  }
}