package ch.cinus.kata.six_kyu.strongestevennumber;

public class StrongestEvenNumber {

  public static int strongestEven(int n, int m) {
    int strongness = -1;
    int number = -1;
    int lowerBound = n % 2 == 0 ? n : n + 1;
    int upperBound = m % 2 == 0 ? m : m - 1;
    for (int i = lowerBound; i <= upperBound; i += 2) {
      int s = Integer.numberOfTrailingZeros(i);
      if (s > strongness) {
        strongness = s;
        number = i;
      }
    }
    return number;
  }
}
