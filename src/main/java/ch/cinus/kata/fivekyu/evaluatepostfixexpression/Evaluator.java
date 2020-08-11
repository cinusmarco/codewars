package ch.cinus.kata.fivekyu.evaluatepostfixexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator {

  public long evaluate(String input) {
    if (input.matches(".*-?[0-9]+ -?[0-9]+ [+\\-*/].*")) {
      return evaluate(reduce(input));
    }
    return Long.parseLong(input);
  }

  String reduce(String s) {
    Pattern pattern = Pattern.compile("(-?[0-9]+ -?[0-9]+ [+\\-*/])");
    Matcher m = pattern.matcher(s);
    StringBuffer sb = new StringBuffer(s.length());
    if (m.find()) {
      String text = String.valueOf(compute(m.group(1)));
      m.appendReplacement(sb, text);
    }
    m.appendTail(sb);
    return sb.toString();
  }

  long compute(String s) {
    final var values = s.split(" ");
    final var a = Integer.parseInt(values[0]);
    final var b = Integer.parseInt(values[1]);

    switch (values[2]) {
      case "+":
        return a + b;
      case "-":
        return a - b;
      case "*":
        return a * b;
      case "/":
        return a / b;
      default:
        throw new UnsupportedOperationException(String.format("Unknown operator: %s", values[2]));
    }
  }
}
