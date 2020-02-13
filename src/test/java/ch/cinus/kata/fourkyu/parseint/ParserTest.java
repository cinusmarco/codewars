package ch.cinus.kata.fourkyu.parseint;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParserTest {

  @ParameterizedTest(name = "{0} is {1}")
  @MethodSource(value = "provider")
  void parsingTest(String input, int expected){
    assertThat(Parser.parseInt(input)).isEqualTo(expected);

  }

  private static Stream<Arguments> provider(){
    return Stream.of(
      Arguments.of("one", 1),
      Arguments.of("twenty", 20),
      Arguments.of("twenty-one", 21),
      Arguments.of("two hundred forty-six", 246),
      Arguments.of("two hundred and forty-six", 246),
      Arguments.of("zero", 0),
      Arguments.of("one million", 1000000),
      Arguments.of("a million", 1000000),
      Arguments.of("one thousand", 1000),
      Arguments.of("one thousand and one", 1001),
      Arguments.of("a thousand", 1000),
      Arguments.of("six thousand two hundred and thirty-six", 6236),
      Arguments.of("one thousand three hundred thirty-seven", 1337),
      Arguments.of("seven hundred eighty-three thousand nine hundred and nineteen", 783919)
    );
  }
}
