/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.sevenkyu.halvingsum;

public class HalvingSum {
  int halvingSum(int n) {
    return doHalvingSum(n, 1);
  }

  int doHalvingSum(int number, int divisor) {
    final int addend = number / divisor;
    if (addend == 0) {
      return 0;
    }
    return addend + doHalvingSum(number, divisor * 2);
  }
}
