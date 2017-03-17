package com.niit.DAO;

import java.util.List;

import com.niit.Model.BlogModel;

public interface BlogDAO {
	public BlogModel getBlog(String blogname);
			public List<BlogModel> getAllBlog();
	public List<BlogModel> getAllblogs(String username);
	public boolean saveblog(BlogModel blogModel);
	public boolean update(BlogModel blogModel);
	public void deleteblog(String blogname);
		

}
