/*******************************************************************
* Name: Abi Nakhle
* Date: 4/28/24
* Assignment: Course Project
*
* Class to handle all interactions with the DateRangedTasks table in the
* database, including creating the table if it doesn't exist and all
* CRUD (Create, Read Update, Delete) operations on the table. Searching
* for tasks by start and end dates are also operations. Interactions 
* are all done using standard SQL syntax then executed by the SQLite 
* JDBC library.
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DateRangedDb {
    
    public static boolean createTable(Connection conn) {
        // SQL statement for creating a new table
        String sql =
            "CREATE TABLE IF NOT EXISTS DateRangedTasks (\n"
            + " Name varchar(20)\n"
            + " ,Desc varchar(60)\n"
            + " ,TimeRestricted varchar(10)\n"
            + " ,Urgency varchar(10)\n"
            + " ,Repeated varchar(10)\n"
            + " ,StartDate varchar(20)\n"
            + " ,EndDate varchar(20));";

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

    public static void addDateRanged(Connection conn, DateRanged d) {
        String sql =
            "INSERT INTO DateRangedTasks(Name, Desc, TimeRestricted, Urgency, Repeated, StartDate, EndDate) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, d.Name);
            pst.setString(2, d.Desc);
            pst.setBoolean(3, d.TimeRestricted);
            pst.setString(4, d.Urgency);
            pst.setString(5, d.Repeated);
            pst.setString(6, d.StartDate);
            pst.setString(7, d.EndDate);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateDateRanged(Connection conn, DateRanged d) {
        String sql =
            "UPDATE DateRangedTasks SET Desc=?, TimeRestricted=?, Urgency=?, Repeated=?, StartDate=?, EndDate=? WHERE Name=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, d.Name);
            pst.setString(2, d.Desc);
            pst.setBoolean(3, d.TimeRestricted);
            pst.setString(4, d.Urgency);
            pst.setString(5, d.Repeated);
            pst.setString(6, d.StartDate);
            pst.setString(7, d.EndDate);
            pst.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDateRanged(Connection conn, String name) {
        String sql = "DELETE from DateRangedTasks WHERE Name=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<DateRanged> getAllDateRangedTasks(Connection conn) {
        ArrayList<DateRanged> dateRangedTasks = new ArrayList<DateRanged>();
        String sql = "SELECT * FROM DateRangedTasks";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                DateRanged d = new DateRanged(rs.getString("Name"),
                    rs.getString("Desc"), rs.getBoolean("TimeRestricted"),
                    rs.getString("Urgency"), rs.getString("Repeated"), 
                    rs.getString("StartDate"), rs.getString("EndDate"));
                dateRangedTasks.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dateRangedTasks;
    }

    public static DateRanged getDateRanged(Connection conn, String name) {
        DateRanged d = new DateRanged();
        String sql = "SELECT * FROM DateRangedTasks WHERE Name=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                d.Name = rs.getString("Name");
                d.Desc = rs.getString("Desc");
                d.TimeRestricted = rs.getBoolean("TimeRestricted");
                d.Urgency = rs.getString("Urgency");
                d.Repeated = rs.getString("Repeated");
                d.StartDate = rs.getString("StartDate");
                d.EndDate = rs.getString("EndDate");
            } else {
                d.Name = name;
                d.Desc = "Not Found";
                d.TimeRestricted = false;
                d.Urgency = "Not Found";
                d.Repeated = "Not Found";
                d.StartDate = "Not Found";
                d.EndDate = "Not Found";
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return d;
    }

    public static ArrayList<DateRanged> getAllDateRangedTasksByStart(Connection conn, String startDate) {
        ArrayList<DateRanged> dateRangedTasksStartDate = new ArrayList<DateRanged>();
        String sql = "SELECT * FROM DateRangedTasks WHERE StartDate=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(6, startDate);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                DateRanged d = new DateRanged(rs.getString("Name"),
                    rs.getString("Desc"), rs.getBoolean("TimeRestricted"),
                    rs.getString("Urgency"), rs.getString("Repeated"), 
                    rs.getString("StartDate"), rs.getString("EndDate"));
                dateRangedTasksStartDate.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dateRangedTasksStartDate;
    }

    public static ArrayList<DateRanged> getAllDateRangedTasksByEnd(Connection conn, String endDate) {
        ArrayList<DateRanged> dateRangedTasksEndDate = new ArrayList<DateRanged>();
        String sql = "SELECT * FROM DateRangedTasks WHERE EndDate=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(7, endDate);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                DateRanged d = new DateRanged(rs.getString("Name"),
                    rs.getString("Desc"), rs.getBoolean("TimeRestricted"),
                    rs.getString("Urgency"), rs.getString("Repeated"), 
                    rs.getString("StartDate"), rs.getString("EndDate"));
                dateRangedTasksEndDate.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dateRangedTasksEndDate;
    }
}
