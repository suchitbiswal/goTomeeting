package com.goToMeeting.citrix.core;

import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;


public class coretestNGLister implements ITestListener, IInvokedMethodListener{


	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		// TODO Auto-generated method stub
		
		Reporter.setCurrentTestResult(result);
		if (method.isTestMethod()) {
			List<Throwable> verificationFailures = baseAssertVerify.getVerificationFailures();
			// if there are verification failures...
			if (verificationFailures.size() > 0) {
				// set the test to failed
				result.setStatus(ITestResult.FAILURE);

				// if there is an assertion failure add it to
				// verificationFailures
				if (result.getThrowable() != null) {
					verificationFailures.add(result.getThrowable());
				}

				int size = verificationFailures.size();
				// if there's only one failure just set that
				if (size == 1) {
					result.setThrowable(verificationFailures.get(0));
				} else {
					// create a failure message with all failures and stack
					// traces (except last failure)
					StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):\n\n");
					for (int i = 0; i < size ; i++) {
						failureMessage.append("Failure ").append(i + 1).append(" of ").append(size).append(":\n");
						Throwable t = verificationFailures.get(i);
						String errorMessage = null;

						errorMessage = Utils.stackTrace(t, false)[1];

						failureMessage.append(errorMessage).append("\n\n");
					}


					Throwable merged = new Throwable(failureMessage.toString());
					result.setThrowable(merged);
					}
				}
			}
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
		//System.out.println("Suchit testcase start");
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
