package ch.cinus.kata.six_kyu.findodd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FindOddTest {

  @ParameterizedTest(name = "{index} {0} in {1}")
  @MethodSource("provider")
  void findTest(String desc, int[] input, int expected) {
    assertThat(desc, FindOdd.findIt(input), equalTo(expected));
  }

  @ParameterizedTest(name = "{index} {0} in {1}")
  @MethodSource("provider")
  void findItCleverTest(String desc, int[] input, int expected) {
    assertThat(desc, FindOdd.findItClever(input), equalTo(expected));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(
            "There are three 5",
            new int[] {20, 1, -1, 2, -2, 3, 3, 5, 5, 1, 2, 4, 20, 4, -1, -2, 5},
            5),
        Arguments.of("There is one -1", new int[] {1, 1, 2, -2, 5, 2, 4, 4, -1, -2, 5}, -1),
        Arguments.of("There are three 5", new int[] {20, 1, 1, 2, 2, 3, 3, 5, 5, 4, 20, 4, 5}, 5),
        Arguments.of("There is one 10", new int[] {10}, 10),
        Arguments.of("There is one 10", new int[] {1, 1, 1, 1, 1, 1, 10, 1, 1, 1, 1}, 10),
        Arguments.of("There is one 1", new int[] {5, 4, 3, 2, 1, 5, 4, 3, 2, 10, 10}, 1));
  }
}
