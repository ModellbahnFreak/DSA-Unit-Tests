package de.unistuttgart.vis.dsass2020.ex00.p3;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * You should use this class to test your class {@link TextStorage}, using
 * JUnit assertions like {@link Assert#assertEquals(Object, Object)}. For
 * additional information on how to test your code with JUnit visit
 * http://www.vogella.com/tutorials/JUnit/article.html
 */
public class TextStorageTest {

    @Test
    public void testTextStorageBasic() {
        final String in = "Hallo Welt";
        final TextStorage<String> text = new TextStorage<>();
        text.set(in);
        assertEquals(in, text.get());
    }

    @Test
    public void testTextStorageRandomTest() {
        final TextStorage<String> text = new TextStorage<>();
        final TextStorage<String> compare = new TextStorage<>();
        String testString = TextStorageTest.randomString();
        text.set(testString);
        compare.set(testString);
        assertEquals(testString.length(), text.getNumberOfMatchingCharacters(compare));
        assertEquals(testString.length(), compare.getNumberOfMatchingCharacters(text));
        assertArrayEquals(text.get().toCharArray(), compare.get().toCharArray());
        assertEquals(text.get(), compare.get());
    }

    @Test
    public void testTextStorageEqualText() {
        final TextStorage<String> text = new TextStorage<>();
        final TextStorage<String> compare = new TextStorage<>();

    }

    private static String randomString() {
        final StringBuilder result = new StringBuilder();
        final int length = (int) (Math.random() * 1000);
        for (int i = 0; i < length; i++) {
            result.append((char) (Math.random() * 128));
        }
        return result.toString();
    }

}
