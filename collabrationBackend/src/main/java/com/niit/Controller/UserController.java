package com.niit.Controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.DAO.UserDAO;
import com.niit.Model.UserModel;
import com.niit.config.MailConfig;

@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserModel userModel;
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public ResponseEntity<List<UserModel>> getAllUser(){  
		List<UserModel> userobjlist= userDAO.list();        

	if(userobjlist.isEmpty()){
		userModel.setErrorCode("100");
		userModel.setErrorMessage("Not user are available");
		userobjlist.add(userModel);
		return new ResponseEntity <List<UserModel>>(userobjlist,HttpStatus.OK);
		}
	
		userModel.setErrorCode("200");
		userModel.setErrorMessage("User is available");
			return new ResponseEntity <List<UserModel>>(userobjlist,HttpStatus.OK);
		}
	
	
	
	
	
	//@GetMapping(value="/validate")
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public UserModel validateCredentials(@RequestBody UserModel userModel, HttpSession session){
UserModel userModel2= new UserModel();
userModel2=userDAO.validate(userModel.getUsername(), userModel.getPassword());
		if(userModel2 == null){
			userModel=new UserModel();
			userModel.setErrorCode("404");
System.out.println("Invalid Credential..password..plese try again");
			userModel.setErrorMessage("Invalid Credential..password..plese try again");
			
		}else{
			
			userDAO.saveonline(userModel2);
			userModel.setErrorCode("200");
			System.out.println("sucess log in ");
			userModel.setErrorMessage("You aer succesfully logged in ....");
			session.setAttribute("Username", userModel.getUsername());
			
				}
return userModel2;
}

	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
		public ResponseEntity<UserModel> Register(@RequestBody UserModel userModel) throws MessagingException {
		if(userDAO.get(userModel.getUsername())==null){
			userDAO.save(userModel);
		
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			   ctx.register(MailConfig.class);
			   ctx.refresh();
			  
			   JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
			   MimeMessage mimeMessage = mailSender.createMimeMessage();
		      	   MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
		      	   mailMsg.setFrom("srimuthurajesh007@gmail.com");
		      	   mailMsg.setTo(userModel.getEmail());
		      	   mailMsg.setSubject("Activated");
		      	   mailMsg.setText("Your successfull registered");
			   mailSender.send(mimeMessage);
		
			
			
			userModel.setErrorCode("200");
			userModel.setErrorMessage("Successfully registered");
		System.out.println("successfully registers");	
		
		
		}
		else{userModel.setErrorCode("404");
		System.out.println("not registers");	
		
		userModel.setErrorMessage("User Exist with name "+userModel.getUsername());
		}
			return new ResponseEntity<UserModel>(userModel,HttpStatus.OK);
		
}	
	@GetMapping("/getUser/{username}")
	public ResponseEntity<UserModel> getUser(@PathVariable("username") String username){
		userModel=userDAO.get(username);
		if(userModel==null){
			userModel= new UserModel();
			userModel.setErrorCode("404");
			userModel.setErrorMessage("No user found for "+username);
		}
	return new ResponseEntity<UserModel>(userModel,HttpStatus.OK);  
			}
	@GetMapping("/logout")
	public void logout(HttpSession session){
		String username = (String) session.getAttribute("Username");
		userDAO.offline(username);
		session.invalidate();

	
	}
	
}














