package domain;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableScraper {
	
	Connection con;
	ResultSet rs;
	ArrayList<String> columnNames;
	
	public TableScraper(String url, String tableName) throws SQLException {
		super();
		con = null;
		rs = null;
		columnNames = new ArrayList<String>();
		retrieveTableContents(url, tableName);
	}
		
	public void retrieveTableContents(String tableDNS, String tableName) throws SQLException {		
		
		//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String url="jdbc:ucanaccess:" + /*System.getProperty("file.separator")*/ "//" +  tableDNS; 
		
		con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		// Creamos la consulta de selección.
		String sql = "SELECT * FROM " + tableName;
		// Ejecutamos la consulta y extraemos los datos
		rs = st.executeQuery(sql);
		// Extraemos la información estructural de la tabla
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		// Creamos una lista con los nombres de las columnas
		for (int i = 1; i <= columnCount; i++ ) {
			columnNames.add(rsmd.getColumnName(i));
		}
		con.close();
		
	}

	public ResultSet getResultSet() {
		return rs;
	}

	public ArrayList<String> getColumnNames() {
		return columnNames;
	}	
}
