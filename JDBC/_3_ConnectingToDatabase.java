import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _3_ConnectingToDatabase {

    /* Building a JDBC URL:
        It has three parts in common. The first piece is always the same. It is the protocol jdbc. The second
        part is the subprotocol, which is the name of the database such as derby, mysql, or postgres. The third
        part is the subname, which is a database‐specific format. Colons (:) separate the three parts

      Protocol       Subname(Database Specific Connection details)
         ╎╎                         ╎
        ┌┴┴┐            ┌-----------┴-----------┐
        jdbc:postgresql://localhost:5432/ocp-book
            └----┬----┘
                 ╎
     Sub-Protocol(Product/Vendor Name)

     Some valid examples:
      - jdbc:postgresql://localhost/zoo
      - jdbc:oracle:thin:@123.123.123.123:1521:zoo
      - jdbc:mysql://localhost:3306
      - jdbc:mysql://localhost:3306/zoo?profileSQL=true */

    /* Establishing a Database Connection */
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        System.out.println(conn);

        /* There is also a signature that takes a username and a password. Example:
              public static void main(String[] args) throws SQLException {
                  Connection conn = DriverManager.getConnection(
                      "jdbc:postgresql://localhost:5432/ocp-book",
                      "username",
                      "Password20182");
                  System.out.println(conn);
              }
        */

        conn.close();
    }

}
