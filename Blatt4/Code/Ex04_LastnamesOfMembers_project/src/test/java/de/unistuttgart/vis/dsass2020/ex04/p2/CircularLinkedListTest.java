package de.unistuttgart.vis.dsass2020.ex04.p2;

import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CircularLinkedListTest {

    private final Random rnd = new Random();

    @Test
    public void testBasicInsertGet() {
        final LinkedList<Integer> ints = new LinkedList<>();
        final CircularLinkedList<Integer> list = new CircularLinkedList<>();
        final int len = this.rnd.nextInt(10000) + 1;
        for (int i = 0; i < len; i++) {
            final int num = this.rnd.nextInt();
            ints.addLast(num);
            list.append(num);
        }
        assertEquals("list size", len, list.size());
        for (int i = 0; i < len; i++) {
            assertEquals("element " + i, ints.get(i), list.get(i));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoElementStartGet() {
        final CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.get(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoElementStartHead() {
        final CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.getHead();
    }

    @Test
    public void testStructureNext() {
        final LinkedList<Integer> ints = new LinkedList<>();
        final CircularLinkedList<Integer> list = new CircularLinkedList<>();
        final int len = this.rnd.nextInt(10000) + 1;
        for (int i = 0; i < len; i++) {
            final int num = this.rnd.nextInt();
            ints.addLast(num);
            list.append(num);
        }
        assertEquals("list size", len, list.size());
        final ILinkedListNode<Integer> head = list.getHead();
        assertEquals(ints.get(0), head.getElement());
        ILinkedListNode<Integer> current = head;
        for (int i = 1; i < len; i++) {
            current = current.getNext();
            if (!ints.get(i).equals(current.getElement())) {
                System.out.println(i);
            }
            assertEquals(ints.get(i), current.getElement());
        }
        current = current.getNext();
        assertEquals(head, current);
    }

    @Test
    public void testStructurePrev() {
        final LinkedList<Integer> ints = new LinkedList<>();
        final CircularLinkedList<Integer> list = new CircularLinkedList<>();
        final int len = this.rnd.nextInt(10000) + 1;
        for (int i = 0; i < len; i++) {
            final int num = this.rnd.nextInt();
            ints.addLast(num);
            list.append(num);
        }
        assertEquals("list size", len, list.size());
        final ILinkedListNode<Integer> head = list.getHead();
        assertEquals(ints.get(0), head.getElement());
        ILinkedListNode<Integer> current = head;
        for (int i = len - 1; i >= 0; i--) {
            current = current.getPrev();
            assertEquals(ints.get(i), current.getElement());
        }
        assertEquals(head, current);
    }

}
