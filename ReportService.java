import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private final Connection conn;

    public ReportService(Connection conn) {
        this.conn = conn;
    }

    // Report 1: Pay history for one employee — available to all roles
    public List<String[]> getPayHistory(int empID) {
        List<String[]> rows = new ArrayList<>();
        String sql = "SELECT pay_date, earnings, fed_tax, fed_med, fed_SS, " +
                     "state_tax, retire_401k, health_care " +
                     "FROM payroll WHERE empid = ? ORDER BY pay_date DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new String[]{
                        rs.getString("pay_date"),
                        rs.getString("earnings"),
                        rs.getString("fed_tax"),
                        rs.getString("fed_med"),
                        rs.getString("fed_SS"),
                        rs.getString("state_tax"),
                        rs.getString("retire_401k"),
                        rs.getString("health_care")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching pay history: " + e.getMessage());
        }
        return rows;
    }

    // Report 2: Total earnings grouped by job title — HR Admin only
    public List<String[]> getTotalPayByJobTitle(int month, int year) {
        List<String[]> rows = new ArrayList<>();
        String sql = "SELECT jt.job_title, SUM(p.earnings) AS total_pay " +
                     "FROM payroll p " +
                     "JOIN employee_job_titles ejt ON p.empid = ejt.empid " +
                     "JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                     "WHERE MONTH(p.pay_date) = ? AND YEAR(p.pay_date) = ? " +
                     "GROUP BY jt.job_title ORDER BY total_pay DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new String[]{
                        rs.getString("job_title"),
                        rs.getString("total_pay")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching pay by job title: " + e.getMessage());
        }
        return rows;
    }

    // Report 3: Total earnings grouped by division — HR Admin only
    public List<String[]> getTotalPayByDivision(int month, int year) {
        List<String[]> rows = new ArrayList<>();
        String sql = "SELECT d.Name AS division, SUM(p.earnings) AS total_pay " +
                     "FROM payroll p " +
                     "JOIN employee_division ed ON p.empid = ed.empid " +
                     "JOIN division d ON ed.div_ID = d.ID " +
                     "WHERE MONTH(p.pay_date) = ? AND YEAR(p.pay_date) = ? " +
                     "GROUP BY d.Name ORDER BY total_pay DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new String[]{
                        rs.getString("division"),
                        rs.getString("total_pay")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching pay by division: " + e.getMessage());
        }
        return rows;
    }

    // Report 4: Employees hired within a date range — HR Admin only
    public List<String[]> getNewHires(String startDate, String endDate) {
        List<String[]> rows = new ArrayList<>();
        String sql = "SELECT empid, Fname, Lname, email, HireDate " +
                     "FROM employees WHERE HireDate BETWEEN ? AND ? " +
                     "ORDER BY HireDate";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new String[]{
                        rs.getString("empid"),
                        rs.getString("Fname"),
                        rs.getString("Lname"),
                        rs.getString("email"),
                        rs.getString("HireDate")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching new hires: " + e.getMessage());
        }
        return rows;
    }
}
