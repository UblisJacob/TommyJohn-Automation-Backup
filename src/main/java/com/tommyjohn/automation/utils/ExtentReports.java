package com.tommyjohn.automation.utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReports {

	@BeforeSuite
	public void setUp() {
		
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent.html");
		
		ExtentReports extent = new ExtentReports();
	//	extent.attachReporter(htmlReporter);
        
	}

	@Test
	public void test1() {
		
	}
	
	@AfterSuite
	public void tearDown() {
		
	}
}
