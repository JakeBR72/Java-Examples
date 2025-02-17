import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Gautham Sivakumar, Jacob Ruth, Taha Topiwala
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert!set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert!set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TEST CASES
    @Test
    public final void testConstructorEmpty() {
        Set<String> orig = this.createFromArgsTest();
        Set<String> expect = this.createFromArgsRef();
        assertEquals(orig, expect);
    }

    @Test
    public final void testConstructorFilled() {
        Set<String> orig = this.createFromArgsTest("a", "b");
        Set<String> expect = this.createFromArgsRef("a", "b");
        assertEquals(orig, expect);
    }

    @Test
    public final void testAddToEmpty() {
        Set<String> orig = this.createFromArgsTest();
        Set<String> expect = this.createFromArgsRef("a");
        orig.add("a");
        assertEquals(orig, expect);
    }

    @Test
    public final void testAddToNonEmptyFront() {
        Set<String> orig = this.createFromArgsTest("b");
        Set<String> expect = this.createFromArgsRef("a", "b");
        orig.add("a");
        assertEquals(orig, expect);
    }

    @Test
    public final void testAddToNonEmptyEnd() {
        Set<String> orig = this.createFromArgsTest("a");
        Set<String> expect = this.createFromArgsRef("a", "b");
        orig.add("b");
        assertEquals(orig, expect);
    }

    @Test
    public final void testAddToMiddle() {
        Set<String> orig = this.createFromArgsTest("a", "c");
        Set<String> expect = this.createFromArgsRef("a", "b", "c");
        orig.add("b");
        assertEquals(orig, expect);
    }

    @Test
    public final void testRemoveToEmpty() {
        Set<String> orig = this.createFromArgsTest("a");
        Set<String> expect = this.createFromArgsRef();
        orig.remove("a");
        assertEquals(orig, expect);
    }

    @Test
    public final void testRemoveToNonEmpty() {
        Set<String> orig = this.createFromArgsTest("a", "b");
        Set<String> expect = this.createFromArgsRef("b");
        orig.remove("a");
        assertEquals(orig, expect);
    }

    @Test
    public final void testRemoveFromThreeElementsBeginning() {
        Set<String> orig = this.createFromArgsTest("a", "b", "c");
        Set<String> expect = this.createFromArgsRef("b", "c");
        String removed = orig.removeAny();
        expect.remove(removed);
        assertEquals(orig, expect);
    }

    @Test
    public final void testRemoveFromThreeElementsMiddle() {
        Set<String> orig = this.createFromArgsTest("a", "b", "c");
        Set<String> expect = this.createFromArgsRef("a", "c");
        String removed = orig.removeAny();
        expect.remove(removed);
        assertEquals(orig, expect);
    }

    @Test
    public final void testRemoveFromThreeElementsEnd() {
        Set<String> orig = this.createFromArgsTest("a", "b", "c");
        Set<String> expect = this.createFromArgsRef("a", "b");
        String removed = orig.removeAny();
        expect.remove(removed);
        assertEquals(orig, expect);
    }

    @Test
    public final void testRemoveAnyToEmpty() {
        Set<String> orig = this.createFromArgsTest("a");
        Set<String> expect = this.createFromArgsRef();
        orig.removeAny();
        assertEquals(orig, expect);
    }

    @Test
    public final void testRemoveAnyToNonEmpty() {
        Set<String> orig = this.createFromArgsTest("a", "b", "c");
        Set<String> expect = this.createFromArgsRef("b", "c");
        orig.removeAny();
        assertEquals(orig, expect);
    }

    @Test
    public final void testContainsEmpty() {
        Set<String> orig = this.createFromArgsTest();
        boolean expect = orig.contains("a");
        assertEquals(false, expect);
    }

    @Test
    public final void testContainsSingleElement() {
        Set<String> orig = this.createFromArgsTest("a");
        boolean expect = orig.contains("a");
        assertEquals(true, expect);
    }

    @Test
    public final void testContainsInMultiElement() {
        Set<String> orig = this.createFromArgsTest("a", "b");
        boolean expect = orig.contains("b");
        assertEquals(true, expect);
    }

    @Test
    public final void testSizeOfEmpty() {
        Set<String> orig = this.createFromArgsTest();
        int expect = orig.size();
        assertEquals(0, expect);
    }

    @Test
    public final void testSizeOfSingle() {
        Set<String> orig = this.createFromArgsTest("a");
        int expect = orig.size();
        assertEquals(1, expect);
    }

    @Test
    public final void testSizeOfTriple() {
        Set<String> orig = this.createFromArgsTest("a", "b", "c");
        int expect = orig.size();
        assertEquals(3, expect);
    }
}
