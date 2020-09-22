package ch.cinus.kata.fourkyu.pokerhand;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokerHand {

  public enum Result {
    TIE,
    WIN,
    LOSS
  }

  private final List<Card> cards;
  private final int rank;

  PokerHand(String hand) {
    final var rawCards = hand.split(" ");
    cards = Arrays.stream(rawCards).map(Card::new).collect(Collectors.toList());
    Collections.sort(cards);
    rank = computeRank();
  }

  private int computeRank() {
    var rank = 0;
    rank += isStraight() && isFlush() ? 1000000000 : 0;
    rank += isFourOfAKind() ? 100000000 : 0;
    rank += isFullHouse() ? 10000000 : 0;
    rank += isFlush() ? 1000000 : 0;
    rank += isStraight() ? 100000 : 0;
    rank += isThreeOfAKind() ? 10000 : 0;
//    rank += isTwoPairs() ? 1000 : 0;
    rank += isTwoOfAKind() ? 1000 : 0;

    rank += highestCard();
    System.err.println(cards + " --> " + rank);
    return rank;
  }

  public Result compareWith(PokerHand other) {
    var result = Result.TIE;
    if (this.rank > other.rank) {
      result = Result.WIN;
    } else if (this.rank < other.rank) {
      result = Result.LOSS;
    }
    return result;
  }

  private boolean isFlush() {
    return cards.stream().collect(Collectors.groupingBy(Card::getSuit)).size() == 1;
  }

  private boolean isStraight() {
    var actual = cards.stream().map(Card::getValue).collect(Collectors.toList());
    var computed =
            IntStream.range(cards.get(0).getValue(), cards.get(0).getValue() + 5)
                    .boxed()
                    .collect(Collectors.toList());
    return actual.equals(computed);
  }

  private boolean isFullHouse() {
    return isThreeOfAKind() && isTwoOfAKind();
  }

  private int highestCard() {
    int highestCard = cards.get(4).getValue();

    if (isFourOfAKind()) {
      highestCard = nofAKind(4).get(0);
    } else if (isFullHouse()) {
      highestCard = nofAKind(3).get(0);
    } else if (isThreeOfAKind()) {
      highestCard = nofAKind(3).get(0);
    } else if (isTwoOfAKind()) {
      highestCard = nofAKind(2).get(0);
    }

    return highestCard;
  }

  private boolean isFourOfAKind() {
    return isNofAKind(4);
  }

  private boolean isThreeOfAKind() {
    return isNofAKind(3);
  }

  private boolean isTwoOfAKind() {
    return isNofAKind(2);
  }

  private boolean isNofAKind(int i) {
    final var map =
            cards.stream().map(Card::getValue).collect(Collectors.groupingBy(Integer::intValue));
    return map.values().stream().anyMatch(ints -> ints.size() == i);
  }

  private List<Integer> nofAKind(int i) {
    final var map =
            cards.stream().map(Card::getValue).collect(Collectors.groupingBy(Integer::intValue));
    return map.values().stream()
            .max(Comparator.comparingInt(List::size))
            .orElse(Collections.emptyList());
  }

  private static class Card implements Comparable<Card> {

    private final String suit;
    private final int value;

    public Card(String card) {
      value = convert(card.substring(0, 1));
      suit = card.substring(1);
    }

    private int convert(String substring) {
      var value = 0;
      switch (substring) {
        case "A":
          value = 14;
          break;
        case "K":
          value = 13;
          break;
        case "Q":
          value = 12;
          break;
        case "J":
          value = 11;
          break;
        case "T":
          value = 10;
          break;
        default:
          value = Integer.parseInt(substring);
      }
      return value;
    }

    @Override
    public int compareTo(Card other) {
      return Integer.compare(this.value, other.value);
    }

    public String getSuit() {
      return suit;
    }

    public int getValue() {
      return value;
    }

    @Override
    public String toString() {
      return value + "" + suit;
    }
  }
}
