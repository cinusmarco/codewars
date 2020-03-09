package ch.cinus.kata.sixkyu.xbonacci;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Xbonacci {
  public double[] xbonacci(double[] signature, int n) {
    System.out.println(String.format("%s | %d", Arrays.toString(signature), n));
    if (n < signature.length) {
      return Arrays.copyOf(signature, n);
    }
    List<Double> sequence = Arrays.stream(signature).boxed().collect(Collectors.toList());
    for (int i = 0; i < n - signature.length; i++) {
      double next = next(sequence, signature.length);
      sequence.add(next);
    }
    return sequence.stream().mapToDouble(d -> d).toArray();
  }

  private double next(List<Double> currentSequence, int n) {
    double tmp = 0.0d;
    for (int i = 1; i < n + 1; i++) {
      int index = currentSequence.size() - i;
      tmp += currentSequence.get(index);
    }
    return tmp;
  }
}
