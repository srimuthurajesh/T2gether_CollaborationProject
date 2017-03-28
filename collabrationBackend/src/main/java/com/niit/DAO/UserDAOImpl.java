package com.niit.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Model.UserModel;

@SuppressWarnings("deprecation")
@EnableTransactionManagement
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	//-----------------------------------------------------getby id------------------------------------------------------------------------------------------------------------	
	@Transactional
	public UserModel get(String username) {
return sessionFactory.getCurrentSession().get(UserModel.class, username);
	}

	//-----------------------------------------------------getlist------------------------------------------------------------------------------------------------------------		
	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserModel> list(){
		String hql="from UserModel";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
		
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public UserModel validate(String username, String password) {
		String hql = "from UserModel where username = '" + username + "' and password = '" + password +"'";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		return (UserModel) query.uniqueResult();

		
	}
	
	

	
	//-----------------------------------------------------registration------------------------------------------------------------------------------------------------------------	
	@Transactional
	public boolean save(UserModel userModel) {
	try{
		sessionFactory.getCurrentSession().save(userModel);
		return true;
	}catch(Exception e){
		System.out.println("error has been catched");
		return false;
	}
		
	}
	
	@Transactional
	public boolean saveonline(UserModel userModel) {
	try{
		userModel.setIs_online('y');
		sessionFactory.getCurrentSession().saveOrUpdate(userModel);
		return true;
	}catch(Exception e){
		
	}
		return false;
	}
	@Transactional
	public boolean offline(String username) {
	try{
		String hql = "from UserModel where username = '" + username + "'";
		Query query =  sessionFactory.getCurrentSession().createQuery(hql);
		UserModel userModel=(UserModel) query.uniqueResult();

	System.out.println("inside logout");
	userModel.setUsername(username);	
	userModel.setIs_online('N');
		sessionFactory.getCurrentSession().saveOrUpdate(userModel);
		return true;
	}catch(Exception e){
		
	}
		return false;
	}
}

