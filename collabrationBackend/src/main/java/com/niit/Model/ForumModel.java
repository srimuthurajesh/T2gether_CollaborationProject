package com.niit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="FORUM_TABLE")
@Component
public class ForumModel extends BaseDomain{

	@Id
	private String forumname;
	
	private String forummembers;
	private String username;
	
	
	
}
