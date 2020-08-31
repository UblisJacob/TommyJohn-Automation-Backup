package com.tommyjohn.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import atu.testrecorder.ATUTestRecorder;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;


public class CustomUtilities {

	public WebDriver driver;
	public static String baseUrl = "https://tommyjohn.com/";
	public static BufferedReader reader;
	public static Properties properties;
	public static final String propertiesFilePath = "src/main/resources/configuration.properties";
//	public static ATUTestRecorder recorder;

	/**
	 * Method to set up the drivers.
	 * 
	 * @throws Exception
	 */

	// @BeforeSuite
	@SuppressWarnings({ })
	public WebDriver launchtj() throws Exception {
		BrowserMobProxy proxy = new BrowserMobProxyServer();
		proxy.setTrustAllServers(true);
		proxy.start(0);
		
		
//		 List<String> allowUrlPatterns = new ArrayList<String>();
//		    allowUrlPatterns.add("https://events.bouncex.net");
		proxy.blacklistRequests("https?://.*\\.bouncex\\.net/.*", 410);
		proxy.blacklistRequests("https?://.*\\.bounceexchange\\.com/.*", 410);
		
		Proxy selproxy = new Proxy();
		String proxyStr = "localhost:" + proxy.getPort();
		
		
		selproxy.setHttpProxy(proxyStr);
		selproxy.setSslProxy(proxyStr);
	

		
		// load properties file
		loadPropertiesFile();

		// System.setProperty("webdriver.chrome.driver"
		// ,"src\\main\\resources\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver" ,"/tmp/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--window-size=1280,800");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--aggressive-cache-discard");
		chromeOptions.addArguments("--disable-cache");
		chromeOptions.addArguments("--disable-application-cache");
		chromeOptions.addArguments("--ignore-certificate-errors");
		chromeOptions.addArguments("--no-default-browser-check");
		chromeOptions.addArguments("--disable-popup-blocking");
//		chromeOptions.addArguments("--proxy-bypass-list=*");

		
		chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		chromeOptions.setCapability(CapabilityType.PROXY, selproxy);
//		chromeOptions.addArguments("--proxy-server={0}".format(proxyStr,proxyStr));
//		chromeOptions.addArguments("--proxy-auto-detect");
		//chromeOptions.addArguments("--proxy-bypass-list=*");
		
		
	//	chromeOptions.addArguments("--proxy-server={0}")

		// chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR ,
		// UnexpectedAlertBehaviour.DISMISS);
		// chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
		// chromeOptions.addArguments("--auto-open-devtools-for-tabs");

		// chromeOptions.setExperimentalOption("excludeSwitches", new
		// String[]{"enable-automation"});



		// chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new ChromeDriver(chromeOptions);
		
	
		
//		recorder = new ATUTestRecorder(properties.getProperty("executionvideospath"), "Recording1" , false) ;
//		
//		
//		recorder.start();
		
		driver.get(baseUrl);

		// driver.navigate().refresh();
		driver.manage().window().maximize();

		
		
//		  WebDriverWait wait = new WebDriverWait(driver, 30); try {
//		  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(
//		  "bcx_local_storage_frame")));
//		  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
//		  ".bx-close-xsvg")));
//		  driver.findElement(By.cssSelector(".bx-close-xsvg")).click();
//		  driver.switchTo().defaultContent(); } catch (ElementNotVisibleException e) {
//		  System.out.println("Overlay not displayed"); } finally {
//		   return driver;
//		  driver.get(baseUrl);  }
		 
		 return driver;
		
	}

	// @AfterSuite
	public void teardown() {
		if (driver != null)
			driver.quit();
	}

	// method to load properties file
	public static void loadPropertiesFile() throws Exception {
		reader = new BufferedReader(new FileReader(propertiesFilePath));
		properties = new Properties();
		properties.load(reader);
		reader.close();
		if (properties.isEmpty()) {
			Reporter.log("Properties file not loaded or empty..!");
			System.exit(0);
		}

	}

}
