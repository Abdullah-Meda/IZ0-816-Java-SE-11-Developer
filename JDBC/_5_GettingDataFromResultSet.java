import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class _5_GettingDataFromResultSet {

    /* A ResultSet is what you get when you query data, i.e. the SELECT statement */

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");

        /* Reading a ResultSet
            - A ResultSet has a cursor, which points to the current location in the data. */
        String sql = "SELECT id, name FROM Strings";
        Map<Integer, String> idToNameMap = new HashMap<>();

        try (var ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery() ) {
            while (rs.next()) {
                idToNameMap.put(rs.getInt("id"), rs.getString("name"));
                /* The above and the following which uses an index instead of a column name are equivalent
                        idToNameMap.put(rs.getInt(1), rs.getString(2)); */
            }
            System.out.println(idToNameMap);
        }

        /* Sometimes you want to get only one row from the table. Maybe you need only one piece of data. Or
           maybe the SQL is just returning the number of rows in the table. When you want only one row, you
           use an if statement rather than a while loop. */
        sql = "SELECT COUNT(*) FROM exhibits";
        try (var ps = conn.prepareStatement(sql);
             var rs = ps.executeQuery()) {

            if (rs.next()) {
                int count = rs.getInt(1);   // OR rs.getInt("count");
                System.out.println(count);
            }

            /* Attempting to access a column name or index that does not exist throws a SQLException, as does
               getting data from a ResultSet when it isn't pointing at a valid row. You need to be able to
               recognize such code

               To sum up this section, it is important to remember the following:
                   - Always use an if statement or while loop when calling rs.next().
                   - Column indexes begin with 1. */

        }

        // -----------------------------------------------------------------------------------------

        /* Getting Data from a Column */

            /* ResultSet get methods:
                Method name                     Return type
                -----------                     -----------
                getBoolean                      boolean
                getDouble                       double
                getInt                          int
                getLong                         long
                getObject                       Object
                getString                       String
            */

        sql = "SELECT id, name FROM exhibits";
        try (var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery()) {
            while (rs.next()) {
                Object idField = rs.getObject("id");
                Object nameField = rs.getObject("name");
                if (idField instanceof Integer) {
                    int id = (Integer) idField;
                    System.out.println(id);
                }
                if (nameField instanceof String) {
                    String name = (String) nameField;
                    System.out.println(name);
                }
            }
        }

        // ----------------------------------------------------------------------------

        /* We've been creating the PreparedStatement and ResultSet in the same try‐with‐resources statement.
           This doesn't work if you have bind variables because they need to be set in between. Luckily, we
           can nest try‐with‐ resources to handle this */
        sql = "SELECT id FROM exhibits WHERE name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Zebra");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id"));
                }
            }
        }

        conn.close();

    }

}
