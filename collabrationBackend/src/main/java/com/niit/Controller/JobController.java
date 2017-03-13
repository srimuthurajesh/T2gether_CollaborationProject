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
	private JobModel JobModel;
	@Autowired
	private JobDAO JobDAO;
	
	@GetMapping("/fetchallJobs")
	public List<JobModel> getallJob(){
		return JobDAO.getAllJob();
	}
	
	@PostMapping(value = "/createJob")
	public ResponseEntity<JobModel> createJob(@RequestBody JobModel Jobmodel, HttpSession session) {
		System.out.println("username:" + JobModel);
//		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
//		Jobmodel.setUserid(loggedInUserID);
//		Jobmodel.setJobstatus('N');// A->Accepted,  R->Rejected
		
		
		if(JobDAO.saveJob(Jobmodel)){
			JobModel=new JobModel();
			JobModel.setErrorCode("200");
			JobModel.setErrorMessage("Job created");
			
		}else{
			JobModel.setErrorCode("400");
			JobModel.setErrorMessage("Job not created ok, try again....");

				}

		return new ResponseEntity<JobModel>(Jobmodel, HttpStatus.OK);
	}

	
	@GetMapping("/getJobbyname/{Jobname}")
	public JobModel getJobbyname(@PathVariable("Jobname") String Jobname) {
		logger.debug("inside getJobbyname JobController ");
		JobModel JobModel = JobDAO.getJob(Jobname);
		
		if(JobModel==null)
		{
			JobModel = new JobModel();
			JobModel.setErrorCode("404");
			JobModel.setErrorMessage("Job not found with the id:" + Jobname);
		}
		
		return JobModel;
			}
}
