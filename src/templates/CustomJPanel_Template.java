package templates;

public class CustomJPanel_Template {
	
	public static String header() {
		return "package presentation;\r\n" + 
				"\r\n" + 
				"import javax.swing.JScrollPane;\r\n" + 
				"\r\n" + 
				"import domain.Manager;\r\n" + 
				"import javax.swing.JPanel;\r\n" + 
				"import java.awt.GridBagLayout;\r\n" + 
				"import javax.swing.JLabel;\r\n" +
				"import javax.swing.JTextField;\r\n" + 
				"\r\n" + 
				"import java.awt.GridBagConstraints;\r\n" + 
				"import java.awt.Insets;\r\n" + 
				"import java.util.ArrayList;\r\n" + 
				"import java.util.Iterator;\r\n" + 
				"import java.util.List;\r\n" + 
				"\r\n" + 
				"import com.toedter.calendar.JCalendar;\r\n" + 
				"import javax.swing.JRadioButton;\r\n" + 
				"import javax.swing.JComboBox;\r\n" + 
				"\r\n" + 
				"public class CustomJPanel extends JScrollPane {\r\n" + 
				"\r\n" + 
				"	List<Object> inputList = new ArrayList<Object>();\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Create the panel.\r\n" + 
				"	 */\r\n" + 
				"	public CustomJPanel() {\r\n" + 
				"		\r\n" + 
				"		JPanel panel = new JPanel();\r\n" + 
				"		setViewportView(panel);\r\n" + 
				"		GridBagLayout gbl_panel = new GridBagLayout();\r\n" + 
				"		gbl_panel.columnWidths = new int[]{0, 31, 0, 0, 0, 0};\r\n" + 
				"		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};\r\n" + 
				"		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};\r\n" + 
				"		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};\r\n" + 
				"		panel.setLayout(gbl_panel);\r\n" + 
				"		\r\n" + 
				"		";
	}
	
	public static String tail() {
		return "	}\r\n" + 
				"\r\n" + 
				"	public ArrayList<String> getInputValues() {\r\n" + 
				"		ArrayList<String> output = new ArrayList<String>();\r\n" + 
				"		Iterator<Object> inputIterator = inputList.iterator();\r\n" + 
				"		while(inputIterator.hasNext()) {\r\n" + 
				"			Object input = inputIterator.next();\r\n" + 
				"			if(input instanceof JLabel) {\r\n" + 
				"				output.add(\"autoincremental\");\r\n" + 
				"			}else if(input instanceof JCalendar) {\r\n" + 
				"				JCalendar current = (JCalendar)input;				\r\n" + 
				"				java.util.Date utilStartDate = current.getDate();\r\n" + 
				"				java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());\r\n" + 
				"				output.add(sqlStartDate.toString());\r\n" + 
				"			}else if(input instanceof JRadioButton) {\r\n" + 
				"				JRadioButton current = (JRadioButton)input;\r\n" + 
				"				if(current.isSelected()) {\r\n" + 
				"					output.add(\"TRUE\");\r\n" + 
				"				}else {\r\n" + 
				"					output.add(\"FALSE\");\r\n" + 
				"				}\r\n" + 
				"			}else if(input instanceof JComboBox) {\r\n" + 
				"				JComboBox<String> current = (JComboBox)input;\r\n" + 
				"				output.add((String)((JComboBox) input).getSelectedItem());\r\n" + 
				"			}\r\n" + 
				"			////////////////////////\r\n" + 
				"			// Ver qué más añadir //\r\n" + 
				"			////////////////////////	\r\n" + 
				"		}\r\n" + 
				"		return output;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}";
	}
}
