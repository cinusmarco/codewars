package ch.cinus.kata.sevenkyu.mumbling;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AccumulTest {

  @ParameterizedTest(name = "{index} {0}")
  @MethodSource({"stringProvider"})
  void test(String input, String expected) {
    assertThat(Accumul.accum(input), equalTo(expected));
  }

  private static Stream<Arguments> stringProvider() {
    return Stream.of(
        Arguments.of(
            "ZpglnRxqenU",
            "Z-Pp-Ggg-Llll-Nnnnn-Rrrrrr-Xxxxxxx-Qqqqqqqq-Eeeeeeeee-Nnnnnnnnnn-Uuuuuuuuuuu"),
        Arguments.of(
            "NyffsGeyylB",
            "N-Yy-Fff-Ffff-Sssss-Gggggg-Eeeeeee-Yyyyyyyy-Yyyyyyyyy-Llllllllll-Bbbbbbbbbbb"),
        Arguments.of(
            "MjtkuBovqrU",
            "M-Jj-Ttt-Kkkk-Uuuuu-Bbbbbb-Ooooooo-Vvvvvvvv-Qqqqqqqqq-Rrrrrrrrrr-Uuuuuuuuuuu"),
        Arguments.of(
            "EvidjUnokmM",
            "E-Vv-Iii-Dddd-Jjjjj-Uuuuuu-Nnnnnnn-Oooooooo-Kkkkkkkkk-Mmmmmmmmmm-Mmmmmmmmmmm"),
        Arguments.of(
            "HbideVbxncC",
            "H-Bb-Iii-Dddd-Eeeee-Vvvvvv-Bbbbbbb-Xxxxxxxx-Nnnnnnnnn-Cccccccccc-Ccccccccccc"));
  }
}
