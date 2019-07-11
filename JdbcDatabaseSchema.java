
	import java.sql.Connection;
	import java.sql.DatabaseMetaData;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
public class JdbcDatabaseSchema {

		public static void main(String[] args) throws SQLException {

			String catalog = null;
			String schemaPattern = null;
			String tableNamePattern = null;
			String columnNamePattern = null;
			String[] types = null;

			Connection myConn = null;
			ResultSet myRs = null;

			try {
				myConn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");

				DatabaseMetaData databaseMetaData = myConn.getMetaData();

				System.out.println("List of Tables");
				System.out.println("--------------");

				myRs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern,
						types);

				while (myRs.next()) {
					System.out.println(myRs.getString("TABLE_NAME"));
				}

				System.out.println("\n\nList of Columns");
				System.out.println("--------------");

				myRs = databaseMetaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);

				while (myRs.next()) {
					System.out.println(myRs.getString("COLUMN_NAME"));
				}

			} catch (Exception exc) {
				exc.printStackTrace();
			} finally {
				close(myConn, myRs);
			}
		}

		private static void close(Connection myConn, ResultSet myRs)
				throws SQLException {

			if (myRs != null) {
				myRs.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

