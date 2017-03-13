package com.niit.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.FriendDAO;
import com.niit.Model.FriendModel;

@RestController
public class FriendController {
	
	@Autowired
	FriendModel friendModel;
	@Autowired
	FriendDAO friendDAO;
	
@GetMapping("/addfriend/{username}")
public ResponseEntity<FriendModel> addfriend(@PathVariable String username, HttpSession session) {
	
	String username1 = (String) session.getAttribute("Username");
	friendModel.setUsername1(username1);
	friendModel.setUsername2(username);
	friendModel.setFriendstatus('w');
	friendDAO.addfriend(friendModel);
	return new ResponseEntity<FriendModel>(friendModel, HttpStatus.OK);


}}
