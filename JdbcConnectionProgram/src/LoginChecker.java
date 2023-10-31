	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.util.Scanner;
public class LoginChecker {
	    public static void main(String[] args) {
	    	
	    	Scanner sc=new Scanner(System.in);
	    	
	    	// user Input for username and password
	    	System.out.println("Enter your user name: ");
	    	String inputUsername = sc.next();
	    	System.out.println("Enter your user password: ");
	        String inputPassword = sc.next(); 

	        // SQL query to retrieve user information
	        String sqlQuery = "SELECT password FROM users WHERE username = ?";

	        try (
	            // Establishing a database connection
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deeptech", "root", "123456");
	            
	            // Prepare the SQL statement
	            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
	        ) {
	            // Setting the input username as a parameter in the query
	            preparedStatement.setString(1, inputUsername);

	            // Execute the query
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                // Retrieving the stored password from the database
	                String storedPassword = resultSet.getString("password");

	                // Comparing the stored password with the input password
	                if (inputPassword.equals(storedPassword)) {
	                    System.out.println("Login Successful!");
	                } else {
	                    System.out.println("Login Failed: Incorrect Password");
	                }
	            } 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }



}
