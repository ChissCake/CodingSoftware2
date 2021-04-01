import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.random.Random;
import components.random.Random1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Chris Zhao, Ko Lim
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
     * Pseudo-random number generator.
     */
    private static final Random GENERATOR = new Random1L();

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /**
     * No argument constructor test
     */
    @Test
    public void noArgsConstructorTest() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(nExpected, n);
    }

    /**
     * Integer argument constructor test, i = 0
     */
    @Test
    public void intConstructorTestZero() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(nExpected, n);
    }

    /**
     * Integer argument constructor test, i = Integer.MAX_VALUE
     */
    @Test
    public void intConstructorTestMaxInt() {
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE);

        assertEquals(nExpected, n);
    }

    /**
     * Integer argument constructor test, i = routine
     */
    @Test
    public void intConstructorTestAllRoutine() {
        int i = Math.abs(GENERATOR.nextInt());
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);

        assertEquals(nExpected, n);
    }

    /**
     * String argument constructor, s = "0"
     */
    @Test
    public void stringConstrutorTestZero() {
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(nExpected, n);
    }

    /**
     * String argument constructor, s = 9999999999
     */
    @Test
    public void stringConstrutorTestNonZero() {
        NaturalNumber n = this.constructorTest("9999999999");
        NaturalNumber nExpected = this.constructorRef("9999999999");

        assertEquals(nExpected, n);
    }

    /**
     * String argument constructor, s = routine
     */
    @Test
    public void stringConstrutorTestNonZeroRoutine() {
        String s = String.valueOf(Math.abs(GENERATOR.nextInt()));
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);

        assertEquals(nExpected, n);
    }

    /**
     * NaturalNumber argument constructor, n = 0
     */
    @Test
    public void nnConstructorZero() {
        NaturalNumber natNum = new NaturalNumber1L(
                Math.abs(GENERATOR.nextInt()));
        NaturalNumber n = this.constructorTest(natNum);
        NaturalNumber nExpected = this.constructorRef(natNum);

        assertEquals(nExpected, n);
    }

    /**
     * NaturalNumber argument constructor, n = 0
     */
    @Test
    public void nnConstructorTestNonZeroRoutine() {
        NaturalNumber natNum = new NaturalNumber1L(10);
        NaturalNumber n = this.constructorTest(natNum);
        NaturalNumber nExpected = this.constructorRef(natNum);

        assertEquals(nExpected, n);
    }

    /**
     * test multiplyBy10 when this = 0, k = 0
     */
    @Test
    public void testMultBy10_1() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        n.multiplyBy10(0);

        assertEquals(nExpected, n);
    }

    /**
     * test multiplyBy10 when this = 0, k = 5
     */
    @Test
    public void testMultBy10_2() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(5);

        n.multiplyBy10(5);

        assertEquals(nExpected, n);
    }

    /**
     * test multiplyBy10 when this = 5, k = 0
     */
    @Test
    public void testMultBy10_3() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(50);

        n.multiplyBy10(0);

        assertEquals(nExpected, n);
    }

    /**
     * test multiplyBy10 when this = 5, k = 5
     */
    @Test
    public void testMultBy10_4() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(55);

        n.multiplyBy10(5);

        assertEquals(nExpected, n);
    }

    /**
     * test divideBy10 with this = 0
     */
    @Test
    public void testDivideBy10_1() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        int rem;
        int remExpected = 0;

        rem = n.divideBy10();

        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * test divideBy10 with this = 10
     */
    @Test
    public void testDivideBy10_2() {
        NaturalNumber n = this.constructorTest(10);
        NaturalNumber nExpected = this.constructorRef(1);
        int rem;
        int remExpected = 0;

        rem = n.divideBy10();

        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * test divideBy10 with this = 15
     */
    @Test
    public void testDivideBy10_3() {
        NaturalNumber n = this.constructorTest(15);
        NaturalNumber nExpected = this.constructorRef(1);
        int rem;
        int remExpected = 5;

        rem = n.divideBy10();

        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * Test isZero when this = 0
     */
    @Test
    public void testIsZeroWhenTrue() {
        NaturalNumber n = this.constructorTest(0);

        assertEquals(true, n.isZero());
    }

    /**
     * Test isZero when this = 1
     */
    @Test
    public void testIsZeroWhenFalse() {
        NaturalNumber n = this.constructorTest(1);

        assertEquals(false, n.isZero());
    }
}
