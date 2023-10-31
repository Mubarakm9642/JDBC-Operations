import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Display {	// user defined Class 

	public static void main(String[] args) {	// Main method
		
		int sid,mark1,mark2,mark3,total;	// Declare variables to store student ID, student mark1,mark2,mark3,total
		double average; // Declare variables to store student average
		String sname,department,result,grade;	// Declare variables to store student name, department, and result,grade
		
		try
		{	// Load the MySQL JDBC driver class.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish a connection to the MySQL database.
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deeptech", "root", "123456");
			Statement st=con.createStatement();
			// Define an SQL query as a string with place holders for values.
			ResultSet rs=st.executeQuery("select * from students");
			System.out.println("sid\tSname\tdepart\tresult");
			System.out.println("");
			while(rs.next()) 
			{	 // Set the placeholders in the prepared statement with the user input values.
				sid=rs.getInt(1);
				sname=rs.getString(2);
				department = rs.getString(3);
				result=rs.getString(4);
				System.out.println(sid+"\t"+sname+"\t"+department+"\t"+result);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	//print the exception details
		}
	}

}
