import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;
import components.stack.Stack1L;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Jacob Ruth
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // CONSTRUCTOR TEST CASES

    /**
     * Test Case: check constructor method (with zero elements).
     */
    @Test
    public void testConstructor0Elements() {
        Stack<String> stack1 = this.createFromArgsTest();
        Stack<String> stack1Expected = new Stack1L<String>();
        assertEquals(stack1Expected, stack1);
    }

    // TEST CASES FOR push
    /**
     * Test Case: push to Stack containing one element.
     */
    @Test
    public void testpush1() {
        Stack<String> stack1 = this.createFromArgsTest("green");
        Stack<String> stack1Expected = this.createFromArgsRef("red", "green");
        stack1.push("red");
        assertEquals(stack1Expected, stack1);
    }

    /**
     * Test Case: push to Stack containing two elements.
     */
    @Test
    public void testpush2() {
        Stack<String> stack1 = this.createFromArgsTest("green", "blue");
        Stack<String> stack1Expected = this.createFromArgsRef("red", "green",
                "blue");
        stack1.push("red");
        assertEquals(stack1Expected, stack1);
    }

    /**
     * Test Case: push to end of Stack containing no elements.
     */
    @Test
    public void testpushToEndEmpty() {
        Stack<String> stack1 = this.createFromArgsTest();
        Stack<String> stack1Expected = this.createFromArgsRef("green");
        stack1.push("green");
        assertEquals(stack1Expected, stack1);
    }

    /**
     * Test Case: push to end of Stack containing two elements.
     */
    @Test
    public void testpushToEndTwo() {
        Stack<String> stack1 = this.createFromArgsTest("red", "blue");
        Stack<String> stack1Expected = this.createFromArgsRef("red", "blue",
                "green");
        stack1.push("green");
        assertEquals(stack1Expected, stack1);
    }

    //TEST CASES FOR pop
    /**
     * Test Case: pop element from Stack containing 1 element.
     */
    @Test
    public void testpopFromOne() {
        Stack<String> stack1 = this.createFromArgsTest("blue");
        Stack<String> stack1Expected = this.createFromArgsRef();
        stack1.pop();
        assertEquals(stack1Expected, stack1);
    }

    /**
     * Test Case: pop element from Stack containing 2 elements.
     */
    @Test
    public void testpopFromTwo() {
        Stack<String> stack1 = this.createFromArgsTest("green", "blue");
        Stack<String> stack1Expected = this.createFromArgsRef("blue");
        stack1.pop();
        assertEquals(stack1Expected, stack1);
    }

    /**
     * Test Case: pop element from end of Stack containing 2 elements.
     */
    @Test
    public void testpopFromTwoEnd() {
        Stack<String> stack1 = this.createFromArgsTest("green", "blue");
        Stack<String> stack1Expected = this.createFromArgsRef("blue");
        stack1.pop();
        assertEquals(stack1Expected, stack1);
    }

    //TEST CASES FOR LENGTH
    /**
     * Test Case: check length of Stack containing 0 elements.
     */
    @Test
    public void testLength0() {
        Stack<String> stack1 = this.createFromArgsTest();
        int stack1Expected = 0;
        assertEquals(stack1Expected, stack1.length());
    }

    /**
     * Test Case: check length of Stack containing 1 elements.
     */
    @Test
    public void testLKength1() {
        Stack<String> stack1 = this.createFromArgsTest("red");
        int stack1Expected = 1;
        assertEquals(stack1Expected, stack1.length());
    }

    /**
     * Test Case: check length of Stack containing 3 elements.
     */
    @Test
    public void testLength3() {
        Stack<String> stack1 = this.createFromArgsTest("red", "green", "blue");
        int stack1Expected = 3;
        assertEquals(stack1Expected, stack1.length());
    }

}