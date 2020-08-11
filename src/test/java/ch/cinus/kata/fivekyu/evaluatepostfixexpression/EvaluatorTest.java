package ch.cinus.kata.fivekyu.evaluatepostfixexpression;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EvaluatorTest {

  private static Stream<Arguments> provider() {
    return Stream.of(
            Arguments.of("1 1 +", 2),
            Arguments.of("1 1 -", 0),
            Arguments.of("1 1 *", 1),
            Arguments.of("1 1 /", 1),
            Arguments.of("10 2 /", 5),
            Arguments.of("10 20 -", -10),
            Arguments.of("1 -1 +", 0),
            Arguments.of("-1 -1 +", -2),
            Arguments.of("1 1 + 1 -", 1),
            Arguments.of("10 10 + 10 -", 10),
            Arguments.of("10 10 10 + -", -10),
            Arguments.of("2 3 9 4 / + *", 10),
            Arguments.of("2 3 9 4 / + * 4 +", 14));
  }

  private static Stream<Arguments> provider4Compute() {
    return Stream.of(
            Arguments.of("1 1 +", 2),
            Arguments.of("1 1 -", 0),
            Arguments.of("1 1 *", 1),
            Arguments.of("1 1 /", 1),
            Arguments.of("1 -1 +", 0),
            Arguments.of("-1 -1 +", -2),
            Arguments.of("10 10 +", 20),
            Arguments.of("100 100 +", 200),
            Arguments.of("10 2 /", 5));
  }

  private static Stream<Arguments> provider4Reduce() {
    return Stream.of(
            Arguments.of("1 1 +", "2"),
            Arguments.of("1 1 + 1 -", "2 1 -"),
            Arguments.of("10 10 10 + -", "10 20 -"),
            Arguments.of("10 20 -", "-10"),
            Arguments.of("10 2 /", "5"));
  }

  @ParameterizedTest
  @MethodSource(value = "provider")
  public void test(String input, long expected) {
    Evaluator eval = new Evaluator();
    assertThat(eval.evaluate(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource(value = "provider4Compute")
  public void testCompute(String input, long expected) {
    Evaluator eval = new Evaluator();
    assertThat(eval.compute(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource(value = "provider4Reduce")
  public void testReduce(String input, String expected) {
    Evaluator eval = new Evaluator();
    assertThat(eval.reduce(input)).isEqualTo(expected);
  }
}
