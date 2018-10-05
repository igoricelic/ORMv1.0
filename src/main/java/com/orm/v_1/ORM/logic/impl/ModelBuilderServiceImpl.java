package com.orm.v_1.ORM.logic.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.orm.v_1.ORM.enums.ColumnType;
import com.orm.v_1.ORM.exceptions.NotSuportTypeException;
import com.orm.v_1.ORM.logic.ModelBuilderService;
import com.orm.v_1.ORM.model.Column;
import com.orm.v_1.ORM.model.Database;
import com.orm.v_1.ORM.model.ForeignKey;
import com.orm.v_1.ORM.model.Id;
import com.orm.v_1.ORM.model.Table;

public class ModelBuilderServiceImpl implements ModelBuilderService {
	
	private static final String EMPTY_WORD = "";

	@Override
	public Database buildModel(String databaseName, List<Class<?>> entities) throws NoSuchFieldException, SecurityException, NotSuportTypeException {
		Map<Class<?>, Table> map = new HashMap<>();
		for(Class<?> entityClass: entities) {
			map.put(entityClass, getTableFromEntity(entityClass));
		}
		return new Database(databaseName, map);
	}
	
	private Table getTableFromEntity (Class<?> entity) throws NoSuchFieldException, SecurityException, NotSuportTypeException {
		Annotation[] annotations = entity.getDeclaredAnnotations();
		String tableName = null;
		
		for(Annotation annotation: annotations) {
			if(annotation instanceof com.orm.v_1.ORM.annotations.Table) {
				com.orm.v_1.ORM.annotations.Table tableAnnotation =  (com.orm.v_1.ORM.annotations.Table) annotation;
				tableName = tableAnnotation.name();
			}
		}
		
		Id id = null;
		List<Column> columns = new LinkedList<Column>();
		List<ForeignKey> foreignKeys = new ArrayList<>();
		Column column = null;
		Field[] fields = entity.getDeclaredFields();
		for(Field field: fields) {
			column = getColumnFromField(field);
			if(column == null) continue;
			if(column instanceof ForeignKey) {
				foreignKeys.add((ForeignKey) column);
				continue;
			}
			if(column.isPrimaryKey()) id = (Id) column;
			columns.add(column);
		}
		
		return new Table(tableName, id, columns, foreignKeys);
	}
	
	private Column getColumnFromField (Field field) throws NotSuportTypeException {
		String nameInDb = "";
		int length = 0;
		ColumnType type = ColumnType.AUTO;
		// Foreign key
		ForeignKey foreignKey = null;
		String foreignRelationName = null;
		Table ownTable, refTable;

		boolean notNull = false, isId = false, autoIncrement = false, isUnique = false;
		String nameInModel = field.getName();
		for(Annotation annotation: field.getDeclaredAnnotations()) {
			if(annotation instanceof com.orm.v_1.ORM.annotations.Id) {
				com.orm.v_1.ORM.annotations.Id idAnnotation = (com.orm.v_1.ORM.annotations.Id) annotation;
				autoIncrement = idAnnotation.auto_increment();
				isId = true;
			}
			else if(annotation instanceof com.orm.v_1.ORM.annotations.Transient) {
				return null;
			}
			else if(annotation instanceof com.orm.v_1.ORM.annotations.Column) {
				com.orm.v_1.ORM.annotations.Column colAnnotation = (com.orm.v_1.ORM.annotations.Column) annotation;
				nameInDb = colAnnotation.name();
				length = colAnnotation.length();
				type = colAnnotation.type();
			}
			else if(annotation instanceof com.orm.v_1.ORM.annotations.join.ForeignKey) {
				com.orm.v_1.ORM.annotations.join.ForeignKey forKeyAnnotation = (com.orm.v_1.ORM.annotations.join.ForeignKey) annotation;
				foreignRelationName = forKeyAnnotation.name();
			}
			else if(annotation instanceof com.orm.v_1.ORM.annotations.Not_Null) {
				notNull = true;
			}
			else if(annotation instanceof com.orm.v_1.ORM.annotations.Unique) {
				isUnique = true;
			}
		}

		if(type == ColumnType.AUTO) {
			Class<?> fieldClass = field.getType();
			ColumnType colType = ColumnType.getTypeByClass(fieldClass);
			if(colType == null) throw new NotSuportTypeException("Not suport Column type " + fieldClass);
			type = colType;
		}
		if(EMPTY_WORD.equals(nameInDb)) {
			nameInDb = nameInModel;
		}
		
		if(isId) {
			return new Id(nameInModel, nameInDb, field, length, autoIncrement);
		}
		
		return new Column(nameInModel, nameInDb, type, field, length, notNull, false, isUnique);
	}

}
