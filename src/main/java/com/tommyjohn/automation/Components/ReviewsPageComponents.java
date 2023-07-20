package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.ReviewsPageLocators;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ReviewsPageComponents extends ReviewsPageLocators {
	public WebDriver driver;
	Wait<WebDriver> wait;
	public static String baseUrl = CustomUtilities.baseUrl;
	public String currentUrl;
	public static WebElement element;
	public JavascriptExecutor jse;
	
	SoftAssert softassert = new SoftAssert();
	GeneralMethods genMethods = new GeneralMethods();

	public ReviewsPageComponents(WebDriver driver) {

		this.driver = driver;

		wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
				.withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);

	}
		public void validateReviewsPage() throws InterruptedException {
			new HomePageComponents(driver).navigateToReviewsPage();
			//validate reviews page title
			softassert.assertEquals(driver.findElement(PAGE_TITLE).getText(), "Tommy John Reviews", "Page Title is not correct");	
			softassert.assertEquals(driver.findElement(PAGE_TITLE).getCssValue("font-size"), "28px", "Font-size of title is not correct");	
			
			//validate subtext
			softassert.assertEquals(driver.findElement(PAGE_TITLE_SUBTEXT).getCssValue("font-size"), "16px", "Font-size of Subtext is not correct");
			
			//Validate total reviews
			WebElement ratings = driver.findElement(TOTAL_STAR_RATINGS);
			WebElement reviews = driver.findElement(TOTAL_REVIEWS);
			softassert.assertTrue(ratings.isDisplayed() , "Ratings are not present");
			softassert.assertTrue(reviews.isDisplayed() , "Reviews are not present");
			softassert.assertEquals(ratings.getCssValue("font-size"), "14px", "Font-size of ratings is not correct");
			softassert.assertEquals(reviews.getCssValue("font-size"), "13px", "Font-size of reviews is not correct");
			
			//validateCX
			WebElement cxuser = driver.findElement(CX_REVIEWS);
			WebElement cxusertitle = driver.findElement(CX_REVIEWS_TITLE);
			WebElement cxusersubtitle = driver.findElement(CX_REVIEWS_SUBTITLE);
			softassert.assertTrue(cxuser.isDisplayed() , "User Feedbacks are not present");
			softassert.assertTrue(cxusertitle.isDisplayed() , "User Feedback Title is not present");
			softassert.assertTrue(cxusersubtitle.isDisplayed() , "User Feedback Sub-Title is not present");
			
			softassert.assertEquals(cxusertitle.getCssValue("font-size"), "18px", "Font-size of reviews is not correct");
			softassert.assertEquals(cxusersubtitle.getCssValue("font-size"), "14px", "Font-size of reviews is not correct");
			
			//validate Bottom section on Reviews Page including Links
			softassert.assertTrue(driver.findElement(SECTION_TITLE).isDisplayed(), "Section Title at the bottom is missing");
			softassert.assertTrue(driver.findElement(SECTION_LINK1).isDisplayed(), "Section Link 1 at the bottom is missing");
			softassert.assertTrue(driver.findElement(SECTION_LINK2).isDisplayed(), "Section Link 2 at the bottom is missing");
			softassert.assertTrue(driver.findElement(SECTION_LINK3).isDisplayed(), "Section Link 3 at the bottom is missing");
			
			
			//validate pagination
			jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,2500)");
			softassert.assertTrue(driver.findElement(PAGES).isDisplayed(), "Pagination at the bottom is missing");
			List<WebElement> pages = driver.findElements(PAGES);
			int num = pages.size();
			if(num>1) {
				for(int i=1;i<num;i++)
				{
					WebElement pge = driver.findElement(By.cssSelector(".yotpo-pager>a:nth-child("+i+")"));
					jse.executeScript("arguments[0].click();", pge);
					Thread.sleep(3000);				
				}
			}else System.out.println("Pagination is not displayed");
			Reporter.log("Pagination is Displayed :: Clickable");
			ExtentReport.extentTest.info("Pagination is Displayed :: Clickable");
			softassert.assertAll();
			
			
		}
}
