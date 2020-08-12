package de.unistuttgart.vis.dsass2020.ex04.p2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LinkedListNodeTest {

    @Test
    public void testNodeValue() {
        final LinkedListNode<Integer> node = new LinkedListNode<>();
        node.setElement(null);
        assertNull(node.getElement());
        node.setElement(0);
        assertEquals(0, (int) node.getElement());
        node.setElement(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, (int) node.getElement());
        node.setElement(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, (int) node.getElement());
        node.setElement(10000);
        assertEquals(10000, (int) node.getElement());
    }

    @Test
    public void testNodeNext() {
        final LinkedListNode<Integer> node = new LinkedListNode<>();
        final LinkedListNode<Integer> nextNode = new LinkedListNode<>();
        assertNull(node.getNext());
        node.setNext(nextNode);
        assertEquals(nextNode, node.getNext());
        node.setNext(node);
        assertEquals(node, node.getNext());
        node.setNext(null);
        assertNull(node.getNext());
    }

    @Test
    public void testNodePrev() {
        final LinkedListNode<Integer> node = new LinkedListNode<>();
        final LinkedListNode<Integer> prevNode = new LinkedListNode<>();
        assertNull(node.getPrev());
        node.setPrev(prevNode);
        assertEquals(prevNode, node.getPrev());
        node.setPrev(node);
        assertEquals(node, node.getPrev());
        node.setPrev(null);
        assertNull(node.getPrev());
    }

    @Test
    public void testCombine() {
        final LinkedListNode<Integer> node = new LinkedListNode<>();
        final LinkedListNode<Integer> nextNode = new LinkedListNode<>();
        final LinkedListNode<Integer> nextPrevNode = new LinkedListNode<>();
        final LinkedListNode<Integer> nextPrevPrevNode = new LinkedListNode<>();
        final LinkedListNode<Integer> nextPrevPrevPrevNode = new LinkedListNode<>();
        node.setNext(nextNode);
        nextNode.setPrev(nextPrevNode);
        nextPrevNode.setPrev(nextPrevPrevNode);
        nextPrevPrevNode.setPrev(nextPrevPrevPrevNode);
        nextPrevPrevPrevNode.setNext(node);
        assertEquals(node, node.getNext().getPrev().getPrev().getPrev().getNext());
    }

}