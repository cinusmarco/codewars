package ch.cinus.kata.sixkyu.strongestevennumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StrongestEvenNumberTest {
  @ParameterizedTest
  @MethodSource(value = "provider")
  public void sampleTests(int lowerBound, int upperBound, int expected) {

    assertThat(StrongestEvenNumber.strongestEven(lowerBound, upperBound)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(1, 2, 2),
        Arguments.of(5, 10, 8),
        Arguments.of(48, 56, 48),
        Arguments.of(1593909165, 2104840083, 1610612736),
        Arguments.of(129, 193, 192));
  }
}
