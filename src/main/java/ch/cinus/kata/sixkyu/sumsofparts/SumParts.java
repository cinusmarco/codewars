package ch.cinus.kata.sixkyu.sumsofparts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SumParts {

  public static int[] sumParts(int[] ls) {
    int total = Arrays.stream(ls).sum();
    ArrayList<Integer> result = new ArrayList<>();
    int[] tmp = new int[1];
    result.add(total);
    result.addAll(
            Arrays.stream(ls)
                    .map(
                            actualValue -> {
                              tmp[0] += actualValue;
                              return total - tmp[0];
                            })
                    .boxed()
                    .collect(Collectors.toList()));
    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}
