package ch.cinus.kata.six_kyu.thedeafratsofhamelin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DinglemouseTest {
  @Test
  void ex1() {
    System.err.println("1");
    assertEquals(0, Dinglemouse.countDeafRats("~O~O~O~O P"));
  }

  @Test
  void ex2() {
    System.err.println("2");
    assertEquals(1, Dinglemouse.countDeafRats("P O~ O~ ~O O~"));
  }

  @Test
  void ex3() {
    System.err.println("3");
    assertEquals(2, Dinglemouse.countDeafRats("~O~O~O~OP~O~OO~"));
  }
}
