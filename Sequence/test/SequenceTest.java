import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Chris Zhao
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
     * Test Constructors
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.constructorTest();
        Sequence<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test add "red" to sequence = <>.
     */
    @Test
    public void testAddToEmpty() {
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef("red");
        s.add(0, "red");

        assertEquals(sExpected, s);

    }

    /**
     * Test add "red" to back with s = <"purple", "yellow", "blue", "green">.
     */
    @Test
    public void testAddToNonEmptyBack() {
        Sequence<String> s = this.createFromArgsTest("purple", "yellow", "blue",
                "green");
        Sequence<String> sExpected = this.createFromArgsRef("purple", "yellow",
                "blue", "green", "red");
        s.add(s.length(), "red");

        assertEquals(sExpected, s);

    }

    /**
     * Test remove on s = <"purple">.
     */
    @Test
    public void testRemoveLeavingEmpty() {
        Sequence<String> s = this.createFromArgsTest("purple");
        Sequence<String> sExpected = this.createFromArgsRef();

        s.remove(0);

        assertEquals(sExpected, s);
    }

    /**
     * Test remove on s = <"purple", "yellow">.
     */
    @Test
    public void testRemoveLeavingNonEmptyFront() {
        Sequence<String> s = this.createFromArgsTest("purple", "yellow");
        Sequence<String> sExpected = this.createFromArgsRef("yellow");

        s.remove(0);

        assertEquals(sExpected, s);
    }

    /**
     * Test remove on s = <"purple", "yellow">.
     */
    @Test
    public void testRemoveLeavingNonEmptyBack() {
        Sequence<String> s = this.createFromArgsTest("purple", "yellow");
        Sequence<String> sExpected = this.createFromArgsRef("purple");

        s.remove(s.length() - 1);

        assertEquals(sExpected, s);
    }

    /**
     * Test length of s = <>.
     */
    @Test
    public void testLengthEmpty() {
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef();
        int lengthExpected = 0;

        assertEquals(lengthExpected, s.length());
        assertEquals(sExpected, s);
    }

    /**
     * Test length of s = <"purple">.
     */
    @Test
    public void testLengthOne() {
        Sequence<String> s = this.createFromArgsTest("purple");
        Sequence<String> sExpected = this.createFromArgsRef("purple");
        int lengthExpected = 1;

        assertEquals(lengthExpected, s.length());
        assertEquals(sExpected, s);
    }

    /**
     * Test length of s = <"purple", "yellow", "blue", "green">.
     */
    @Test
    public void testLengthGreaterThanOne() {
        Sequence<String> s = this.createFromArgsTest("purple", "yellow", "blue",
                "green");
        Sequence<String> sExpected = this.createFromArgsRef("purple", "yellow",
                "blue", "green");
        int lengthExpected = 4;

        assertEquals(lengthExpected, s.length());
        assertEquals(sExpected, s);
    }

}
