import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/scsvmv_lab";
        String user = "root";
        String password = "root";

        // Step 1: Ensure MySQL Driver is loaded
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
            return;
        }

        // Step 2: Establish connection and execute query
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM student")) {

            // Table Header
            System.out.printf("%-8s %-12s %-5s%n", "REG", "NAME", "MARKS");
            System.out.println("---------------------------");

            // Output Records
            while (rs.next()) {
                System.out.printf("%-8s %-12s %-5d%n",
                        rs.getString("reg_no"),
                        rs.getString("name"),
                        rs.getInt("marks"));
            }

        } catch (SQLException e) {
            System.out.println("Database error : " + e.getMessage());
        }
    }
}
