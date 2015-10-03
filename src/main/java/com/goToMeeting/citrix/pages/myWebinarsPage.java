package com.goToMeeting.citrix.pages;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import com.goToMeeting.citrix.core.basePageObject;;


public class myWebinarsPage extends basePageObject {

	public myWebinarsPage (WebDriver driver) {		
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "scheduleWebinar")
	private WebElement scheduleWebinar;

	@FindBy(css = "#scheduleForm #name")
	private WebElement newWebinarName;

	@FindBy(css = "#scheduleForm #description")
	private WebElement newWebinarDescription;

	@FindBy(id = "recurrenceForm_recurs_trig")
	private WebElement selectOccurance;

	@FindBy(css = "#recurrenceForm_recurs__menu")
	private WebElement selectOccuranceMenu;

	@FindBy(xpath = ".//*[@id='ui-datepicker-div']//span[@class='ui-datepicker-month']")
	private WebElement datePickerMonth;

	@FindBy(xpath = ".//*[@id='ui-datepicker-div']//span[@class='ui-datepicker-year']")
	private WebElement datePickerYear;

	@FindBy(css = "#ui-datepicker-div .ui-datepicker-next")
	private WebElement datePickerNext;

	@FindBy(id = "webinarTimesForm.dateTimes_0.baseDate")
	private WebElement dateInput;	

	@FindBy(id = "webinarTimesForm.dateTimes_0.startTime")
	private WebElement startTimeInput;

	@FindBy(id = "webinarTimesForm.dateTimes_0.endTime")
	private WebElement endTimeInput;

	@FindBy(id = "schedule.submit.button")
	private WebElement scheduleBtn;

	@FindBy(id = "webinarTimesForm_dateTimes_0_startAmPm_trig")
	private WebElement startTimeAMPM;

	@FindBy(id = "webinarTimesForm_dateTimes_0_endAmPm_trig")
	private WebElement endTimeAMPM;

	@FindBy(xpath = ".//a[@href='#cancelWebinarContainer']")
	private WebElement cancelWebinarLink;
	

	@FindBy(id = "manageWebinar")
	private WebElement manageWebinarContainer;
	
	@FindBy(id = "confirmDelete")
	private WebElement confirmDeleteBtn;
	
	
	/**
	 * Select the occurrence from the ListBox on the my Webinar page, 
	 * (This method used java script executer to do the select 
	 * @param selection
	 */
	public void selectOccurance (String selection) throws Exception {


		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('recurrenceForm_recurs').style.display='inline';");
		Select select = new Select(driver.findElement(By.id("recurrenceForm_recurs")));
		select.selectByVisibleText(selection);
		executor.executeScript("document.getElementById('recurrenceForm_recurs').style.display='none';");

	}

	/**
	 * Returns the date after no of days, its ignores the week end days 
	 * @param noOfDays
	 * @return 
	 */
	public Date addDaysTocurrentDate  (int noOfDays) {

		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MMMMM-dd" );   
		Calendar cal = Calendar.getInstance();  

		for (int i=0;i<noOfDays;) {
			cal.add(Calendar.DAY_OF_MONTH, 1);

			if(cal.get(Calendar.DAY_OF_WEEK)<=5)
			{
				i++;
			}
		}
		return cal.getTime();
	}

	/**
	 * select the provided date from date picker including the start and end time for the 
	 * @param Date
	 * @throws Exception 
	 */
	private void ChoosedateAndTime (Date selectDate, int durationInHr) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MMMMM-dd" );

		log ("selecting date from date picker is : "+new SimpleDateFormat("yyyy-MMMMM-dd HH:mm a").format(selectDate.getTime()));

		String month = new SimpleDateFormat("MMMM").format(selectDate.getTime());
		String year= new SimpleDateFormat("YYYY").format(selectDate.getTime());
		String day= new SimpleDateFormat("d").format(selectDate.getTime());
		String startTime = new SimpleDateFormat("hh:mm").format(selectDate.getTime());
		String startTimeAMPM = new SimpleDateFormat("a").format(selectDate.getTime());

		
		// add one hour to the current time 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(selectDate);
		calendar.add(Calendar.HOUR, durationInHr);
		Date toDate = calendar.getTime();

		
		// add one Hr to the date 
		
		String EndTime  = new SimpleDateFormat("hh:mm").format(toDate.getTime());
		String endTimeAMPM = new SimpleDateFormat("a").format(toDate.getTime()); 


		// Match the Month and Year , it can select a date from next one year 		
		for (int icount= 0;icount< 12;) {
			if ((datePickerMonth.getText().equals(month)) && (datePickerYear.getText().equals(year))) {
				// Select the date 
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table[@class='ui-datepicker-calendar']//a[text()='"+day+"']")).click();;
				break;
			}

			else {
				datePickerNext.click();
				WaitForElementToBeVisible(datePickerMonth, 5);
			}

			icount++;
		}

		
        // using js executer because webdriver send keys is not working 
		
		log ("selecting end time" + startTime );
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('webinarTimesForm.dateTimes_0.startTime').value = '"+startTime+"';");
		selectStartAMPM(startTimeAMPM);
		
		selectEndAMPM (endTimeAMPM);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		log ("selecting end time" + EndTime );
		endTimeInput.click();
		jse1.executeScript("document.getElementById('webinarTimesForm.dateTimes_0.endTime').value = '"+EndTime+"';");

	}

	/**
	 * This will select a date after number of days that are provided , it can only select date within next one year
	 * @param NoOfdaye
	 * @throws Exception 
	 * @return Date ( For future verification purpose)
	 */
	private void selectDateAfterCurrentDate (Date scheduleDate,  int durationInHr) throws Exception {

		//log ("Selecting date after "+NoOfdays+" days From current date");
		dateInput.click();
		
		//Date expDate = addDaysTocurrentDate(NoOfdays);
		this.ChoosedateAndTime(scheduleDate, durationInHr);
		
		}

	/**
	 * This will select AM/PM from the list start AMPM list box
	 * @param inputAMPM
	 */
	public void selectStartAMPM (String inputAMPM) throws Exception { 

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('webinarTimesForm_dateTimes_0_startAmPm').style.display='inline';");
		Select select = new Select(driver.findElement(By.id("webinarTimesForm_dateTimes_0_startAmPm")));
		select.selectByVisibleText(inputAMPM);
		executor.executeScript("document.getElementById('webinarTimesForm_dateTimes_0_startAmPm').style.display='none';");
	}
	
	

	/**
	 * This will select AM/PM from the list end AMPM list box
	 * @param inputAMPM
	 */
	public void selectEndAMPM (String inputAMPM) throws Exception {

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('webinarTimesForm_dateTimes_0_endAmPm').style.display='inline';");
		Select select = new Select(driver.findElement(By.id("webinarTimesForm_dateTimes_0_endAmPm")));
		select.selectByVisibleText(inputAMPM);
		executor.executeScript("document.getElementById('webinarTimesForm_dateTimes_0_endAmPm').style.display='none';");

	}


	/**
	 * This will select the time zone from the select time zone 
	 * @param inputTimeZone
	 * @throws Exception
	 */
	public void selectTimeZone (String inputTimeZone) throws Exception{


		String idTimeZone = "webinarTimesForm_timeZone";

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('"+idTimeZone+"').style.display='inline';");
		Select select = new Select(driver.findElement(By.id(idTimeZone)));
		select.selectByVisibleText(inputTimeZone);
		executor.executeScript("document.getElementById('"+idTimeZone+"').style.display='none';");

	}


	/**
	 * This will select the language from select language 
	 * @param inputLanguage
	 * @throws Exception
	 */
	public void selectlanguage (String inputLanguage) throws Exception {

		String idlanguage = "language";

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById('"+idlanguage+"').style.display='inline';");
		Select select = new Select(driver.findElement(By.id(idlanguage)));
		select.selectByVisibleText(inputLanguage);
		executor.executeScript("document.getElementById('"+idlanguage+"').style.display='none';");

	}
	
	

	/**
	 * This verifys the current page title 
	 * @param pageTitle
	 */
	public void verifyPageTitle (String pageTitle ) {
		try {
		WaitforPageLoad (pageTitle, 20);}
		catch (Exception e) {
			verifyEquals(pageTitle, driver.getTitle());}
			
		
	}


	/**
	 * This will create a webinar on the provided Date ( Date will have the time)  with provided webinar title, This will select the default values in other fields 
	 * , Occurance, TimeZone, language, and default time of one Hr
	 * @param [int] noOfDays
	 * @param [String] WebinarTitle
	 * @param [int] durationinHr
	 * @throws [Exception 
	 */
	public void createWebinar (Date scheduleDate, String WebinarTitle,  int durationInHr ) throws Exception {

		newWebinarName.sendKeys(WebinarTitle);
		newWebinarDescription.sendKeys(WebinarTitle);
		this.selectOccurance ("One session");
		this.selectDateAfterCurrentDate (scheduleDate,  durationInHr);


		this.selectTimeZone("(GMT-07:00) Pacific Time (US and Canada);Tijuana");
		//Thread.sleep(10000);
		this.selectlanguage("English");
		//Thread.sleep(10000);
		scheduleBtn.click();

	}

	/**
	 * This will cancel all the webinars, as part of cleaning up process , so that verification of new webinar can be clean, 
	 * Pre condition : this method assumes that we are on my webinar page  
	 */
	public void cancelAlltheWebinars () {
		
		
		
		List <WebElement> ListWebelement = new ArrayList<WebElement>();
		
		ListWebelement= driver.findElements(By.cssSelector("#upcomingWebinar .openWebinar"));
		
		
		for (int i =0 ;i<ListWebelement.size();i++){
			
			ListWebelement= driver.findElements(By.cssSelector("#upcomingWebinar .openWebinar"));
			ListWebelement.get(0).findElement(By.xpath(".//li[@class='actionBtn']//a[text()='Edit']")).click();
			WaitForElementToBeClickable(cancelWebinarLink, 10);
			cancelWebinarLink.click();
			WaitForElementToBeClickable(confirmDeleteBtn, 5);
			confirmDeleteBtn.click();
			
			
		}
		
		
	}
	
	
	public String formatDateforVerification (Date expDate, int duration) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat( "EEE, d MMM yyyy K:mm a, z" );
		
		
		// add one hour to the current time 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(expDate);
		
		String fromdate = new SimpleDateFormat("EEE, MMM d, yyyy K:mm a - ").format(calendar.getTime());
		
		calendar.add(Calendar.HOUR, duration);
		Date toDate = calendar.getTime();
		String EndTime  = new SimpleDateFormat("K:mm a z").format(toDate.getTime());
		
		log (fromdate+EndTime);
		
		return fromdate+EndTime;
		
	}
	
}
