package ch.cinus.kata.fivekyu.commondenominators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ch.cinus.kata.fivekyu.commondenominators.Fracts.RationalNumber;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FractsTest {
  @Test
  void test_fractions() throws Exception {
    long[][] lst;
    lst = new long[][] {{1, 2}, {1, 3}, {10, 40}};
    assertEquals("(6,12)(4,12)(3,12)", Fracts.convertFrac(lst));
  }

  @Test
  void testSetDenominator() {
    RationalNumber a = new RationalNumber(2, 3);
    RationalNumber expected = new RationalNumber(4, 6);
    assertEquals(expected, a.setDenominator(6));
  }

  @ParameterizedTest
  @MethodSource("lcm_provider")
  void testGCD(int expected, int a, int b) {
    assertEquals(expected, Fracts.lcm(a, b));
  }

  private static Stream<Arguments> lcm_provider() {
    return Stream.of(Arguments.of(2, 2, 2), Arguments.of(6, 2, 3), Arguments.of(6, 2, 6));
  }
}
