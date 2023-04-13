package com.springbootemail.application.controller;


import com.springbootemail.application.Repository.JobRepository;
import com.springbootemail.application.exception.FinalException;
import com.springbootemail.application.model.JobModel;
import com.springbootemail.application.model.Retrive;
import com.springbootemail.application.request.JobRequest;
import com.springbootemail.application.service.APISchedulerService;
import com.springbootemail.application.util.ResponseGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/scheduler")
public class APISchedulerController {

	@Autowired
	private APISchedulerService apiSchedulerService;
@Autowired
private JobRepository jobRepository;

	@PostMapping(path = "/insert-job")
	public String insertJob(@ModelAttribute JobRequest jobRequest, Model model) throws FinalException {
		apiSchedulerService.insertJob(jobRequest);
		List<JobModel> listJobs=  jobRepository.findAll();
		model.addAttribute("listJobs",listJobs);
		model.addAttribute("jobRequest", jobRequest);
		return "fetch_jobs" ;
	}

	@PutMapping(path = "/update-job")
	public ResponseEntity<Object> updateJob(@RequestBody JobRequest jobRequest) throws FinalException {
		return new ResponseEntity<>(ResponseGeneratorUtil.okResponse(apiSchedulerService.updateJob(jobRequest)), HttpStatus.OK);
	}

	@GetMapping(path = "/get-job/{jobID}")
	public ResponseEntity<Object> getJobDetailsByID(@PathVariable("jobID") long jobID) throws FinalException {
		return new ResponseEntity<>(ResponseGeneratorUtil.okResponse(apiSchedulerService.getJobDetailsByID(jobID)), HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete-job/{jobID}")
	public String deleteJob(@ModelAttribute @PathVariable("jobID") long jobID,Model model,JobRequest jobRequest) throws FinalException {
		apiSchedulerService.deleteJob(jobID);
		List<JobModel> listJobs=  jobRepository.findAll();
		model.addAttribute("listJobs",listJobs);
		model.addAttribute("jobRequest", jobRequest);
		return "fetch_jobs" ;
	}
	@PutMapping(path = "/activate-job/{jobID}")
	public ResponseEntity<Object> activateJob(@PathVariable("jobID") long jobID) throws FinalException {
		return new ResponseEntity<>(ResponseGeneratorUtil.okResponse(apiSchedulerService.activateJob(jobID)), HttpStatus.OK);
	}

	@PutMapping(path = "/deactivate-job/{jobID}")
	public ResponseEntity<Object> deactivateJob(@PathVariable("jobID") long jobID) throws FinalException {
		return new ResponseEntity<>(ResponseGeneratorUtil.okResponse(apiSchedulerService.deactivateJob(jobID)), HttpStatus.OK);
	}
}
