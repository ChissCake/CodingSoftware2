import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Chris Zhao, Ko Lim
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "yellow", "red", "purple");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "yellow", "red", "purple", "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmptyToNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "yellow", "red", "purple", "green");
        m.add("blue");
        m.add("yellow");
        m.add("red");
        m.add("purple");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "yellow", "red", "purple", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "yellow", "red", "purple", "green");

        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstLeavingNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "yellow", "red", "purple", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "yellow", "red", "purple", "green");
        String ans = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(ans, "blue");
    }

    @Test
    public final void testRemoveFirstLeavingEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String ans = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(ans, "blue");
    }

    @Test
    public final void testIsInInsertionModeTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "yellow", "red", "purple", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "yellow", "red", "purple", "green");

        assertEquals(mExpected, m);
        assertEquals(true, m.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "yellow", "red", "purple", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "yellow", "red", "purple", "green");

        assertEquals(mExpected, m);
        assertEquals(false, m.isInInsertionMode());
    }

    @Test
    public final void testOrder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(ORDER, m.order());
    }
    //Insertion mode size 0 & size >0, Extraction Mode size 0 & size > 0

    @Test
    public final void testSizeInsertionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(mExpected, m);
        assertEquals(0, m.size());
    }

    @Test
    public final void testSizeInsertionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "blue",
                "yellow", "red", "purple", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "yellow", "red", "purple", "green");

        assertEquals(mExpected, m);
        assertEquals(5, m.size());
    }

    @Test
    public final void testSizeExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(0, m.size());
    }

    @Test
    public final void testSizeExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "yellow", "red", "purple", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "yellow", "red", "purple", "green");

        assertEquals(mExpected, m);
        assertEquals(5, m.size());
    }
}
