package ch.cinus.kata.sixkyu.findtheparityoutlier;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FindOutlierTest {

  @ParameterizedTest
  @MethodSource("provider")
  void test(int[] input, int expected) {
    assertThat(FindOutlier.find(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(new int[] {2, 6, 8, -10, 3}, 3),
        Arguments.of(
            new int[] {206847684, 1056521, 7, 17, 1901, 21104421, 7, 1, 35521, 1, 7781}, 206847684),
        Arguments.of(new int[] {Integer.MAX_VALUE, 0, 1}, 0));
  }
}
