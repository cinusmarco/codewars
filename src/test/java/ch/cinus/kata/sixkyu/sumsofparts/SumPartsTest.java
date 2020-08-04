package ch.cinus.kata.sixkyu.sumsofparts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SumPartsTest {

  private static Stream<Arguments> generateData() {
    return Stream.of(
            Arguments.of(new int[]{}, new int[]{0}),
            Arguments.of(new int[]{0, 1, 3, 6, 10}, new int[]{20, 20, 19, 16, 10, 0}),
            Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, new int[]{21, 20, 18, 15, 11, 6, 0}),
            Arguments.of(
                    new int[]{744125, 935, 407, 454, 430, 90, 144, 6710213, 889, 810, 2579358},
                    new int[]{
                            10037855, 9293730, 9292795, 9292388, 9291934, 9291504, 9291414, 9291270, 2581057,
                            2580168, 2579358, 0
                    }));
  }

  @ParameterizedTest
  @MethodSource(value = "generateData")
  void test(int[] ls, int[] expected) {
    int[] actual = SumParts.sumParts(ls);
    assertThat(actual).isEqualTo(expected);
  }
}
