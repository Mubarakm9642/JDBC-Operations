import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class CreateProgram {  // user defined Class 

	public static void main(String[] args) { // Main method
		
		int sid;	// Declare a variable to store student ID.
		String sname,department,result; // Declare variables to store student name, department, and result
		Scanner sc= new Scanner(System.in);	// Create a Scanner object to read user input.
		// Prompt the user to enter a student ID and read the input.
		System.out.println("Enter Student Id");
		sid = sc.nextInt();
		 // Prompt the user to enter a student name and read the input.
		System.out.println("Enter Student Name");
		sname = sc.next();
		// Prompt the user to enter a student department and read the input.
		System.out.println("Enter Student Department");
		department = sc.next();
		 // Prompt the user to enter a student result and read the input.
		System.out.println("Enter Student Result");
		result = sc.next();
		
		try
		{	// Load the MySQL JDBC driver class.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish a connection to the MySQL database.
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deeptech", "root", "123456");
			// Define an SQL query as a string with place holders for values.
			String str = "insert into students values(?,?,?,?)";
			PreparedStatement ps =con.prepareStatement(str);
			// Set the place holders in the prepared statement with the user input values.
			ps.setInt(1, sid);
			ps.setString(2, sname);
			ps.setString(3, department);
			ps.setString(4, result);
			int status = ps.executeUpdate();
			
			// Check if the insertion was successful and display a message accordingly.
			if(status==1)
			{
				System.out.println("Student Record Saved..");
			}
			else
			{
				System.out.println("Record failed to Save");
			} 
		}
		catch(Exception e)
		{
			e.printStackTrace();	//print the exception details
		}

	}

}
