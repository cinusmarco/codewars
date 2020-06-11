package ch.cinus.kata.twokyu.primestreamingnc17;

import java.util.BitSet;
import java.util.stream.IntStream;

public class Primes {
  private static final SieveOfEratosthenes SIEVE_OF_ERATOSTHENES = new SieveOfEratosthenes();

  public static IntStream stream() {
    return IntStream.iterate(2, index -> SIEVE_OF_ERATOSTHENES.nextSetBit(index + 1));
  }
}

class SieveOfEratosthenes {
  private static final int N = 810000000;
  private static final int FLOORED_SQRT_N = 28460;

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

  public int nextSetBit(int fromIndex) {
    return bitset.nextSetBit(fromIndex);
  }
}
