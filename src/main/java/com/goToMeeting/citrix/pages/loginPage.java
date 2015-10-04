package com.goToMeeting.citrix.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.goToMeeting.citrix.core.basePageObject;
//
public class loginPage extends basePageObject {

	public loginPage (WebDriver driver) {		
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//a[@href='https://global.gotowebinar.com']")
	private WebElement signInLink;

	@FindBy(id = "emailAddress")
	private WebElement emailAdress;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "submit")
	private WebElement submit;

	@FindBy(id = "scheduleWebinar")
	private WebElement scheduleWebinar;
	
	

	/**
	 * Logins to the goTomeeting webinar 
	 * @param userName
	 * @param Password
	 * @throws Exception 
	 */
	public void Login (String userName, String Password, String url) throws Exception {

		driver.get(url);		
		driver.manage().window().maximize();
	
		WaitForElementToBeClickable(emailAdress, 20);	
		emailAdress.clear();
		emailAdress.sendKeys(userName);
		
		password.sendKeys(Password);		
		submit.click();	
	} 

	
	public void goToCreateWebinar () {
		WaitForElementToBeClickable(scheduleWebinar, 20);
		scheduleWebinar.click();
	}

	


}
