import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class _3_InteractingWithPaths {

    /* Just like String values, Path instances are immutable */

    /* As rule of thumb, if the method declares an IOException, then it usually requires the paths it
       operates on to exist */

    private static final Path absPath = Paths.get("C:\\home\\languages\\java\\certification\\exercises");
    private static final Path relPath = Paths.get("java\\certification\\..\\.\\exercises.txt");

    public static void main(String[] args) {

        /* public String toString() - returns a String representation of the entire path
           public int getNameCount() - retrieve the number of elements
           public Path getName(int index) - retrieve a reference to each element */
        System.out.println("Path: " + absPath);    // adding toString() is redundant!
        for (int i = 0; i < absPath.getNameCount(); i++) {
            System.out.println("Element "+i+": "+ absPath.getName(i));   /* - Even though this is an absolute path,
                                                                           the root element is not included in the
                                                                           list of names. As we said, these methods
                                                                           do not consider the root as part of the
                                                                           path.
                                                                         - Throws IllegalArgumentException on
                                                                           invalid indices */
        }



        /* public Path subpath(int beginIndex, int endIndex) - select portions of a Path and create a new one
            -> The references are inclusive of the beginIndex, and exclusive of the endIndex.*/
        System.out.println("Full Path: " + absPath);
        System.out.println("subpath(0, 3): " + absPath.subpath(0, 3));
        System.out.println("subpath(2, 4): " + absPath.subpath(2, 4)); /* Throws IllegalArgumentException on
                                                                           invalid indices */
        System.out.println("subpath(0, 5): " + absPath.subpath(0, 5));



        /* public Path getFileName() - returns the Path element of the current file or directory
           public Path getParent() - returns the full path of the containing directory or null if operated
                                     on root path or on top of a relative path
           public Path getRoot() - returns the root element of the file within the file system, or null if the
                                   path is a relative path */
        System.out.println("Filename: " + absPath.getFileName());
        System.out.println("Root: " + absPath.getRoot());
        Path currentParent = absPath;
        while ((currentParent = currentParent.getParent()) != null) {
            System.out.println("Current parent: " + currentParent);
        }

        System.out.println("Filename: " + relPath.getFileName());
        System.out.println("Root: " + relPath.getRoot());           // null
        currentParent = relPath;
        while ((currentParent = currentParent.getParent()) != null) {
            System.out.println("Current parent: " + currentParent);     /* You also see that these methods do not
                                                                           resolve the path symbols and treat them
                                                                           as a distinct part of the path */
        }



        /* public boolean isAbsolute() - returns true if the path the object references is absolute and false
                                         if the path the object references is relative
           public Path toAbsolutePath() - converts a relative Path object to an absolute Path object by joining
                                          it to the current working directory. */
        System.out.println("Is path absolute? " + absPath.isAbsolute());    // true
        Path absolutePath = absPath.toAbsolutePath();      // returns path itself as the path is already absolute
        System.out.println(absolutePath);

        System.out.println("Is path absolute? " + relPath.isAbsolute());    // false
        absolutePath = relPath.toAbsolutePath();
        System.out.println(absolutePath);



        /* public Path resolve(Path other) - The object on which the resolve() method is invoked becomes the
                                             basis of the new Path object, with the input argument being appended
                                             onto the Path
           public Path resolve(String other) - Similar but takes a string argument */
        Path path1 = Paths.get("\\cats\\..\\panther");
        Path path2 = Path.of("food");
        System.out.println(path1.resolve(path2));       // \cats\..\panther\food

        Path path3 = Path.of("C:\\turkey\\food");           // This is an absolute path
        System.out.println(path3.resolve("C:\\tiger\\cage"));    /* - The parameter is also an absolute path
                                                                    - The output will be C:\tiger\cage */



        /* public Path relativize() - The idea is this: if you are pointed at a path in the file system, what
                                      steps would you need to take to reach the other path? */
        Path path4 = Path.of("fish.txt");
        var path5 = Path.of("friendly\\birds.txt");     // the file itself counts as one level
        System.out.println(path4.relativize(path5));    // ..\friendly\birds.txt
        System.out.println(path5.relativize(path4));    // ..\..\fish.txt

        Path path6 = Paths.get("E:\\habitat");
        Path path7 = Paths.get("E:\\sanctuary\\raven\\poe.txt");
        System.out.println(path6.relativize(path7));    // ..\sanctuary\raven\poe.txt
        System.out.println(path7.relativize(path6));    // ..\..\..\habitat
            /*  - The relativize() method requires that both paths are absolute or both
                  relative and throws an IllegalArgumentException if the types are mixed.
                - On Windows???based systems, it also requires that if absolute paths are used,
                  then both paths must have the same root directory or drive letter. */



        /* public Path normalize() */
        var path8 = Path.of(".\\armadillo\\..\\shells.txt");
        System.out.println(path8.normalize());                     // shells.txt
        var path9 = Path.of("\\cats\\..\\panther\\food");
        System.out.println(path9.normalize());                     // \panther\food
        var path10 = Path.of("..\\..\\fish.txt");
        System.out.println(path10.normalize());                     // ..\..\fish.txt

        var path11 = Paths.get("/pony/../weather.txt");
        var path12 = Paths.get("/weather.txt");
        System.out.println(path11.equals(path12));                          // false
        System.out.println(path11.normalize().equals(path12.normalize()));  // true
        System.out.println(path11.normalize().equals(path12));              // true


        /* public Path toRealPath(LinkOption??? options) throws IOException
             - This method is similar to normalize(), in that it eliminates any redundant path symbols
             - It is also similar to toAbsolutePath(), in that it will join the path with the current working
               directory if the path is relative
             - toRealPath() will throw an exception if the path does not exist. In addition, it will follow
               symbolic links, with an optional varargs parameter to ignore them */
        try {
            System.out.println(Path.of("C:\\Users\\abdul_nr6ehsg\\Desktop\\3.txt").toRealPath());

            /* To gain access to the current working directory as a Path object */
            System.out.println(Paths.get(".").toRealPath());
        } catch (IOException e) { e.printStackTrace(); }

    }

}
