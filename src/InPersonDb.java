/*******************************************************************
* Name: Abi Nakhle
* Date: 4/28/24
* Assignment: Course Project
*
* Class to handle all interactions with the InPersonTasks table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the table. 
* Searching by date is also an operation. Interactions are all done 
* using standard SQL syntax then executed by the SQLite JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InPersonDb {
    
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        String sql =
            "CREATE TABLE IF NOT EXISTS InPersonTasks (\n"
            + " Name varchar(20)\n"
            + " ,Desc varchar(60)\n"
            + " ,TimeRestricted varchar(10)\n"
            + " ,Urgency varchar(10)\n"
            + " ,Repeated varchar(10)\n"
            + " ,TaskDate varchar(20)\n"
            + " ,FullDay varchar(10)\n"
            + " ,Location varchar(30));";

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

    public static void addInPerson(Connection conn, InPerson i) {
        String sql =
            "INSERT INTO InPersonTasks(Name, Desc, TimeRestricted, Urgency, Repeated, TaskDate, FullDay, Location) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, i.Name);
            pst.setString(2, i.Desc);
            pst.setBoolean(3, i.TimeRestricted);
            pst.setString(4, i.Urgency);
            pst.setString(5, i.Repeated);
            pst.setString(6, i.TaskDate);
            pst.setBoolean(7, i.FullDay);
            pst.setString(8, i.Location);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateInPerson(Connection conn, InPerson i) {
        String sql =
            "UPDATE InPersonTasks SET Desc=?, TimeRestricted=?, Urgency=?, Repeated=?, TaskDate=?, FullDay=?, Location=? WHERE Name=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, i.Name);
            pst.setString(2, i.Desc);
            pst.setBoolean(3, i.TimeRestricted);
            pst.setString(4, i.Urgency);
            pst.setString(5, i.Repeated);
            pst.setString(6, i.TaskDate);
            pst.setBoolean(7, i.FullDay);
            pst.setString(8, i.Location);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteInPerson(Connection conn, String name) {
        String sql = "DELETE from InPersonTasks WHERE Name=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<InPerson> getAllInPersonTasks(Connection conn) {
        ArrayList<InPerson> inPersonTasks = new ArrayList<InPerson>();
        String sql = "SELECT * FROM InPersonTasks";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                InPerson i = new InPerson(rs.getString("Name"),
                    rs.getString("Desc"), rs.getBoolean("TimeRestricted"),
                    rs.getString("Urgency"), rs.getString("Repeated"), 
                    rs.getString("TaskDate"), rs.getBoolean("FullDay"),
                    rs.getString("Location"));
                inPersonTasks.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return inPersonTasks;
    }

    public static InPerson getInPerson(Connection conn, String name) {
        InPerson i = new InPerson();
        String sql = "SELECT * FROM InPersonTasks WHERE Name=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                i.Name = rs.getString("Name");
                i.Desc = rs.getString("Desc");
                i.TimeRestricted = rs.getBoolean("TimeRestricted");
                i.Urgency = rs.getString("Urgency");
                i.Repeated = rs.getString("Repeated");
                i.TaskDate = rs.getString("TaskDate");
                i.FullDay = rs.getBoolean("FullDay");
                i.Location = rs.getString("Location");
            } else {
                i.Name = name;
                i.Desc = "Not Found";
                i.TimeRestricted = false;
                i.Urgency = "Not Found";
                i.Repeated = "Not Found";
                i.TaskDate = "Not Found";
                i.FullDay = false;
                i.Location = "Not Found";
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return i;
    }

    public static ArrayList<InPerson> getAllInPersonTasksByDate(Connection conn, String taskDate) {
        ArrayList<InPerson> inPersonTasksDate = new ArrayList<InPerson>();
        String sql = "SELECT * FROM InPersonTasks WHERE TaskDate=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(6, taskDate);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                InPerson i = new InPerson(rs.getString("Name"),
                    rs.getString("Desc"), rs.getBoolean("TimeRestricted"),
                    rs.getString("Urgency"), rs.getString("Repeated"), 
                    rs.getString("TaskDate"), rs.getBoolean("FullDay"),
                    rs.getString("Location"));
                inPersonTasksDate.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return inPersonTasksDate;
    }
}