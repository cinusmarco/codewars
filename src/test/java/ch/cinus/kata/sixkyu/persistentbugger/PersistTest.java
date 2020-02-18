package ch.cinus.kata.sixkyu.persistentbugger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PersistTest {

  @ParameterizedTest
  @CsvSource({"39,3", "4,0", "25,2", "999,4"})
  void basicTest(long input, int expected) {
    assertThat(Persist.persistence(input)).isEqualTo(expected);
  }
}
