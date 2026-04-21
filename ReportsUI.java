import java.util.List;
import java.util.Scanner;

public class ReportsUI {

    private final ReportService reportService;
    private final User currentUser;
    private final Scanner scanner;

    public ReportsUI(ReportService reportService, User currentUser) {
        this.reportService = reportService;
        this.currentUser   = currentUser;
        this.scanner       = new Scanner(System.in);
    }

    // Called from MenuHandler — shows pay history for the given empID
    public void showPayHistory(int empID) {
        List<String[]> rows = reportService.getPayHistory(empID);
        System.out.println("\n--- Pay History (Employee " + empID + ") ---");
        System.out.printf("%-12s %10s %10s %10s %10s %10s %10s %10s%n",
            "Pay Date", "Earnings", "Fed Tax", "Fed Med", "Fed SS",
            "State Tax", "401k", "Health");
        System.out.println("-".repeat(94));
        if (rows.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (String[] r : rows) {
                System.out.printf("%-12s %10s %10s %10s %10s %10s %10s %10s%n",
                    r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7]);
            }
        }
    }

    // HR Admin only
    public void showPayByJobTitle() {
        if (!currentUser.isAdmin()) {
            System.out.println("Access denied: HR Admin only.");
            return;
        }
        int[] monthYear = promptMonthYear();
        List<String[]> rows = reportService.getTotalPayByJobTitle(monthYear[0], monthYear[1]);
        System.out.println("\n--- Total Pay by Job Title (" + monthYear[0] + "/" + monthYear[1] + ") ---");
        System.out.printf("%-20s %12s%n", "Job Title", "Total Pay");
        System.out.println("-".repeat(34));
        if (rows.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (String[] r : rows) {
                System.out.printf("%-20s %12s%n", r[0], r[1]);
            }
        }
    }

    // HR Admin only
    public void showPayByDivision() {
        if (!currentUser.isAdmin()) {
            System.out.println("Access denied: HR Admin only.");
            return;
        }
        int[] monthYear = promptMonthYear();
        List<String[]> rows = reportService.getTotalPayByDivision(monthYear[0], monthYear[1]);
        System.out.println("\n--- Total Pay by Division (" + monthYear[0] + "/" + monthYear[1] + ") ---");
        System.out.printf("%-20s %12s%n", "Division", "Total Pay");
        System.out.println("-".repeat(34));
        if (rows.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (String[] r : rows) {
                System.out.printf("%-20s %12s%n", r[0], r[1]);
            }
        }
    }

    // HR Admin only
    public void showNewHires() {
        if (!currentUser.isAdmin()) {
            System.out.println("Access denied: HR Admin only.");
            return;
        }
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.next();
        System.out.print("Enter end date   (YYYY-MM-DD): ");
        String endDate = scanner.next();

        List<String[]> rows = reportService.getNewHires(startDate, endDate);
        System.out.println("\n--- New Hires (" + startDate + " to " + endDate + ") ---");
        System.out.printf("%-6s %-15s %-15s %-30s %-12s%n",
            "ID", "First Name", "Last Name", "Email", "Hire Date");
        System.out.println("-".repeat(80));
        if (rows.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (String[] r : rows) {
                System.out.printf("%-6s %-15s %-15s %-30s %-12s%n",
                    r[0], r[1], r[2], r[3], r[4]);
            }
        }
    }

    private int[] promptMonthYear() {
        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter year (e.g. 2024): ");
        int year = scanner.nextInt();
        return new int[]{month, year};
    }
}
