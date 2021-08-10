import java.sql.*;

public class _4_PreparedStatements {

    /* PreparedStatement is far superior over Statement for the following reasons:
        Performance: In most programs, you run similar queries multiple times. A PreparedStatement figures out
                     a plan to run the SQL well and remembers it.
        Security:    As you will see in Chapter 22, “Security,” you are protected against an attack called SQL
                     injection when using a PreparedStatement correctly.
        Readability: It's nice not to have to deal with string concatenation in building a query string with lots
                     of parameters.
        Future use:  Even if your query is being run only once or doesn't have any parameters, you should still
                     use a PreparedStatement. That way future editors of the code won't add a variable and have
                     to remember to change to PreparedStatement then.
     */

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");

        /* Obtaining a PreparedStatement
             - The following doesn't compile as an SQL statement is mandatory when creating a PreparedStatement
                    try (var ps = conn.prepareStatement()) {}  // DOES NOT COMPILE
             - There are overloaded signatures that allow you to specify a ResultSet type and concurrency mode */
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM exhibits")) { }

        /* Modifying data using executeUpdate()
             - The method takes the SQL statement to run as a parameter.
             - It returns the number of rows that were inserted, deleted, or changed. */
        var insertSQL = "INSERT INTO exhibits VALUES(10, 'Deer', 3)";
        try (var ps = conn.prepareStatement(insertSQL)) {
            int result = ps.executeUpdate();
            System.out.println(result);                     // 1
        }

        var updateSQL = "UPDATE exhibits SET name = '' WHERE name = 'None'";
        try (PreparedStatement ps = conn.prepareStatement(updateSQL)) {
            System.out.println(ps.executeUpdate());         // 0
        }

        var deleteSQL = "DELETE FROM exhibits WHERE id = 10";
        try (PreparedStatement ps = conn.prepareStatement(deleteSQL)) {
            System.out.println(ps.executeUpdate());         // 1
        }



        /* Reading Data with executeQuery() */
        var selectSQL = "SELECT * FROM exhibits";
        try (var ps = conn.prepareStatement(selectSQL);
             ResultSet rs = ps.executeQuery()) {
        }



        /* Processing data with execute()
             - A method that can run either a query or an update
             - It returns a boolean so that we know whether there is a ResultSet
             - If the PreparedStatement refers to sql that is a SELECT, the boolean is true and we can get the
               ResultSet. If it is not a SELECT, we can get the number of rows updated. */
        try (PreparedStatement ps = conn.prepareStatement(selectSQL)) {
            boolean isResultSet = ps.execute();
            if (isResultSet) {
                try (ResultSet rs = ps.getResultSet()) {
                    System.out.println("Ran a query");
                }
            } else {
                int result = ps.getUpdateCount();
                System.out.println("Ran an update");
            }
        }



        /* What do you think happens if we use the wrong method for a SQL statement?
             - An SQLException is thrown
             - We can't get a compiler error since the SQL is a String! */



        /* Working with parameters
             - A bind variable is a placeholder that lets you specify the actual values at runtime
             - "?" is a bind variable
             - Notice how the bind variables are counted starting with 1 rather than 0.
             - Look at method addEntryUsingParameters() below */
        addEntryUsingParameters(conn, 6, 1, "Edith");

        /* Updating Multiple Times
             - Lets say we need to add 2 names to our database. We can use the same PreparedStatement object. */
        var sql = "INSERT INTO names VALUES(?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 20);
            ps.setInt(2, 1);
            ps.setString(3, "Ester");
            ps.executeUpdate();

            ps.setInt(1, 21);
            ps.setString(3, "Elias");
            /* NOTE how we did not give a value for the second bind variable! The PreparedStatement is smart
               enough to remember the parameters that were already set and retain them. You only have to set
               the ones that are different */
            ps.executeUpdate();
        }

    }

    public static void addEntryUsingParameters(Connection conn, int key, int type, String name) throws SQLException {
        String sql = "INSERT INTO names VALUES(?, ?, ?)";   /* Values will be substituted with the "?" */

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            /* Set the values */
            ps.setInt(1, key);          /* Notice how the bind variables are counted starting with
                                                       1 rather than 0. */
            ps.setString(3, name);
            ps.setInt(2, type);
            /* Execute the update */
            ps.executeUpdate();


            /* NOTE, an SQLException will be thrown in the following circumstances:
                - If you don't set all the bind variables before executeUpdate()
                - If you try to set more values than you have as bind variables  */


            /* The methods you need to know for the exam to set bind variables:
                  Method name         Parameter type          Example database type
                  -----------         --------------          ---------------------
                  setBoolean          Boolean                 BOOLEAN
                  setDouble           Double                  DOUBLE
                  setInt              Int                     INTEGER
                  setLong             Long                    BIGINT
                  setObject           Object                  Any type
                  setString           String                  CHAR, VARCHAR

              - Notice the setObject() method works with any Java type. If you pass a primitive, it will be
                autoboxed into a wrapper type
             */
        }

        conn.close();
    }

}
