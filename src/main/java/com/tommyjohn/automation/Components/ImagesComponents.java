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


public class ImagesComponents {

    GeneralMethods objGeneralMethods = new GeneralMethods();

    public ImagesComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    String searchText = "";


    public void VerifyAltText() {

        List<WebElement> images = driver.findElements(By.tagName("img"));
        int errorImageCount = 0;

        System.out.println("Total images: " + images.size());

        for (WebElement img : images) {

            String alt = img.getAttribute("alt");
            String src = img.getAttribute("src");

            // Check null OR empty OR blank alt text
            if (alt == null || alt.trim().isEmpty()) {

                System.out.println("Missing ALT for image: " + src);
                Reporter.log("Missing ALT for image: " + src );
                ExtentReport.extentTest.info("Missing ALT for image: " + src);
                errorImageCount++;
            } else {
                System.out.println("ALT: " + alt);
                Reporter.log("Alt contains in the" + alt );
                ExtentReport.extentTest.info("Alt contains in the" + alt);
            }
        }

        System.out.println("Total images with missing alt: " + errorImageCount);

        if (errorImageCount > 0) {
            Assert.fail( errorImageCount + " image(s) have missing ALT attributes.");
        }
    }

}