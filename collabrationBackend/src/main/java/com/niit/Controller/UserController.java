package com.niit.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*import com.niit.DAO.UserDAO;
import com.niit.Model.UserModel;
*/

import com.niit.DAO.UserDAO;
import com.niit.Model.UserModel;


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
	public ResponseEntity<UserModel> validateCredentials(@RequestBody UserModel userModel, HttpSession session){
		
		if(userDAO.validate(userModel.getUsername(), userModel.getPassword()) == null){
			userModel=new UserModel();
			userModel.setErrorCode("404");
			userModel.setErrorMessage("Invalid Credential..password..plese try again");
			
		}else{
			userModel.setErrorCode("200");
			userModel.setErrorMessage("You aer succesfully logged in ....");
			session.setAttribute("Username", userModel.getUsername());
			
				}
return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
}

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
		public ResponseEntity<UserModel> Register(@RequestBody UserModel userModel){
		if(userDAO.get(userModel.getUsername())==null){
			userDAO.save(userModel);
			userModel.setErrorCode("200");
			userModel.setErrorMessage("Successfully registered");}
		else{userModel.setErrorCode("400");
		userModel.setErrorMessage("User Exist with name "+userModel.getUsername());
		}
			return new ResponseEntity<UserModel>(userModel,HttpStatus.OK);
		
}
	@GetMapping("/hello")
	public String rajesh(){
		userDAO.save(userModel);
		userModel.setErrorCode("200");
		userModel.setErrorMessage("Successfully registered");
	
		return "hwllo rajesh, how u doing";
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
	
}














