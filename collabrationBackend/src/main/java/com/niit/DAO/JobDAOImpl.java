package com.niit.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Model.BlogModel;
import com.niit.Model.FriendModel;
import com.niit.Model.JobApplyModel;
import com.niit.Model.JobModel;
import com.niit.Model.UserModel;

@EnableTransactionManagement
@Repository("JobDAO")
public class JobDAOImpl implements JobDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	
	//------------------------------------------------------GET Job---------------------------------------------------------------
	@Transactional
	public JobModel getJob(String Jobname){
		return (JobModel) sessionFactory.getCurrentSession().get(JobModel.class, Jobname);
	
	}
	
	
	//------------------------------------------------------GET ALL Job---------------------------------------------------------------
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<JobModel> getAllJob(){
		Query<JobModel> query = sessionFactory.getCurrentSession().createQuery("from JobModel");
	return query.list();
	}
	
	//------------------------------------------------------GET all Job of by user---------------------------------------------------------------
	@Transactional
	public List<JobModel> getAllJobs(String username){
		String hql="from JobModel where username='"+username+"'";
	Query<JobModel> query = sessionFactory.getCurrentSession().createQuery(hql);
return query.list();
	}
	
	//------------------------------------------------------SAVE Job---------------------------------------------------------------
	@Transactional
	public boolean saveJob(JobModel JobModel){
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(JobModel);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//------------------------------------------------------update Job---------------------------------------------------------------
	@Transactional
	public boolean update(JobModel JobModel){
		return false;
	}
	@Transactional
	public void deletejob(String jobname){
		Session session=sessionFactory.openSession();
	JobModel jobModel= new JobModel();
	
		String hql="from JobModel where jobname='"+jobname+"'";
			jobModel=(JobModel) session.createQuery(hql).uniqueResult();
			sessionFactory.getCurrentSession().delete(jobModel);
				}

	//------------------------------------------------------SAVE Job---------------------------------------------------------------
	@Transactional
	public void applyjob(JobApplyModel jobApplyModel){
		sessionFactory.getCurrentSession().save(jobApplyModel);	

	}
	@Transactional
	public List<JobApplyModel> applyjobbyid(String username){
		String hql = "from JobApplyModel where username = '" + username + "'" ;
		List<JobApplyModel> list= sessionFactory.openSession().createQuery(hql).list();

		return list;

	}
	
}
