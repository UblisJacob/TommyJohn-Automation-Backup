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


    public void VerifyAltText() {

        String csvPath = "D:\\IdeaProject\\CSV\\homepage_report.csv";
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

        String csvPath = "D:\\IdeaProject\\CSV\\PLP_report.csv";
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

    private String getAltTextSafe(WebElement img) {
        try {
            // 1️⃣ Try normal Selenium ALT
            String alt = img.getAttribute("alt");
            if (alt != null && !alt.trim().isEmpty()) {
                return alt.trim();
            }

            // 2️⃣ Try JS (handles Shadow DOM)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object result = js.executeScript(
                    "return arguments[0].getAttribute('alt');", img
            );

            if (result != null && !result.toString().trim().isEmpty()) {
                return result.toString().trim();
            }

        } catch (Exception ignored) {}

        return "Missing";
    }




}