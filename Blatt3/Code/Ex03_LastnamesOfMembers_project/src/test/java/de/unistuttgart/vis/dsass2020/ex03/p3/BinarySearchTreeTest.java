package de.unistuttgart.vis.dsass2020.ex03.p3;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinarySearchTreeTest {

    @Test
    public void testFull() {
        final IBinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(44);
        tree.insert(21);
        tree.insert(16);
        tree.insert(11);
        tree.insert(19);
        tree.insert(40);
        tree.insert(73);
        tree.insert(47);
        tree.insert(85);
        assertTrue(tree.isFull());
    }

    @Test
    public void testSize() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        final LinkedList<Integer> reference = new LinkedList<>();
        final int num = (int) (Math.random() * 1000);
        for (int i = 0; i < num; i++) {
            final int addNum = (int) ((Math.random() * 2.0 - 1) * Integer.MAX_VALUE);
            if (!reference.contains(addNum)) {
                tree.insert(addNum);
                reference.add(addNum);
            }
        }
        assertEquals(reference.size(), tree.size());
    }

    @Test
    public void testPreorderPrint() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(44);
        tree.insert(21);
        tree.insert(16);
        tree.insert(11);
        tree.insert(19);
        tree.insert(40);
        tree.insert(73);
        tree.insert(47);
        tree.insert(85);
        assertEquals("BinarySearchTree<>(preorder)[44,21,16,11,19,40,73,47,85,]", tree.toStringPreorder());
    }

    @Test
    public void testInorderPrint() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        final LinkedList<Integer> reference = new LinkedList<>();
        final int num = (int) (Math.random() * 1000);
        for (int i = 0; i < num; i++) {
            final int addNum = (int) ((Math.random() * 2.0 - 1) * Integer.MAX_VALUE);
            if (!reference.contains(addNum)) {
                tree.insert(addNum);
                reference.add(addNum);
            }
        }
        reference.sort(Integer::compareTo);
        //assertEquals("BinarySearchTree<>(inorder)[" + reference.stream().map(x -> x.toString()).collect(Collectors
        // .joining(",")) + ",]",
        //tree.toStringInorder());
    }

    @Test
    public void testActualFixedValues() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(44);
        tree.insert(21);
        tree.insert(16);
        tree.insert(11);
        tree.insert(19);
        tree.insert(40);
        tree.insert(73);
        tree.insert(47);
        tree.insert(85);
        final IBinaryTreeNode<Integer> curr = tree.getRootNode();
        assertEquals((Integer) 44, curr.getValue());
        assertEquals((Integer) 21, curr.getLeftChild().getValue());
        assertEquals((Integer) 16, curr.getLeftChild().getLeftChild().getValue());
        assertEquals((Integer) 11, curr.getLeftChild().getLeftChild().getLeftChild().getValue());
        assertEquals((Integer) 19, curr.getLeftChild().getLeftChild().getRightChild().getValue());
        assertEquals((Integer) 40, curr.getLeftChild().getRightChild().getValue());
        assertEquals((Integer) 73, curr.getRightChild().getValue());
        assertEquals((Integer) 47, curr.getRightChild().getLeftChild().getValue());
        assertEquals((Integer) 85, curr.getRightChild().getRightChild().getValue());
    }

    @Test
    public void testActualStructure() throws NoSuchFieldException, IllegalAccessException {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        final Field nullNodeField = tree.getClass().getDeclaredField("nullNode");
        nullNodeField.setAccessible(true);
        final IBinaryTreeNode<Integer> nullNode = (IBinaryTreeNode<Integer>) nullNodeField.get(tree);
        assertEquals("Initial root was not nullNode", nullNode, tree.getRootNode());
        final LinkedList<Integer> reference = new LinkedList<>();
        final int num = (int) (Math.random() * 20);
        for (int i = 0; i < num; i++) {
            final int addNum = (int) ((Math.random() * 2.0 - 1) * 100);
            if (!reference.contains(addNum)) {
                tree.insert(addNum);
                reference.add(addNum);
            }
        }
        reference.sort(Integer::compareTo);
        System.out.println("Expected: ");
        System.out.println(reference);
        System.out.println("Actual: ");
        System.out.println(tree.toStringLatex());
        assertEquals("Not shifted far enough", reference.size(), BinarySearchTreeTest.testFromNode(tree.getRootNode(),
                reference, 0,
                reference.size(), nullNode));
    }

    private static int testFromNode(final IBinaryTreeNode<Integer> node, final LinkedList<Integer> reference, final int start, final int end,
                                    final IBinaryTreeNode<Integer> nullNode) {
        int shift = 0;
        final IBinaryTreeNode<Integer> left = node.getLeftChild();
        if (left != null && left != nullNode) {
            System.out.print("(");
            shift += testFromNode(left, reference, start + shift, end, nullNode);
            System.out.print(")");
        }
        System.out.print(node.getValue() + ",");
        assertEquals("The " + (start + shift) + "-th smallest value was incorrect", node.getValue(), reference.get(start + shift));
        shift++;
        final IBinaryTreeNode<Integer> right = node.getRightChild();
        if (right != null && right != nullNode) {
            System.out.print("(");
            shift += testFromNode(right, reference, start + shift, end, nullNode);
            System.out.print(")");
        }
        return shift;
    }

    @Test
    public void testDuplicates() throws IllegalAccessException, NoSuchFieldException {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        final Field nullNodeField = tree.getClass().getDeclaredField("nullNode");
        nullNodeField.setAccessible(true);
        final IBinaryTreeNode<Integer> nullNode = (IBinaryTreeNode<Integer>) nullNodeField.get(tree);
        final int num = (int) (Math.random() * 50);
        final int addNum = (int) ((Math.random() * 2.0 - 1) * Integer.MAX_VALUE);
        for (int i = 0; i < num; i++) {
            tree.insert(addNum);
        }
        //assertEquals(1, tree.size());
        assertEquals("Value wasn't added at all", (Integer) addNum, tree.getRootNode().getValue());
        assertEquals("Left child of head isn't nullNode", nullNode, tree.getRootNode().getLeftChild());
        assertEquals("Right child of head isn't nullNode", nullNode, tree.getRootNode().getRightChild());
    }

}
