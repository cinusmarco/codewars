package ch.cinus.kata.sixkyu.encryptthis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EncryptTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void basicTest(String input, String expected) {
    assertThat(Encrypt.encryptThis(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("Hello", "72olle"),
        Arguments.of("good", "103doo"),
        Arguments.of("hello world", "104olle 119drlo"),
        Arguments.of(
            "A wise old owl lived in an oak", "65 119esi 111dl 111lw 108dvei 105n 97n 111ka"),
        Arguments.of(
            "The more he saw the less he spoke",
            "84eh 109ero 104e 115wa 116eh 108sse 104e 115eokp"),
        Arguments.of(
            "The less he spoke the more he heard",
            "84eh 108sse 104e 115eokp 116eh 109ero 104e 104dare"),
        Arguments.of(
            "Why can we not all be like that wise old bird",
            "87yh 99na 119e 110to 97ll 98e 108eki 116tah 119esi 111dl 98dri"),
        Arguments.of(
            "Thank you Piotr for all your help", "84kanh 121uo 80roti 102ro 97ll 121ruo 104ple"));
  }
}
