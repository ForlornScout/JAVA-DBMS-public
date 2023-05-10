import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import java.sql.*;

public class DBMethods {

    // Class variables
    private static Connection conn = null;
    private static final String JDBS_DRIVER = "com.mysql.cj.jdbc.Driver";

    // Connect to the database
    public static void connect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBS_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver is broken...");
            e.printStackTrace();
            throw e;
        }

        System.out.println("MySQL Driver has been registered");

        // Establish connection
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password");
        } catch (SQLException e) {
            System.out.println("Connection failed... SAD");
            e.printStackTrace();
            throw e;
        }
    }

    // Get connection with return type
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "password");

        return conn;
    }

    // Disconnect from the database
    public static void disconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // Execute the passed in Query statement
    public static ResultSet dataExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSet crs = null;
        try {
            // Connect to the database
            connect();
            System.out.println("Select statement: " + queryStmt + "\n");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryStmt);

            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
        } catch (SQLException e) {
            System.out.println("Problem occured at dataExecuteQuery!");
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            disconnect();
        }
        return crs;
    }

    // Execute update in the database
    public static void dataExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            disconnect();
        }
    }
}
