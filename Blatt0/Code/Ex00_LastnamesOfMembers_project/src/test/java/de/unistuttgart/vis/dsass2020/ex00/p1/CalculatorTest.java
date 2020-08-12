package de.unistuttgart.vis.dsass2020.ex00.p1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the simple calculator implementation in {@link Calculator}
 */
public class CalculatorTest {

    private static final int NUM_TEST = 10000;

    /**
     * Just create additional tests by creating new methods according to this
     * pattern.
     */
    @Test
    public void testAdd() {
        final ICalculator cal = new Calculator();
        assertEquals(2 + 5, cal.add(2, 5));
        assertEquals(3 + 4, cal.add(3, 4));
    }

    @Test
    public void testMult() {
        final ICalculator cal = new Calculator();
        assertEquals(2 * 5, cal.multiply(2, 5));
        assertEquals(3 * 4, cal.multiply(3, 4));
    }

    @Test
    public void testMin() {
        final ICalculator cal = new Calculator();
        assertEquals(2, cal.minimum(2, 5));
        assertEquals(3, cal.minimum(4, 3));
    }

    @Test
    public void testDigitSum() {
        final ICalculator cal = new Calculator();
        for (int i = 0; i < NUM_TEST; i++) {
            final int x = (int) (Math.random() * Integer.MAX_VALUE);
            final int expected = (x + "").chars().reduce(0, (a, b) -> a + b - '0');
            assertEquals(expected, cal.quersumme(x));
        }
    }

}
