package ch.cinus.kata.fourkyu.rankingsystems;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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

  @ParameterizedTest(name = "Invalid Rank {0}")
  @CsvFileSource(resources = "/invalid.csv")
  void increaseProgressTest_shouldThrowException_whenInvalidParameters(int invalidRank) {
    User user = new User();

    assertThatThrownBy(() -> user.incProgress(invalidRank))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest(name = "User[{0};{1}].incProgress({2}) => User[{3};{4}]")
  @CsvFileSource(resources = "/ranking.csv")
  void increaseProgressTest(
      int startingRank,
      int startingProgress,
      int exerciseRank,
      int expectedRank,
      int expectedProgress)
      throws Exception {
    User user = new User(startingRank, startingProgress);

    user.incProgress(exerciseRank);

    assertThat(user.rank).isEqualTo(expectedRank);
    assertThat(user.progress).isEqualTo(expectedProgress);
  }
}
