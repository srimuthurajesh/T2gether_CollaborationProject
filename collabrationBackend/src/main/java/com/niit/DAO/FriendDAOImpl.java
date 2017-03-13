package com.niit.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Model.FriendModel;

@EnableTransactionManagement
@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Transactional
	public void addfriend(FriendModel friendModel){
		System.out.println("hjii am inside daoimpl");
		sessionFactory.getCurrentSession().save(friendModel);
		
		
	}
}
