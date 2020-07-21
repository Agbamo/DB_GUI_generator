package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.Manager;

public class DBManager {
		
	public static ResultSet insert(String query) throws SQLException {			
		String url="jdbc:ucanaccess://" +  Manager.getInstance().getDatabasePath(); 
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		con.close();
		return rs;
	}
}
