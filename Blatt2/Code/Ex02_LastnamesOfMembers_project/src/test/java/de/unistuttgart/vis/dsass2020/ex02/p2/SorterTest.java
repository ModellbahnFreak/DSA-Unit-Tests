package de.unistuttgart.vis.dsass2020.ex02.p2;

import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SorterTest {

    private static List<Consumer<ISimpleList<?>>> getSortAlgorithms() {
        return List.of(Sorter::bubbleSort, Sorter::insertionSort, Sorter::selectionSort);
        //Sorter::bubbleSort, Sorter::insertionSort, Sorter::selectionSort
    }

    private static <T extends Comparable<T>> void assertSorted(final ISimpleList<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).compareTo(list.get(i + 1)) <= 0);
        }
    }

    @Test
    public void testRandom() {
        for (final var algorithm : SorterTest.getSortAlgorithms()) {
            final ISimpleList<Integer> testList = new SimpleList<>();
            final int numItems = (int) (Math.random() * 100);
            for (int i = 0; i < numItems; i++) {
                testList.append((int) ((Math.random() * 2 - 1) * Integer.MAX_VALUE));
            }
            System.out.println(testList);
            algorithm.accept(testList);
            System.out.println(testList);
            SorterTest.assertSorted(testList);
        }
    }

    @Test
    public void testNormal() {
        for (final var algorithm : SorterTest.getSortAlgorithms()) {
            final ISimpleList<Integer> testList = new SimpleList<>();
            testList.append(1);
            testList.append(70);
            testList.append(6);
            testList.append(4);
            testList.append(0);
            testList.append(9);
            testList.append(0);
            testList.append(1000);
            testList.append(5);
            algorithm.accept(testList);
            SorterTest.assertSorted(testList);
        }

    }

    @Test
    public void testEmpty() {
        for (final var algorithm : SorterTest.getSortAlgorithms()) {
            final ISimpleList<Integer> testList = new SimpleList<>();
            algorithm.accept(testList);
            assertEquals(0, testList.size());
        }
    }

    @Test
    public void testSingleElement() {
        for (final var algorithm : SorterTest.getSortAlgorithms()) {
            final ISimpleList<Integer> testList = new SimpleList<>();
            testList.append(-1);
            algorithm.accept(testList);
            assertEquals(1, testList.size());
            SorterTest.assertSorted(testList);
        }
    }

}
