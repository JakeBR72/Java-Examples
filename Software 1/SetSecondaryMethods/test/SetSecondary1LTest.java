import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

public final class SetSecondary1LTest {

    /**
     * Construct and return a {@code Set<String>} containing the given
     * {@code String}s.
     *
     * @param args
     *            the {@code String}s to put in the set
     * @return {@code Set<String>} of the given {@code String}s
     * @ensures createFromArgs = [the Set<String> of the given Strings]
     */
    private static Set<String> createFromArgs(String... args) {
        Set<String> set = new SetSecondary1L<String>();
        for (String s : args) {
            set.add(s);
        }
        return set;
    }

    @Test
    public void testSubset1() {
        Set<String> s1 = createFromArgs("one", "two");
        Set<String> s2 = createFromArgs("two", "three");
        boolean test = ((SetSecondary1L<String>) s1).isSubset(s2);
        assertTrue(!test);
    }

    @Test
    public void testSubset2() {
        Set<String> s1 = createFromArgs("one", "two");
        Set<String> s2 = createFromArgs("one");
        boolean test = ((SetSecondary1L<String>) s1).isSubset(s2);
        assertTrue(test);
    }

    @Test
    public void testSubset3() {
        Set<String> s1 = createFromArgs("one", "two", "three");
        Set<String> s2 = createFromArgs("one", "three");
        boolean test = ((SetSecondary1L<String>) s1).isSubset(s2);
        assertTrue(test);
    }

    @Test
    public void testSubset4() {
        Set<String> s1 = createFromArgs("one", "two", "three");
        Set<String> s2 = createFromArgs();
        boolean test = ((SetSecondary1L<String>) s1).isSubset(s2);
        assertTrue(test);
    }

    @Test
    public void testAddEmptyEmpty() {
        Set<String> s1 = createFromArgs();
        Set<String> s2 = createFromArgs();
        s1.add(s2);
        assertEquals("{}", s1.toString());
        assertEquals("{}", s2.toString());
    }

    @Test
    public void testAddNonEmptyEmpty() {
        Set<String> s1 = createFromArgs("one");
        Set<String> s2 = createFromArgs();
        s1.add(s2);
        assertEquals("{one}", s1.toString());
        assertEquals("{}", s2.toString());
    }

    @Test
    public void testAddEmptyNonEmpty() {
        Set<String> s1 = createFromArgs();
        Set<String> s2 = createFromArgs("one");
        s1.add(s2);
        assertEquals("{one}", s1.toString());
        assertEquals("{}", s2.toString());
    }

    @Test
    public void testAddNonEmptyNonEmptyNonSubset() {
        Set<String> s1 = createFromArgs("one", "two");
        Set<String> s2 = createFromArgs("two", "three");
        s1.add(s2);
        Set<String> s3 = createFromArgs();
        s3.add("one");
        s3.add("two");
        s3.add("three");
        assertEquals(s3, s1);
        assertEquals("{two}", s2.toString());
    }

    @Test
    public final void testRemoveEmptyEmpty() {
        Set<String> s1 = createFromArgs();
        Set<String> s2 = createFromArgs();
        Set<String> result = s1.remove(s2);
        assertEquals("{}", s1.toString());
        assertEquals("{}", s2.toString());
        assertEquals("{}", result.toString());
    }

    @Test
    public final void testRemoveNonEmptyEmpty() {
        Set<String> s1 = createFromArgs("one");
        Set<String> s2 = createFromArgs();
        Set<String> result = s1.remove(s2);
        assertEquals("{one}", s1.toString());
        assertEquals("{}", s2.toString());
        assertEquals("{}", result.toString());
    }

    @Test
    public final void testRemoveEmptyNonEmpty() {
        Set<String> s1 = createFromArgs();
        Set<String> s2 = createFromArgs("one");
        Set<String> result = s1.remove(s2);
        assertEquals("{}", s1.toString());
        assertEquals("{one}", s2.toString());
        assertEquals("{}", result.toString());
    }

    @Test
    public final void testRemoveNonEmptyNonEmptySubset() {
        Set<String> s1 = createFromArgs("one", "two");
        Set<String> s2 = createFromArgs("one");
        Set<String> result = s1.remove(s2);
        assertEquals("{two}", s1.toString());
        assertEquals("{one}", s2.toString());
        assertEquals("{one}", result.toString());
    }

    @Test
    public final void testRemoveNonEmptyNonEmptyNonSubset() {
        Set<String> s1 = createFromArgs("two");
        Set<String> s2 = createFromArgs("one");
        Set<String> result = s1.remove(s2);
        assertEquals("{two}", s1.toString());
        assertEquals("{one}", s2.toString());
        assertEquals("{}", result.toString());
    }

    @Test
    public final void testRemoveNonEmptyNonEmptyOverlapping() {
        Set<String> s1 = createFromArgs("one", "two");
        Set<String> s2 = createFromArgs("two", "three");
        Set<String> result = s1.remove(s2);
        assertEquals("{one}", s1.toString());
        Set<String> s3 = createFromArgs();
        s3.add("two");
        s3.add("three");
        assertEquals(s3, s2);
        assertEquals("{two}", result.toString());
    }

    @Test
    public final void testRemoveNonEmptyNonEmptyOverlapping2() {
        Set<String> s1 = createFromArgs("one", "two");
        Set<String> s2 = createFromArgs("three", "two");
        Set<String> result = s1.remove(s2);
        assertEquals("{one}", s1.toString());
        Set<String> s3 = createFromArgs();
        s3.add("three");
        s3.add("two");
        assertEquals(s3, s2);
        assertEquals("{two}", result.toString());
    }
}