package de.unistuttgart.vis.dsass2020.ex05.p2;

import de.unistuttgart.vis.dsass2020.ex05.p1.Point;
import de.unistuttgart.vis.dsass2020.ex05.p1.Rectangle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class SimpleQuadTreeTest {

    private static class TestElement implements QuadTreeElement {

        private final Point p;

        private TestElement(final float x, final float y) {
            this.p = new Point(x, y);
        }

        private TestElement() {
            this.p = null;
        }

        @Override
        public Point getPosition() {
            return this.p;
        }
    }

    @Test
    public void testSimpleTree() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);

        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Arrays.asList(p1, p2, p3, p4), 1);

        assertTrue(tree.boundingBox.contains(p1.getPosition()));
        assertTrue(tree.boundingBox.contains(p2.getPosition()));
        assertTrue(tree.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.boundingBox.contains(p4.getPosition()));
        assertNull(tree.leafElements);

        assertTrue(tree.tl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p4.getPosition()));
        assertEquals(1, tree.tl.leafElements.size());
        assertTrue(tree.tl.leafElements.contains(p1));

        assertFalse(tree.tr.boundingBox.contains(p1.getPosition()));
        assertTrue(tree.tr.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p4.getPosition()));
        assertEquals(1, tree.tr.leafElements.size());
        assertTrue(tree.tr.leafElements.contains(p2));

        assertFalse(tree.bl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p2.getPosition()));
        assertTrue(tree.bl.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p4.getPosition()));
        assertEquals(1, tree.bl.leafElements.size());
        assertTrue(tree.bl.leafElements.contains(p3));

        assertFalse(tree.br.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.br.boundingBox.contains(p4.getPosition()));
        assertEquals(1, tree.br.leafElements.size());
        assertTrue(tree.br.leafElements.contains(p4));
    }

    @Test
    public void testTreeTwoLeafElements() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);
        final TestElement p5 = new TestElement(1.25f, 1.25f);

        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Arrays.asList(p1, p2, p3, p4, p5), 2);

        assertTrue(tree.boundingBox.contains(p1.getPosition()));
        assertTrue(tree.boundingBox.contains(p2.getPosition()));
        assertTrue(tree.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.boundingBox.contains(p4.getPosition()));
        assertTrue(tree.boundingBox.contains(p5.getPosition()));
        assertNull(tree.leafElements);

        assertTrue(tree.tl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p4.getPosition()));
        assertTrue(tree.tl.boundingBox.contains(p5.getPosition()));
        assertEquals(2, tree.tl.leafElements.size());
        assertTrue(tree.tl.leafElements.contains(p1));
        assertTrue(tree.tl.leafElements.contains(p5));

        assertFalse(tree.tr.boundingBox.contains(p1.getPosition()));
        assertTrue(tree.tr.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p5.getPosition()));
        assertEquals(1, tree.tr.leafElements.size());
        assertTrue(tree.tr.leafElements.contains(p2));

        assertFalse(tree.bl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p2.getPosition()));
        assertTrue(tree.bl.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p5.getPosition()));
        assertEquals(1, tree.bl.leafElements.size());
        assertTrue(tree.bl.leafElements.contains(p3));

        assertFalse(tree.br.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.br.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p5.getPosition()));
        assertEquals(1, tree.br.leafElements.size());
        assertTrue(tree.br.leafElements.contains(p4));
    }

    @Test
    public void testTreeOnBorder() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(1.5f, 1.5f);

        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Arrays.asList(p1, p2, p3, p4), 2);

        assertTrue(tree.boundingBox.contains(p1.getPosition()));
        assertTrue(tree.boundingBox.contains(p2.getPosition()));
        assertTrue(tree.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.boundingBox.contains(p4.getPosition()));
        assertNull(tree.leafElements);

        assertTrue(tree.tl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.tl.boundingBox.contains(p4.getPosition()));
        assertEquals(2, tree.tl.leafElements.size());
        assertTrue(tree.tl.leafElements.contains(p1));
        assertTrue(tree.tl.leafElements.contains(p4));

        assertFalse(tree.tr.boundingBox.contains(p1.getPosition()));
        assertTrue(tree.tr.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.tr.boundingBox.contains(p4.getPosition()));
        assertEquals(1, tree.tr.leafElements.size());
        assertTrue(tree.tr.leafElements.contains(p2));

        assertFalse(tree.bl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p2.getPosition()));
        assertTrue(tree.bl.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.bl.boundingBox.contains(p4.getPosition()));
        assertEquals(1, tree.bl.leafElements.size());
        assertTrue(tree.bl.leafElements.contains(p3));

        assertFalse(tree.br.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.br.boundingBox.contains(p4.getPosition()));
        assertEquals(0, tree.br.leafElements.size());
    }

    @Test
    public void testTreeSubdivTopLeftAndSearch() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);
        final TestElement p5 = new TestElement(1.3f, 1.3f);

        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Arrays.asList(p1, p2, p3, p4, p5), 1);

        assertTrue(tree.boundingBox.contains(p1.getPosition()));
        assertTrue(tree.boundingBox.contains(p2.getPosition()));
        assertTrue(tree.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.boundingBox.contains(p4.getPosition()));
        assertTrue(tree.boundingBox.contains(p5.getPosition()));
        assertNull(tree.leafElements);

        assertTrue(tree.tl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tl.boundingBox.contains(p4.getPosition()));
        assertTrue(tree.tl.boundingBox.contains(p5.getPosition()));
        assertNull(tree.tl.leafElements);

        assertTrue(tree.tl.tl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.tl.tl.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tl.tl.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tl.tl.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.tl.tl.boundingBox.contains(p5.getPosition()));
        assertEquals(1, tree.tl.tl.leafElements.size());
        assertTrue(tree.tl.tl.leafElements.contains(p1));

        assertFalse(tree.tl.tr.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.tl.tr.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tl.tr.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tl.tr.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.tl.tr.boundingBox.contains(p5.getPosition()));
        assertEquals(0, tree.tl.tr.leafElements.size());
        assertNull(tree.tl.tr.tl);
        assertNull(tree.tl.tr.tr);
        assertNull(tree.tl.tr.bl);
        assertNull(tree.tl.tr.br);

        assertFalse(tree.tl.bl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.tl.bl.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tl.bl.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tl.bl.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.tl.bl.boundingBox.contains(p5.getPosition()));
        assertEquals(0, tree.tl.bl.leafElements.size());
        assertNull(tree.tl.bl.tl);
        assertNull(tree.tl.bl.tr);
        assertNull(tree.tl.bl.bl);
        assertNull(tree.tl.bl.br);

        assertFalse(tree.tl.br.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.tl.br.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tl.br.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tl.br.boundingBox.contains(p4.getPosition()));
        assertTrue(tree.tl.br.boundingBox.contains(p5.getPosition()));
        assertEquals(1, tree.tl.br.leafElements.size());
        assertTrue(tree.tl.br.leafElements.contains(p5));

        assertFalse(tree.tr.boundingBox.contains(p1.getPosition()));
        assertTrue(tree.tr.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.tr.boundingBox.contains(p5.getPosition()));
        assertEquals(1, tree.tr.leafElements.size());
        assertTrue(tree.tr.leafElements.contains(p2));

        assertFalse(tree.bl.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p2.getPosition()));
        assertTrue(tree.bl.boundingBox.contains(p3.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.bl.boundingBox.contains(p5.getPosition()));
        assertEquals(1, tree.bl.leafElements.size());
        assertTrue(tree.bl.leafElements.contains(p3));

        assertFalse(tree.br.boundingBox.contains(p1.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p2.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p3.getPosition()));
        assertTrue(tree.br.boundingBox.contains(p4.getPosition()));
        assertFalse(tree.br.boundingBox.contains(p5.getPosition()));
        assertEquals(1, tree.br.leafElements.size());
        assertTrue(tree.br.leafElements.contains(p4));


        final Rectangle all = new Rectangle(0, 0, 4, 4);
        final Rectangle topLeft = new Rectangle(0, 0, 1.5f, 1.5f);
        final Rectangle bottomRight = new Rectangle(2, 2, 0, 0);
        final Rectangle none = new Rectangle(2.001f, 0, 100, 100);
        final ArrayList<TestElement> allResult = new ArrayList<>();
        final ArrayList<TestElement> topLeftResult = new ArrayList<>();
        final ArrayList<TestElement> bottomRightResult = new ArrayList<>();
        final ArrayList<TestElement> noneResult = new ArrayList<>();

        tree.rangeQuery(allResult, all);
        tree.rangeQuery(topLeftResult, topLeft);
        tree.rangeQuery(bottomRightResult, bottomRight);
        tree.rangeQuery(noneResult, none);

        assertEquals(5, allResult.size());
        assertTrue(allResult.contains(p1));
        assertTrue(allResult.contains(p2));
        assertTrue(allResult.contains(p3));
        assertTrue(allResult.contains(p4));
        assertTrue(allResult.contains(p5));

        assertEquals(2, topLeftResult.size());
        assertTrue(topLeftResult.contains(p1));
        assertFalse(topLeftResult.contains(p2));
        assertFalse(topLeftResult.contains(p3));
        assertFalse(topLeftResult.contains(p4));
        assertTrue(topLeftResult.contains(p5));

        assertEquals(1, bottomRightResult.size());
        assertFalse(bottomRightResult.contains(p1));
        assertFalse(bottomRightResult.contains(p2));
        assertFalse(bottomRightResult.contains(p3));
        assertTrue(bottomRightResult.contains(p4));
        assertFalse(bottomRightResult.contains(p5));

        assertEquals(0, noneResult.size());
        assertFalse(noneResult.contains(p1));
        assertFalse(noneResult.contains(p2));
        assertFalse(noneResult.contains(p3));
        assertFalse(noneResult.contains(p4));
        assertFalse(noneResult.contains(p5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullList() {
        new SimpleQuadTree<TestElement>(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyElements() {
        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Collections.emptyList(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullElements() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);
        new SimpleQuadTree<TestElement>(Arrays.asList(p1, p2, null, p3, p4), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPoints() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement();
        final TestElement p4 = new TestElement(2, 2);
        new SimpleQuadTree<TestElement>(Arrays.asList(p1, p2, null, p3, p4), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLowMaxElements() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);
        new SimpleQuadTree<TestElement>(Arrays.asList(p1, p2, p3, p4), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchTestNullList() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);
        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Arrays.asList(p1, p2, p3, p4), 1);
        tree.rangeQuery(null, new Rectangle(0, 0, 0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchTestNullRectangle() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);
        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Arrays.asList(p1, p2, p3, p4), 1);
        tree.rangeQuery(new ArrayList<>(), null);
    }

    @Test
    public void testSearchTestFilledList() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);
        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Arrays.asList(p1, p2, p3, p4), 1);
        final ArrayList<TestElement> result = new ArrayList<>(Collections.singletonList(p1));
        tree.rangeQuery(result, new Rectangle(0, 0, 0, 0));
        assertEquals(1, result.size());
        assertTrue(result.contains(p1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSearchTestUnmodifiableList() {
        final TestElement p1 = new TestElement(1, 1);
        final TestElement p2 = new TestElement(2, 1);
        final TestElement p3 = new TestElement(1, 2);
        final TestElement p4 = new TestElement(2, 2);
        final QuadTree<TestElement> tree = new SimpleQuadTree<>(Arrays.asList(p1, p2, p3, p4), 1);
        tree.rangeQuery(Collections.emptyList(), new Rectangle(0, 0, 4, 4));
    }

}
