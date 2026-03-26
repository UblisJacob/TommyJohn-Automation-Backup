package com.tommyjohn.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v140.emulation.Emulation;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestContext;
import org.testng.Reporter;

public class CustomUtilities {

    public EdgeDriver edgedriver;
    public static String baseUrl = "https://www.tommyjohn.com/";
    public static BufferedReader reader;
    public static Properties properties;
    public static final String propertiesFilePath = "src/main/resources/configuration.properties";
    public static String Suitname = null;

    /**
     * Launch browser and set ThreadLocal driver
     * @param browser Chrome, Firefox, Edge, Safari
     * @param headless true to run Chrome in headless mode, false otherwise
     */
    public WebDriver launchtj(String browser, boolean headless) throws Exception {
        loadPropertiesFile();

        WebDriver localDriver = null;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--use-fake-ui-for-media-stream");
            chromeOptions.addArguments("--use-fake-device-for-media-stream");
            chromeOptions.addArguments("--unsafely-treat-insecure-origin-as-secure=http://192.168.5.184");
            chromeOptions.addArguments("--disable-web-security");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--force-device-scale-factor=1");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
            chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
            chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--aggressive-cache-discard");
            chromeOptions.addArguments("--disable-cache");
            chromeOptions.addArguments("--disable-application-cache");
            chromeOptions.addArguments("--no-default-browser-check");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            chromeOptions.addArguments("--host-resolver-rules=MAP cdn.attn.tv 127.0.0.1");
            chromeOptions.addArguments("--host-resolver-rules=MAP tommyjohn.attn.tv 127.0.0.1");
            chromeOptions.addArguments("--host-resolver-rules=MAP creatives.attn.tv 127.0.0.1");

            // apply headless if requested
            if (headless) chromeOptions.addArguments("--headless=new");

            localDriver = new ChromeDriver(chromeOptions);

            try {
                DevTools devTools = ((ChromeDriver) localDriver).getDevTools();
                devTools.createSession();
                devTools.send(Emulation.setDeviceMetricsOverride(
                        1920, 1080, 1.0, false,
                        Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty()
                ));
            } catch (Exception e) {
                System.out.println("DevTools session/setup failed: " + e.getMessage());
            }

            localDriver.get(baseUrl);
            localDriver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1280,800");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--aggressive-cache-discard");
            options.addArguments("--disable-cache");
            options.addArguments("--disable-application-cache");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--disable-popup-blocking");
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

            localDriver = new FirefoxDriver(options);
            localDriver.get(baseUrl);
            localDriver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            edgedriver = new EdgeDriver(options);
            edgedriver.get(baseUrl);
            edgedriver.manage().window().maximize();
            localDriver = edgedriver;

        } else if (browser.equalsIgnoreCase("safari")) {
            System.setProperty("webdriver.edge.driver", properties.getProperty("safariPath"));
            SafariOptions options = new SafariOptions();
            options.setCapability("--headless", true);
            options.setCapability("--window-size=1280,800", true);
            options.setCapability("--disable-gpu", true);
            options.setCapability("--no-sandbox", true);
            options.setCapability("--disable-dev-shm-usage", true);
            options.setCapability("--aggressive-cache-discard", true);
            options.setCapability("--disable-cache", true);
            options.setCapability("--disable-application-cache", true);
            options.setCapability("--ignore-certificate-errors", true);
            options.setCapability("--no-default-browser-check", true);
            options.setCapability("--disable-popup-blocking", true);
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

            localDriver = new SafariDriver(options);
            localDriver.get(baseUrl);
            localDriver.manage().window().maximize();
        }

        if (localDriver != null) {
            DriverManager.setDriver(localDriver);
        }

        return localDriver;
    }

    public void teardown() {
        WebDriver d = DriverManager.getDriver();
        if (d != null) {
            try {
                d.quit();
            } catch (Exception e) {
                System.out.println("Error during driver.quit(): " + e.getMessage());
            } finally {
                DriverManager.unload();
            }
        }
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

    public static String takeScreenShot(String name, WebDriver driverParam) {
        WebDriver d = driverParam != null ? driverParam : DriverManager.getDriver();
        if (d == null) {
            System.out.println("❌ takeScreenShot skipped: driver is null");
            return "";
        }

        String rootDir = System.getProperty("user.dir");
        String screenShotsFolder = rootDir + "/test-output/Screenshots/";
        File folder = new File(screenShotsFolder);
        if (!folder.exists()) folder.mkdirs();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MMM_yyyy_HH_mm_ss");
        String dateAndTime = dtf.format(LocalDateTime.now());
        String filePath = screenShotsFolder + name + "_" + dateAndTime + ".jpg";

        try {
            File file = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(filePath));
            System.out.println("✔ Screenshot is taken: " + filePath);
            return filePath;
        } catch (Exception e) {
            System.out.println("⚠ Failed to save screenshot: " + e.getMessage());
            return "";
        }
    }

    public static String takessiffailed(String mname) {
        return takessiffailed(mname, DriverManager.getDriver());
    }

    public static String takessiffailed(String mname, WebDriver driverParam) {
        return takeScreenShot(mname != null ? mname : "screenshot", driverParam);
    }

    public static String screenshot(WebDriver driverParam) {
        WebDriver d = driverParam != null ? driverParam : DriverManager.getDriver();
        if (d == null) {
            System.out.println("❌ screenshot skipped: driver is null");
            return "";
        }
        try {
            return ((TakesScreenshot) d).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.out.println("⚠ Failed to capture screenshot as base64: " + e.getMessage());
            return "";
        }
    }

    public static String screenshot() {
        return screenshot(DriverManager.getDriver());
    }

    public static void GetSuiteName(ITestContext context) {
        Suitname = context.getCurrentXmlTest().getSuite().getName();
        System.out.println("Suite: " + Suitname);
    }

    public static class DriverManager {
        private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

        public static void setDriver(WebDriver driverInstance) { driver.set(driverInstance); }
        public static WebDriver getDriver() { return driver.get(); }
        public static void unload() { driver.remove(); }
    }
}
