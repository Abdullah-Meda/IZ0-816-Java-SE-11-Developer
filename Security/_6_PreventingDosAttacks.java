import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class _6_PreventingDosAttacks {

    /* A denial of service (DoS) attack is when a hacker makes one or more requests with the intent of disrupting
       legitimate requests */


    /* Leaking resources
         - Make to always close opened resources, such as Streams */


    /* Reading very large resources
         - For example the following method, for small files, it works just fine!
         - However on larger files, your program could run out of memory and crash
         - To prevent this problem, you can check the size of the file before reading it */
    public void transform(Path in, Path out) throws IOException {
        var list = Files.readAllLines(in);
        list.removeIf(s -> s.trim().isBlank());
        Files.write(out, list);
    }


    /* Including potentially large resources
         - An inclusion attack is when multiple files or components are embedded within a single file
         - The reason these files can become unexpectedly large is that they can include other entities */


    /* Overflowing Numbers
         - When checking file size, be careful with an int type and loops.
         - Since an int has a maximum size, exceeding that size results in integer overflow and result in a
           negative number */


    /* Wasting Data Structures
         - Beware of code that attempts to create a very large array or other data structure
         - Input validation is your friend. You could limit the size of an array parameter or, better yet, don't
           allow the size to be set at all */
}
