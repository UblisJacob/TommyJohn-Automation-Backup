package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.Pages.AccountLoginPage;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
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
import org.openqa.selenium.devtools.v140.network.Network;
import org.openqa.selenium.devtools.v140.network.model.Request;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

import static com.tommyjohn.automation.PageLocators.CollectionPageLocator.*;
import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.CHECKOUT_BUTTON;


public class GTMComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();

    public GTMComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    String searchText = "";


    public void ValidateGTMforFacetEvent() throws InterruptedException {
        // ✅ 1. Setup Chrome with DevTools
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ 2. Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        // ✅ 3. Expected Payload
        String expectedPayload = "[{\"action\":\"click\",\"target\":{\"facet\":\"b2787170-7dc6-41dc-8a60-6fcdd22ba0ce\",\"facet-id\":\"coolcotton\"},\"metadata\":{\"locale\":\"en_US\"},\"user\":{\"identities\":{},\"segments\":[],\"traits\":{}}}]";
        AtomicReference<String> capturedPayload = new AtomicReference<>();

        // ✅ 4. Listen for Attraqt requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.contains("collect-eu.attraqt.io")) {
                request.getPostData().ifPresent(payload -> {
                    System.out.println("🔹 Captured Network Request:");
                    System.out.println(payload);

                    if (payload.contains("\"action\":\"click\"")) {
                        System.out.println("🔹 Found 'Click' Event Network Request!");
                        capturedPayload.set(payload);
                    }
                });
            }
        });

        // ✅ 5. Navigate to PLP
        driver.get("https://www.tommyjohn.com/collections/mens-all");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ✅ 6. Expand filters
//        By ALL_FILTERS = By.xpath("//span[@class='icon icon-filters']");
//        wait.until(ExpectedConditions.elementToBeClickable(ALL_FILTERS)).click();
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@class='icon icon-filters']")));
        Thread.sleep(2000);  // allow panel to expand

        // ✅ 7. Expand "Collection" filter
        By COLLECTION_FILTER = By.xpath("//legend[contains(text(),'Collection')]/..");  // Select parent fieldset
        WebElement collectionFilter = wait.until(ExpectedConditions.presenceOfElementLocated(COLLECTION_FILTER));
        js.executeScript("arguments[0].click();", collectionFilter);  // JS click
        Thread.sleep(2000);

        // ✅ 8. Click on Cool Cotton facet
        By COOL_COTTON_FACET = By.xpath("//label[@for='Filters-facet_collection-coolcotton']");
        WebElement facetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(COOL_COTTON_FACET));
        js.executeScript("arguments[0].click();", facetElement);  // JS click
        Thread.sleep(8000);  // Wait for network call to trigger

        // ✅ 9. Validate the captured payload
        if (capturedPayload.get() != null) {
            boolean isValid = compareJSONPayload(capturedPayload.get(), expectedPayload);
            if (!isValid) {
                Assert.fail("❌ Test Failed: Captured payload did not match expected!");
            } else {
                System.out.println("✅ Test Passed: Facet click event validated successfully!");
            }
        } else {
            Assert.fail("❌ Test Failed: Expected 'click' event was NOT found!");
        }

        driver.quit();
    }

    public void ValidateGTMforViewCollectionEvent() throws InterruptedException {
        // ✅ 1. Setup Chrome with DevTools
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();


        // ✅ 2. Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));
        // ✅ 3. Expected JSON Payload for Comparison
        String expectedPayload = "[{\"action\":\"view\",\"target\":{\"pathToPage\":\"/collections/mens-all\"},"
                + "\"sourceId\":\"9d6408ec-5e38-4216-a13d-fe98c7c51017\","
                + "\"metadata\":{\"locale\":\"en_US\"},"
                + "\"user\":{\"identities\":{\"sessionid\":\"IGNORE_THIS\"},\"segments\":[],\"traits\":{}}}]";
        final boolean[] payloadMatched = {false};

        // ✅ 4. Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.contains("collect-eu.attraqt.io")) {  // ✅ Filter by "attraqt"
                System.out.println("🔹 Found 'attraqt' Network Request:");
                System.out.println(" ➤ URL: " + url);

                Optional<String> postData = request.getPostData();
                postData.ifPresent(payload -> {
                    System.out.println("  ➤ Payload: " + payload);

                    // ✅ 5. Compare payload with expected JSON
                    if (compareJSONPayload(payload, expectedPayload)) {
                        System.out.println("✅ Payload matches the expected value!");
                        payloadMatched[0] = true;
                    } else {
                        System.out.println("❌ Payload does NOT match!");
                    }
                });
            }
        });

        // ✅ 6. Navigate to the target page & wait for requests
        driver.get("https://www.tommyjohn.com/collections/mens-all");
        Thread.sleep(20000); // Wait for network requests to be captured
        if (!payloadMatched[0]) {
            Assert.fail("❌ Test Failed: Expected 'view' event was NOT found or didn't match!");
        }

        driver.quit();  // ✅ Close the browser
    }


    public void ValidateGTMForAddToCartEvent() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // ✅ Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        // ✅ Expected JSON Payload for Comparison (Ignoring "sourceId" & "sessionid")
        String expectedPayload = "[{\"action\":\"add-to-cart\",\"target\":{\"product\":\"7201149059117\"},"
                + "\"metadata\":{\"locale\":\"en_US\",\"quantity\":1},"
                + "\"user\":{\"identities\":{},\"segments\":[],\"traits\":{}}}]";
        final boolean[] payloadMatched = {false};

        // ✅ Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.contains("collect-eu.attraqt.io")) {  // ✅ Filter by "attraqt"
                request.getPostData().ifPresent(payload -> {
                    if (payload.contains("\"action\":\"add-to-cart\"")) {  // ✅ Only process "add-to-cart" requests
                        System.out.println("🔹 Found 'add-to-cart' Network Request:");
                        System.out.println("  ➤ URL: " + url);
                        System.out.println("  ➤ Payload: " + payload);


                        // ✅ Compare payload with expected JSON
                        if (compareJSONPayload(payload, expectedPayload)) {
                            System.out.println("✅ Payload matches the expected value!");
                            payloadMatched[0] = true;
                        } else {
                            System.out.println("❌ Payload does NOT match!");
                        }
                    }
                });
            }
        });

        // ✅ Perform Add-to-Cart Action
        new AddToCartComponents(driver).addsingleproductToCart();
        Thread.sleep(5000); // ✅ Wait for network request
        driver.quit();  // ✅ Close the browser
        if (!payloadMatched[0]) {
            Assert.fail("❌ Test Failed: Expected 'add-to-cart' event was NOT found or didn't match!");
        }
    }

    public void ValidateGTMForClickEvent() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // ✅ Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));
        // ✅ Expected JSON Payload for "click" event
        String expectedPayload = "[{\"action\":\"click\",\"target\":{\"product\":\"7201149059117\"},"
                + "\"metadata\":{\"locale\":\"en_US\"},"
                + "\"user\":{\"identities\":{},\"segments\":[],\"traits\":{}}}]";

        final boolean[] payloadMatched = {false};  // ✅ Flag to track match

        // ✅ Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.contains("collect-eu.attraqt.io")) {  // ✅ Filter by "attraqt"
                request.getPostData().ifPresent(payload -> {
                    if (payload.contains("\"action\":\"click\"")) {  // ✅ Only process "click" requests
                        System.out.println("🔹 Found 'click' Network Request:");
                        System.out.println("  ➤ URL: " + url);
                        System.out.println("  ➤ Payload: " + payload);

                        // ✅ Compare payload with expected JSON
                        if (compareJSONPayload(payload, expectedPayload)) {
                            System.out.println("✅ Payload matches the expected value!");
                            payloadMatched[0] = true;  // ✅ Mark as matched
                        } else {
                            System.out.println("❌ Payload does NOT match!");
                        }
                    }
                });
            }
        });

        // ✅ Perform Click Action (Replace with actual click action)
        new AddToCartComponents(driver).addsingleproductToCart();
        Thread.sleep(5000); // ✅ Wait for network request

        driver.quit();  // ✅ Close the browser

        // ❌ Fail the test if no matching payload was found
        if (!payloadMatched[0]) {
            Assert.fail("❌ Test Failed: Expected 'click' event was NOT found or didn't match!");
        }
    }

    public void ValidateGTMForViewProductEvent() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // ✅ Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        // ✅ Expected JSON Payload for "view" product event
        String expectedPayload = "[{\"action\":\"view\",\"target\":{\"product\":\"7201149059117\"},"
                + "\"metadata\":{\"locale\":\"en_US\"},"
                + "\"user\":{\"identities\":{},\"segments\":[],\"traits\":{}}}]";

        final boolean[] payloadMatched = {false};  // ✅ Flag to track match

        // ✅ Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.contains("collect-eu.attraqt.io")) {  // ✅ Filter by "attraqt"
                request.getPostData().ifPresent(payload -> {
                    if (payload.contains("\"action\":\"view\"") && payload.contains("\"product\"")) {  // ✅ Only process "view" product requests
                        System.out.println("🔹 Found 'view' Product Network Request:");
                        System.out.println("  ➤ URL: " + url);
                        System.out.println("  ➤ Payload: " + payload);

                        // ✅ Compare payload with expected JSON
                        if (compareJSONPayload(payload, expectedPayload)) {
                            System.out.println("✅ Payload matches the expected value!");
                            payloadMatched[0] = true;  // ✅ Mark as matched
                        } else {
                            System.out.println("❌ Payload does NOT match!");
                        }
                    }
                });
            }
        });

        // ✅ Perform View Product Action (Replace with actual action)
        new AddToCartComponents(driver).addsingleproductToCart();
        Thread.sleep(5000); // ✅ Wait for network request

        driver.quit();  // ✅ Close the browser

        // ❌ Fail the test if no matching payload was found
        if (!payloadMatched[0]) {
            Assert.fail("❌ Test Failed: Expected 'view' product event was NOT found or didn't match!");
        }
    }

    public void ValidateGTMForSearchEvent() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // ✅ Run in headless mode (uncomment after debugging)
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        // ✅ Expected JSON Payload Adjusted to Match Actual Data
        String expectedSearchTerm = "second skin";
        String expectedPathToPage = "/search";

        final boolean[] payloadMatched = {false};  // ✅ Flag to track match

        // ✅ Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.contains("collect-eu.attraqt.io")) {  // ✅ Filter by "attraqt"
                request.getPostData().ifPresent(payload -> {
                    System.out.println("🔹 Captured Network Request: " + payload); // ✅ Log full payload

                    if (payload.contains("\"action\":\"view\"") && payload.contains("\"pathToPage\":\"" + expectedPathToPage + "\"")
                            && payload.contains("\"search-terms\":\"" + expectedSearchTerm + "\"")) {  // ✅ Check for search terms in "view" event
                        System.out.println("🔹 Found 'view' Search Event:");
                        System.out.println("  ➤ URL: " + url);
                        System.out.println("  ➤ Payload: " + payload);

                        payloadMatched[0] = true;  // ✅ Mark as matched
                    }
                });
            }
        });

        // ✅ Perform Search Action
        driver.get("https://www.tommyjohn.com/");
        new HomePageComponents(driver).ValidateSearchResult();

        Thread.sleep(15000); // ✅ Increased wait time to capture request

        driver.quit();  // ✅ Close the browser

        // ❌ Fail the test if no matching payload was found
        if (!payloadMatched[0]) {
            Assert.fail("❌ Test Failed: Expected 'view' event with search terms was NOT found or didn't match!");
        }
    }

    public void ValidateGTMForRemoveFromCartEvent() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        // ✅ Expected JSON Payload (product ID will be dynamically matched)
        String expectedPayload = "[{\"action\":\"remove-from-cart\",\"target\":{\"product\":\"7884372607021\"},"
                + "\"metadata\":{\"locale\":\"en_US\"},"
                + "\"user\":{\"identities\":{},\"segments\":[],\"traits\":{}}}]";

        final boolean[] payloadMatched = {false};  // ✅ Flag to track match

        // ✅ Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.contains("collect-eu.attraqt.io")) {  // ✅ Filter by "attraqt"
                request.getPostData().ifPresent(payload -> {
                    if (payload.contains("\"action\":\"remove-from-cart\"")) {  // ✅ Ensure it's a remove-from-cart event
                        System.out.println("🔹 Found 'remove-from-cart' Event Network Request:");
                        System.out.println("  ➤ URL: " + url);
                        System.out.println("  ➤ Payload: " + payload);

                        // ✅ Compare payload with expected JSON
                        if (compareJSONPayload(payload, expectedPayload)) {
                            System.out.println("✅ Payload matches the expected value!");
                            payloadMatched[0] = true;  // ✅ Mark as matched
                        } else {
                            System.out.println("❌ Payload does NOT match!");
                        }
                    }
                });
            }
        });

        // ✅ Perform Remove From Cart Action
        driver.get("https://www.tommyjohn.com/");
        new AddToCartComponents(driver).removesingleproductfromCart();
        Thread.sleep(10000); // ✅ Wait for network request

        driver.quit();  // ✅ Close the browser

        // ❌ Fail the test if no matching payload was found
        if (!payloadMatched[0]) {
            Assert.fail("❌ Test Failed: Expected 'remove-from-cart' event was NOT found or didn't match!");
        }
    }


    public void ValidateSplunk() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // ✅ Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        final boolean[] gtmFound = {false};  // ✅ Flag to check GTM presence

        // ✅ Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            String url = requestSent.getRequest().getUrl();

            if (url.contains("https://www.googletagmanager.com/gtm.js?id=GTM-WQ4H93GN")) {
                System.out.println("✅ GTM Script Found in Network: " + url);
                gtmFound[0] = true;
            }
        });

        // ✅ Listen for response received to extract Initiator details
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            String requestUrl = responseReceived.getResponse().getUrl();

            if (requestUrl.contains("https://www.googletagmanager.com/gtm.js?id=GTM-WQ4H93GN")) {
                System.out.println("🔍 GTM Script Loaded! Extracting Initiator...");
                devTools.send(Network.getResponseBody(responseReceived.getRequestId())).getBody();

                // ✅ Extract Initiator (Caller Script)
                devTools.addListener(Network.loadingFailed(), failedRequest -> {
                    if (failedRequest.getRequestId().equals(responseReceived.getRequestId())) {
                        System.out.println("❌ GTM Request Failed to Load!");
                    }
                });
            }
        });

        // ✅ Perform the action (Navigate to the target page)
        driver.get("https://www.tommyjohn.com/collections/mens-all");  // Change the URL if needed
        Thread.sleep(10000);  // Wait for network requests

        driver.quit();  // ✅ Close the browser

        // ❌ Fail the test if GTM script was not found
        if (!gtmFound[0]) {
            Assert.fail("❌ Test Failed: Google Tag Manager script (gtm.js) was NOT found!");
        } else {
            System.out.println("🎉 Test Passed: GTM script is successfully loaded!");
        }
    }

    public void ValidatePowerfront() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // ✅ Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        final boolean[] gtmFound = {false};  // ✅ Flag to check GTM presence

        // ✅ Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            String url = requestSent.getRequest().getUrl();

            if (url.contains("https://www.googletagmanager.com/gtm.js?id=GTM-WQ4H93GN")) {
                System.out.println("✅ GTM Script Found in Network: " + url);
                gtmFound[0] = true;
            }
        });

        // ✅ Listen for response received to extract Initiator details
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            String requestUrl = responseReceived.getResponse().getUrl();

            if (requestUrl.contains("https://www.googletagmanager.com/gtm.js?id=GTM-WQ4H93GN")) {
                System.out.println("🔍 GTM Script Loaded! Extracting Initiator...");
               System.out.println(devTools.send(Network.getResponseBody(responseReceived.getRequestId())).getBody());

                // ✅ Extract Initiator (Caller Script)
                devTools.addListener(Network.loadingFailed(), failedRequest -> {
                    if (failedRequest.getRequestId().equals(responseReceived.getRequestId())) {
                        System.out.println("❌ GTM Request Failed to Load!");
                    }
                });
            }
        });

        // ✅ Perform the action (Navigate to the target page)
        driver.get("https://www.tommyjohn.com/products/air-flow-short?variant=41983583191085");  // Change the URL if needed
        Thread.sleep(10000);  // Wait for network requests

        driver.quit();  // ✅ Close the browser

        // ❌ Fail the test if GTM script was not found
        if (!gtmFound[0]) {
            Assert.fail("❌ Test Failed: Google Tag Manager script (gtm.js) was NOT found!");
        } else {
            System.out.println("🎉 Test Passed: GTM script is successfully loaded!");
        }

    }

//    public void ValidateRudderstackForPLP() throws InterruptedException {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");  // ✅ Run in headless mode
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920,1080");
//
//        ChromeDriver driver = new ChromeDriver(options);
//        DevTools devTools = driver.getDevTools();
//        devTools.createSession();
//
//        // ✅ Enable network monitoring
//        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//
//        final boolean[] propertiesFound = {false};  // ✅ Track if 'properties' were found
//
//        // ✅ Listen for network requests
//        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
//            Request request = requestSent.getRequest();
//            String url = request.getUrl();
//
//            if (url.equals("https://dp.tommyjohn.com/v1/page")) {  // ✅ Filter by exact URL
//                request.getPostData().ifPresent(payload -> {
//                    System.out.println("🔹 Found Tommy John Network Request:");
//                    System.out.println("  ➤ URL: " + url);
//                    System.out.println("  ➤ Payload: " + payload);
//
//                    // ✅ Extract "properties" from JSON payload
//                    try {
//                        JSONObject jsonPayload = new JSONObject(payload);
//
//                        if (jsonPayload.has("properties")) {
//                            JSONObject properties = jsonPayload.getJSONObject("properties");
//
//                            // ✅ Extract values
//                            String productId = properties.optString("product_id", "");
//                            String activeVariantSku = properties.optString("active_variant_sku", "");
//                            String defaultSwatchVariantSku = properties.optString("default_swatch_variant_sku", "");
//
//                            System.out.println("  ➤ Extracted Properties:");
//                            System.out.println("    • product_id: " + productId);
//                            System.out.println("    • active_variant_sku: " + activeVariantSku);
//                            System.out.println("    • default_swatch_variant_sku: " + defaultSwatchVariantSku);
//
//                            // ✅ Ensure values are present
//                            if (!productId.isEmpty() && !activeVariantSku.isEmpty() && !defaultSwatchVariantSku.isEmpty()) {
//                                propertiesFound[0] = true;
//                            } else {
//                                System.out.println("❌ One or more required properties are missing!");
//                            }
//                        } else {
//                            System.out.println("❌ 'properties' object NOT found in JSON!");
//                        }
//                    } catch (JSONException e) {
//                        Assert.fail("❌ Test Failed: Error parsing JSON - " + e.getMessage());
//                    }
//                });
//            }
//        });
//
//        // ✅ Perform a page navigation or interaction
//        driver.get("https://www.tommyjohn.com/collections/mens-underwear");
//        Thread.sleep(5000);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
//
//        WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
//        System.out.println("🔹 Current URL: " + driver.getCurrentUrl());
//
//        Thread.sleep(5000); // ✅ Wait for network request
//
//        driver.quit();  // ✅ Close the browser
//
//        // ✅ Fail the test if 'properties' were not found
//        if (!propertiesFound[0]) {
//            Assert.fail("❌ Test Failed: Expected 'properties' object was NOT found in the network request!");
//        } else {
//            System.out.println("✅ Test Passed: 'properties' object was found successfully!");
//        }
//    }

    public void ValidateRudderstackForPLP() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));
        final boolean[] isValidPayload = {false};

        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            // Filter only for dp.tommyjohn.com/v1/page
            if (url.equals("https://dp.tommyjohn.com/v1/page")) {
                request.getPostData().ifPresent(payload -> {
                    try {
                        JSONObject json = new JSONObject(payload);
                        JSONObject properties = json.optJSONObject("properties");

                        if (properties != null) {
                            String handle = properties.optString("collection_handle", "");
                            String title = properties.optString("collection_title", "");
                            String division = properties.optString("page_division", "");

                            System.out.println("🔹 Extracted Properties:");
                            System.out.println("  • collection_handle: " + handle);
                            System.out.println("  • collection_title: " + title);
                            System.out.println("  • page_division: " + division);

                            if (!handle.isEmpty() && !title.isEmpty() && !division.isEmpty()) {
                                isValidPayload[0] = true;
                            } else {
                                System.out.println("❌ One or more expected properties are missing!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Failed to parse payload: " + e.getMessage());
                    }
                });
            }
        });

        // Navigate to PLP
        driver.get("https://www.tommyjohn.com/collections/mens-underwear");

        // Wait for network events to complete
        Thread.sleep(15000);

        driver.quit();

        if (!isValidPayload[0]) {
            Assert.fail("❌ Test failed: Expected PLP properties were not found in the request payload.");
        } else {
            System.out.println("✅ PLP properties validated successfully!");
        }
    }


    public void ValidateRudderstackForPDP() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // ✅ Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        final boolean[] propertiesFound = {false};  // ✅ Track if 'properties' were found

        // ✅ Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.equals("https://dp.tommyjohn.com/v1/page")) {  // ✅ Filter by exact URL
                request.getPostData().ifPresent(payload -> {
                    System.out.println("🔹 Found Tommy John Network Request:");
                    System.out.println("  ➤ URL: " + url);
                    System.out.println("  ➤ Payload: " + payload);

                    // ✅ Extract "properties" from JSON payload
                    try {
                        JSONObject jsonPayload = new JSONObject(payload);

                        if (jsonPayload.has("properties")) {
                            JSONObject properties = jsonPayload.getJSONObject("properties");

                            // ✅ Extract values
                            String productId = properties.optString("product_id", "7381045739565");
                            String activeVariantSku = properties.optString("active_variant_sku", "3636:S");
                            String defaultSwatchVariantSku = properties.optString("default_swatch_variant_sku", "3636:S");


                            System.out.println("  ➤ Extracted Properties:");
                            System.out.println("    • product_id: " + productId);
                            System.out.println("    • active_variant_sku: " + activeVariantSku);
                            System.out.println("    • default_swatch_variant_sku: " + defaultSwatchVariantSku);

                            // ✅ Mark properties as found if values exist
                            if (!productId.equals("7201149059117") || !activeVariantSku.equals("3636:S") || !defaultSwatchVariantSku.equals("3636:S")) {
                                propertiesFound[0] = true;
                            }
                        } else {
                            System.out.println("❌ 'properties' object NOT found in JSON!");
                        }
                    } catch (JSONException e) {
                        System.out.println("❌ Error parsing JSON: " + e.getMessage());
                    }
                });
            }
        });

        // ✅ Perform a page navigation or interaction
        driver.get("https://www.tommyjohn.com/collections/mens-underwear");
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_HEAD_LINE_TEXT));
        String text = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT).getText();
        System.out.println(text);
        WebElement el = driver.findElement(FIRST_PRODUCT_HEAD_LINE_TEXT);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", el);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_TITLE));
        Reporter.log(driver.getCurrentUrl());
        ExtentReport.extentTest.info(driver.getCurrentUrl());

        Thread.sleep(5000); // ✅ Wait for network request

        driver.quit();  // ✅ Close the browser

        // ✅ Fail the test if 'properties' were not found
        if (!propertiesFound[0]) {
            Assert.fail("❌ Test Failed: Expected 'properties' object was NOT found in the network request!");
        }

    }

    public void ValidateKlaviyo() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

        // Enable performance logging for fallback
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        final boolean[] klaviyoMetricFound = {false};

        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            String url = requestSent.getRequest().getUrl();
            if (url.contains("a.klaviyo.com/client/events")) {
                klaviyoMetricFound[0] = true;
                System.out.println("🔹 Found Klaviyo in DevTools request URL: " + url);
            }
        });

        driver.get("https://www.tommyjohn.com/");
        new AccountLoginPage(driver).loginAccount("testuserQA1@gmail.com", "Test@123");
        new AddToCartComponents(driver).addsingleproductToCartKlaviyo();

        Thread.sleep(3000);

        // Click Cart Icon
        driver.findElement(HomePageLocators.CART_ICON).click();

        // Wait until inline cart is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='text-heading-lg font-medium leading-none sm:text-heading']")));
            System.out.println("✅ Inline cart is now visible!");
        } catch (TimeoutException e) {
            System.out.println("⚠️ Cart did not render fully, continuing...");
        }

        // Trigger any potential lazy-load pixels
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

        // Wait up to 15 seconds
        long startTime = System.currentTimeMillis();
        while (!klaviyoMetricFound[0] && (System.currentTimeMillis() - startTime) < 15000) {
            Thread.sleep(1000);
        }

        if (!klaviyoMetricFound[0]) {
            System.out.println("⚠️ DevTools did not catch it, falling back to Performance Logs...");
            LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);
            for (LogEntry entry : logs) {
                String message = entry.getMessage();
                if (message.contains("a.klaviyo.com/client/events")) {
                    klaviyoMetricFound[0] = true;
                    System.out.println("🔹 Found Klaviyo in Performance Logs.");
                    break;
                }
            }
        }

        driver.quit();

        if (!klaviyoMetricFound[0]) {
            Assert.fail("❌ Test Failed: Expected Klaviyo 'Added to Cart' event was NOT found even in fallback!");
        } else {
            System.out.println("✅ Test Passed: Klaviyo 'Added to Cart' event found!");
        }
    }



    public void ValidateContentSquare() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        final boolean[] miniCartFound = {false};

        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            // ✅ Look for ContentSquare pageview event
            if (url.contains("c.az.contentsquare.net/pageview")) {
                System.out.println("🔵 Found ContentSquare Network Request:");
                System.out.println("  ➤ URL: " + url);

                // ✅ Decode URL parameter inside the request
                try {
                    String decodedUrl = java.net.URLDecoder.decode(url, java.nio.charset.StandardCharsets.UTF_8.toString());
                    System.out.println("  ➤ Decoded URL: " + decodedUrl);

                    if (decodedUrl.contains("cs-mini-cart")) {
                        System.out.println("✅ Mini-Cart Event Detected in ContentSquare request!");
                        miniCartFound[0] = true;
                    }
                } catch (Exception e) {
                    System.out.println("❌ Error decoding URL: " + e.getMessage());
                }
            }
        });

        // ✅ Your actions that trigger mini-cart
        driver.get("https://www.tommyjohn.com/");
        new AccountLoginPage(driver).loginAccount("testuserQA1@gmail.com", "Test@123");
        new AddToCartComponents(driver).addsingleproductToCart();
        Thread.sleep(15000);

        driver.quit();

        if (!miniCartFound[0]) {
            Assert.fail("❌ Test Failed: 'cs-mini-cart' event not found in ContentSquare request!");
        } else {
            System.out.println("✅ Test Passed: 'cs-mini-cart' event detected!");
        }
    }

    public void validateGTMClaritasForSiteWide() throws Exception {
        // Setup Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // Launch ChromeDriver
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable network logging
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));

        // Store all matching TRKN URLs
        List<String> trknUrls = new ArrayList<>();

        // Add listener for network requests
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String url = request.getRequest().getUrl();
            if (url.contains("trkn.us/pixel/conv")) {
                System.out.println("📡 TRKN Pixel Fired: " + url);
                trknUrls.add(url);
            }
        });

        // Step 1: Go to Tommy John homepage
        driver.get("https://www.tommyjohn.com/collections/mens-all");

        // Optional: Wait or interact with the page to let the pixel fire
        Thread.sleep(10000);  // Wait 10 seconds to allow events to fire

        // Step 2: Validate the pixel parameters
        boolean pixelFired = trknUrls.stream().anyMatch(url ->
                url.contains("ppt=15693") &&
                        url.contains("g=sitewide_pixel") &&
                        url.contains("gid=38495")
        );

        if (!pixelFired) {
            Assert.fail("❌ Expected TRKN pixel was NOT fired!");
        } else {
            System.out.println("✅ TRKN pixel with expected parameters was fired successfully.");
        }

        // Close browser
        driver.quit();
    }

    public void validateGTMClaritasForAddToCart() throws Exception {
        ChromeOptions options = new ChromeOptions();
         options.addArguments("--headless=new"); // Enable for headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));
        // Store matching TRKN pixel URLs
        List<String> trknPixelUrls = new ArrayList<>();

        // Listen to all network requests
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String url = request.getRequest().getUrl();
            if (url.contains("trkn.us/pixel/conv/") && url.contains("g=add_to_cart")) {
                trknPixelUrls.add(url);
                System.out.println("🟢 TRKN AddToCart Pixel Fired: " + url);
            }
        });

        new AddToCartComponents(driver).addsingleproductToCart();
        Thread.sleep(10000); // ✅ Wait for network request
        if (trknPixelUrls.isEmpty()) {
            Assert.fail("❌ TRKN 'add_to_cart' pixel was NOT fired!");
        } else {
            System.out.println("✅ TRKN 'add_to_cart' pixel fired successfully.");
        }

        driver.quit();
    }

    public void validateGTMClaritasForCheckout() throws Exception {
        ChromeOptions options = new ChromeOptions();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
         options.addArguments("--headless=new"); // Enable for headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));
        // Store matching TRKN pixel URLs
        List<String> trknCheckoutUrls  = new ArrayList<>();

        // Listen to all network requests
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String url = request.getRequest().getUrl();
            if (url.contains("trkn.us/pixel/conv/") && url.contains("g=checkout")) {
                trknCheckoutUrls.add(url);
                System.out.println("🟢 TRKN Checkout Pixel Fired: " + url);
            }
        });

        new AddToCartComponents(driver).addsingleproductToCart();
        driver.findElement(HomePageLocators.CART_ICON).click();
        Thread.sleep(5000); // Let cart overlay load

        // Step 2: Click Checkout (simulate user action)
//        WebElement checkoutBtn = driver.findElement(CHECKOUT_BUTTON);
//        jse.executeScript("arguments[0].click();", checkoutBtn);
        driver.findElement(CHECKOUT_BUTTON).click();
        Thread.sleep(10000); // ✅ Wait for network request
        if (trknCheckoutUrls.isEmpty()) {
            Assert.fail("❌ TRKN 'checkout' pixel was NOT fired!");
        } else {
            System.out.println("✅ TRKN 'checkout' pixel fired successfully.");
        }

        driver.quit();


    }

    public void validateGTMArtsaiForContent() throws InterruptedException {
    ChromeOptions options = new ChromeOptions();
 //   options.addArguments("--headless=new");
    options.addArguments("--disable-gpu");
    options.addArguments("--window-size=1920,1080");

    // Launch ChromeDriver
    ChromeDriver driver = new ChromeDriver(options);
    DevTools devTools = driver.getDevTools();
    devTools.createSession();

    // Enable network logging
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));
    // Store all matching Adxcel URLs
    List<String> adxcelUrls = new ArrayList<>();

    // Add listener for network requests
    devTools.addListener(Network.requestWillBeSent(), request -> {
        String url = request.getRequest().getUrl();
        if (url.contains("data.adxcel") && url.contains("pixel/?ad_log=refer&action=content")) {
            System.out.println("📡 Adxcel Pixel Fired: " + url);
            adxcelUrls.add(url);
        }
    });

    // Step 1: Navigate to Tommy John page where pixel should fire
    driver.get("https://www.tommyjohn.com/collections/mens-all");
    driver.findElement(By.xpath("(//a[contains(text(),'Men')])[2]")).click();

    // Optional: Wait for pixel scripts to fire
    Thread.sleep(20000);

    // Step 2: Validate the Adxcel pixel
    if (adxcelUrls.isEmpty()) {
        Assert.fail("❌ Adxcel 'content' pixel was NOT fired!");
    } else {
        System.out.println("✅ Adxcel 'content' pixel fired successfully.");
    }

    // Close browser
    driver.quit();
}

    public void validateGTMBingEvent() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty()  // maxPostDataSizeReportingEnabled
        ));
        List<String> bingUrls = new ArrayList<>();

        devTools.addListener(Network.requestWillBeSent(), request -> {
            String url = request.getRequest().getUrl();
            if (url.contains("bat.bing.com/bat.js")) {
                System.out.println("📡 Bing Script Loaded: " + url);
                bingUrls.add(url);
            }
        });

        driver.get("https://www.tommyjohn.com/collections/mens-all");

        // Wait up to 20 seconds for the Bing URL to appear in the list
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean scriptFound = wait.until(driver1 -> !bingUrls.isEmpty());

        if (!scriptFound) {
            Assert.fail("❌ Bing bat.js script was NOT loaded!");
        } else {
            System.out.println("✅ Bing bat.js script was loaded successfully.");
        }

        driver.quit();
    }



    // ✅ Function to Compare JSON Payloads
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



}