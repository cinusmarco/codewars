package ch.cinus.kata.fivekyu.simplepiglatin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PigLatinTest {
  @ParameterizedTest
  @MethodSource("provider")
  void FixedTests(String expected, String toTranslate) {
    assertThat(PigLatin.pigIt(toTranslate)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("igPay atinlay siay oolcay", "Pig latin is cool"),
        Arguments.of("elloHay orldway !", "Hello world !"),
        Arguments.of("uisQay ustodietcay psosiay ustodescay ?","Quis custodiet ipsos custodes ?"),
        Arguments.of("hisTay siay ymay tringsay", "This is my string"));
  }
}
