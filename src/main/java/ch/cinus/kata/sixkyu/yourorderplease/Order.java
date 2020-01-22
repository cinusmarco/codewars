package ch.cinus.kata.sixkyu.yourorderplease;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Order {
  public static String order2(String words) {
    if (words.isEmpty()) {
      return "";
    }
    String[] splits = words.split(" ");

    Arrays.sort(
        splits,
        (s, t1) -> {
          Pattern p = Pattern.compile("\\d+");
          Matcher mS = p.matcher(s);
          mS.find();
          Integer sNumber = Integer.valueOf(mS.group());
          Matcher mT = p.matcher(t1);
          mT.find();
          Integer tNumber = Integer.valueOf(mT.group());
          return sNumber.compareTo(tNumber);
        });
    return Arrays.stream(splits).collect(Collectors.joining(" "));
  }

  public static String order(String words) {
    return Arrays.stream(words.split(" "))
        .sorted(Comparator.comparing(s -> Integer.valueOf(s.replaceAll("\\D", ""))))
        .collect(Collectors.joining(" "));
  }
}
