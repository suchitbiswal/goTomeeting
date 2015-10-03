package com.goToMeeting.citrix.core;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


public class coretestNGLister implements ITestListener, IInvokedMethodListener{

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		String sTestMethodName = iTestResult.getMethod().getMethodName();
		String sTestSuiteName = iTestResult.getTestClass().getRealClass().getSimpleName();
		baseAssertVerify.log("<<< END: " + iTestResult.getMethod().getMethodName()	+ " FAILED >>>");
		Reporter.log (""+baseAssertVerify.getVerificationFailures ());
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("Suchit testcase start");
		String sTestMethodName = iTestResult.getMethod().getMethodName();
		String sTestSuiteName = iTestResult.getTestClass().getRealClass().getSimpleName();

		baseAssertVerify.log("---------------------Start------------------------");
		baseAssertVerify.log("<<< START: " + sTestSuiteName + "." + sTestMethodName 	+ " >>> ");
		
		
	}

	public void onTestSuccess(ITestResult iTestResult) {
		// TODO Auto-generated method stub		
		
		String sTestMethodName = iTestResult.getMethod().getMethodName();
		String sTestSuiteName = iTestResult.getTestClass().getRealClass().getSimpleName();
		baseAssertVerify.log("<<< END: " + sTestSuiteName + "." + sTestMethodName + " PASSED >>>");
		
	}

}
