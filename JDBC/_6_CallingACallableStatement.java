import java.sql.*;

public class _6_CallingACallableStatement {

    /* - A stored procedure is code that is compiled in advance and stored in the database.
       - Stored procedures are commonly written in a database‐specific variant of SQL, which varies among database
         software providers */

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");

        /* Calling a procedure without parameters */
        String sql = "{call read_e_names()}";
        try (CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getString(3));
            }
        }



        /* Passing an IN parameter
             - The read_names_by_letter() stored procedure takes a parameter for the prefix or first letter
               of the stored procedure.
             - An IN parameter is used for input. */
        sql = "{ call read_names_by_letter(?) }";
        try (var cs = conn.prepareCall(sql)) {
            cs.setString("prefix", "Z");   /* OR cs.setString(1, "Z"); */

            try (var rs = cs.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString(3));
                }
            }
        }



        /* Returning an OUT parameter
             - Stored procedures can have OUT parameters for output. The magic_number() stored procedure sets
               its OUT parameter to 42 */
        sql = "{ ?= call magic_number(?) }";       /* we included two special characters (?=) to specify that
                                                      the stored procedure has an output value */
        try (var cs = conn.prepareCall(sql)) {
            cs.registerOutParameter(1, Types.INTEGER);  /* Here, we register the OUT parameter. This
                                                                    is important. It allows JDBC to retrieve the
                                                                    value on line 51 */

            cs.execute();   /*  we call execute() instead of executeQuery() since we are
                                not returning a ResultSet from our stored procedure */
            System.out.println(cs.getInt(1));
        }



        /* Working with an INOUT parameter
            - Possible to use the same parameter for both input and output */
        sql = "{ call double_number(?) }";
        try (var cs = conn.prepareCall(sql)) {
            cs.setInt(1, 8);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            System.out.println(cs.getInt("num"));
        }

        conn.close();

    }

    /* Stored procedure parameter types:
                                                IN      OUT     INOUT
        Used for input                          Yes     No      Yes
        Used for output                         No      Yes     Yes
        Must set parameter value                Yes     No      Yes
        Must call registerOutParameter()        No      Yes     Yes
        Can include ?=                          No      Yes     Yes
    */

}
