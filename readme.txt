
Setup instructions for eclipse
	1. Make sure the machine has maven and JDK 1.7.* installed 
	2. pull the project from github
	3. go to the folder /workspace/goTomeeting, make sure pom.xml is present 
	4. here are the commands to create the project for eclips e
		a. mvn eclipse:clean 
		b. mvn eclipse:eclipse 
	5. mvn eclipse:eclipse will take some time to download the dependencies 
	6. open eclipse on the machine 
	7. select :     File > Import > General > Existing Projects into workspace 
	8. In the Select Root directory Browse to the /workspace/goTomeeting folder where pom.xml is present 
	9. select the project and finish
	
Will the these instructions project setup should have been complete 

Execution instruction : 
	1. I have kept it simple for now, it will execute the only test that I created from commandline 
	2. it supports chrome and firefox browser for now, please make changes in the testconfig.properties to change the browser 
	3. Suite file can conroll the execution classes and methods 
	4. For execution from command line 
		a. open terminal / dos prompt 
		b. go to folder /workspace/goTomeeting, where pom.xml is present execute the below command 
		c. mvn verify 
		d. this will execute the suite file goToMeeting_Suite.xml 
		
Results  instructions : 
	1. execution results can be seen from the command line / terminal as it executes 
	2. it also generates the HTML report in the folder /workspace/goTomeeting/target/failsafe-report/html/index.html
	3. open the above html from browser or from eclipse browser it will show the details of execution


		
	
	
