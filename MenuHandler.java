import java.util.Scanner;
import javax.swing.SwingUtilities;

public class MenuHandler {

    private User currentUser;
    private ReportsUI reportsUI;
    private SalaryUI salaryUI;
    private Scanner scanner;

    public MenuHandler(User user, ReportsUI reportsUI) {
        this.currentUser = user;
        this.reportsUI   = reportsUI;
        this.scanner     = new Scanner(System.in);
        this.salaryUI    = new SalaryUI();
    }

    public void showMenu() {
        if (currentUser.isAdmin()) {
            showAdminMenu();
        } else {
            showEmployeeMenu();
        }
    }

    private void showAdminMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== HR Admin Menu ===");
            System.out.println("1. Search / View Employee");
            System.out.println("2. Add Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Update Salary by Range");
            System.out.println("6. View Pay History for Employee");
            System.out.println("7. Total Pay by Job Title");
            System.out.println("8. Total Pay by Division");
            System.out.println("9. View New Hires");
            System.out.println("10. Logout");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            EmployeeDAO dao = new EmployeeDAO();

            switch (choice) {
                case 1:
                    SwingUtilities.invokeLater(() -> new SearchUI("HR"));
                    break;
                case 2:
                    addEmployeeConsole(dao);
                    break;
                case 3:
                    System.out.print("Enter Employee ID to update: ");
                    try {
                        int empID = Integer.parseInt(scanner.nextLine().trim());
                        dao.updateEmployee(empID);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    try {
                        int empID = Integer.parseInt(scanner.nextLine().trim());
                        dao.deleteEmployee(empID);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;
                case 5:
                    salaryUI.showSalaryUpdateMenu();
                    break;
                case 6:
                    System.out.print("Enter Employee ID: ");
                    try {
                        int empID = Integer.parseInt(scanner.nextLine().trim());
                        reportsUI.showPayHistory(empID);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;
                case 7:
                    reportsUI.showPayByJobTitle();
                    break;
                case 8:
                    reportsUI.showPayByDivision();
                    break;
                case 9:
                    reportsUI.showNewHires();
                    break;
                case 10:
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showEmployeeMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Employee Menu ===");
            System.out.println("1. Search My Information");
            System.out.println("2. View My Pay History");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    SwingUtilities.invokeLater(() -> new SearchUI("EMPLOYEE"));
                    break;
                case 2:
                    reportsUI.showPayHistory(currentUser.getEmpID());
                    break;
                case 3:
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addEmployeeConsole(EmployeeDAO dao) {
        System.out.println("\n=== Add New Employee ===");
        try {
            System.out.print("First Name: ");
            String fname = scanner.nextLine().trim();
            System.out.print("Last Name: ");
            String lname = scanner.nextLine().trim();
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            System.out.print("DOB (YYYY-MM-DD): ");
            String dob = scanner.nextLine().trim();
            System.out.print("Hire Date (YYYY-MM-DD): ");
            String hireDate = scanner.nextLine().trim();
            System.out.print("Salary: ");
            double salary = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("SSN (XXX-XX-XXXX): ");
            String ssn = scanner.nextLine().trim();
            System.out.print("Address ID: ");
            int addressID = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Job Title ID: ");
            int jobTitleID = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Division ID: ");
            int divID = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Mobile Phone: ");
            String mobile = scanner.nextLine().trim();
            System.out.print("Emergency Contact Name: ");
            String ecName = scanner.nextLine().trim();
            System.out.print("Emergency Contact Phone: ");
            String ecPhone = scanner.nextLine().trim();

            Employee emp = new Employee(0, fname, lname, email, dob, hireDate,
                    salary, ssn, addressID, jobTitleID, divID, mobile, ecName, ecPhone);
            dao.insertEmployee(emp);
            System.out.println("Employee added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Employee not added.");
        }
    }
}