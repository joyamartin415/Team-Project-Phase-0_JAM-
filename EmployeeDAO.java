
// EmployeeDAO.java — searchByEmpID(), searchByName(), searchByDOB(), searchBySSN()
// Returns Employee object or null


import java.sql.*;
import java.util.Scanner;

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
                "(fname, lname, email, dob, hireDate, salary, ssn, addressID, job_title_id, div_ID, mobilePhone, emergencyContactName, emergencyContactPhone) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
   
               stmt.setString(1, emp.getFname());
               stmt.setString(2, emp.getLname());
               stmt.setString(3, emp.getEmail());
               stmt.setString(4, emp.getDob());
               stmt.setString(5, emp.getHireDate());
               stmt.setDouble(6, emp.getSalary());
               stmt.setString(7, emp.getSSN());
               stmt.setInt(8, emp.getAddressID());
               stmt.setInt(9, emp.getJob_title_id());
               stmt.setInt(10, emp.getDiv_ID());
               stmt.setString(11, emp.getMobilePhone());
               stmt.setString(12, emp.getEmergencyContactName());
               stmt.setString(13, emp.getEmergencyContactPhone());
               stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Update Employee
    public boolean updateEmployee(int empID) {

        Employee emp = searchByEmpID(empID);
        if (emp == null) {
            System.out.println("Employee not found.");
            return false;
        }

        String[] fields = {
                "fname", "lname", "email", "dob", "hireDate", "salary", "ssn",
                "addressID", "job_title_id", "div_ID", "mobilePhone",
                "emergencyContactName", "emergencyContactPhone"
        };

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select field to update:");
        for (int i = 0; i < fields.length; i++) {
            System.out.println((i + 1) + ". " + fields[i]);
        }

        System.out.print("Enter selection (1-" + fields.length + "): ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid selection.");
            return false;
        }

        if (choice < 1 || choice > fields.length) {
            System.out.println("Invalid selection.");
            return false;
        }

        String selectedField = fields[choice - 1];

        System.out.print("Enter new value for " + selectedField + ": ");
        String newValue = scanner.nextLine().trim();
        if (newValue.isEmpty()) {
            System.out.println("No value entered.");
            return false;
        }

        System.out.print("Confirm update? (Y/N): ");
        String confirm = scanner.nextLine().trim();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Update cancelled.");
            return false;
        }

        String sql = "UPDATE employees SET " + selectedField + " = ? WHERE empID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            switch (selectedField) {
                case "salary":
                    stmt.setDouble(1, Double.parseDouble(newValue));
                    break;
                case "addressID":
                case "job_title_id":
                case "div_ID":
                    stmt.setInt(1, Integer.parseInt(newValue));
                    break;
                default:
                    stmt.setString(1, newValue);
            }

            stmt.setInt(2, empID);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee updated successfully.");
                return true;
            }
            System.out.println("No rows updated.");
            return false;
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            return false;
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
