package ch.cinus.kata.fourkyu.rankingsystems;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SolutionTestJunit4 {
  @Test
  public void checkDefaultRanking_isMinus8() {
    User user = new User();
    assertEquals(-8, user.rank);
  }

  @Test
  public void checkDefaultProgress_is0() {
    User user = new User();
    assertEquals(0, user.progress);
  }

  @Test
  public void test() throws Exception {
    increaseProgressTest(-8, 0, -7, -8, 10);
    increaseProgressTest(-8, 10, -7, -8, 20);
    increaseProgressTest(-8, 10, -4, -7, 70);
    increaseProgressTest(-8, 0, -6, -8, 40);
    increaseProgressTest(-8, 0, -8, -8, 3);
    increaseProgressTest(-5, 0, -6, -5, 1);
    increaseProgressTest(-5, 0, -8, -5, 0);
    increaseProgressTest(-2, 90, -1, -1, 0);
    increaseProgressTest(-1, 90, 1, 1, 0);
    increaseProgressTest(-8, 0, 1, -2, 40);
    increaseProgressTest(8, 100, 8, 8, 100);
  }

  void increaseProgressTest(
      int startingRank,
      int startingProgress,
      int exerciseRank,
      int expectedRank,
      int expectedProgress)
      throws Exception {
    User user = new User(startingRank, startingProgress);

    user.incProgress(exerciseRank);

    assertEquals(expectedRank, user.rank);
    assertEquals(expectedProgress, user.progress);
  }
}
