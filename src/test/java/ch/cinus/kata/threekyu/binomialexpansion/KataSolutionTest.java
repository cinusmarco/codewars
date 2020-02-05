package ch.cinus.kata.threekyu.binomialexpansion;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KataSolutionTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void expansionTest(String input, String expected) {
    assertThat(KataSolution.expand(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource(value = "binomialTestProvider")
  void binomialCoefficientTest(int n, int k, int expected) {
    assertThat(KataSolution.C(n, k)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource(value = "beautifyProvider")
  void beautifyTest(String input, String expected) {
    assertThat(KataSolution.beautify(input)).isEqualTo(expected);
  }

  private static Stream<Arguments> binomialTestProvider() {
    return Stream.of(
        Arguments.of(15, 7, 6435),
        Arguments.of(10, 7, 120),
        Arguments.of(6, 4, 15),
        Arguments.of(4, 2, 6),
        Arguments.of(3, 2, 3),
        Arguments.of(3, 1, 3),
        Arguments.of(8, 2, 28),
        Arguments.of(3, 0, 1));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("(x-1)^0", "1"),
        Arguments.of("(x+1)^0", "1"),
        Arguments.of("(x+1)^1", "x+1"),
        Arguments.of("(X+1)^1", "X+1"),
        Arguments.of("(x-1)^1", "x-1"),
        Arguments.of("(x+1)^2", "x^2+2x+1"),
        Arguments.of("(x+1)^2", "x^2+2x+1"),
        Arguments.of("(A+1)^2", "A^2+2A+1"),
        Arguments.of("(Aa+1)^2", "Aa^2+2Aa+1"),
        Arguments.of("(x+1)^3", "x^3+3x^2+3x+1"),
        Arguments.of("(7x-7)^0", "1"),
        Arguments.of("(-7x-7)^0", "1"),
        Arguments.of("(2x-3)^3", "8x^3-36x^2+54x-27"),
        Arguments.of("(-2x-3)^3", "-8x^3-36x^2-54x-27"),
        Arguments.of("(-x-1)^3", "-x^3-3x^2-3x-1"),
        Arguments.of("(-9t-0)^2", "81t^2"),
        Arguments.of("(0t+2)^2", "4"),
        Arguments.of(
            "(12a+1)^7", "35831808a^7+20901888a^6+5225472a^5+725760a^4+60480a^3+3024a^2+84a+1"),
        Arguments.of(
            "((-6f-12)^9",
            "-10077696f^9-181398528f^8-1451188224f^7-6772211712f^6-20316635136f^5-40633270272f^4-54177693696f^3-46438023168f^2-23219011584f-5159780352"),
        Arguments.of("(-2x-3)^3", "-8x^3-36x^2-54x-27"),
        Arguments.of("(5m+3)^4", "625m^4+1500m^3+1350m^2+540m+81"),
        Arguments.of("(-5m+3)^4", "625m^4-1500m^3+1350m^2-540m+81"));
  }

  private static Stream<Arguments> beautifyProvider() {
    return Stream.of(
        Arguments.of("+1", "1"),
        Arguments.of("-1", "-1"),
        Arguments.of("x+1", "x+1"),
        Arguments.of("x-1", "x-1"),
        Arguments.of("1x^1+1", "x+1"),
        Arguments.of("1x^1-1", "x-1"),
        Arguments.of("81t^2+0t+0", "81t^2"),
        Arguments.of("+0t^2+0t^1+4", "4"),
        Arguments.of("0t^2+0t^1+4", "4"),
        Arguments.of("625m^4-1500m^3+1350m^2-540m+81", "625m^4-1500m^3+1350m^2-540m+81"));
  }
}
