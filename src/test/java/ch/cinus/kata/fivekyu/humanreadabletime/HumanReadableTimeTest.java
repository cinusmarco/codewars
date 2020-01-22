package ch.cinus.kata.fivekyu.humanreadabletime;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HumanReadableTimeTest {

  @ParameterizedTest(name = "makeReadable({0}) is {1}")
  @MethodSource(value = "provider")
  void test(int input, String expected) {
    assertThat(HumanReadableTime.makeReadable(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(0, "00:00:00"),
        Arguments.of(5, "00:00:05"),
        Arguments.of(60, "00:01:00"),
        Arguments.of(86399, "23:59:59"),
        Arguments.of(86400, "24:00:00"),
        Arguments.of(3600, "01:00:00"),
        Arguments.of(3660, "01:01:00"),
        Arguments.of(359999, "99:59:59"));
  }
}
