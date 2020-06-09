package ch.cinus.kata.sixkyu.splitandaddarray;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KataTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void test(int[] input, int n, int[] expected) {
    int[] result = Kata.splitAndAdd(input, n);
    assertThat(result).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(new int[] {4, 2, 5, 3, 2, 5, 7}, 2, new int[] {10, 18}),
        Arguments.of(new int[] {1, 2, 3, 4, 5}, 2, new int[] {5, 10}),
        Arguments.of(new int[] {1, 2, 3, 4, 5}, 3, new int[] {15}),
        Arguments.of(new int[] {15}, 3, new int[] {15}),
        Arguments.of(new int[] {32, 45, 43, 23, 54, 23, 54, 34}, 2, new int[] {183, 125}),
        Arguments.of(
            new int[] {32, 45, 43, 23, 54, 23, 54, 34},
            0,
            new int[] {32, 45, 43, 23, 54, 23, 54, 34}),
        Arguments.of(new int[] {3, 234, 25, 345, 45, 34, 234, 235, 345}, 3, new int[] {305, 1195}),
        Arguments.of(
            new int[] {
              3, 234, 25, 345, 45, 34, 234, 235, 345, 34, 534, 45, 645, 645, 645, 4656, 45, 3
            },
            4,
            new int[] {1040, 7712}),
        Arguments.of(
            new int[] {23, 345, 345, 345, 34536, 567, 568, 6, 34536, 54, 7546, 456},
            20,
            new int[] {79327}));
  }
}
