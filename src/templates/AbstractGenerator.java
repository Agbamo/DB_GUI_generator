package templates;

import domain.scraper.AbstractTableScraper;

public abstract class  AbstractGenerator {
	
	public AbstractGenerator() {
		super();
	}

	// Autogeneraci�n del c�digo de CustomJPanel.java
	public String generateGUICode(AbstractTableScraper tableScraper, String tableName) {
		// 1. Se copia la parte anterior al c�digo que depende de la base de datos.
		// 2. Se llama a la implementaci�n de un m�todo abstracto
		//    para generar la parte del c�digo que var�a en funci�n
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al c�digo que depende de la base de datos.
		return CustomJPanel_Template.header() 
				+ generateGUIDetails(tableScraper,tableName) 
				+ CustomJPanel_Template.tail();
	}
	
	// Autogeneraci�n del c�digo de Manager.java
	public String generateManagerCode(AbstractTableScraper tableScraper, String tableName) {
		// 1. Se copia la parte anterior al c�digo que depende de la base de datos.
		// 2. Se llama a la implementaci�n de un m�todo abstracto
		//    para generar la parte del c�digo que var�a en funci�n
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al c�digo que depende de la base de datos.
		return Manager_Template.header() 
				+ generateManagerDetails(tableScraper, tableName) 
				+ Manager_Template.tail();
	}
	
	protected abstract String generateGUIDetails(AbstractTableScraper tableScraper, String tableName);
	
	protected abstract String generateManagerDetails(AbstractTableScraper tableScraper, String tableName);

}
