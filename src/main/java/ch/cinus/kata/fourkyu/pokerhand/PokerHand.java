package ch.cinus.kata.fourkyu.pokerhand;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokerHand {

  public static final long TWO_OF_A_KIND_VALUE = 100L;
  public static final long TWO_PAIRS_VALUE = TWO_OF_A_KIND_VALUE * 10;
  public static final long THREE_OF_A_KIND_VALUE = TWO_PAIRS_VALUE * 10;
  public static final long STRAIGHT_VALUE = THREE_OF_A_KIND_VALUE * 10;
  public static final long FLUSH_VALUE = STRAIGHT_VALUE * 10;
  public static final long FULL_HOUSE_VALUE = FLUSH_VALUE * 10;
  public static final long FOUR_OF_A_KIND_VALUE = FULL_HOUSE_VALUE * 10;
  public static final long STRAIGHT_FLUSH_VALUE = FOUR_OF_A_KIND_VALUE * 10;

  public enum Result {
    TIE,
    WIN,
    LOSS
  }

  private final List<Card> cards;
  private long rank;
  private List<Integer> kickers;

  PokerHand(String hand) {
    final var rawCards = hand.split(" ");
    cards = Arrays.stream(rawCards).map(Card::new).collect(Collectors.toList());
    Collections.sort(cards);
    computeRankAndKickers();
  }

  private void computeRankAndKickers() {
    rank = computeRank();
    kickers = computeKickers();
    Collections.sort(kickers);
  }

  private long computeRank() {
    var localRank = 0L;
    if (isStraight() && isFlush()) {
      localRank += STRAIGHT_FLUSH_VALUE;
    } else if (isFourOfAKind()) {
      localRank += FOUR_OF_A_KIND_VALUE;
    } else if (isFullHouse()) {
      localRank += FULL_HOUSE_VALUE;
    } else if (isFlush()) {
      localRank += FLUSH_VALUE;
    } else if (isStraight()) {
      localRank += STRAIGHT_VALUE;
    } else if (isThreeOfAKind()) {
      localRank += THREE_OF_A_KIND_VALUE;
    } else if (isTwoPairs()) {
      localRank += TWO_PAIRS_VALUE;
    } else if (isTwoOfAKind()) {
      localRank += TWO_OF_A_KIND_VALUE;
    }
    localRank += highestCard();

    return localRank;
  }

  private List<Integer> computeKickers() {
    final var map = cards.stream().map(Card::getValue).collect(Collectors.groupingBy(Integer::intValue));
    return map.entrySet().stream()
        .filter(entry -> entry.getValue().size() == 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  public Result compareWith(PokerHand other) {
    var result = Result.TIE;
    if (this.rank > other.rank) {
      result = Result.WIN;
    } else if (this.rank < other.rank) {
      result = Result.LOSS;
    } else {
      // we have a tie, lets check the kickers
      result = compareKickers(other);
    }

    // special cases (imperfect situations in Kata)
    result = tieBreakerWhenFullHouse(other, result);
    return result;
  }

  private Result tieBreakerWhenFullHouse(PokerHand other, Result result) {
    if (result == Result.TIE && isFullHouse()) {
      // compare the
      final Integer mySupport = nofAKind(2).get(0);
      final Integer otherSupport = other.nofAKind(2).get(0);
      if (mySupport > otherSupport) {
        result = Result.WIN;
      } else if (mySupport < otherSupport) {
        result = Result.LOSS;
      }
    }
    return result;
  }

  private Result compareKickers(PokerHand other) {
    Result result = Result.TIE;
    for (int i = 0; i < this.kickers.size(); i++) {
      if (this.kickers.get(i) > other.kickers.get(i)) {
        result = Result.WIN;
      } else if (this.kickers.get(i) < other.kickers.get(i)) {
        result = Result.LOSS;
      }
    }
    return result;
  }

  private boolean isFlush() {
    return cards.stream().collect(Collectors.groupingBy(Card::getSuit)).size() == 1;
  }

  private boolean isStraight() {
    var actual = cards.stream().map(Card::getValue).collect(Collectors.toList());
    var computed =
        IntStream.range(cards.get(0).getValue(), cards.get(0).getValue() + 5).boxed().collect(Collectors.toList());
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

  private boolean isTwoPairs() {
    final var map = cards.stream().map(Card::getValue).collect(Collectors.groupingBy(Integer::intValue));
    return map.size() == 3;
  }

  private boolean isNofAKind(int i) {
    final var map = cards.stream().map(Card::getValue).collect(Collectors.groupingBy(Integer::intValue));
    return map.values().stream().anyMatch(ints -> ints.size() == i);
  }

  private List<Integer> nofAKind(int i) {
    final var map = cards.stream().map(Card::getValue).collect(Collectors.groupingBy(Integer::intValue));
    return map.values().stream().filter(ints -> ints.size() == i).findFirst().orElse(Collections.emptyList());
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
