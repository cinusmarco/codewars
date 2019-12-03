package ch.cinus.kata.fivekyu.josephussurvivor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class JosephusSurvivorTest {

  @ParameterizedTest(name = "Testing where n = {0} and k = {1}")
  @MethodSource("provider")
  void josephusTest(final int n, final int k, final int expected) {
    assertThat(JosephusSurvivor.josephusSurvivor(n, k)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(7, 3, 4),
        Arguments.of(9, 4, 1),
        Arguments.of(4, 7, 2),
        Arguments.of(6, 4, 5),
        Arguments.of(5, 5, 2),
        Arguments.of(11, 19, 10),
        Arguments.of(40, 3, 28),
        Arguments.of(14, 2, 13),
        Arguments.of(100, 1, 100),
        Arguments.of(2, 6, 1),
        Arguments.of(5, 300, 1),
        Arguments.of(300, 300, 265));
  }
}
