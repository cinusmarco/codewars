package ch.cinus.kata.sixkyu.xbonacci;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class XbonacciTest {

  private Xbonacci variabonacci;

  @BeforeEach
  void beforeEach() {
    variabonacci = new Xbonacci();
  }

  @AfterEach
  void afterEach() {
    variabonacci = null;
  }

  @ParameterizedTest
  @MethodSource(value = "provider")
  void basicTests(double[] signature, int n, double[] expected) {
    assertThat(variabonacci.xbonacci(signature, n)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(new double[] {0, 1}, 10, new double[] {0, 1, 1, 2, 3, 5, 8, 13, 21, 34}),
        Arguments.of(new double[] {0, 1}, 2, new double[] {0, 1}),
        Arguments.of(new double[] {0, 1}, 1, new double[] {0}),
        Arguments.of(new double[] {1, 1}, 10, new double[] {1, 1, 2, 3, 5, 8, 13, 21, 34, 55}),
        Arguments.of(
            new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 0.0},
            9,
            new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}),
        Arguments.of(
            new double[] {0, 0, 0, 0, 1}, 10, new double[] {0, 0, 0, 0, 1, 1, 2, 4, 8, 16}),
        Arguments.of(
            new double[] {1, 0, 0, 0, 0, 0, 1}, 10, new double[] {1, 0, 0, 0, 0, 0, 1, 2, 3, 6}));
  }
}
