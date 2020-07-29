package templates;

import java.util.ArrayList;

public class DomainManager_Template {
	
	public static String header() {
		return "package domain;\r\n" + 
				"\r\n" + 
				"import java.util.ArrayList;\r\n" + 
				"import java.util.Arrays;\r\n" + 
				"\r\n" + 
				"public class Manager {\r\n" + 
				"	";
	}
	
	public static String tail() {
		return "	\r\n" + 
				"	\r\n" + 
				"	private static Manager singleInstance = new Manager();\r\n" + 
				"	\r\n" + 
				"    public static Manager getInstance() {\r\n" + 
				"        return singleInstance;\r\n" + 
				"    }\r\n" + 
				"	\r\n" + 
				"	public Manager() {\r\n" + 
				"		super();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public String getDatabasePath() {\r\n" + 
				"		return databasePath;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setDatabasePath(String databasePath) {\r\n" + 
				"		this.databasePath = databasePath;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public ArrayList<String> getValuesToInsert() {\r\n" + 
				"		return valuesToInsert;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setValuesToInsert(ArrayList<String> valuesToInsert) {\r\n" + 
				"		this.valuesToInsert = valuesToInsert;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public TableSchema getSchema() {\r\n" + 
				"		return schema;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setSchema(TableSchema schema) {\r\n" + 
				"		this.schema = schema;\r\n" + 
				"	}\r\n" + 
				"}";
	}
}
