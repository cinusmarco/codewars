package ch.cinus.kata.fivekyu.int32toipv4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KataTest {

  private static Stream<Arguments> generateData() {
    return Stream.of(
            Arguments.of(2154959208L, "128.114.17.104"),
            Arguments.of(0, "0.0.0.0"),
            Arguments.of(32, "0.0.0.32"),
            Arguments.of(2149583361L, "128.32.10.1"));
  }

  @ParameterizedTest
  @MethodSource(value = "generateData")
  void test(long input, String expected) {
    assertThat(Kata.longToIP(input)).isEqualTo(expected);
  }
}
