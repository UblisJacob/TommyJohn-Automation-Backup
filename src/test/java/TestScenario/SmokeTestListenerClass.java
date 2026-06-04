package TestScenario;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.base.ExtentReport;
import com.base.GDrivePOST;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.util.Set;

import static com.base.Slack.sendSlackNotificationSmokeTest;

public class SmokeTestListenerClass extends ExtentReport
        implements ITestListener, IExecutionListener {

    WebDriver driver;
    static int count = 1;
    static boolean shouldPrint = true;
    static String browserName = null;

    // 🔧 CHANGE: default safe value
    public static String reportLink = "Report upload failed";

    public String SuiteName = null;

    public int Fpassed = 0;
    public int Ffailed = 0;
    public int Fskipped = 0;

    // ================= EXECUTION START =================
    @Override
    public void onExecutionStart() {
        initializeExtentReports();
        System.out.println("Smoke Tests started executing");
    }

    // ================= SUITE START =================
    @Override
    public void onStart(ITestContext context) {

        String SmokeTestSuite = "Smoke Test Suite";
        SuiteName = context.getSuite().getName();
        browserName = context.getCurrentXmlTest().getParameter("browser");

        System.out.println("SuiteName: " + SuiteName);
        System.out.println("Browser: " + browserName);

        if (SuiteName.equals(SmokeTestSuite)) {
            sparkReporter_AllTests.config()
                    .setReportName("Smoke Test Report");
        } else {
            sparkReporter_AllTests.config()
                    .setReportName("Regression Test Report");
        }
    }

    // ================= TEST START =================
    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getName();

        if (shouldPrint) {
            extentTest = extentReport.createTest(testName);
            extentTest.info("Executing " + testName + " Attempt: " + count);
        } else {
            extentTest.info("Re-Executing " + testName + " Attempt: " + count);
        }
    }

    // ================= SUCCESS =================
    @Override
    public void onTestSuccess(ITestResult result) {

        count=1;
        shouldPrint=true;
        String testName = result.getName();
        extentTest.pass(testName + " got executed successfully");
        extentTest.log(Status.PASS, "Test Passed");
        System.out.println(testName + " got executed successfully");

    }


    // ================= FAILURE =================
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


    // ================= SKIPPED =================
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName + " got failed");
    }



    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    // ================= EXECUTION FINISH =================
    @Override
    public void onExecutionFinish() {

        generateExtentReports();

        try {
            // 🔧 CHANGE: Service Account upload
            reportLink = GDrivePOST.UploadSmokeReport();
            System.out.println("Smoke Report uploaded: " + reportLink);

        } catch (Exception e) {
            System.err.println("Smoke report upload failed");
            e.printStackTrace();
        }

        try {
            // 🔧 CHANGE: Slack only if link is valid
            if (!reportLink.contains("failed")) {
                sendSlackNotificationSmokeTest(
                        String.valueOf(Fpassed),
                        String.valueOf(Ffailed),
                        String.valueOf(Fskipped),
                        reportLink
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= SUITE FINISH =================
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