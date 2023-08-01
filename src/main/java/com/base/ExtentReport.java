package com.base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public WebDriver driver;

	public static ExtentTest extentTest;
	public static ExtentReports extentReport;
	public static ExtentSparkReporter sparkReporter_AllTests;

	// @BeforeSuite
	public static void initializeExtentReports(String browserName) {
		extentReport = new ExtentReports();
		File erfile = new File(System.getProperty("user.dir") + "/test-output/extentreport_" + browserName + ".html");
		sparkReporter_AllTests = new ExtentSparkReporter(erfile);

		sparkReporter_AllTests.config().setTheme(Theme.DARK);
		browserName = browserName.substring(0,1).toUpperCase()+browserName.substring(1);
		sparkReporter_AllTests.config().setReportName("Regression Test Report: " + browserName);

		extentReport.attachReporter(sparkReporter_AllTests);

		// return extentReport;
	}

	// @AfterSuite
	public static void generateExtentReports(String browserName) {
		extentReport.flush();
//		To automatically open reports after execution is complete
		try {
			Desktop.getDesktop().browse(
					new File(System.getProperty("user.dir") + "/test-output/extentreport_" + browserName + ".html")
							.toURI());
		} catch (IOException e) {
			System.out.println("Reports file not found");
			e.printStackTrace();
		}

	}

}
