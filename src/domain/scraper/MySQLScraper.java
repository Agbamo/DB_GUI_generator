package domain.scraper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLScraper extends AbstractTableScraper {

	public MySQLScraper(String url, String tableName) throws SQLException {
		super(url, tableName);
	}

	@Override
	public void retrieveTableContents(String tableDNS, String tableName) throws SQLException {

		String url="jdbc:mysql://" +  tableDNS; 
		Connection con = DriverManager.getConnection(url, "root", "");
		
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
			rsmd.getColumnClassName(i);			
		}
		System.out.println("Columnas: " + columnNames);
		System.out.println("Tipos: " + columnTypes);
		con.close();
	}

}
