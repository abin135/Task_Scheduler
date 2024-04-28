/*******************************************************************
* Name: Abi Nakhle
* Date: 4/28/24
* Assignment: Course Project
*
* Class to handle all interactions with the OneDayTasks table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the table. 
* Interactions are all done using standard SQL syntax then executed by 
* the SQLite JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskDb {
    
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        String sql =
            "CREATE TABLE IF NOT EXISTS Tasks (\n"
            + " Name varchar(20)\n"
            + " ,Desc varchar(60)\n"
            + " ,TimeRestricted varchar(10)\n"
            + " ,Urgency varchar(10)\n"
            + " ,Repeated varchar(10));";

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

    public static void addTask(Connection conn, Task t) {
        String sql =
            "INSERT INTO Tasks(Name, Desc, TimeRestricted, Urgency, Repeated) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.Name);
            pst.setString(2, t.Desc);
            pst.setBoolean(3, t.TimeRestricted);
            pst.setString(4, t.Urgency);
            pst.setString(5, t.Repeated);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTask(Connection conn, Task t) {
        String sql =
            "UPDATE Tasks SET Desc=?, TimeRestricted=?, Urgency=?, Repeated=? WHERE Name=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.Name);
            pst.setString(2, t.Desc);
            pst.setBoolean(3, t.TimeRestricted);
            pst.setString(4, t.Urgency);
            pst.setString(5, t.Repeated);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(Connection conn, String name) {
        String sql = "DELETE from Tasks WHERE Name=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> getAllTasks(Connection conn) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String sql = "SELECT * FROM Tasks";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Task t = new Task(rs.getString("Name"),
                    rs.getString("Desc"), rs.getBoolean("TimeRestricted"),
                    rs.getString("Urgency"), rs.getString("Repeated"));
                tasks.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    public static Task getTask(Connection conn, String name) {
        Task t = new Task();
        String sql = "SELECT * FROM Tasks WHERE Name=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                t.Name = rs.getString("Name");
                t.Desc = rs.getString("Desc");
                t.TimeRestricted = rs.getBoolean("TimeRestricted");
                t.Urgency = rs.getString("Urgency");
                t.Repeated = rs.getString("Repeated");
            } else {
                t.Name = name;
                t.Desc = "Not Found";
                t.TimeRestricted = false;
                t.Urgency = "Not Found";
                t.Repeated = "Not Found";
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return t;
    }
}