package TestScenario;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.base.ExtentReport;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Set;

import static com.base.Slack.sendSlackNotificationADA;
import static com.base.Slack.sendSlackNotificationReg;

public class ListenerClass extends ExtentReport implements ITestListener, IExecutionListener {

	WebDriver driver;
	static int count = 1;
	static boolean shouldPrint = true;
	static String browserName = null;

	public String reportLink = null;

	public String SuiteName = null;

	public int Fpassed = 0;
	public int Ffailed = 0;
	public int Fskipped = 0;



	@Override

	public void onExecutionStart() {
		initializeExtentReports();
		System.out.println("Tests started executing");

	}


	@Override
	public void onStart(ITestContext context) {
		String ADACheckSuiteName = "ADACheck Test Suite";
		SuiteName = context.getSuite().getName().toString();
		System.out.println("SuiteName: " + SuiteName);
		browserName = context.getCurrentXmlTest().getParameter("browser");
		System.out.println("Test execution started using Browser: " + browserName);
		if(SuiteName.equals(ADACheckSuiteName))
		{
			sparkReporter_AllTests.config().setReportName("ADACheck Test Report");
		}
		else {

			sparkReporter_AllTests.config().setReportName("Regression Test Report");
		}

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

		String screenShotPath = CustomUtilities.screenshot();

		if (count < Retry.maxRetryCount) {
			shouldPrint = false;


		} else {
			shouldPrint = true;

		}

		String testName = result.getName();

		String fileName = result.getMethod().getMethodName().replaceAll(" ", "_");

		//       String screenShotPath = CustomUtilities.screenshot();
		//extentTest.addScreenCaptureFromPath(screenShotPath);

		extentTest.info("Screenshot",
				MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotPath).build());
		extentTest.info(result.getThrowable());
		extentTest.fail(testName + " got failed");

		if (count == Retry.maxRetryCount) {
			//         extentTest.addScreenCaptureFromPath(screenShotPath);
			count = 1;
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
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{

	}

	@Override
	public void onExecutionFinish() {

		generateExtentReports();

		try {
			reportLink = com.base.GDrivePOST.UploadReport();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
		try {
//		sendSlackNotificationReg(String.valueOf(Fpassed),String.valueOf(Ffailed),String.valueOf(Fskipped),reportLink);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void onFinish(ITestContext context) {

		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
//			System.out.println(context.getFailedTests().size());
//			System.out.println(context.getPassedTests().size());
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
			if (context.getSkippedTests().getResults(method).size() > 0) {
				skippedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					skippedTests.remove(temp);
				}
			}
		}

		Fpassed = Fpassed + (context.getPassedTests().size());
		Ffailed = Ffailed + (context.getFailedTests().size());
		Fskipped = Fskipped + (context.getSkippedTests().size());

	}
}
