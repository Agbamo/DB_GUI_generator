package templates;

import java.sql.SQLException;
import java.util.Iterator;

public class DBManager_Template {
	
	public static String header() {
		return "package persistence;\r\n" + 
				"\r\n" + 
				"import java.sql.Connection;\r\n" + 
				"import java.sql.DriverManager;\r\n" + 
				"import java.sql.SQLException;\r\n" + 
				"import java.sql.Statement;\r\n" + 
				"import java.util.Iterator;\r\n" + 
				"import java.util.Properties;\r\n" + 
				"\r\n" + 
				"import domain.Manager;\r\n" + 
				"\r\n" + 
				"public class DBManager {	\r\n" + 
				"	public static void insertValues() throws SQLException, ClassNotFoundException  {			\r\n" +
				"		insert(generateQuery());\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	private static int insert(String query) throws SQLException, ClassNotFoundException  {";

	}
	
	public static String tail() {
		return  "				fieldInserted = true;\r\n" + 
				"			}else {\r\n" + 
				"				columnsIterator.next();\r\n" + 
				"			}\r\n" + 
				"			if(columnsIterator.hasNext() && fieldInserted) {\r\n" + 
				"				query += \", \";\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		query += \") VALUES (\";\r\n" + 
				"		Iterator<String> valuesIterator = Manager.getInstance().getValuesToInsert().iterator();\r\n" + 
				"		typesIterator = Manager.getInstance().getSchema().getTypes().iterator();\r\n" + 
				"		while(valuesIterator.hasNext()) {\r\n" + 
				"			boolean fieldInserted = false;\r\n" + 
				"			String thisType = typesIterator.next();\r\n" + 
				"			String thisValue = valuesIterator.next();\r\n" + 
				"			if(!thisType.equals(\"autonumeric\")) {\r\n" + 
				"				if(thisType.equals(\"boolean\")) {\r\n" + 
				"					if(thisValue.equals(\"True\")) {\r\n" + 
				"						query += \"-1\";\r\n" + 
				"					}else {\r\n" + 
				"						query += \"0\";\r\n" + 
				"					}\r\n" + 
				"				}else {\r\n" + 
				"					query += \"\\'\" + thisValue + \"\\'\";\r\n" + 
				"				}\r\n" + 
				"				\r\n" + 
				"				fieldInserted = true;\r\n" + 
				"			}\r\n" + 
				"			if(valuesIterator.hasNext() && fieldInserted) {\r\n" + 
				"				query += \", \";\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		query += \")\";\r\n" + 
				"		System.out.println(query);\r\n" + 
				"		return query;\r\n" + 
				"	}\r\n" + 
				"}";
	}
}
