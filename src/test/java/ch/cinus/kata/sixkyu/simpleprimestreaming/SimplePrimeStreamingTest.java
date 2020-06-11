package ch.cinus.kata.sixkyu.simpleprimestreaming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePrimeStreamingTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void test(int from, int howMany, String expected) {
    assertThat(SimplePrimeStreaming.solve(from, howMany)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(2, 2, "57"),
        Arguments.of(10, 3, "192"),
        Arguments.of(20, 9, "414347535"),
        Arguments.of(30, 12, "616771737983"),
        Arguments.of(40, 8, "83899710"),
        Arguments.of(50, 6, "031071"),
        Arguments.of(10000, 5, "02192"),
        Arguments.of(20000, 5, "09334"));
  }
}
