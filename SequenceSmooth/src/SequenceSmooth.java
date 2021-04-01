import components.sequence.Sequence;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        /*
         * if (s1.length() > 1) { for (int i = 0; i < s1.length() - 1; i++) {
         * s2.add(i, (s1.entry(i) + s1.entry(i + 1)) / 2);
         *
         * } }
         */
        if (s1.length() == 1) {
            s2.clear();
        } else {
            int elementOne = s1.remove(0);
            smooth(s1, s2);

            s2.add(0, elementOne / 2 + s1.entry(0) / 2
                    + (((elementOne % 2 + s1.entry(0) % 2)) / 2));
            s1.add(0, elementOne);
        }

    }

}
