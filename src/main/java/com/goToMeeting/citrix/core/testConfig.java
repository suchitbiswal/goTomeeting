package com.goToMeeting.citrix.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class testConfig {

	private static final String DEFAULT_PROP_FILENAME = "testConfig.properties";
	public static Properties properties = new Properties();
	public static String testConfigFile,testRoot,browserType, userName, password, url ;
	
	 
	static {
		try {
			testRoot = System.getProperty("testRoot");
			if (testRoot == null)
				testRoot = System.getProperty("user.dir"); 
			    testConfigFile = System.getProperty("testConfigFile");
			FileInputStream in = null;
			if (testConfigFile == null) {
				in = new FileInputStream(testRoot + File.separator + DEFAULT_PROP_FILENAME);
				} else {
					in = new FileInputStream(testRoot + File.separator + testConfigFile);
					}
			properties.load(in);
			browserType = properties.getProperty("selenium.browser");
			in.close();	

			// Get the Browser type 
			// get the user name 
			userName = properties.getProperty("goToMeeting.UserName");
			// get the password 
			password = properties.getProperty("goToMeeting.Password");
			
			// get the URL
			url= properties.getProperty("goToMeeting.URL");
			
		} catch (IOException e) {
			System.err.println("Failed to read The file : " + testConfigFile);
		}
	}


}
