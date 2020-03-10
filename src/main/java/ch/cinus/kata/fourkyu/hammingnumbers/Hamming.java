package ch.cinus.kata.fourkyu.hammingnumbers;

import java.util.PriorityQueue;

public class Hamming {

  private static final double logOf2 = Math.log(2);
  private static final double logOf3 = Math.log(3);
  private static final double logOf5 = Math.log(5);

  /*
  Implementing a uniform cost search. Cost = value of the number
  Children -> all the elements with a single digit difference from parent (don't expand if already seen)
   */
  public static long hamming(int n) {
    // The elements of the search frontier
    PriorityQueue<HammingNumber> frontier =
        new PriorityQueue<>(
            5000,
            (thisNumber, otherNumber) -> {
              // take the logarithms for comparison
              double logThis =
                  thisNumber.i * logOf2 + thisNumber.j * logOf3 + thisNumber.k * logOf5;
              double logOther =
                  otherNumber.i * logOf2 + otherNumber.j * logOf3 + otherNumber.k * logOf5;
              return Double.compare(logThis, logOther);
            });

    // Initialize the frontier
    frontier.add(new HammingNumber(0, 0, 0)); // 1

    for (int it = 1; it < n; it++) {
      // Get the current lowest Hamming number from the frontier
      HammingNumber current = frontier.remove();

      // at the origin of the (i,j) plane -> move on the k axis
      if (current.i == 0 && current.j == 0) {
        frontier.add(new HammingNumber(current.i, current.j, current.k + 1));
      }
      // at the origin of line (i) -> move on the j axis
      if (current.i == 0) {
        frontier.add(new HammingNumber(current.i, current.j + 1, current.k));
      }
      // always move one further on the (i)-line
      frontier.add(new HammingNumber(current.i + 1, current.j, current.k));
    }

    return frontier.element().getValue();
  }

  static class HammingNumber {
    private int i, j, k;

    public HammingNumber(int i, int j, int k) {
      this.i = i;
      this.j = j;
      this.k = k;
    }

    long getValue() {
      return (long) (Math.pow(2L, i) * Math.pow(3L, j) * Math.pow(5L, k));
    }
  }
}
