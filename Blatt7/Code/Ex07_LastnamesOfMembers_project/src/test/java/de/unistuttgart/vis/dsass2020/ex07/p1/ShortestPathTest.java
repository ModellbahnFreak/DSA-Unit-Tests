package de.unistuttgart.vis.dsass2020.ex07.p1;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ShortestPathTest {

    @Test
    public void testInfinity() {
        assertTrue(Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY);
        assertTrue(Double.POSITIVE_INFINITY > 800);
        assertFalse(Double.POSITIVE_INFINITY > Double.POSITIVE_INFINITY);
    }

    @Test
    public void testLectureGraph() {
        final WeightedGraph<Character, Integer> graph = new WeightedGraph<>(6);
        graph.setNodeMetaData(0, 's');
        graph.setNodeMetaData(1, 'u');
        graph.setNodeMetaData(2, 'v');
        graph.setNodeMetaData(3, 'x');
        graph.setNodeMetaData(4, 'y');
        graph.setNodeMetaData(5, '-');
        graph.addEdge(new Edge<Integer>(0, 1, 10).setMetaData(0));
        graph.addEdge(new Edge<Integer>(0, 3, 5).setMetaData(1));
        graph.addEdge(new Edge<Integer>(1, 2, 1).setMetaData(2));
        graph.addEdge(new Edge<Integer>(1, 3, -2).setMetaData(3));
        graph.addEdge(new Edge<Integer>(2, 4, -5).setMetaData(4));
        graph.addEdge(new Edge<Integer>(3, 1, 3).setMetaData(5));
        graph.addEdge(new Edge<Integer>(3, 4, 2).setMetaData(6));
        graph.addEdge(new Edge<Integer>(4, 0, 7).setMetaData(7));
        graph.addEdge(new Edge<Integer>(4, 2, 6).setMetaData(8));
        graph.addEdge(new Edge<Integer>(5, 5, -10).setMetaData(9));
        final ShortestPath<Character, Integer> path = new ShortestPath<>(graph, 0);
        ShortestPathTest.assertDouble(0, path.distanceTo(0));
        ShortestPathTest.assertDouble(8, path.distanceTo(1));
        ShortestPathTest.assertDouble(9, path.distanceTo(2));
        ShortestPathTest.assertDouble(5, path.distanceTo(3));
        ShortestPathTest.assertDouble(4, path.distanceTo(4));
        ShortestPathTest.assertDouble(Double.POSITIVE_INFINITY, path.distanceTo(5));
        assertFalse(path.hasNegativeCycle());
        assertTrue(path.existsPathTo(0));
        assertTrue(path.existsPathTo(1));
        assertTrue(path.existsPathTo(2));
        assertTrue(path.existsPathTo(3));
        assertTrue(path.existsPathTo(4));
        assertFalse(path.existsPathTo(5));
        ShortestPathTest.assertPath(path, 0);
        ShortestPathTest.assertPath(path, 1, 1, 5);
        ShortestPathTest.assertPath(path, 2, 1, 5, 2);
        ShortestPathTest.assertPath(path, 3, 1);
        ShortestPathTest.assertPath(path, 4, 1, 5, 2, 4);
        assertNull(path.pathTo(5));
    }

    @Test
    public void testNegativeCycle() {
        final WeightedGraph<Character, Integer> graph = new WeightedGraph<>(3);
        graph.setNodeMetaData(0, 's');
        graph.setNodeMetaData(1, 'u');
        graph.setNodeMetaData(2, 'v');
        graph.addEdge(new Edge<Integer>(0, 1, 2).setMetaData(0));
        graph.addEdge(new Edge<Integer>(1, 2, 1).setMetaData(1));
        graph.addEdge(new Edge<Integer>(2, 0, -5).setMetaData(2));
        final ShortestPath<Character, Integer> path = new ShortestPath<>(graph, 0);
        assertTrue(path.hasNegativeCycle());
        assertTrue(path.existsPathTo(0));
        assertTrue(path.existsPathTo(1));
        assertTrue(path.existsPathTo(2));
        try {
            path.distanceTo(1);
            fail();
        } catch (final IllegalStateException e) {
            assertTrue(true);
        }
        try {
            path.distanceTo(1);
            fail();
        } catch (final IllegalStateException e) {
            assertTrue(true);
        }
        try {
            path.pathTo(1);
            fail();
        } catch (final IllegalStateException e) {
            assertTrue(true);
        }
        try {
            path.pathTo(2);
            fail();
        } catch (final IllegalStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLectureGraphIllegalArgument() {
        final WeightedGraph<Character, Integer> graph = new WeightedGraph<>(6);
        graph.setNodeMetaData(0, 's');
        graph.setNodeMetaData(1, 'u');
        graph.setNodeMetaData(2, 'v');
        graph.setNodeMetaData(3, 'x');
        graph.setNodeMetaData(4, 'y');
        graph.setNodeMetaData(5, '-');
        graph.addEdge(new Edge<Integer>(0, 1, 10).setMetaData(0));
        graph.addEdge(new Edge<Integer>(0, 3, 5).setMetaData(1));
        graph.addEdge(new Edge<Integer>(1, 2, 1).setMetaData(2));
        graph.addEdge(new Edge<Integer>(1, 3, -2).setMetaData(3));
        graph.addEdge(new Edge<Integer>(2, 4, -5).setMetaData(4));
        graph.addEdge(new Edge<Integer>(3, 1, 3).setMetaData(5));
        graph.addEdge(new Edge<Integer>(3, 4, 2).setMetaData(6));
        graph.addEdge(new Edge<Integer>(4, 0, 7).setMetaData(7));
        graph.addEdge(new Edge<Integer>(4, 2, 6).setMetaData(8));
        graph.addEdge(new Edge<Integer>(5, 5, -10).setMetaData(9));
        try {
            final ShortestPath<Character, Integer> path = new ShortestPath<>(null, 0);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            final ShortestPath<Character, Integer> path = new ShortestPath<>(graph, -1);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
        final ShortestPath<Character, Integer> path = new ShortestPath<>(graph, 0);
        try {
            path.distanceTo(6);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            path.pathTo(Integer.MIN_VALUE);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            path.existsPathTo(Integer.MAX_VALUE);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    private static void assertDouble(final double expected, final double actual) {
        assertTrue(expected == actual || Math.abs(expected - actual) < 0.0001);
    }

    private static void assertPath(final ShortestPath<?, Integer> path, final int to, final int... edges) {
        final Iterator<IEdge<Integer>> pathI = path.pathTo(to).iterator();
        for (final int e : edges) {
            assertEquals((Integer) e, pathI.next().getMetaData());
        }
        assertFalse(pathI.hasNext());
    }

}
