package domain.generators;

import java.util.Iterator;

import domain.scraper.AbstractTableScraper;

public class PostgreSQLGenerator extends AbstractGenerator{

	// Generaci�n de la parte variable del c�digo de la interfaz.
	@Override
	public String generateGUIDetails(AbstractTableScraper tableScraper, String tableName) {
		String output = "";
		int count = 1;
		Iterator<String> namesIterator = tableScraper.getColumnNames().iterator();
		Iterator<String> typesIterator = tableScraper.getColumnTypes().iterator();
		while(namesIterator.hasNext() && typesIterator.hasNext()) {
			String name = namesIterator.next();
			String type = typesIterator.next();
			output += "JLabel lbl" + count + " = new JLabel(\"" + name + "\");\r\n\t\t"
					+ "GridBagConstraints gbc_lbl" + count + " = new GridBagConstraints();\r\n\t\t"
							+ "gbc_lbl" + count + ".anchor = GridBagConstraints.EAST;\r\n\t\t"
							+ "gbc_lbl" + count + ".insets = new Insets(0, 0, 5, 5);\r\n\t\t"
							+ "gbc_lbl" + count + ".gridx = 1;\r\n\t\t"
							+ "gbc_lbl" + count + ".gridy = " + (2*(count-1)+1) + ";\r\n\t\t"
							+ "panel.add(lbl" + count + ", gbc_lbl" + count + ");\r\n\t\t"
							+ "\r\n\t\t";
			switch(type) {
		 		case "java.lang.String":
		 			output += "JTextField input" + count + " = new JTextField();\r\n\t\t" ;
					break;
				case "java.lang.Integer":
					output += "JTextField input" + count + " = new JTextField();\r\n\t\t" ;
					break;
				case "java.lang.Long":
					output += "JTextField input" + count + " = new JTextField();\r\n\t\t" ;
					break;
				case "java.lang.Double":
					output += "JTextField input" + count + " = new JTextField();\r\n\t\t" ;
					break;
				case "java.sql.Timestamp":
					output += "JCalendar input" + count + " = new JCalendar();\r\n\t\t" ;
					break;
				case "java.sql.Date":
					output += "JCalendar input" + count + " = new JCalendar();\r\n\t\t" ;
					break;
				case "java.math.BigDecimal":
					output += "JTextField input" + count + " = new JTextField();\r\n\t\t" ;
					break;
				case "java.lang.Boolean":
					output += "JRadioButton input" + count + " = new JRadioButton(\"\");\r\n\t\t" ;
					break;
				default:
					output += "JTextField input" + count + " = new JTextField();\r\n\t\t" ;
					break;
			}
			output += "GridBagConstraints gbc_input" + count + " = new GridBagConstraints();\r\n\t\t" 
					+"gbc_input" + count + ".insets = new Insets(0, 0, 5, 5);\r\n\t\t";
			if(!type.equals("java.sql.Timestamp") || !type.equals("java.math.BigDecimal") 
					|| !type.equals("java.lang.Boolean")) {
				output += "gbc_input" + count + ".fill = GridBagConstraints.HORIZONTAL;\r\n\t\t";
			}
			output +="gbc_input" + count + ".gridx = 2;\r\n\t\t"
					+"gbc_input" + count + ".gridy = " + (2*(count-1)+1) + ";\r\n\t\t" 
					+"panel.add(input" + count + ", gbc_input" + count + ");\r\n\t\t" 
					+"inputList.add(input" + count + ");"
					+"\r\n\r\n\t\t";
		count++;
		}
		return output;
	}

	// Generaci�n de la parte variable del c�digo del dominio.
	@Override
	public String generateDomainManagerDetails(AbstractTableScraper tableScraper, String tableName) {
		String output = "";
		
		output += "String databasePath = \"";
		output += tableScraper.getUrl();
		output += "\";\r\n" + 
				"	ArrayList<String> valuesToInsert = new ArrayList<String>();\r\n" + 
				"	TableSchema schema = new TableSchema(\r\n" + 
				"		\"";
		output += tableName;
		output += "\",\r\n" + 
				"		Arrays.asList(";
		output += formatWithCommas(tableScraper.getColumnNames());
		output += "), \r\n" + 
				"		Arrays.asList(";
		output += formatWithCommas(tableScraper.getColumnTypes());
		output += "));";
		return output;
	}

	// Generaci�n de la parte variable del c�digo de la persistencia.
	@Override
	protected String generateDBManagerDetails(AbstractTableScraper tableScraper, String tableName) {
		return	"		   Properties props = new Properties();\r\n" + 
				"		   props.setProperty(\"user\",\"postgres\");\r\n" + 
				"		   props.setProperty(\"password\",\"postgrespostgres\");\r\n" + 
				"		   Connection con = DriverManager.getConnection(\"jdbc:postgresql://\" + Manager.getInstance().getDatabasePath(), props);\r\n" + 
				"		   Statement st = con.createStatement();\r\n" + 
				"		   return st.executeUpdate(query);" + 
				"	}\r\n\r\n" + 
				"	private static String generateQuery() {\r\n" + 
				"		String query = \"\";\r\n" + 
				"		query = \"INSERT INTO public.\\\"\" + Manager.getInstance().getSchema().getTableName() +	\"\\\" (\"; \r\n" + 
				"		Iterator<String> typesIterator = Manager.getInstance().getSchema().getTypes().iterator();\r\n" + 
				" 		Iterator<String> columnsIterator = Manager.getInstance().getSchema().getFields().iterator();" +
				"		while(columnsIterator.hasNext()) {\r\n" + 
				"			boolean fieldInserted = false;\r\n" + 
				"			if(!typesIterator.next().equals(\"autonumeric\")) {\r\n" + 
				"				query += \"\\\"\" + columnsIterator.next() + \"\\\"\";";
	}

}