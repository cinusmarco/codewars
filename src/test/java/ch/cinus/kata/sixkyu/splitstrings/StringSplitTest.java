package ch.cinus.kata.sixkyu.splitstrings;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringSplitTest {

  @ParameterizedTest
  @MethodSource("provider")
  public void test(String input, String[] expected) {

    assertThat(StringSplit.solution(input)).isEqualTo(expected);

  }


  private static Stream<Arguments> provider() {
    return Stream.of(
            Arguments.of("abcdef", new String[]{"ab", "cd", "ef"}),
            Arguments.of("HelloWorld", new String[]{"He", "ll", "oW", "or", "ld"}),
            Arguments.of("abcde", new String[]{"ab", "cd", "e_"}),
            Arguments.of("LovePizza", new String[]{"Lo", "ve", "Pi", "zz", "a_"}));
  }
}
