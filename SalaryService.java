import java.sql.*;

public class SalaryService {

    public int updateSalaryByRange(double minSalary, double maxSalary, double percentage) {
        String sql = "UPDATE employees SET salary = salary * (1 + ? / 100) " +
                     "WHERE salary BETWEEN ? AND ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, percentage);
            stmt.setDouble(2, minSalary);
            stmt.setDouble(3, maxSalary);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating salaries: " + e.getMessage());
            return -1;
        }
    }

    public int countEmployeesInRange(double minSalary, double maxSalary) {
        String sql = "SELECT COUNT(*) FROM employees WHERE salary BETWEEN ? AND ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, minSalary);
            stmt.setDouble(2, maxSalary);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error counting employees: " + e.getMessage());
        }

        return 0;
    }
}