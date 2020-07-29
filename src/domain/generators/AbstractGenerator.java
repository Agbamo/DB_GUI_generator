package domain.generators;

import java.util.ArrayList;
import java.util.Iterator;

import domain.scraper.AbstractTableScraper;
import templates.CustomJPanel_Template;
import templates.DBManager_Template;
import templates.DomainManager_Template;

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
	public String generateDomainManagerCode(AbstractTableScraper tableScraper, String tableName) {
		// 1. Se copia la parte anterior al c�digo que depende de la base de datos.
		// 2. Se llama a la implementaci�n de un m�todo abstracto
		//    para generar la parte del c�digo que var�a en funci�n
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al c�digo que depende de la base de datos.
		return DomainManager_Template.header() 
				+ generateDomainManagerDetails(tableScraper, tableName) 
				+ DomainManager_Template.tail();
	}
	
	public String generateDBManagerCode(AbstractTableScraper tableScraper, String tableName) {
		// 1. Se copia la parte anterior al c�digo que depende de la base de datos.
		// 2. Se llama a la implementaci�n de un m�todo abstracto
		//    para generar la parte del c�digo que var�a en funci�n
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al c�digo que depende de la base de datos.
		return DBManager_Template.header() 
				+ generateDBManagerDetails(tableScraper, tableName) 
				+ DBManager_Template.tail();
	}
	
	protected abstract String generateGUIDetails(AbstractTableScraper tableScraper, String tableName);
	
	protected abstract String generateDomainManagerDetails(AbstractTableScraper tableScraper, String tableName);
	
	protected abstract String generateDBManagerDetails(AbstractTableScraper tableScraper, String tableName);

	protected String formatWithCommas(ArrayList<String> list) {
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
