import java.util.Scanner;

public class MenuHandler {

    private User currentUser;
    private ReportsUI reportsUI;
    private Scanner scanner;

    public MenuHandler(User user, ReportsUI reportsUI) {
        this.currentUser = user;
        this.reportsUI   = reportsUI;
        this.scanner     = new Scanner(System.in);
    }

    public void showMenu() {
        if (currentUser.isAdmin()) {
            showAdminMenu();
        } else {
            showEmployeeMenu();
        }
    }

    private void showAdminMenu() {
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n=== HR Admin Menu ===");
            System.out.println("1. View Pay History for Employee");
            System.out.println("2. Total Pay by Job Title");
            System.out.println("3. Total Pay by Division");
            System.out.println("4. View New Hires");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int empID = scanner.nextInt();
                    reportsUI.showPayHistory(empID);
                    break;
                case 2:
                    reportsUI.showPayByJobTitle();
                    break;
                case 3:
                    reportsUI.showPayByDivision();
                    break;
                case 4:
                    reportsUI.showNewHires();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showEmployeeMenu() {
        int choice = 0;

        while (choice != 2) {
            System.out.println("\n=== Employee Menu ===");
            System.out.println("1. View My Pay History");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reportsUI.showPayHistory(currentUser.getEmpID());
                    break;
                case 2:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}