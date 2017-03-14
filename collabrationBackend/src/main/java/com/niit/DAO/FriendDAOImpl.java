package com.niit.DAO;
import java.util.List;

import org.hibernate.Session;
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
	@Transactional
	public List<FriendModel> notifications(String username){

		System.out.println("am inside notification backend daoimpl");
String hql="from FriendModel where username2= '"+username+"'and friendstatus='w'";
	List<FriendModel> list= sessionFactory.openSession().createQuery(hql).list();
	return list;
	}
	
	@Transactional
	public void acceptfriend(FriendModel friendModel){
		System.out.println("am inside acceptfriend daoimpl");
		Session session=sessionFactory.openSession();
		String hql="from FriendModel where username1= '"+friendModel.getUsername1()+"'and username2='"+friendModel.getUsername2()+"'";
		FriendModel friendModel2= (FriendModel) session.createQuery(hql).uniqueResult();
		friendModel2.setFriendstatus('f');
		System.out.println(friendModel2.getFriendid());
		System.out.println(friendModel2.getUsername1());
		System.out.println(friendModel2.getUsername2());
		System.out.println(friendModel2.getFriendstatus());
		System.out.println(friendModel2);
		session.saveOrUpdate(friendModel2);
				System.out.println("save or update done");
						session.close();
	}

	
}
