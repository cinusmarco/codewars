package ch.cinus.kata.sixkyu.diseasespread;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EpidemTest {

  @ParameterizedTest
  @MethodSource("provider")
  public void fixedTest(int tm, int n, int s0, int i0, double b, double a, int expected) {
    long actual = Epidem.epidemic(tm, n, s0, i0, b, a);
    long r = Math.abs(actual - expected);
    assertThat(r).isLessThanOrEqualTo(1L);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(18, 432, 1004, 1, 0.00209, 0.51, 420),
        Arguments.of(12, 288, 1007, 2, 0.00206, 0.45, 461),
        Arguments.of(13, 312, 999, 1, 0.00221, 0.55, 409),
        Arguments.of(24, 576, 1005, 1, 0.00216, 0.45, 474),
        Arguments.of(24, 576, 982, 1, 0.00214, 0.44, 460),
        Arguments.of(20, 480, 1000, 1, 0.00199, 0.53, 386),
        Arguments.of(28, 672, 980, 1, 0.00198, 0.44, 433),
        Arguments.of(14, 336, 996, 2, 0.00206, 0.41, 483),
        Arguments.of(13, 312, 993, 2, 0.0021, 0.51, 414),
        Arguments.of(28, 672, 999, 1, 0.00197, 0.55, 368));
  }
}
