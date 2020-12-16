//package com.tommyjohn.automation.utils;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.Properties;
//import java.util.logging.Level;
//
//import org.openqa.selenium.Capabilities;
//import org.openqa.selenium.WebDriver;
//
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.logging.LogType;
//import org.openqa.selenium.logging.LoggingPreferences;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.Reporter;
//
//public class FFExtensionUtility {
//
//	public static WebDriver driver;
//	public static String baseUrl = "https://tommyjohn.com/";
//	public static BufferedReader reader;
//	public static Properties properties;
//	public static final String propertiesFilePath = "src/main/resources/configuration.properties";
//	
//     @SuppressWarnings("deprecation")
//	public static WebDriver launchffwithextension() throws Exception
//     {
//        
//    	 loadPropertiesFile();
//    	 System.setProperty("webdriver.gecko.driver", properties.getProperty("ffDriverPath"));
//    	 
//    	 FirefoxProfile firefoxprofile = new FirefoxProfile();
//    	 //chromeOptions.addArguments("load-extension=src/main/resources/heapdebugger.crx");
//    	 firefoxprofile.addExtension(new File("src/main/resources/heapdebuggerff.zip"));
//    	 FirefoxOptions options = new FirefoxOptions();
////    	 options.addArguments("--headless");
//    	 options.setCapability("w3c", true);
//    	
//    	 options.addArguments("start-maximized");
//    	 options.addArguments("--no-sandbox");
//    	 options.addArguments("--disable-dev-shm-usage");
//    	 options.addArguments("--host-resolver-rules=MAP events.bouncex.net 127.0.0.1");
//    	 options.addArguments("--host-resolver-rules=MAP assets.bounceexchange.com 127.0.0.1");
//    	 options.addArguments("--host-resolver-rules=MAP api.bounceexchange.com 127.0.0.1");
//    	 options.addArguments("--host-resolver-rules=MAP tag.bounceexchange.com 127.0.0.1");
// 		
// 	//	chromeOptions.addArguments("auto-open-devtools-for-tabs");
// 		
// 		LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.BROWSER, Level.ALL);
//        logPrefs.enable(LogType.CLIENT, Level.ALL);
//        logPrefs.enable(LogType.DRIVER, Level.ALL);

//        capabilities.setCapability(FirefoxDriver.PROFILE, firefoxprofile);
//        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
//        
//        
//       
//        
//        options.setCapability("goog:loggingPrefs", logPrefs);
//
//        driver = new FirefoxDriver(capabilities);
//        
//        System.out.println("Opening extension");
//        driver.get(baseUrl);
//       
//              
//        Thread.sleep(15000);
//        return driver;
//        
//         }
//            
//          
//
//
//
//     public static void loadPropertiesFile() throws Exception {
// 		reader = new BufferedReader(new FileReader(propertiesFilePath));
// 		properties = new Properties();
// 		properties.load(reader);
// 		reader.close();
// 		if (properties.isEmpty()) {
// 			Reporter.log("Properties file not loaded or empty..!");
// 			System.exit(0);
// 		}
//     }
//}
//
