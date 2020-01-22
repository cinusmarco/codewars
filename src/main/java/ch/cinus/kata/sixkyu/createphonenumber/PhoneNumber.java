package ch.cinus.kata.sixkyu.createphonenumber;

import java.util.Arrays;

public class PhoneNumber {
  public static String createPhoneNumber(int[] numbers) {
    return String.format("(%d%d%d) %d%d%d-%d%d%d%d", Arrays.stream(numbers).boxed().toArray());
  }
}
