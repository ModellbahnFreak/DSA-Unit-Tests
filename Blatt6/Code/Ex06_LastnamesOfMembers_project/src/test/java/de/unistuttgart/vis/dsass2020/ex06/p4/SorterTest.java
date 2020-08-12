package de.unistuttgart.vis.dsass2020.ex06.p4;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SorterTest {

    @Test
    public void testSorterBasicStatic() {
        final Integer[] data = new Integer[]{4, 5, 2, 1, 3, 7, 9, 8, 0, 6};
        final ISimpleList<Integer> list = SorterTest.listFrom(data);
        assertTrue(listEquals(list, data));
        Sorter.heapSort(list);
        assertFalse(listEquals(list, data));
        Arrays.sort(data);
        assertTrue(listEquals(list, data));
    }

    @Test
    public void testSorterDuplicatesStatic() {
        final Integer[] data = new Integer[]{2, 4, 3, 2, 8, 5, 7, 2, 0, 9, 2};
        final ISimpleList<Integer> list = SorterTest.listFrom(data);
        assertTrue(listEquals(list, data));
        Sorter.heapSort(list);
        assertFalse(listEquals(list, data));
        Arrays.sort(data);
        assertTrue(listEquals(list, data));
    }

    @Test
    public void testSorterRandom() {
        final Integer[] data = new Integer[(int) (Math.random() * 200)];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (((Math.random() * 2) - 1) * Integer.MAX_VALUE);
        }
        final ISimpleList<Integer> list = SorterTest.listFrom(data);
        Sorter.heapSort(list);
        Arrays.sort(data);
        assertTrue(listEquals(list, data));
    }

    public static ISimpleList<Integer> listFrom(final Integer... numbers) {
        final SimpleList<Integer> newList = new SimpleList<Integer>();
        for (final Integer i : numbers) {
            newList.append(i);
        }
        return newList;
    }

    public static boolean listEquals(final ISimpleList<Integer> list, final Integer... numbers) {
        if (list.size() != numbers.length) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(numbers[i])) {
                return false;
            }
        }
        return true;
    }
}
