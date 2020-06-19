package ch.cinus.kata.sixkyu.numberzoopatrol;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class NumberZooPatrolTest {

  @Rule public Timeout globalTimeout = new Timeout(1, TimeUnit.SECONDS);

  @Test
  public void testDescriptionExamples1() {
    assertEquals(2, findMissingNumber(1, 3));
  }

  @Test
  public void testDescriptionExamples2() {
    assertEquals(1, findMissingNumber(2, 3, 4));
  }

  @Test
  public void testDescriptionExamples3() {
    assertEquals(12, findMissingNumber(13, 11, 10, 3, 2, 1, 4, 5, 6, 9, 7, 8));
  }

  @Test
  public void testDescriptionExamples4() {
    assertEquals(3, findMissingNumber(1, 2));
  }

  @Test
  public void testPerformanceIsLinear() {
    // Solutions with linear runtime should finish in about 200 to 300 ms.
    // 1. Generate an array with 999,999 numbers with increasing and
    //    decreasing values interleaved so sorting algorithms which
    //    are able detect pre-sorted arrays would still need
    //    at least loglinear time.
    // 2. Find the missing number 100 times.
    int[] numbers = generateNumbers(1_000_000, 66_667);
    for (int i = 0; i < 249_999; i++) {
      int temp = numbers[i * 2];
      numbers[i * 2] = numbers[999_997 - i * 2];
      numbers[999_997 - i * 2] = temp;
    }
    for (int i = 0; i < 100; i++) findMissingNumber(numbers.clone());
  }

  private static int findMissingNumber(int... numbers) {
    return NumberZooPatrol.findMissingNumber(numbers);
  }

  private static int[] generateNumbers(int bound, int missingNumber) {
    if (missingNumber < 1 || missingNumber > bound)
      throw new IllegalArgumentException("Missing number is not in allowed range");
    int[] numbers = new int[bound - 1];
    int pos = 0;
    for (int i = 1; i <= bound; i++) if (i != missingNumber) numbers[pos++] = i;
    return numbers;
  }
}
