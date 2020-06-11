package ch.cinus.kata.eightkyu.wilsonprime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WilsonPrimeTest {
  @Test
  public void test0() {
    assertEquals(false, WilsonPrime.am_i_wilson(0));
  }

  @Test
  public void test1() {
    assertEquals(false, WilsonPrime.am_i_wilson(1));
  }

  @Test
  public void test5() {
    assertEquals(true, WilsonPrime.am_i_wilson(5));
  }

  @Test
  public void test13() {
    assertEquals(true, WilsonPrime.am_i_wilson(13));
  }

  @Test
  public void test563() {
    assertEquals(true, WilsonPrime.am_i_wilson(563));
  }

  @Test
  public void test333() {
    assertEquals(false, WilsonPrime.am_i_wilson(333));
  }
}
