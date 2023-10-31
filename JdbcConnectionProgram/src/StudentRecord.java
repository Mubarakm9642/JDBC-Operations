import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentRecord {

	public static void main(String[] args) {
		
		int sid,mark1,mark2,mark3,total;
		String sname,department,result,grade;
		double average;
		int i,n;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of student records");
		n = sc.nextInt();
		
		for(i=0;i<n;i++)
		{
			System.out.println("Enter Student ID");
			sid = sc.nextInt();
			System.out.println("Enter the Student Name");
			sname = sc.next();
			System.out.println("Enter Student Department");
			department = sc.next();
			System.out.println("Enter 3 Subjects Marks");
			mark1 = sc.nextInt();
			mark2 = sc.nextInt();
			mark3 = sc.nextInt();
			
			total = mark1 + mark2 + mark3;
			
			average = total/3;
			if(mark1>=35 && mark2>=35 && mark3>=35)
			{
				result = "pass";
			}
			else
			{
				result = "fail";
			}
			
			if(average>=90)
			{
				grade = "A";
			}
			else if(average>=75)
			{
				grade = "B";
			}
			else if(average>=58)
			{
				grade = "C";
			}
			else if(average>=35)
			{
				grade = "D";
			}
			else
			{
				grade = "NIL";
			}
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deeptech", "root", "123456");
				String str = "insert into student1 values(?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps =con.prepareStatement(str);
				ps.setInt(1, sid);
				ps.setString(2, sname);
				ps.setString(3, department);
				ps.setInt(4, mark1);
				ps.setInt(5, mark2);
				ps.setInt(6, mark3);
				ps.setInt(7, total);
				ps.setDouble(8, average);
				ps.setString(9, result);
				ps.setString(10, grade);
				int status = ps.executeUpdate();
				
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
				e.printStackTrace();
			}
		}

	}

}
