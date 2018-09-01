package com.orm.v_1.ORM.tests.test2;

import java.util.Date;

import com.orm.v_1.ORM.annotations.Column;
import com.orm.v_1.ORM.annotations.Id;
import com.orm.v_1.ORM.annotations.Not_Null;
import com.orm.v_1.ORM.annotations.Table;
import com.orm.v_1.ORM.annotations.Unique;

@Table(name = "admin")
public class Administrator {
	
	@Id(auto_increment = true)
	private Integer id;
	
	private String name;
	
	private String surname;
	
	@Column(name = "favorite_number")
	private Integer favoriteNumber;
	
	@Unique
	@Not_Null
	private String email;
	
	@Not_Null
	private String password;
	
	@Column(name = "date_of_registration")
	private Date dateOfRegistration;

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

	public Integer getFavoriteNumber() {
		return favoriteNumber;
	}

	public void setFavoriteNumber(Integer favoriteNumber) {
		this.favoriteNumber = favoriteNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", surname=" + surname + ", favoriteNumber="
				+ favoriteNumber + ", email=" + email + ", password=" + password + ", dateOfRegistration="
				+ dateOfRegistration + "]";
	}

}
