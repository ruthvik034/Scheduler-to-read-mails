package com.springbootemail.application.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.springbootemail.application.model.Retrive;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import javax.persistence.Embedded;
import java.util.*;

public class JobRequest {
	@Nullable
	private long jobID;
	private String jobName;
	private String description;
	private String cronExpression;
	private int retryCount;
	private int delayBetweenRetries;
	private boolean isActive;
	private Retrive retrive;

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

	public boolean getIsActive() {
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


	public Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			} else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			} else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	public Retrive getRetrive() {
		return retrive;
	}

	public void setRetrive(Retrive retrive) {
		this.retrive = retrive;
	}

	@Override
	public String toString() {
		return "JobRequest{" +
				"jobID=" + jobID +
				", jobName='" + jobName + '\'' +
				", description='" + description + '\'' +
				", cronExpression='" + cronExpression + '\'' +
				", retryCount=" + retryCount +
				", delayBetweenRetries=" + delayBetweenRetries +
				", isActive=" + isActive +
				", retrive=" + retrive +
				'}';
	}
}
