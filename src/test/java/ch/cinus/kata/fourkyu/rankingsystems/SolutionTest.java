package ch.cinus.kata.fourkyu.rankingsystems;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SolutionTest {

  @Test
  void checkDefaultRanking_isMinus8() {
    User user = new User();
    assertThat(user.rank).isEqualTo(-8);
  }

  @Test
  void checkDefaultProgress_is0() {
    User user = new User();
    assertThat(user.progress).isEqualTo(0);
  }


  @ParameterizedTest(name = "User[{0};{1}].incProgress({2}) => User[{3};{4}]")
  @MethodSource("provider")
  void increaseProgressTest(
      int startingRank,
      int startingProgress,
      int exerciseRank,
      int expectedRank,
      int expectedProgress) {
    User user = new User(startingRank, startingProgress);

    user.incProgress(exerciseRank);

    assertThat(user.rank).isEqualTo(expectedRank);
    assertThat(user.progress).isEqualTo(expectedProgress);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(-8, 0, -7, -8, 10),
        Arguments.of(-8, 10, -7, -8, 20),
        Arguments.of(-8, 10, -4, -7, 70),
        Arguments.of(-8, 0, -6, -8, 40),
        Arguments.of(-8, 0, -8, -8, 3),
        Arguments.of(-5, 0, -6, -5, 1),
        Arguments.of(-5, 0, -8, -5, 0),
        Arguments.of(-2, 90, -1, -1, 0),
        Arguments.of(-1, 90, 1, 1, 0),
        Arguments.of(-8, 0, 1, -2, 40));
  }
}
