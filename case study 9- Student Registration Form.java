import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegisterForm {
    public static void main(String[] args) {
        JFrame f = new JFrame("Student Registration");
        f.setLayout(new FlowLayout());

        // Form components
        JTextField name = new JTextField(12);
        JComboBox<String> br = new JComboBox<>(new String[]{"CSE", "IT", "ECE"});
        
        JRadioButton m = new JRadioButton("Male", true);
        JRadioButton fe = new JRadioButton("Female");
        ButtonGroup g = new ButtonGroup();
        g.add(m); 
        g.add(fe);

        // Feature 2: Added Hostel Required Checkbox
        JCheckBox hostel = new JCheckBox("Hostel required");

        JButton sub = new JButton("Submit");
        JButton clear = new JButton("Clear"); // Feature 1: Clear button

        // Feature 3: Table setup to display student details
        String[] columnNames = {"Name", "Branch", "Gender", "Hostel"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(380, 120));

        // Adding components to frame
        f.add(new JLabel("Name")); 
        f.add(name);
        f.add(new JLabel("Branch")); 
        f.add(br);
        f.add(m); 
        f.add(fe);
        f.add(hostel);
        f.add(sub);
        f.add(clear);
        f.add(scrollPane);

        // Submit Button Action
        sub.addActionListener(e -> {
            String studentName = name.getText().trim();
            if (studentName.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Name cannot be empty");
            } else {
                String branch = (String) br.getSelectedItem();
                String gender = m.isSelected() ? "Male" : "Female";
                String hostelReq = hostel.isSelected() ? "Yes" : "No";

                // Add record to JTable
                model.addRow(new Object[]{studentName, branch, gender, hostelReq});
            }
        });

        // Feature 1: Clear Button Action
        clear.addActionListener(e -> {
            name.setText("");
            br.setSelectedIndex(0);
            m.setSelected(true);
            hostel.setSelected(false);
            model.setRowCount(0); // Clears all table entries
        });

        f.setSize(420, 320);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
