package de.unistuttgart.vis.dsass2020.ex04.p3;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BinarySearchTreeTest {

    private static class Tuple {
        private final BinarySearchTree<Integer> tree;
        private final IBinaryTreeNode<Integer> nullNode;
        private final LinkedList<Integer> reference;

        Tuple(final BinarySearchTree<Integer> tree, final IBinaryTreeNode<Integer> nullNode, final LinkedList<Integer> reference) {
            this.tree = tree;
            this.nullNode = nullNode;
            this.reference = reference;
        }
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
    public void testPreorderIterate() {
        final BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        final LinkedList<Integer> list = new LinkedList<>();
        list.addLast(44);
        list.addLast(21);
        list.addLast(16);
        list.addLast(11);
        list.addLast(19);
        list.addLast(40);
        list.addLast(73);
        list.addLast(47);
        list.addLast(85);
        tree.insert(44);
        tree.insert(21);
        tree.insert(16);
        tree.insert(11);
        tree.insert(19);
        tree.insert(40);
        tree.insert(73);
        tree.insert(47);
        tree.insert(85);
        final Iterator<Integer> treeIt = tree.iterator(TreeTraversalType.PREORDER);
        final Iterator<Integer> listIt = list.iterator();
        while (listIt.hasNext() && treeIt.hasNext()) {
            assertEquals(listIt.next(), treeIt.next());
        }
        assertEquals(listIt.hasNext(), treeIt.hasNext());
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
        final Iterator<Integer> treeIt = tree.iterator(TreeTraversalType.INORDER);
        final Iterator<Integer> listIt = reference.iterator();
        while (listIt.hasNext() && treeIt.hasNext()) {
            assertEquals(listIt.next(), treeIt.next());
        }
        assertEquals(listIt.hasNext(), treeIt.hasNext());
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

    private static Tuple prepareTreeRandom() throws NoSuchFieldException, IllegalAccessException {
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
        return new Tuple(tree, nullNode, reference);
    }

    @Test
    public void testActualStructure() throws NoSuchFieldException, IllegalAccessException {
        final Tuple t = prepareTreeRandom();
        final Iterator<Integer> it = t.tree.iterator(TreeTraversalType.INORDER);
        if (t.tree.size() > 0) {
            BinarySearchTreeTest.testFromNodeInorder(t.tree.getRootNode(), it, t.nullNode);
        }
        assertFalse(it.hasNext());
    }

    @Test
    public void testActualStructurePreorder() throws NoSuchFieldException, IllegalAccessException {
        final Tuple t = prepareTreeRandom();
        final Iterator<Integer> it = t.tree.iterator(TreeTraversalType.PREORDER);
        if (t.tree.size() > 0) {
            BinarySearchTreeTest.testFromNodePreorder(t.tree.getRootNode(), it, t.nullNode);
        }
        assertFalse(it.hasNext());
    }

    @Test
    public void testActualStructurePostorder() throws NoSuchFieldException, IllegalAccessException {
        final Tuple t = prepareTreeRandom();
        final Iterator<Integer> it = t.tree.iterator(TreeTraversalType.POSTORDER);
        if (t.tree.size() > 0) {
            BinarySearchTreeTest.testFromNodePostorder(t.tree.getRootNode(), it, t.nullNode);
        }
        assertFalse(it.hasNext());
    }

    @Test
    public void testActualStructureLevelorder() throws NoSuchFieldException, IllegalAccessException {
        final Tuple t = prepareTreeRandom();
        final LinkedList<Integer> levelorderList = new LinkedList<>();
        final LinkedList<IBinaryTreeNode<Integer>> queue = new LinkedList<>();
        if (t.tree.size() > 0) {
            queue.addLast(t.tree.getRootNode());
            while (!queue.isEmpty()) {
                final IBinaryTreeNode<Integer> current = queue.removeFirst();
                levelorderList.addLast(current.getValue());
                final IBinaryTreeNode<Integer> leftNode = current.getLeftChild();
                final IBinaryTreeNode<Integer> rightNode = current.getRightChild();
                if (leftNode != null && leftNode != t.nullNode) {
                    queue.addLast(leftNode);
                }
                if (rightNode != null && rightNode != t.nullNode) {
                    queue.addLast(rightNode);
                }
            }
            final Iterator<Integer> it = t.tree.iterator(TreeTraversalType.LEVELORDER);
            final Iterator<Integer> listIt = levelorderList.iterator();
            while (listIt.hasNext() && it.hasNext()) {
                assertEquals(listIt.next(), it.next());
            }
            assertFalse(it.hasNext());
            assertFalse(listIt.hasNext());
        }
    }

    private static void testFromNodeInorder(final IBinaryTreeNode<Integer> node, final Iterator<Integer> it,
                                            final IBinaryTreeNode<Integer> nullNode) {
        final IBinaryTreeNode<Integer> left = node.getLeftChild();
        if (left != null && left != nullNode) {
            testFromNodeInorder(left, it, nullNode);
        }
        assertEquals(node.getValue(), it.next());
        System.out.println(node.getValue() + ", ");
        final IBinaryTreeNode<Integer> right = node.getRightChild();
        if (right != null && right != nullNode) {
            testFromNodeInorder(right, it, nullNode);
        }
    }

    private static void testFromNodePreorder(final IBinaryTreeNode<Integer> node, final Iterator<Integer> it,
                                             final IBinaryTreeNode<Integer> nullNode) {
        assertEquals(node.getValue(), it.next());
        System.out.println(node.getValue() + ", ");
        final IBinaryTreeNode<Integer> left = node.getLeftChild();
        if (left != null && left != nullNode) {
            testFromNodePreorder(left, it, nullNode);
        }
        final IBinaryTreeNode<Integer> right = node.getRightChild();
        if (right != null && right != nullNode) {
            testFromNodePreorder(right, it, nullNode);
        }
    }

    private static void testFromNodePostorder(final IBinaryTreeNode<Integer> node, final Iterator<Integer> it,
                                              final IBinaryTreeNode<Integer> nullNode) {
        final IBinaryTreeNode<Integer> left = node.getLeftChild();
        if (left != null && left != nullNode) {
            testFromNodePostorder(left, it, nullNode);
        }
        final IBinaryTreeNode<Integer> right = node.getRightChild();
        if (right != null && right != nullNode) {
            testFromNodePostorder(right, it, nullNode);
        }
        assertEquals(node.getValue(), it.next());
        System.out.println(node.getValue() + ", ");
    }

    @Test
    public void testLevelOrder() throws NoSuchFieldException, IllegalAccessException {
        final Tuple t = prepareTreeRandom();
        final Queue<IBinaryTreeNode<Integer>> queue = new ArrayDeque<>();
        if (t.tree.size() > 0) {
            queue.add(t.tree.getRootNode());
            final Iterator<Integer> it = t.tree.iterator(TreeTraversalType.LEVELORDER);
            while (!queue.isEmpty()) {
                final IBinaryTreeNode<Integer> node = queue.remove();
                assertEquals(node.getValue(), it.next());
                if (node.getLeftChild() != t.nullNode && node.getLeftChild() != null) {
                    queue.add(node.getLeftChild());
                }
                if (node.getRightChild() != t.nullNode && node.getRightChild() != null) {
                    queue.add(node.getRightChild());
                }
            }
            assertFalse(it.hasNext());
        }
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
