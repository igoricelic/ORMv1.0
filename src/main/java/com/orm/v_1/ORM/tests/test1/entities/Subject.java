package com.orm.v_1.ORM.tests.test1.entities;

import com.orm.v_1.ORM.annotations.Column;
import com.orm.v_1.ORM.annotations.Id;
import com.orm.v_1.ORM.annotations.Not_Null;
import com.orm.v_1.ORM.annotations.Table;
import com.orm.v_1.ORM.annotations.Unique;
import com.orm.v_1.ORM.logic.OrmEntity;

@Table(name="subject")
public class Subject implements OrmEntity {
	
	@Id(auto_increment=true)
	private Integer id;
	
	private String name;
	
	private String descritpion;
	
	@Not_Null
	private Integer value;
	
	@Unique
	@Not_Null
	@Column(name="code_idef")
	private String codeIdef;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescritpion() {
		return descritpion;
	}

	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getCodeIdef() {
		return codeIdef;
	}

	public void setCodeIdef(String codeIdef) {
		this.codeIdef = codeIdef;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", descritpion=" + descritpion + ", value=" + value
				+ ", codeIdef=" + codeIdef + "]";
	}

}
