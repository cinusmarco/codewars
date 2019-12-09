package ch.cinus.kata.six_kyu.worda10n;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AbbreviatorTest {

  @ParameterizedTest(name = "{index} [{0}] should shrink to [{1}]")
  @CsvFileSource(resources = "/abbreviator.csv")
  public void test(String input, String expected) {
    Abbreviator abbr = new Abbreviator();
    assertThat(abbr.abbreviate(input)).isEqualTo(expected);
  }
}
