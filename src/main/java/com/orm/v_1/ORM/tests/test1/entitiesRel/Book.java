package com.orm.v_1.ORM.tests.test1.entitiesRel;

import com.orm.v_1.ORM.annotations.Id;
import com.orm.v_1.ORM.annotations.Not_Null;
import com.orm.v_1.ORM.annotations.Table;
import com.orm.v_1.ORM.annotations.Unique;
import com.orm.v_1.ORM.annotations.join.ForeignKey;
import com.orm.v_1.ORM.annotations.join.ManyToOne;

@Table(name="book")
public class Book {
	
	@Id(auto_increment=true)
	private Integer id;
	
	private String title;
	
	private String description;
	
	@Unique
	@Not_Null
	private String code;
	
	@ForeignKey(name="fk_book_author")
	@ManyToOne(column="author_id")
	private Author author;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
