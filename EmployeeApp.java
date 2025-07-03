import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {

    static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String DB_USER = "root";         // replace with your username
    static final String DB_PASS = "rahul";     // replace with your password

    public static void main(String[] args) {

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n=== Employee DB Menu ===");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addEmployee(con, sc);
                        break;
                    case 2:
                        viewEmployees(con);
                        break;
                    case 3:
                        updateEmployee(con, sc);
                        break;
                    case 4:
                        deleteEmployee(con, sc);
                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addEmployee(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter department: ");
        String dept = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, dept);
        pst.setDouble(3, salary);

        int rows = pst.executeUpdate();
        System.out.println(rows > 0 ? "Employee added successfully." : "Failed to add employee.");
    }

    static void viewEmployees(Connection con) throws SQLException {
        String sql = "SELECT * FROM employees";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        System.out.println("\nID\tName\tDepartment\tSalary");
        System.out.println("---------------------------------------------");
        while (rs.next()) {
            System.out.printf("%d\t%s\t%s\t%.2f\n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("salary"));
        }
    }

    static void updateEmployee(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("New name: ");
        String name = sc.nextLine();
        System.out.print("New department: ");
        String dept = sc.nextLine();
        System.out.print("New salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, dept);
        pst.setDouble(3, salary);
        pst.setInt(4, id);

        int rows = pst.executeUpdate();
        System.out.println(rows > 0 ? "Employee updated successfully." : "Employee ID not found.");
    }

    static void deleteEmployee(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employees WHERE id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);

        int rows = pst.executeUpdate();
        System.out.println(rows > 0 ? "Employee deleted successfully." : "Employee ID not found.");
    }
}
