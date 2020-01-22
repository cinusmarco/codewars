package ch.cinus.kata.sixkyu.tribonaccisequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Xbonacci {

  public double[] tribonacciFirst(double[] s, int n) {
    if (n == 0) {
      return new double[0];
    }
    List<Double> doubles = Arrays.stream(s).boxed().collect(Collectors.toList());
    for (int i = 2; i < n; i++) {
      doubles.add(doubles.get(i) + doubles.get(i - 1) + doubles.get(i - 2));
    }
    double[] answer = new double[n];
    for (int i = 0; i < n; i++) {
      answer[i] = doubles.get(i);
    }
    return answer;
  }

  public double[] tribonacci(double[] s, int n) {
    if (n == 0) {
      return new double[0];
    }
    List<Double> doubles = computeNext(s[0], s[1], s[2], n);
    return doubles.stream().mapToDouble(Double::doubleValue).toArray();
  }

  private List<Double> computeNext(double first, double second, double third, int lefts) {
    ArrayList<Double> doubles = new ArrayList<>();
    doubles.add(first);
    if (lefts > 1) {
      doubles.addAll(computeNext(second, third, first + second + third, lefts - 1));
    }
    return doubles;
  }
}
