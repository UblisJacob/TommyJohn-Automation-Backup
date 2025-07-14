package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.Pages.AccountLoginPage;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;
import junit.framework.Assert;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.*;
import net.lightbody.bmp.proxy.CaptureType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.Network;
import org.openqa.selenium.devtools.v138.network.model.Request;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Reporter;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.stream.Collectors;


import static com.tommyjohn.automation.ADACheck.ADACheckMethods.softAssert;
import static com.tommyjohn.automation.PageLocators.CheckoutPageLocators.*;
import static com.tommyjohn.automation.PageLocators.CollectionPageLocator.*;
import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.*;
import com.tommyjohn.automation.PageLocators.CheckoutPageLocators;
import org.testng.asserts.SoftAssert;


public class ElevarComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();
    public JavascriptExecutor jse;
    Wait<WebDriver> wait;
    public ElevarComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    String searchText = "";



    public void ValidateElevarFacebookForCheckout() throws Exception {
        {
            // Setup
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            SoftAssert softAssert = new SoftAssert();
            ChromeDriver driver = new ChromeDriver(options);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            String originalWindow = driver.getWindowHandle();

            // Setup DevTools for original window
            DevTools devTools = driver.getDevTools();
            devTools.createSession();
            devTools.send(Network.enable(
                    Optional.empty(), // maxTotalBufferSize
                    Optional.empty(), // maxResourceBufferSize
                    Optional.empty(), // maxPostDataSize
                    Optional.empty()  // maxPostDataSizeReportingEnabled
            ));
            List<String> facebookPixelUrls = new ArrayList<>();

            devTools.addListener(Network.requestWillBeSent(), request -> {
                String url = request.getRequest().getUrl();
                if (url.contains("facebook.com/tr")) {
                    System.out.println("🔍 Facebook Pixel URL: " + url);
                    if (url.contains("ev=InitiateCheckout")) {
                        facebookPixelUrls.add(url);
                    }
                }
            });

            // Step 1: Add product to cart and go to cart
            new AddToCartComponents(driver).addsingleproductToCart();
            driver.findElement(HomePageLocators.CART_ICON).click();
            Thread.sleep(5000); // Let cart overlay load

            // Step 2: Click Checkout (simulate user action)
            WebElement checkoutBtn = driver.findElement(CHECKOUT_BUTTON);
            jse.executeScript("arguments[0].click();", checkoutBtn);

            // Step 3: Switch to checkout window if opened
            Thread.sleep(5000);
            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    devTools = ((ChromeDriver) driver).getDevTools(); // re-init for new window
                    devTools.createSession();
                    devTools.send(Network.enable(
                            Optional.empty(), // maxTotalBufferSize
                            Optional.empty(), // maxResourceBufferSize
                            Optional.empty(), // maxPostDataSize
                            Optional.empty()  // maxPostDataSizeReportingEnabled
                    ));
                    // Re-register listener for new window
                    devTools.addListener(Network.requestWillBeSent(), request -> {
                        String url = request.getRequest().getUrl();
                        if (url.contains("facebook.com/tr")) {
                            System.out.println("🌐 Facebook Pixel (new tab): " + url);
                            if (url.contains("ev=InitiateCheckout")) {
                                facebookPixelUrls.add(url);
                            }
                        }
                    });
                    break;
                }
            }

            // Step 4: Simulate filling email (to trigger pixel)
            wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
            driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
//            driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
            softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
            wait=new WebDriverWait(driver, Duration.ofSeconds(30));
          //  wait.until(ExpectedConditions.presenceOfElementLocated(FIRSTNAME_TEXTBOX));
            Thread.sleep(5000);
            driver.findElement(FIRSTNAME_TEXTBOX).clear();
            driver.findElement(FIRSTNAME_TEXTBOX).sendKeys("Test");
            driver.findElement(LASTNAME_TEXTBOX).clear();
            driver.findElement(LASTNAME_TEXTBOX).sendKeys("User");
            driver.findElement(ADDRESS1_TEXTBOX).clear();
            driver.findElement(ADDRESS1_TEXTBOX).sendKeys("100 Broadway Test");
            Thread.sleep(5000);
            driver.findElement(ADDRESS2_TEXTBOX).clear();
            driver.findElement(ADDRESS2_TEXTBOX).sendKeys("8th floor");
            driver.findElement(CITY_TEXTBOX).clear();
            driver.findElement(CITY_TEXTBOX).sendKeys("New York");
            WebElement countrydp = driver.findElement(COUNTRY_DROPDOWN);
            Select country = new Select(countrydp);
            country.selectByValue("US");

            WebElement statedp = driver.findElement(STATE_DROPDOWN);
            Select state = new Select(statedp);
            state.selectByValue("NY");

            driver.findElement(PINCODE_TEXTBOX).clear();
            driver.findElement(PINCODE_TEXTBOX).sendKeys("10005");
            Thread.sleep(2000);
            driver.findElement(PHONE_TEXTBOX).clear();
            driver.findElement(PHONE_TEXTBOX).sendKeys("8889996667");
            Reporter.log("Shipping Address Details entered");
            ExtentReport.extentTest.info("Shipping Address Details entered");
            // driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
            WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
            Reporter.log("User is navigated to select shipping method");
            ExtentReport.extentTest.info("User is navigated to select shipping method");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));

            Thread.sleep(5000); // Let pixel trigger naturally

            // Step 5: Refresh a few times if needed to allow pixel to fire
            int refreshAttempts = 3;
            for (int i = 0; i < refreshAttempts && facebookPixelUrls.isEmpty(); i++) {
                driver.navigate().refresh();
                System.out.println("🔄 Refresh #" + (i + 1));
                Thread.sleep(5000); // wait after refresh
            }

            // Step 6: Validate pixel fired
            if (facebookPixelUrls.isEmpty()) {
                System.out.println("❌ Facebook Pixel 'InitiateCheckout' was NOT fired!");
                Assert.fail("Facebook Pixel 'InitiateCheckout' event not found.");
            } else {
                System.out.println("✅ Facebook Pixel 'InitiateCheckout' captured:");
                facebookPixelUrls.forEach(System.out::println);
            }

            // Cleanup
            driver.quit();
        }
    }






        private static boolean compareJSONPayload(String actualPayload, String expectedPayload) {
        try {
            JSONArray actualArray = new JSONArray(actualPayload);
            JSONArray expectedArray = new JSONArray(expectedPayload);

            // ✅ Convert both JSONs to a normalized string
            String actualFormatted = actualArray.toString();
            String expectedFormatted = expectedArray.toString();

            System.out.println("🔹 Normalized Actual: " + actualFormatted);
            System.out.println("🔹 Normalized Expected: " + expectedFormatted);

            // ✅ Remove dynamic fields like "sessionid"
            for (int i = 0; i < actualArray.length(); i++) {
                JSONObject actualJson = actualArray.getJSONObject(i);
                JSONObject expectedJson = expectedArray.getJSONObject(i);

                if (actualJson.has("user")) {
                    actualJson.getJSONObject("user").getJSONObject("identities").remove("sessionid");
                }
                if (expectedJson.has("user")) {
                    expectedJson.getJSONObject("user").getJSONObject("identities").remove("sessionid");
                }

                actualJson.remove("sourceId");
                expectedJson.remove("sourceId");

                // ✅ Compare JSONs (ignoring order)
                JSONAssert.assertEquals(expectedJson.toString(), actualJson.toString(), JSONCompareMode.NON_EXTENSIBLE);
            }

            return true;  // ✅ JSONs match!
        } catch (JSONException e) {
            System.out.println("❌ JSON Comparison Error: " + e.getMessage());
            return false;
        } catch (AssertionError e) {
            System.out.println("❌ JSON Assertion Error: " + e.getMessage());
            return false;
        }
    }

    public void entershippingdetails() throws Exception {
        wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(FIRSTNAME_TEXTBOX));

        Thread.sleep(2000);
        driver.findElement(FIRSTNAME_TEXTBOX).clear();
        driver.findElement(FIRSTNAME_TEXTBOX).sendKeys("Test");
        driver.findElement(LASTNAME_TEXTBOX).clear();
        driver.findElement(LASTNAME_TEXTBOX).sendKeys("User");
        driver.findElement(ADDRESS1_TEXTBOX).clear();
        driver.findElement(ADDRESS1_TEXTBOX).sendKeys("100 Broadway Test");
        Thread.sleep(5000);
        driver.findElement(ADDRESS2_TEXTBOX).clear();
        driver.findElement(ADDRESS2_TEXTBOX).sendKeys("8th floor");
        driver.findElement(CITY_TEXTBOX).clear();
        driver.findElement(CITY_TEXTBOX).sendKeys("New York");
        WebElement countrydp = driver.findElement(COUNTRY_DROPDOWN);
        Select country = new Select(countrydp);
        country.selectByValue("US");

        WebElement statedp = driver.findElement(STATE_DROPDOWN);
        Select state = new Select(statedp);
        state.selectByValue("NY");

        driver.findElement(PINCODE_TEXTBOX).clear();
        driver.findElement(PINCODE_TEXTBOX).sendKeys("10005");
        Thread.sleep(2000);
        driver.findElement(PHONE_TEXTBOX).clear();
        driver.findElement(PHONE_TEXTBOX).sendKeys("8889996667");
    }



}



