package ch.cinus.kata.fivekyu.int32toipv4;

public class Kata {

  public static String longToIP(long input) {
    if (input == 0) {
      return "0.0.0.0";
    }
    final int leadingZeros = Long.numberOfLeadingZeros(input) - 32;
    final String bits = "0".repeat(leadingZeros) + Long.toBinaryString(input);

    StringBuilder answer = new StringBuilder();
    for (int i = 0; i < bits.length(); i += 8) {
      String octet = bits.substring(i, i + 8);
      Long decimalValue = Long.valueOf(octet, 2);
      answer.append(decimalValue);
      answer.append(".");
    }
    return answer.substring(0, answer.length() - 1);
  }
}
