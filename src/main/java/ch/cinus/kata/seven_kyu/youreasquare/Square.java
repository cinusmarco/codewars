package ch.cinus.kata.seven_kyu.youreasquare;

public class Square {
  public static boolean isSquare(int n) {
    double sqrt = Math.sqrt(n);
    return (int) sqrt == sqrt;
  }
}
