import java.io.Console;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.stream.Stream;

public class _2_InjectionAndInputValidation {

    /* Injection is an attack where dangerous input runs in a program as part of a command  */
    /* An exploit is an attack that takes advantage of weak security */


    public static void main(String[] args) throws Exception {

        /* Preventing Injection with a PreparedStatement
             - Use PreparedStatement instead of Statement
             - Use bind variables instead of concatenating a String with a variable
             - SQL injection is often caused by a lack of properly sanitized user input */
        String sql = "SELECT opens FROM hours WHERE day = ?";       // Use bind variables
//      String sql = "SELECT opens FROM hours WHERE day = '" + day      <---- String Concatenation is harmful!
        String day = "This should be a user input";
        try (Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
             var ps = conn.prepareStatement(sql)) {
            ps.setString(1, day);
            try (var rs = ps.executeQuery()) {
                if (rs.next())
                    System.out.println(rs.getInt("opens"));
            }
        }

        /* Invalidating Invalid Input with Validation
             - Command injection is another type that uses operating system commands to do something unexpected
             - Validate before command is processed */
        Console console = System.console();
        String dirName = console.readLine();
        if (dirName.equals("mammal") || dirName.equals("birds")) {  // Validate using values from a whitelist
            Path path = Paths.get("c:/data/diets/" + dirName);
            try (Stream<Path> stream = Files.walk(path)) {
                stream.filter(p -> p.toString().endsWith(".txt")).forEach(System.out::println);
            }
        }

    }

}
