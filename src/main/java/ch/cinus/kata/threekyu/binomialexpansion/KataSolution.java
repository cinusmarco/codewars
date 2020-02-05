package ch.cinus.kata.threekyu.binomialexpansion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KataSolution {

  public static String expand(String expr) {
    String patternString =
        "\\((-?)([0-9]*)([a-zA-Z]+)([+-][0-9]*)\\)\\^([0-9]*)"; // the expr is (ax+b)^n, where ab &
    // n
    // are numbers. x is any sequence of letter
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(expr);
    if (matcher.find()) {
      int a =
          Integer.parseInt(
              matcher.group(1) + (matcher.group(2).equals("") ? "1" : matcher.group(2)));
      String var = matcher.group(3);
      int b = Integer.parseInt(matcher.group(4));
      int p = Integer.parseInt(matcher.group(5));

      return beautify(recBuild(a, b, var, p, 0, p));
    } else { // if the input string is invalid, abort
      throw new IllegalArgumentException(String.format("'%s' is an invalid expression", expr));
    }
  }

  static String recBuild(int a, int b, String var, int v, int k, int N) {
    // v is n - k
    if (v == 0) { // last term
      return String.format("%+d", (long) Math.pow(b, k));
    } else { // intermediate term --> binomial theorem (see:
      // https://www.wolframalpha.com/input/?i=binomial%20theorem)
      long coefficient = (long) (C(N, k) * Math.pow(a, v) * Math.pow(b, k));
      String term = String.format("%+d%s^%d", coefficient, var, v);
      return term + recBuild(a, b, var, v - 1, k + 1, N);
    }
  }

  static String beautify(String input) {
    return input
        .replaceAll("\\A0[a-zA-Z](\\^[0-9])?", "") // remove leading term with 0 coefficient
        .replaceAll("[+-]0[a-zA-Z](\\^[0-9])?", "") // remove term with 0 coefficient
        .replaceAll("[+-]0", "") // remove trailing 0
        .replaceAll("\\A\\+", "") // removing leading +
        .replaceAll("\\^1\\b", "") // remove and ^1
        .replaceAll("\\b1([a-zA-Z])", "$1"); // remove any 1 coefficient
  }
  // Implementation of n choose k (see:
  // https://en.wikipedia.org/wiki/Binomial_coefficient#Multiplicative_formula)
  static long C(long n, long k) {
    long result = 1;
    for (long i = 0; i < k; i++) {
      result = result * (n - i) / (i + 1);
    }
    return result;
  }
}
