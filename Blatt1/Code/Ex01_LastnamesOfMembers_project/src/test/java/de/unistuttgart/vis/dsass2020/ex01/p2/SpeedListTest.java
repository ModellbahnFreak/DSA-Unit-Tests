package de.unistuttgart.vis.dsass2020.ex01.p2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

//import de.unistuttgart.vis.dsass2020.ex01.p2.SpeedList;

public class SpeedListTest {

    @Test
    public void testSpeedList() {
        final SpeedList<Integer> ints = new SpeedList<>(10);
        for (int i = 0; i < 100; i++) {
            ints.append(i);
        }
        assertEquals(99, (long) ints.getElementAt(99));
    }
}
