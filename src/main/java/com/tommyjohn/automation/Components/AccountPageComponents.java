package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.AccountPageLocators;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

import com.tommyjohn.automation.commonMethods.GeneralMethods;

public class AccountPageComponents extends AccountPageLocators {
	WebDriver driver;
	public Actions actions;
	String currentUrl;
	SoftAssert softassert = new SoftAssert();
	List<WebElement> list = new ArrayList<WebElement>();
	String text;
	Wait<WebDriver> wait;
	WebElement element;
	GeneralMethods genMethods = new GeneralMethods();

	public AccountPageComponents(WebDriver driver) {
		this.driver = driver;

		wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
				.withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);
	}
	
	/**
	 * This method should be used from AccountLogin Page
	 */
	public void loginflow() {
		genMethods = new GeneralMethods();
		
		//wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(AccountLoginLocators.LOGIN_HEADING_TEXT, 0));
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

	public void navigateTo_AccountPage(String email, String password) throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(NavigationElementComponents.ACCOUNT_NAV_DROPDOWN));
		driver.findElement(HomePageLocators.ACCOUNT_NAV_DROPDOWN).click();
		driver.navigate().to("https://www.tommyjohn.com/account/login");

		wait.until(ExpectedConditions.visibilityOfElementLocated(AccountLoginLocators.LOGIN_HEADING));
		

		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).sendKeys(email);
		driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(password);
		driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();

		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_PAGE_HEADING));

		String loggedInID = driver.findElement(LOGGED_IN_ID).getText();

		if (loggedInID.contentEquals(email))
			throw new Exception("account page is not displayed after successful login");
		Reporter.log("User navigates to account page after successful login");

	}

	/**
	 * 
	 * 1. User must be signed in to view the Account page. 2. User can view Login
	 * Information, Address Book, and Order History. 3. User can "Manage Addresses"
	 * in the Address Book.
	 * 
	 * @throws Exception
	 */
	public void Validate_LoggedContentsOn_AccountPage() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		Negative Validation
//		User must be signed in to view the Account page
		String email =  CustomUtilities.properties.getProperty("loginemail");
		String password =  CustomUtilities.properties.getProperty("password");
		actions = new Actions(driver);
		
//		Hover on Hi, Sign in dropdown
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.ACCOUNT_NAV_DROPDOWN));
			element = driver.findElement(HomePageLocators.ACCOUNT_NAV_DROPDOWN);
			actions.moveToElement(element).perform();
		}catch (TimeoutException e) {
			System.out.println("Hi, Sign in dropdown not found or is not clickable");
			Assert.fail("Hi, Sign in dropdown not found or is not clickable");
		}
		
//		Click My Account Option
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.MYACCOUNT_OPTION));
			element = driver.findElement(HomePageLocators.MYACCOUNT_OPTION);
			actions.click(element).perform();
		}catch (Exception e) {
			System.out.println("My Account option in dropdown not found or is not clickable");
			Assert.fail("My Account option in dropdown not found or is not clickable");
		}

//		1. User must be signed in to view the Account page
//		wait for login page, if login page does not appear check if element LOGGED_IN_ID is present if it is then throw error
		try {
			ExpectedConditions.visibilityOfElementLocated(AccountLoginLocators.LOGIN_HEADING_LOGO);
			System.out.println("Guest user was correctly redirected to login page after clicking on My Account option");
		}catch (TimeoutException e) {
			softassert.fail("After clicking on My account option as a Guest User, User seems to be have been redirected to Account Page as a logged in user");
		}
		
		ExtentReport.extentTest.info("Guest user was correctly redirected to login page after clicking on My Account option");

		
//		Go back to homepage
		driver.navigate().to("https://www.tommyjohn.com");
		Thread.sleep(4000);
		
//		Positive Validation
		navigateTo_AccountPage(email, password);

		softassert.assertTrue(driver.findElement(LOGIN_INFORMATION_TITLE).isDisplayed(),
				"Login Information title is not displayed on account page");
		softassert.assertTrue(driver.findElement(LOGGED_IN_ID).isDisplayed(),
				"Logged-in id is not displayed on 'Login Information' section");

		softassert.assertTrue(driver.findElement(ADDRESS_BOOK_TITLE).isDisplayed(),
				"Address Book title is not displayed on account page");

		 wait.until(ExpectedConditions.elementToBeClickable(MANAGE_ADDRESSES));
		 jse.executeScript("arguments[0].click();" , driver.findElement(MANAGE_ADDRESSES));
		//driver.findElement(MANAGE_ADDRESSES).click();

//		After clicking on Manage Addresses link verify if correctly redirected to addresses page, if facing login issue then handle via re-login
		try{
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(MANAGE_ADDRESS_BOOK_HEADING));
		}catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			System.out.println("Addresses Page is not displayed, Current page url is: " + currentUrl + "\nInstead of https://www.tommyjohn.com/account/addresses");
			System.out.println("Unexpectedly Redirected to Login Page!, Handling via relogin");
			
			try {
				loginflow();
			}catch (TimeoutException e2) {
				currentUrl = driver.getCurrentUrl();
				System.out.println("Addresses Page is not displayed even after performing Re-Login, Current page url is: " + currentUrl);
				Assert.fail("Addresses Page is not displayed even after performing Re-Login, Current page url is: " + currentUrl);
			}
		}

		softassert.assertTrue(driver.getCurrentUrl().equals("https://www.tommyjohn.com/account/addresses"),
				"User Navigated to incorrect page after clicking on Manage Addresses link");

		Thread.sleep(3000);
		softassert.assertTrue(driver.findElement(DEFAULT_SHIPPING_ADDRESS_TITLE).isDisplayed(),
				"Default address shipping title is not displayed inside 'Address Book' section");

		softassert.assertTrue(driver.findElement(ADDITIONAL_ADDRESSES_TITLE).isDisplayed(),
				"Additional addresses title is not displayed inside 'Address Book' section");

		driver.findElement(RETURN_TO_ACCOUNT_PAGE_LINK).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_PAGE_HEADING));

		// softassert.assertTrue(driver.findElement(ADDRESS_CONTENT).isDisplayed(),
		// "Default address shipping contents are not displayed for logged-in user");

		softassert.assertTrue(driver.findElement(ORDER_HISTORY_TITLE).isDisplayed(),
				"Order History title is not displayed on account page");
		list = driver.findElements(ORDER_HISTORY_TABLE_TITLE_HEADING);
		softassert.assertTrue(list.size() > 0, "Order history table has no columns on account page");

		softassert.assertTrue(driver.findElement(FIRST_ORDER_NUMBER).isDisplayed(),
				"Order number is not displayed on Order History table");
		Reporter.log("Account page contents for logged-in user are Displayed :: Correct");
		ExtentReport.extentTest.info("Account page contents for logged-in user are Displayed :: Correct");
		

		text = driver.findElement(FIRST_ORDER_NUMBER).getText();
		
		
		jse.executeScript("window.scrollBy(0,350)");
		//jse.executeScript("arguments[0].click();" , driver.findElement(FIRST_ORDER_NUMBER));
		driver.findElement(FIRST_ORDER_NUMBER).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_NUMBER_HEADING));

		currentUrl = driver.getCurrentUrl();

		Assert.assertTrue(currentUrl.contains("https://www.tommyjohn.com/account/orders/"),
				"Wrong page opened after click on order number from account page");

		String text1 = driver.findElement(ORDER_NUMBER_HEADING).getText();
		String OrderNumber = text1.substring(text1.indexOf("#") + 1, text1.indexOf("-") - 1);

		System.out.println("actual number is: " + OrderNumber + " and expected is: " + text);

		softassert.assertEquals(text, OrderNumber, "Tracking order number is incorrect");
		Reporter.log("Order number is clickable and navigates correctly for track the order");
		ExtentReport.extentTest.info("Order number is clickable and navigates correctly for track the order");

		softassert.assertAll();

	}
}
