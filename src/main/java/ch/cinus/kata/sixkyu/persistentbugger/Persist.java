package ch.cinus.kata.sixkyu.persistentbugger;

public class Persist {
  public static int persistence(long n) {
    if (n < 10) {
      return 0;
    } else {
      return 1
          + persistence(
              String.valueOf(n)
                  .chars()
                  .map(Character::getNumericValue)
                  .reduce((i, i1) -> i * i1)
                  .getAsInt());
    }
  }
}
