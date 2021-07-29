public class _2_IOStreamsIntroduction {

    /* Byte Streams vs. Character Streams
        - Byte streams read/write binary data ( 0s and 1s) and have class names that
          end in InputStream or OutputStream.
        - Character streams read/write text data and have class names that end in
          Reader or Writer. */

    /* Input vs. Output Streams
        - Most InputStream stream classes have a corresponding OutputStream
          class, and vice versa
        - There are exceptions to this rule.
            - For the exam, you should know that PrintWriter has no accompanying PrintReader class.
            - Likewise, the PrintStream is an OutputStream that has no corresponding InputStream class. */

    /* Low‐Level vs. High‐Level Streams
        - A low‐level stream connects directly with the source of the data, such as a
          file, an array, or a String. Low‐level streams process the raw data or
          resource and are accessed in a direct and unfiltered manner
        - Alternatively, a high‐level stream is built on top of another stream using
          wrapping. Wrapping is the process by which an instance is passed to the
          constructor of another class, and operations on the resulting instance are
          filtered and applied to the original instance */

    /* The java.io library defines four ABSTRACT classes that are the parents of all
       stream classes defined within the API: InputStream, OutputStream,
       Reader, and Writer.

       - InputStream            Abstract class for all input byte streams
       - OutputStream           Abstract class for all output byte streams
       - Reader                 Abstract class for all input character streams
       - Writer                 Abstract class for all output character streams */

    /* One common area where the exam likes to play tricks on you is mixing and
       matching stream classes that are not compatible with each other.

       new BufferedInputStream(new FileReader("z.txt"));        // DOES NOT COMPILE
       new BufferedWriter(new FileOutputStream("z.txt"));       // DOES NOT COMPILE
       new ObjectInputStream(new FileOutputStream("z.txt"));    // DOES NOT COMPILE
       new BufferedInputStream(new InputStream());              // DOES NOT COMPILE

       * Do not mix Reader/ Writer classes with InputStream/ OutputStream classes */

    /* Review of java.io Class Name Properties:
        - A class with the word InputStream or OutputStream in its name is used
          for reading or writing binary or byte data, respectively.
        - A class with the word Reader or Writer in its name is used for reading or
          writing character or string data, respectively.
        - Most, but not all, input classes have a corresponding output class.
        - A low‐level stream connects directly with the source of the data.
        - A high‐level stream is built on top of another stream using wrapping.
        - A class with Buffered in its name reads or writes data in groups of bytes or
          characters and often improves performance in sequential file systems.
          With a few exceptions, you only wrap a stream with another stream if they
          share the same abstract parent. */



}
