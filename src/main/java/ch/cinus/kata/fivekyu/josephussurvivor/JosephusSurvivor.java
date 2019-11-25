package ch.cinus.kata.fivekyu.josephussurvivor;

public class JosephusSurvivor {
  public static int josephusSurvivor(final int n, final int k) {
    return n == 1 ? 1 : (k - 1 + josephusSurvivor(n - 1, k)) % n + 1;
  }

  /*
  Example with N=5 and k = 2
    [1 2 3 4 5]  <-- Initial array
    remove k-th element and shift so that k+1 as at first index
    [3 4 5 1]
    remove k-th element and shift so that k+1 as at first index
    [5 1 3]
    remove k-th element and shift so that k+1 as at first index
    [3 5]
    remove k-th element and shift so that k+1 as at first index
    [3]
     # What position is our final survivor in?
     1
     1
     3
     1
     3
     # What are the results of our equation above?
     # (k-1 + f(n - 1, k)) % n + 1
     1
     1 = (2 - 1 + 1) % 2 + 1
     3 = (2 - 1 + 1) % 3 + 1
     1 = (2 - 1 + 3) % 4 + 1
     3 = (2 - 1 + 1) % 5 + 1
  */
}
