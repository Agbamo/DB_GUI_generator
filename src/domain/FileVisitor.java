package domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import domain.generators.AbstractGenerator;
import domain.scraper.AbstractTableScraper;
import persistence.FileBroker;

public class FileVisitor {
	
	File inputRoot;        // El armaz�n del proyecto y las plantillas.
	File outputRoot;       // El proyecto autogenerado.
	
	File customPanelFile;  // El archivo CustomJPanel.java
	File managerFile;      // El archivo manager.java
	
	AbstractGenerator generator;
	
	public FileVisitor(File inputRoot, File outputRoot, AbstractTableScraper tableScraper, AbstractGenerator generator) {
		super();
		this.inputRoot = inputRoot;
		this.outputRoot = outputRoot;
		this.generator = generator;
		customPanelFile = new File(outputRoot + System.getProperty("file.separator") 
				+ "src" + System.getProperty("file.separator") 
				+ "presentation" + System.getProperty("file.separator") 
				+ "CustomJPanel.java");
		managerFile = new File(outputRoot + System.getProperty("file.separator") 
				+ "src" + System.getProperty("file.separator") 
				+ "domain" + System.getProperty("file.separator") 
				+ "Manager.java");
	}
	
	// Autogeneramos el c�digo variable y guardamos el proyecto.
	public void writeProject() throws IOException{
		// Generamos el c�digo de la parte variable de la interfaz.
		String customPanelCode = generateCustomJPanelCode();
		// Generamos el c�digo de la parte variable del dominio.
		String managerCode = generateManagerCode();
		// Copiamos la estructura base del proyecto.
		FileBroker.copyFolder(inputRoot, outputRoot);
		// Copiamos el c�digo autogenerado del archivo CustomJPanel.java
		FileBroker.write(customPanelFile, customPanelCode);
		// Copiamos el c�digo autogenerado del archivo Manager.java
		FileBroker.write(managerFile, managerCode);
	}

	private String generateCustomJPanelCode() {
		return generator.generateGUICode();
	}
	
	private String generateManagerCode() {
		return generator.generateManagerCode();
	}

}
