import java.io.*;

public class _4_IOStreamClasses {

    /* The java.io concrete stream classes:

        Class Name             Low/ High Level              Description
        ----------             ---------------              -------------------
        FileInputStream             Low                     Reads file data as bytes
        FileOutputStream            Low                     Writes file data as bytes
        FileReader                  Low                     Reads file data as characters
        FileWriter                  Low                     Writes file data as characters
        BufferedInputStream         High                    Reads byte data from an existing InputStream in a buffered manner, which improves efficiency and performance
        BufferedOutputStream        High                    Writes byte data to an existing OutputStream in a buffered manner, which improves efficiency and performance
        BufferedReader              High                    Reads character data from an existing Reader in a buffered manner, which improves efficiency and performance
        BufferedWriter              High                    Writes character data to an existing Writer in a buffered manner, which improves efficiency and performance
        ObjectInputStream           High                    Deserializes primitive Java data types and graphs of Java objects from an existing InputStream
        ObjectOutputStream          High                    Serializes primitive Java data types and graphsof Java objects to an existing OutputStream
        PrintStream                 High                    Writes formatted representations of Java objects to a binary stream
        PrintWriter                 High                    Writes formatted representations of Java objects to a character stream
     */

    /* If you need to append to an existing file, there's a constructor for that. The FileOutputStream class
       includes overloaded constructors that take a boolean append flag. When set to true, the output stream
       will append to the end of a file if it already exists. */

    private static final String fileName = "This should be a file path";

    public static void main(String[] args) {    // DO NOT RUN THIS CLASS, AS NO FILE PATH HAS BEEN PROVIDED
                                                // THE GOAL OF THIS CLASS IS TO GIVE AN OVERVIEW OF THE I/O CLASSES
                                                // IN ORDER TO REVISE COMMON I/O METHODS, REFER TO _3_CommonIOStreamMethods.java

        /* FileInputStream / FileOutputStream - Reading and Writing Binary Data */
        try (var in = new FileInputStream(fileName);
             var out = new FileOutputStream(fileName)) {

            int b;
            while ((b = in.read()) != -1) out.write(b);

        } catch (IOException e) { e.printStackTrace(); }

        /* BufferedInputStream / BufferedOutputStream - Buffering Binary Data */
        try (var in = new BufferedInputStream(new FileInputStream(fileName));
             var out = new BufferedOutputStream(new FileOutputStream(fileName))) {

            var buffer = new byte[1024];
            int lengthRead;     /* The return value lengthRead is critical for determining
                                   whether we are at the end of the stream and knowing how many bytes we
                                   should write into our output stream */

            while ((lengthRead = in.read(buffer)) > 0) {        // reads 1024 bytes at a time
                out.write(buffer, 0, lengthRead);           // writes 1024 bytes at a time
                out.flush();       // to ensure data is written to disk between each iteration
            }

        } catch (IOException e) { e.printStackTrace(); }

        /* FileReader / FileWriter - Reading and Writing Character Data */
        try (var reader = new FileReader(fileName);
             var writer = new FileWriter(fileName)) {

            int b;
            while ((b = reader.read()) != -1) writer.write(b);

            /* The following is possible in FileWriter but not in FileOutputStream */
            writer.write("Hello World");

        } catch (IOException e) { e.printStackTrace(); }

        /* BufferedReader / BufferedWriter - Buffering Character Data */
        try (var reader = new BufferedReader(new FileReader(fileName));
             var writer = new BufferedWriter(new FileWriter(fileName))) {

            String s;
            while ((s = reader.readLine()) != null) {   // reads a line at a time (since this is a string, it can..
                                                        //      ..be manipulated before writing it
                writer.write(s);                        // writes the line
                writer.newLine();                       // adds a line break as .readLine() strips it from the string
            }

            /* Each loop iteration corresponds to reading and writing a line
               of a file. Assuming the length of the lines in the file are reasonably sized, this
               implementation will perform well */

        } catch (IOException e) { e.printStackTrace(); }

        /* CONTINUED IN THE NEXT CLASS FILE */

    }
}
