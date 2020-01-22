package ch.cinus.kata.sevenkyu.youreasquare;

public class Square {
  public static boolean isSquare(int n) {
    double sqrt = Math.sqrt(n);
    return (int) sqrt == sqrt;
  }
}
