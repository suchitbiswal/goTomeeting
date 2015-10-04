package com.goToMeeting.citrix.core;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;


public abstract class baseAssertVerify {

	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();
	
	private static void addVerificationFailure(Throwable e) {
		List verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(e);
	}
	
	public static List getVerificationFailures() {
		List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList() : verificationFailures;
	}
	
	public static void verifyEquals(String actual, String expected) throws RuntimeException  {
		try {
			Assert.assertEquals(actual, expected);
			log("Expected value: " + expected + " Actual value: " + actual + " - PASSED");
		} catch (Exception e) {
			log("Expected value: " + expected + " Actual value: " + actual + " - FAILED");
			throw new RuntimeException(e);
		}
	}

	
	public static void verifyEquals1(Object actual, Object expected) {
		try {
			Assert.assertEquals(actual, expected);
			log("Expected value: " + expected.toString() + " Actual value: " + actual.toString() + " - PASSED");
		} catch (Throwable e) {
			log (e.getMessage());
			log("Expected value: " + expected.toString() + " Actual value: " + actual.toString() + " - FAILED");
			addVerificationFailure(e);
		}
	}
	
	public static void log (String Logstment) {
		
		// Time stamp : 
		
		java.util.Date date = new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		Reporter.log ("<br>");
		Reporter.log (timestamp.toLocaleString() +":"+Logstment);
		System.out.println(timestamp.toLocaleString() +":"+Logstment);
	}
	
	
}
