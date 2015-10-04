package com.goToMeeting.citrix.tests;

import java.sql.Timestamp;
import java.util.Date;

import org.testng.annotations.Test;

import com.goToMeeting.citrix.core.baseAssertVerify;
import com.goToMeeting.citrix.core.baseWebDriver;
import com.goToMeeting.citrix.core.testConfig;
import com.goToMeeting.citrix.pages.loginPage;
import com.goToMeeting.citrix.pages.manageWebinarPage;
import com.goToMeeting.citrix.pages.myWebinarsPage;

public class goToMeetingTests extends baseWebDriver  {

	//http://www.gotomeeting.com/online/webinar
		
	@Test (enabled = true)
	public void createWebinarTest () throws Exception {
			
		
		int noOfDays = 3;
		int durationOfWebinar = 2;
		
		loginPage login= new loginPage (driver);
		myWebinarsPage mywebinar= new myWebinarsPage (driver);
		
		login.Login (testConfig.userName, testConfig.password, testConfig.url);		
		
		//Verify login is success and my webinar page appears 
		mywebinar.verifyPageTitle("My Webinars");
		
		mywebinar.cancelAlltheWebinars ();
		
		login.goToCreateWebinar();	
		
		//verify create webinar page appears 
		mywebinar.verifyPageTitle("Schedule a webinar");	
	
		
		String WebinarTitle = "GotoMeeting Webinar for test " + new Timestamp(System.currentTimeMillis());
		Date scheduleDate = mywebinar.addDaysTocurrentDate(noOfDays);
		
		//create a webinar after 3 days 		
		mywebinar.createWebinar (scheduleDate, WebinarTitle,  durationOfWebinar);
			
		//verify if the webinar created successfully in Manage webinar page 
		manageWebinarPage managewebinar= new manageWebinarPage (driver);
		mywebinar.verifyPageTitle("Manage Webinar");	
		verifyEquals(managewebinar.getWebinarName(), WebinarTitle);
		verifyEquals(managewebinar.getWebinarDescription(), WebinarTitle);
		verifyEquals( managewebinar.getWebinarDateTime(), mywebinar.formatDateforVerification(scheduleDate,durationOfWebinar));
					
		//get the webinar ID 
		String WebinarId= managewebinar.getWebinarID();
		
		//Verify if webinar created from mywebinar page 
		managewebinar.goToMyWebinar();
		mywebinar.verifyPageTitle("My Webinars");
		verifyEquals(mywebinar.getWebinarNameFromMyWebinar(WebinarId), WebinarTitle);
		verifyEquals(mywebinar.getWebinarDateTimeFromMyWebinar(WebinarId), mywebinar.formatDateforVerification(scheduleDate, 2));
			

		
	}
	
	
}
