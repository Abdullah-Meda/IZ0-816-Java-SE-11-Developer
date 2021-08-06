public class _1_RelationalDatabasesAndSQL {

    /* - JDBC is an API
       - A database is an organized collection of data
       - A relational database is a database that is organized into tables, which consist of rows and columns  */

    /* CRUD operations:
           Operation       SQL Keyword         Description
           ----------------------------------------------------------
           Create          INSERT              Adds a new row to the table
           Read            SELECT              Retrieves data from the table
           Update          UPDATE              Changes zero or more rows in the table
           Delete          DELETE              Removes zero or more rows from the table
     */

    /* The INSERT statement is usually used to create one new row in a table; Here's an example:
            Eg: INSERT INTO exhibits VALUES (3, 'Asian Elephant', 7.5);

       The SELECT statement reads data from the table:
            Eg: SELECT * FROM exhibits WHERE ID = 3;
            Eg: SELECT name, num_acres FROM exhibits WHERE id = 3;
            Eg: SELECT COUNT(*), SUM(num_acres)
                FROM exhibits;

       The UPDATE statement changes zero or more rows in the database:
            Eg: UPDATE exhibits
                SET num_acres = num_acres + .5
                WHERE name = 'Asian Elephant';

       The DELETE statement deletes one or more rows in the database.
            Eg: DELETE FROM exhibits WHERE name = 'Asian Elephant';
     */

}
