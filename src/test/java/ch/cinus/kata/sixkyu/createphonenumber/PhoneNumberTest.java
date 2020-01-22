package ch.cinus.kata.sixkyu.createphonenumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PhoneNumberTest {
  @ParameterizedTest(name = "{index} {0}")
  @MethodSource("provider")
  void tests(String desc, int[] input, String expected) {
    assertThat(desc, PhoneNumber.createPhoneNumber(input), equalTo(expected));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("(123) 456-7890", new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "(123) 456-7890"));
  }
}
