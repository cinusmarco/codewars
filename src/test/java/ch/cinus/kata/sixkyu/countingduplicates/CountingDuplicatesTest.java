package ch.cinus.kata.sixkyu.countingduplicates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CountingDuplicatesTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void basicTest(String input, int expected) {
    assertThat(CountingDuplicates.duplicateCount(input)).isEqualTo(expected);
  }

  @Test
  void reallyLongStringContainingDuplicatesReturnsThree() {
    String testThousandA = new String(new char[1000]).replace('\0', 'a');
    String testHundredB = new String(new char[100]).replace('\0', 'b');
    String testTenC = new String(new char[10]).replace('\0', 'c');
    String test1CapitalA = new String(new char[1]).replace('\0', 'A');
    String test1d = new String(new char[1]).replace('\0', 'd');
    String test = test1d + test1CapitalA + testTenC + testHundredB + testThousandA;

    assertThat(CountingDuplicates.duplicateCount(test)).isEqualTo(3);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("abcde", 0),
        Arguments.of("abcdea", 1),
        Arguments.of("indivisibility", 1),
        Arguments.of("aA11", 2),
        Arguments.of("indivisibilities", 2));
  }
}
