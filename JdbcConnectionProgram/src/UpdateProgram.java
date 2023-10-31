import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateProgram {	// user defined Class 

	public static void main(String[] args) { // Main method
		
		int sid; // Declare a variable to store student ID.
		String department;	// Declare variables to store student name, department, and result
		
		try
		{
			Scanner sc = new Scanner(System.in);	// Create a Scanner object to read user input.
			// Prompt the user to enter a student ID and read the input.
			System.out.println("Enter Student Id");
			sid = sc.nextInt();
			// Prompt the user to enter a student department and read the input.
			System.out.println("Enter the Department Name to change");
			department = sc.next();
			
			// Load the MySQL JDBC driver class.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish a connection to the MySQL database.
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deeptech", "root", "123456");
			// Define an SQL query as a string with place holders for values.
			String str = "update students set department=? where sid=?";
			PreparedStatement ps =con.prepareStatement(str);
			ps.setString(1, department);
			 // Set the place holders in the prepared statement with the user input values.
			ps.setInt(2, sid);
			int status = ps.executeUpdate();
			// Check if the update was successful and display a message accordingly.
			if(status==1)
			{
				System.out.println("Update Success");
			}
			else
			{
				System.out.println("Update failed");
			} 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();	//print the exception details
		}

	}

}
