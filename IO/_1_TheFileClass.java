import java.io.File;

public class _1_TheFileClass {

    /* Remember, a File instance can represent a file or a directory. */

    /* The absolute path of a file or directory is the full path from the root directory to
       the file or directory, including all subdirectories that contain the file or directory

        The relative path of a file or directory is the path from the current working directory
        to the file or directory */

    public static void main(String[] args) {

        /* Two ways to retrieve the local separator character */
        System.out.println(System.getProperty("file.separator"));
        System.out.println(File.separator);

        /* To check if a path exists: */
        File file1 = new File("/home/pl/java/iz0-816.txt");
        System.out.println(file1.exists());      // true if file exists

        /* There are 3 constructors for the File class */

            /* public File(String pathname) */
            var zooFile1 = new File("/home/tiger/data/stripes.txt");

            /* public File(File parent, String child) */
            File parent = new File("/home/tiger");
            File zooFile2 = new File(parent, "data/stripes.txt");

            /* public File(String parent, String child) */
            File zooFile3 = new File("/home/tiger", "data/stripes.txt");

        /* java.io.File Methods - scroll to the bottom for a full summary */
        var file = new File("C:\\Users\\abdul_nr6ehsg\\IdeaProjects\\IZ0-816-Java-SE-11-Developer\\IO\\_0_test.txt");
        /* Add your custom file path here -------^^^ */
        System.out.println(file.exists());      // true in my case
        if (file.exists()) {
            /* getAbsolutePath() */ System.out.println("Absolute Path: " + file.getAbsolutePath());
            /* isDirectory() */     System.out.println("Is Directory: " + file.isDirectory());
            /* getParent() */       System.out.println("Parent Path: " + file.getParent());
            /* isFile() */
            if (file.isFile()) {
                /* length() */      System.out.println("File Size: " + file.length());
                /* lastModified() */System.out.println("Last Modified: " + file.lastModified());
            } else {    // else it is a directory, so we list the files inside of it
                /* listFiles */
                for (File subFile : file.listFiles()) {
                    /* getName */   System.out.println("   " + subFile.getName());
                }
            }
        }

    }

}
/* Commonly used java.io.File methods:

    Method Name                         Description
    -----------                         ------------------------------------
    boolean delete()                    Deletes the file or directory and returns true only if
                                        successful. If this instance denotes a directory, then the
                                        directory must be empty in order to be deleted.

    boolean exists()                    Checks if a file exists

    String getAbsolutePath()            Retrieves the absolute name of the file or directory
                                        within the file system

    String getName()                     Retrieves the name of the file or directory.

    String getParent()                  Retrieves the parent directory that the path is
                                        contained in or null if there is none

    boolean isDirectory()               Checks if a File reference is a directory within the file system

    boolean isFile()                    Checks if a File reference is a file within the file system

    long lastModified()                 Returns the number of milliseconds since the epoch
                                        (number of milliseconds since 12 a.m. UTC on January 1, 1970) when
                                        the file was last modified

    long length()                       Retrieves the number of bytes in the file

    File[] listFiles()                  Retrieves a list of files within a directory

    boolean mkdir()                     Creates the directory named by this path

    boolean mkdirs()                    Creates the directory named by this path including any
                                        nonexistent parent directories

    boolean renameTo(File dest)         Renames the file or directory denoted by this path to
                                        dest and returns true only if successful
 */