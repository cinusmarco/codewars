package ch.cinus.kata.fourkyu.matrixdeterminant;

public class Matrix {
  public static int determinant(int[][] matrix) {
    return computeDeterminant(matrix);
  }

  private static int computeDeterminant(int[][] matrix) {
    int sum = 0;
    if (matrix.length == 1) { // 1x1 is value itself
      return matrix[0][0];
    }
    if (matrix.length == 2) { // 2x2 easy to compute
      return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    for (int i = 0; i < matrix.length; i++) { // Compute by Laplace expansion
      int[][] smaller = getSubMatrix(matrix, i);
      int sign = determineSign(i);
      sum +=
          sign
              * matrix[0][i]
              * computeDeterminant(
                  smaller); // recursive step: The final determinant is the sum of all "smaller" determinants
    }

    return sum;
  }

  private static int[][] getSubMatrix(int[][] matrix, int i) {
    int[][] submatrix = new int[matrix.length - 1][matrix.length - 1];
    for (int row = 1; row < matrix.length; row++) {
      for (int column = 0; column < matrix.length; column++) {
        if (column < i) {
          submatrix[row - 1][column] = matrix[row][column];
        } else if (column > i) {
          submatrix[row - 1][column - 1] = matrix[row][column];
        }
      }
    }
    return submatrix;
  }

  private static int determineSign(int i) {
    return i % 2 == 0 ? 1 : -1;
  }
}
