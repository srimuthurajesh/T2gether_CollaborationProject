package com.niit.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.JobDAO;
import com.niit.Model.JobModel;
import com.niit.Model.UserModel;

@RestController
public class JobController {

	private static final Logger logger = 
			LoggerFactory.getLogger(JobController.class);
	

	@Autowired
	private JobModel jobModel;
	@Autowired
	private JobDAO jobDAO;
	
	@GetMapping("/fetchallJobs")
	public List<JobModel> getallJob(){
		return jobDAO.getAllJob();
	}
	
	@PostMapping(value = "/createJob")
	public ResponseEntity<JobModel> createJob(@RequestBody JobModel Jobmodel, HttpSession session) {
		System.out.println("username:" + jobModel);
//		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
//		Jobmodel.setUserid(loggedInUserID);
//		Jobmodel.setJobstatus('N');// A->Accepted,  R->Rejected
		
		
		if(jobDAO.saveJob(Jobmodel)){
			jobModel=new JobModel();
			jobModel.setErrorCode("200");
			jobModel.setErrorMessage("Job created");
			
		}else{
			jobModel.setErrorCode("400");
			jobModel.setErrorMessage("Job not created ok, try again....");

				}

		return new ResponseEntity<JobModel>(Jobmodel, HttpStatus.OK);
	}

	
	@GetMapping("/getJobbyname/{Jobname}")
	public JobModel getJobbyname(@PathVariable("Jobname") String Jobname) {
		logger.debug("inside getJobbyname JobController ");
		JobModel jobModel = jobDAO.getJob(Jobname);
		
		if(jobModel==null)
		{
			jobModel = new JobModel();
			jobModel.setErrorCode("404");
			jobModel.setErrorMessage("Job not found with the id:" + Jobname);
		}
		
		return jobModel;
			}

@GetMapping(value = "/deletejob/{jobname}")
	public void deleteBlog(@PathVariable("jobname")String jobname, HttpSession session) {
		
	System.out.println("hii am job"+jobname);
	jobDAO.deletejob(jobname);
}
}
