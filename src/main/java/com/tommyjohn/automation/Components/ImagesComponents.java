package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.opencsv.CSVReader;
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


public class ImagesComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();

    public ImagesComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    String searchText = "";
    int count = 0;
    int PLPs = 55;
    SoftAssert softAssert = new SoftAssert();


    public void VerifyAltText() {

        List<WebElement> images = driver.findElements(By.tagName("img"));
        int errorImageCount = 0;

        System.out.println("Total images: " + images.size());
        ExtentReport.extentTest.info("Total images found: " + images.size());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        for (WebElement img : images) {

            // Scroll into view
            try {
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", img);
                Thread.sleep(150);
            } catch (Exception ignored) {}

            String src = img.getAttribute("src");

            // Get HREF (if exists)
            String href = "No HREF Found";
            try {
                WebElement parent = img.findElement(By.xpath("./ancestor::a[1]"));
                href = parent.getAttribute("href");
            } catch (Exception ignored) {}

            // Wait a moment for lazy-loaded ALT
            try {
                wait.until(driver1 -> img.getAttribute("alt") != null);
            } catch (Exception ignored) {}

            // Fetch ALT
            String alt = img.getAttribute("alt");

            // ===============================
            // VALIDATION + HIGHLIGHTING
            // ===============================

            if (alt == null) {

                // -----------------------------------------
                // ❌ CASE 1 — ALT ATTRIBUTE NOT PRESENT
                // -----------------------------------------
                highlightElement(img, "3px solid red");

                String message = "❌ ALT ATTRIBUTE NOT PRESENT | SRC: " + src + " | HREF: " + href;
                System.out.println(message);
                Reporter.log(message);
                ExtentReport.extentTest.info(message);

                errorImageCount++;

            } else if (alt.trim().isEmpty()) {

                // -----------------------------------------
                // ❌ CASE 2 — ALT="" EMPTY
                // -----------------------------------------
                highlightElement(img, "3px solid yellow");

                String message = "❌ ALT EMPTY | ALT=\"\" | SRC: " + src + " | HREF: " + href;
                System.out.println(message);
                Reporter.log(message);
                ExtentReport.extentTest.info(message);

                errorImageCount++;

            } else {

                // -----------------------------------------
                // ✅ CASE 3 — VALID ALT
                // -----------------------------------------
                String message = "✅ ALT: \"" + alt + "\" | SRC: " + src + " | HREF: " + href;
                System.out.println(message);
                Reporter.log(message);
                ExtentReport.extentTest.info(message);
            }
        }

        // FINAL SUMMARY
        System.out.println("Total images with ALT issues: " + errorImageCount);
        ExtentReport.extentTest.info("Total images with ALT issues: " + errorImageCount);

        if (errorImageCount > 0) {
            Assert.fail(errorImageCount + " image(s) are missing valid ALT attributes.");
        }
    }

    public void highlightElement(WebElement element, String style) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].style.border='" + style + "';", element);
        } catch (Exception ignored) {}
    }


    public void Validateallurls() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        String csv_path = "src/main/resources/PLPs.csv";
        CSVReader CSVReader = new CSVReader(new FileReader(csv_path));
        List<String[]> list = CSVReader.readAll();

        int missingAltTotal = 0;
        int missingHrefTotal = 0;

        for (String[] data : list) {
            for (String path : data) {

                String url = "https://www.tommyjohn.com" + path;

                Reporter.log("🔍 Starting validation for URL: " + url);
                ExtentReport.extentTest.info("🔍 Starting validation for URL: " + url);

                driver.get(url);

                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT));

                    count++;
                    Reporter.log(url + " loads fine and products are displayed");
                    ExtentReport.extentTest.info(url + " loads fine and products are displayed");

                    int missingAlt = 0;
                    int missingHref = 0;

                    scrollUntilAllProductsLoad();

                    List<WebElement> productLinks = driver.findElements(By.xpath(
                            "//a[contains(@class,'block') and contains(@class,'scroll-mt-4xl') and contains(@class,'leading-none')]"
                    ));

                    ExtentReport.extentTest.info("🔢 Total products found: " + productLinks.size());

                    for (WebElement card : productLinks) {

                        try {
                            ((JavascriptExecutor) driver)
                                    .executeScript("arguments[0].scrollIntoView({block:'center'});", card);
                            Thread.sleep(250);
                        } catch (Exception ignored) {}

                        // ============================
                        // ⚠️ Skip cards with color=undefined
                        // ============================
                        String href = card.getAttribute("href");

                        if (href != null && href.contains("color=undefined")) {
                            continue;  // 🚫 Skip entire product card
                        }

                        // ============================
                        // HREF VALIDATION
                        // ============================
                        String hrefFinal = (href == null || href.trim().isEmpty()) ? "Missing" : href;

                        if (hrefFinal.equals("Missing")) {
                            missingHref++;
                        }

                        // ============================
                        // Extract IMG
                        // ============================
                        WebElement img = null;

                        try {
                            img = card.findElement(By.cssSelector("img.object-cover"));
                        } catch (Exception ignore) {}

                        if (img == null) {
                            List<WebElement> imgs = card.findElements(By.xpath(".//img"));
                            for (WebElement i : imgs) {
                                if (i.isDisplayed()) {
                                    img = i;
                                    break;
                                }
                            }
                        }

                        // IMG NOT FOUND
                        if (img == null) {
                            String msg =
                                    "❌ ALT: Missing (no IMG found)\n" +
                                            "HREF: " + hrefFinal + "\n" +
                                            "SRC: Missing";

                            Reporter.log(msg);
                            ExtentReport.extentTest.info(msg);

                            missingAlt++;
                            continue;
                        }

                        ((JavascriptExecutor) driver)
                                .executeScript("arguments[0].scrollIntoView({block:'center'});", img);
                        Thread.sleep(200);

                        // Extract alt & src
                        String alt = img.getAttribute("alt");
                        String src = img.getAttribute("src");
                        String dataSrc = img.getAttribute("data-src");
                        String srcset = img.getAttribute("srcset");

                        String srcFinal =
                                (src != null && !src.isBlank()) ? src :
                                        (dataSrc != null && !dataSrc.isBlank()) ? dataSrc :
                                                (srcset != null && !srcset.isBlank()) ? srcset :
                                                        "Missing";

                        String altFinal = (alt == null || alt.trim().isEmpty()) ? "Missing" : alt;

                        // ============================
                        // REPORT PRODUCT CARD
                        // ============================
                        String msg =
                                (altFinal.equals("Missing") ? "❌ ALT: Missing" : "✅ ALT: " + altFinal) + "\n" +
                                        "HREF: " + hrefFinal + "\n" +
                                        "SRC: " + srcFinal;

                        Reporter.log(msg);
                        ExtentReport.extentTest.info(msg);

                        if (altFinal.equals("Missing")) missingAlt++;
                    }

                    missingAltTotal += missingAlt;
                    missingHrefTotal += missingHref;

                } catch (Exception ex) {
                    Reporter.log("❌ Page is not loading → " + url);
                    ExtentReport.extentTest.info("❌ Page is not loading → " + url);
                }
            }
        }

        // ============================
        // FINAL SUMMARY
        // ============================
        ExtentReport.extentTest.info("===== 🔴 FINAL SUMMARY 🔴 =====");
        ExtentReport.extentTest.info("Total PLPs passed: " + count);
        ExtentReport.extentTest.info("Total missing ALT (Product Cards): " + missingAltTotal);
        ExtentReport.extentTest.info("Total missing HREF (Product Cards): " + missingHrefTotal);

        if (missingAltTotal > 0 || missingHrefTotal > 0) {
            softAssert.fail("ALT/HREF validation failed");
        }

        softAssert.assertEquals(count, PLPs, "Some URLs are not loading properly");
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
}