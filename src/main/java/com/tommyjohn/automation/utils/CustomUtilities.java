package com.tommyjohn.automation.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class CustomUtilities {

	public static WebDriver driver;
	public static String baseUrl = "https://tommyjohn.com/";
	public static BufferedReader reader;
	public static Properties properties;
	public static final String propertiesFilePath = "src/main/resources/configuration.properties";

	/**
	 * Method to set up the drivers.
	 * 
	 * @throws Exception
	 */
	@BeforeSuite
	public static WebDriver launchtj() throws Exception 
	{
		// load properties file
		loadPropertiesFile();

	//	System.setProperty("webdriver.chrome.driver" ,"src\\main\\resources\\chromedriver.exe");
	//	System.setProperty("webdriver.chrome.driver" ,"/tmp/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver" ,properties.getProperty("chromeDriverPath"));
		ChromeOptions chromeOptions = new ChromeOptions();
		//chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("window-size=1366,768");
		driver= new ChromeDriver(chromeOptions);
		driver.get(baseUrl);
		driver.navigate().refresh();
		driver.manage().window().maximize();
		return driver;
	}
	@AfterSuite
	public static void teardown()
	{
		 if (driver != null)
			 driver.quit();
	}
	
	//method to load properties file
		public static void loadPropertiesFile()throws Exception 
		{
			reader = new BufferedReader(new FileReader(propertiesFilePath));
			properties = new Properties();
			properties.load(reader);
			reader.close();
			if(properties.isEmpty()) {
				Reporter.log("Properties file not loaded or empty..!");
				System.exit(0);
			}

		}

}
