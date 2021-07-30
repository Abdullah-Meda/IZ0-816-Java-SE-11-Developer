import java.io.*;

public class _6_PrintingData {

    /* THIS IS A CONTINUATION OF _4_IOStreamClasses */

    /* SCROLL TO END FOR A REVIEW DIAGRAM */

    /* - PrintStream and PrintWriter are high‐level output print streams classes that are useful
         for writing text data to a stream.
       - Both classes have a lot of methods in common. However, when working with string data, you
         should use PrintWriter
       - Just remember that one operates on an OutputStream and the other a Writer
       - These two classes do not have corresponding input stream classes
       - Strangely, the PrintWriter class even has a constructor that takes an OutputStream as input
      */

    public static void main(String[] args) {

        try (var out = new PrintWriter(System.out)) { // System.out prints to console, but it could be a File path too

            /* print() */
                out.write(String.valueOf(5));       // Writer Method
                out.print(5);                       // PrintWriter Method

                var a = new AClass();
                out.write((a == null) ? "null" : a.toString());     // Writer Method
                out.print(a);                                       // PrintWriter Method

            out.println();

            /* println() - identical to the print() methods, except that they also print a line break after
                           the String value is written */
                out.println("A return character in added after this string");

                /* To find the break character on your OS: */
                System.getProperty("line.separator");
                System.lineSeparator();             // Add a System.out.println() if you wish

            out.println();

            /* format() - Common print stream format symbols:
                   Symbol      Description
                   ------      ----------------------
                 -   %s        Applies to any type, commonly String values
                 -   %d        Applies to integer values like int and long
                 -   %f        Applies to floating‐point values like float and double
                 -   %n        Inserts a line break using the system‐dependent lineseparator */
                out.format("%s %n    Score: %2.3f out of %d", "James", 90.25, 100);
                /* format() does NOT insert a line break at the end */
        }

        /* Most of the time, you can't wrap byte and character streams with each other, although as we
        mentioned, there are exceptions. The InputStreamReader class wraps an InputStream with a
        Reader, while the OutputStreamWriter class wraps an OutputStream with a Writer. */
        try (Reader r = new InputStreamReader(System.in);
             Writer w = new OutputStreamWriter(System.out)) {
        } catch (IOException e) { e.printStackTrace(); }

    }

}

class AClass {
    private final String lang = "Java";
    private final int version = 11;

    public String toString() { return "[ lang=" + lang + ", version=" + version + " ]"; }
}

/* Diagram of I/O stream classes

                   ┝-------- FileInputStream
    InputStream <--┝-------- FilterInputStream <------------ BufferedInputStream
                   ┝-------- ObjectInputStream

    Reader <-------┝-------- BufferedReader
                   ┝-------- InputStreamReader

                   ┝-------- FileOutputStream
    OutputStream <-┝-------- FilterOutputStream <--┝-------- BufferedOutputStream
                   ┝-------- ObjectOutputStream    ┝-------- PrintStream

                   ┝-------- BufferedWriter
    Writer <-------┝-------- OutputStreamWriter <----------- FileWriter
                   ┝-------- PrintWriter

 */
