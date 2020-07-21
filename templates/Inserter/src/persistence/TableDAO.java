package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import domain.Manager;

public class TableDAO {
	
	public static void insert() throws SQLException {
		DBManager.insert(generateQuery());
	}
	
	private static String generateQuery() {
		String query = "";
		query = "INSERT INTO " + Manager.getInstance().getSchema().getTableName() +	" (";
		Iterator<String> columnsIterator = Manager.getInstance().getSchema().getFields().iterator();
		Iterator<String> typesIterator = Manager.getInstance().getSchema().getTypes().iterator();
		while(columnsIterator.hasNext()) {
			boolean fieldInserted = false;
			if(!typesIterator.next().equals("autonumeric")) {
				query += "" + columnsIterator.next() + "";
				fieldInserted = true;
			}else {
				columnsIterator.next();
			}
			if(columnsIterator.hasNext() && fieldInserted) {
				query += ", ";
			}
		}
		query += ") VALUES (";
		Iterator<String> valuesIterator = Manager.getInstance().getValuesToInsert().iterator();
		typesIterator = Manager.getInstance().getSchema().getTypes().iterator();
		while(valuesIterator.hasNext()) {
			boolean fieldInserted = false;
			String thisType = typesIterator.next();
			String thisValue = valuesIterator.next();
			if(!thisType.equals("autonumeric")) {
				if(thisType.equals("boolean")) {
					if(thisValue.equals("True")) {
						query += "-1";
					}else {
						query += "0";
					}
				}else {
					query += "\'" + thisValue + "\'";
				}
				
				fieldInserted = true;
			}
			if(valuesIterator.hasNext() && fieldInserted) {
				query += ", ";
			}
		}
		query += ")";
		System.out.println(query);
		return query;
	}
}