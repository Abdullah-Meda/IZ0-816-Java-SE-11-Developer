import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class _5_ManagingFileAttributes {

    /* - The Files class also provides numerous methods for accessing file and directory metadata, referred
         to as file attributes.
       - A file attribute is data about a file within the system, such as its size and visibility, that is not part of
         the file contents */

    public static void main(String[] args) {

        /* public static boolean isDirectory(Path path, LinkOption… options)
           public static boolean isSymbolicLink(Path path)
           public static boolean isRegularFile(Path path, LinkOption… options)
             - It is possible for isRegularFile() to return true for a symbolic link, as long as the link
               resolves to a regular file */
        System.out.println(Files.isDirectory(Path.of("D:\\Java")));                 // true
        System.out.println(Files.isSymbolicLink(Paths.get("D:\\Java\\test.txt")));  // false
        System.out.println(Files.isRegularFile(Path.of("D:\\Java\\test.txt")));     // true



        /* public static boolean isHidden(Path path) throws IOException
             - A hidden file can't normally be viewed when listing the contents of a directory
           public static boolean isReadable(Path path)
             - isReadable is a boolean permission to open the file's contents
           public static boolean isWritable(Path path)
             - isWritable is a boolean permission to modify the file
           public static boolean isExecutable(Path path)
             - isExecutable is a boolean permission to r run the file as a program */
        System.out.println(Files.isReadable(Path.of("D:\\Java\\test.txt")));        // true
        System.out.println(Files.isWritable(Path.of("D:\\Java\\test.txt")));        // true
        System.out.println(Files.isExecutable(Path.of("D:\\Java\\test.txt")));      // true
        try {
            System.out.println(Files.isHidden(Path.of("D:\\Java\\test.txt")));      // false
        } catch (IOException e) { e.printStackTrace(); }



        /* public static long size(Path path) throws IOException
             - To determine the size of the file in bytes
             - The Files.size() method is defined only on files. Calling Files.size() on a directory is
               undefined, and the result depends on the file system */
        try {
            System.out.println(Files.size(Path.of("D:\\Java\\test.txt")));          // 20
        } catch (IOException e) { e.printStackTrace(); }



        /* public static FileTime getLastModifiedTime(Path path, LinkOption… options) throws IOException
             - The method returns a FileTime object, which represents a timestamp
             - For convenience, it has a toMillis() method that returns the epoch time, which is the number
               of milliseconds since 12 a.m. UTC on January 1, 1970 */
        try {
            System.out.println(Files.getLastModifiedTime(Path.of("D:\\Java\\test.txt")).toMillis());
        } catch (IOException e) { e.printStackTrace(); }

    }

}
