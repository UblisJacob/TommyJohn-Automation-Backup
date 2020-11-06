package com.tommyjohn.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.net.NetworkUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;


public class ChromeExtensionUtility {

	public static WebDriver driver;
	public EdgeDriver edgedriver;
	public static String baseUrl = "https://tommyjohn.com";
	public static BufferedReader reader;
	public static Properties properties;
	public static final String propertiesFilePath = "src/main/resources/configuration.properties";

	@SuppressWarnings("deprecation")
	public static WebDriver launchchromewithextension() throws Exception
	{

		loadPropertiesFile();
		
		//browserproxy
//		final	BrowserMobProxy proxy = new BrowserMobProxyServer();
//		proxy.start();
//		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT,CaptureType.RESPONSE_CONTENT);
//		proxy.newHar("heapanalytics.com");
//		
//		Proxy selproxy = ClientUtil.createSeleniumProxy(proxy);
//		
//		String ipAddress = new NetworkUtils().getIp4NonLoopbackAddressOfThisMachine().getHostAddress();
//		String proxyStr = ipAddress + ":" + proxy.getPort();
//		
//		selproxy.setHttpProxy(proxyStr);
//		selproxy.setSslProxy(proxyStr);
		//
		
		System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
		ChromeOptions chromeOptions = new ChromeOptions();
		//chromeOptions.addArguments("load-extension=src/main/resources/heapdebugger.crx");
		chromeOptions.addExtensions(new File("src/main/resources/heapdebugger.crx"));
		chromeOptions.addArguments("--headless");
		chromeOptions.setExperimentalOption("w3c", false);

		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--host-resolver-rules=MAP events.bouncex.net 127.0.0.1");
		chromeOptions.addArguments("--host-resolver-rules=MAP assets.bounceexchange.com 127.0.0.1");
		chromeOptions.addArguments("--host-resolver-rules=MAP api.bounceexchange.com 127.0.0.1");
		chromeOptions.addArguments("--host-resolver-rules=MAP tag.bounceexchange.com 127.0.0.1");
//bwsrproxy
//		chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//		chromeOptions.setCapability(CapabilityType.PROXY, selproxy);
		//	chromeOptions.addArguments("auto-open-devtools-for-tabs");

		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		logPrefs.enable(LogType.CLIENT, Level.ALL);
		logPrefs.enable(LogType.DRIVER, Level.ALL);

		chromeOptions.setCapability("goog:loggingPrefs", logPrefs);

		driver = new ChromeDriver(chromeOptions);

		System.out.println("Opening extension");
		driver.get(baseUrl);
		Thread.sleep(15000);
//		final Har httpMessages = proxy.getHar();
//	    for(HarEntry httpMessage : httpMessages.getLog().getEntries()){
//
//	        // check no errors on the XHR requests
//	        if(httpMessage.getRequest().getUrl().contains("heap")) {
//	        	System.out.println(httpMessage.getResponse().getHeaders());
////	            Assertions.assertEquals(200, httpMessage.getResponse().getStatus());
//	        }
//
//	    }
		return driver;
		

	}

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
