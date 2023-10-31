import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

class DeleteProgram {	// user defined Class 

	public static void main(String[] args) { // Main method
		String status;
		int sid;	// Declare a variable to store student ID.
		Scanner din = new Scanner(System.in);	// Create a Scanner object to read user input.
		// Prompt the user to enter a student ID and read the input.
		System.out.println("Enter Student Id");
		sid=din.nextInt();
		try
		{	// Load the MySQL JDBC driver class.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish a connection to the MySQL database.
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deeptech", "root", "123456");
			// Define an SQL query as a string with place holders for values.
			String str="delete from students where sid=?";
			PreparedStatement ps = con.prepareStatement(str);
			 // Set the place holders in the prepared statement with the user input values.
			ps.setInt(1, sid);
		System.out.println("Do u Really want to delete record Press y/Y to confirm or press Any key to cancel");
		status = din.next();
		// Check if the operation was successful and display a message accordingly.
		if(status.equalsIgnoreCase("y"))
		{
			int x= ps.executeUpdate();
			if(x==1)
			{
				System.out.println("deletion success");
			}
			else
			{
				System.out.println("deletion failed");
			}
		}
		else
		{
			System.out.println("Process  failed");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();	//print the exception details
		}
	}

}
