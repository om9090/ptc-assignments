package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleCalculatorTest {
    @Test
    public void addTwoPlusThree() {
        SimpleCalculator calculator = new SimpleCalculator();
        assertEquals(5, calculator.add(2, 3));
    }
}
