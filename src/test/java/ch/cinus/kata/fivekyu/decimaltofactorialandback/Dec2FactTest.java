package ch.cinus.kata.fivekyu.decimaltofactorialandback;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Dec2FactTest {
  @Test
  public void test11() {
    assertEquals("1212210", Dec2Fact.dec2FactString(1001L));
  }

  @Test
  public void testExample() {
    assertEquals("341010", Dec2Fact.dec2FactString(463L));
  }

  @Test
  public void testInverse11() {
    assertEquals(1001L, Dec2Fact.factString2Dec("1212210"));
  }

  @Test
  public void testInverseExample() {
    assertEquals(463L, Dec2Fact.factString2Dec("341010"));
  }

  @Test
  public void test1() {
    assertEquals("A0000000000", Dec2Fact.dec2FactString(36288000));
  }

  @Test
  public void test7() {
    assertEquals("14F9122694751231010", Dec2Fact.dec2FactString(8150835199999999L));
  }

  @Test
  public void test9() {
    assertEquals("2DCAA5842344512200", Dec2Fact.dec2FactString(1000000000000000L));
  }
}
