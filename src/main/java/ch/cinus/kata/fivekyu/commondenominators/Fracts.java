package ch.cinus.kata.fivekyu.commondenominators;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Fracts {
  // your code
  public static String convertFrac(long[][] lst) {
    List<RationalNumber> rationalNumbers =
        Arrays.stream(lst)
            .map(longs -> new RationalNumber(longs[0], longs[1]))
            .map(RationalNumber::reduce)
            .collect(Collectors.toList());

    Long lcm =
        rationalNumbers.stream()
            .map(rationalNumber -> rationalNumber.denominator)
            .reduce(1L, Fracts::lcm);

    return rationalNumbers.stream()
        .map(rationalNumber -> rationalNumber.setDenominator(lcm))
        .map(Object::toString)
        .collect(Collectors.joining());
  }

  static long GCD(long n1, long n2) {
    if (n2 == 0) return n1;
    return GCD(n2, n1 % n2);
  }

  static long lcm(long n1, long n2) {
    return n1 * n2 / GCD(n1, n2);
  }

  static class RationalNumber {
    long numerator;
    long denominator;

    RationalNumber(long numerator, long denominator) {
      this.numerator = numerator;
      this.denominator = denominator;
    }

    RationalNumber reduce() {
      long gcd = GCD(numerator, denominator);
      long n = this.numerator / gcd;
      long d = this.denominator / gcd;
      return new RationalNumber(n, d);
    }

    RationalNumber setDenominator(long d) {
      long n = d / denominator * numerator;
      return new RationalNumber(n, d);
    }

    @Override
    public String toString() {
      return String.format("(%d,%d)", numerator, denominator);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      RationalNumber that = ((RationalNumber) o).reduce();
      RationalNumber me = this.reduce();
      return me.numerator == that.numerator && me.denominator == that.denominator;
    }

    @Override
    public int hashCode() {
      return Objects.hash(numerator, denominator);
    }
  }
}
