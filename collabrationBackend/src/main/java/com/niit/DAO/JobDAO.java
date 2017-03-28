package com.niit.DAO;

import java.util.List;

import com.niit.Model.JobApplyModel;
import com.niit.Model.JobModel;

public interface JobDAO {
	public JobModel getJob(String Jobname);
	public List<JobModel> getAllJob();
	public List<JobModel> getAllJobs(String username);
	public boolean saveJob(JobModel JobModel);
	public boolean update(JobModel JobModel);
	public void deletejob(String jobname);		
	public void applyjob(JobApplyModel JobApplyModel);
	public List<JobApplyModel> applyjobbyid(String username);
	

}
