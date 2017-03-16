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
		friendModel= (FriendModel) session.createQuery(hql).uniqueResult();
		friendModel.setFriendstatus('f');
		sessionFactory.getCurrentSession().saveOrUpdate(friendModel);
				
				
		hql="from FriendModel where username1= '"+friendModel.getUsername2()+"'and username2='"+friendModel.getUsername1()+"'";
		if( session.createQuery(hql).uniqueResult()==null){
			FriendModel friendModel2= new FriendModel();
			friendModel2.setUsername1(friendModel.getUsername2());
			friendModel2.setUsername2(friendModel.getUsername1());
			friendModel2.setFriendstatus('f');
			sessionFactory.getCurrentSession().save(friendModel2);
			
		}else{
			friendModel.setFriendstatus('f');
			sessionFactory.getCurrentSession().saveOrUpdate(friendModel);
					
		}
				
				session.close();
	
		}

	@Transactional
	public List<FriendModel> friendslist(String username){
		Session session=sessionFactory.openSession();
		String hql="from FriendModel where username1= '"+username+"'and friendstatus='f'";
		List<FriendModel> list= sessionFactory.openSession().createQuery(hql).list();

		return list;
	}

	@Transactional
	public void unfriend(String username1, String username2){
	Session session=sessionFactory.openSession();
	String hql="from FriendModel where username1= '"+username1+"'and username2='"+username2+"'";
	FriendModel friendModel= new FriendModel();
	friendModel=(FriendModel) session.createQuery(hql).uniqueResult();
	sessionFactory.getCurrentSession().delete(friendModel);

	 hql="from FriendModel where username1= '"+username2+"'and username2='"+username1+"'";
	friendModel=(FriendModel) session.createQuery(hql).uniqueResult();
	sessionFactory.getCurrentSession().delete(friendModel);
	
	
	}
	
}
