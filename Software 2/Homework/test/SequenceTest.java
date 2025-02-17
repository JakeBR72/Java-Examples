import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     * Test Case: Add to Empty Sequence.
     */
    @Test
    public void testAddEmpty() {
        Sequence<String> seq1 = this.createFromArgsTest();
        Sequence<String> seq1Expected = this.createFromArgsRef("red");
        seq1.add(0, "red");
        assertEquals(seq1Expected, seq1);
    }

    /**
     * Test Case: Add to Sequence Containing One Element.
     */
    @Test
    public void testAdd1() {
        Sequence<String> seq1 = this.createFromArgsTest("green");
        Sequence<String> seq1Expected = this.createFromArgsRef("red", "green");
        seq1.add(0, "red");
        assertEquals(seq1Expected, seq1);
    }

    /**
     * Test Case: Add to Sequence Containing Two Elements.
     */
    @Test
    public void testAdd2() {
        Sequence<String> seq1 = this.createFromArgsTest("green", "blue");
        Sequence<String> seq1Expected = this.createFromArgsRef("red", "green",
                "blue");
        seq1.add(0, "red");
        assertEquals(seq1Expected, seq1);
    }
}