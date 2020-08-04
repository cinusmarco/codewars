package ch.cinus.kata.sixkyu.crackthepin;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CodeWarsTest {

  private final CodeWars objTF = new CodeWars();

  private static Stream<Arguments> generateTestData() {
    return Stream.of(
            Arguments.of("Should work with simple PIN", "12345", "827ccb0eea8a706c4c34a16891f84e7b"),
            Arguments.of("Should work with harder PIN", "00078", "86aa400b65433b608a9db30070ec60cd"));
  }

  @ParameterizedTest
  @MethodSource(value = "generateTestData")
  void test(String description, String expected, String input) throws NoSuchAlgorithmException {
    assertThat(objTF.crack(input)).as(description).isEqualTo(expected);
  }
}
