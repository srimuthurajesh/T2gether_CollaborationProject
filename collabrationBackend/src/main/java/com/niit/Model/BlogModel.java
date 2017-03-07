package com.niit.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name="BLOG_TABLE")
@Component
public class BlogModel extends BaseDomain {

	@Id
	private String blogname;
	private String blogdescription;
	@Column(name="username")
	private String userid;
	
	@Transient
	@Column(name="date_time")
	private Date blogdatetime;
	private char blogstatus;
	private String blogreaason;
	public String getBlogname() {
		return blogname;
	}
	public void setBlogname(String blogname) {
		this.blogname = blogname;
	}
	public String getBlogdescription() {
		return blogdescription;
	}
	public void setBlogdescription(String blogdescription) {
		this.blogdescription = blogdescription;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getBlogdatetime() {
		return blogdatetime;
	}
	public void setBlogdatetime(Date blogdatetime) {
		this.blogdatetime = blogdatetime;
	}
	public char getBlogstatus() {
		return blogstatus;
	}
	public void setBlogstatus(char blogstatus) {
		this.blogstatus = blogstatus;
	}
	public String getBlogreaason() {
		return blogreaason;
	}
	public void setBlogreaason(String blogreaason) {
		this.blogreaason = blogreaason;
	}
	
	
	
	
	
}
