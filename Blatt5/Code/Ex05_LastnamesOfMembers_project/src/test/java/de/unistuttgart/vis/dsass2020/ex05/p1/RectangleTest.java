package de.unistuttgart.vis.dsass2020.ex05.p1;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RectangleTest {

    @Test
    public void testNewRect() {
        final Rectangle r1 = new Rectangle(0, 0, 0, 0);
        assertTrue(Math.abs(r1.x - 0) < 0.0001);
        assertTrue(Math.abs(r1.y - 0) < 0.0001);
        assertTrue(Math.abs(r1.width - 0) < 0.0001);
        assertTrue(Math.abs(r1.height - 0) < 0.0001);
        final Rectangle r2 = new Rectangle(10, 20, 30, 40);
        assertTrue(Math.abs(r2.x - 10) < 0.0001);
        assertTrue(Math.abs(r2.y - 20) < 0.0001);
        assertTrue(Math.abs(r2.width - 30) < 0.0001);
        assertTrue(Math.abs(r2.height - 40) < 0.0001);
        final Rectangle r3 = new Rectangle(10, 20, -30, 40);
        assertTrue(Math.abs(r3.x + 20) < 0.0001);
        assertTrue(Math.abs(r3.y - 20) < 0.0001);
        assertTrue(Math.abs(r3.width - 30) < 0.0001);
        assertTrue(Math.abs(r3.height - 40) < 0.0001);
        final Rectangle r4 = new Rectangle(10, 20, 30, -40);
        assertTrue(Math.abs(r4.x - 10) < 0.0001);
        assertTrue(Math.abs(r4.y + 20) < 0.0001);
        assertTrue(Math.abs(r4.width - 30) < 0.0001);
        assertTrue(Math.abs(r4.height - 40) < 0.0001);
        final Rectangle r5 = new Rectangle(-10, 20, 30, 40);
        assertTrue(Math.abs(r5.x + 10) < 0.0001);
        assertTrue(Math.abs(r5.y - 20) < 0.0001);
        assertTrue(Math.abs(r5.width - 30) < 0.0001);
        assertTrue(Math.abs(r5.height - 40) < 0.0001);
        final Rectangle r6 = new Rectangle(10, -20, 30, 40);
        assertTrue(Math.abs(r6.x - 10) < 0.0001);
        assertTrue(Math.abs(r6.y + 20) < 0.0001);
        assertTrue(Math.abs(r6.width - 30) < 0.0001);
        assertTrue(Math.abs(r6.height - 40) < 0.0001);
    }

    @Test
    public void testIntersectManualValues() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        final Rectangle r2 = new Rectangle(50, 50, 100, 100);
        final Rectangle r3 = new Rectangle(100, 150, 100, 100);
        final Rectangle r4 = new Rectangle(100, 150.01f, 100, 100);
        final Rectangle r5 = new Rectangle(300, 300, 100, 100);
        assertTrue(r1.intersects(r2));
        assertTrue(r2.intersects(r1));

        assertTrue(r1.intersects(r3));
        assertTrue(r3.intersects(r1));

        assertTrue(r1.intersects(r4));
        assertTrue(r4.intersects(r1));

        assertFalse(r1.intersects(r5));
        assertFalse(r5.intersects(r1));

        assertTrue(r2.intersects(r3));
        assertTrue(r3.intersects(r2));

        assertFalse(r2.intersects(r4));
        assertFalse(r4.intersects(r2));

        assertFalse(r2.intersects(r5));
        assertFalse(r5.intersects(r2));

        assertTrue(r3.intersects(r4));
        assertTrue(r4.intersects(r3));

        assertFalse(r3.intersects(r5));
        assertFalse(r5.intersects(r3));

        assertFalse(r4.intersects(r5));
        assertFalse(r5.intersects(r4));
    }

    @Test
    public void testFullyCoinatinedManualValues() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        final Rectangle r2 = new Rectangle(50, 50, 100, 100);
        final Rectangle r3 = new Rectangle(105, 105, 90, 90);
        final Rectangle r4 = new Rectangle(100, 100, 100, 100);
        final Rectangle r5 = new Rectangle(105, 105, 96, 96);
        final Rectangle r6 = new Rectangle(3000, 3000, 1, 1);

        assertFalse(r1.fullyContaines(r2));
        assertFalse(r2.fullyContaines(r1));

        assertTrue(r1.fullyContaines(r3));
        assertFalse(r3.fullyContaines(r1));

        assertTrue(r1.fullyContaines(r4));
        assertTrue(r4.fullyContaines(r1));

        assertFalse(r1.fullyContaines(r5));
        assertFalse(r5.fullyContaines(r1));

        assertFalse(r1.fullyContaines(r6));
        assertFalse(r6.fullyContaines(r1));

        assertFalse(r2.fullyContaines(r3));
        assertFalse(r3.fullyContaines(r2));

        assertFalse(r2.fullyContaines(r4));
        assertFalse(r4.fullyContaines(r2));

        assertFalse(r3.fullyContaines(r4));
        assertTrue(r4.fullyContaines(r3));
    }

    @Test
    public void testContainsManualValues() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        final Point p1 = new Point(150, 150);
        final Point p2 = new Point(100, 100);
        final Point p3 = new Point(0, 0);
        final Point p4 = new Point(-200, -57654);
        final Point p5 = new Point(150, 250);
        final Point p6 = new Point(200, 150);

        assertTrue(r1.contains(p1));
        assertTrue(r1.contains(p2));
        assertFalse(r1.contains(p3));
        assertFalse(r1.contains(p4));
        assertFalse(r1.contains(p5));
        assertTrue(r1.contains(p6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsNull() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        r1.contains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntersectsNull() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        r1.intersects(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFullyContainedNull() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        r1.fullyContaines(null);
    }
}
