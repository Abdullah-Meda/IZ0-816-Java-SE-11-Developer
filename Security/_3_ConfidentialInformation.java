import java.io.Console;
import java.util.Arrays;

public class _3_ConfidentialInformation {

    public static void main(String[] args) {

        /* Guarding sensitive data from output
             - Avoid putting confidential information in a toString() method
             - For such data, avoid:
                 - Writing to a log file
                 - Printing an exception or stack trace
                 - System.out and System.err messages
                 - Writing to data files */



        /* Protecting data in memory
             - When calling the readPassword() on Console, it returns a char[] instead of a String. This is safer
               for two reasons:
                    - It is not stored as a String, so Java won't place it in the String pool,
                      where it could exist in memory long after the code that used it is run.
                    - You can null out the value of the array element rather than waiting for the
                      garbage collector to do it.
             - When the sensitive data cannot be overwritten, it is good practice to set confidential data to
               null when you're done using it*/
        Console console = System.console();
        char[] password = console.readPassword();
        Arrays.fill(password, 'x');



        /* Limiting File Access
             - When looking at a policy, pay attention to whether the policy grants access to more than is needed
               to run the program. If our application needs to read a file, it should only have read permissions.
               This is the principle of least privilege.
             - Here's an example of a policy:
                    grant {
                        permission java.io.FilePermission
                        "C:\\water\\fish.txt",
                        "read, write";
                    };
        */

    }
}
