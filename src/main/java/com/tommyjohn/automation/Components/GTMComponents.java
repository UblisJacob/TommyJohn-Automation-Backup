package com.tommyjohn.automation.Components;

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
import org.openqa.selenium.devtools.v133.network.Network;
import org.openqa.selenium.devtools.v133.network.model.Request;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.tommyjohn.automation.PageLocators.CollectionPageLocator.*;


public class GTMComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();

    public GTMComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    String searchText="";


    public void ValidateGTMforFacetEvent() throws InterruptedException {
        // ✅ 1. Setup Chrome with DevTools
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ 2. Enable network monitoring
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // ✅ 3. Expected JSON Payload for Comparison
        String expectedPayload = "[{\"action\":\"click\",\"target\":{\"facet\":\"b2787170-7dc6-41dc-8a60-6fcdd22ba0ce\","
                + "\"facet-id\":\"coolcotton\"},"
                + "\"metadata\":{\"locale\":\"en_US\"},"
                + "\"user\":{\"identities\":{},\"segments\":[],\"traits\":{}}}]";

        AtomicReference<String> capturedPayload = new AtomicReference<>();

        // ✅ 4. Listen for network requests
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            Request request = requestSent.getRequest();
            String url = request.getUrl();

            if (url.contains("collect-eu.attraqt.io")) {  // ✅ Filter requests to Attraqt
                request.getPostData().ifPresent(payload -> {
                    System.out.println("🔹 Captured Network Request:");
                    System.out.println(payload);  // 🔹 Print actual payload for debugging

                    if (payload.contains("\"action\":\"click\"")) {  // ✅ Ensure it's a click event
                        System.out.println("🔹 Found 'Click' Event Network Request!");
                        capturedPayload.set(payload); // Store the payload for validation
                    }
                });
            }
        });

        // ✅ 5. Navigate to the target page
        driver.get("https://www.tommyjohn.com/collections/mens-all");
        Thread.sleep(5000);
        new CollectionPageComponent(driver).ValidateFacet();

        // ✅ 7. Validate the captured payload
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

        driver.quit();  // ✅ Close the browser
    }

    public void ValidateGTMforViewCollectionEvent() throws InterruptedException {
        // ✅ 1. Setup Chrome with DevTools
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();


        // ✅ 2. Enable network monitoring
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

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
        Thread.sleep(10000); // Wait for network requests to be captured
        if (!payloadMatched[0]) {
            Assert.fail("❌ Test Failed: Expected 'add-to-cart' event was NOT found or didn't match!");
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
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // ✅ Expected JSON Payload for Comparison (Ignoring "sourceId" & "sessionid")
        String expectedPayload = "[{\"action\":\"add-to-cart\",\"target\":{\"product\":\"1307257536557\"},"
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
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // ✅ Expected JSON Payload for "click" event
        String expectedPayload = "[{\"action\":\"click\",\"target\":{\"product\":\"1307257536557\"},"
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
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // ✅ Expected JSON Payload for "view" product event
        String expectedPayload = "[{\"action\":\"view\",\"target\":{\"product\":\"1307257536557\"},"
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
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

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
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // ✅ Expected JSON Payload (product ID will be dynamically matched)
        String expectedPayload = "[{\"action\":\"remove-from-cart\",\"target\":{\"product\":\"1307257536557\"},"
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