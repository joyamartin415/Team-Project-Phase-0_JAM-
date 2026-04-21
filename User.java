public class User {
    private int userID;
    private int empID;
    private String username;
    private String role; // "ADMIN" or "EMPLOYEE"

    // Constructor
    public User(int userID, int empID, String username, String role) {
        this.userID = userID;
        this.empID = empID;
        this.username = username;
        this.role = role;
    }

    // Getters
    public int getUserID() { return userID; }
    public int getEmpID() { return empID; }
    public String getUsername() { return username; }
    public String getRole() { return role; }

    // Helper — you'll use this in MenuHandler
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }
}