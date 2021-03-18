package ch.cinus.kata.sixkyu.detectpangram;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class PangramTest {

  @ParameterizedTest(name = "Function --> check | {index} {0} should be {1}")
  @MethodSource("provider")
  void test(String s, boolean expected) {
    PangramChecker pc = new PangramChecker();
    assertThat(pc.check(s), equalTo(expected));
  }

  @ParameterizedTest(name = "Function --> check2 | {index} {0} should be {1}")
  @MethodSource("provider")
  void test2(String s, boolean expected) {
    PangramChecker pc = new PangramChecker();
    assertThat(pc.check2(s), equalTo(expected));
  }

  @ParameterizedTest(name = "Function --> check3 | {index} {0} should be {1}")
  @MethodSource("provider")
  void test3(String s, boolean expected) {
    PangramChecker pc = new PangramChecker();
    assertThat(pc.check3(s), equalTo(expected));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("The quick brown fox jumps over the lazy dog.", true),
        Arguments.of("You shall not pass!", false),
        Arguments.of("abcdefghijklmopqrstuvwxyz", false), // missing n
        Arguments.of("abcdefghijklmnopqrstuvwxyz", true),
        Arguments.of("ab1cde4fgh6ijk8lmno0pqrs'''tuvwxyz", true),
        Arguments.of("abcDefGhijKlmnOpqrsTUvwxyz", true),
        Arguments.of("aAbcdefghijklmopqrstuvwxyz", false), // missing n
        Arguments.of("abcDefGhijKlmnOpqrsTUvwxyz", true));
  }
}
