package ch.cinus.kata.eightkyu.findsmallestintinarray;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SmallestIntegerFinderTest {

  @ParameterizedTest
  @MethodSource("provider")
  void test(int[] array, int expected) {
    assertThat("Finding minimum", SmallestIntegerFinder.findSmallestInt(array), equalTo(expected));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(new int[] {78, 56, 232, 12, 11, 43}, 11),
        Arguments.of(new int[] {78, 56, -2, 12, 8, -33}, -33),
        Arguments.of(new int[] {0, Integer.MIN_VALUE, Integer.MAX_VALUE}, Integer.MIN_VALUE));
  }
}
