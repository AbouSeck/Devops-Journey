**This is our Crud Webapp Pipeline Job's folder.**

![Jobs in home](/Capture%20d%E2%80%99%C3%A9cran%20(8).png?raw=true "The 5 Jobs")

We have our five jobs in XML Format : 
   - Job1.xml : RunMysqlServer Job
   - Job2.xml : BuildAPIRest Job
   - Job3.xml : RunRestApi Job
   - Job4.xml : BuildWebApp Job
   - Job5.xml : TestandDeployWebApp Job
   
   
To import this jobs : 
  - Clone this repository and checkout _crud-webapp_ branch
  - Go to Manage Jenkins > Jenkins CLI and download the Jenkins CLI jar file 
  - In the directory you downloaded it, type the following command :
        
         java -jar jenkins-cli.jar -s {jenkins url} -auth {username}:{password} create-job {newjobname} < {path to Jobs Folder}/{Job-want-import}.xml


![Crud WebApp Pipeline](Capture%20d%E2%80%99%C3%A9cran%20(9).png?raw=true "The Pipeline")
