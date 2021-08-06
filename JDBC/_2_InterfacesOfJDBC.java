import java.sql.*;

public class _2_InterfacesOfJDBC {

    /* Key JDBC Interfaces:
            - Driver: Establishes a connection to the database
            - Connection: Sends commands to a database
            - PreparedStatement: Executes a SQL query
            - CallableStatement: Executes commands stored in the database
            - ResultSet: Reads results of a query
     */

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement("SELECT name FROM animal");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                System.out.println(rs.getString(1));
        }
    }


}
