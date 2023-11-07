package TestScenario;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.base.ExtentReport;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ListenerClass extends ExtentReport implements ITestListener, IExecutionListener {

	WebDriver driver;
	static int count = 1;
	static boolean shouldPrint = true;
	static String browserName = null;
	
	
	@Override

	public void onExecutionStart() {
		initializeExtentReports();
		System.out.println("Tests started executing");
		
	}
		
	
	@Override
	public void onStart(ITestContext context) {
		
		browserName = context.getCurrentXmlTest().getParameter("browser");
		System.out.println("Test execution started using Browser: " + browserName);
	
	}
	

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();

		if(shouldPrint)
		{
//			then create test
			extentTest = extentReport.createTest(testName);
			System.out.println("Executing " + testName + " Attempt Number: " + count);
			extentTest.info("Executing " + testName + " Attempt Number: " + count);
		}
			else
			{
			System.out.println("Re-Executing " + testName + " Attempt Number: " + count);
			extentTest.info("Re-Executing " + testName + " Attempt Number: " + count);
		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		count=1;
		shouldPrint=true;
		String testName = result.getName();
		extentTest.pass(testName + " got executed successfully");
		extentTest.log(Status.PASS, "Test Passed");
		System.out.println(testName + " got executed successfully");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		
count++;
		if (count <= Retry.maxRetryCount) {
			shouldPrint = false;
			
			 
		} else {
			shouldPrint = true;
			count = 1;
		}

		String testName = result.getName();

		String fileName = result.getMethod().getMethodName().replaceAll(" ", "_");

		String screenShotPath = CustomUtilities.takeScreenShot(fileName);
		//extentTest.addScreenCaptureFromPath(screenShotPath);
		
		extentTest.info("Screenshot",
				MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		extentTest.info(result.getThrowable());
		extentTest.fail(testName + " got failed");
		
		if (count == Retry.maxRetryCount) {
//			
			extentTest.addScreenCaptureFromPath(screenShotPath);
			
		}
		else
		{
			count++;
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " got failed");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
	
	@Override
	public void onExecutionFinish() {
		generateExtentReports();
	}


	@Override
	public void onFinish(ITestContext context) {

		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}
		for (ITestResult temp : skippedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getSkippedTests().getResults(method).size() > 1) {
				skippedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					skippedTests.remove(temp);
				}
			}
		}

	}

}
