package com.base;

import java.awt.Desktop;
import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

    public static ExtentTest extentTest;
    public static ExtentReports extentReport;
    public static ExtentSparkReporter sparkReporter_AllTests;

    public static void initializeExtentReports() {

        extentReport = new ExtentReports();

        File reportDir = new File("Report");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        sparkReporter_AllTests = new ExtentSparkReporter(reportDir);
        sparkReporter_AllTests.config().setTheme(Theme.DARK);

        extentReport.attachReporter(sparkReporter_AllTests);
    }

    public static void generateExtentReports() {

        if (extentReport != null) {
            extentReport.flush();
        }

        // Local execution only
        if (System.getenv("CI") == null) {
            try {
                File reportFile = new File("Report/index.html");
                if (reportFile.exists()) {
                    Desktop.getDesktop().browse(reportFile.toURI());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
