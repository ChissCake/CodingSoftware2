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

    @Test
    public final void testConstructorEmpty() {
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

    @Test
    public final void testConstructorNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        Set<String> sExpected = this.createFromArgsRef("a", "b", "c");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Add 1 test
     */
    @Test
    public void add1Test() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("zero");

        s.add("zero");

        assertEquals(sExpected, s);
    }

    /**
     * Add multiple test
     */
    @Test
    public void addMultipleTest() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("zero", "one", "two",
                "three");

        s.add("zero");
        s.add("one");
        s.add("two");
        s.add("three");

        assertEquals(sExpected, s);
    }

    /**
     * Add non empty test
     */
    @Test
    public void addNonEmptyTest() {
        Set<String> s = this.createFromArgsTest("zero", "one", "three");
        Set<String> sExpected = this.createFromArgsRef("zero", "one", "two",
                "three");

        s.add("two");

        assertEquals(sExpected, s);
    }

    /**
     * Add multiple non empty test
     */
    @Test
    public void addMultipleNonEmptyTest() {
        Set<String> s = this.createFromArgsTest("zero", "three");
        Set<String> sExpected = this.createFromArgsRef("zero", "one", "two",
                "three");

        s.add("two");
        s.add("one");

        assertEquals(sExpected, s);
    }

    /**
     * Remove 1 test
     */
    @Test
    public void remove1Test() {
        Set<String> s = this.createFromArgsTest("zero");
        Set<String> sExpected = this.createFromArgsRef();

        s.remove("zero");

        assertEquals(sExpected, s);
    }

    /**
     * Remove multiple test
     */
    @Test
    public void removeMultipleTest() {
        Set<String> s = this.createFromArgsTest("zero", "one", "two", "three");
        Set<String> sExpected = this.createFromArgsRef("zero", "two");

        s.remove("one");
        s.remove("three");

        assertEquals(sExpected, s);
    }

    /**
     * Remove multiple test
     */
    @Test
    public void removeMultiple2Test() {
        Set<String> s = this.createFromArgsTest("zero", "one", "two", "three");
        Set<String> sExpected = this.createFromArgsRef();

        s.remove("one");
        s.remove("three");
        s.remove("two");
        s.remove("zero");

        assertEquals(sExpected, s);
    }

    /**
     * Remove Any test
     */
    @Test
    public void removeAnyTest1() {
        Set<String> s = this.createFromArgsTest("zero", "one", "two", "three");
        Set<String> sExpected = this.createFromArgsRef("zero", "one", "two",
                "three");

        String str = s.removeAny();
        assertEquals(sExpected.size() - 1, s.size());

        String strExpected = sExpected.remove(str);

        assertEquals(sExpected, s);
        assertEquals(strExpected, str);

    }

    /**
     * Remove Any test
     */
    @Test
    public void removeAnyTest2() {
        Set<String> s = this.createFromArgsTest("zero", "one", "two", "three");
        Set<String> sExpected = this.createFromArgsRef("zero", "one", "two",
                "three");

        String str = s.removeAny();
        String str2 = s.removeAny();

        assertEquals(sExpected.size() - 2, s.size());

        String strExpected = sExpected.remove(str);
        String str2Expected = sExpected.remove(str2);

        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
        assertEquals(str2Expected, str2Expected);

    }

    /**
     * Remove Any test
     */
    @Test
    public void removeAnyTest3() {
        Set<String> s = this.createFromArgsTest("one");
        Set<String> sExpected = this.createFromArgsRef();

        s.removeAny();

        assertEquals(sExpected, s);
    }

    @Test
    public void containsTrueCorrect() {
        Set<String> s = this.createFromArgsTest("zero", "one", "two", "three");
        Set<String> sExpected = this.createFromArgsRef("zero", "one", "two",
                "three");

        assertEquals(true, s.contains("zero"));
        assertEquals(sExpected, s);

    }

    @Test
    public void containsFalseCorrect() {
        Set<String> s = this.createFromArgsTest("zero", "one", "two", "three");
        Set<String> sExpected = this.createFromArgsRef("zero", "one", "two",
                "three");

        assertEquals(false, s.contains("four"));
        assertEquals(sExpected, s);

    }

    /**
     * Size test
     */
    @Test
    public void sizeTest1() {
        Set<String> s = this.createFromArgsTest("zero", "one", "two", "three");
        Set<String> sExpected = this.createFromArgsRef("zero", "one", "two",
                "three");

        assertEquals(sExpected, s);
        assertEquals(sExpected.size(), s.size());

    }

    /**
     * Size test
     */
    @Test
    public void sizeTest2() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        assertEquals(sExpected, s);
        assertEquals(sExpected.size(), s.size());

    }

}
