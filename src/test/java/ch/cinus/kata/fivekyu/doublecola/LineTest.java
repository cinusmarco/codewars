package ch.cinus.kata.fivekyu.doublecola;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LineTest {

  @ParameterizedTest(name = "{0}-th in {1} is {2}")
  @MethodSource(value = "provider")
  void test(int n, String[] names, String expected) {
    Line systemUnderTest = new Line();
    assertThat(systemUnderTest.WhoIsNext(names, n)).isEqualTo(expected);
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(
            1, new String[] {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"}, "Sheldon"),
        Arguments.of(
            8, new String[] {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"}, "Leonard"),
        Arguments.of(
            6, new String[] {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"}, "Sheldon"),
        Arguments.of(52, new String[] {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"}, "Penny"),
        Arguments.of(
            6, new String[] {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard", "Amy"}, "Amy"),
            Arguments.of(
                    10, new String[] {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard", "Amy"}, "Leonard"));
  }
}
