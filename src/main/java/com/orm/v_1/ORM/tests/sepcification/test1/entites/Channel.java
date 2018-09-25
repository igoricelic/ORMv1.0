package com.orm.v_1.ORM.tests.sepcification.test1.entites;

import java.util.Date;

import com.orm.v_1.ORM.annotations.Id;
import com.orm.v_1.ORM.annotations.Table;
import com.orm.v_1.ORM.annotations.Unique;

@Table(name = "channel")
public class Channel {
	
	@Id(auto_increment = true)
	private Integer id;
	
	@Unique
	private String name;
	
	private Integer lcn;
	
	private String url;
	
	private Date created;

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

	public Integer getLcn() {
		return lcn;
	}

	public void setLcn(Integer lcn) {
		this.lcn = lcn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", lcn=" + lcn + ", url=" + url + ", created=" + created + "]";
	}

}
