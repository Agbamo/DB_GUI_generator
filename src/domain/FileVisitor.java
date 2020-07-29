package domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import domain.generators.AbstractGenerator;
import domain.scraper.AbstractTableScraper;
import persistence.FileBroker;

public class FileVisitor {
	
	String tableName;
	
	File inputRoot;          // El armazón del proyecto y las plantillas.
	File outputRoot;         // El proyecto autogenerado.
	
	File customPanelFile;    // El archivo CustomJPanel.java
	File domainManagerFile;  // El archivo manager.java
	File dbManagerFile;      // El archivo DBManager.java
	
	AbstractGenerator generator;
	AbstractTableScraper tableScraper;
	
	public FileVisitor(File inputRoot, File outputRoot, AbstractTableScraper tableScraper, AbstractGenerator generator, String tableName) {
		super();
		this.inputRoot = inputRoot;
		this.outputRoot = outputRoot;
		this.generator = generator;
		this.tableScraper = tableScraper;
		this.tableName = tableName;
		
		customPanelFile = new File(outputRoot + System.getProperty("file.separator") 
								+ "src" + System.getProperty("file.separator") 
								+ "presentation" + System.getProperty("file.separator") 
								+ "CustomJPanel.java");
		domainManagerFile = new File(outputRoot + System.getProperty("file.separator") 
								+ "src" + System.getProperty("file.separator") 
								+ "domain" + System.getProperty("file.separator") 
								+ "Manager.java");
		dbManagerFile = new File(outputRoot + System.getProperty("file.separator") 
								+ "src" + System.getProperty("file.separator") 
								+ "persistence" + System.getProperty("file.separator") 
								+ "DBManager.java");
	}
	
	// Autogeneramos el código variable y guardamos el proyecto.
	public void writeProject() throws IOException{
		// Generamos el código de la parte variable de la interfaz.
		String customPanelCode = generateCustomJPanelCode();
		// Generamos el código de la parte variable del dominio.
		String domainManagerCode = generateDomainManagerCode();
		// Generamos el código de la parte variable del gestor de base de datos.
		String dbManagerCode = generateDBManagerCode();
		// Copiamos la estructura base del proyecto.
		FileBroker.copyFolder(inputRoot, outputRoot);
		// Copiamos el código autogenerado del archivo CustomJPanel.java
		FileBroker.write(customPanelFile, customPanelCode);
		// Copiamos el código autogenerado del archivo Manager.java
		FileBroker.write(domainManagerFile, domainManagerCode);
		// Copiamos el código autogenerado del archivo DBManager.java
		FileBroker.write(dbManagerFile, dbManagerCode);
	}

	private String generateCustomJPanelCode() {
		return generator.generateGUICode(tableScraper, tableName);
	}
	
	private String generateDomainManagerCode() {
		return generator.generateDomainManagerCode(tableScraper, tableName);
	}
	
	private String generateDBManagerCode() {
		return generator.generateDBManagerCode(tableScraper, tableName);
	}

}
