package ch.cinus.kata.sixkyu.decodethemorsecode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MorseCodeDecoderTest {
  private MorseCodeDecoder decoder;

  @ParameterizedTest(name = "{0} is {1}")
  @MethodSource(value = "provider")
  void testDecoder(String input, String expected) {
    assertThat(MorseCodeDecoder.decode(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(".... . -.--   .--- ..- -.. .", "HEY JUDE"),
        Arguments.of(".- . .. . . .   . ...---...", "AEIEEE ESOS"),
        Arguments.of(
            "... --- ... -.-.--   - .... .   --.- ..- .. -.-. -.-   -... .-. --- .-- -.   ..-. --- -..-   .--- ..- -- .--. ...   --- ...- . .-.   - .... .   .-.. .- --.. -.--   -.. --- --. .-.-.-",
            "SOS! THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG."));
  }
}
