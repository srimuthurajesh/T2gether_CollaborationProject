package com.niit.DAO;

import java.util.List;

import com.niit.Model.UserModel;

public interface UserDAO{

	public UserModel get(String userid);
	public UserModel  validate(String userid, String password);
	//public boolean  validate(String userid, String password);
	
	public boolean save(UserModel userModel);
	public List<UserModel> list();

}
