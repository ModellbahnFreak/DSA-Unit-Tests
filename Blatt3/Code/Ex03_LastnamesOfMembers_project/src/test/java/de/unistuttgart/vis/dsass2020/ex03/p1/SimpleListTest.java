package de.unistuttgart.vis.dsass2020.ex03.p1;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SimpleListTest {

    /**
     * Tests if the list can handle an arbitrary amount of numbers
     */
    @Test
    public void testIteratorSimple() {
        final LinkedList<Integer> addItems = new LinkedList<>();
        final SimpleList<Integer> ints = new SimpleList<>();
        final int num = (int) (Math.random() * 100000);
        for (int i = 0; i < num; i++) {
            final int addNum = (int) ((Math.random() * 2.0 - 1) * Integer.MAX_VALUE);
            addItems.add(addNum);
            ints.append(addNum);
        }
        final Iterator<Integer> itOrig = addItems.iterator();
        final Iterator<Integer> itList = ints.iterator();
        while (itOrig.hasNext() && itList.hasNext()) {
            assertEquals(itOrig.next(), itList.next());
        }
        assertEquals(itOrig.hasNext(), itList.hasNext());
    }

    /**
     * Tests if the skip works
     */
    @Test
    public void testIteratorSkip() {
        final LinkedList<Integer> addItems = new LinkedList<>();
        final SimpleList<Integer> ints = new SimpleList<>();
        final int num = (int) (Math.random() * 100000);
        final int stepSize = (int) (Math.random() * num / 10.0);
        for (int i = 0; i < num; i++) {
            final int addNum = (int) ((Math.random() * 2.0 - 1) * Integer.MAX_VALUE);
            addItems.add(addNum);
            ints.append(addNum);
        }
        final Iterator<Integer> expected = addItems.iterator();
        final Iterator<Integer> actual = ints.skippingIterator(stepSize);
        while (actual.hasNext() && expected.hasNext()) {
            assertEquals("Skipping iterator didn't match expected", actual.next(), expected.next());
            for (int i = 0; i < stepSize - 1 && expected.hasNext(); i++) {
                expected.next();
            }
        }
        assertFalse("Skipping iterator was not finished when loop ended", actual.hasNext());
    }

}
