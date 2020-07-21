package domain;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.scraper.AccessTableScraper;
import domain.util.ProgramDirectoryUtilities;
import domain.generators.AbstractGenerator;
import domain.generators.AccessGenerator;
import domain.scraper.AbstractTableScraper;

public class ProjectGenerator {

	static String inputUrl = "";                      // Dirección de la base de datos de entrada.
	static String tableName = "";                     // Nombre de la tabla a convertir.
	static String dbSystem;                           // Tipo de base de datos.

	private static AbstractTableScraper tableScraper; // Lector de la estructura de la base de datos.
	private static AbstractGenerator adapter;         // Generador de la parte del código variante entre proyectos.
	private static FileVisitor fileVisitor;           // Gestor de archivos; encargado de copiar la estructura del proyecto.
	
	static File inputRoot = new File(ProgramDirectoryUtilities.getProgramDirectory() 
			+ System.getProperty("file.separator") + "templates" +  System.getProperty("file.separator") 
		    + "Inserter" + System.getProperty("file.separator"));
	static File outputRoot = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Inserter");
	
	public static void main(String[] args) {	
		// Leemos los argumentos recibidos por consola.
		if (readArguments(args)) {
			try {
				// Extraemos los nombres de las columnas y los valores contenidos por la tabla de entrada.
				instanceTableScraper();
				// Copiamos la estructura del proyecto y generamos las partes variables del código.
				instanceGenerator();
				fileVisitor = new FileVisitor(inputRoot, outputRoot, tableScraper, adapter, tableName);
				fileVisitor.writeProject();
				
			// Si ocurre una excepción durante la lectura, se informa al usuario y se detiene la ejecución.	
			} catch (SQLException sqle) {
				System.out.println("Error while reading input database");
				sqle.printStackTrace();
				return;
			// Si ocurre una excepción durante la copia, se informa al usuario y se detiene la ejecución.	
			} catch (IOException ioe) {
				System.out.println("Error while copying project templates");
				ioe.printStackTrace();
				return;
			}
		// Si los argumentos recibidos no tienen el formato correcto, se informa al usuario y se detiene la ejecución.	
		} else {
			System.out.println("GUI_Generator:  Usage is:");
			System.out.println("         java GUI_Generator inputDataBaseURL tableName (Access/MySQL/)");
			return;
		}
	}
	
	
	// Instanciamos el generador adecuado para el SGBD que nos ocupa.
	private static void instanceGenerator() {
		if(dbSystem.equals("Access")) {
			adapter = new AccessGenerator();
		}
		////////////////////////////////////
		//           WIP
		////////////////////////////////////
	}


	// Instanciamos el scraper adecuado para el SGBD que nos ocupa.
	private static void instanceTableScraper() throws SQLException{
		if(dbSystem.equals("Access")) {
			tableScraper = new AccessTableScraper(inputUrl, tableName);
		}
		////////////////////////////////////
		//           WIP
		////////////////////////////////////
	}

	private static boolean readArguments(String[] args) {
		if(args.length == 3) {
			inputUrl = args[0];
			tableName = args[1];
			dbSystem = args[2];
		} else {
			return false;
		}
		return true;
	}
		
}
