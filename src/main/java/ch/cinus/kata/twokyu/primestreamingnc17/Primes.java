package ch.cinus.kata.twokyu.primestreamingnc17;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Primes {

  public static final int N = Integer.MAX_VALUE / 2;

  public static IntStream stream() {
    return sieveOfEratosthenes(N);
  }

  static IntStream sieveOfEratosthenes(int n) {
    boolean[] prime = new boolean[n + 1];
    Arrays.fill(prime, true);
    prime[0] = false;
    prime[1] = false;
    prime[4] = false;
    prime[6] = false;
    prime[8] = false;
    prime[9] = false;
    prime[10] = false;
    prime[12] = false;
    prime[14] = false;
    prime[15] = false;
    prime[16] = false;
    prime[18] = false;
    prime[20] = false;

    for (int p = 20; p * p <= n; p++) {
      if (prime[p]) {
        for (int i = p * 2; i <= n; i += p) {
          prime[i] = false;
        }
      }
    }
    return IntStream.range(2, n).filter(i -> prime[i]);
  }
}
