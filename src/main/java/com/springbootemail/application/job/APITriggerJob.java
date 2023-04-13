package com.springbootemail.application.job;

import com.springbootemail.application.constants.JobConstants;
import com.springbootemail.application.model.JobModel;
import com.springbootemail.application.model.Retrive;
import com.springbootemail.application.service.MailService;
import com.springbootemail.application.util.LoggerWrapper;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class APITriggerJob implements Job {

    private static final Logger LOG = LoggerWrapper.getLogger(APITriggerJob.class);
@Autowired
private Retrive retrive;
@Autowired
private MailService notificationService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        JobModel jobModel = (JobModel) jobDataMap.get(JobConstants.JOB_DATA);
        String jobID = String.valueOf(jobDataMap.get(JobConstants.JOB_ID));

System.out.println(jobModel);
this.retrive=jobModel.getRetrive();
        try{
            notificationService.readMails(retrive);
            if(notificationService.getNotfound()){
                System.out.println("no_mails");
            }

        }
        catch (Exception e){
            System.out.println(String.valueOf(e));
        }
        System.out.println("download_Success")  ;



    }
}
