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
            + " Name varchar(20)\n"
            + " ,Desc varchar(60)\n"
            + " ,TimeRestricted varchar(10)\n"
            + " ,Urgency varchar(10)\n"
            + " ,Repeated varchar(10)\n"
            + " ,TaskDate date/time\n"
            + " ,FullDay varchar(10);";

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

    public static void addOneDay(Connection conn, OneDay o) {
        String sql =
            "INSERT INTO OneDayTasks(Name, Desc, TimeRestricted, Urgency, Repeated, TaskDate, FullDay) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, o.Name);
            pst.setString(2, o.Desc);
            pst.setBoolean(3, o.TimeRestricted);
            pst.setString(4, o.Urgency);
            pst.setString(5, o.Repeated);
            pst.setDate(6, o.TaskDate);
            pst.setBoolean(7, o.FullDay);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateOneDay(Connection conn, OneDay o) {
        String sql =
            "UPDATE OneDayTasks SET Desc=?, TimeRestricted=?, Urgency=?, Repeated=?, TaskDate=?, FullDay=? WHERE Name=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, o.Name);
            pst.setString(2, o.Desc);
            pst.setBoolean(3, o.TimeRestricted);
            pst.setString(4, o.Urgency);
            pst.setString(5, o.Repeated);
            pst.setDate(6, o.TaskDate);
            pst.setBoolean(7, o.FullDay);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteOneDay(Connection conn, String name) {
        String sql = "DELETE from OneDayTasks WHERE Name=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<OneDay> getAllOneDayTasks(Connection conn) {
        ArrayList<OneDay> oneDayTasks = new ArrayList<OneDay>();
        String sql = "SELECT * FROM OneDayTasks";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                OneDay o = new OneDay(rs.getString("Name"),
                    rs.getString("Desc"), rs.getBoolean("TimeRestricted"),
                    rs.getString("Urgency"), rs.getString("Repeated"), 
                    rs.getDate("TaskDate"), rs.getBoolean("FullDay"));
                oneDayTasks.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return oneDayTasks;
    }

    public static OneDay getOneDay(Connection conn, String name) {
        OneDay o = new OneDay();
        String sql = "SELECT * FROM People WHERE ID=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p.ID = rs.getInt("ID");
                p.FirstName = rs.getString("FirstName");
                p.LastName = rs.getString("LastName");
                p.Age = rs.getInt("Age");
            } else {
                p.ID = id;
                p.FirstName = "Not";
                p.LastName = "Found";
                p.Age = 999;
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return p;
    }
}
