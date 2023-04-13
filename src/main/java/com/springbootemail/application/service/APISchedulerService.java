package com.springbootemail.application.service;


import com.springbootemail.application.Repository.JobRepository;
import com.springbootemail.application.constants.ErrorCodes;
import com.springbootemail.application.constants.JobConstants;
import com.springbootemail.application.exception.FinalException;
import com.springbootemail.application.job.APITriggerJob;
import com.springbootemail.application.model.JobModel;
import com.springbootemail.application.model.Retrive;
import com.springbootemail.application.request.JobRequest;
import com.springbootemail.application.response.JobResponse;
import com.springbootemail.application.scheduler.JobScheduler;
import com.springbootemail.application.util.JobUtil;
import com.springbootemail.application.util.LoggerWrapper;
import com.springbootemail.application.util.RequestValidatorUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class APISchedulerService {

    private static final Logger LOG = LoggerWrapper.getLogger(APISchedulerService.class);

    private final JobRepository jobRepository;

    private final JobScheduler jobScheduler;

    private final RequestValidatorUtil requestValidatorUtil;

    @Autowired
    public APISchedulerService(JobScheduler jobScheduler, JobRepository jobRepository, RequestValidatorUtil requestValidatorUtil) {
        this.jobScheduler = jobScheduler;
        this.jobRepository = jobRepository;
        this.requestValidatorUtil = requestValidatorUtil;
    }

    public JobResponse insertJob(JobRequest jobRequest) throws FinalException {
        try {
            this.requestValidatorUtil.validateJobRequest(jobRequest);

            jobRequest.setIsActive(true);

            JobModel jobModel = JobUtil.getJobModel(jobRequest);
            jobModel.setRetrive(jobRequest.getRetrive());
            jobModel = this.jobRepository.save(jobModel);
            jobRequest.setJobID(jobModel.getJobID());

            this.jobScheduler.insertJob(APITriggerJob.class, jobModel);

            return JobUtil.getJobResponse(jobModel);
        } catch (DataIntegrityViolationException de) {
            throw new FinalException(ErrorCodes.CLIENT_ERROR, de.getMessage());
        }
    }

    public JobResponse updateJob(JobRequest jobRequest) throws FinalException {
        try {
            this.requestValidatorUtil.validateJobRequest(jobRequest);

            JobModel jobModel = JobUtil.getJobModel(jobRequest);
            this.jobScheduler.updateJob(APITriggerJob.class, jobModel);

            jobModel = this.jobRepository.save(jobModel);

            return JobUtil.getJobResponse(jobModel);
        } catch (DataIntegrityViolationException de) {
            throw new FinalException(ErrorCodes.CLIENT_ERROR, de.getMessage());
        }
    }

    public JobModel getJobDetailsByID(long jobID) throws FinalException {
        try {
            return this.jobScheduler.getJobDetailsByID(jobID);
        } catch (DataIntegrityViolationException de) {
            throw new FinalException(ErrorCodes.CLIENT_ERROR, de.getMessage());
        }
    }

    @Transactional
    public JobModel deleteJob(long jobID) throws FinalException {
        try {
            JobModel jobModel = this.jobScheduler.deleteJob(jobID);
            this.jobRepository.deleteById(jobID);

            return jobModel;
        } catch (DataIntegrityViolationException de) {
            throw new FinalException(ErrorCodes.CLIENT_ERROR, de.getMessage());
        }
    }

    public String activateJob(long jobID) throws FinalException {
        try {
            boolean isActivated = this.jobScheduler.activateJob(jobID);

            Optional<JobModel> jobModel = this.jobRepository.findById(jobID);
            jobModel.get().setActive(isActivated);
            this.jobRepository.save(jobModel.get());

            return isActivated ? JobConstants.JOB_ACTIVATED : JobConstants.JOB_NOT_ACTIVATED;
        } catch (DataIntegrityViolationException de) {
            throw new FinalException(ErrorCodes.CLIENT_ERROR, de.getMessage());
        }
    }

    public String deactivateJob(long jobID) throws FinalException {
        try {
            boolean isDeactivated = this.jobScheduler.deactivateJob(jobID);

            Optional<JobModel> jobModel = this.jobRepository.findById(jobID);
            jobModel.get().setActive(!isDeactivated);
            this.jobRepository.save(jobModel.get());

            return isDeactivated ? JobConstants.JOB_DEACTIVATED : JobConstants.JOB_NOT_DEACTIVATED;
        } catch (DataIntegrityViolationException de) {
            throw new FinalException(ErrorCodes.CLIENT_ERROR, de.getMessage());
        }
    }
}
