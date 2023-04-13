package com.springbootemail.application.util;


import com.springbootemail.application.constants.JobConstants;
import com.springbootemail.application.exception.FinalException;
import com.springbootemail.application.model.JobModel;
import com.springbootemail.application.request.JobRequest;
import com.springbootemail.application.response.JobResponse;
import org.quartz.*;

public final class JobUtil {
	public static JobDetail buildJobDetail(final Class jobClass, final JobModel jobModel, final long jobID) throws FinalException {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(JobConstants.JOB_DATA, jobModel);
		jobDataMap.put(JobConstants.JOB_ID, jobID);
		return JobBuilder.newJob(jobClass).withIdentity(String.valueOf(jobID)).setJobData(jobDataMap).build();
	}

	public static Trigger buildTrigger(final Class jobClass, final JobModel jobModel, final long jobID) throws FinalException {
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(jobModel.getCronExpression());
		return TriggerBuilder.newTrigger().withIdentity(String.valueOf(jobID)).withSchedule(builder).startNow().build();
	}

	public static JobModel getJobModel(JobRequest jobRequest) {
		JobModel jobModel = new JobModel();
		jobModel.setJobID(jobRequest.getJobID());
		jobModel.setJobName(jobRequest.getJobName());
		jobModel.setCronExpression(jobRequest.getCronExpression());
		jobModel.setDescription(jobRequest.getDescription());

		jobModel.setRetryCount(jobRequest.getRetryCount());
		jobModel.setDelayBetweenRetries(jobRequest.getDelayBetweenRetries());
		jobModel.setIsActive(jobRequest.getIsActive());
		return jobModel;
	}

	public static JobResponse getJobResponse(JobModel jobModel) {
		JobResponse jobResponse = new JobResponse();
		jobResponse.setJobID(jobModel.getJobID());
		jobResponse.setJobName(jobModel.getJobName());
		jobResponse.setActive(jobModel.IsActive());
		return jobResponse;
	}
}
