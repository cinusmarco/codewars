package ch.cinus.kata.eightkyu.wilsonprime;

import java.math.BigDecimal;

public class WilsonPrime {
  public static boolean am_i_wilson(double p) {
    if (p <= 1) {
      return false;
    }
    BigDecimal n = BigDecimal.valueOf((int) p);
    try {
      return fac(n.subtract(BigDecimal.ONE)).add(BigDecimal.ONE).divide(n.pow(2)).scale() <= 0;
    } catch (ArithmeticException e) {
      return false;
    }
  }

  static BigDecimal fac(BigDecimal n) {
    BigDecimal result = BigDecimal.ONE;
    while (!n.equals(BigDecimal.ZERO)) {
      result = result.multiply(n);
      n = n.subtract(BigDecimal.ONE);
    }
    return result;
  }
}
