package domain.scraper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class AbstractTableScraper {
	Connection con;
	ResultSet rs;
	ArrayList<String> columnNames;
	ArrayList<String> columnTypes;
	
	public AbstractTableScraper(String url, String tableName) throws SQLException {
		super();
		con = null;
		rs = null;
		columnNames = new ArrayList<String>();
		columnTypes = new ArrayList<String>();
		retrieveTableContents(url, tableName);
	}
		
	public abstract void retrieveTableContents(String tableDNS, String tableName) throws SQLException;

	public ResultSet getResultSet() {
		return rs;
	}

	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	public ArrayList<String> getColumnTypes() {
		return columnTypes;
	}

	public void setColumnTypes(ArrayList<String> columnTypes) {
		this.columnTypes = columnTypes;
	}
}
