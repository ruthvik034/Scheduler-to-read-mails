# Scheduler-to-read-mails
An Email Schduler to real/download attachents from mails
  
Note:the program is made using mysql database,quartz scheduler,java mail Api'
1.Add your mysql schema  url in the application,properties file.
2.Make sure your schema has all the tables required to run a database based quartz scheduler(you can find the sql code in Quartz.sql file under resources folder
  
  
features:
1.you can directly download the mails by providing your credentials.
2.you can access whitelisted mails only(so that you cant download random files which might contains malware).
3.can be modifies to access ,mail with a specific subject pattern for further better security .
3.can Schedule to download at a specific time or in intervals .
4.can delete,activate,deactivate,update and delete your job .
5. specific domain mails can also be access as every mail domain has different imap protocol.(gmail & outlook are only included)
Ex:for outlook an authentication code is required to access the imap protocol .

API's:
1. login endpoint = http://localhost:8080/
2. main page endpoint = http://localhost:8080/index2

Example http requests:
// Update Job endpoint = localhost:8080/api/scheduler/update-job/
// HTTP method = PUT
// For updating a job get the entire request body using get-job details end point and make necessary changes
// Json body =
{
    "jobName": "email Schedule 2",
    "description": "This job is to download  the gmail attachments.2",
    "cronExpression": "0 33 11 ? * * *",
    "retryCount": 235,
    "delayBetweenRetries": 2335,
  "retrive":{
        "saveDirectory":"Directory",
        "userName":"Username",
        "password":"Password",
        "domain":"gmail",
        "tenantid":"1234",
        "clientid":"34211",
        "client_secret":"1231241",
        "email_list":["@gmail.com"]
  }
}

// Delete job end point = localhost:8080/api/scheduler/delete-job/235
// HTTP Method = DELETE

// Activate job end point = localhost:8080/api/scheduler/activate-job/235
// HTTP Method = PUT

// De-Activate job end point = localhost:8080/api/scheduler/deactivate-job/235
// HTTP Method = PUT
