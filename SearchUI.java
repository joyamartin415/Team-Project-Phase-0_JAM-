import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchUI extends JFrame {

    private EmployeeDAO dao = new EmployeeDAO();

    private JTextField searchField = new JTextField(15);

    private JTextField idField = new JTextField();
    private JTextField fnameField = new JTextField();
    private JTextField lnameField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField dobField = new JTextField();
    private JTextField hireDateField = new JTextField();
    private JTextField salaryField = new JTextField();
    private JTextField ssnField = new JTextField();

    private JButton searchBtn = new JButton("Search");

    private String role;

    public SearchUI(String role) {

        this.role = role;

        setTitle("Employee Search System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2));

        add(new JLabel("Search (ID / Name / DOB / SSN):"));
        add(searchField);

        add(searchBtn);

        add(new JLabel("ID:"));
        add(idField);

        add(new JLabel("First Name:"));
        add(fnameField);

        add(new JLabel("Last Name:"));
        add(lnameField);

        add(new JLabel("Email:"));
        add(emailField);

        add(new JLabel("DOB:"));
        add(dobField);

        add(new JLabel("Hire Date:"));
        add(hireDateField);

        add(new JLabel("Salary:"));
        add(salaryField);

        add(new JLabel("SSN:"));
        add(ssnField);

        setFieldAccess();

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        setVisible(true);
    }


    private void setFieldAccess() {

        boolean editable = role.equalsIgnoreCase("HR");

        idField.setEditable(false);

        fnameField.setEditable(editable);
        lnameField.setEditable(editable);
        emailField.setEditable(editable);
        dobField.setEditable(editable);
        hireDateField.setEditable(editable);
        salaryField.setEditable(editable);

        ssnField.setEditable(false);
    }


    private void performSearch() {

        String input = searchField.getText();

        Employee emp = null;

        try {
            int id = Integer.parseInt(input);
            emp = dao.searchByEmpID(id);
        } catch (Exception e) {
          
        }

        if (emp == null) {
            emp = dao.searchByName(input);
        }

        if (emp == null) {
            emp = dao.searchByDOB(input);
        }

        if (emp == null) {
            emp = dao.searchBySSN(input);
        }

        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Employee not found.");
            return;
        }

        populateFields(emp);
    }

    // Display 
    private void populateFields(Employee emp) {

        idField.setText(String.valueOf(emp.getEmpID()));
        fnameField.setText(emp.getFname());
        lnameField.setText(emp.getLname());
        emailField.setText(emp.getEmail());
        dobField.setText(emp.getDob());
        hireDateField.setText(emp.getHireDate());
        salaryField.setText(String.valueOf(emp.getSalary()));
        ssnField.setText(emp.getSSN());
    }


    public static void main(String[] args) {

        new SearchUI("HR");
    }
}