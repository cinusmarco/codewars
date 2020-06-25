/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.fourkyu.humanreadabledurationformat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TimeFormatterTest {

    @ParameterizedTest
    @MethodSource(value = "provider")
    void test(int seconds, String expected){
        final String result = TimeFormatter.formatDuration(seconds);

        assertThat(result).isEqualTo(expected);
    }
    @ParameterizedTest
    @MethodSource(value = "provider")
    void testV2(int seconds, String expected){
        final String result = TimeFormatterV2.formatDuration(seconds);

        assertThat(result).isEqualTo(expected);
    }


  private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(1, "1 second"),
                Arguments.of(0, "now"),
                Arguments.of(1, "1 second"),
                Arguments.of(62, "1 minute and 2 seconds"),
                Arguments.of(120, "2 minutes"),
                Arguments.of(3600, "1 hour"),
                Arguments.of(2*3600, "2 hours"),
                Arguments.of(60*60*24, "1 day"),
                Arguments.of(2*60*60*24, "2 days"),
                Arguments.of(60*60*24*365, "1 year"),
                Arguments.of(2*60*60*24*365, "2 years"),
                Arguments.of(3*60*60*24*365, "3 years"),
                Arguments.of(1551600, "17 days and 23 hours"),
                Arguments.of(3662, "1 hour, 1 minute and 2 seconds")
        );
    }

}
