package ch.cinus.kata.sixkyu.numberzoopatrol;

import java.util.Arrays;
import java.util.LongSummaryStatistics;

public class NumberZooPatrol {
  public static int findMissingNumber(int[] numbers) {
    final LongSummaryStatistics longSummaryStatistics =
        Arrays.stream(numbers).mapToLong(value -> (long) value).summaryStatistics();

    final long sum = longSummaryStatistics.getSum();
    final long max = longSummaryStatistics.getMax();

    final long shouldBeTotal = (long) (max / 2.0 * (1L + max));
    int missingNumber = (int) (shouldBeTotal - sum);
    return missingNumber == 0 ? (int) max + 1 : missingNumber == -1 ? 1 : missingNumber;
  }
}
