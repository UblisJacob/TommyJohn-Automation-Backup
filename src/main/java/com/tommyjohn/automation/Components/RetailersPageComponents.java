package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.RetailersPageLocators;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static com.tommyjohn.automation.PageLocators.ContactUsPageLocators.HELP_PAGETITLE;

public class RetailersPageComponents extends RetailersPageLocators {
    public WebDriver driver;
    Wait<WebDriver> wait;
    List<WebElement> list;
    public static String baseUrl = CustomUtilities.baseUrl;

    public String currentUrl;
    public String currentUrl1;
    public String currentUrl2;
    boolean flag = false;
    public static WebElement element;
    public static WebElement ele1;
    public Actions action;
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    SoftAssert softassert = new SoftAssert();
    WebElement footer_link;
    String text;
    String linkText;
    String pageTitle;
    GeneralMethods objGeneralMethods = new GeneralMethods();

    public RetailersPageComponents(WebDriver driver) {

        this.driver = driver;
        wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
                .withTimeout(Duration.ofSeconds(60)).ignoring(NoSuchElementException.class);

    }

    public void ValidatePageTitleInRetailersPage()
    {
        driver.get("https://www.tommyjohn.com/pages/retailers");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(RETAILERS_PAGE_HEADER));
        softassert.assertTrue(driver.findElement(RETAILERS_PAGE_HEADER).isDisplayed(), "Retailers page title is not displayed.");
        System.out.println(driver.findElement(RETAILERS_PAGE_HEADER).getText());
        softassert.assertEquals(driver.findElement(RETAILERS_PAGE_HEADER).getText(), "Contact Us","Text changed for Help Center page title.");
        Reporter.log("Retailers Page Title is displayed :: Correct.");
        ExtentReport.extentTest.info("Retailers Page Title is displayed :: Correct.");
        softassert.assertAll();





    }


}
