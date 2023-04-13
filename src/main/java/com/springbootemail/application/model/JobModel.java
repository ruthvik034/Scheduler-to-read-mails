package com.springbootemail.application.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class JobModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobID;
	@Column(unique = true)
	private String jobName;
	@Column
	private String description;
	@Column
	private String cronExpression;
	@Column
	private int retryCount;
	@Column
	private int delayBetweenRetries;
	@Column
	private boolean isActive;
	@Embedded
	private Retrive retrive;

	public Retrive getRetrive() {
		return retrive;
	}

	public void setRetrive(Retrive retrive) {
		this.retrive = retrive;
	}

	public long getJobID() {
		return jobID;
	}

	public void setJobID(long jobID) {
		this.jobID = jobID;
	}
	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Integer getDelayBetweenRetries() {
		return delayBetweenRetries;
	}

	public void setDelayBetweenRetries(Integer delayBetweenRetries) {
		this.delayBetweenRetries = delayBetweenRetries;
	}

	public boolean IsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}


	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "JobModel{" +
				"jobID=" + jobID +
				", jobName='" + jobName + '\'' +
				", description='" + description + '\'' +
				", cronExpression='" + cronExpression + '\'' +
				", retryCount=" + retryCount +
				", delayBetweenRetries=" + delayBetweenRetries +
				", isActive=" + isActive +
				", maildetails=" + retrive +
				'}';
	}
}
