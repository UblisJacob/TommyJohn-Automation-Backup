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
import static com.base.Slack.sendSlackNotificationGTM;

public class GTMListenerClass extends ExtentReport implements ITestListener, IExecutionListener {

    WebDriver driver;
//    static int count = 1;
//    static boolean shouldPrint = true;
    static String browserName = null;

    public String reportLink = null;

    public String SuiteName = null;

    public int Fpassed = 0;
    public int Ffailed = 0;
    public int Fskipped = 0;


    private static ThreadLocal<String> currentTestName = new ThreadLocal<>();
    private static ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(() -> 1);

    @Override

    public void onExecutionStart() {
        initializeExtentReports();
        System.out.println("Tests started executing");

    }


    @Override
    public void onStart(ITestContext context) {
        String GTMSuiteName = "GTM Test Suite";
        SuiteName = context.getSuite().getName().toString();
        System.out.println("SuiteName: " + SuiteName);
        browserName = context.getCurrentXmlTest().getParameter("browser");
        System.out.println("Test execution started using Browser: " + browserName);
        if(SuiteName.equals(GTMSuiteName))
        {
            sparkReporter_AllTests.config().setReportName("GTM Test Report");
        }
        else {

            sparkReporter_AllTests.config().setReportName("Regression Test Report");
        }

    }


    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        // 🔥 NEW TEST → RESET RETRY COUNT
        if (!testName.equals(currentTestName.get())) {
            retryCount.set(1);
            currentTestName.set(testName);
        }

        if (retryCount.get() == 1) {
            extentTest = extentReport.createTest(testName);
            extentTest.info("Executing " + testName + " | Attempt 1");
        } else {
            extentTest.info("Re-executing " + testName +
                    " | Attempt " + retryCount.get());
        }

        System.out.println("Executing " + testName +
                " | Attempt " + retryCount.get());
    }


    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.pass(result.getName() + " got executed successfully");

        // ✅ CLEANUP
        retryCount.set(1);
        currentTestName.remove();
        extentTest = null;
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String screenShotPath = CustomUtilities.screenshot();

        extentTest.info("Screenshot",
                MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotPath).build());
        extentTest.fail(result.getThrowable());

        retryCount.set(retryCount.get() + 1);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.skip(result.getThrowable());

        // ✅ CLEANUP
        retryCount.set(1);
        currentTestName.remove();
        extentTest = null;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onExecutionFinish() {

        generateExtentReports();

        try {
            reportLink = com.base.GDrivePOST.UploadGTMReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        try {
//            sendSlackNotificationGTM(String.valueOf(Fpassed),String.valueOf(Ffailed),String.valueOf(Fskipped),reportLink);
        } catch (Exception e)
        {
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