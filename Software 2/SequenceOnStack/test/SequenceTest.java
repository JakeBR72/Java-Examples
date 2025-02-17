import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

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

    // CONSTRUCTOR TEST CASES

    /**
     * Test Case: check constructor method (with zero elements).
     */
    @Test
    public void testConstructor0Elements() {
        Sequence<String> seq1 = this.createFromArgsTest();
        Sequence<String> seq1Expected = new Sequence1L<String>();
        assertEquals(seq1Expected, seq1);
    }

    // TEST CASES FOR ADD
    /**
     * Test Case: add to sequence containing one element.
     */
    @Test
    public void testAdd1() {
        Sequence<String> seq1 = this.createFromArgsTest("green");
        Sequence<String> seq1Expected = this.createFromArgsRef("red", "green");
        seq1.add(0, "red");
        assertEquals(seq1Expected, seq1);
    }

    /**
     * Test Case: add to sequence containing two elements.
     */
    @Test
    public void testAdd2() {
        Sequence<String> seq1 = this.createFromArgsTest("green", "blue");
        Sequence<String> seq1Expected = this.createFromArgsRef("red", "green",
                "blue");
        seq1.add(0, "red");
        assertEquals(seq1Expected, seq1);
    }

    /**
     * Test Case: add to end of sequence containing no elements.
     */
    @Test
    public void testAddToEndEmpty() {
        Sequence<String> seq1 = this.createFromArgsTest();
        Sequence<String> seq1Expected = this.createFromArgsRef("green");
        seq1.add(seq1.length(), "green");
        assertEquals(seq1Expected, seq1);
    }

    /**
     * Test Case: add to end of sequence containing two elements.
     */
    @Test
    public void testAddToEndTwo() {
        Sequence<String> seq1 = this.createFromArgsTest("red", "blue");
        Sequence<String> seq1Expected = this.createFromArgsRef("red", "blue",
                "green");
        seq1.add(seq1.length(), "green");
        assertEquals(seq1Expected, seq1);
    }

    //TEST CASES FOR REMOVE
    /**
     * Test Case: remove element from sequence containing 1 element.
     */
    @Test
    public void testRemoveFromOne() {
        Sequence<String> seq1 = this.createFromArgsTest("blue");
        Sequence<String> seq1Expected = this.createFromArgsRef();
        seq1.remove(0);
        assertEquals(seq1Expected, seq1);
    }

    /**
     * Test Case: remove element from sequence containing 2 elements.
     */
    @Test
    public void testRemoveFromTwo() {
        Sequence<String> seq1 = this.createFromArgsTest("green", "blue");
        Sequence<String> seq1Expected = this.createFromArgsRef("blue");
        seq1.remove(0);
        assertEquals(seq1Expected, seq1);
    }

    /**
     * Test Case: remove element from end of sequence containing 2 elements.
     */
    @Test
    public void testRemoveFromTwoEnd() {
        Sequence<String> seq1 = this.createFromArgsTest("green", "blue");
        Sequence<String> seq1Expected = this.createFromArgsRef("green");
        seq1.remove(seq1.length() - 1);
        assertEquals(seq1Expected, seq1);
    }

    //TEST CASES FOR LENGTH
    /**
     * Test Case: check length of sequence containing 0 elements.
     */
    @Test
    public void testLength0() {
        Sequence<String> seq1 = this.createFromArgsTest();
        int seq1Expected = 0;
        assertEquals(seq1Expected, seq1.length());
    }

    /**
     * Test Case: check length of sequence containing 1 elements.
     */
    @Test
    public void testLKength1() {
        Sequence<String> seq1 = this.createFromArgsTest("red");
        int seq1Expected = 1;
        assertEquals(seq1Expected, seq1.length());
    }

    /**
     * Test Case: check length of sequence containing 3 elements.
     */
    @Test
    public void testLength3() {
        Sequence<String> seq1 = this.createFromArgsTest("red", "green", "blue");
        int seq1Expected = 3;
        assertEquals(seq1Expected, seq1.length());
    }

}
