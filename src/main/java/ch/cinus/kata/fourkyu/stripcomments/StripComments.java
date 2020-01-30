package ch.cinus.kata.fourkyu.stripcomments;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StripComments {
  public static String stripComments(String text, String[] commentSymbols) {
    String commentSymbolsPattern = String.join("", commentSymbols);
    return Arrays.stream(text.split("\\n"))
        .map(s -> discardAfterComment(s, commentSymbolsPattern))
        .collect(Collectors.joining("\n"));
  }

  private static String discardAfterComment(String s, String commentSymbolsPattern) {
    StringBuilder sb = new StringBuilder(s.length());
    Pattern pattern = Pattern.compile(String.format("(.*?)[%s](.*)", commentSymbolsPattern));
    Matcher matcher = pattern.matcher(s);
    if (matcher.find()) {
      String beforeComment = matcher.group(1);
      matcher.appendReplacement(sb, beforeComment);
    } else {
      sb.append(s);
    }
    return sb.toString().stripTrailing();
  }
}
