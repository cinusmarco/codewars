package ch.cinus.kata.sixkyu.romannumeralsencoder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ConversionTest {

  @ParameterizedTest(name = "{index} {0}")
  @MethodSource("provider")
  void shouldConvertToRoman(String desc, String expected, int number) {
    Conversion conversion = new Conversion();
    assertThat(desc, conversion.solution(number), equalTo(expected));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("solution(1) should equal to I", "I", 1),
        Arguments.of("solution(3) should equal to III", "III", 3),
        Arguments.of("solution(4) should equal to IV", "IV", 4),
        Arguments.of("solution(6) should equal to VI", "VI", 6),
        Arguments.of("solution(2008) should equal to MMVIII", "MMVIII", 2008),
        Arguments.of("solution(1666) should equal to MDCLXVI", "MDCLXVI", 1666),
        Arguments.of("solution(990) should equal to CMXC", "CMXC", 990),
        Arguments.of("solution(999) should equal to CMXCIX", "CMXCIX", 999),
        Arguments.of("solution(1990) should equal to MCMXC", "MCMXC", 1990),
        Arguments.of("solution(1900) should equal to MCM", "MCM", 1900),
        Arguments.of("solution(49) should equal to XLIX", "XLIX", 49),
        Arguments.of("solution(19) should equal to XIX", "XIX", 19),
        Arguments.of("solution(490) should equal to CDXC", "CDXC", 490),
        Arguments.of("solution(1999) should equal to MCMXCIX", "MCMXCIX", 1999));
  }
}
