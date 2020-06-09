package ch.cinus.kata.threekyu.primestreamingpg13;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Primes {

  public static final int N = 50000000;

  public static IntStream stream() {
    return sieveOfEratosthenes(N).stream().mapToInt(Integer::intValue);
  }

  static List<Integer> sieveOfEratosthenes(int n) {
    boolean[] prime = new boolean[n + 1];
    Arrays.fill(prime, true);
    for (int p = 2; p * p <= n; p++) {
      if (prime[p]) {
        for (int i = p * 2; i <= n; i += p) {
          prime[i] = false;
        }
      }
    }
    List<Integer> primeNumbers = new LinkedList<>();
    for (int i = 2; i <= n; i++) {
      if (prime[i]) {
        primeNumbers.add(i);
      }
    }
    return primeNumbers;
  }
}
