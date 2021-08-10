import java.sql.DriverManager;
import java.sql.SQLException;

public class _7_ClosingDatabaseResources {

    /* Not closing them creates a resource leak that will eventually slow down your program. */

    /* Closing a JDBC resource should close any resources that it created. In particular, the following are true:
            - Closing a Connection also closes PreparedStatement (or CallableStatement) and ResultSet.
            - Closing a PreparedStatement (or CallableStatement) also closes the ResultSet. */

    /* There's another way to close a ResultSet. JDBC automatically closes a ResultSet when you run another
       SQL statement from the same Statement. This could be a PreparedStatement or a CallableStatement */

    public static void main(String[] args) throws SQLException {

        /* The following code closes 4 resources */
        var url = "jdbc:derby:zoo";
        var sql = "SELECT COUNT(*) FROM names WHERE is = ?";

        try (var conn = DriverManager.getConnection(url);
             var ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 1);
            var rs1 = ps.executeQuery();
            while (rs1.next()) System.out.println("Count: " + rs1.getInt(1));

            ps.setInt(1, 2);
            var rs2 = ps.executeQuery();
            while (rs2.next()) System.out.println("Count: " + rs2.getInt(1));

            rs2.close();

        }

    }



}
