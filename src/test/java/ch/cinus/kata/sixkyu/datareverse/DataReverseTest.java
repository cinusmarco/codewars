package ch.cinus.kata.sixkyu.datareverse;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DataReverseTest {

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.arguments(
            new int[] {
              1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1,
              0, 1, 0
            },
            new int[] {
              1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1,
              1, 1, 1
            }),
        Arguments.arguments(
            new int[] {
              1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1,
              0, 1, 0
            },
            new int[] {
              1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1,
              1, 1, 1
            }));
  }

  @ParameterizedTest
  @MethodSource(value = "provider")
  public void test(int[] input, int[] expected) {
    assertThat(Kata.DataReverse(input)).isEqualTo(expected);
  }
}
