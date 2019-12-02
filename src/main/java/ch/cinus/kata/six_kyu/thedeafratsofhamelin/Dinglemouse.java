package ch.cinus.kata.six_kyu.thedeafratsofhamelin;

public class Dinglemouse {
  public static int countDeafRats(final String town) {
    if (town.startsWith("P")) {
      // Hamelin is far left
      return countDeafRatsOfHamelin(town.replaceAll("P", ""))[1];
    } else if (town.endsWith("P")) {
      // Hamelin is far right
      return countDeafRatsOfHamelin(town.replaceAll("P", ""))[0];
    } else {
      // Hamelin is in the middle
      String[] split = town.split("P");
      return countDeafRatsOfHamelin(split[0])[0] + countDeafRatsOfHamelin(split[1])[1];
    }
  }

  private static int[] countDeafRatsOfHamelin(String str) {
    String normalized = str.replaceAll(" ", "");
    char[] chars = normalized.toCharArray();
    int goingLeft = 0;
    int goingRight = 0;

    for (int i = 0; i < chars.length; i += 2) {
      if (chars[i] == 'O' && chars[i + 1] == '~') {
        goingLeft++;
      } else if (chars[i] == '~' && chars[i + 1] == 'O') {
        goingRight++;
      } else {
        throw new RuntimeException("Something went wrong");
      }
    }
    return new int[] {goingLeft, goingRight};
  }
}
