import java.io.Serializable;
import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Read a text file and generate an html file that sorts the words within the
 * textfile alphabetically with the number of each word occuring
 *
 * @author Chris Zhao
 *
 */
public final class WordCount {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCount() {
    }

    @SuppressWarnings("serial")
    private static class StringLT implements Comparator<String>, Serializable {

        @Override
        public int compare(String str1, String str2) {
            //Allows for the comparison of strings
            return str1.compareToIgnoreCase(str2);
        }
    }

    /**
     * Out puts the "opening" tags for the words counted HTML file. The expected
     * elements created by this method are:
     *
     *
     * <html> <head> <title>Words Counted in {@code inputFileName}</title>
     * </head> <body>
     * <h2>Words Counted in {@code inputFileName}</h2>
     * <hr />
     * <table border="1">
     * <tr>
     * <th>Words</th>
     * <th>Counts</th>
     * </tr>
     * <tr>
     *
     *
     *
     *
     * @param out
     *            the output stream
     *
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre> out.is_open and out.content = #out.content * [method
     * output of HTML opening tags for the header] </pre>
     */
    public static void generateHTMLHeader(SimpleWriter output,
            String fileName) {
        output.println("<html> <head> <title>Words Counted in " + fileName
                + "}</title>");
        output.println("<h2>Words Counted in " + fileName + "</h2>");
        output.print("<hr />\r\n" + "<table border=\"1\">\r\n" + "<tr>\r\n"
                + "<th>Words</th>\r\n" + "<th>Counts</th>\r\n" + "</tr>");
    }

    /**
     * Outputs the closing tags for the word count HTML file. The elements are
     * as follows:
     *
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     *
     * @updates out.content
     * @requires out.is_open
     * @ensures out.is_open and out.content = #out.content * [method output of
     *          HTML opening tags]
     */
    public static void generateHTMLCloser(SimpleWriter out) {
        out.print("</table>\r\n" + "</body>\r\n" + "</html>\n");
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @requires str != null charSet != null
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: strSet is not null";

        Set<Character> chars = new Set1L<>();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            chars.add(charArray[i]);
        }
        charSet.transferFrom(chars);
    }

    /**
     * Reads in all the strings for the input file into a queue and creates a
     * map. If a string is read and is the duplicate of a key, the word is not
     * added, but the value of the key is increased by 1.
     *
     * @param in
     *            the input stream of the text file
     * @param wordQueue
     *            the queue of words
     * @param wordMap
     *            a map with the strings being the key having a value that
     *            corresponds to the number of times that word occurs
     *
     * @updates wordQueue, wordMap
     * @requires in.is_open
     * @ensures for all i:String where(wordQueue = i) (there exists no j:String
     *          (j = i and wordQueue = i * j)) and for all i:String where
     *          (wordQueue = i) (there exists j:Integer(map.pair(i,<j>) and j =
     *          i * [number of times found in input stream]
     *
     */
    public static void readInText(SimpleReader in, Queue<String> wordQueue,
            Map<String, Integer> wordMap, Set<Character> separators) {

        assert in != null : "Violation of: input is not null";
        assert in.isOpen() : "Violation of: input.is_open";

        /*
         * run the loop until the end of the input stream/file
         */
        while (!in.atEOS()) {
            String lineIn = in.nextLine();
            String word = "";

            /*
             * Read in the line character by character until it reaches a
             * separator
             *
             */
            for (char c : lineIn.toCharArray()) {
                if (!separators.contains(c)) {
                    word += c;
                } else {

                    /*
                     * If there is a word between the separators, then check if
                     * it is a key value within map.
                     */
                    if (word != "") {

                        /*
                         * If already a key, add 1 to the value associated with
                         * it
                         */
                        if (wordMap.hasKey(word)) {
                            wordMap.replaceValue(word, wordMap.value(word) + 1);
                        } else {

                            /*
                             * If not in the map, then create it and add the
                             * word to the queue
                             */
                            wordMap.add(word, 1);
                            wordQueue.enqueue(word);
                        }
                    }
                    //Clear the characters after updating
                    word = "";
                }

            }
            //Check if there are any words at the end of the string read in
            if (word != "") {

                /*
                 * If already a key, add 1 to the value associated with it
                 */
                if (wordMap.hasKey(word)) {
                    wordMap.replaceValue(word, wordMap.value(word) + 1);
                } else {

                    /*
                     * If not in the map, then create it and add the word to the
                     * queue
                     */
                    wordMap.add(word, 1);
                    wordQueue.enqueue(word);
                }
            }
        }
    }

    /**
     * Outputs all the words within the Queue as well as the number of times it
     * occurs within the text in an HTML format such that the output looks like
     * this:
     *
     * <tr>
     * <td>{@code value(word)}</td>
     * <td>{@code value associated with {@code wordMap.key}</td>
     * </tr>
     *
     * @param output name of output file
     *
     * @param wordQueue
     * @param wordMap
     *
     * @updates out.content
     * @requires outputName != null, wordQueue!= null, wordMap != null
     * @ensures out.is_open and out.content = #out.content * [method output of
     *          HTML body tags]
     */
    public static void generateHTMLFile(SimpleWriter out, String InputFileName,
            Queue<String> wordQueue, Map<String, Integer> wordMap) {
        assert InputFileName != null : "Violation of: input is not null";
        assert wordQueue != null : "Violation of: wordQueue is not null";
        assert wordMap != null : "Violation of: wordMap is not null";

        generateHTMLHeader(out, InputFileName);

        for (int i = 0; i < wordQueue.length(); i++) {
            out.print("<tr>\n<td>" + wordQueue.front() + "</td>\n");
            out.print("<td>" + wordMap.value(wordQueue.front()) + "</td>");
            out.println("</tr>");
            wordQueue.rotate(1);
        }

        generateHTMLCloser(out);

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Prompt user for name of text file + path
         */
        out.print("File to read in:");
        String inputFileName = in.nextLine();
        out.print("Name of output file:");
        String outputFileName = in.nextLine();

        SimpleReader inputStream = new SimpleReader1L(inputFileName);
        SimpleWriter outputStream = new SimpleWriter1L(outputFileName);

        Queue<String> wordQueue = new Queue1L<>();
        Map<String, Integer> wordMap = new Map1L<>();
        Comparator<String> alphaSort = new StringLT();
        Set<Character> separators = new Set1L<>();

        /*
         * Create the separator set with separators in it
         */
        generateElements(",.?1234567890!@#$%^&*|\t\\ `~<>(){}[];:\"/-_",
                separators);

        /*
         * Read in the text from the output file
         */
        readInText(inputStream, wordQueue, wordMap, separators);

        /*
         * sort words alphabetically
         */
        wordQueue.sort(alphaSort);

        /*
         * Call function that creates and output HTML file
         */
        generateHTMLFile(outputStream, inputFileName, wordQueue, wordMap);

        /*
         * Close input and output streams
         */
        inputStream.close();
        outputStream.close();
        in.close();
        out.close();
    }

}
