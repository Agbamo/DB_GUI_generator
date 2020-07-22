package templates;

import java.util.ArrayList;
import java.util.Iterator;

import domain.scraper.AbstractTableScraper;

public class AccessGenerator extends AbstractGenerator{
	
	public AccessGenerator() {
		super();
	}

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
							+ "gbc_lbl" + count + ".gridy = " + 2*(count-1)+1 + ";\r\n\t\t"
							+ "panel.add(lbl" + count + ", gbc_lbl" + count + ");\r\n\t\t"
							+ "\r\n\t\t";
			switch(type) {
		 		case "java.lang.String":
		 			output += "JTextField input" + count + " = new JTextField();\r\n\t\t" ;
					break;
				case "java.lang.Integer":
					output += "JTextField input" + count + " = new JTextField();\r\n\t\t" ;
					break;
				case "java.sql.Timestamp":
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
					+"gbc_input" + count + ".insets = new Insets(0, 0, 5, 5);\r\n\t\t" 
					+"gbc_input" + count + ".gridx = 2;\r\n\t\t"
					+"gbc_input" + count + ".gridy = " + 2*(count-1)+1 + ";\r\n\t\t" 
					+"panel.add(input" + count + ", gbc_input" + count + ");\r\n\t\t" 
					+"inputList.add(input" + count + ");"
					+ "\r\n\t\t";
		count++;
		}
		return output;
	}

	@Override
	public String generateManagerDetails(AbstractTableScraper tableScraper, String tableName) {
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
		output += formatWithCommas(tableScraper.getColumnTypes());
		output += "), \r\n" + 
				"		Arrays.asList(";
		output += formatWithCommas(tableScraper.getColumnNames());
		output += "));";
		return output;
	}

	private String formatWithCommas(ArrayList<String> list) {
		String output = "";
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()) {
			String element = iterator.next();
			output += "\"" + element + "\"";
			if(iterator.hasNext()) {
				output += ", ";	
			}
		}
		return output;
	}

}
