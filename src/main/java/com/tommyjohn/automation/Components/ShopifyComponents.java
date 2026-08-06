package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ShopifyLocators;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.network.Network;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static com.tommyjohn.automation.PageLocators.AccountLoginLocators.CUSTOMER_EMAIL_INPUT;
import static com.tommyjohn.automation.PageLocators.AccountLoginLocators.LOGIN_BUTTON;

public class ShopifyComponents extends ShopifyLocators {

    public ShopifyComponents(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;
    public WebElement element;
    public Actions action;
    public JavascriptExecutor jse;
    List<WebElement> RemoveItems;
    Properties properties = CustomUtilities.properties;
    List<WebElement> allElements;
    int elementsCount;
    String text = null;
    List<WebElement> Tabs;
    String allClasses = null;
    public Actions actions;
    String invalidemail = "drewstrr@tre.cde";
    String invalidpassword = "123";
    String forgotpasswordemail = "testuserQA1@gmail.com";
    String currentUrl;


    public void ValidateShopifyAccountLogin(String createdAccountEmail, String password) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            wait.until(d ->
                    js.executeScript("return document.readyState")
                            .equals("complete"));

            //----------------------------------------------------
            // Start Network Listener
            //----------------------------------------------------

            DevTools devTools = ((ChromeDriver) driver).getDevTools();

            devTools.createSession();

            devTools.send(org.openqa.selenium.devtools.v147.network.Network.enable(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()
            ));

            devTools.addListener(Network.requestWillBeSent(), request -> {

                String url = request.getRequest().getUrl();

                if (url.contains("login")
                        || url.contains("challenge")
                        || url.contains("captcha")
                        || url.contains("cloudflare")
                        || url.contains("accounts.shopify")) {

                    System.out.println("REQUEST : " + url);
                }

            });

            devTools.addListener(Network.responseReceived(), response -> {

                String url = response.getResponse().getUrl();

                int status = response.getResponse().getStatus().intValue();

                if (url.contains("login")
                        || url.contains("challenge")
                        || url.contains("captcha")
                        || url.contains("cloudflare")
                        || url.contains("accounts.shopify")) {

                    System.out.println("RESPONSE : " + status + " -> " + url);
                }

            });

            //----------------------------------------------------
            // Email
            //----------------------------------------------------

            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));

            email.clear();

            email.sendKeys(createdAccountEmail);

            Reporter.log("Email entered");

            //----------------------------------------------------
            // Continue
            //----------------------------------------------------

            wait.until(ExpectedConditions.elementToBeClickable(
                    CONTINUE_EMAIL_CTA)).click();

            Reporter.log("Continue clicked");

            //----------------------------------------------------
            // Get Started
            //----------------------------------------------------

            wait.until(ExpectedConditions.elementToBeClickable(
                    GET_STARTED)).click();

            Reporter.log("Get Started clicked");

            //----------------------------------------------------
            // Close Windows Security Popup
            //----------------------------------------------------

            dismissWindowsSecurityPopup();

            //----------------------------------------------------
            // Different Method
            //----------------------------------------------------

            wait.until(ExpectedConditions.elementToBeClickable(
                    LOGIN_USING_DIFFERENT_METHOD)).click();

            Reporter.log("Different Method clicked");

            //----------------------------------------------------
            // Continue With Password
            //----------------------------------------------------

            wait.until(ExpectedConditions.elementToBeClickable(
                    CONTINUE_WITH_PASSWORD)).click();

            Reporter.log("Continue With Password clicked");

            //----------------------------------------------------
            // Password
            //----------------------------------------------------

            WebElement passwordField = wait.until(
                    ExpectedConditions.elementToBeClickable(PASSWORD_INPUT));

            passwordField.click();

            passwordField.clear();

            passwordField.sendKeys(password);

            Thread.sleep(1000);

            System.out.println("Password Length : "
                    + passwordField.getAttribute("value").length());

            //----------------------------------------------------
            // CAPTCHA Validation
            //----------------------------------------------------

            System.out.println("navigator.webdriver : "
                    + js.executeScript("return navigator.webdriver"));

            System.out.println("Current URL : "
                    + driver.getCurrentUrl());

            System.out.println("Title : "
                    + driver.getTitle());

            List<WebElement> iframes =
                    driver.findElements(By.tagName("iframe"));

            for (WebElement frame : iframes) {

                String src = frame.getAttribute("src");

                if (src != null) {

                    System.out.println("IFRAME : " + src);

                }
            }

            String source =
                    driver.getPageSource().toLowerCase();

            if (source.contains("captcha"))
                System.out.println("CAPTCHA keyword detected");

            if (source.contains("turnstile"))
                System.out.println("Turnstile detected");

            if (source.contains("recaptcha"))
                System.out.println("reCAPTCHA detected");

            if (source.contains("cloudflare"))
                System.out.println("Cloudflare detected");

            //----------------------------------------------------
            // Login
            //----------------------------------------------------

            WebElement login = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            SHOPIFY_LOGIN_BUTTON));

            System.out.println("Enabled : " + login.isEnabled());

            System.out.println("Displayed : " + login.isDisplayed());

            login.click();

            Reporter.log("Login clicked");

            Thread.sleep(7000);

            //----------------------------------------------------
            // Final URL
            //----------------------------------------------------

            String url = driver.getCurrentUrl();

            System.out.println("Final URL : " + url);

            //----------------------------------------------------
            // Success
            //----------------------------------------------------

            if (url.contains("admin.shopify.com")
                    || url.contains("/store/")) {

                Reporter.log("Login Successful");

                ExtentReport.extentTest.pass("Login Successful");

                return;
            }

            //----------------------------------------------------
            // MFA
            //----------------------------------------------------

            if (url.contains("two-factor")) {

                Reporter.log("Password Accepted");

                Reporter.log("Waiting for Authentication Code");

                ExtentReport.extentTest.info(
                        "Reached Authentication Page");

                return;
            }

            //----------------------------------------------------
            // Returned to Login
            //----------------------------------------------------

            if (url.contains("/login")) {

                System.out.println();

                System.out.println("Returned to Login");

                System.out.println(driver.findElement(
                        By.tagName("body")).getText());

                throw new Exception(
                        "Shopify returned to Login page after clicking Login.");
            }

            throw new Exception(
                    "Unknown Login Flow : " + url);

        }

        catch (Exception e) {

            System.out.println("--------------------------------");

            System.out.println("Current URL : "
                    + driver.getCurrentUrl());

            System.out.println(driver.findElement(
                    By.tagName("body")).getText());

            System.out.println("--------------------------------");

            throw e;
        }
    }

    public void dismissWindowsSecurityPopup() throws Exception {

        // Wait for the Windows Security dialog
        Thread.sleep(2500);

        Robot robot = new Robot();

        // Press ESC to close the Passkey popup
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);

        Thread.sleep(1000);
    }


}

