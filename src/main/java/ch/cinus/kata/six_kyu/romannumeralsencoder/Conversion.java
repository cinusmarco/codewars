package ch.cinus.kata.six_kyu.romannumeralsencoder;

public class Conversion {
  public String solution(int n) {
    if (n >= 1000) return "M" + solution(n - 1000);
    if (n >= 900) return "CM" + solution(n - 900);
    if (n >= 500) return "D" + solution(n - 500);
    if (n >= 400) return "CD" + solution(n - 400);
    if (n >= 100) return "C" + solution(n - 100);
    if (n >= 90) return "XC" + solution(n - 90);
    if (n >= 50) return "L" + solution(n - 50);
    if (n >= 40) return "XL" + solution(n - 40);
    if (n >= 10) return "X" + solution(n - 10);
    if (n == 9) return "IX" + solution(n - 9);
    if (n >= 5) return "V" + solution(n - 5);
    if (n == 4) return "IV";
    if (n >= 1) return "I" + solution(n - 1);
    return "";
  }
}
