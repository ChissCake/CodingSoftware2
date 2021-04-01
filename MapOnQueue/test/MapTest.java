import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Chris Zhao
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * test constructor.
     */
    @Test
    public final void testConstructor() {
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        assertEquals(s, sExpected);

    }

    /**
     * Test for add non-Empty.
     */
    @Test
    public final void testAddNonEmpty() {
        Map<String, String> s = this.createFromArgsTest("red", "blue", "white",
                "black");
        Map<String, String> sExpected = this.createFromArgsRef("red", "blue",
                "white", "black", "good", "bad");

        s.add("good", "bad");
        boolean result = false;
        for (Pair<String, String> x : sExpected) {
            if (s.hasKey(x.key()) && s.hasValue(x.value())
                    && s.key(x.value()).equals(x.key())) {
                result = true;
            }
        }

        assertEquals(true, result);
    }

    /**
     * Test for add Remove.
     */
    @Test
    public final void testRemove() {
        Map<String, String> s = this.createFromArgsTest("red", "blue", "white",
                "black");
        Map<String, String> sExpected = this.createFromArgsRef("white",
                "black");

        s.remove("red");
        assertEquals(false, s.hasKey("red"));
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
        Map<String, String> s = this.createFromArgsTest("red", "blue", "white",
                "black");
        Map<String, String> sExpected = this.createFromArgsTest("red", "blue",
                "white", "black");

        /*
         * The call
         */
        Pair<String, String> p = s.removeAny();

        /*
         * Evaluation
         */
        assertEquals(true, sExpected.hasKey(p.key()));
        sExpected.remove(p.key());
        assertEquals(sExpected, s);

    }

    /**
     * Test for Value.
     */
    @Test
    public final void testValue() {
        Map<String, String> s = this.createFromArgsTest("red", "blue", "white",
                "black");

        assertEquals("blue", s.value("red"));
    }

    /**
     *
     * Test for Has-key.
     */
    @Test
    public final void testHasKey() {
        Map<String, String> s = this.createFromArgsTest("red", "blue", "white",
                "black");
        assertEquals(true, s.hasKey("red"));
    }

    /**
     * Test for Size.
     */
    @Test
    public final void testSize() {
        Map<String, String> s = this.createFromArgsTest("red", "blue", "white",
                "black", "good", "bad");
        int sizeTest = s.size();
        int sizeRef = 3;
        assertEquals(sizeTest, sizeRef);
    }
}
