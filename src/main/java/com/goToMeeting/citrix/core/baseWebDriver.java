package com.goToMeeting.citrix.core;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class baseWebDriver  extends baseAssertVerify{
	protected WebDriver driver;	
	
	public static WebDriver getWebDriver() {		
		
		if (testConfig.browserType.equals("*firefox")) {
		DesiredCapabilities firefoxCap = new DesiredCapabilities();		
		return new FirefoxDriver(firefoxCap);	
		}
		
		else if (testConfig.browserType.equals("*chrome")){
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			return new ChromeDriver(capabilities);
		}
		
		else {
			HtmlUnitDriver driver = new HtmlUnitDriver(true);
			return driver;
		}
		
	}
	
	
	@BeforeClass (alwaysRun = true)
	public void setUp(ITestContext ctx) throws Exception {
	
		String chromeProp = "webdriver.chrome.driver";
		
		String osType = System.getProperty("os.name");
		
		log ("suchit is in setup");
		File targetChromedriver = null;
		if (osType.toLowerCase().contains("win")) {
			targetChromedriver = new File(testConfig.testRoot + File.separator + "target" + File.separator + "classes"+ File.separator + "drivers" + File.separator + "chrome" + File.separator + "chromedriver.exe");
		} else if (osType.toLowerCase().contains("mac")) {
			targetChromedriver = new File(testConfig.testRoot + File.separator + "target"+ File.separator + "classes"+ File.separator + "drivers" + File.separator + "chrome" + File.separator + "chromedriver");
		}
		
		if (targetChromedriver.exists()) {
			log ("suchit is in setup final");
			System.setProperty(chromeProp, targetChromedriver.getAbsolutePath());
			return;
			}	
		}
	
	
	
	
	@BeforeMethod (alwaysRun = true)
	public void testSetup() throws Exception {
		driver = this.getWebDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	
	@AfterMethod (alwaysRun = true) 
	public void postTestCase(ITestResult _result) {

		try{
			driver.quit();
		}

		catch (Exception e){ // - Suchit - Don nothing if the driver is closed before this 
		}




	}
		
	}
	

