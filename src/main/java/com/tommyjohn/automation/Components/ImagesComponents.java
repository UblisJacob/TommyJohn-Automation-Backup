package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
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
import org.openqa.selenium.devtools.v147.network.Network;
import org.openqa.selenium.devtools.v147.network.model.Request;
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
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import static com.tommyjohn.automation.PageLocators.CollectionPageLocator.*;
import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.CHECKOUT_BUTTON;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.PDP_TABS;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.PRODUCT_TITLE;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.QUANTITY_ATC_SECTION;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.SIZE_SECTION;
import static com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators.SWATCH_SECTION;
import static org.testng.Reporter.log;


public class ImagesComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();

    public ImagesComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    String searchText = "";
//    int count = 0;
//    int PLPs = 55;
 //   SoftAssert softAssert = new SoftAssert();
    private int missingAltTotal = 0;
    private int missingUrlTotal = 0;
    private boolean hasFailures = false;

    public void VerifyAltText() {

        String csvPath = "C:\\Users\\UTI\\IdeaProjects\\TJ_Automation\\CSV\\homepage_report.csv";
        CSVWriter writer;

        boolean hasFailures = false;

        try {
            File file = new File(csvPath);

            // Overwrite file every run
            writer = new CSVWriter(new FileWriter(file, false));

            // Always write header
            writer.writeNext(new String[]{"ALT Status", "ALT Text", "SRC", "URL"});

        } catch (Exception e) {
            throw new RuntimeException("Unable to create CSV file", e);
        }

        List<WebElement> images = driver.findElements(By.tagName("img"));

        int missingAltTotal = 0;
        int missingUrlTotal = 0;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        for (WebElement img : images) {

            try {
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", img);
                Thread.sleep(150);
            } catch (Exception ignored) {}

            String src = img.getAttribute("src");

            String urlFinal = "Missing";
            try {
                urlFinal = img.findElement(By.xpath("./ancestor::a[1]")).getAttribute("href");
                if (urlFinal == null || urlFinal.trim().isEmpty()) {
                    urlFinal = "Missing";
                }
            } catch (Exception ignored) {}

            try {
                wait.until(d -> img.getAttribute("alt") != null);
            } catch (Exception ignored) {}

            String alt = img.getAttribute("alt");
            String altFinal = (alt == null || alt.trim().isEmpty())
                    ? "Missing AltText_Homepage"
                    : alt;

            String status;
            if (altFinal.equals("Missing AltText_Homepage")) {
                highlightElement(img, "3px solid red");
                status = "Missing AltText_Homepage";
                missingAltTotal++;
            } else if (urlFinal.equals("Missing")) {
                status = "Missing URL";
                missingUrlTotal++;
            } else {
                status = "Valid ALT";
            }

            // ✅ Console + Report (ALL DATA)
            System.out.println(status + " | ALT: " + altFinal + " | SRC: " + src + " | URL: " + urlFinal);
            ExtentReport.extentTest.info(
                    status + " | ALT: " + altFinal + " | SRC: " + src + " | URL: " + urlFinal
            );

            // ✅ CSV → ONLY FAILURES
            if (!status.equals("Valid ALT")) {
                writer.writeNext(new String[]{status, altFinal, src, urlFinal});
                hasFailures = true;
            }
        }

        // ✅ Write success row if no failures
        if (!hasFailures) {
            writer.writeNext(new String[]{
                    "All images contain ALT text as expected", "-", "-", "-"
            });
        }

        try { writer.close(); } catch (Exception ignored) {}

        // ✅ FINAL SUMMARY
        ExtentReport.extentTest.info("===== 🔴 FINAL SUMMARY 🔴 =====");
        ExtentReport.extentTest.info("Missing ALT count: " + missingAltTotal);
        ExtentReport.extentTest.info("Missing URL count: " + missingUrlTotal);

        if (missingAltTotal > 0 || missingUrlTotal > 0) {
            Assert.fail("Homepage ALT / URL validation failed");
        }
    }






    public void highlightElement(WebElement element, String style) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].style.border='" + style + "';", element);
        } catch (Exception ignored) {}
    }


    public void Validateallurls() throws Exception {

        SoftAssert softAssert = new SoftAssert();

        String csvPath = "C:\\Users\\UTI\\IdeaProjects\\TJ_Automation\\CSV\\PLP_report.csv";
        CSVWriter writer;
        boolean hasFailures = false;

        // ================= CSV SETUP =================
        try {
            File file = new File(csvPath);
            writer = new CSVWriter(new FileWriter(file, false));
            writer.writeNext(new String[]{"ALT Status", "ALT Text", "SRC", "URL"});
        } catch (Exception e) {
            throw new RuntimeException("Unable to create CSV file", e);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/PLPs.csv"));
        List<String[]> list = csvReader.readAll();

        int missingAltTotal = 0;
        int missingUrlTotal = 0;

        // ================= EXPECTED PLP COUNT =================
        int PLPs = 55;
        int loadedPLPs = 0;

        // ================= MAIN LOOP =================
        for (String[] data : list) {
            for (String path : data) {

                String url = "https://www.tommyjohn.com" + path;
                Reporter.log("🔍 Starting validation for URL: " + url);
                System.out.println("🔍 Starting validation for URL: " + url);
                ExtentReport.extentTest.info("🔍 Starting validation for URL: " + url);
                log("🔍 Validating URL: " + url);

                driver.get(url);

                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));
                    loadedPLPs++;

                    scrollUntilAllProductsLoad();

                    List<WebElement> productLinks = driver.findElements(By.xpath(
                            "//a[contains(@class,'block') and contains(@class,'scroll-mt-4xl')]"
                    ));

                    for (WebElement card : productLinks) {

                        String href = card.getAttribute("href");

                        if (href != null && href.contains("color=undefined")) {
                            log("⚠️ Skipped product with invalid color param");
                            continue;
                        }

                        String hrefFinal =
                                (href == null || href.trim().isEmpty()) ? "Missing" : href;

                        WebElement img;
                        try {
                            img = card.findElement(By.tagName("img"));
                        } catch (Exception e) {
                            log("⚠️ No <img> found inside product card | URL: " + hrefFinal);
                            continue;
                        }

                        // ================= SRC HANDLING =================
                        String src = img.getAttribute("src");
                        String dataSrc = img.getAttribute("data-src");

                        String srcFinal =
                                (src != null && !src.isBlank()) ? src :
                                        (dataSrc != null && !dataSrc.isBlank()) ? dataSrc : "Missing";

                        // ================= ALT (SAFE) =================
                        String altFinal = getAltTextSafe(img);

                        // ================= STATUS =================
                        String status;
                        if ("Missing".equals(altFinal)) {
                            status = "❌ Missing AltText_PLPs";
                            missingAltTotal++;
                        } else if ("Missing".equals(hrefFinal)) {
                            status = "❌ Missing URL";
                            missingUrlTotal++;
                        } else {
                            status = "✅ Valid ALT";
                        }

                        // ================= ALWAYS LOG =================
                        String logMessage =
                                status + " | ALT: " + altFinal +
                                        " | SRC: " + srcFinal +
                                        " | URL: " + hrefFinal;

                        log(logMessage);
                        System.out.println(logMessage);
                        ExtentReport.extentTest.info(logMessage);

                        // ================= CSV → FAILURES ONLY =================
                        if (!status.contains("Valid")) {
                            writer.writeNext(new String[]{
                                    status, altFinal, srcFinal, hrefFinal
                            });
                            hasFailures = true;
                        }
                    }

                } catch (Exception e) {
                    log("❌ Page not loading or products not visible: " + url);
                    String errorMsg = "❌ Page not loading: " + url; Reporter.log(errorMsg);
                    ExtentReport.extentTest.info(errorMsg);
                    System.out.println(errorMsg);
                }
            }
        }

        // ================= CSV SUCCESS ROW =================
        if (!hasFailures) {
            writer.writeNext(new String[]{
                    "All images contain ALT text as expected", "-", "-", "-"
            });
        }

        try { writer.close(); } catch (Exception ignored) {}

        // ================= FINAL SUMMARY =================

        ExtentReport.extentTest.info("===== 🔴 FINAL SUMMARY 🔴 ====="); ExtentReport.extentTest.info("Missing ALT count: " + missingAltTotal); ExtentReport.extentTest.info("Missing URL count: " + missingUrlTotal); ExtentReport.extentTest.info( "Expected PLPs: " + PLPs + " | Successfully loaded PLPs: " + loadedPLPs );

        System.out.println("===== FINAL SUMMARY ====="); System.out.println("Missing ALT count: " + missingAltTotal); System.out.println("Missing URL count: " + missingUrlTotal); System.out.println("Expected PLPs: " + PLPs + " | Loaded PLPs: " + loadedPLPs);

        log("===== FINAL SUMMARY =====");
        log("Missing ALT count: " + missingAltTotal);
        log("Missing URL count: " + missingUrlTotal);
        log("Expected PLPs: " + PLPs + " | Loaded PLPs: " + loadedPLPs);


        // ================= ASSERTIONS =================
        if (missingAltTotal > 0 || missingUrlTotal > 0) {
            softAssert.assertTrue(
                    false,
                    "PLP ALT / URL validation failed → Missing ALT: "
                            + missingAltTotal + ", Missing URL: " + missingUrlTotal
            );
        }

        softAssert.assertEquals(
                loadedPLPs,
                PLPs,
                "PLP count mismatch → Expected: " + PLPs + ", Loaded: " + loadedPLPs
        );

        softAssert.assertAll();
    }



    public void scrollUntilAllProductsLoad() throws InterruptedException {
        long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

        while (true) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1500);

            long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            if (newHeight == lastHeight) {
                break;
            }

            lastHeight = newHeight;
        }
    }



    public void ValidateallPDPs() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String pdpCsvPath = "src/main/resources/ImagePDP.csv";
        String reportCsvPath = "C:\\Users\\UTI\\IdeaProjects\\TJ_Automation\\CSV\\PDP_report.csv";

        CSVWriter writer = new CSVWriter(new FileWriter(reportCsvPath, false));
        writer.writeNext(new String[]{"Status", "ALT Text", "SRC", "URL"});
        writer.flush();

        missingAltTotal = 0;
        int missingUrlTotal = 0;
        int loadedPDPs = 0;

        CSVReader csvReader = new CSVReader(new FileReader(pdpCsvPath));
        List<String[]> list = csvReader.readAll();

        for (String[] data : list) {

            if (data[0].startsWith("#")) continue;

            for (String path : data) {

                String url = "https://www.tommyjohn.com" + path;
                System.out.println("🔍 Validating PDP: " + url);
                ExtentReport.extentTest.info("Validating PDP: " + url);

                try {
                    driver.get(url);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_TITLE));
                    loadedPDPs++;

                    // ================= MAIN PAGE =================
                    validateImagesForAlt(url, writer);

                    // ================= TABS =================
                    List<WebElement> tabs = driver.findElements(PDP_TABS);

                    for (int i = 1; i <= tabs.size(); i++) {
                        try {
                            WebElement tab =
                                    driver.findElement(By.xpath("(//span[@class='text-sm text-text-tertiary'])[" + i + "]"));
                            js.executeScript("arguments[0].click();", tab);
                            Thread.sleep(2000);

                            validateImagesForAlt(url, writer);

                        } catch (Exception ignored) {}
                    }

                } catch (Exception e) {
                    missingUrlTotal++;
                    System.out.println("❌ PDP not loading: " + url);
                    ExtentReport.extentTest.info("PDP not loading: " + url);
                }
            }
        }

        // ================= CSV SUCCESS =================
        if (missingAltTotal == 0) {
            writer.writeNext(new String[]{"All images contain ALT text as expected", "-", "-", "-"});
        }

        writer.close();

        // ================= SUMMARY =================
        ExtentReport.extentTest.info("===== 🔴 PDP FINAL SUMMARY 🔴 =====");
        ExtentReport.extentTest.info("Missing ALT count: " + missingAltTotal);
        ExtentReport.extentTest.info("Missing URL count: " + missingUrlTotal);
        ExtentReport.extentTest.info("Loaded PDPs: " + loadedPDPs);

        System.out.println("===== PDP FINAL SUMMARY =====");
        System.out.println("Missing ALT count: " + missingAltTotal);
        System.out.println("Missing URL count: " + missingUrlTotal);
        System.out.println("Loaded PDPs: " + loadedPDPs);

        // ================= HARD FAIL =================
        if (missingAltTotal > 0) {
            Assert.fail("❌ PDP ALT TEXT VALIDATION FAILED | Missing ALT count: " + missingAltTotal);
        }
    }




    // ================= Helper: Validate images ALT text =================


    private void validateImagesForAlt(String url, CSVWriter writer) throws IOException {

        List<WebElement> images = driver.findElements(By.tagName("img"));

        // Deduplicate across Main + all Tabs for this PDP
        Set<String> seenImages = new HashSet<>();

        for (WebElement img : images) {

            // ---------- FULL SRC ----------
            String fullSrc = img.getAttribute("src");
            if (fullSrc == null || fullSrc.isBlank()) {
                fullSrc = img.getAttribute("data-src");
            }
            if (fullSrc == null || fullSrc.isBlank()) {
                fullSrc = "Missing";
            }

            // ---------- Dedup key ----------
            String dedupKey = normalizeForDedup(fullSrc);
            if (!seenImages.add(dedupKey)) {
                continue; // ignore duplicate image
            }

            // ---------- ALT ----------
            String altText = img.getAttribute("alt");

            if (altText == null || altText.trim().isEmpty()) {

                // ❌ FAIL
                missingAltTotal++;

                String failMsg =
                        "❌ Missing ALT | SRC: " + fullSrc +
                                " | URL: " + url;

                // Console
                System.out.println(failMsg);

                // Extent
                ExtentReport.extentTest.info(failMsg);

                // TestNG
                Reporter.log(failMsg);

                // CSV → FAILURES ONLY
                writer.writeNext(new String[]{
                        "❌ Missing AltText_PDPs",
                        "",
                        fullSrc,
                        url
                });
                writer.flush();

            } else {

                // ✅ PASS (NO CSV)
                String passMsg =
                        "✅ Valid ALT | ALT: " + altText +
                                " | SRC: " + fullSrc +
                                " | URL: " + url;

                System.out.println(passMsg);
                ExtentReport.extentTest.info(passMsg);
                Reporter.log(passMsg);
            }
        }
    }


    private String normalizeForDedup(String src) {

        if (src == null || src.isBlank()) {
            return "Missing-" + UUID.randomUUID();
        }

        // Strip query params
        String clean = src.split("\\?")[0];

        // Use filename only for dedup
        return clean.substring(clean.lastIndexOf("/") + 1);
    }



    // ================= Helper: Validate sections =================
    private void validateSection(By locator, String sectionName, String url) {
        try {
            if (driver.findElement(locator).isDisplayed()) {
                System.out.println(sectionName + " section displayed");
            }
        } catch (Exception ex) {
            System.out.println("❌ " + sectionName + " section NOT displayed for URL: " + url);
            Reporter.log(sectionName + " section NOT displayed for URL: " + url);
            ExtentReport.extentTest.info(sectionName + " section NOT displayed for URL: " + url);
        }
    }





    private String getAltTextSafe(WebElement img) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", img);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(driver -> {
                String alt = img.getAttribute("alt");
                String src = img.getAttribute("src");
                String dataSrc = img.getAttribute("data-src");
                return (alt != null && !alt.isEmpty()) && (src != null || dataSrc != null);
            });

            String alt = img.getAttribute("alt");
            if (alt != null && !alt.trim().isEmpty()) return alt.trim();

            Object jsAlt = ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].getAttribute('alt');", img
            );
            if (jsAlt != null && !jsAlt.toString().trim().isEmpty()) return jsAlt.toString().trim();

        } catch (Exception ignored) {}
        return "Missing";
    }
}