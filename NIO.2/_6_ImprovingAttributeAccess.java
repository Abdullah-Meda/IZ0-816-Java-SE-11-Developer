import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class _6_ImprovingAttributeAccess {

    /* A view is a group of related attributes for a particular file system type */

    /* NIO.2 includes two methods for working with attributes in a single method call: a read‐only attributes
       method and an updatable view method. For each method, you need to provide a file system type object,
       which tells the NIO.2 method which type of view you are requesting. By updatable view, we mean that we
       can both read and write attributes with the same object */

    /* The attributes and view types:
        Attributes interface        View interface            Description
        --------------------        --------------            -----------------------------
        BasicFileAttributes         BasicFileAttributeView    Basic set of attributes supported by all file systems
        DosFileAttributes           DosFileAttributeView      Basic set of attributes along with those supported
                                                              by DOS/Windows‐based systems
        PosixFileAttributes         PosixFileAttributeView    Basic set of attributes along with those supported
                                                              by POSIX systems, such as UNIX, Linux, Mac, etc.
     */

    public static void main(String[] args) {

        /* public static <A extends BasicFileAttributes> A readAttributes(
                    Path path,
                    Class<A> type,
                    LinkOption… options) throws IOException
             - To read attributes of a class in a read‐only capacity
             - Applying it requires specifying the Path and BasicFileAttributes.class (or 1 of the other
               two) parameters */
        try {
            BasicFileAttributes data = Files.readAttributes(
                    Path.of("D:\\Java\\test.txt"), BasicFileAttributes.class);

            System.out.println("Is a directory? " + data.isDirectory());            // false
            System.out.println("Is a regular file? " + data.isRegularFile());       // true
            System.out.println("Is a symbolic link? " + data.isSymbolicLink());     // false
            System.out.println("Size (in bytes): " + data.size());                  // 20
            System.out.println("Last modified: " + data.lastModifiedTime());        // 2021-08-02T16:16:44.298508Z

        } catch (IOException e) { e.printStackTrace(); }



        /* public static <V extends FileAttributeView> V getFileAttributeView(
                    Path path,
                    Class<V> type,
                    LinkOption… options)
             - Returns an updatable view
             - Does NOT declare a checked exception
             - The following example modifies the last modified time of a file */
        BasicFileAttributeView view = Files.getFileAttributeView(
                Path.of("D:\\Java\\test.txt"), BasicFileAttributeView.class);
        try {
            BasicFileAttributes attributes = view.readAttributes();     // obtain file metadata

            // create a new FileTime value
            FileTime lastModifiedTime = FileTime.fromMillis(attributes.lastModifiedTime().toMillis() + 10_000);

            /* set the new lastModifiedTime using the setTimes() method
                 - public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
                 - This method allows us to pass null for any date/time value that we do not want to modify */
            view.setTimes(lastModifiedTime, null, null);

        } catch (IOException e) { e.printStackTrace(); }
    }
}
