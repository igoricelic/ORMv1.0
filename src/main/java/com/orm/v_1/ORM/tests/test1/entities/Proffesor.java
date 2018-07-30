package com.orm.v_1.ORM.tests.test1.entities;

import java.util.Date;

import com.orm.v_1.ORM.annotations.Column;
import com.orm.v_1.ORM.annotations.Id;
import com.orm.v_1.ORM.annotations.Not_Null;
import com.orm.v_1.ORM.annotations.Table;
import com.orm.v_1.ORM.annotations.Unique;

@Table(name="proffesor")
public class Proffesor {
	
	@Id(auto_increment=true)
	private Integer id;
	
	private String name;
	
	private String surname;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Not_Null
	@Unique
	private String licence;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	@Override
	public String toString() {
		return "Proffesor [id=" + id + ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth
				+ ", licence=" + licence + "]";
	}

}
