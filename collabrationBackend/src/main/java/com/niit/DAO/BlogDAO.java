package com.niit.DAO;

import java.util.List;

import com.niit.Model.BlogModel;
import com.niit.Model.CommentModel;

public interface BlogDAO {
	public BlogModel getBlog(String blogname);
			public List<BlogModel> getAllBlog();
	public List<BlogModel> getAllblogs(String username);
	public boolean saveblog(BlogModel blogModel);
	public boolean update(BlogModel blogModel);
	public void deleteblog(String blogname);
	public void addcomment(CommentModel commentModel);
	public List<CommentModel> getcomments(String blogname);

		
		

}
