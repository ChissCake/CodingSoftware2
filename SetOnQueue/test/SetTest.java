import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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
            assert !set.contains(
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
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for add, remove, removeAny, contains, and size

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);

    }

    /**
     * Add "red" to s = <>.
     */
    @Test
    public final void testAddEmpty() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("red");

        s.add("red");

        assertEquals(sExpected, s);
    }

    /**
     * Add "red" to s = <"orange", "yellow", "green">.
     */
    @Test
    public final void testAddNonEmpty() {
        Set<String> s = this.createFromArgsTest("orange", "yellow", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "orange",
                "yellow", "green");

        s.add("red");

        assertEquals(sExpected, s);
    }

    /**
     * Removes "red" from s = <"red", "orange", "yellow", "green">.
     */
    @Test
    public final void testRemove() {
        Set<String> s = this.createFromArgsTest("red", "orange", "yellow",
                "green");
        Set<String> sExpected = this.createFromArgsRef("orange", "yellow",
                "green");

        s.remove("red");

        assertEquals(sExpected, s);
    }

    /**
     * Test remove Any from s = <"red", "orange", "yellow", "green">.
     */
    @Test
    public final void testRemoveAny() {
        /*
         * Setup
         */
        Set<String> s = this.createFromArgsTest("red", "orange", "yellow",
                "green");
        Set<String> sExpected = this.createFromArgsRef("red", "orange",
                "yellow", "green");
        /*
         * The call
         */
        String str = s.removeAny();

        /*
         * Evaluation
         */
        assertEquals(true, sExpected.contains(str));
        sExpected.remove(str);
        assertEquals(sExpected, s);

    }

    /**
     * Test Checks if "red" is contained in s = <"red", "orange", "yellow",
     * "green">.
     */
    @Test
    public final void testContainsTrue() {
        Set<String> s = this.createFromArgsTest("red", "orange", "yellow",
                "green");
        Set<String> sExpected = this.createFromArgsRef("red", "orange",
                "yellow", "green");

        assertEquals(true, s.contains("red"));
        assertEquals(sExpected, s);
    }

    /**
     * Test Checks if "red" is contained in s = <>.
     */
    @Test
    public final void testContainsFalse() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        assertEquals(false, s.contains("red"));
        assertEquals(sExpected, s);
    }

    /**
     * Test Checks size of set = <>.
     */
    @Test
    public final void testSizeEmpty() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        assertEquals(0, s.size());
        assertEquals(sExpected, s);
    }

    /**
     * Test Checks size of set = <"red", "orange", "yellow", "green">.
     */
    @Test
    public final void testSizeNonEmpty() {
        Set<String> s = this.createFromArgsTest("red", "orange", "yellow",
                "green");
        Set<String> sExpected = this.createFromArgsRef("red", "orange",
                "yellow", "green");

        assertEquals(4, s.size());
        assertEquals(sExpected, s);
    }
}
