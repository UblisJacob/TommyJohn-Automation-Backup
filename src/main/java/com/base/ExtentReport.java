package com.base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReport{
	
	public WebDriver driver;

	public static ExtentTest extentTest;
	public static ExtentReports extentReport;
	public static ExtentSparkReporter sparkReporter_AllTests;
//	public static String browserName = context.getCurrentXmlTest().getParameter("browser");
	

	// @BeforeSuite

	public static void initializeExtentReports() {

		extentReport = new ExtentReports();

		File erfile = new File( "Report");

		sparkReporter_AllTests = new ExtentSparkReporter(erfile);

		sparkReporter_AllTests.config().setTheme(Theme.DARK);

	//	sparkReporter_AllTests.config().setReportName("Regression Test Report: ");

		extentReport.attachReporter(sparkReporter_AllTests);

			// return extentReport;
	}


	// @AfterSuite
	public static void generateExtentReports() {
		extentReport.flush();
//		To automatically open reports after execution is complete
		try {
			Desktop.getDesktop().browse(
					new File("Report/Index.html")
							.toURI());
		} catch (IOException e) {
			System.out.println("Reports file not found");
			e.printStackTrace();
		}

	}

}
