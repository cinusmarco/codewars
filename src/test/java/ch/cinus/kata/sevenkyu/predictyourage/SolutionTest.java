package ch.cinus.kata.sevenkyu.predictyourage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SolutionTest {

  @ParameterizedTest
  @MethodSource(value = "provider")
  void test(List<Integer> grandparentAges, int expectedAge) {
    assertThat(
            Solution.predictAge(
                grandparentAges.get(0),
                grandparentAges.get(1),
                grandparentAges.get(2),
                grandparentAges.get(3),
                grandparentAges.get(4),
                grandparentAges.get(5),
                grandparentAges.get(6),
                grandparentAges.get(7)))
        .isEqualTo(expectedAge);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(List.of(65, 60, 75, 55, 60, 63, 64, 45), 86),
        Arguments.of(List.of(32, 54, 76, 65, 34, 63, 64, 45), 79));
  }
}
