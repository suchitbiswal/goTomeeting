package com.goToMeeting.citrix.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.goToMeeting.citrix.core.basePageObject;

public class manageWebinarPage extends basePageObject{

	public manageWebinarPage (WebDriver driver) {		
		super(driver);
		PageFactory.initElements(driver, this);
	}


	
	
	@FindBy(id = "trainingName")
	private WebElement webinarName;
	
	@FindBy(id = "trainingDesc")
	private WebElement webinarDescription;
	
	@FindBy(xpath = ".//*[@id='dateTime']/p")
	private WebElement webinarDateTime;
	
	@FindBy(id = "WebinarInfoID")
	private WebElement webinarID;
	
	
	
	
	public String getWebinarName () {
		return this.webinarName.getText();
	}
	
	public String getWebinarDescription () {
		return this.webinarDescription.getText();
	}

	
	public String getWebinarDateTime () {
		return this.webinarDateTime.getText();
	}
	
	public String getWebinarID () {
		return this.webinarID.getText();
	}
}
