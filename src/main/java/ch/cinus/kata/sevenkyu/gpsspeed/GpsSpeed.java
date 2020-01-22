package ch.cinus.kata.sevenkyu.gpsspeed;

import java.util.stream.IntStream;

public class GpsSpeed {
  public static int gps(int s, double[] x) {
    // your code
    return (int)
        Math.floor(
            IntStream.range(1, x.length)
                .mapToDouble(i -> (x[i] - x[i - 1]) * 3600 / s)
                .max()
                .orElseGet(() -> 0.0d));
  }
}
