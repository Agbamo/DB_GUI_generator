package domain.scraper;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccessTableScraper extends AbstractTableScraper{
	
	Connection con;
	ResultSet rs;

	public AccessTableScraper(String url, String tableName) throws SQLException {
		super(url, tableName);
	}
		
	public void retrieveTableContents(String tableDNS, String tableName) throws SQLException {		
		
		String url="jdbc:ucanaccess://" +  tableDNS; 
		
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
			columnNames.add(rsmd.getColumnLabel(i));
			columnTypes.add(rsmd.getColumnClassName(i));
			rsmd.getColumnClassName(i);			///////////
			// columnClassName=java.lang.String, columnDisplaySize=255, columnLabel=SHORTTEXT
		}
		System.out.println("Columnas: " + columnNames);
		System.out.println("Tipos: " + columnTypes);
		con.close();	
	}
}
