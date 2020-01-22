package ch.cinus.kata.sixkyu.bouncingball;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BouncingBallTest {

  @Test
  void test1() {
    assertEquals(3, BouncingBall.bouncingBall(3.0, 0.66, 1.5));
  }

  @Test
  void test2() {
    assertEquals(15, BouncingBall.bouncingBall(30.0, 0.66, 1.5));
  }

  @Test
  void testInvalidParameters() {
    assertEquals(-1, BouncingBall.bouncingBall(1, 0.66, 1.5));
  }

  @Test
  void testInvalidParameters2() {
    assertEquals(-1, BouncingBall.bouncingBall(-1, 0.66, 1.5));
  }

  @Test
  void testInvalidParameters3() {
    assertEquals(-1, BouncingBall.bouncingBall(10, 1, 1.5));
  }
}
