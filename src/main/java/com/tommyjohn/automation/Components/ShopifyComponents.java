package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ShopifyLocators;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
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

        try {

            wait.until(webDriver ->
                    ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));

            // ==========================
            // Enter Email
            // ==========================

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));

            emailField.clear();
            emailField.sendKeys(createdAccountEmail);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                            "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                    emailField);

            Reporter.log("Email entered successfully");
            ExtentReport.extentTest.info("Email entered successfully");

            // ==========================
            // Continue with Email
            // ==========================

            WebElement continueEmail = wait.until(
                    ExpectedConditions.elementToBeClickable(CONTINUE_EMAIL_CTA));

            continueEmail.click();

            Reporter.log("Clicked Continue with Email");

            // ==========================
            // Get Started
            // ==========================

            WebElement getStarted = wait.until(
                    ExpectedConditions.elementToBeClickable(GET_STARTED));

            getStarted.click();

            Reporter.log("Clicked Get Started");

            // ==========================
            // Close Windows Security Popup
            // ==========================

            dismissWindowsSecurityPopup();

            // ==========================
            // Login Using Different Method
            // ==========================

            WebElement differentMethod = wait.until(
                    ExpectedConditions.elementToBeClickable(LOGIN_USING_DIFFERENT_METHOD));

            differentMethod.click();

            Reporter.log("Clicked Login using Different Method");

            // ==========================
            // Continue with Password
            // ==========================

            WebElement continuePassword = wait.until(
                    ExpectedConditions.elementToBeClickable(CONTINUE_WITH_PASSWORD));

            continuePassword.click();

            Reporter.log("Clicked Continue with Password");

            // ==========================
            // Enter Password
            // ==========================

            WebElement passwordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));

            passwordField.clear();
            passwordField.sendKeys(password);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                            "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));" +
                            "arguments[0].blur();",
                    passwordField);

            Reporter.log("Password entered successfully");

            // ==========================
            // Wait for Login Button
            // ==========================

            wait.until(driver -> {

                WebElement btn = driver.findElement(SHOPIFY_LOGIN_BUTTON);

                String ariaDisabled = btn.getAttribute("aria-disabled");

                return btn.isDisplayed()
                        && btn.isEnabled()
                        && (ariaDisabled == null || ariaDisabled.equals("false"));

            });

            WebElement loginButton = driver.findElement(SHOPIFY_LOGIN_BUTTON);

            System.out.println("Enabled : " + loginButton.isEnabled());
            System.out.println("aria-disabled : " + loginButton.getAttribute("aria-disabled"));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", loginButton);

            Thread.sleep(1000);

            try {

                loginButton.click();

            } catch (Exception ex) {

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", loginButton);

            }

            Reporter.log("Clicked Login");

            Thread.sleep(5000);

            String currentUrl = driver.getCurrentUrl();

            System.out.println("Current URL : " + currentUrl);

            // Retry with ENTER if Shopify returns to login page
            if (currentUrl.contains("/login?rid=")) {

                System.out.println("Returned to Login page. Trying ENTER...");

                passwordField.sendKeys(Keys.ENTER);

                Thread.sleep(5000);

                currentUrl = driver.getCurrentUrl();

                System.out.println("Retry URL : " + currentUrl);
            }

            // Wait for successful navigation
            wait.until(ExpectedConditions.or(

                    ExpectedConditions.urlContains("/login/two-factor/passkey"),

                    ExpectedConditions.urlContains("admin.shopify.com"),

                    ExpectedConditions.urlContains("/store/")

            ));

            Reporter.log("Shopify Login Successful");
            ExtentReport.extentTest.pass("Shopify Login Successful");

        } catch (Exception e) {

            System.out.println("Current URL : " + driver.getCurrentUrl());

            System.out.println(driver.findElement(By.tagName("body")).getText());

            Reporter.log("Shopify Login Failed : " + e.getMessage());
            ExtentReport.extentTest.fail("Shopify Login Failed : " + e.getMessage());

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

