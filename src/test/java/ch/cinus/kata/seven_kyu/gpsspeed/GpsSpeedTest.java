package ch.cinus.kata.seven_kyu.gpsspeed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GpsSpeedTest {
  @ParameterizedTest
  @MethodSource("provider")
  void test(double[] x, int s, long expected) {
    assertEquals(expected, GpsSpeed.gps(s, x));
  }

  private static Stream<Arguments> provider(){
    return Stream.of(
      Arguments.of(new double[] {0.0, 0.23, 0.46, 0.69, 0.92, 1.15, 1.38, 1.61}, 20, 41L),
      Arguments.of(new double[] {0.0, 0.11, 0.22, 0.33, 0.44, 0.65, 1.08, 1.26, 1.68, 1.89, 2.1, 2.31, 2.52, 3.25}, 12, 219L),
      Arguments.of(new double[] {0.0, 0.18, 0.36, 0.54, 0.72, 1.05, 1.26, 1.47, 1.92, 2.16, 2.4, 2.64, 2.88, 3.12, 3.36, 3.6, 3.84}, 20, 80L)
    );
  }
}