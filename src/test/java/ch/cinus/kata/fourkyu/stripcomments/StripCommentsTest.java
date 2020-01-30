package ch.cinus.kata.fourkyu.stripcomments;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StripCommentsTest {

  @ParameterizedTest
  @MethodSource("provider")
  void stripComments_shouldReturnExpected_whenInputIsFeasible(
      String input, String markers, String expected) {
    String[] commentSymbols = markers.split(" ");
    assertThat(StripComments.stripComments(input, commentSymbols)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(
            "apples, pears # and bananas\ngrapes\nbananas !apples",
            "# !",
            "apples, pears\ngrapes\nbananas"),
        Arguments.of("a\n b\nc ", "# $", "a\n b\nc"),
        Arguments.of("a", "1", "a"),
        Arguments.of("a", "a", ""),
        Arguments.of("             ", "#", ""),
        Arguments.of("#One #Two", "#", ""),
        Arguments.of("# I'm a comment", "#", "")

    );
  }
}
