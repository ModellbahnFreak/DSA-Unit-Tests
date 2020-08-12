package de.unistuttgart.dsass2020.ex10.p3;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

public class ClosedHashMapTest {

    @Test
    public void testPutGet() throws NoSuchFieldException, IllegalAccessException {
        final ClosedHashMap<String> m = new ClosedHashMap<>(13, 11);
        final List<String> insertItems = Arrays.asList("Hallo", "Welt", "Wow", "bla", "", "LONG", "Another word");
        final List<String> storageOrder = Arrays.asList("bla", "", "Wow", "Welt", null, null, null, null, null, null, "Another word", null, "LONG");
        assertNull(m.put(insertItems.get(0).hashCode(), insertItems.get(0)));
        assertNull(m.put(insertItems.get(1).hashCode(), insertItems.get(1)));
        assertNull(m.put(insertItems.get(2).hashCode(), insertItems.get(2)));
        assertEquals("Hallo", m.put(insertItems.get(0).hashCode(), insertItems.get(3)));
        assertNull(m.put(-12, insertItems.get(4)));
        assertNull(m.put(1, insertItems.get(5)));
        assertNull(m.put(14, insertItems.get(6)));

        assertNotEquals(insertItems.get(0), m.get(insertItems.get(0).hashCode()));
        assertEquals(insertItems.get(1), m.get(insertItems.get(1).hashCode()));
        assertEquals(insertItems.get(2), m.get(insertItems.get(2).hashCode()));
        assertEquals(insertItems.get(3), m.get(insertItems.get(0).hashCode()));
        assertNull(m.get(insertItems.get(3).hashCode()));
        assertEquals(insertItems.get(4), m.get(-12));
        assertEquals(insertItems.get(5), m.get(1));
        assertEquals(insertItems.get(6), m.get(14));

        final Field f = AbstractHashMap.class.getDeclaredField("map");
        f.setAccessible(true);
        final AbstractHashMap.KeyValuePair[] map = (AbstractHashMap.KeyValuePair[]) f.get(m);
        for (int i = 0; i < 13; i++) {
            if (storageOrder.get(i) == null) {
                assertNull(map[i]);
            } else {
                assertEquals(storageOrder.get(i), map[i].getValue());
            }
        }

        final Iterator<AbstractHashMap.KeyValuePair<String>> it = m.iterator();
        final Iterator<AbstractHashMap.KeyValuePair<String>> it2 = m.iterator();
        it2.next();
        storageOrder.stream().filter(Objects::nonNull).forEachOrdered(x -> {
            assertEquals(x, it.next().getValue());
            it.remove();
        });

        try {
            it2.next();
            fail();
        } catch (final ConcurrentModificationException e) {
            assertTrue(true);
        }

        assertNull(m.get(insertItems.get(1).hashCode()));
        assertNull(m.get(insertItems.get(2).hashCode()));
        assertNull(m.get(insertItems.get(0).hashCode()));
        assertNull(m.get(insertItems.get(3).hashCode()));
        assertNull(m.get(-12));
        assertNull(m.get(1));
        assertNull(m.get(14));
    }

    @Test
    public void testIllegalGetPut() {
        try {
            final ClosedHashMap<String> m = new ClosedHashMap<>(-1, 11);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            final ClosedHashMap<String> m = new ClosedHashMap<>(2100, -10);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            final ClosedHashMap<String> m = new ClosedHashMap<>(10000, 10000);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }
        final ClosedHashMap<String> m = new ClosedHashMap<>(3, 1);

        try {
            m.put(null, "Hi");
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            m.put(null, "Hi");
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            m.containsKey(null);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            m.get(null);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            m.remove(null);
            fail();
        } catch (final IllegalArgumentException e) {
            assertTrue(true);
        }

        assertNull(m.put(0, "0"));
        assertNull(m.put(13, "1"));
        assertNull(m.put(26, "2"));

        assertEquals("0", m.put(0, "3"));

        assertEquals("3", m.get(0));
        assertEquals("1", m.get(13));
        assertEquals("2", m.get(26));

        try {
            m.put(1, "4");
            fail();
        } catch (final IllegalStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testConcurrentMod() {
        final ClosedHashMap<String> m = new ClosedHashMap<>(13, 11);
        final List<String> insertItems = Arrays.asList("Hallo", "Welt", "Wow", "bla", "", "LONG", "Another word", "Empty");
        assertNull(m.put(insertItems.get(0).hashCode(), insertItems.get(0)));
        assertNull(m.put(insertItems.get(1).hashCode(), insertItems.get(1)));
        assertNull(m.put(insertItems.get(2).hashCode(), insertItems.get(2)));
        assertEquals("Hallo", m.put(insertItems.get(0).hashCode(), insertItems.get(3)));
        assertNull(m.put(-12, insertItems.get(4)));
        assertNull(m.put(1, insertItems.get(5)));
        assertNull(m.put(14, insertItems.get(6)));

        final Iterator<AbstractHashMap.KeyValuePair<String>> it = m.iterator();
        assertEquals("bla", it.next().getValue());
        m.put(0, insertItems.get(7));
        try {
            it.next();
            fail();
        } catch (final ConcurrentModificationException e) {
            assertTrue(true);
        }
        assertEquals(insertItems.get(7), m.get(0));

        m.remove(insertItems.get(1).hashCode());
        m.remove(insertItems.get(2).hashCode());
        m.remove(insertItems.get(0).hashCode());
        m.remove(-12);
        m.remove(1);
        m.remove(14);
        m.remove(0);

        assertNull(m.get(0));
        assertNull(m.get(insertItems.get(1).hashCode()));
        assertNull(m.get(insertItems.get(2).hashCode()));
        assertNull(m.get(insertItems.get(0).hashCode()));
        assertNull(m.get(-12));
        assertNull(m.get(1));
        assertNull(m.get(14));
    }

    @Test
    public void iHopeThisResultsInAnError() {
        final ClosedHashMap<String> testMap = new ClosedHashMap<>(50, 5);
        testMap.put(5, "testPos1");
        testMap.put(55, "againPos1");
        testMap.put(-35, "testPos2");
        testMap.put(105, "alsoPos1");
        testMap.put(115, "thisShouldBeMovedBack");
        System.out.println(testMap);
        testMap.remove(5);
        System.out.println(testMap);
        assertNotEquals(null, testMap.get(-35));
    }

}
