import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class _7_ApplyingFunctionalProgramming {

    /* The Files class includes some incredibly useful Stream API methods that operate on files, directories,
       and directory trees */

    public static void main(String[] args) {

        /* public static Stream<Path> list(Path dir) throws IOException
             - List the contents of a directory */
        try (Stream<Path> files = Files.list(Path.of("D:\\Java"))) {  /* we put our Stream objects inside a
                                                                    try‐with‐resources method? The NIO.2
                                                                    stream‐ based methods open a connection
                                                                    to the file system that must be properly
                                                                    closed, else a resource leak could ensue */
            files.forEach(System.out::println);
        } catch (IOException e) { e.printStackTrace(); }

        /* Scroll down to copyPath() which deep copies a Path using Files.list() and Files.copy() */
        try {
            Files.createDirectory(Path.of("D:\\Java\\NewDirectory"));     // created a directory for testing purposes
            copyPath(Path.of("D:\\Java"), Path.of("D:\\DeepCopied"));  // deep copy a directory

            /* Delete extra files and folders made */
            /* Scroll down to deletePath() which deletes a directory tree */
            deletePath(Path.of("D:\\DeepCopied"));
            deletePath(Path.of("D:\\Java\\NewDirectory"));
        } catch (IOException e) { e.printStackTrace(); }



        /* public static Stream<Path> walk(Path start, FileVisitOption… options) throws IOException
             - Default maximum depth of Integer.MAX_VALUE
           public static Stream<Path> walk(Path start, int maxDepth, FileVisitOption… options) throws IOException
             - Allows the user to set a maximum depth
             - Example: try (var s = Files.walk(source, 5)) {...}

             Here, I find the total size of all the files in a directory: */
        long totalSize = 0;
        try (Stream<Path> s = Files.walk(Path.of("C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer"))) {
            totalSize = s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(p -> {
                        try { return Files.size(p); }
                        catch (IOException e) { e.printStackTrace(); }
                        return 0L;
                    }).sum();
        } catch (IOException e) { e.printStackTrace(); }
        System.out.format("Total size: %.2f MB \n", (totalSize / 1_000_000.0)); // Print total size in MegaBytes

        /* The walk() method is different in that it does not follow symbolic links by default and requires the
           FOLLOW_LINKS option to be enabled. Example:
                try (var s = Files.walk(source, FileVisitOption.FOLLOW_LINKS)) {...}

           - Make your the symbolic links do not create an endless cycle when using this option as a file or
             dir may point to an ancestor directory. A path may be caused to be visited twice or more resulting in
             an exception being thrown
        */



        /* public static Stream<Path> find(Path start, int maxDepth,
                    BiPredicate<Path,BasicFileAttributes> matcher,
                    FileVisitOption… options) throws IOException
             - The find() method behaves in a similar manner as the walk() method, except that it takes a
               BiPredicate to filter the data.
             - It also requires a depth limit be set.
             - Like walk(), find() also supports the FOLLOW_LINK option

             This following example searches a directory tree and prints all .java files with a size of at
             least 1,000 bytes, using a depth limit of 10: */
        try (Stream<Path> files = Files.find(
                Path.of("C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer"),
                10,
                (p, a) -> a.isRegularFile() && p.toString().endsWith(".java") && a.size() > 1000)) {
            files.parallel().forEach(System.out::println);
        } catch (IOException e) { e.printStackTrace(); }

        /* Here is a similar but lengthier implementation of above code using walk() */
        try (Stream<Path> files = Files.walk(Path.of("C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer"))) {
            files.parallel().filter(p -> {
                BasicFileAttributes data = null;
                try { data = Files.readAttributes(p, BasicFileAttributes.class); }
                catch (IOException e) { e.printStackTrace(); }
                return (data.isRegularFile() && data.size() > 1000);
            }).filter(p -> p.toString().endsWith(".java")).forEach(System.out::println);
        } catch (IOException e) { e.printStackTrace(); }



        /* public static Stream<String> lines(Path path) throws IOException
             - Using Files.readAllLines() to read a very large file could result in an OutOfMemoryError problem
             - Luckily, NIO.2 solves this problem with the above Stream API method
             - REMEMBER: The readAllLines() method returns a List, not a Stream */
        try (Stream<String> lines = Files.lines(Path.of("D:\\Java\\test.txt"))) {
            lines.forEach(System.out::println);
        } catch (IOException e) { e.printStackTrace(); }

    }

    /* HELPER METHODS */

    /* Deletes a file or a directory tree if the directory has contents recursively */
    public static void deletePath(Path path) throws IOException {
        if (!Files.isDirectory(path) || Files.list(path).count() == 0) { Files.deleteIfExists(path); }
        else {
            try (Stream<Path> files = Files.list(path)) {
                files.forEach(p -> {
                    try { deletePath(p); }
                    catch (IOException e) { e.printStackTrace(); }
                });
                Files.delete(path);
            }
        }
    }

    /* Deep copies a path recursively */
    public static void copyPath(Path source, Path target) {
        try {
            Files.copy(source, target);
            if (Files.isDirectory(source)) {
                try (Stream<Path> files = Files.list(source)) {
                    files.forEach(p -> copyPath(p, target.resolve(p.getFileName())));
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

}