/*******************************************************************
* Name: Abi Nakhle
* Date: 4/26/24
* Assignment: Course Project
*
* Class to handle all interactions with the Persons table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the Persons table.
* Note that the interactions are all done using standard SQL syntax
* that is then executed by the SQLite JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OneDayDb {
    
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        String sql =
            "CREATE TABLE IF NOT EXISTS OneDayTasks (\n"
            + " ID integer PRIMARY KEY\n"
            + " ,FirstName varchar(20)\n"
            + " ,LastName varchar(40)\n"
            + " ,Age integer);";

        System.out.println(sql);

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
