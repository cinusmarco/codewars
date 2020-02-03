package ch.cinus.kata.fourkyu.permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class Permutations {

  private static HashSet<String> permutations = new HashSet<>();

  public static List<String> singlePermutations(String s) {
    return permutation(s, "").stream()
        .filter(s1 -> s1.length() == s.length())
        .distinct()
        .collect(Collectors.toList());
  }

  private static List<String> permutation(String leftovers, String answer) {
    if (leftovers.length() == 0) {
      permutations.add(answer);
      return List.of(answer);
    }
    ArrayList<String> subs = new ArrayList<>();
    for (int i = 0; i < leftovers.length(); i++) {
      char ch = leftovers.charAt(i);
      String newLeftovers = leftovers.substring(0, i) + leftovers.substring(i + 1);

      subs.addAll(permutation(newLeftovers, answer + ch));
    }
    return subs;
  }
}
