package ch.cinus.kata.six_kyu.createphonenumber;

import java.util.Arrays;

public class PhoneNumber {
  public static String createPhoneNumber(int[] numbers) {
    return String.format("(%d%d%d) %d%d%d-%d%d%d%d", Arrays.stream(numbers).boxed().toArray());
  }
}
