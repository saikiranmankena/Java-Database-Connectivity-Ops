import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcInsert {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student" , "student");
			
			System.out.println("Database connection successful!\n");
			
			myStmt = myConn.createStatement();
			
			System.out.println("Inserting employees");
			int rowsAffected = myStmt.executeUpdate(
					"insert into employees " +
					"(last_name, first_name, email, department, salary) " + 
					"values " + 
					"('Wright', 'Eric', 'eric.wright@foo.com', 'HR', 33000.00)");
			
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}

}

