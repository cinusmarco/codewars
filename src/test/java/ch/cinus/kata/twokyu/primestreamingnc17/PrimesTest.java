package ch.cinus.kata.twokyu.primestreamingnc17;

import static org.assertj.core.api.Assertions.assertThat;

import ch.cinus.kata.threekyu.primestreamingpg13.Primes;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrimesTest {

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(0, 10, new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29}),
        Arguments.of(10, 10, new int[] {31, 37, 41, 43, 47, 53, 59, 61, 67, 71}),
        Arguments.of(
            1000, 10, new int[] {7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017}));
  }

  @ParameterizedTest
  @MethodSource(value = "provider")
  void test(int skip, int limit, int... expect) {
    int[] found = Primes.stream().skip(skip).limit(limit).toArray();
    assertThat(found).isEqualTo(expect);
  }
}
