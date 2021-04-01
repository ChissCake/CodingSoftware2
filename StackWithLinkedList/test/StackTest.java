import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
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

    @Test
    public final void testPushEmpty() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("red");

        s.push("red");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPushNonEmpty() {
        Stack<String> s = this.createFromArgsTest("red");
        Stack<String> sExpected = this.createFromArgsRef("blue", "red");

        s.push("blue");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPopEmpty() {
        Stack<String> s = this.createFromArgsTest("red");
        Stack<String> sExpected = this.createFromArgsRef();

        String ans = s.pop();

        assertEquals(sExpected, s);
        assertEquals("red", ans);
    }

    @Test
    public final void testPopNonEmpty() {
        Stack<String> s = this.createFromArgsTest("blue", "red");
        Stack<String> sExpected = this.createFromArgsRef("red");

        String ans = s.pop();

        assertEquals(sExpected, s);
        assertEquals("blue", ans);
    }

    @Test
    public final void testLengthEmpty() {
        Stack<String> s = this.createFromArgsTest();

        int sLength = s.length();

        assertEquals(0, sLength);
    }

    @Test
    public final void testLengthNonEmpty() {
        Stack<String> s = this.createFromArgsTest("red", "blue");

        int sLength = s.length();

        assertEquals(2, sLength);
    }

}
