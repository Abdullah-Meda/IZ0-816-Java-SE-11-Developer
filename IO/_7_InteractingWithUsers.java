import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class _7_InteractingWithUsers {

    /* The java.io API includes numerous classes for interacting with the user.
       For example, you might want to write an application that asks a user to log in
       and prints a success message */

    public static void main(String[] args) throws IOException {

        /* Printing ERROR messages */
        System.err.println("This is an error message printed in RED in IntelliJ IDEA");

        /* Reading input as a stream */
        var reader = new BufferedReader(new InputStreamReader(System.in));  /* Note the use of InputStreamReader
                                                                               instead of FileReader */
        String input = reader.readLine();
        System.out.println("You entered: " + input);

        /* System.out / System.err:
            Because these are static objects, the System streams are shared by the entire application.
            The JVM creates and opens them for us. They can be used in a try‐with‐resources statement or by
            calling close(), although closing them is not recommended. Closing the System streams makes them
            permanently unavailable for all threads in the remainder of the program. */

        // ---------------------------------------------------------------------------------

        /* Acquiring Input with java.io.Console */
        Console console = System.console();         // NOTE: Console c = new Console in invalid as the
                                                    //       Console class does not have a constructor
        if (console != null) {      // System.console() returns null on failure
            String userInput = console.readLine();
            console.writer().println("You entered: " + userInput);
        } else {
            System.err.println("Console not available");
        }

        /* Here are some common methods of the Console class */

            /* reader() | writer() | format() */
            if (console == null) { System.err.println("Console not available"); }
            else {
                console.writer().println("Welcome to Our Zoo!");
                console.format("It has %d animals and employs %d people", 391, 25);
                console.writer().format(new Locale("fr", "CA"), "It has %d animals and employs %d people", 391, 25);
                console.writer().println();
                console.printf("The zoo spans %5.1f acres", 128.91);
            }

            /* readLine() | readPassword() */
            if (console == null) { System.err.println("Console not available"); }
            else {
                console.readLine();                             // takes input
                console.readLine("Enter your age: ");       // takes input with a prompt msg

                /*  The readPassword() methods are similar to the readLine() with two important differences:
                     - The text the user types is not echoed back and displayed on the screen as they are typing.
                     - The data is returned as a char[] instead of a String. */
                console.readPassword();
                console.readPassword("Enter a %s: ", "password");
            }

    }

}
