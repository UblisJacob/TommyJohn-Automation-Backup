package TestScenario;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.base.ExtentReport;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import static com.base.Slack.*;

public class ImageListenerClass extends ExtentReport implements ITestListener, IExecutionListener {

    WebDriver driver;
    static int count = 1;
    static boolean shouldPrint = true;
    static String browserName = null;

    public String reportLink = null;

    public String SuiteName = null;

//    public int Fpassed = 0;
//    public int Ffailed = 0;
//    public int Fskipped = 0;

    static Set<String> passedTests = new HashSet<>();
    static Set<String> failedTests = new HashSet<>();
    static Set<String> skippedTests = new HashSet<>();

    @Override

    public void onExecutionStart() {
        initializeExtentReports();
        System.out.println("Tests started executing");

    }


    @Override
    public void onStart(ITestContext context) {
        String ImageCheckSuiteName = "Image Test Suite";
        SuiteName = context.getSuite().getName().toString();
        System.out.println("SuiteName: " + SuiteName);
        browserName = context.getCurrentXmlTest().getParameter("browser");
        System.out.println("Test execution started using Browser: " + browserName);
        if(SuiteName.equals(ImageCheckSuiteName))
        {
            sparkReporter_AllTests.config().setReportName("Images Missing Alt Text");
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

        String testName = result.getMethod().getMethodName();

        // ✅ FINAL RESULT → PASS
        failedTests.remove(testName);
        skippedTests.remove(testName);
        passedTests.add(testName);

        count = 1;
        shouldPrint = true;

        extentTest.pass(testName + " executed successfully");
        extentTest.log(Status.PASS, "Test Passed");

        System.out.println(testName + " executed successfully");
    }


    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        String screenShotPath = CustomUtilities.screenshot();

        extentTest.info("Screenshot",
                MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotPath).build());
        extentTest.info(result.getThrowable());
        extentTest.fail(testName + " failed");

        // Retry handling
        if (count < Retry.maxRetryCount) {
            shouldPrint = false;
            count++;
        } else {
            // ❗ Final failure only
            shouldPrint = true;
            count = 1;
            failedTests.add(testName);
            passedTests.remove(testName);
        }
    }



    @Override
    public void onTestSkipped(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        skippedTests.add(testName);
        passedTests.remove(testName);
        failedTests.remove(testName);

        extentTest.log(Status.SKIP, testName + " skipped");
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {

    }

    @Override
    public void onExecutionFinish() {

        generateExtentReports();

        int finalPassed  = passedTests.size();
        int finalFailed  = failedTests.size();
        int finalSkipped = skippedTests.size();

        Map<String, String> links;
        try {
            links = com.base.GDrivePOST.UploadImageReportAndCSV();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            sendSlackNotificationImageCSV(
                    String.valueOf(finalPassed),
                    String.valueOf(finalFailed),
                    String.valueOf(finalSkipped),
                    links.get("reportLink"),
                    links.get("homepageCsvLink"),
                    links.get("altCsvLink"),
                    passedTests,
                    failedTests,
                    skippedTests
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void onFinish(ITestContext context) {
        // ❌ DO NOTHING HERE
        // Avoid retry-inflated TestNG counts
    }

}


