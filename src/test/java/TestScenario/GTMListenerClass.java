package TestScenario;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.base.ExtentReport;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.util.Set;

import static com.base.Slack.sendSlackNotificationGTM;

public class GTMListenerClass extends ExtentReport
        implements ITestListener, IExecutionListener {

    WebDriver driver;
    static String browserName = null;

    // ✅ SAFE DEFAULT
    public static String reportLink = "GTM report upload failed";

    public String SuiteName = null;

    public int Fpassed = 0;
    public int Ffailed = 0;
    public int Fskipped = 0;

    private static ThreadLocal<String> currentTestName = new ThreadLocal<>();
    private static ThreadLocal<Integer> retryCount =
            ThreadLocal.withInitial(() -> 1);

    // ================= EXECUTION START =================
    // ================= EXECUTION START =================
    @Override
    public void onExecutionStart() {
        initializeExtentReports();
        System.out.println("GTM Tests started executing");
    }

    // ================= SUITE START =================
    @Override
    public void onStart(ITestContext context) {

        String GTMSuiteName = "GTM Test Suite";
        SuiteName = context.getSuite().getName();
        browserName = context.getCurrentXmlTest().getParameter("browser");

        System.out.println("SuiteName: " + SuiteName);
        System.out.println("Browser: " + browserName);

        if (SuiteName.equals(GTMSuiteName)) {
            sparkReporter_AllTests.config()
                    .setReportName("GTM Test Report");
        } else {
            sparkReporter_AllTests.config()
                    .setReportName("Regression Test Report");
        }
    }

    // ================= TEST START =================
    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

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

    // ================= SUCCESS =================
    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.log(Status.PASS,
                result.getName() + " executed successfully");

        retryCount.set(1);
        currentTestName.remove();
        extentTest = null;
    }

    // ================= FAILURE =================
    @Override
    public void onTestFailure(ITestResult result) {

        try {

            String screenshotBase64 = CustomUtilities.screenshot();

            if (screenshotBase64 != null && !screenshotBase64.trim().isEmpty()) {

                extentTest.info(
                        "Screenshot",
                        MediaEntityBuilder
                                .createScreenCaptureFromBase64String(screenshotBase64)
                                .build()
                );
            } else {
                extentTest.warning("Screenshot could not be captured.");
            }

        } catch (Exception e) {
            extentTest.warning("Screenshot attachment failed: " + e.getMessage());
        }

        extentTest.fail(result.getThrowable());

        retryCount.set(retryCount.get() + 1);
    }

    // ================= SKIPPED =================
    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.skip(result.getThrowable());

        retryCount.set(1);
        currentTestName.remove();
        extentTest = null;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}


    // ================= EXECUTION FINISH =================
    @Override
    public void onExecutionFinish() {

        try {
            generateExtentReports();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // ✅ Service Account upload
            reportLink = com.base.GDrivePOST.UploadGTMReport();
            System.out.println("GTM Report uploaded: " + reportLink);

        } catch (Exception e) {
            System.err.println("GTM report upload failed");
            e.printStackTrace();
        }

        try {
            // ✅ Slack ONLY if upload succeeded
            if (!reportLink.contains("failed")) {
                sendSlackNotificationGTM(
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