package com.orm.v_1.ORM.tests.test1.entitiesRel;

import java.util.Date;
import java.util.List;

import com.orm.v_1.ORM.annotations.Column;
import com.orm.v_1.ORM.annotations.Id;
import com.orm.v_1.ORM.annotations.Table;
import com.orm.v_1.ORM.annotations.join.ForeignKey;
import com.orm.v_1.ORM.annotations.join.OneToMany;

@Table(name="author")
public class Author {
	
	@Id(auto_increment=true)
	private Integer id;
	
	private String name;
	
	private String surname;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@ForeignKey(name="fk_author_books")
	@OneToMany(ref_column="author_id")
	private List<Book> books;

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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
