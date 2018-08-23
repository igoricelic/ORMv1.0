package com.orm.v_1.ORM.query.generator;

import java.util.ArrayList;
import java.util.List;

import com.orm.v_1.ORM.enums.ColumnType;
import com.orm.v_1.ORM.exceptions.ColumnNotFoundException;
import com.orm.v_1.ORM.model.Column;
import com.orm.v_1.ORM.model.Table;

public class QueryGeneratorImpl implements QueryGenerator {
	
	public enum Type {
		SELECT, COUNT
	}
	
	private Type type;

	@Override
	public String generateQuery(String specification, Table table, Object[] args) throws ColumnNotFoundException {
		String query = "%s * FROM %s WHERE %s;";
		List<Column> columns = new ArrayList<>();
		Column column = null;
		for(String nameInModel: getFieldList(specification)) {
			column = table.getColumnByDbName(nameInModel);
			if(column == null) throw new ColumnNotFoundException("Column " + nameInModel + " not found in table " + table.getName());
			columns.add(column);
		}
		String whereQuery = "";
		String value = null;
		for(int i=0; i<columns.size(); i++) {
			column = columns.get(i);
			if(column.getType() == ColumnType.VARCHAR) value = "\"" + args[i] + "\"";
			whereQuery = whereQuery + column.getNameInDb() + " = " + value + " ";
		}
		return String.format(query, type.toString(), table.getName(), whereQuery);
	}
	
	private List<String> getFieldList (String specification) {
		String[] namesOfFields = specification.split("By");
		this.type = Type.valueOf(namesOfFields[0].toUpperCase());
		List<String> names = new ArrayList<>();
		String name = null;
		for(int i=1; i<namesOfFields.length; i++) {
			name = Character.toLowerCase(namesOfFields[i].charAt(0)) + namesOfFields[i].substring(1);
			names.add(name);
		}
		
		return names;
	}

}
