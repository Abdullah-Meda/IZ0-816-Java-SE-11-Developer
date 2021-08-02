import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class _1_CreatingPath {

    /* A symbolic link is a special file within a file system that serves as a reference or pointer to
       another file or directory */

    /* Different ways to obtaining a Path */
    public static void main(String[] args) throws URISyntaxException {

        /* Obtaining a Path using the Path interface */
        Path path1 = Path.of("pandas/cute.png");    // Inside the double quotes acn be a relative or an absolute path
        Path path2 =
            Path.of("/", "home", "zooDirectory");  /* The Path.of() method also includes a varargs
                                                                  to pass additional path elements. The values
                                                                  will be combined and automatically separated
                                                                  by the operating system–dependent file
                                                                  separator */


        /* Obtaining a Path with the Paths class */
        Path path3 = Paths.get("pandas/cute.png");
        Path path4 = Paths.get("/", "home", "zooDirectory"); /* Same explanation as ^^^^^^^ */


        /* Obtaining a Path with a URI Class */
        URI uri = new URI("pandas/cute.png");       // throws URISyntaxException
        Path path5 = Path.of(uri);
        Path path6 = Paths.get(uri);
        URI uri1 = path5.toUri();                       // Converting a Path back to a URI


        /* Obtaining a Path from the FileSystem Class */
        Path path7 = FileSystems.getDefault().getPath("pandas/cuddly.png"); // Longerform of Path.of() / Paths.get()


        /* Obtaining a Path from the java.io.File Class */
        File file = new File("pandas/cute.png");
        Path path8 = file.toPath();
        File backToFile = path8.toFile();               // Converting a Path back to a File

    }

}
