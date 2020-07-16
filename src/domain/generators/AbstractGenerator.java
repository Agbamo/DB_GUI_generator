package domain.generators;

import templates.CustomJPanel_Template;
import templates.Manager_Template;

public abstract class  AbstractGenerator {
	
	public AbstractGenerator() {
		super();
	}

	// Autogeneración del código de CustomJPanel.java
	public String generateGUICode() {
		// 1. Se copia la parte anterior al código que depende de la base de datos.
		// 2. Se llama a la implementación de un método abstracto
		//    para generar la parte del código que varía en función
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al código que depende de la base de datos.
		return CustomJPanel_Template.header() 
				+ generateGUIDetails() 
				+ CustomJPanel_Template.tail();
	}
	
	// Autogeneración del código de Manager.java
	public String generateManagerCode() {
		// 1. Se copia la parte anterior al código que depende de la base de datos.
		// 2. Se llama a la implementación de un método abstracto
		//    para generar la parte del código que varía en función
		//    de la estructura de la base de datos.
		// 3. Se copia la parte posterior al código que depende de la base de datos.
		return Manager_Template.header() 
				+ generateManagerDetails() 
				+ Manager_Template.tail();
	}
	
	protected abstract String generateGUIDetails();
	
	protected abstract String generateManagerDetails();

}
