package ch.cinus.kata.fourkyu.bagels;

import org.junit.Test;

public class BagelTest {

  @Test
  public void testBagel() {
    Bagel bagel = BagelSolver.getBagel();

    org.junit.Assert.assertEquals(bagel.getValue() == 4, java.lang.Boolean.TRUE);
  }
}
