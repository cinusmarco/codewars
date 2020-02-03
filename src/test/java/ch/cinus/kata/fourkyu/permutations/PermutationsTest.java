package ch.cinus.kata.fourkyu.permutations;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PermutationsTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void test(String input, List<String> result) {
    assertThat(Permutations.singlePermutations(input))
        .containsExactlyInAnyOrder(result.toArray(new String[0]));
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("a", List.of("a")),
        Arguments.of("ab", List.of("ab", "ba")),
        Arguments.of("aabb", List.of("aabb", "abab", "abba", "baab", "baba", "bbaa")));
  }
}
