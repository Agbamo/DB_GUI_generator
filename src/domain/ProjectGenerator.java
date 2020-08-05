package domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import domain.scraper.AccessTableScraper;
import domain.scraper.MySQLScraper;
import domain.scraper.PostgreSQLScraper;
import domain.util.ProgramDirectoryUtilities;
import domain.generators.AbstractGenerator;
import domain.generators.AccessGenerator;
import domain.generators.MySQLGenerator;
import domain.generators.PostgreSQLGenerator;
import domain.scraper.AbstractTableScraper;

public class ProjectGenerator {

	static String inputUrl = "";                      // Dirección de la base de datos de entrada.
	static String tableName = "";                     // Nombre de la tabla a convertir.
	static String dbSystem;                           // Tipo de base de datos.

	private static AbstractTableScraper tableScraper; // Lector de la estructura de la base de datos.
	private static AbstractGenerator generator;       // Generador de la parte del código variante entre proyectos.
	private static FileVisitor fileVisitor;           // Gestor de archivos; encargado de copiar la estructura del proyecto.
	
	static File inputRoot = new File(ProgramDirectoryUtilities.getProgramDirectory() 
			+ System.getProperty("file.separator") + "base_structure" +  System.getProperty("file.separator") 
		    + "Inserter" + System.getProperty("file.separator"));
	static File outputRoot = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Inserter");
	
	public static void main(String[] args) {	
		// Leemos los argumentos recibidos por consola.
		if (readArguments(args)) {
			System.out.println("GUI Generator");
			try {
				// Extraemos los nombres de las columnas y los valores contenidos por la tabla de entrada.
				instanceTableScraper();
				if(instanceGenerator() == -1) {
					System.out.println("SGBD inválido.\r\nLas opciones son: [Access/MySQL/PostgreSQL]");
					return;
				}
				// Copiamos la estructura del proyecto y generamos las partes variables del código.
				fileVisitor = new FileVisitor(inputRoot, outputRoot, tableScraper, generator, tableName);
				fileVisitor.writeProject();
				System.out.println("Programa generado correctamente!");
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
			System.out.println("GUI_Generator\r\n  Usage is:");
			System.out.println("         java GUI_Generator inputDataBaseURL tableName [Access/MySQL/PostgreSQL]");
			return;
		}
	}
	
	
	// Instanciamos el generador con la extensión adecuada para el SGBD que nos ocupa.
	private static int instanceGenerator() {
		if(dbSystem.equals("Access")) {
			generator = new AccessGenerator();
			return 0;
		}else if(dbSystem.equals("MySQL")) {
			generator = new MySQLGenerator();
			return 0;
		}else if(dbSystem.equals("PostgreSQL")) {
			generator = new PostgreSQLGenerator();
			return 0;
		}
		return -1;
	}

	// Instanciamos el scraper con la extensión adecuada para el SGBD que nos ocupa.
	private static void instanceTableScraper() throws SQLException{
		if(dbSystem.equals("Access")) {
			tableScraper = new AccessTableScraper(inputUrl, tableName);
		}else if(dbSystem.equals("MySQL")) {
			tableScraper = new MySQLScraper(inputUrl, tableName);
		}else if(dbSystem.equals("PostgreSQL")) {
			tableScraper = new PostgreSQLScraper(inputUrl, tableName);
		}
	}

	private static boolean readArguments(String[] args) {
		if(args.length == 3) {
			String auxURL = args[0];
			inputUrl = auxURL.replace("\\", "\\\\");
			tableName = args[1];
			dbSystem = args[2];
		} else {
			return false;
		}
		return true;
	}	
}
