package de.unistuttgart.vis.dsass2020.ex05.p3;

import de.unistuttgart.vis.dsass2020.ex05.p1.Rectangle;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CollisionMapTest {

    @Test
    public void testCreateCollisionMap() {
        final Set<Rectangle> rects = Set.of(new Rectangle(0, 0, 1, 1), new Rectangle(99, 99, 1, 1), new Rectangle(99,
                        0, 1, 1),
                new Rectangle(0, 99, 1, 1));
        final CollisionMap map = new CollisionMap(rects);
        assertTrue(map.collide(new Rectangle(0, 0, 100, 100)));
        assertFalse(map.collide(new Rectangle(2, 2, 96, 96)));
    }

    @Test
    public void testCollisionCandidates() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        final Rectangle r2 = new Rectangle(50, 50, 100, 100);
        final Rectangle r3 = new Rectangle(105, 105, 90, 90);
        //final Rectangle r4 = new Rectangle(100, 100, 100, 100);
        final Rectangle r5 = new Rectangle(106, 106, 96, 96);
        //final Rectangle r6 = new Rectangle(3000, 3000, 1, 1);
        final Rectangle r7 = new Rectangle(100, 150, 100, 100);
        final Rectangle r8 = new Rectangle(100, 150.01f, 100, 100);
        final Rectangle r9 = new Rectangle(300, 300, 50, 50);
        final Set<Rectangle> rects = Set.of(r1, r2, r3, r5, r7, r8, r9);
        final CollisionMap map = new CollisionMap(rects, 150, 150);
        final Method getCandidates = CollisionMap.class.getDeclaredMethod("getCollisionCandidates", Rectangle.class);
        getCandidates.setAccessible(true);
        final Rectangle rect = new Rectangle(100, 100, 4, 4);
        final Set<Rectangle> colCandidates = (Set<Rectangle>) getCandidates.invoke(map, rect);
        assertEquals(3, colCandidates.size());
        assertTrue(colCandidates.contains(r1));
        assertTrue(colCandidates.contains(r2));
        assertTrue(colCandidates.contains(r3));
        //assertTrue(colCandidates.contains(r4));
        assertTrue(rect.intersects(r1));
        assertTrue(rect.intersects(r2));
        assertFalse(rect.intersects(r3));
        //assertTrue(rect.intersects(r4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullList() {
        new CollisionMap(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullListElement() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        final Set<Rectangle> testSet = new HashSet<>();
        testSet.add(r1);
        testSet.add(null);
        new CollisionMap(testSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyList() {
        new CollisionMap(Collections.emptySet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroGridSizeX() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        new CollisionMap(Set.of(r1), 0, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroGridSizeY() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        new CollisionMap(Set.of(r1), 100, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCollisionNull() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        final CollisionMap map = new CollisionMap(Set.of(r1));
        map.collide(null);
    }

    @Test
    public void testCollisionOutside() {
        final Rectangle r1 = new Rectangle(100, 100, 100, 100);
        final CollisionMap map = new CollisionMap(Set.of(r1));
        assertFalse(map.collide(new Rectangle(3000, 3000, 1, 1)));
    }

}
