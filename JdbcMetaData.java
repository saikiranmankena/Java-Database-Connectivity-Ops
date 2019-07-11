	import java.sql.Connection;
	import java.sql.DatabaseMetaData;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class JdbcMetaData {

		public static void main(String[] args) throws SQLException {

			Connection myConn = null;

			try {
				myConn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");

				DatabaseMetaData databaseMetaData = myConn.getMetaData();
				
				System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
				System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
				System.out.println();
				
				System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
				System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());
				
			} catch (Exception exc) {
				exc.printStackTrace();
			} finally {
				close(myConn);
			}
		}

		private static void close(Connection myConn)
				throws SQLException {

			if (myConn != null) {
				myConn.close();
			}
		}
	}
