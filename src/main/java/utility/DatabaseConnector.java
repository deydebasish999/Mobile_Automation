package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class DatabaseConnector {
	Properties prop = null;
	Connection conn = null;
	ConfigFileReader reader = new ConfigFileReader();
	Statement statement = null;
	ResultSet rs= null;
	
//Need to Test
	public void setDbConnection() {
		prop = new Properties();
		prop.setProperty("user", reader.getDBUsername());
		prop.setProperty("password", reader.getDBPassword());
		prop.setProperty("ssl", "true");
		String url = reader.getDBConnectionURL();
		Map<Object,Object> dbData = new HashMap<Object,Object>();

		// Connection to SQL
		try {
			conn = DriverManager.getConnection(url, prop);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn != null) {
			System.out.println("Connection to DB created successfully");
		}

		try {

			statement = conn.createStatement();
			String query = ReadDataFromExcel.getQueryFromDbSheet();
			System.out.println(query);
			rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount=rsmd.getColumnCount();
			for(int i=0;i<colCount;i++) {
				dbData.put(rsmd.getColumnName(i), rs.next());
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
