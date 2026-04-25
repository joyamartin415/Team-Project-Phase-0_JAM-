import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Local MySQL database connection
    private static final String URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String USER = "root";

    // Change this to YOUR MySQL password
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // Optional test method
    public static void main(String[] args) {
        Connection conn = getConnection();

        if (conn != null) {
            System.out.println("Connected to employee_management database successfully!");
        } else {
            System.out.println("Could not connect to the database.");
        }
    }
}