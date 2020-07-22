package domain;

import java.util.List;

public class TableSchema {
	
	private String tableName;
	private List<String> fields;
	private List<String> types;
	private List<List<String>> foreingKeyValues;
	
	public TableSchema(String tableName, List<String> list, List<String> list2) {
		super();
		this.tableName = tableName;
		this.fields = list;
		this.types =  list2;
	}
	
	public List<String> getFields() {
		return fields;
	}
	
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	
	public List<String> getTypes() {
		return types;
	}
	
	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<List<String>> getForeingKeyValues() {
		return foreingKeyValues;
	}

	public void setForeingKeyValues(List<List<String>> foreingKeyValues) {
		this.foreingKeyValues = foreingKeyValues;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}


