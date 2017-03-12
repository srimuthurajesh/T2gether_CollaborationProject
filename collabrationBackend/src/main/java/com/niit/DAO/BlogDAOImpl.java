package com.niit.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Model.BlogModel;

@EnableTransactionManagement
@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	
	//------------------------------------------------------GET BLOG---------------------------------------------------------------
	@Transactional
	public BlogModel getBlog(String blogname){
		return (BlogModel) sessionFactory.getCurrentSession().get(BlogModel.class, blogname);
	
	}
	
	
	//------------------------------------------------------GET ALL BLOG---------------------------------------------------------------
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<BlogModel> getAllBlog(){
		Query<BlogModel> query = sessionFactory.getCurrentSession().createQuery("from BlogModel");
	return query.list();
	}
	
	//------------------------------------------------------GET all BLOG of by user---------------------------------------------------------------
	@Transactional
	public List<BlogModel> getAllblogs(String username){
		String hql="from BlogModel where username='"+username+"'";
	Query<BlogModel> query = sessionFactory.getCurrentSession().createQuery(hql);
return query.list();
	}
	
	//------------------------------------------------------SAVE BLOG---------------------------------------------------------------
	@Transactional
	public boolean saveblog(BlogModel blogModel){
		try{
			sessionFactory.getCurrentSession().save(blogModel);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//------------------------------------------------------update BLOG---------------------------------------------------------------
	@Transactional
	public boolean update(BlogModel blogModel){
		return false;
	}
}
