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


public class ImagesComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();

    public ImagesComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    String searchText = "";
    int count = 0;
    int PLPs = 2;
    SoftAssert softAssert = new SoftAssert();


    public void VerifyAltText() {
        String csvPath = "D:\\IdeaProject\\CSV\\homepage_report.csv";
        CSVWriter writer;

        try {
            File file = new File(csvPath);

            // Overwrite the file on every run
            writer = new CSVWriter(new FileWriter(file, false));

            // Always write header when overwriting
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

            String urlFinal = "Missing AltText_Homepage";
            try {
                urlFinal = img.findElement(By.xpath("./ancestor::a[1]")).getAttribute("href");
                if (urlFinal == null || urlFinal.trim().isEmpty()) {
                    urlFinal = "Missing AltText_Homepage";
                }
            } catch (Exception ignored) {}

            try {
                wait.until(driver1 -> img.getAttribute("alt") != null);
            } catch (Exception ignored) {}

            String alt = img.getAttribute("alt");
            String altFinal = (alt == null || alt.trim().isEmpty()) ? "Missing AltText_Homepage" : alt;

            // ✅ FINAL STATUS LOGIC
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

            // ✅ Logging
            System.out.println(
                    status + " | ALT: " + altFinal +
                            " | SRC: " + src +
                            " | URL: " + urlFinal
            );

            ExtentReport.extentTest.info(
                    status + " | ALT: " + altFinal +
                            " | SRC: " + src +
                            " | URL: " + urlFinal
            );

            writer.writeNext(new String[]{status, altFinal, src, urlFinal});
        }

        try {
            writer.close();
        } catch (Exception ignored) {}

        // ✅ SUMMARY
        ExtentReport.extentTest.info("===== 🔴 FINAL SUMMARY 🔴 =====");
        ExtentReport.extentTest.info("Total images with missing ALT: " + missingAltTotal);
        ExtentReport.extentTest.info("Total images with missing URL: " + missingUrlTotal);

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

        String csvPath = "D:\\IdeaProject\\CSV\\PLP_report.csv";
        CSVWriter writer = null;

        try {
            File file = new File(csvPath);

            // Overwrite file every run
            writer = new CSVWriter(new FileWriter(file, false));

            // Always write header when overwriting
            writer.writeNext(new String[]{"ALT Status", "ALT Text", "SRC", "URL"});

        } catch (Exception e) {
            throw new RuntimeException("Unable to create CSV file", e);
        }


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/PLPs.csv"));
        List<String[]> list = csvReader.readAll();

        int missingAltTotal = 0;
        int missingUrlTotal = 0;

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

                    scrollUntilAllProductsLoad();

                    List<WebElement> productLinks = driver.findElements(By.xpath(
                            "//a[contains(@class,'block') and contains(@class,'scroll-mt-4xl') and contains(@class,'leading-none')]"
                    ));

                    for (WebElement card : productLinks) {

                        try {
                            ((JavascriptExecutor) driver)
                                    .executeScript("arguments[0].scrollIntoView({block:'center'});", card);
                            Thread.sleep(250);
                        } catch (Exception ignored) {}

                        String href = card.getAttribute("href");
                        if (href != null && href.contains("color=undefined")) {
                            continue;
                        }

                        String hrefFinal = (href == null || href.trim().isEmpty()) ? "Missing" : href;

                        WebElement img = null;
                        try {
                            img = card.findElement(By.cssSelector("img.object-cover"));
                        } catch (Exception ignored) {}

                        if (img == null) {
                            List<WebElement> imgs = card.findElements(By.xpath(".//img"));
                            for (WebElement i : imgs) {
                                if (i.isDisplayed()) {
                                    img = i;
                                    break;
                                }
                            }
                        }

                        String altFinal = "Missing";
                        String srcFinal = "Missing";

                        if (img != null) {
                            try {
                                ((JavascriptExecutor) driver)
                                        .executeScript("arguments[0].scrollIntoView({block:'center'});", img);
                                Thread.sleep(200);
                            } catch (Exception ignored) {}

                            String alt = img.getAttribute("alt");
                            String src = img.getAttribute("src");
                            String dataSrc = img.getAttribute("data-src");
                            String srcset = img.getAttribute("srcset");

                            srcFinal = (src != null && !src.isBlank()) ? src :
                                    (dataSrc != null && !dataSrc.isBlank()) ? dataSrc :
                                            (srcset != null && !srcset.isBlank()) ? srcset : "Missing";

                            altFinal = (alt == null || alt.trim().isEmpty()) ? "Missing" : alt;
                        }

                        // ✅ FINAL STATUS LOGIC
                        String status;
                        if (altFinal.equals("Missing")) {
                            status = "Missing AltText_PLPs";
                            missingAltTotal++;
                        } else if (hrefFinal.equals("Missing")) {
                            status = "Missing URL";
                            missingUrlTotal++;
                        } else {
                            status = "Valid ALT";
                        }

                        // ✅ Logging
                        System.out.println(
                                status + " | ALT: " + altFinal +
                                        " | SRC: " + srcFinal +
                                        " | URL: " + hrefFinal
                        );

                        ExtentReport.extentTest.info(
                                status + " | ALT: " + altFinal +
                                        " | SRC: " + srcFinal +
                                        " | URL: " + hrefFinal
                        );

                        writer.writeNext(new String[]{status, altFinal, srcFinal, hrefFinal});
                    }

                } catch (Exception ex) {
                    Reporter.log("❌ Page is not loading → " + url);
                    ExtentReport.extentTest.info("❌ Page is not loading → " + url);
                }
            }
        }

        try {
            writer.close();
        } catch (Exception ignored) {}

        // ✅ FINAL SUMMARY
        ExtentReport.extentTest.info("===== 🔴 FINAL SUMMARY 🔴 =====");
        ExtentReport.extentTest.info("Total PLPs passed: " + count);
        ExtentReport.extentTest.info("Total missing ALT (Product Cards): " + missingAltTotal);
        ExtentReport.extentTest.info("Total missing URL (Product Cards): " + missingUrlTotal);

        if (missingAltTotal > 0 || missingUrlTotal > 0) {
            softAssert.fail("ALT / URL validation failed");
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