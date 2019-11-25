package ch.cinus.kata.fivekyu.josephussurvivor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class JosephusSurvivorTest {

  @ParameterizedTest(name = "Testing where n = {0} and k = {1}")
  @MethodSource("provider")
  void josephusTest(final int n, final int k, final int expected) {
    assertThat(JosephusSurvivor.josephusSurvivor(n, k), equalTo(expected));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(7, 3, 4),
        Arguments.of(11, 19, 10),
        Arguments.of(40, 3, 28),
        Arguments.of(14, 2, 13),
        Arguments.of(100, 1, 100),
        Arguments.of(2, 6, 1),
        Arguments.of(5, 300, 1),
        Arguments.of(300, 300, 265));
  }
}
