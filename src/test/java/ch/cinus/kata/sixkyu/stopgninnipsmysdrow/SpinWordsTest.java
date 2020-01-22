package ch.cinus.kata.sixkyu.stopgninnipsmysdrow;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SpinWordsTest {

  private static Stream<Arguments> provider() {
    return Stream.of(
            Arguments.of("emocleW", "Welcome"),
            Arguments.of("Hey wollef sroirraw", "Hey fellow warriors"));
  }

  @ParameterizedTest
  @MethodSource(value = "provider")
  void test(String input, String expected) {
    SpinWords systemUnderTest = new SpinWords();
    assertThat(systemUnderTest.spinWords(input)).isEqualTo(expected);
  }
}
