package com.tommyjohn.automation.Components;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.BlogPageLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.commonMethods.GeneralMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.utils.CustomUtilities;

public class BlogPageComponents extends BlogPageLocators{
	
	public WebDriver driver;
	GeneralMethods objGeneralMethods = new GeneralMethods();
	
	String blogUrl= "https://www.tommyjohn.com/blogs/news";
	
	
	public BlogPageComponents(WebDriver driver) {
		this.driver = driver;
	}
	
	public void validateBlog() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		String readMore = "";
		String prevLink = "";
		String blogLink = "";
		String nextLink = "";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.navigate().to(blogUrl);
		
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(BLOG_LINK));
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		Thread.sleep(3000);
		objGeneralMethods.clickButtonByJS(driver, BLOG_LINK);*/
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(BLOG_TITLE));
		
		if(driver.findElement(BLOG_TITLE).isDisplayed()) {
			System.out.println("BLOG TITLE is displayed on Blog Page");
			Reporter.log("BLOG TITLE is displayed on Blog Page");
			ExtentReport.extentTest.info("BLOG TITLE is displayed on Blog Page");
		}
		else {
			Reporter.log("BLOG TITLE is not displayed on Blog Page");
			ExtentReport.extentTest.info("BLOG TITLE is not displayed on Blog Page");
			throw new Exception("BLOG TITLE is not displayed on Blog Page");
		}
		
		if(driver.findElement(ARTICLE_IMG).isDisplayed()) {
			System.out.println("ARTICLE IMG is displayed ");
			Reporter.log("ARTICLE IMG is displayed on Blog Page");
			ExtentReport.extentTest.info("ARTICLE IMG is displayed on Blog Page");
			
			if(driver.findElement(ARTICLE_HEADING).isDisplayed()) {
				System.out.println("ARTICLE Summary is displayed ");
				Reporter.log("ARTICLE Summary is displayed on Blog Page");
				ExtentReport.extentTest.info("ARTICLE Summary is displayed on Blog Page");
			}
			
			else {
				Reporter.log("ARTICLE Summary is not displayed on Blog Page");
				ExtentReport.extentTest.info("ARTICLE Summary is not displayed on Blog Page");
				throw new Exception("ARTICLE Summary is not displayed ");
			}
		}
		else {
			Reporter.log("ARTICLE IMG is not displayed on Blog Page");
			ExtentReport.extentTest.info("ARTICLE IMG is not displayed on Blog Page");
			throw new Exception("ARTICLE IMG is not displayed ");
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(READ_MORE_ARTICLE));
		if(driver.findElement(READ_MORE_ARTICLE).isDisplayed()) {
			System.out.println("READ MORE link is displayed on Blog Page");
			Reporter.log("READ MORE link is displayed on Blog Page");
			ExtentReport.extentTest.info("READ MORE link is displayed on Blog Page");
			readMore = "https://www.tommyjohn.com/blogs/news/underwear-size-chart";
			jse.executeScript("arguments[0].click();", driver.findElement(READ_MORE_ARTICLE));
			Thread.sleep(3000);
			if(driver.getCurrentUrl().contains(readMore))
			{
				System.out.println("Read more link redirects to correct page");
				Reporter.log("Read more link redirects to correct page");
				ExtentReport.extentTest.info("Read more link redirects to correct page");
			}
			else {
				Reporter.log("Read more link doesn't redirect to correct page");
				ExtentReport.extentTest.info("Read more link doesn't redirect to correct page");
				throw new Exception("Read more link doesn't redirect to correct page");
			}
			driver.navigate().to(blogUrl);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		}
		else {
			Reporter.log("READ MORE link is not displayed on Blog Page");
			ExtentReport.extentTest.info("READ MORE link is not displayed on Blog Page");
			throw new Exception("READ MORE link is not displayed on Blog Page");
		}
		
		if(driver.findElement(BLOG_CARD_IMG).isDisplayed()) {
			System.out.println("BLOG_CARD_IMG is displayed ");
			Reporter.log("BLOG_CARD_IMG is displayed on Blog Page");
			ExtentReport.extentTest.info("BLOG_CARD_IMG is displayed on Blog Page");
			
			if(driver.findElement(BLOG_CARD_TITLE).isDisplayed()) {
				System.out.println("BLOG_CARD_TITLE is displayed ");
				Reporter.log("BLOG_CARD_TITLE is displayed on Blog Page");
				ExtentReport.extentTest.info("BLOG_CARD_TITLE is displayed on Blog Page");
			}
			
			else {
				Reporter.log("BLOG_CARD_TITLE is not displayed on Blog Page");
				ExtentReport.extentTest.info("BLOG_CARD_TITLE is not displayed on Blog Page");
				throw new Exception("BLOG_CARD_TITLE is not displayed ");
			}
		}
		else {
			Reporter.log("BLOG_CARD_IMG is not displayed on Blog Page");
			ExtentReport.extentTest.info("BLOG_CARD_IMG is not displayed on Blog Page");
			throw new Exception("BLOG_CARD_IMG is not displayed ");
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(READ_MORE_BLOG_CARD));
		objGeneralMethods.scrollElementIntoView(driver, READ_MORE_BLOG_CARD);
		if(driver.findElement(READ_MORE_BLOG_CARD).isDisplayed()) {
			System.out.println("READ MORE blog card link is displayed");
			Reporter.log("READ MORE blog card link is displayed");
			ExtentReport.extentTest.info("READ MORE blog card link is displayed");
			readMore = "https://www.tommyjohn.com/blogs/news/summer-pajamas-for-women";
			jse.executeScript("arguments[0].click();", driver.findElement(READ_MORE_BLOG_CARD));
			Thread.sleep(3000);
			if(driver.getCurrentUrl().contains(readMore))
			{
				System.out.println("Read more blog card link redirects to correct page");
				Reporter.log("Read more blog card link redirects to correct page");
				ExtentReport.extentTest.info("Read more blog card link redirects to correct page");
			}
			else {
				Reporter.log("Read more blog card link doesn't redirect to correct page");
				ExtentReport.extentTest.info("Read more blog card link doesn't redirect to correct page");
				throw new Exception("Read more blog card link doesn't redirect to correct page");
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			blogLink = driver.getCurrentUrl();
			
			softAssert.assertTrue(driver.findElement(BLOG_RETURN_LINK).isDisplayed(), "BLOG_RETURN_LINK is not displayed");
			softAssert.assertTrue(driver.findElement(BLOG_ARTICLE_DATE).isDisplayed(), "BLOG_ARTICLE_DATE is not displayed");
			softAssert.assertTrue(driver.findElement(BLOG_ARTICLE_TITLE).isDisplayed(), "BLOG_ARTICLE_TITLE is not displayed");
			softAssert.assertTrue(driver.findElement(BLOG_ARTICLE_CONTENT).isDisplayed(), "BLOG_ARTICLE_CONTENT is not displayed");
			softAssert.assertTrue(driver.findElement(BLOG_PREV_ARTICLE).isDisplayed(), "BLOG_PREV_ARTICLE is not displayed");
			softAssert.assertTrue(driver.findElement(BLOG_PREV_LINK).isDisplayed(), "BLOG_PREV_LINK is not displayed");
			softAssert.assertTrue(driver.findElement(BLOG_NEXT_ARTICLE).isDisplayed(), "BLOG_NEXT_ARTICLE is not displayed");
			softAssert.assertTrue(driver.findElement(BLOG_NEXT_LINK).isDisplayed(), "BLOG_NEXT_LINK is not displayed");
			
			objGeneralMethods.scrollElementIntoView(driver, BLOG_PREV_LINK);
			prevLink = driver.findElement(BLOG_PREV_LINK).getAttribute("href");
			jse.executeScript("arguments[0].click();", driver.findElement(BLOG_PREV_LINK));
			Thread.sleep(3000);
			if(driver.getCurrentUrl().contains(prevLink))
			{
				System.out.println("Previous Blog link redirects to correct page");
				Reporter.log("Previous Blog link redirects to correct page");
				ExtentReport.extentTest.info("Previous Blog link redirects to correct page");
			}
			else {
				Reporter.log("Previous Blog link doesn't redirect to correct page");
				ExtentReport.extentTest.info("Previous Blog link doesn't redirect to correct page");
				throw new Exception("Previous Blog link doesn't redirect to correct page");
			}
			
			driver.navigate().to(blogLink);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			wait.until(ExpectedConditions.visibilityOfElementLocated(BLOG_NEXT_LINK));
			objGeneralMethods.scrollElementIntoView(driver, BLOG_NEXT_LINK);
			
			nextLink = driver.findElement(BLOG_NEXT_LINK).getAttribute("href");
			jse.executeScript("arguments[0].click();", driver.findElement(BLOG_NEXT_LINK));
			Thread.sleep(3000);
			if(driver.getCurrentUrl().contains(nextLink))
			{
				System.out.println("Next Blog link redirects to correct page");
				Reporter.log("Next Blog link redirects to correct page");
				ExtentReport.extentTest.info("Next Blog link redirects to correct page");
			}
			else {
				Reporter.log("Next Blog link doesn't redirect to correct page");
				ExtentReport.extentTest.info("Next Blog link doesn't redirect to correct page");
				throw new Exception("Next Blog link doesn't redirect to correct page");
			}
			
			driver.navigate().to(blogUrl);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(READ_MORE_ARTICLE));
		objGeneralMethods.scrollElementIntoView(driver, READ_MORE_ARTICLE);
		//jse.executeScript("arguments[0].scrollIntoView(true);", LOAD_MORE);
		if(driver.findElement(READ_MORE_ARTICLE).isDisplayed()) {
			System.out.println("LOAD_MORE link is displayed on Blog Page");
			Reporter.log("LOAD_MORE is displayed on Blog Page");
			ExtentReport.extentTest.info("LOAD_MORE is displayed on Blog Page");
			jse.executeScript("arguments[0].click();", driver.findElement(READ_MORE_ARTICLE));
		}
		else {
			Reporter.log("LOAD_MORE link is not displayed on Blog Page");
			ExtentReport.extentTest.info("LOAD_MORE link is not displayed on Blog Page");
			throw new Exception("LOAD_MORE link is not displayed on Blog Page");
		}
		
		softAssert.assertAll();
		
	}
}