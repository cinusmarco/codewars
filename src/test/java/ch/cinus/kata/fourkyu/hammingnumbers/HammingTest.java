package ch.cinus.kata.fourkyu.hammingnumbers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HammingTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void fixedTests(int input, int expected) {
    assertThat(Hamming.hamming(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(1, 1),
        Arguments.of(2, 2),
        Arguments.of(3, 3),
        Arguments.of(4, 4),
        Arguments.of(5, 5),
        Arguments.of(6, 6),
        Arguments.of(7, 8),
        Arguments.of(8, 9),
        Arguments.of(9, 10),
        Arguments.of(10, 12),
        Arguments.of(11, 15),
        Arguments.of(12, 16),
        Arguments.of(13, 18),
        Arguments.of(14, 20),
        Arguments.of(15, 24),
        Arguments.of(16, 25),
        Arguments.of(17, 27),
        Arguments.of(18, 30),
        Arguments.of(19, 32));
  }
}
