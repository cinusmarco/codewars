package ch.cinus.kata.sixkyu.simpleprimestreaming;

import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

public class SimplePrimeStreaming {
  private static final SieveOfEratosthenes SIEVE_OF_ERATOSTHENES = new SieveOfEratosthenes();

  public static String solve(int a, int b) {
    return SIEVE_OF_ERATOSTHENES.first(a + b).stream()
        .map(String::valueOf)
        .collect(Collectors.joining())
        .substring(a, a + b);
  }

  static class SieveOfEratosthenes {
    private static final int N = 100000;
    private static final int FLOORED_SQRT_N = 1000;

    private BitSet bitset;

    SieveOfEratosthenes() {
      init(); // assume everyone is prime
      applySieve(); // filter out non primes
    }

    private void init() {
      bitset = new BitSet(N);
      bitset.set(2, N - 1);
    }

    private void applySieve() {
      int i = 1;
      while (i < FLOORED_SQRT_N) {
        i = bitset.nextSetBit(i + 1);
        for (int j = i * i; j < N; j += i) {
          bitset.clear(j);
        }
      }
    }

    public List<Integer> first(int n) {
      return bitset.stream().filter(value -> value != 0).limit(n).boxed().collect(Collectors.toList());
    }
  }
}
