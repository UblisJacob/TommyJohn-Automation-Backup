package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.AccountPageLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.PageLocators.ReOrderPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

import com.tommyjohn.automation.commonMethods.GeneralMethods;

public class ReOrderPageComponents extends ReOrderPageLocators {
	WebDriver driver;
	public Actions action;
	WebElement element;
	String text;
	String productName = null;
	Wait<WebDriver> wait;
	SoftAssert softAssert;
	String currentUrl = null;
	GeneralMethods genMethods;
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	Random random;

	public ReOrderPageComponents(WebDriver driver) {
		this.driver = driver;
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
	}
	
	/**
	 * This method should be used from AccountLogin Page
	 */
	public void loginflow() {
		genMethods = new GeneralMethods();
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(AccountLoginLocators.LOGIN_HEADING_TEXT, 0));
		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).clear();
		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).sendKeys(CustomUtilities.properties.getProperty("loginemail"));
		driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).clear();
		driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(CustomUtilities.properties.getProperty("password"));
		
//		Click on Login button
		try {
			wait.until(ExpectedConditions.elementToBeClickable(AccountLoginLocators.LOGIN_BUTTON));
			genMethods.clickButtonByJS(driver, AccountLoginLocators.LOGIN_BUTTON);
//			driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();
		} catch (TimeoutException | NoSuchElementException e) {
			System.out.println("Login Button not found or is not clickable");
			Assert.fail("Login Button not found or is not clickable");
		}
	}

	public void reorderPagevalidation() throws Exception {

		softAssert = new SoftAssert();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.ACCOUNT_NAV_DROPDOWN));
		driver.findElement(HomePageLocators.ACCOUNT_NAV_DROPDOWN).click();

//		Login as a User
		loginflow();
		
//		Wait for account page to load
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(AccountPageLocators.ACCOUNT_PAGE_HEADING, 0));

		element = driver.findElement(HomePageLocators.ACCOUNT_NAV_DROPDOWN);
		action.moveToElement(element).perform();

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(HEADER_REORDER_LINK));
			element = driver.findElement(HEADER_REORDER_LINK);
			action.click(element).perform();
		} catch (Exception e) {
			System.out.println("Reorder option not displayed");
			Assert.fail("Reorder option not displayed");
		}

//		Wait for Reorder page to load
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(REORDER_TEXT));
		} catch (TimeoutException t1) {
			currentUrl = driver.getCurrentUrl();
			System.out.println("Reorder Page is not displayed, Current page url is: " + currentUrl + "\nInstead of https://www.tommyjohn.com/account?view=reorder");
			System.out.println("Unexpectedly Redirected to Login Page, Handling via relogin");
			try{
				loginflow();
				wait.until(ExpectedConditions.visibilityOfElementLocated(REORDER_TEXT));
			}catch (TimeoutException t2) {
				currentUrl = driver.getCurrentUrl();
				System.out.println("Reorder Page is not displayed even after performing Re-Login, Current page url is: " + currentUrl);
				Assert.fail("Reorder Page is not displayed even after performing Re-Login, Current page url is: " + currentUrl);
			}
		}

		softAssert.assertEquals(driver.findElement(REORDER_TEXT).getText(), "Reorder",
				"Reorder page header is not present on Reorder page");
		softAssert.assertTrue(driver.findElement(PRODUCT_IMAGE).isDisplayed(),
				"Product Images not present on reorder page");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_DETAILS).isDisplayed(),
				"Reorder item details link is not present");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_TITLE).isDisplayed(),
				"Reorder item title is not present");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_VARIANT).isDisplayed(),
				"Reorder item variant is not present");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_PRICE).isDisplayed(),
				"Reorder item price is not present");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_ATC).isDisplayed(),
				"Add to cart option is not present on reorder page");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_WRITEREVIEW).isDisplayed(),
				"Write a review option is not present on reorder page");
		
		// Check gift box is hidden
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		List<WebElement> element = driver.findElements(REORDER_ITEM_TITLE);
		
		for (WebElement titles : element) {
			if (titles.getText().contains("Gift Box"))
				throw new Exception("Product tile of gift box is not hidden from reorder page");
		}
		
		softAssert.assertAll();
		
		System.out.println("total products on reorder page is: " + element.size());
		Reporter.log("total products on reorder page is: " + element.size());
	}

	/**
	 * Adds a Random product to cart from Reorder page and validates if the same is present in flyout cart
	 * @throws Exception
	 */
	public void reorderitem() throws Exception {
		softAssert = new SoftAssert();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		random = new Random();
		genMethods = new GeneralMethods();
		
//		Go to the Reorder page
		reorderPagevalidation();
		
		List<WebElement> list = new ArrayList<WebElement>();
		list = driver.findElements(REORDER_ITEM_ATC);
		
		if (list.size() > 0) {
			int i = random.nextInt(0, list.size());
			System.out.println("random number = " + i);
			
			WebElement productToAdd = driver.findElement(By.cssSelector(".account-reorder-grid>.reorder-item:nth-child("+ i +")>a:nth-child(2)>span"));
			text = productToAdd.getText();
			System.out.println("Adding: " + text);
			genMethods.scrollElementIntoView(driver, productToAdd);
			list.get(i-1).click();
		}else {
			softAssert.fail("No Products on Reorder Page");
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.FIRST_PRODUCT_TITLE_IN_CART));
		String productInCart = driver.findElement(FlyCartPageLocator.FIRST_PRODUCT_TITLE_IN_CART).getText();
		
		softAssert.assertTrue(productInCart.equals(text),
				"Wrong product added to cart, Expected: " + text + "\nActual: " + productInCart);
		element = driver.findElement(FlyCartPageLocator.CHECKOUT_BUTTON);
		jsExecutor.executeScript("arguments[0].click();", element);
		
		softAssert.assertAll();
		
		System.out.println("Product was successfully added to cart after clicking on ATC CTA on Reorder Page");
		Reporter.log("Product was successfully added to cart after clicking on ATC CTA on Reorder Page");
	
	}
	
	/**
	 * Gives a Random number(index) which can be used to select a product from a list
	 * @return int index
	 */
	private int chooseRandomProduct() {
		random = new Random();
		int number = 0;
		List<WebElement> list = new ArrayList<WebElement>();
		list = driver.findElements(REORDER_ITEMS_LIST);
		
		if (list.size() > 0) {
			number = random.nextInt(0, list.size());
//			System.out.println("random number = " + i);
		}else {
			Assert.fail("No Products on Reorder Page");
		}
		
		return number;
	}

	/**
	 * Validates if correct page opened after clicking on Order Details link on Reorder Page
	 * @throws Exception
	 */
	public void orderdetails() throws Exception {
		softAssert = new SoftAssert();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		random = new Random();
		genMethods = new GeneralMethods();
		String orderNumber = null;
		
//		Go to Reorder Page
		reorderPagevalidation();
		
		int index = chooseRandomProduct();
		
		element = genMethods.autoIndex(driver, REORDER_ITEM_DETAILS_GENERIC, index, 1);
		text = element.getText();
		System.out.println("Clicking on: " + text);
		genMethods.scrollElementIntoView(driver, element);
		genMethods.clickButtonByJS(driver, element);
		
		orderNumber = text.split("#")[1];
//		System.out.println(orderNumber);
		
		try {
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ORDERDETAILS_TITLE, 0));
		}catch (Exception e) {
			System.out.println("Order details page didn't open");
			Assert.fail("Order details page didn't open");
		}
		String orderPageTitle = driver.findElement(ORDERDETAILS_TITLE).getText();
		
		softAssert.assertTrue(orderPageTitle.contains(orderNumber), "Incorrect Order Page opened after clicking on Order #" + orderNumber);
		
		softAssert.assertAll();
		
		System.out.println("Correct page opened after clicking on Order #" + orderNumber);
		Reporter.log("Correct page opened after clicking on Order #" + orderNumber);
	}

	/**
	 * Validates if correct page(PDP) opened after clicking on a product image on Reorder page
	 * @throws Exception
	 */
	public void reorderpagetopdp() throws Exception {
		softAssert = new SoftAssert();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		random = new Random();
		genMethods = new GeneralMethods();
		
//		Go to Reorder Page
		reorderPagevalidation();
		
//		Get a random product
		int index = chooseRandomProduct();
		
		element = genMethods.autoIndex(driver, REORDER_ITEM_TITLE_GENERIC, index, 1);
		WebElement productImg = genMethods.autoIndex(driver, PRODUCT_IMAGE_GENERIC, index, 1);
		
		productName = element.getText();
		
		System.out.println("Clicking on: " + productName);
		genMethods.scrollElementIntoView(driver, element);
		genMethods.clickButtonByJS(driver, productImg);
		
//		Wait for PDP to load
		String productNameOnPDP = null;
		
		try {
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ProductDetailsPageLocators.PRODUCT_TITLE, 0));
			productNameOnPDP = driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
			softAssert.assertTrue(productNameOnPDP.equals(productName), "Wrong product page opened from reorder page, Expected: "+ productName + "\nFound:" + productNameOnPDP);
		}catch (TimeoutException e) {
			System.out.println("Product Name was not found on PDP");
			softAssert.fail("Product Name was not found on PDP");
		}
		
		softAssert.assertAll();
		
		System.out.println("Correct Page opened after clicking on product image of " + productName);
		Reporter.log("Correct Page opened after clicking on product image of "+ productName);
	}
	
	/**
	 * Validates if Reviews Page opened after clicking on CTA on Re-order page
	 * @throws Exception
	 */
	public void reviewsPage_fromReorder() throws Exception {
		softAssert = new SoftAssert();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		random = new Random();
		genMethods = new GeneralMethods();
		
//		Go to Reorder Page
		reorderPagevalidation();
		
//		Get a random product
		int index = chooseRandomProduct();
		
		WebElement writeReviewCTA = genMethods.autoIndex(driver, REORDER_ITEM_WRITEREVIEW_GENERIC, index, 1);
		genMethods.scrollElementIntoView(driver, writeReviewCTA);
		
		genMethods.clickButtonByJS(driver, writeReviewCTA);

//		Wait for Write Review page to load
		try{
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ProductDetailsPageLocators.REVIEWS_TITLE, 0));
		}catch (Exception e) {
			System.out.println("Ratings & Reviews page didnt open after clicking on Write a Review CTA");
		}
		
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.WRITE_REVIEW_TITLE).getText(),
				"Write a Review", "Language mismatch for Write a Review title");
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.NAME).getText(), "* Name",
				"Laguage mismatch for Name ");
		driver.findElement(ProductDetailsPageLocators.NAME_TEXTBOX).sendKeys("Vijeta");
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.EMAIL).getText(), "* Email",
				"Laguage mismatch for Email ");
		driver.findElement(ProductDetailsPageLocators.EMAIL_TEXTBOX).sendKeys("vijeta@tommyjohnwear.com");
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.LOCATION).getText(),
				"Location (Optional)", "Laguage mismatch for Location ");
		driver.findElement(ProductDetailsPageLocators.LOCATION_TEXTBOX).sendKeys("India");
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.FIT).getText(), "Fit",
				"Laguage mismatch for Fit	 ");
		WebElement fit = driver.findElement(ProductDetailsPageLocators.FIT_SELECTION);
		jsExecutor.executeScript("arguments[0].click();", fit);
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.OVERALLRATING).getText(),
				"* Overall Rating", "Laguage mismatch for Overall Rating	 ");
		WebElement stars = driver.findElement(ProductDetailsPageLocators.STAR_SELECTION);
		jsExecutor.executeScript("arguments[0].click();", stars);
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.HEADLINE).getText(), "* Add a Headline",
				"Laguage mismatch for Headline ");
		driver.findElement(ProductDetailsPageLocators.HEADLINE_TEXTBOX).sendKeys("Nice Product");
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.REVIEW).getText(), "* Review",
				"Laguage mismatch for Review	 ");
		driver.findElement(ProductDetailsPageLocators.REVIEW_TEXTBOX).sendKeys("Must Buy");

		softAssert.assertTrue(driver.findElement(ProductDetailsPageLocators.SUBMIT_BTN).isEnabled(),
				"Submit button not enabled");
		
		softAssert.assertAll();
		
		System.out.println("Review Page from reorder is correct");
		Reporter.log("Review Page from reorder is correct");

	}

}
