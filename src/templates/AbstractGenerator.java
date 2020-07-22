package templates;

import domain.scraper.AbstractTableScraper;

public abstract class  AbstractGenerator {
	
	public AbstractGenerator() {
		super();
	}

	// Autogeneración del código de CustomJPanel.java
	public String generateGUICode(AbstractTableScraper tableScraper, String tableName) {
		// 1. Se copia la parte anterior al código que depende de la base de datos.
		// 2. Se llama a la implementación de un método abstracto
		//    para generar la parte del código que varía en función
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al código que depende de la base de datos.
		return CustomJPanel_Template.header() 
				+ generateGUIDetails(tableScraper,tableName) 
				+ CustomJPanel_Template.tail();
	}
	
	// Autogeneración del código de Manager.java
	public String generateManagerCode(AbstractTableScraper tableScraper, String tableName) {
		// 1. Se copia la parte anterior al código que depende de la base de datos.
		// 2. Se llama a la implementación de un método abstracto
		//    para generar la parte del código que varía en función
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al código que depende de la base de datos.
		return Manager_Template.header() 
				+ generateManagerDetails(tableScraper, tableName) 
				+ Manager_Template.tail();
	}
	
	protected abstract String generateGUIDetails(AbstractTableScraper tableScraper, String tableName);
	
	protected abstract String generateManagerDetails(AbstractTableScraper tableScraper, String tableName);

}
