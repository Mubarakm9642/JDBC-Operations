import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;	

public class StudentCRUD {
	    public static void main(String[] args) {
	        try (
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deeptech", "root", "123456");
	            Scanner scanner = new Scanner(System.in)
	        ) {
	            while (true) {
	                System.out.println("Choose operation:");
	                System.out.println("1. Create");
	                System.out.println("2. Read");
	                System.out.println("3. Update");
	                System.out.println("4. Delete");
	                System.out.println("5. Exit");
	                int choice = scanner.nextInt();

	                switch (choice) {
	                    case 1:
	                        createStudent(connection, scanner);
	                        break;
	                    case 2:
	                        readStudent(connection);
	                        break;
	                    case 3:
	                        updateStudent(connection, scanner);
	                        break;
	                    case 4:
	                        deleteStudent(connection, scanner);
	                        break;
	                    case 5:
	                        System.out.println("Exiting...");
	                        return;
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void createStudent(Connection connection, Scanner scanner) throws SQLException {
	        System.out.println("Enter student roll:");
	        int roll = scanner.nextInt();
	        System.out.println("Enter student name:");
	        String name = scanner.next();
	        System.out.println("Enter student average marks:");
	        double averageMarks = scanner.nextDouble();
	        System.out.println("Enter student grade:");
	        String grade = scanner.next();

	        String sql = "INSERT INTO studentCRUD (roll, name, averagemarks, grade) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, roll);
	            preparedStatement.setString(2, name);
	            preparedStatement.setDouble(3, averageMarks);
	            preparedStatement.setString(4, grade);

	            int rowsInserted = preparedStatement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Student record created successfully.");
	            } else {
	                System.out.println("Failed to create student record.");
	            }
	        }
	    }

	    private static void readStudent(Connection connection) throws SQLException {
	        String sql = "SELECT * FROM studentCRUD";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                int roll = resultSet.getInt("roll");
	                String name = resultSet.getString("name");
	                double averageMarks = resultSet.getDouble("averagemarks");
	                String grade = resultSet.getString("grade");

	                System.out.println("Roll: " + roll + ", Name: " + name + ", Average Marks: " + averageMarks + ", Grade: " + grade);
	            }
	        }
	    }

	    private static void updateStudent(Connection connection, Scanner scanner) throws SQLException {
	        System.out.println("Enter student roll to update:");
	        int roll = scanner.nextInt();
	        System.out.println("Enter new average marks:");
	        double averageMarks = scanner.nextDouble();
	        System.out.println("Enter new grade:");
	        String grade = scanner.next();

	        String sql = "UPDATE studentCRUD SET averagemarks = ?, grade = ? WHERE roll = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setDouble(1, averageMarks);
	            preparedStatement.setString(2, grade);
	            preparedStatement.setInt(3, roll);

	            int rowsUpdated = preparedStatement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Student record updated successfully.");
	            } else {
	                System.out.println("Student not found or update failed.");
	            }
	        }
	    }

	    private static void deleteStudent(Connection connection, Scanner scanner) throws SQLException {
	        System.out.println("Enter student roll to delete:");
	        int roll = scanner.nextInt();

	        String sql = "DELETE FROM studentCRUD WHERE roll = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, roll);

	            int rowsDeleted = preparedStatement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Student record deleted successfully.");
	            } else {
	                System.out.println("Student not found or delete failed.");
	            }
	        }
	    }
}
