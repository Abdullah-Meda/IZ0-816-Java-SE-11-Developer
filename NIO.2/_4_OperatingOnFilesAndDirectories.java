import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _4_OperatingOnFilesAndDirectories {

    /* - The Files helper class is capable of interacting with real files and directories within the system.
       - Because of this, most of the methods take optional parameters and throw an IOException if the path does
         not exist.
       - The Files class also replicates numerous methods found in the java.io.File, albeit often with
         a different name or list of parameters */

    /* HERE are some common methods applied to files and directories */

    public static void main(String[] args) {

        /* public static boolean exists(Path path, LinkOption… options)
             - Checks whether the file exists
             - Does not throw an exception */
        boolean b1 = Files.exists(Path.of("D:\\Java\\test.txt")); // put your custom path here
        System.out.println("Path " + (b1 ? "Exists" : "Missing"));

        boolean b2 = Files.exists(Paths.get("D:\\Java"));  // Note: this path references a directory
        System.out.println("Path " + (b2 ? "Exists" : "Missing"));



        /* public static boolean isSameFile(Path path, Path path2) throws IOException
             - Checks if two Path instances refer to the same file
             - The method takes two Path objects as input, resolves all path symbols, and follows symbolic links.
             - Despite the name, the method can also be used to determine whether two Path objects refer to the same directory.
             - If the two path objects are equal, in terms of equals(), then the method will just return
               true without checking whether the file exists */
        try {
            System.out.println(Files.isSameFile(
                    Path.of("C:\\Documents\\java.txt"),
                    Path.of("C:\\Desktop\\java.txt") ));        // false
        } catch (IOException e) { e.printStackTrace(); }



        /* public static Path createDirectory(Path dir, FileAttribute<?>… attrs) throws IOException
             - Creates a directory and throw an exception if it already exists or the paths leading up to the
               directory do not exist
           public static Path createDirectories(Path dir, FileAttribute<?>… attrs) throws IOException
             - Creates the target directory along with any nonexistent parent directories leading up to the path.
             - If all of the directories already exist, createDirectories() will simply complete without doing
               anything */
        try {
            Files.createDirectory(Path.of("D:\\Java\\NewDir"));    /* throws exception if dir already exists or
                                                                           if the path is invalid */
            Files.createDirectories(Paths.get("D:\\Java\\NewDir2\\InnerDir"));
        } catch (IOException e) { e.printStackTrace(); }



        /* copy()
             - A method to copy files and directories */
        try {
            /* public static Path copy(Path source, Path target, CopyOption… options) throws IOException */
            Files.copy(Path.of("D:\\Java\\test.txt"), /* File copying */
                    Path.of("D:\\Java\\NewDir\\copiedTest.txt"));   // test.txt but file is saved as copiedTest.txt

            Files.copy(Path.of("D:\\Java\\NewDir"),
                    Path.of("D:\\Java\\NewDir2\\NewDir"));
                                /* - Directory copying ^^^
                                   - When directories are copied, the copy is shallow. A shallow copy means
                                     that the files and subdirectories within the directory are not copied */

            /* Copying and replacing files
                 - For the exam, you need to know that without the REPLACE_EXISTING
                   option, this method will throw an exception if the file already exists. */
            Files.copy(Path.of("D:\\Java\\test.txt"),
                    Path.of("D:\\Java\\NewDir\\copiedTest.txt"),
                    StandardCopyOption.REPLACE_EXISTING);       // replaces existing copiedTest.txt

            /* The Files class includes two copy() methods that operate with I/O streams:
                 public static long copy(InputStream in, Path target, CopyOption… options) throws IOException
                     - Reads the contents of a stream and writes the output to a file
                 public static long copy(Path source, OutputStream out) throws IOException
                     - Reads the contents of a file and writes the output to a stream */
            try (var is = new FileInputStream("D:\\Java\\source-data.txt")) {
                Path targetFile = Paths.get("D:\\Java\\output-data.txt");
                Files.copy(is, targetFile); // throws exception if output-data.txt already present
            }
            Path inputFile = Path.of("D:\\Java\\source-data.txt");
            Files.copy(inputFile, System.out);  // Prints the contents of a file directly to the System.out stream

            /* Copying files in to a directory
                 - Causes an exception to be thrown, such as the following:
                        var file = Paths.get("C:\\Users\\abdul_nr6ehsg\\Desktop\\JavaTestFiles\\test.txt");
                        var directory = Paths.get("C:\\Users\\abdul_nr6ehsg\\Desktop\\NewDir2");
                        Files.copy(file, directory);
                 - The following would be correct: */
            var file = Paths.get("D:\\Java\\test.txt");
            var directory = Paths.get("D:\\Java\\NewDir2\\test.txt");
            Files.copy(file, directory);

            /* You also define directory using resolve(), which saves you from having to write the filename twice.*/
            directory = Path.of("D:\\Java\\NewDir2").resolve(file.getFileName());

        } catch (IOException e) { e.printStackTrace(); }



        /* public static Path move(Path source, Path target, CopyOption… options) throws IOException
             - Moving or renaming files and directories */
        try {
            /* Renaming */
            Path path = Path.of("D:\\Java\\ToBeRenamed");
            Files.createDirectory(path);
            var renamedDir = Path.of("D:\\Java\\Renamed");
            Files.move(path, renamedDir);   // renames the directory "ToBeRenamed" to "Renamed"

            /* Moving */
            Path fileToBeMoved = Paths.get("D:\\Java\\output-data.txt");
            Path newLocationOfFile = Path.of("D:\\Java\\Renamed\\renamedAndMoved.txt");
            Files.move(fileToBeMoved, newLocationOfFile);   // completely moves the file and renames it too!

            /* Similarities - move() and copy()
                 - Like copy(), move() requires REPLACE_EXISTING to overwrite the target if it exists, else it
                   will throw an exception.
                 - Also like copy(), move() will not put a file in a directory if the source is a file and the
                   target is a directory. Instead, it will create a new file with the name of the directory
            */

            /* Performing an atomic move
                 - One in which a file is moved within the file system as a single indivisible operation */
            fileToBeMoved = Paths.get("D:\\Java\\Renamed\\renamedAndMoved.txt");
            newLocationOfFile = Path.of("D:\\Java\\AtomicallyMoved.txt");
            Files.move(fileToBeMoved, newLocationOfFile, StandardCopyOption.ATOMIC_MOVE);

        } catch (IOException e) { e.printStackTrace(); }



        /* public static BufferedReader newBufferedReader(Path path) throws IOException
           public static BufferedWriter newBufferedWriter(Path path, OpenOption… options) throws IOException */
        try {
            /* newBufferedWriter() */
            var list = new ArrayList<String>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
            try(var writer = Files.newBufferedWriter(Path.of("D:\\Java\\WrittenUsingIO.txt"))) {
                for (String line : list) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            /* newBufferedReader() */
            try (var reader = Files.newBufferedReader(Path.of("D:\\Java\\WrittenUsingIO.txt"))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null)
                    System.out.println(currentLine);
            }
        } catch (IOException e) { e.printStackTrace(); }



        /* public static List<String> readAllLines(Path path) throws IOException
             - A convenient method for turning the lines of a file into a List */
        try {
            List<String> list = Files.readAllLines(Path.of("D:\\Java\\WrittenUsingIO.txt"));
            System.out.println("Contents of List from readAllLines: ");
            for (String line : list) System.out.println(line);
        } catch (IOException e) { e.printStackTrace(); }



        /* public static void delete(Path path) throws IOException
             - Throws an exception if the path does not exis
           public static boolean deleteIfExists(Path path) throws IOException
             - Returns true if the delete was successful, and false otherwise. Similar to createDirectories()

            - To delete a directory, it must be empty.
            - Both of these methods throw an exception if operated on a non-empty directory.
            - In addition, if the path is a symbolic link, then the symbolic link will be deleted, not the
              path that the symbolic link points to.

            Here, I delete the extra files and folders I created above: */
        try {
            Files.delete(Path.of("D:\\Java\\NewDir\\copiedTest.txt"));
            Files.delete(Path.of("D:\\Java\\NewDir"));

            Files.delete(Path.of("D:\\Java\\NewDir2\\NewDir"));
            Files.delete(Path.of("D:\\Java\\NewDir2\\InnerDir"));
            Files.delete(Path.of("D:\\Java\\NewDir2\\test.txt"));
            Files.delete(Path.of("D:\\Java\\NewDir2"));

            Files.deleteIfExists(Path.of("D:\\Java\\Renamed"));
            Files.deleteIfExists(Path.of("D:\\Java\\AtomicallyMoved.txt"));

            Files.deleteIfExists(Path.of("D:\\Java\\WrittenUsingIO.txt"));
        } catch (IOException e) { e.printStackTrace(); }

    }
}
