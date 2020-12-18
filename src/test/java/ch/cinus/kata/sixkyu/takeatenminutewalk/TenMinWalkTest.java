/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.sixkyu.takeatenminutewalk;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TenMinWalkTest {

  @ParameterizedTest
  @MethodSource(value = "valueprovider")
  void test(char[] input, boolean expected) {
    assertThat(TenMinWalk.isValid(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> valueprovider() {
    return Stream.of(
        Arguments.of(new char[] {'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's'}, true),
        Arguments.of(new char[] {'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e'}, false),
        Arguments.of(new char[] {'w'}, false),
        Arguments.of(new char[] {'n', 'n', 'n', 's', 'n', 's', 'n', 's', 'n', 's'}, false));
  }
}
