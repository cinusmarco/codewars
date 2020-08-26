package ch.cinus.kata.fourkyu.decodethemorsecodeadvanced;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MorseCodeDecoderTest {

  @ParameterizedTest(name = "{0} is {1}")
  @MethodSource(value = "providerBits")
  void decodeBits(String input, String expected) {
    assertThat(MorseCodeDecoder.decodeBits(input)).isEqualTo(expected);
  }

  @ParameterizedTest(name = "{0} is {1}")
  @MethodSource(value = "providerMorse")
  void decodeMorse(String input, String expected) {
    assertThat(MorseCodeDecoder.decodeMorse(input)).isEqualTo(expected);
  }

  @ParameterizedTest(name = "{0} is {1}")
  @MethodSource(value = "providerCompleteDecode")
  void completeDecoder(String input, String expected) {
    assertThat(MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits(input)))
        .isEqualTo(expected);
  }

  private static Stream<Arguments> providerBits() {
    return Stream.of(
            Arguments.of(
                    "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011",
                    ".... . -.--   .--- ..- -.. ."),
            Arguments.of("01110", "."),
            Arguments.of("000000011100000", "."),
            Arguments.of("1", "."),
            Arguments.of("101", ".."),
            Arguments.of("11011", ".."),
            Arguments.of("10001", ". ."),
            Arguments.of("10000001", ". ."),
            Arguments.of("1110111", "--"),
            Arguments.of("111000111", ".."),
            Arguments.of("10000000001", ". ."),
            Arguments.of("1100011", ". ."),
            Arguments.of("111000111", ". .")
    );
  }

  private static Stream<Arguments> providerMorse() {
    return Stream.of(
        Arguments.of(".... . -.--   .--- ..- -.. .", "HEY JUDE"),
        Arguments.of(".- . .. . . .   . ...---...", "AEIEEE ESOS"),
        Arguments.of(
            "... --- ... -.-.--   - .... .   --.- ..- .. -.-. -.-   -... .-. --- .-- -.   ..-. --- -..-   .--- ..- -- .--. ...   --- ...- . .-.   - .... .   .-.. .- --.. -.--   -.. --- --. .-.-.-",
            "SOS! THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG."));
  }

  private static Stream<Arguments> providerCompleteDecode() {
    return Stream.of(
            Arguments.of("1", "E"),
            Arguments.of("101", "I"),
            Arguments.of(
                    "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011",
                    "HEY JUDE"));
  }
}
