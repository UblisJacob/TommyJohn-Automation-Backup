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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;


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
    	 System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
    	

    	 ChromeOptions chromeOptions = new ChromeOptions();
    	 chromeOptions.addExtensions(new File("src/main/resources/heapdebbuger.crx"));
//    	 chromeOptions.addArguments("--headless");
    	 chromeOptions.setExperimentalOption("w3c", false);
    	 chromeOptions.addArguments("--host-resolver-rules=MAP events.bouncex.net 127.0.0.1");
 		chromeOptions.addArguments("--host-resolver-rules=MAP assets.bounceexchange.com 127.0.0.1");
 		chromeOptions.addArguments("--host-resolver-rules=MAP api.bounceexchange.com 127.0.0.1");
 		chromeOptions.addArguments("--host-resolver-rules=MAP tag.bounceexchange.com 127.0.0.1");
 		
 	//	chromeOptions.addArguments("auto-open-devtools-for-tabs");
 		
 		LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.CLIENT, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);
        
        chromeOptions.setCapability("goog:loggingPrefs", logPrefs);

    	 driver = new ChromeDriver(chromeOptions);
        
        System.out.println("Opening extension");
        driver.get(baseUrl);
        driver.manage().window().maximize();
              
        Thread.sleep(15000);
        return driver;
        
         }
            //do something useful with the data
          



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
