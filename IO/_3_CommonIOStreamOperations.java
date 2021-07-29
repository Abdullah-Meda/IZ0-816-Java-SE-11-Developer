import java.io.*;

public class _3_CommonIOStreamOperations {

    /* Common I/O stream methods:
        Stream Class            Method Signature                Description
        ------------            -----------                     --------------------------------------
      - All Streams             void close()                    Closes stream and releases resources
      - All Input Streams       int read()                      Reads a single byte or returns ‐1 if no bytes were available
      - InputStream             int read(byte[] b)              Reads values into a buffer. Returns number of bytes read
      - Reader                  int read(char[] c)              Reads values into a buffer. Returns number of bytes read
      - InputStream             int read(byte[] b,              Reads up to length values into a buffer starting from position offset. Returns number of bytes read
                                    int offset, int length)
      - Reader                  int read(char[] c,              Reads up to length values into a buffer starting from position offset. Returns number of bytes read
                                    int offset, int length)
      - All Output Streams      void write(int)                 Writes a single byte
      - OutputStream            void write(byte[] b)            Writes an array of values into the stream
      - Writer                  void write(char[] c)            Writes an array of values into the stream
      - OutputStream            void write(byte[] b,            Writes length values from an array into the stream, starting with an offset index
                                    int offset, int length)
      - Writer                  void write(char[] c,            Writes length values from an array into the stream, starting with an offset index
                                    int offset, int length)
      - All Input Streams       boolean markSupported()         Returns true if the stream class supports mark()
      - All Input Streams       mark(int readLimit)             Marks the current position in the stream
      - All Input Streams       void reset()                    Attempts to reset the stream to the mark() position
      - All Input Streams       long skip(long n)               Reads and discards a specified number of characters
      - All Input Streams       void flush()                    Flushes buffered data through the stream
    */

    public static void main(String[] args) {

        var fileRead = "C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer\\IO\\_0_read.txt";
        var fileWrite = "C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer\\IO\\_0_write.txt";

        try (var in = new FileInputStream(fileRead);
             var out = new FileOutputStream(fileWrite)) {

            /* read() and write() */
            out.write((char) in.read());

            /* read(byte[] b)
               void write(byte[] b) */
            byte[] arr = new byte[10];
            int noOfBytesRead = in.read(arr);
            out.write(arr);

            /* read(byte[] b, int offset, int length)
               void write(byte[] b, int offset, int length) */
            byte[] arr2 = new byte[25];
            noOfBytesRead = in.read(arr2, 15, 10);   /* <----  What this line does is read 10 bytes from
                                                               the current file pointer and store them in
                                                               arr2[16] to arr2[25] as the offset supplied was
                                                               15. NOTE that the offset is the offset in the
                                                               buffer array and not the characters in the file */
            out.write(arr2, 15, 10);

            /* Current output in _0_write.txt: "This file is used by " */
            /* Next 5 bytes to be written : "other" */

            /* boolean markSupported()
               mark(int readLimit)
               void reset() */
            out.write((char) in.read());        // writes "o"
            out.write((char) in.read());        // writes "t"
            if (in.markSupported()) {
                in.mark(15);            // Marks up to 15 bytes
                out.write((char) in.read());    // writes "h"
                out.write((char) in.read());    // writes "e"
                in.reset();                     // Resets stream to position before "h"
            }
            out.write((char) in.read());        // writes "h"
            out.write((char) in.read());        // writes "e"
            out.write((char) in.read());        // writes "r"

            /* In my case, mark() wasn't supported, so "other" was written to the _0_write.txt. If markSupported()
               did return true, then "otheher" would have been written */

            /* long skip(long n) */
            long noOfValuesSkipped = in.skip(6);    /* This line skips the next 6 bytes(characters) which
                                                          happen to be " class" */

        } catch (IOException e) { e.printStackTrace(); }
    }
}
