package ch.cinus.kata.sixkyu.tribonaccisequence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class XbonacciTest {
  private Xbonacci variabonacci;

  @BeforeEach
  void setUp() throws Exception {
    variabonacci = new Xbonacci();
  }

  @AfterEach
  void tearDown() throws Exception {
    variabonacci = null;
  }

  private double precision = 1e-10;

  @ParameterizedTest
  @MethodSource("provider")
  void basicTests(String message, double[] expected, double[] seq, int n) {
    assertThat(message, variabonacci.tribonacci(seq, n), equalTo(expected));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(
            "{1, 1, 1, 3, 5, 9, 17, 31, 57, 105}",
            new double[] {1, 1, 1, 3, 5, 9, 17, 31, 57, 105},
            new double[] {1, 1, 1},
            10),
        Arguments.of(
            "{0, 0, 1, 1, 2, 4, 7, 13, 24, 44}",
            new double[] {0, 0, 1, 1, 2, 4, 7, 13, 24, 44},
            new double[] {0, 0, 1},
            10),
        Arguments.of(
            "{0, 1, 1, 2, 4, 7, 13, 24, 44, 81}",
            new double[] {0, 1, 1, 2, 4, 7, 13, 24, 44, 81},
            new double[] {0, 1, 1},
            10),
        Arguments.of(
            "{0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274}",
            new double[] {0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274},
            new double[] {0, 1, 1},
            12),
        Arguments.of(
            "{0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149}",
            new double[] {0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149},
            new double[] {0, 1, 1},
            11));
  }
}
