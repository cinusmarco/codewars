package ch.cinus.kata.fivekyu.doublecola;

public class Line {

  public String WhoIsNext(String[] names, int n) {
    if (n <= names.length) return names[n - 1];
    else return WhoIsNext(names, (n - (names.length - 1)) / 2);
  }

  /*
   a - b - c - d - e
   aa - bb - cc - dd - ee
   aaaa - bbbb - cccc - dddd - eeee
   aaaaaaaa - bbbbbbbb - cccccccc - dddddddd - eeeeeeee
  */
}
