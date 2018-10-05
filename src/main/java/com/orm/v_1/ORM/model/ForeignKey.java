package com.orm.v_1.ORM.model;

import java.lang.reflect.Field;

import com.orm.v_1.ORM.enums.ColumnType;

public class ForeignKey extends Column {

	private enum RelationType {
		OneToOne, OneToMany, ManyToOne, ManyToMany;
		
		public static RelationType getType (int value) {
			switch (value) {
				case 1:
					return RelationType.OneToOne;
				case 2:
					return RelationType.OneToMany;
				case 3:
					return RelationType.ManyToOne;
				case 4:
					return RelationType.ManyToMany;
			}
			return null;
		}
	}
	
	public ForeignKey(String nameInModel, String nameInDb, ColumnType type, Field field, int length, boolean notNull,
			String keyName, int relationType, Table owner, Table refTable) {
		super(nameInModel, nameInDb, type, field, length, notNull, false, false);
		this.keyName = keyName;
		this.owner = owner;
		this.refTable = refTable;
		this.relationType = RelationType.getType(relationType);
	}
	
	private String keyName;
	
	private RelationType relationType;
	
	private Table owner, refTable;

}
