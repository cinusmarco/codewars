package ch.cinus.kata.sevenkyu.youreasquare;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SquareTest {
  @ParameterizedTest(name = "{index} {0}")
  @MethodSource("provider")
  void shouldWorkForSomeExamples(String as, boolean expected, int value) throws Exception {

    assertThat(as, Square.isSquare(value), equalTo(expected));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("negative numbers aren't square numbers", false, -1),
        Arguments.of("0 is a square number (0 * 0)", true, 0),
        Arguments.of("3 isn't a square number", false, 3),
        Arguments.of("4 is a square number (2 * 2)", true, 4),
        Arguments.of("25 is a square number (5 * 5)", true, 25),
        Arguments.of("26 isn't a square number", false, 26));
  }
}
