package ch.cinus.kata.sevenkyu.vowelcount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class VowelsTest {

  @ParameterizedTest(name = "{index} {1} has {0} vowels")
  @MethodSource("provider")
  void test(int expected, String str) {
    assertEquals(expected, Vowels.getCount(str));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(5, "abracadabra"),
        Arguments.of(3, "elephant"),
        Arguments.of(0, "bcd"),
        Arguments.of(0, ""),
        Arguments.of(0, null),
        Arguments.of(3, "elephant"),
        Arguments.of(5, "elephant riding"));
  }
}
