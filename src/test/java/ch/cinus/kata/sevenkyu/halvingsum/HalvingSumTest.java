/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.sevenkyu.halvingsum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HalvingSumTest {
  @Test
  public void test() {
    HalvingSum hs = new HalvingSum();
    assertEquals(47, hs.halvingSum(25));
    assertEquals(247, hs.halvingSum(127));
  }
}
