package ch.cinus.kata.sevenkyu.pokerhandisflush;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KataTest {
  @ParameterizedTest
  @MethodSource(value = "provider")
  void test(String[] input, boolean expected) {
    assertThat(Kata.CheckIfFlush(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(new String[] {"AS", "3S", "9S", "KS", "4S"}, true),
        Arguments.of(new String[] {"AD", "4S", "7H", "KC", "5S"}, false),
        Arguments.of(new String[] {"AD", "4S", "10H", "KC", "5S"}, false),
        Arguments.of(new String[] {"QD", "4D", "10D", "KD", "5D"}, true));
  }
}
