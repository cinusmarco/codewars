package ch.cinus.kata.beta.fastprimetest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void test(boolean answer, int toBeTested) {
    assertThat(Prime.isPrime(toBeTested)).isEqualTo(answer);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(false, 0),
        Arguments.of(false, 1),
        Arguments.of(true, 2),
        Arguments.of(true, 7),
        Arguments.of(false, 15),
        Arguments.of(false, 106),
        Arguments.of(false, 189),
        Arguments.of(true, 211),
        Arguments.of(true, 541));
  }
}
