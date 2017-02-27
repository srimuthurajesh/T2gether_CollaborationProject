package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.BlogDAO;
import com.niit.Model.BlogModel;

@RestController
public class BlogController {

	@Autowired
	private BlogModel blogModel;
	@Autowired
	private BlogDAO blogDAO;
	
	@GetMapping("/getallBlog")
	public List<BlogModel> getallBlog(){
		return blogDAO.getAllBlog();
	}
	
	@PutMapping("/approveblog/{blogID}")
	public BlogModel approveblog(@PathVariable("blogid")int blogid){
		blogModel=blogDAO.getBlog(blogid);
		blogModel.setBlogstatus('A');
		
		if(blogDAO.update(blogModel)){
			blogModel.setErrorCode("200");
			blogModel.setErrorMessage("SuccessFully approved");
		}else{
			blogModel.setErrorCode("404");
			blogModel.setErrorMessage("No able to approve the blog");
			
		}return blogModel;
	}
}
