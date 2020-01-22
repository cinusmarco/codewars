package ch.cinus.kata.sixkyu.yourorderplease;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrderTest {

  @ParameterizedTest(name = "{index} {0}")
  @MethodSource({"stringProvider"})
  void test(String reason, String input, String expected) {
    assertThat(reason, Order.order(input), equalTo(expected));
  }

  private static Stream<Arguments> stringProvider() {
    return Stream.of(
        Arguments.of("4 words to sort", "is2 Thi1s T4est 3a", "Thi1s is2 3a T4est"),
        Arguments.of(
            "6 words to sort",
            "4of Fo1r pe6ople g3ood th5e the2",
            "Fo1r the2 g3ood 4of th5e pe6ople"),
        Arguments.of("Empty input should return empty string", "", ""));
  }
}
