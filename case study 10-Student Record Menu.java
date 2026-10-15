//part-1-db.java//
import java.sql.*;

public class Db {
    public static Connection open() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/scsvmv_lab", "root", "root");
    }
}

//part-2-student app.java//
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Scanner;

public class StudentApp {

    // Helper method to execute SELECT, UPDATE, DELETE queries
    static void run(String sql, Object... v) {
        try (Connection c = Db.open(); 
             PreparedStatement p = c.prepareStatement(sql)) {
            
            for (int i = 0; i < v.length; i++) {
                p.setObject(i + 1, v[i]);
            }

            if (sql.trim().toUpperCase().startsWith("SELECT")) {
                try (ResultSet rs = p.executeQuery()) {
                    boolean found = false;
                    while (rs.next()) {
                        found = true;
                        System.out.printf("Reg No: %-8s | Name: %-12s | Marks: %d%n",
                                rs.getString(1), rs.getString(2), rs.getInt(3));
                    }
                    if (!found) {
                        System.out.println("No matching record found.");
                    }
                }
            } else {
                int rows = p.executeUpdate();
                System.out.println(rows + " row(s) affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Feature 3: Show database records inside a Swing JTable
    static void showInJTable() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Database View");
            String[] columnNames = {"Reg No", "Name", "Marks"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(model);

            try (Connection c = Db.open();
                 Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM student")) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("reg_no"),
                        rs.getString("name"),
                        rs.getInt("marks")
                    });
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + e.getMessage());
                return;
            }

            frame.add(new JScrollPane(table));
            frame.setSize(400, 250);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Feature 1: Menu Loop (Options 1 to 5)
        while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT MENU ===");
            System.out.println("1. Search Student");
            System.out.println("2. Update Marks");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All in Swing JTable");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Register Number to Search: ");
                    String searchReg = sc.nextLine();
                    run("SELECT * FROM student WHERE reg_no = ?", searchReg);
                    break;

                case 2:
                    System.out.print("Enter Register Number to Update: ");
                    String updateReg = sc.nextLine();
                    System.out.print("Enter New Marks: ");
                    int newMarks = Integer.parseInt(sc.nextLine().trim());
                    run("UPDATE student SET marks = ? WHERE reg_no = ?", newMarks, updateReg);
                    break;

                case 3:
                    // Feature 2: Delete Option
                    System.out.print("Enter Register Number to Delete: ");
                    String deleteReg = sc.nextLine();
                    run("DELETE FROM student WHERE reg_no = ?", deleteReg);
                    break;

                case 4:
                    // Feature 3: Display Records in Swing JTable
                    System.out.println("Opening Swing JTable GUI window...");
                    showInJTable();
                    break;

                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select 1-5.");
            }
        }
    }
}
