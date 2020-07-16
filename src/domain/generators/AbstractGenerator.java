package domain.generators;

import templates.CustomJPanel_Template;
import templates.Manager_Template;

public abstract class  AbstractGenerator {
	
	public AbstractGenerator() {
		super();
	}

	// Autogeneraci�n del c�digo de CustomJPanel.java
	public String generateGUICode() {
		// 1. Se copia la parte anterior al c�digo que depende de la base de datos.
		// 2. Se llama a la implementaci�n de un m�todo abstracto
		//    para generar la parte del c�digo que var�a en funci�n
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al c�digo que depende de la base de datos.
		return CustomJPanel_Template.header() 
				+ generateGUIDetails() 
				+ CustomJPanel_Template.tail();
	}
	
	// Autogeneraci�n del c�digo de Manager.java
	public String generateManagerCode() {
		// 1. Se copia la parte anterior al c�digo que depende de la base de datos.
		// 2. Se llama a la implementaci�n de un m�todo abstracto
		//    para generar la parte del c�digo que var�a en funci�n
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al c�digo que depende de la base de datos.
		return Manager_Template.header() 
				+ generateManagerDetails() 
				+ Manager_Template.tail();
	}
	
	protected abstract String generateGUIDetails();
	
	protected abstract String generateManagerDetails();

}
