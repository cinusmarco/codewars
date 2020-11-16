/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.fourkyu.matrixdeterminant;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatrixTest {

  @ParameterizedTest
  @MethodSource(value = "testDataProvider")
  void testDeterminant(int[][] matrix, int expectedDeterminant) {
    assertThat(Matrix.determinant(matrix)).isEqualTo(expectedDeterminant);
  }

  private static Stream<Arguments> testDataProvider() {
    return Stream.of(
        Arguments.of(new int[][] {{1}}, 1),
        Arguments.of(new int[][] {{1, 3}, {2, 5}}, -1),
        Arguments.of(
            new int[][] {
              {-2, -5, 9, 9, -9, -8, 4, -1},
              {-3, 5, -7, -3, -9, -4, -2, 1},
              {1, -9, -8, 8, 0, -5, 1, 0},
              {0, 7, 4, -1, -8, -3, -1, -8},
              {6, 7, 9, -1, -8, 8, -6, -9},
              {3, 2, 0, -8, -10, -8, -4, 7},
              {7, 8, 2, 3, -2, -2, 1, -6},
              {3, 1, -5, -8, 7, 0, 9, -8}
            },
            -313208168),
        Arguments.of(new int[][] {{2, 5, 3}, {1, -2, -1}, {1, 3, 4}}, -20));
  }
}
