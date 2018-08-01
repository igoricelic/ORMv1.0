package com.orm.v_1.ORM.tests.test1.entities;

import java.util.Date;

import com.orm.v_1.ORM.annotations.Column;
import com.orm.v_1.ORM.annotations.Id;
import com.orm.v_1.ORM.annotations.Not_Null;
import com.orm.v_1.ORM.annotations.Table;
import com.orm.v_1.ORM.logic.OrmEntity;

@Table(name="class")
public class SClass implements OrmEntity {
	
	@Id(auto_increment=true)
	private Integer id;
	
	@Not_Null
	@Column(name="subject_id")
	private Integer subjectId;
	
	@Not_Null
	@Column(name="proffesor_id")
	private Integer proffesorId;
	
	@Column(name="date_time")
	private Date dateTime;
	
	@Column(name="class_room")
	private String classRoom;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getProffesorId() {
		return proffesorId;
	}

	public void setProffesorId(Integer proffesorId) {
		this.proffesorId = proffesorId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SClass [id=" + id + ", subjectId=" + subjectId + ", proffesorId=" + proffesorId + ", dateTime="
				+ dateTime + ", classRoom=" + classRoom + ", description=" + description + "]";
	}

}
