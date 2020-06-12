/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2020.
 */

package ch.cinus.kata.sevenkyu.nthpower;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KataTest {
    @Test
    public void basicTests() {
        assertEquals(30, Kata.modifiedSum(new int[] {1,2,3}, 3));
        assertEquals(30, Kata.modifiedSum(new int[] {1,2}, 5));
    }
}
