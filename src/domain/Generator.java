package domain;

import java.sql.SQLException;
import java.util.ArrayList;

public class Generator {

	static String accessUrl = ""; // Dirección de la base de datos de entrada.
	static String mongoUri = "";  // Dirección de la base de datos de salida.
	static String tableName = ""; // Nombre de la tabla a convertir.

	public static void main(String[] args) {	
		// Leemos los argumentos recibidos por consola.
		if (readArguments(args)) {
			try {
				// Extraemos los nombres de las columnas y los valores contenidos por la tabla de entrada.
				MSAccessManager msManager = new MSAccessManager(accessUrl, tableName);
				ArrayList<Document> documents = SQL2MongoTemplate.convert(msManager.getColumnNames(), msManager.getResultSet());
				try {
					if(!MongoManager.insertDocuments(mongoUri, tableName, documents)) {
						// Si ocurre una excepción durante la escritura, se informa al usuario y se detiene la ejecución.
						System.out.println("Error while inserting to output database");
						return;
					}
				} catch (Exception e) {
					System.out.println("Error while writing in output database");
					e.printStackTrace();
					return;
				}
			// Si ocurre una excepción durante la lectura, se informa al usuario y se detiene la ejecución.	
			} catch (SQLException e) {
				System.out.println("Error while reading input database");
				e.printStackTrace();
				return;
			}
		// Si los argumentos recibidos no tienen el formato correcto, se informa al usuario y se detiene la ejecución.	
		} else {
			System.out.println("TableConverter:  Usage is:");
			System.out.println("         java TableConverter inputDataBaseURL outputDataBaseURL tableName");
			return;
		}
	}
	
	
	private static boolean readArguments(String[] args) {
		if(args.length == 3) {
			accessUrl = args[0];
			mongoUri = args[1];
			tableName = args[2];
		} else {
			return false;
		}
		return true;
	}
}
