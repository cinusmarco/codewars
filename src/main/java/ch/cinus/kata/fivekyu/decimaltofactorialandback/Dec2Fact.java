package ch.cinus.kata.fivekyu.decimaltofactorialandback;

public class Dec2Fact {
  public static String dec2FactString(long nb) {
    return dec2FactString(nb, 1);
  }

  public static long factString2Dec(String str) {
    final long[] factorial = {1};
    final int[] iteration = {1};
    StringBuilder stringBuilder = new StringBuilder(str);
    return stringBuilder
        .reverse()
        .chars()
        .mapToObj(c -> (char) c)
        .map(Character::getNumericValue)
        .mapToLong(Integer::intValue)
        .map(
            i -> {
              long h = i * factorial[0];
              factorial[0] *= (iteration[0]++);
              return h;
            })
        .reduce(0L, Long::sum);
  }

  private static String dec2FactString(long nb, int depth) {
    if (nb == 0) return "";
    long quotient = nb / depth;
    long remainder = nb % depth;
    return dec2FactString(quotient, depth + 1) + convert(remainder);
  }

  private static String convert(long aLong) {
    if (aLong < 10) return String.valueOf(aLong);
    char c = (char) (65 + (aLong - 10));
    return String.valueOf(c);
  }
}
