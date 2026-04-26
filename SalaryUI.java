import java.util.Scanner;

public class SalaryUI {

    private SalaryService salaryService;
    private Scanner scanner;

    public SalaryUI() {
        this.salaryService = new SalaryService();
        this.scanner = new Scanner(System.in);
    }

    public void showSalaryUpdateMenu() {
        System.out.println("\n=== Salary Range Update ===");

        double minSalary;
        System.out.print("Enter minimum salary: $");
        try {
            minSalary = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Returning to menu.");
            return;
        }

        double maxSalary;
        System.out.print("Enter maximum salary: $");
        try {
            maxSalary = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Returning to menu.");
            return;
        }

        if (minSalary > maxSalary) {
            System.out.println("Min cannot be greater than max. Returning to menu.");
            return;
        }

        double percentage;
        System.out.print("Enter increase percentage (e.g. 5 for 5%): ");
        try {
            percentage = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid percentage. Returning to menu.");
            return;
        }

        if (percentage <= 0) {
            System.out.println("Percentage must be greater than 0. Returning to menu.");
            return;
        }

        int count = salaryService.countEmployeesInRange(minSalary, maxSalary);
        System.out.println("\n" + count + " employee(s) found between $" +
                String.format("%.2f", minSalary) + " and $" +
                String.format("%.2f", maxSalary) + ".");

        if (count == 0) {
            System.out.println("No employees to update. Returning to menu.");
            return;
        }

        System.out.print("Apply " + percentage + "% increase to " + count + " employee(s)? (Y/N): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Update cancelled.");
            return;
        }

        int updated = salaryService.updateSalaryByRange(minSalary, maxSalary, percentage);

        if (updated >= 0) {
            System.out.println("Success! " + updated + " salary(ies) updated.");
        } else {
            System.out.println("Update failed. Please try again.");
        }
    }
}