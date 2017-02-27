package com.niit.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Model.UserModel;

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
	@Transactional
	public List<UserModel> list(){
		String hql="from UserModel";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
		
	}
	
	//-----------------------------------------------------login------------------------------------------------------------------------------------------------------------		
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public UserModel validate(String userid, String password) {
		System.out.println("hii am rajesh2");
		
	String hql="from UserModel where id='"+userid+"'and password='"+password+"'";
	System.out.println("hii am rajesh1");
	
	Query<UserModel> query=sessionFactory.getCurrentSession().createQuery(hql);
	System.out.println("hii am rajesh");
	return (UserModel) query.uniqueResult();
	}

	
	
	//-----------------------------------------------------registration------------------------------------------------------------------------------------------------------------	
	@Transactional
	public boolean save(UserModel userModel) {
	try{
		sessionFactory.getCurrentSession().save(userModel);
		return true;
	}catch(Exception e){
		
	}
		return false;
	}
}
