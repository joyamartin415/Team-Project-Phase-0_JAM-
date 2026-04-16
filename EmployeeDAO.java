
// EmployeeDAO.java — searchByEmpID(), searchByName(), searchByDOB(), searchBySSN()
// Returns Employee object or null

import java.sql.*;

public class EmployeeDAO {

    private String url = "jdbc:mysql://localhost:3306/employeedata";
    private String user = "root";
    private String password = "password";
    
    // Reusable Connection Method
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    // Method to insert an employee in database
    public void insertEmployee(Employee emp) {

        String sql = "insert into employees " +
                "(empID, fname, lname, email, dob, hireDate, salary, ssn, addressID, job_title_id, div_ID, mobilePhone, emergencyContactName, emergencyContactPhone) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
   
               stmt.setInt(1, emp.getEmpID());
               stmt.setString(2, emp.getFname());
               stmt.setString(3, emp.getLname());
               stmt.setString(4, emp.getEmail());
               stmt.setString(5, emp.getDob());
               stmt.setString(6, emp.getHireDate());
               stmt.setDouble(7, emp.getSalary());
               stmt.setString(8, emp.getSSN());
               stmt.setInt(9, emp.getAddressID());
               stmt.setInt(10, emp.getJob_title_id());
               stmt.setInt(11, emp.getDiv_ID());
               stmt.setString(12, emp.getMobilePhone());
               stmt.setString(13, emp.getEmergencyContactName());
               stmt.setString(14, emp.getEmergencyContactPhone());
               stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// Search by EmployeeID
    public Employee searchByEmpID(int empID) {

        String sql = "SELECT * FROM employees WHERE empID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empID);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Employee(
                            rs.getInt("empID"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("email"),
                            rs.getString("dob"),
                            rs.getString("hireDate"),
                            rs.getDouble("salary"),
                            rs.getString("ssn"),
                            rs.getInt("addressID"),
                            rs.getInt("job_title_id"),
                            rs.getInt("div_ID"),
                            rs.getString("mobilePhone"),
                            rs.getString("emergencyContactName"),
                            rs.getString("emergencyContactPhone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Search by Employee Name 
    public Employee searchByName(String name) {

        String sql = "SELECT * FROM employees WHERE fname LIKE ? OR lname LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Employee(
                            rs.getInt("empID"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("email"),
                            rs.getString("dob"),
                            rs.getString("hireDate"),
                            rs.getDouble("salary"),
                            rs.getString("ssn"),
                            rs.getInt("addressID"),
                            rs.getInt("job_title_id"),
                            rs.getInt("div_ID"),
                            rs.getString("mobilePhone"),
                            rs.getString("emergencyContactName"),
                            rs.getString("emergencyContactPhone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Search by Date of Birth
    public Employee searchByDOB(String dob) {

        String sql = "SELECT * FROM employees WHERE dob = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dob);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Employee(
                            rs.getInt("empID"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("email"),
                            rs.getString("dob"),
                            rs.getString("hireDate"),
                            rs.getDouble("salary"),
                            rs.getString("ssn"),
                            rs.getInt("addressID"),
                            rs.getInt("job_title_id"),
                            rs.getInt("div_ID"),
                            rs.getString("mobilePhone"),
                            rs.getString("emergencyContactName"),
                            rs.getString("emergencyContactPhone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Search by SSN
    public Employee searchBySSN(String ssn) {

        String sql = "SELECT * FROM employees WHERE ssn = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ssn);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Employee(
                            rs.getInt("empID"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("email"),
                            rs.getString("dob"),
                            rs.getString("hireDate"),
                            rs.getDouble("salary"),
                            rs.getString("ssn"),
                            rs.getInt("addressID"),
                            rs.getInt("job_title_id"),
                            rs.getInt("div_ID"),
                            rs.getString("mobilePhone"),
                            rs.getString("emergencyContactName"),
                            rs.getString("emergencyContactPhone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
