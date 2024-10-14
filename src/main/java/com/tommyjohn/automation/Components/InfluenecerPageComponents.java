package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.InfluencerPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static com.tommyjohn.automation.ADACheck.ADACheckMethods.softAssert;

public class InfluenecerPageComponents extends InfluencerPageLocators {

    public WebDriver driver;
    Wait<WebDriver> wait;
    List<WebElement> list;
    public static String baseUrl = CustomUtilities.baseUrl;

    public InfluenecerPageComponents(WebDriver driver) {

        this.driver = driver;

        wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
                .withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);

    }

    public void VerifyFavoritesRecs()
    {
        SoftAssert softAssert = new SoftAssert();

        driver.get("https://www.tommyjohn.com/pages/influencer-lp8-16");
        wait.until(ExpectedConditions.visibilityOfElementLocated(FAVORITES_SECTION));

        softAssert.assertTrue(driver.findElement(FAVORITES_SECTION).isDisplayed(),"Favorites section is not available");
        Reporter.log("Favorites section is available");
        ExtentReport.extentTest.info("Favorites section is available");

    }

    public void VerifyTrendingThisWeek()
    {

        SoftAssert softAssert = new SoftAssert();
        driver.get("https://www.tommyjohn.com/pages/influencer-lp8-16");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TRENDINGTHISWEEK));

        softAssert.assertTrue(driver.findElement(TRENDINGTHISWEEK).isDisplayed(),"Trending this week section is not available");
        Reporter.log("Trending this week section is available");
        ExtentReport.extentTest.info("Trending this week section is available");


    }

    public void VerifyShopByCategory()
    {

        SoftAssert softAssert = new SoftAssert();
        driver.get("https://www.tommyjohn.com/pages/influencer-lp8-16");
        wait.until(ExpectedConditions.visibilityOfElementLocated(SHOPBYCATEGORY));

        softAssert.assertTrue(driver.findElement(SHOPBYCATEGORY).isDisplayed(),"Shop By Category section is not available");
        Reporter.log("Shop By Category section is available");
        ExtentReport.extentTest.info("Shop By Category section is available");


    }




}
