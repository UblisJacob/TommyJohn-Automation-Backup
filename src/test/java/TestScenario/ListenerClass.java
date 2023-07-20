package TestScenario;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.base.ExtentReport;
import com.tommyjohn.automation.utils.CustomUtilities;

 

public class ListenerClass extends ExtentReport implements ITestListener,IExecutionListener {

	WebDriver driver;
	
	@Override
	public void onExecutionStart() {
		initializeExtentReports();
		System.out.println("Tests execution started");
		
	}
	@Override
	public void onStart(ITestContext context) {
		
		
		System.out.println("Class 1 Tests started executing");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getName();
		 extentTest = extentReport.createTest(testName);
		 extentTest.log(Status.INFO,testName + " started executing");
		System.out.println(testName + " started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.PASS, testName + " got executed successfully");
		System.out.println(testName + " got executed successfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName().replaceAll(" ", "_");
		
		try {
			CustomUtilities.takessiffailed(testName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("screenshot is taken");
		String screenShotPath = "test-output/Screenshot_"+testName+".jpg";
		
	
		extentTest.addScreenCaptureFromPath(screenShotPath);
		//".png"
		 extentTest.log(Status.FAIL, "Screenshot", MediaEntityBuilder.createScreenCaptureFromPath("test-output/Screenshot_"+testName+".jpg").build());
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testName + " got failed");
		
	}
		


	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,testName + " got failed");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void onExecutionFinish() {
		
		generateExtentReports();
	}


		
}
