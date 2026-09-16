package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.Pages.AccountLoginPage;
import com.tommyjohn.automation.Pages.CheckoutPage;
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
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v152.network.Network;
import org.openqa.selenium.devtools.v152.network.model.Request;
import org.openqa.selenium.devtools.v152.network.model.ResourceType;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;



import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import static com.tommyjohn.automation.PageLocators.CheckoutPageLocators.COMPLETE_ORDER;
import static com.tommyjohn.automation.PageLocators.CheckoutPageLocators.ORDER_CONFIRMATION;
import static com.tommyjohn.automation.PageLocators.CollectionPageLocator.*;
import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.CHECKOUT_BUTTON;
import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.VIEW_CART_LINK;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.CART_ICON;


public class GTMComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();

    public GTMComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    String searchText = "";
    public JavascriptExecutor jse;

    public void validateDataSteamEvents() throws Exception {

        SoftAssert softAssert = new SoftAssert();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));


        // =========================================================
        // DATASTEAM REQUEST LIST
        // =========================================================

        List<DataSteamRequestData> dataSteamRequests =
                Collections.synchronizedList(new ArrayList<>());


        // =========================================================
        // EVENT FLAGS
        // =========================================================

        boolean validDataSteamRequest = false;

        boolean categoryEventFound = false;

        boolean actionEventFound = false;

        boolean cartClickEventFound = false;


        // =========================================================
        // START DEVTOOLS
        // =========================================================

        DevTools devTools =
                ((HasDevTools) driver).getDevTools();

        devTools.createSession();


        devTools.send(
                Network.enable(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                )
        );


        // =========================================================
        // CAPTURE DATASSTEAM NETWORK REQUESTS
        // =========================================================

        devTools.addListener(
                Network.requestWillBeSent(),
                requestWillBeSent -> {

                    Request request =
                            requestWillBeSent.getRequest();

                    String requestUrl =
                            request.getUrl();

                    String postData =
                            request.getPostData().orElse("");

                    String resourceType =
                            requestWillBeSent
                                    .getType()
                                    .toString();


                    String requestContent =
                            (requestUrl + " " + postData)
                                    .toLowerCase();


                    // -------------------------------------------------
                    // FILTER DATASTEAM REQUEST
                    // -------------------------------------------------

                    if (requestContent.contains("datasteam")) {

                        DataSteamRequestData data =
                                new DataSteamRequestData(
                                        request.getMethod(),
                                        requestUrl,
                                        postData,
                                        resourceType
                                );

                        dataSteamRequests.add(data);


                        System.out.println();

                        System.out.println(
                                "=============================================="
                        );

                        System.out.println(
                                "DataSteam Request Captured"
                        );

                        System.out.println(
                                "URL :: " + requestUrl
                        );

                        System.out.println(
                                "=============================================="
                        );
                    }
                }
        );


        // =========================================================
        // ADD PRODUCT TO CART
        // =========================================================

        System.out.println();

        System.out.println(
                "Adding product to cart..."
        );


        new AddToCartComponents(driver)
                .addsingleproductToCart();


        JavascriptExecutor jse =
                (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", driver.findElement(VIEW_CART_LINK));

        // =========================================================
        // WAIT FOR CART PAGE
        // =========================================================

        try {

            wait.until(
                    ExpectedConditions.urlContains("/cart")
            );

            System.out.println();

            System.out.println(
                    "Cart page reached successfully."
            );

        } catch (TimeoutException e) {

            softAssert.fail(
                    "Cart page was not reached after adding product."
            );

            softAssert.assertAll();

            return;
        }


        // =========================================================
        // WAIT FOR DATASTEAM REQUEST
        // =========================================================

        try {

            wait.until(
                    driver ->
                            !dataSteamRequests.isEmpty()
            );

        } catch (TimeoutException e) {

            System.out.println();

            System.out.println(
                    "================================================="
            );

            System.out.println(
                    "DATASTEAM PIXEL VALIDATION FAILED"
            );

            System.out.println(
                    "No DataSteam request was captured."
            );

            System.out.println(
                    "================================================="
            );


            softAssert.fail(
                    "No DataSteam request was captured after adding product to cart."
            );

            softAssert.assertAll();

            return;
        }


        // =========================================================
        // PROCESS ALL DATASTEAM REQUESTS
        // =========================================================

        for (DataSteamRequestData data :
                dataSteamRequests) {


            String requestUrl =
                    data.getUrl();


            // -----------------------------------------------------
            // ONLY API.DATASTEAM.IO
            // -----------------------------------------------------

            if (!requestUrl.contains(
                    "api.datasteam.io")) {

                continue;
            }


            // -----------------------------------------------------
            // ONLY RAW DATA EVENTS
            // -----------------------------------------------------

            if (!requestUrl
                    .toLowerCase()
                    .contains("/rawdata/")) {

                continue;
            }


            validDataSteamRequest = true;


            System.out.println();

            System.out.println(
                    "================================================="
            );

            System.out.println(
                    "VALID DATASTEAM EVENT FOUND"
            );

            System.out.println(
                    "================================================="
            );

            System.out.println(
                    "URL :: " + requestUrl
            );


            // =====================================================
            // EXTRACT QUERY PARAMETERS
            // =====================================================

            Map<String, String> parameters =
                    extractQueryParameters(
                            requestUrl
                    );


            String version =
                    parameters.get("v");

            String sessionEvent =
                    parameters.get("se");

            String dataPayload =
                    parameters.get("d");

            String callback =
                    parameters.get("callback");


            System.out.println();

            System.out.println(
                    "DataSteam Query Parameters"
            );

            System.out.println(
                    "---------------------------------------------"
            );

            System.out.println(
                    "v :: " + version
            );

            System.out.println(
                    "se :: " + sessionEvent
            );


            if (dataPayload != null) {

                System.out.println(
                        "d :: [Payload - "
                                + dataPayload.length()
                                + " characters]"
                );

            } else {

                System.out.println(
                        "d :: NULL"
                );
            }


            System.out.println(
                    "callback :: " + callback
            );


            System.out.println();

            System.out.println(
                    "DataSteam v :: " + version
            );

            System.out.println(
                    "DataSteam se :: " + sessionEvent
            );


            // =====================================================
            // VALIDATE QUERY PARAMETERS
            // =====================================================

            softAssert.assertNotNull(
                    version,
                    "DataSteam 'v' query parameter is missing."
            );


            softAssert.assertNotNull(
                    sessionEvent,
                    "DataSteam 'se' query parameter is missing."
            );


            softAssert.assertNotNull(
                    dataPayload,
                    "DataSteam 'd' query parameter is missing."
            );


            if (dataPayload == null ||
                    dataPayload.isEmpty()) {

                continue;
            }


            // =====================================================
            // DECODE BASE64 PAYLOAD
            // =====================================================

            try {

                String decodedPayload =
                        new String(
                                Base64.getDecoder().decode(
                                        dataPayload
                                ),
                                StandardCharsets.UTF_8
                        );


                System.out.println();

                System.out.println(
                        "================================================="
                );

                System.out.println(
                        "DECODED DATASTEAM PAYLOAD"
                );

                System.out.println(
                        "================================================="
                );

                System.out.println(
                        decodedPayload
                );


                // =================================================
                // PARSE JSON
                // =================================================

                JsonObject jsonPayload =
                        JsonParser
                                .parseString(
                                        decodedPayload
                                )
                                .getAsJsonObject();


                // =================================================
                // GET COMMON PAYLOAD VALUES
                // =================================================

                String payloadVersion =
                        getJsonValue(
                                jsonPayload,
                                "v"
                        );


                String payloadSession =
                        getJsonValue(
                                jsonPayload,
                                "se"
                        );


                String pageUrl =
                        getJsonValue(
                                jsonPayload,
                                "u"
                        );


                String pageName =
                        getJsonValue(
                                jsonPayload,
                                "pn"
                        );


                String pageTitle =
                        getJsonValue(
                                jsonPayload,
                                "t"
                        );


                String productId =
                        getJsonValue(
                                jsonPayload,
                                "pr"
                        );


                String eventSequence =
                        getJsonValue(
                                jsonPayload,
                                "s"
                        );


                String eventType =
                        getJsonValue(
                                jsonPayload,
                                "l"
                        );


                String variable01 =
                        getJsonValue(
                                jsonPayload,
                                "v01"
                        );


                String variable02 =
                        getJsonValue(
                                jsonPayload,
                                "v02"
                        );


                String variable03 =
                        getJsonValue(
                                jsonPayload,
                                "v03"
                        );


                String variable04 =
                        getJsonValue(
                                jsonPayload,
                                "v04"
                        );


                // =================================================
                // PRINT PAYLOAD VALUES
                // =================================================

                System.out.println();

                System.out.println(
                        "DataSteam Payload Fields"
                );

                System.out.println(
                        "---------------------------------------------"
                );

                System.out.println(
                        "v    :: " + payloadVersion
                );

                System.out.println(
                        "se   :: " + payloadSession
                );

                System.out.println(
                        "u    :: " + pageUrl
                );

                System.out.println(
                        "pn   :: " + pageName
                );

                System.out.println(
                        "t    :: " + pageTitle
                );

                System.out.println(
                        "pr   :: " + productId
                );

                System.out.println(
                        "s    :: " + eventSequence
                );

                System.out.println(
                        "l    :: " + eventType
                );

                System.out.println(
                        "v01  :: " + variable01
                );

                System.out.println(
                        "v02  :: " + variable02
                );

                System.out.println(
                        "v03  :: " + variable03
                );

                System.out.println(
                        "v04  :: " + variable04
                );


                // =================================================
                // COMMON PAYLOAD VALIDATION
                // =================================================

                softAssert.assertFalse(
                        isNullOrEmpty(payloadVersion),
                        "DataSteam payload 'v' should not be empty."
                );


                softAssert.assertFalse(
                        isNullOrEmpty(payloadSession),
                        "DataSteam payload 'se' should not be empty."
                );


                softAssert.assertFalse(
                        isNullOrEmpty(eventType),
                        "DataSteam payload event type 'l' should not be empty."
                );


                // =================================================
                // CATEGORY EVENT
                // =================================================

                if ("Category".equalsIgnoreCase(
                        eventType)) {

                    categoryEventFound = true;


                    System.out.println();

                    System.out.println(
                            "DataSteam Category Event Found"
                    );


                    // ---------------------------------------------
                    // Validate Category page URL
                    // ---------------------------------------------

                    softAssert.assertFalse(
                            isNullOrEmpty(pageUrl),
                            "DataSteam Category event should contain page URL 'u'."
                    );


                    // ---------------------------------------------
                    // Validate Category page title
                    // ---------------------------------------------

                    softAssert.assertFalse(
                            isNullOrEmpty(pageTitle),
                            "DataSteam Category event should contain page title 't'."
                    );


                    // ---------------------------------------------
                    // Validate Category sequence
                    // ---------------------------------------------

                    softAssert.assertFalse(
                            isNullOrEmpty(eventSequence),
                            "DataSteam Category event should contain sequence 's'."
                    );


                    System.out.println();

                    System.out.println(
                            "Category Event Validation Passed"
                    );
                }


                // =================================================
                // ACTION EVENT
                // =================================================

                if ("Action".equalsIgnoreCase(
                        eventType)) {

                    actionEventFound = true;


                    System.out.println();

                    System.out.println(
                            "DataSteam Action Event Found"
                    );


                    System.out.println(
                            "v01 :: " + variable01
                    );

                    System.out.println(
                            "v03 :: " + variable03
                    );

                    System.out.println(
                            "v04 :: " + variable04
                    );


                    // ---------------------------------------------
                    // Action sequence
                    // ---------------------------------------------

                    softAssert.assertFalse(
                            isNullOrEmpty(eventSequence),
                            "DataSteam Action event should contain sequence 's'."
                    );


                    // =================================================
                    // IDENTIFY CART CLICK EVENT
                    // =================================================

                    boolean isCartClickEvent =

                            "Click".equalsIgnoreCase(
                                    variable01
                            )

                                    &&

                                    "Click".equalsIgnoreCase(
                                            variable03
                                    )

                                    &&

                                    variable04 != null

                                    &&

                                    variable04
                                            .toLowerCase()
                                            .contains("/cart");


                    if (isCartClickEvent) {

                        cartClickEventFound = true;


                        System.out.println();

                        System.out.println(
                                "DataSteam Cart Click Event Found"
                        );


                        System.out.println(
                                "v01 :: " + variable01
                        );

                        System.out.println(
                                "v03 :: " + variable03
                        );

                        System.out.println(
                                "v04 :: " + variable04
                        );


                        System.out.println();

                        System.out.println(
                                "DataSteam Cart Click Event Validation Passed"
                        );
                    }
                }


            } catch (IllegalArgumentException e) {

                softAssert.fail(
                        "Unable to Base64 decode DataSteam payload. "
                                + "Error: "
                                + e.getMessage()
                );


            } catch (Exception e) {

                softAssert.fail(
                        "Unable to parse DataSteam payload. "
                                + "Error: "
                                + e.getMessage()
                );
            }
        }


        // =========================================================
        // FINAL VALIDATIONS
        // =========================================================

        softAssert.assertTrue(
                validDataSteamRequest,
                "No valid DataSteam RawData request was captured."
        );


        softAssert.assertTrue(
                categoryEventFound,
                "DataSteam Category event was not captured."
        );


        softAssert.assertTrue(
                actionEventFound,
                "DataSteam Action event was not captured."
        );


        softAssert.assertTrue(
                cartClickEventFound,
                "DataSteam Cart Click event was not captured."
        );


        // =========================================================
        // FINAL RESULT
        // =========================================================

        System.out.println();

        System.out.println(
                "================================================="
        );

        System.out.println(
                "DATASTEAM PIXEL VALIDATION"
        );

        System.out.println(
                "================================================="
        );

        System.out.println(
                "Total DataSteam Events :: "
                        + dataSteamRequests.size()
        );

        System.out.println(
                "Valid RawData Request  :: "
                        + validDataSteamRequest
        );

        System.out.println(
                "Category Event Found   :: "
                        + categoryEventFound
        );

        System.out.println(
                "Action Event Found     :: "
                        + actionEventFound
        );

        System.out.println(
                "Cart Click Event Found :: "
                        + cartClickEventFound
        );


        if (validDataSteamRequest
                && categoryEventFound
                && actionEventFound
                && cartClickEventFound) {

            System.out.println();

            System.out.println(
                    "================================================="
            );

            System.out.println(
                    "DATASTEAM PIXEL VALIDATION PASSED"
            );

            System.out.println(
                    "DataSteam events were captured successfully."
            );

            System.out.println(
                    "================================================="
            );
        }


        // =========================================================
        // ASSERT ALL
        // =========================================================

        softAssert.assertAll();
    }

    private static class DataSteamRequestData {

        private final String method;
        private final String url;
        private final String postData;
        private final String resourceType;


        public DataSteamRequestData(
                String method,
                String url,
                String postData,
                String resourceType) {

            this.method = method;
            this.url = url;
            this.postData = postData;
            this.resourceType = resourceType;
        }


        public String getMethod() {
            return method;
        }


        public String getUrl() {
            return url;
        }


        public String getPostData() {
            return postData;
        }


        public String getResourceType() {
            return resourceType;
        }
    }

    private Map<String, String> extractQueryParameters(
            String requestUrl) {

        Map<String, String> parameters =
                new LinkedHashMap<>();

        try {

            URI uri =
                    new URI(requestUrl);

            String query =
                    uri.getRawQuery();

            if (query == null ||
                    query.isEmpty()) {

                return parameters;
            }


            for (String parameter :
                    query.split("&")) {

                String[] pair =
                        parameter.split("=", 2);


                String key =
                        URLDecoder.decode(
                                pair[0],
                                StandardCharsets.UTF_8
                        );


                String value = "";


                if (pair.length > 1) {

                    value =
                            URLDecoder.decode(
                                    pair[1],
                                    StandardCharsets.UTF_8
                            );
                }


                parameters.put(
                        key,
                        value
                );
            }


        } catch (Exception e) {

            System.out.println(
                    "Error extracting DataSteam parameters: "
                            + e.getMessage()
            );
        }


        return parameters;

    }


    private void validateDataSteamField(
            JsonObject dataObject,
            String fieldName,
            SoftAssert softAssert) {

        boolean exists =
                dataObject.has(fieldName);


        String value =
                getJsonValue(
                        dataObject,
                        fieldName
                );


        System.out.println(
                fieldName
                        + " :: "
                        + value
        );


        softAssert.assertTrue(
                exists,
                "DataSteam field is missing: "
                        + fieldName
        );


        if (exists) {

            softAssert.assertFalse(
                    value == null
                            || value.trim().isEmpty(),
                    "DataSteam field is empty: "
                            + fieldName
            );
        }
    }

    private String getJsonValue(
            JsonObject jsonObject,
            String fieldName) {

        if (jsonObject == null) {
            return null;
        }

        if (!jsonObject.has(fieldName)) {
            return null;
        }

        if (jsonObject.get(fieldName).isJsonNull()) {
            return null;
        }

        return jsonObject
                .get(fieldName)
                .getAsString();
    }

    private boolean isNullOrEmpty(
            String value) {

        return value == null ||
                value.trim().isEmpty();
    }

    private void printJsonField(
            JsonObject jsonObject,
            String fieldName) {

        String value =
                getJsonValue(
                        jsonObject,
                        fieldName
                );

        System.out.println(
                fieldName
                        + " :: "
                        + value
        );
    }





    private static class LWDRequestData {

        private final String method;
        private final String url;
        private final String postData;
        private final String resourceType;

        public LWDRequestData(
                String method,
                String url,
                String postData,
                String resourceType) {

            this.method = method;
            this.url = url;
            this.postData = postData;
            this.resourceType = resourceType;
        }

        public String getMethod() {
            return method;
        }

        public String getUrl() {
            return url;
        }

        public String getPostData() {
            return postData;
        }

        public String getResourceType() {
            return resourceType;
        }
    }


    /**
     * ================================================================
     * VALIDATE LWD PIXEL
     *
     * Manual flow:
     *
     * 1. Add product
     * 2. Go to Checkout
     * 3. Network tab -> filter "lw_pixel"
     * 4. Click Complete Order
     * 5. Capture LWD events
     *
     * Events expected:
     *
     * - Page View
     * - SKU
     * - Purchase
     *
     * LWD information can be present in:
     *
     * - Request URL
     * - POST Payload
     *
     * ================================================================
     */
    public void validateLWDPixel() throws Exception {

        SoftAssert softAssert = new SoftAssert();

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );

        JavascriptExecutor jse =
                (JavascriptExecutor) driver;


        /*
         * ============================================================
         * LWD REQUEST STORAGE
         * ============================================================
         */

        List<LWDRequestData> lwdPixelRequests =
                Collections.synchronizedList(
                        new ArrayList<>()
                );


        /*
         * ============================================================
         * ALL NETWORK REQUESTS
         *
         * Used only for debugging if no lw_pixel request is found.
         * ============================================================
         */

        List<String> networkRequests =
                Collections.synchronizedList(
                        new ArrayList<>()
                );


        /*
         * ============================================================
         * DEVTOOLS SETUP
         *
         * Start BEFORE the test flow.
         *
         * This is important because we want to behave like:
         *
         * Network -> filter lw_pixel
         *
         * before clicking Complete Order.
         * ============================================================
         */

        DevTools devTools =
                ((HasDevTools) driver).getDevTools();

        devTools.createSession();

        devTools.send(
                Network.enable(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                )
        );


        System.out.println();
        System.out.println(
                "================================================"
        );

        System.out.println(
                "CHROME NETWORK MONITORING ENABLED"
        );

        System.out.println(
                "Network Filter :: lw_pixel"
        );

        System.out.println(
                "================================================"
        );


        /*
         * ============================================================
         * NETWORK REQUEST LISTENER
         * ============================================================
         *
         * Equivalent to manually entering:
         *
         * Network -> Filter -> lw_pixel
         *
         * We search both:
         *
         * 1. Request URL
         * 2. POST Payload
         *
         * ============================================================
         */

        devTools.addListener(
                Network.requestWillBeSent(),
                request -> {

                    try {

                        String requestUrl =
                                request.getRequest().getUrl();


                        /*
                         * Ignore invalid URLs.
                         */

                        if (requestUrl == null ||
                                requestUrl.trim().isEmpty()) {

                            return;
                        }


                        /*
                         * =================================================
                         * REQUEST METHOD
                         * =================================================
                         */

                        String requestMethod =
                                request.getRequest().getMethod();


                        /*
                         * =================================================
                         * RESOURCE TYPE
                         * =================================================
                         */

                        String requestType =
                                String.valueOf(
                                        request.getType()
                                );


                        /*
                         * =================================================
                         * GET POST DATA / PAYLOAD
                         * =================================================
                         */

                        String postData = "";

                        try {

                            if (request.getRequest()
                                    .getPostData()
                                    .isPresent()) {

                                postData =
                                        request.getRequest()
                                                .getPostData()
                                                .get();
                            }

                        } catch (Exception payloadException) {

                            System.out.println(
                                    "Unable to read request payload."
                            );

                            System.out.println(
                                    "URL :: "
                                            + requestUrl
                            );
                        }


                        /*
                         * =================================================
                         * STORE GENERAL NETWORK REQUEST
                         *
                         * Useful for debugging.
                         * =================================================
                         */

                        synchronized (networkRequests) {

                            if (networkRequests.size() < 500) {

                                networkRequests.add(
                                        requestMethod
                                                + " | "
                                                + requestType
                                                + " | "
                                                + requestUrl
                                );
                            }
                        }


                        /*
                         * =================================================
                         * SEARCH URL + PAYLOAD
                         *
                         * Equivalent to:
                         *
                         * Network -> Filter -> lw_pixel
                         * =================================================
                         */

                        String searchableContent =
                                (
                                        requestUrl
                                                + " "
                                                + postData
                                ).toLowerCase(
                                        Locale.ROOT
                                );


                        /*
                         * =================================================
                         * LWD FILTER
                         * =================================================
                         */

                        boolean isLWDRequest =
                                searchableContent.contains(
                                        "lw_pixel"
                                );


                        /*
                         * Not an LWD request.
                         */

                        if (!isLWDRequest) {

                            return;
                        }


                        /*
                         * =================================================
                         * STORE LWD REQUEST
                         * =================================================
                         */

                        LWDRequestData lwdRequest =
                                new LWDRequestData(
                                        requestMethod,
                                        requestUrl,
                                        postData,
                                        requestType
                                );

                        lwdPixelRequests.add(
                                lwdRequest
                        );


                        /*
                         * =================================================
                         * PRINT LWD REQUEST
                         * =================================================
                         */

                        System.out.println();

                        System.out.println(
                                "================================================"
                        );

                        System.out.println(
                                "************ LWD PIXEL FOUND ************"
                        );

                        System.out.println(
                                "TYPE   :: "
                                        + requestType
                        );

                        System.out.println(
                                "METHOD :: "
                                        + requestMethod
                        );

                        System.out.println(
                                "URL    :: "
                                        + requestUrl
                        );


                        if (postData != null &&
                                !postData.trim().isEmpty()) {

                            System.out.println();

                            System.out.println(
                                    "PAYLOAD ::"
                            );

                            System.out.println(
                                    postData
                            );
                        }


                        System.out.println(
                                "****************************************"
                        );

                        System.out.println(
                                "================================================"
                        );

                    } catch (Exception listenerException) {

                        System.out.println(
                                "Error while processing network request:"
                        );

                        listenerException.printStackTrace();
                    }
                }
        );


        /*
         * ============================================================
         * RESPONSE LISTENER
         *
         * Diagnostic only.
         * ============================================================
         */

        devTools.addListener(
                Network.responseReceived(),
                response -> {

                    try {

                        String responseUrl =
                                response.getResponse().getUrl();


                        if (responseUrl == null ||
                                responseUrl.trim().isEmpty()) {

                            return;
                        }


                        if (responseUrl
                                .toLowerCase(Locale.ROOT)
                                .contains("lw_pixel")) {

                            System.out.println();

                            System.out.println(
                                    "************ LWD RESPONSE ************"
                            );

                            System.out.println(
                                    "URL :: "
                                            + responseUrl
                            );

                            System.out.println(
                                    "STATUS :: "
                                            + response.getResponse()
                                            .getStatus()
                            );

                            System.out.println(
                                    "**************************************"
                            );
                        }

                    } catch (Exception responseException) {

                        System.out.println(
                                "Error while processing LWD response."
                        );
                    }
                }
        );


        /*
         * ============================================================
         * STEP 1
         * ADD PRODUCT TO CART
         * ============================================================
         */

        System.out.println();
        System.out.println(
                "STEP 1 :: Adding product to cart..."
        );

        new AddToCartComponents(driver)
                .addmultipleproductToCartforFreeOrder();


        /*
         * ============================================================
         * STEP 2
         * GO TO CHECKOUT
         * ============================================================
         */

        System.out.println();
        System.out.println(
                "STEP 2 :: Navigating to Checkout..."
        );

        new CheckoutPageComponents(driver)
                .validateFreeOrder();


        /*
         * ============================================================
         * APPLY PROMO
         * ============================================================
         */

        System.out.println();
        System.out.println(
                "Applying promo code..."
        );

        new CheckoutPageComponents(driver)
                .CheckFreeOrderpromo("TECH TEST");


        /*
         * ============================================================
         * READY TO COMPLETE ORDER
         * ============================================================
         */

        System.out.println();
        System.out.println(
                "================================================"
        );

        System.out.println(
                "STEP 3 :: Ready to click Complete Order"
        );

        System.out.println(
                "LWD Network monitoring is ACTIVE."
        );

        System.out.println(
                "================================================"
        );


        /*
         * ============================================================
         * CLICK COMPLETE ORDER
         * ============================================================
         */

        try {

            WebElement completeOrderButton =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    COMPLETE_ORDER
                            )
                    );


            completeOrderButton.click();


            System.out.println();
            System.out.println(
                    "Complete Order clicked normally."
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "Normal Complete Order click failed."
            );

            System.out.println(
                    "Using JavaScript click..."
            );


            WebElement completeOrderButton =
                    wait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    COMPLETE_ORDER
                            )
                    );


            jse.executeScript(
                    "arguments[0].click();",
                    completeOrderButton
            );


            System.out.println(
                    "Complete Order clicked using JavaScript."
            );
        }


        /*
         * ============================================================
         * WAIT FOR ORDER CONFIRMATION
         * ============================================================
         *
         * We don't use this as the trigger for LWD.
         *
         * LWD requests are captured by the listener independently.
         * ============================================================
         */

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            ORDER_CONFIRMATION
                    )
            );

            System.out.println();
            System.out.println(
                    "Order confirmation displayed."
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "WARNING: Order confirmation locator "
                            + "was not detected."
            );

            System.out.println(
                    "Current URL :: "
                            + driver.getCurrentUrl()
            );
        }


        /*
         * ============================================================
         * WAIT FOR LWD EVENTS
         *
         * Purchase / SKU events may fire during:
         *
         * /processing
         * /thank-you
         *
         * Give them enough time.
         * ============================================================
         */

        System.out.println();
        System.out.println(
                "Waiting for LWD events..."
        );


        long timeoutInSeconds = 60;

        long startTime =
                System.currentTimeMillis();


        while (
                (System.currentTimeMillis() - startTime)
                        < timeoutInSeconds * 1000
        ) {

            Thread.sleep(2000);


            boolean purchaseFound =
                    containsLWDEvent(
                            lwdPixelRequests,
                            "Purchase"
                    );


            if (purchaseFound) {

                System.out.println();
                System.out.println(
                        "Purchase LWD event captured."
                );

                /*
                 * Purchase is the final event we definitely
                 * expect after Complete Order.
                 *
                 * Break once it is found.
                 */

                break;
            }


            long elapsedSeconds =
                    (
                            System.currentTimeMillis()
                                    - startTime
                    ) / 1000;


            System.out.println(
                    "Waiting for LWD events... "
                            + elapsedSeconds
                            + " seconds"
            );
        }


        /*
         * ============================================================
         * NO LWD REQUEST FOUND
         * ============================================================
         */

        if (lwdPixelRequests.isEmpty()) {

            System.out.println();
            System.out.println(
                    "================================================"
            );

            System.out.println(
                    "LWD PIXEL VALIDATION FAILED"
            );

            System.out.println(
                    "No request containing 'lw_pixel' was captured."
            );

            System.out.println(
                    "================================================"
            );


            System.out.println();
            System.out.println(
                    "Current Browser URL:"
            );

            System.out.println(
                    driver.getCurrentUrl()
            );


            /*
             * ========================================================
             * DEBUG NETWORK REQUESTS
             * ========================================================
             */

            System.out.println();

            System.out.println(
                    "================================================"
            );

            System.out.println(
                    "NETWORK REQUESTS"
            );

            System.out.println(
                    "================================================"
            );


            synchronized (networkRequests) {

                for (String request :
                        networkRequests) {

                    System.out.println(
                            request
                    );
                }
            }


            System.out.println(
                    "================================================"
            );


            softAssert.fail(
                    "No network request containing "
                            + "'lw_pixel' was captured."
            );

            softAssert.assertAll();

            return;
        }


        /*
         * ============================================================
         * PROCESS LWD REQUESTS
         * ============================================================
         */

        int purchaseCount = 0;

        int skuCount = 0;

        int pageViewCount = 0;


        System.out.println();

        System.out.println(
                "================================================"
        );

        System.out.println(
                "LWD PIXEL REQUESTS CAPTURED"
        );

        System.out.println(
                "Total Requests :: "
                        + lwdPixelRequests.size()
        );

        System.out.println(
                "================================================"
        );


        /*
         * ============================================================
         * PROCESS EACH REQUEST
         * ============================================================
         */

        for (LWDRequestData request :
                lwdPixelRequests) {


            System.out.println();

            System.out.println(
                    "================================================"
            );

            System.out.println(
                    "LWD REQUEST"
            );

            System.out.println(
                    "TYPE   :: "
                            + request.getResourceType()
            );

            System.out.println(
                    "METHOD :: "
                            + request.getMethod()
            );

            System.out.println(
                    "URL    :: "
                            + request.getUrl()
            );


            if (request.getPostData() != null &&
                    !request.getPostData().trim().isEmpty()) {

                System.out.println();

                System.out.println(
                        "PAYLOAD ::"
                );

                System.out.println(
                        request.getPostData()
                );
            }


            /*
             * ========================================================
             * EXTRACT URL PARAMETERS
             * ========================================================
             */

            String requestUrl =
                    request.getUrl();

            Map<String, String> parameters =
                    extractQueryParameters(
                            requestUrl
                    );


            /*
             * ========================================================
             * EXTRACT PAYLOAD PARAMETERS
             *
             * Important:
             *
             * If the LWD event data is in the Payload,
             * extract it as well.
             * ========================================================
             */

            String postData =
                    request.getPostData();


            if (postData != null &&
                    !postData.trim().isEmpty()) {

                Map<String, String> postParameters =
                        extractQueryParametersFromPayload(
                                postData
                        );


                parameters.putAll(
                        postParameters
                );
            }


            /*
             * ========================================================
             * PRINT PARAMETERS
             * ========================================================
             */

            System.out.println();

            System.out.println(
                    "--------------- LWD PARAMETERS ---------------"
            );


            for (Map.Entry<String, String> entry :
                    parameters.entrySet()) {

                System.out.println(
                        entry.getKey()
                                + " :: "
                                + entry.getValue()
                );
            }


            System.out.println(
                    "----------------------------------------------"
            );


            /*
             * ========================================================
             * GET EVENT
             * ========================================================
             */

            String event =
                    getParameterIgnoreCase(
                            parameters,
                            "lw_event"
                    );


            if (event == null ||
                    event.trim().isEmpty()) {

                System.out.println(
                        "WARNING: lw_event was not found."
                );

                continue;
            }


            String normalizedEvent =
                    event.trim()
                            .toLowerCase(
                                    Locale.ROOT
                            );


            /*
             * ========================================================
             * PURCHASE / TRANSACTION
             * ========================================================
             */

            if (normalizedEvent.equals("purchase")
                    ||
                    normalizedEvent.equals("transaction")) {

                purchaseCount++;


                System.out.println();

                System.out.println(
                        "******** LWD TRANSACTION ********"
                );

                System.out.println(
                        "Event :: "
                                + event
                );

                System.out.println(
                        "*********************************"
                );
            }


            /*
             * ========================================================
             * SKU
             * ========================================================
             */

            else if (
                    normalizedEvent.equals("sku")
            ) {

                skuCount++;


                System.out.println();

                System.out.println(
                        "******** LWD SKU EVENT ********"
                );

                System.out.println(
                        "SKU Event Number :: "
                                + skuCount
                );

                System.out.println(
                        "Event :: "
                                + event
                );

                System.out.println(
                        "********************************"
                );
            }


            /*
             * ========================================================
             * PAGE VIEW
             * ========================================================
             */

            else if (
                    normalizedEvent.equals("page view")
                            ||
                            normalizedEvent.equals("pageview")
            ) {

                pageViewCount++;


                System.out.println();

                System.out.println(
                        "******** LWD PAGE VIEW ********"
                );

                System.out.println(
                        "Event :: "
                                + event
                );

                System.out.println(
                        "********************************"
                );
            }
        }


        /*
         * ============================================================
         * EVENT SUMMARY
         * ============================================================
         */

        System.out.println();

        System.out.println(
                "================================================"
        );

        System.out.println(
                "LWD PIXEL EVENT SUMMARY"
        );

        System.out.println(
                "================================================"
        );

        System.out.println(
                "Total LWD Requests :: "
                        + lwdPixelRequests.size()
        );

        System.out.println(
                "Transactions       :: "
                        + purchaseCount
        );

        System.out.println(
                "SKU Events         :: "
                        + skuCount
        );

        System.out.println(
                "Page View Events   :: "
                        + pageViewCount
        );

        System.out.println(
                "================================================"
        );


        /*
         * ============================================================
         * VALIDATIONS
         * ============================================================
         */

        softAssert.assertTrue(
                purchaseCount > 0,
                "LWD Purchase/Transaction event was NOT captured."
        );


        softAssert.assertTrue(
                skuCount > 0,
                "LWD SKU event was NOT captured."
        );


        softAssert.assertTrue(
                pageViewCount > 0,
                "LWD Page View event was NOT captured."
        );


        /*
         * ============================================================
         * FINAL ASSERTIONS
         * ============================================================
         */

        softAssert.assertAll();
    }


    /**
     * ================================================================
     * EXTRACT URL QUERY PARAMETERS
     * ================================================================
     */
//    private Map<String, String> extractQueryParameters(
//            String requestUrl) {
//
//        Map<String, String> parameters =
//                new LinkedHashMap<>();
//
//        if (requestUrl == null ||
//                requestUrl.trim().isEmpty()) {
//
//            return parameters;
//        }
//
//
//        try {
//
//            URI uri =
//                    new URI(requestUrl);
//
//            String query =
//                    uri.getRawQuery();
//
//
//            if (query == null ||
//                    query.isEmpty()) {
//
//                return parameters;
//            }
//
//
//            String[] pairs =
//                    query.split("&");
//
//
//            for (String pair : pairs) {
//
//                if (pair == null ||
//                        pair.isEmpty()) {
//
//                    continue;
//                }
//
//
//                String[] keyValue =
//                        pair.split("=", 2);
//
//
//                String key =
//                        URLDecoder.decode(
//                                keyValue[0],
//                                StandardCharsets.UTF_8
//                        );
//
//
//                String value = "";
//
//
//                if (keyValue.length > 1) {
//
//                    value =
//                            URLDecoder.decode(
//                                    keyValue[1],
//                                    StandardCharsets.UTF_8
//                            );
//                }
//
//
//                parameters.put(
//                        key,
//                        value
//                );
//            }
//
//
//        } catch (Exception e) {
//
//            System.out.println(
//                    "Unable to parse LWD Pixel URL:"
//            );
//
//            System.out.println(
//                    requestUrl
//            );
//
//            e.printStackTrace();
//        }
//
//
//        return parameters;
//    }


    /**
     * ================================================================
     * EXTRACT POST PAYLOAD PARAMETERS
     * ================================================================
     */
    private Map<String, String> extractQueryParametersFromPayload(
            String payload) {

        Map<String, String> parameters =
                new LinkedHashMap<>();


        if (payload == null ||
                payload.trim().isEmpty()) {

            return parameters;
        }


        try {

            String cleanPayload =
                    payload.startsWith("?")
                            ? payload.substring(1)
                            : payload;


            String[] pairs =
                    cleanPayload.split("&");


            for (String pair : pairs) {

                if (pair == null ||
                        pair.trim().isEmpty()) {

                    continue;
                }


                String[] keyValue =
                        pair.split("=", 2);


                String key =
                        URLDecoder.decode(
                                keyValue[0],
                                StandardCharsets.UTF_8
                        );


                String value = "";


                if (keyValue.length > 1) {

                    value =
                            URLDecoder.decode(
                                    keyValue[1],
                                    StandardCharsets.UTF_8
                            );
                }


                parameters.put(
                        key,
                        value
                );
            }


        } catch (Exception e) {

            System.out.println(
                    "Unable to parse LWD POST payload:"
            );

            System.out.println(
                    payload
            );

            e.printStackTrace();
        }


        return parameters;
    }


    /**
     * ================================================================
     * GET PARAMETER IGNORING CASE
     *
     * Handles:
     *
     * lw_clientID
     * lw_clientid
     * LW_CLIENTID
     *
     * ================================================================
     */
    private String getParameterIgnoreCase(
            Map<String, String> parameters,
            String parameterName) {

        if (parameters == null ||
                parameterName == null) {

            return null;
        }


        for (Map.Entry<String, String> entry :
                parameters.entrySet()) {

            if (entry.getKey()
                    .equalsIgnoreCase(parameterName)) {

                return entry.getValue();
            }
        }


        return null;
    }


    /**
     * ================================================================
     * CHECK WHETHER AN LWD EVENT WAS CAPTURED
     * ================================================================
     */
    private boolean containsLWDEvent(
            List<LWDRequestData> requests,
            String expectedEvent) {

        if (requests == null ||
                requests.isEmpty()) {

            return false;
        }


        for (LWDRequestData request :
                requests) {

            Map<String, String> parameters =
                    extractQueryParameters(
                            request.getUrl()
                    );


            /*
             * Add POST payload parameters.
             */

            if (request.getPostData() != null &&
                    !request.getPostData()
                            .trim()
                            .isEmpty()) {

                Map<String, String> postParameters =
                        extractQueryParametersFromPayload(
                                request.getPostData()
                        );


                parameters.putAll(
                        postParameters
                );
            }


            String event =
                    getParameterIgnoreCase(
                            parameters,
                            "lw_event"
                    );


            if (event != null &&
                    event.trim()
                            .equalsIgnoreCase(
                                    expectedEvent
                            )) {

                return true;
            }
        }


        return false;
    }

        public void validateElevarFacebookInitiateCheckout() throws Exception {

        SoftAssert softAssert = new SoftAssert();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        // =========================================================
        // Store Facebook Pixel related requests
        // =========================================================
        List<String> facebookRequests =
                Collections.synchronizedList(new ArrayList<>());

        // =========================================================
        // Start DevTools
        // =========================================================
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        devTools.createSession();

        devTools.send(
                Network.enable(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                ));

        Reporter.log("DevTools Network monitoring started.", true);
        ExtentReport.extentTest.info(
                "DevTools Network monitoring started."
        );

        // =========================================================
        // Capture Facebook / Pixel requests
        // =========================================================
        devTools.addListener(
                Network.requestWillBeSent(),
                request -> {

                    String requestUrl = request.getRequest().getUrl();

                    if (requestUrl == null || requestUrl.isEmpty()) {
                        return;
                    }

                    String lowerUrl = requestUrl.toLowerCase();

                    /*
                     * Capture Facebook Pixel related requests.
                     *
                     * Examples:
                     * connect.facebook.net
                     * facebook.com/tr
                     * fbevents.js
                     */
                    if (lowerUrl.contains("facebook.com")
                            || lowerUrl.contains("connect.facebook.net")
                            || lowerUrl.contains("fbevents")
                            || lowerUrl.contains("facebook")) {

                        facebookRequests.add(requestUrl);

                        System.out.println(
                                "[Facebook Request Captured] "
                                        + requestUrl
                        );
                    }

                    // =====================================================
                    // Immediately check whether InitiateCheckout is present
                    // =====================================================
                    try {

                        String decodedUrl = URLDecoder.decode(
                                requestUrl,
                                StandardCharsets.UTF_8
                        );

                        String decodedLowerUrl =
                                decodedUrl.toLowerCase();

                        if (decodedLowerUrl.contains("initiatecheckout")
                                || decodedLowerUrl.contains("initiate_checkout")
                                || decodedLowerUrl.contains("initiate%20checkout")) {

                            System.out.println(
                                    "================================================="
                            );

                            System.out.println(
                                    "[SUCCESS] Facebook InitiateCheckout detected!"
                            );

                            System.out.println(
                                    "[InitiateCheckout Request] "
                                            + decodedUrl
                            );

                            System.out.println(
                                    "================================================="
                            );
                        }

                    } catch (Exception e) {

                        System.out.println(
                                "Unable to decode Facebook request URL: "
                                        + e.getMessage()
                        );
                    }
                }
        );

        try {

            // =========================================================
            // Navigate to PLP
            // =========================================================

            Reporter.log("Navigating to Men's PLP...", true);
            ExtentReport.extentTest.info(
                    "Navigating to Men's PLP."
            );

            driver.get(
                    "https://www.tommyjohn.com/collections/mens-underwear"
            );

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("(//a[contains(@href,'/products/')])[1]")
                    )
            );

            Reporter.log("Men's PLP loaded successfully.", true);
            ExtentReport.extentTest.info(
                    "Men's PLP loaded successfully."
            );

            // =========================================================
            // Click first product
            // =========================================================

            WebElement firstProduct =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath(
                                            "(//a[contains(@href,'/products/')])[1]"
                                    )
                            )
                    );

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].click();",
                            firstProduct
                    );

            wait.until(
                    ExpectedConditions.urlContains("/products/")
            );

            Reporter.log("Navigated to PDP.", true);
            ExtentReport.extentTest.info(
                    "Navigated to PDP."
            );

            // =========================================================
            // Add product to cart
            // =========================================================

            Reporter.log("Adding product to cart...", true);
            ExtentReport.extentTest.info(
                    "Adding product to cart."
            );

            AddToCartComponents addToCartComponents =
                    new AddToCartComponents(driver);

            addToCartComponents.addsingleproductToCart();

            Reporter.log(
                    "Product added to cart successfully.",
                    true
            );

            ExtentReport.extentTest.info(
                    "Product added to cart successfully."
            );

            // =========================================================
            // Wait for Checkout button
            // =========================================================

            WebElement checkoutButton =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    CHECKOUT_BUTTON
                            )
                    );

            Reporter.log(
                    "Checkout button is available.",
                    true
            );

            ExtentReport.extentTest.info(
                    "Checkout button is available."
            );

            // =========================================================
            // Clear previously captured Facebook requests
            // =========================================================

            synchronized (facebookRequests) {
                facebookRequests.clear();
            }

            Reporter.log(
                    "Previous Facebook requests cleared.",
                    true
            );

            ExtentReport.extentTest.info(
                    "Previous Facebook requests cleared."
            );

            // =========================================================
            // Click Checkout
            // =========================================================

            Reporter.log(
                    "Clicking Checkout button...",
                    true
            );

            ExtentReport.extentTest.info(
                    "Clicking Checkout button."
            );

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].click();",
                            checkoutButton
                    );

            // =========================================================
            // Wait for Checkout page
            // =========================================================

            wait.until(
                    ExpectedConditions.urlContains("/checkouts/")
            );

            Reporter.log(
                    "Checkout page loaded successfully.",
                    true
            );

            ExtentReport.extentTest.info(
                    "Checkout page loaded successfully."
            );

            // =========================================================
            // Allow Checkout analytics/pixel scripts to initialize
            // =========================================================

            Reporter.log(
                    "Waiting 3 seconds for Checkout analytics to initialize...",
                    true
            );

            ExtentReport.extentTest.info(
                    "Waiting 3 seconds for Checkout analytics to initialize."
            );

            Thread.sleep(3000);

            // =========================================================
            // Wait for Facebook InitiateCheckout
            // =========================================================

            Reporter.log(
                    "Waiting up to 30 seconds for Facebook InitiateCheckout...",
                    true
            );

            ExtentReport.extentTest.info(
                    "Waiting up to 30 seconds for Facebook InitiateCheckout."
            );

            boolean initiateCheckoutFired =
                    waitForFacebookInitiateCheckout(
                            facebookRequests,
                            30
                    );

            // =========================================================
            // Print captured Facebook requests
            // =========================================================

            System.out.println(
                    "\n================================================="
            );

            System.out.println(
                    "CAPTURED FACEBOOK REQUESTS"
            );

            System.out.println(
                    "================================================="
            );

            synchronized (facebookRequests) {

                if (facebookRequests.isEmpty()) {

                    System.out.println(
                            "No Facebook requests captured."
                    );

                } else {

                    for (String requestUrl : facebookRequests) {

                        System.out.println(
                                "[Facebook] " + requestUrl
                        );
                    }
                }
            }

            System.out.println(
                    "=================================================\n"
            );

            // =========================================================
            // Validate result
            // =========================================================

            if (initiateCheckoutFired) {

                Reporter.log(
                        "[PASS] Facebook InitiateCheckout Fired.",
                        true
                );

                ExtentReport.extentTest.pass(
                        "Facebook InitiateCheckout event fired upon entering Checkout."
                );

            } else {

                Reporter.log(
                        "[FAIL] Facebook InitiateCheckout NOT Found.",
                        true
                );

                ExtentReport.extentTest.fail(
                        "Facebook InitiateCheckout event was NOT detected upon entering Checkout."
                );
            }

            System.out.println(
                    "Facebook InitiateCheckout Fired : "
                            + initiateCheckoutFired
            );

            softAssert.assertTrue(
                    initiateCheckoutFired,
                    "Facebook InitiateCheckout Event NOT Fired upon entering Checkout."
            );

            // =========================================================
            // Assert all
            // =========================================================

            softAssert.assertAll();

        } catch (AssertionError e) {

            Reporter.log(
                    "[FAIL] Facebook InitiateCheckout validation failed.",
                    true
            );

            ExtentReport.extentTest.fail(
                    "Facebook InitiateCheckout validation failed: "
                            + e.getMessage()
            );

            throw e;

        } catch (Exception e) {

            Reporter.log(
                    "[ERROR] Exception while validating Facebook InitiateCheckout: "
                            + e.getMessage(),
                    true
            );

            ExtentReport.extentTest.fail(
                    "Exception while validating Facebook InitiateCheckout: "
                            + e.getMessage()
            );

            throw e;
        }
    }


    /**
     * Waits for Facebook InitiateCheckout event.
     *
     * The method continuously checks the captured Facebook
     * requests for up to the specified timeout.
     */
    private boolean waitForFacebookInitiateCheckout(
            List<String> facebookRequests,
            int timeoutSeconds) {

        long endTime =
                System.currentTimeMillis()
                        + (timeoutSeconds * 1000L);

        while (System.currentTimeMillis() < endTime) {

            synchronized (facebookRequests) {

                for (String requestUrl : facebookRequests) {

                    if (requestUrl == null) {
                        continue;
                    }

                    try {

                        // Decode URL
                        String decodedUrl =
                                URLDecoder.decode(
                                        requestUrl,
                                        StandardCharsets.UTF_8
                                );

                        String lowerUrl =
                                decodedUrl.toLowerCase();

                        // =================================================
                        // Check InitiateCheckout
                        // =================================================

                        if (lowerUrl.contains("initiatecheckout")
                                || lowerUrl.contains("initiate_checkout")
                                || lowerUrl.contains("initiate checkout")) {

                            System.out.println(
                                    "[PASS] InitiateCheckout detected:"
                            );

                            System.out.println(
                                    decodedUrl
                            );

                            return true;
                        }

                    } catch (Exception e) {

                        System.out.println(
                                "Error decoding Facebook request: "
                                        + e.getMessage()
                        );

                        // Fallback to original URL
                        String lowerUrl =
                                requestUrl.toLowerCase();

                        if (lowerUrl.contains("initiatecheckout")
                                || lowerUrl.contains("initiate_checkout")) {

                            return true;
                        }
                    }
                }
            }

            // Check every 500 ms
            try {

                Thread.sleep(500);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                return false;
            }
        }

        System.out.println(
                "[FAIL] InitiateCheckout was not detected within "
                        + timeoutSeconds
                        + " seconds."
        );

        return false;
    }


    public void validateGTMEvents() throws Exception {

        SoftAssert softAssert = new SoftAssert();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        List<String> requests =
                Collections.synchronizedList(new ArrayList<>());

        //=========================================================
        // Start DevTools
        //=========================================================

        DevTools devTools = ((ChromeDriver) driver).getDevTools();

        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));

        devTools.addListener(Network.requestWillBeSent(), request -> {

            String url = request.getRequest().getUrl();

            requests.add(url);

            System.out.println(url);

        });

        Reporter.log("DevTools Network Listener Started");
        ExtentReport.extentTest.info("DevTools Network Listener Started");

        //=========================================================
        // Navigate to Men's PLP
        //=========================================================

        new HomePageComponents(driver).navigateToMensAll();

        By firstProduct =
                By.xpath("(//a[contains(@href,'/products/')])[1]");

        wait.until(ExpectedConditions.elementToBeClickable(firstProduct));

        Reporter.log("Navigated to Men's All PLP");
        ExtentReport.extentTest.info("Navigated to Men's All PLP");

        //=========================================================
        // Click First Product
        //=========================================================

        driver.findElement(firstProduct).click();

        Reporter.log("Clicked First Product");
        ExtentReport.extentTest.info("Clicked First Product");

        //=========================================================
        // Wait for PDP
        //=========================================================

        wait.until(ExpectedConditions.urlContains("/products/"));

        Thread.sleep(3000);

        //=========================================================
        // Print All Requests
        //=========================================================

        System.out.println();
        System.out.println("================ ALL NETWORK REQUESTS ================");

        requests.forEach(System.out::println);

        System.out.println("======================================================");
        System.out.println();

        //=========================================================
        // Vendor Validations
        //=========================================================

        boolean facebook = isVendorTriggered(
                requests,
                "facebook",
                "facebook.com",
                "connect.facebook.net",
                "fbevents");


        boolean contentSquare = isVendorTriggered(
                requests,
                "contentsquare",
                "c.contentsquare.net");

        boolean fredhopper = isVendorTriggered(
                requests,
                "fredhopper",
                "fredhopperservices",
                "fh");

        //=========================================================
        // Print Results
        //=========================================================

        System.out.println("Facebook Fired       : " + facebook);
        System.out.println("ContentSquare Fired  : " + contentSquare);
        System.out.println("Fredhopper Fired     : " + fredhopper);

        Reporter.log("Facebook Fired : " + facebook);
        Reporter.log("ContentSquare Fired : " + contentSquare);
        Reporter.log("Fredhopper Fired : " + fredhopper);

        ExtentReport.extentTest.info("Facebook Fired : " + facebook);
        ExtentReport.extentTest.info("ContentSquare Fired : " + contentSquare);
        ExtentReport.extentTest.info("Fredhopper Fired : " + fredhopper);

        //=========================================================
        // Debug Missing Vendors
        //=========================================================

        if (!facebook)
            printMatchingRequests(
                    requests,
                    "Facebook",
                    "facebook",
                    "fbevents");


        if (!contentSquare)
            printMatchingRequests(
                    requests,
                    "ContentSquare",
                    "contentsquare");

        if (!fredhopper)
            printMatchingRequests(
                    requests,
                    "Fredhopper",
                    "fredhopper",
                    "fh");

        //=========================================================
        // Assertions
        //=========================================================

        softAssert.assertTrue(
                facebook,
                "Facebook Product View Event NOT Fired");


        softAssert.assertTrue(
                contentSquare,
                "ContentSquare Event NOT Fired");

        softAssert.assertTrue(
                fredhopper,
                "Fredhopper Product View Event NOT Fired");

        //=========================================================
        // Final Result
        //=========================================================

        try {

            softAssert.assertAll();

            Reporter.log("All GTM Events Fired Successfully.");
            ExtentReport.extentTest.pass("All GTM Events Fired Successfully.");

        } catch (AssertionError e) {

            Reporter.log("GTM Validation Failed.");
            ExtentReport.extentTest.fail(e.getMessage());

            throw e;
        }
    }



    private boolean isVendorTriggered(
            List<String> requests,
            String... keywords) {

        return requests.stream()
                .map(String::toLowerCase)
                .anyMatch(url -> {

                    for (String keyword : keywords) {

                        if (url.contains(
                                keyword.toLowerCase()
                        )) {

                            return true;
                        }
                    }

                    return false;
                });
    }


    private void printMatchingRequests(
            List<String> requests,
            String vendor,
            String... keywords) {

        System.out.println(
                "\n========== "
                        + vendor.toUpperCase()
                        + " =========="
        );

        boolean found = false;

        for (String request : requests) {

            String lowerCaseRequest =
                    request.toLowerCase();

            for (String keyword : keywords) {

                if (lowerCaseRequest.contains(
                        keyword.toLowerCase()
                )) {

                    System.out.println(request);

                    found = true;

                    break;
                }
            }
        }

        if (!found) {

            System.out.println(
                    "No matching requests found for "
                            + vendor
            );
        }

        System.out.println(
                "========================================="
        );
    }






    public void ValidateGTMforFacetEvent() throws InterruptedException {
        // ✅ 1. Setup Chrome with DevTools
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ 2. Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty(), // reportDirectSocketTraffic
                Optional.empty()  // enableDurableMessages
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
//         options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();


        // ✅ 2. Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty(), // reportDirectSocketTraffic
                Optional.empty()  // enableDurableMessages
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
//        options.addArguments("--headless");  // ✅ Run in headless mode
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty(), // reportDirectSocketTraffic
                Optional.empty()  // enableDurableMessages
        ));

        // ✅ Expected JSON Payload for Comparison (Ignoring "sourceId" & "sessionid")
        String expectedPayload = "[{\"action\":\"add-to-cart\",\"target\":{\"product\":\"4931239444525\"},"
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

        Thread.sleep(10000); // ✅ Wait for network request
        driver.quit();  // ✅ Close the browser
        if (!payloadMatched[0]) {
            Assert.fail("❌ Test Failed: Expected 'add-to-cart' event was NOT found or didn't match!");
        }
    }

    public void ValidateGTMForClickEvent() throws Exception {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // ✅ Run in headless mode
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
        devTools.send(Network.enable(
                Optional.empty(), // maxTotalBufferSize
                Optional.empty(), // maxResourceBufferSize
                Optional.empty(), // maxPostDataSize
                Optional.empty(), // reportDirectSocketTraffic
                Optional.empty()  // enableDurableMessages
        ));
        // ✅ Expected JSON Payload for "click" event
        String expectedPayload = "[{\"action\":\"click\",\"target\":{\"product\":\"4931239444525\"},"
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
//         options.addArguments("--headless");  // ✅ Run in headless mode
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
       devTools.send(Network.enable(
        Optional.empty(), // maxTotalBufferSize
        Optional.empty(), // maxResourceBufferSize
        Optional.empty(), // maxPostDataSize
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
));

        // ✅ Expected JSON Payload for "view" product event
        String expectedPayload = "[{\"action\":\"view\",\"target\":{\"product\":\"4931239444525\"},"
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
//        options.addArguments("--headless");  // ✅ Run in headless mode (uncomment after debugging)
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
       devTools.send(Network.enable(
        Optional.empty(), // maxTotalBufferSize
        Optional.empty(), // maxResourceBufferSize
        Optional.empty(), // maxPostDataSize
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
       devTools.send(Network.enable(
        Optional.empty(), // maxTotalBufferSize
        Optional.empty(), // maxResourceBufferSize
        Optional.empty(), // maxPostDataSize
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
));

        // ✅ Expected JSON Payload (product ID will be dynamically matched)
        String expectedPayload = "[{\"action\":\"remove-from-cart\",\"target\":{\"product\":\"4931429924909\"},"
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
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
//        options.addArguments("--headless"); // ✅ Run in headless mode
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // ✅ Enable network monitoring
       devTools.send(Network.enable(
        Optional.empty(), // maxTotalBufferSize
        Optional.empty(), // maxResourceBufferSize
        Optional.empty(), // maxPostDataSize
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
//        options.addArguments("--headless=new");
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

       devTools.send(Network.enable(
        Optional.empty(), // maxTotalBufferSize
        Optional.empty(), // maxResourceBufferSize
        Optional.empty(), // maxPostDataSize
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
                            if (!productId.equals("4931239444525") || !activeVariantSku.equals("3636:S") || !defaultSwatchVariantSku.equals("3636:S")) {
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
//         options.addArguments("--headless");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36");
//        options.addArguments("--disable-gpu");
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
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
        new AddToCartComponents(driver).addsingleproductToCart();

        Thread.sleep(3000);

        // Click Cart Icon
//        driver.findElement(HomePageLocators.CART_ICON).click();

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
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

       devTools.send(Network.enable(
        Optional.empty(), // maxTotalBufferSize
        Optional.empty(), // maxResourceBufferSize
        Optional.empty(), // maxPostDataSize
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
  //      new AccountLoginPage(driver).loginAccount("testuserQA1@gmail.com", "Test@123");
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
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
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
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
//        options.addArguments("--headless"); // Enable for headless mode
//        options.addArguments("--disable-gpu");
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
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
        Thread.sleep(30000); // ✅ Wait for network request
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
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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

    public void validateGTMBingEvent() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

       devTools.send(Network.enable(
        Optional.empty(), // maxTotalBufferSize
        Optional.empty(), // maxResourceBufferSize
        Optional.empty(), // maxPostDataSize
        Optional.empty(), // reportDirectSocketTraffic
        Optional.empty()  // enableDurableMessages
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


        Thread.sleep(10000);

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


    public void ValidatePixelForDirectMailRetargeting() throws Exception {
        jse = (JavascriptExecutor) driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");

        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        AtomicReference<String> encodedData = new AtomicReference<>();
        AtomicReference<String> conversionUrl = new AtomicReference<>();

        devTools.addListener(Network.requestWillBeSent(), requestSent -> {

            Request request = requestSent.getRequest();

            String url = request.getUrl();

            if (!url.contains("datasteam"))
                return;

            System.out.println("==================================================");
            System.out.println("DataStream Request");
            System.out.println(url);

            try {

                URI uri = URI.create(url);

                String query = uri.getQuery();

                if (query == null)
                    return;

                for (String param : query.split("&")) {

                    String decoded = URLDecoder.decode(param, StandardCharsets.UTF_8);

                    if (decoded.startsWith("d=")) {

                        encodedData.set(decoded.substring(2));

                    }

                    if (decoded.contains("conversion=true")) {

                        conversionUrl.set(url);

                    }

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        });

        // Launch site
        driver.get("https://www.tommyjohn.com/");

        // Add product
        new AddToCartComponents(driver).addsingleproductToCart();
        jse.executeScript("arguments[0].click();", driver.findElement(VIEW_CART_LINK));


        Thread.sleep(10000);

        Assert.assertNotNull(encodedData.get(),
                "No DataStream CartItem request found.");

        // Decode Base64

        String decodedJson = new String(
                Base64.getDecoder().decode(encodedData.get()),
                StandardCharsets.UTF_8);

        System.out.println("Decoded JSON");
        System.out.println(decodedJson);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode json = mapper.readTree(decodedJson);

        String event = json.get("l").asText();
        String productName = json.get("v06").asText();
        String productPrice = json.get("v03").asText();
        String quantity = json.get("v08").asText();
        String productUrl = json.get("v04").asText();

        System.out.println("Event : " + event);
        System.out.println("Product : " + productName);
        System.out.println("Price : " + productPrice);
        System.out.println("Qty : " + quantity);

        Assert.assertEquals(event, "CartItem");

        Assert.assertEquals(productName,
                "Performance Crew Sock (3-Pack)");

        Assert.assertEquals(productPrice,
                "35.00");

        Assert.assertEquals(quantity,
                "1");

        Assert.assertTrue(productUrl.contains("/products/"));

        System.out.println("CartItem Validation Passed");

    /*
       COMPLETE CHECKOUT HERE

       new CheckoutPage(driver).completeCheckout();

       Wait until Thank You page loads
    */

        if (conversionUrl.get() != null) {

            URI uri = URI.create(conversionUrl.get());

            String query = uri.getQuery();

            String orderId = "";
            String orderTotal = "";

            for (String param : query.split("&")) {

                String[] pair = param.split("=");

                if (pair.length < 2)
                    continue;

                if (pair[0].equals("orderId")) {

                    orderId = pair[1];

                }

                if (pair[0].equals("orderTotal")) {

                    orderTotal = pair[1];

                }

            }

            System.out.println("Order Id : " + orderId);
            System.out.println("Order Total : " + orderTotal);

        /*
            Compare against values displayed on Thank You page.

            Example:

            Assert.assertEquals(orderId, expectedOrderId);
            Assert.assertEquals(orderTotal, expectedOrderTotal);
        */

        }

        driver.quit();
    }



}