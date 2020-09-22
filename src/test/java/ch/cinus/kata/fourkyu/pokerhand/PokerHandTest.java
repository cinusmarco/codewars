package ch.cinus.kata.fourkyu.pokerhand;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PokerHandTest {

  private static final PokerHand.Result loss = PokerHand.Result.LOSS;
  private static final PokerHand.Result win = PokerHand.Result.WIN;
  private static final PokerHand.Result tie = PokerHand.Result.TIE;

  static Stream<Arguments> cases() {
    return Stream.of(
            Arguments.of("Highest straight flush wins", loss, "2H 3H 4H 5H 6H", "KS AS TS QS JS"),
            Arguments.of("Straight flush wins of 4 of a kind", win, "2H 3H 4H 5H 6H", "AS AD AC AH JD"),
            Arguments.of("Highest 4 of a kind wins", win, "AS AH 2H AD AC", "JS JD JC JH 3D"),
            Arguments.of("Highest 4 of a kind wins", win, "4S 4H KH 4D 4C", "3S 3D 3C 3H AD"),
            Arguments.of("4 Of a kind wins of full house", loss, "2S AH 2H AS AC", "JS JD JC JH AD"),
            Arguments.of("Full house wins of flush", win, "2S AH 2H AS AC", "2H 3H 5H 6H 7H"),
            Arguments.of("Highest flush wins", win, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H"),
            Arguments.of("Flush wins of straight", win, "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C"),
            Arguments.of("Equal straight is tie", tie, "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S"),
            Arguments.of("Straight wins of three of a kind", win, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS"),
            Arguments.of("3 Of a kind wins of two pair", loss, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS"),
            Arguments.of("2 Pair wins of pair", win, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S"),
            Arguments.of("Highest pair wins", loss, "6S AD 7H 4S AS", "AH AC 5H 6H 7S"),
            Arguments.of("Pair wins of nothing", loss, "2S AH 4H 5S KC", "AH AC 5H 6H 7S"),
            Arguments.of("Highest card loses", loss, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"),
            Arguments.of("Highest card wins", win, "4S 5H 6H TS AC", "3S 5S 6S TC AS"),
            Arguments.of("Equal cards is tie", tie, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C"));
  }

  @ParameterizedTest(name = "{index} - {0}")
  @MethodSource(value = "cases")
  void test(String description, PokerHand.Result expected, String playerHand, String opponentHand) {
    PokerHand player = new PokerHand(playerHand);
    PokerHand opponent = new PokerHand(opponentHand);
    assertThat(player.compareWith(opponent)).as(description).isEqualTo(expected);
  }
}
