package com.niit.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.BlogDAO;
import com.niit.Model.BlogModel;
import com.niit.Model.CommentModel;

@RestController
public class BlogController {

	private static final Logger logger = 
			LoggerFactory.getLogger(BlogController.class);
	

	@Autowired
	private BlogModel blogModel;
	@Autowired
	private BlogDAO blogDAO;
	
	@GetMapping("/fetchallblogs")
	public List<BlogModel> getallBlog(){
		return blogDAO.getAllBlog();
	}
	
	@PostMapping(value = "/createblog")
	public ResponseEntity<BlogModel> createBlog(@RequestBody BlogModel blogmodel, HttpSession session) {
		String username = (String) session.getAttribute("Username");
		blogmodel.setUsername(username);
//		blogmodel.setBlogstatus('N');// A->Accepted,  R->Rejected
		
		
		if(blogDAO.saveblog(blogmodel)){
			blogModel=new BlogModel();
			blogModel.setErrorCode("200");
			blogModel.setErrorMessage("Blog created");
			
		}else{
			blogModel.setErrorCode("400");
			blogModel.setErrorMessage("blog not created ok, try again....");

				}

		return new ResponseEntity<BlogModel>(blogmodel, HttpStatus.OK);
	}
	
	@PutMapping("/approveblog/{blogID}")
	public BlogModel approveblog(@PathVariable("blogid")String blogid){
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
	
	@GetMapping("/getblogbyname/{blogname}")
	public BlogModel getBlogbyname(@PathVariable("blogname") String blogname) {
		logger.debug("inside getblogbyname BlogController ");
		BlogModel blogModel = blogDAO.getBlog(blogname);
		
		if(blogModel==null)
		{
			blogModel = new BlogModel();
			blogModel.setErrorCode("404");
			blogModel.setErrorMessage("Blog not found with the id:" + blogname);
		}
		
		return blogModel;
			}
	@GetMapping(value = "/userblog")
			public List<BlogModel> userBlog( HttpSession session) {
				String username = (String) session.getAttribute("Username");
			return blogDAO.getAllblogs(username);
			
	}

@GetMapping(value = "/deleteblog/{blogname}")
	public void deleteBlog(@PathVariable("blogname")String blogname, HttpSession session) {
		blogDAO.deleteblog(blogname);
}

@GetMapping(value = "/editblog/{blogname}")
	public BlogModel editBlog(@PathVariable("blogname")String blogname, HttpSession session) {
		return blogDAO.getBlog(blogname);
}


@PostMapping(value = "/addcomment")
	public void addcomment(@RequestBody CommentModel commentModel, HttpSession session) {
	System.out.println("hi am"+commentModel);
	System.out.println(commentModel.getBlogname());
	System.out.println(commentModel.getComments());				
	String username = (String) session.getAttribute("Username");
commentModel.setUsername(username);
blogDAO.addcomment(commentModel);
}

@GetMapping(value = "/getcomments/{blogname}")
public List<CommentModel> getcomments(@PathVariable ("blogname")String blogname) {
System.out.println("inside controller getcomments");
	return blogDAO.getcomments(blogname);
}
			
}
