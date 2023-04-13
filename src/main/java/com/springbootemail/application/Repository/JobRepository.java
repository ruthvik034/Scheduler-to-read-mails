package com.springbootemail.application.Repository;


import com.springbootemail.application.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobModel,Long> {
	JobModel findByJobName(String JobName);

	Integer deleteByJobName(String JobName);
}
