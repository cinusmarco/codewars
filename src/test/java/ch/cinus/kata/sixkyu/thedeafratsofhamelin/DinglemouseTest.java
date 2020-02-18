package ch.cinus.kata.sixkyu.thedeafratsofhamelin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DinglemouseTest {
  @Test
  void ex1() {
    assertEquals(0, Dinglemouse.countDeafRats("~O~O~O~O P"));
  }

  @Test
  void ex2() {
    assertEquals(1, Dinglemouse.countDeafRats("P O~ O~ ~O O~"));
  }

  @Test
  void ex3() {
    assertEquals(2, Dinglemouse.countDeafRats("~O~O~O~OP~O~OO~"));
  }
}
