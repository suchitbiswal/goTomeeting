package com.goToMeeting.citrix.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class basePageObject extends baseAssertVerify{

	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public basePageObject(WebDriver driver) {
		this.driver = driver;	
		wait = new WebDriverWait(driver, 20);
		
	}
		

	
	// TBD Suchit : Wait methods go here 
	
	
	public void WaitForElementToBeClickable (WebElement element, int itime) {
		
		log ("Waiting for web element  >>:"+element.toString()+" ");
		wait = new WebDriverWait(driver, itime);
		
		element = wait.until(ExpectedConditions.elementToBeClickable(element));		
		
	}
	
	
	public void WaitForElementToBeVisible (WebElement element, int itime) {
		log ("Waiting for web element  >>:"+element.toString()+" ");
		wait = new WebDriverWait(driver, itime);
		element = wait.until(ExpectedConditions.visibilityOf(element));		
		
	}
	

	 
	
}
