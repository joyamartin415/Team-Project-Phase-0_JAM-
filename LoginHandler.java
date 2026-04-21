import java.sql.*;

public class LoginHandler {

    private Connection conn;

    public LoginHandler(Connection conn) {
        this.conn = conn;
    }

    public User login(String username, String password) {
        String sql = "SELECT userID, empID, username, role FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int userID  = rs.getInt("userID");
                    int empID   = rs.getInt("empID");
                    String role = rs.getString("role");
                    return new User(userID, empID, username, role);
                }
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null; // login failed — caller should display error message
    }
}