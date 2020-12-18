package ch.cinus.kata.sixkyu.takeatenminutewalk;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TenMinWalk {

  private TenMinWalk() {}

  public static boolean isValid(char[] walk) {
    boolean isValid;
    // check length
    isValid = walk.length == 10;

    final Map<Character, List<Character>> walkedDirections =
        new String(walk).chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(character -> character));
    // check number blocks walked north is same as blocks walked south
    isValid &=
        walkedDirections.getOrDefault('n', Collections.emptyList()).size()
            == walkedDirections.getOrDefault('s', Collections.emptyList()).size();

    // check number blocks walked east is same as blocks walked west
    isValid &=
        walkedDirections.getOrDefault('e', Collections.emptyList()).size()
            == walkedDirections.getOrDefault('w', Collections.emptyList()).size();

    return isValid;
  }
}
