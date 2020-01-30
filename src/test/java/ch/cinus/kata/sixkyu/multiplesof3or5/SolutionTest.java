package ch.cinus.kata.sixkyu.multiplesof3or5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class SolutionTest {
  @ParameterizedTest
  @CsvFileSource(resources = "/multiplesOf3or5.csv")
  void increaseProgressTest(int upperLimit, int expected) {
    Solution s = new Solution();
    assertThat(s.solution(upperLimit)).isEqualTo(expected);
  }
}
