import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private Connection conn;

    public ReportService(Connection conn) {
        this.conn = conn;
    }

    // Report 1: Pay history for one employee
    public List<String[]> getPayHistory(int empID) {
        List<String[]> results = new ArrayList<>();
        String sql = "SELECT payDate, salaryAmount FROM payroll WHERE empID = ? ORDER BY payDate DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(new String[]{
                        rs.getString("payDate"),
                        rs.getString("salaryAmount")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getPayHistory: " + e.getMessage());
        }

        return results;
    }

    // Report 2: Total pay grouped by job title for a given month/year
    public List<String[]> getTotalPayByJobTitle(int month, int year) {
        List<String[]> results = new ArrayList<>();
        String sql = """
            SELECT jt.title, SUM(p.salaryAmount) AS totalPay
            FROM payroll p
            JOIN employee_job_titles ejt ON p.empID = ejt.empID
            JOIN job_titles jt ON ejt.job_titleID = jt.job_titleID
            WHERE MONTH(p.payDate) = ? AND YEAR(p.payDate) = ?
            GROUP BY jt.title
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(new String[]{
                        rs.getString("title"),
                        rs.getString("totalPay")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getTotalPayByJobTitle: " + e.getMessage());
        }

        return results;
    }

    // Report 3: Total pay grouped by division for a given month/year
    public List<String[]> getTotalPayByDivision(int month, int year) {
        List<String[]> results = new ArrayList<>();
        String sql = """
            SELECT d.divisionName, SUM(p.salaryAmount) AS totalPay
            FROM payroll p
            JOIN employee_division ed ON p.empID = ed.empID
            JOIN division d ON ed.divID = d.divID
            WHERE MONTH(p.payDate) = ? AND YEAR(p.payDate) = ?
            GROUP BY d.divisionName
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(new String[]{
                        rs.getString("divisionName"),
                        rs.getString("totalPay")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getTotalPayByDivision: " + e.getMessage());
        }

        return results;
    }

    // Report 4: Employees hired between two dates
    public List<String[]> getNewHires(String startDate, String endDate) {
        List<String[]> results = new ArrayList<>();
        String sql = """
            SELECT empID, firstName, lastName, email, hireDate
            FROM employees
            WHERE hireDate BETWEEN ? AND ?
            ORDER BY hireDate
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(new String[]{
                        rs.getString("empID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("hireDate")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getNewHires: " + e.getMessage());
        }

        return results;
    }
}