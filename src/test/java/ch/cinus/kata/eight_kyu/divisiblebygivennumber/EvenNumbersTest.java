package ch.cinus.kata.eight_kyu.divisiblebygivennumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EvenNumbersTest {

  @ParameterizedTest
  @MethodSource(value = "supplier")
  void test(int[] numbers, int divisor, int[] expected) {
    assertThat(EvenNumbers.divisibleBy(numbers, divisor)).isEqualTo(expected);
  }

  private static Stream<Arguments> supplier() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 2, new int[] {2, 4, 6}),
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 3, new int[] {3, 6}),
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 4, new int[] {4}));
  }
}
