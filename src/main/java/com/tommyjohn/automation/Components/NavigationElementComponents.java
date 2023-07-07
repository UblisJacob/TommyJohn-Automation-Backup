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

import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.AccountPageLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HelpCenterPageLocators;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.QuizPageLocators;
import com.tommyjohn.automation.PageLocators.ReOrderPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

import com.tommyjohn.automation.commonMethods.GeneralMethods;

public class NavigationElementComponents extends HomePageLocators {

	WebDriver driver;
	Wait<WebDriver> wait;
	public static String baseUrl = CustomUtilities.baseUrl;
	public String currentUrl;
	public String pageHeader;
	public static WebElement element;
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	Actions action;
	SoftAssert softassert;
	GeneralMethods genMethods;

	public NavigationElementComponents(WebDriver driver) {
		this.driver = driver;

		wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
				.withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);
	}

	/**
	 * This method validates User is able to see up to four links to the left of the
	 * Tommy John logo.
	 * 
	 * @author Sandesh
	 */
	public void validateNavLinksDesktop() {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		String linkText = null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(LEVEL_ZERO_LINKS_LIST));

		List<WebElement> linksList = driver.findElements(LEVEL_ZERO_LINKS_LIST);

		softassert.assertTrue(linksList.size() <= 4,
				"No. of Links on Left Side of TJ logo is: " + linksList.size() + "No. of links should be upto 4");

		System.out.println("No. of Links on Left Side of TJ logo is: " + linksList.size());

		for (int i = 1; i <= linksList.size(); i++) {
			linkText = genMethods.autoIndex(driver, LEVEL_ZERO_LINKS_TEXT, i, 1).getText();
			WebElement link = genMethods.autoIndex(driver, LEVEL_ZERO_SPECIFIC_OPTION, i, 1);

			if (link.isDisplayed()) {
				System.out.println("Link Text is '" + linkText + "' and it is Displayed");
			} else {
				softassert.fail("Link Text is '" + linkText + "' and it is Not Displayed");
			}
		}

		softassert.assertAll();

		Reporter.log("No. of Links on Left Side of TJ logo is: " + linksList.size());
		Reporter.log("Link Text is '" + linkText + "' and it is Displayed");
	}

	/**
	 * User should be able to see the Tommy John logo in the middle of the header.
	 * User should see search, account and cart elements.
	 * 
	 * @author Sandesh
	 */
	public void validateLogoAndRightSideLinksDesktop() {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();

		wait.until(ExpectedConditions.visibilityOfElementLocated(LEVEL_ZERO_LINKS_LIST));

//		1. User should be able to see the Tommy John logo in the middle of the header.
		WebElement TJLOGO = driver.findElement(HOMEPAGE_LOGO);
		String alignment = TJLOGO.getCssValue("text-align");

		softassert.assertTrue(TJLOGO.isDisplayed(), "TJ Logo in header is not Displayed");
		softassert.assertTrue(alignment.equals("center"), "TJ Logo in header is not center aligned");

//		2. User should see search, account and cart elements.
		List<WebElement> elementsList = driver.findElements(HEADER_RIGHT_SIDE_ELEMENTS_LIST);

		softassert.assertTrue(elementsList.size() == 3, "No. of elements on Right Side of TJ logo is: "
				+ elementsList.size() + "No. of elements should be upto 3");

		System.out.println("No. of elements on Right Side of TJ logo is: " + elementsList.size());

		for (int i = 1; i <= elementsList.size(); i++) {
			element = genMethods.autoIndex(driver, HEADER_RIGHT_SIDE_SPECIFIC_ELEMENT, i, 1);
			softassert.assertTrue(element.isDisplayed(), "Element No." + i + " is not displayed");
		}

		softassert.assertAll();

		Reporter.log("TJ Logo is displayed and is centrally aligned");
		Reporter.log("No. of elements on Right Side of TJ logo is: " + elementsList.size());
	}

	/**
	 * User should be able to click and type in the search bar.
	 * 
	 * @author Sandesh
	 */
	public void validateSearchBarDesktop() {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);

		wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_HEADER));

		try {
			wait.until(ExpectedConditions.elementToBeClickable(SEARCH_TEXTBOX));
		} catch (Exception e) {
			Assert.fail("search bar is not clickable after trying for 30 seconds");
		}

		System.out.println("Search Text Box is Clickable");

		WebElement searchTXTBX = driver.findElement(SEARCH_TEXTBOX);
		WebElement searchIcon = driver.findElement(SEARCH_ICON);

		if (searchTXTBX.isDisplayed()) {
			action.click(searchIcon).perform();
			action.click(searchTXTBX).perform();

			String display = driver.findElement(SEARCH_SUGGESTION_CONTAINER).getAttribute("style");

			softassert.assertTrue(display.contains("block"),
					"Desktop Search Suggestion did not come up after clicking on Search box");

			String searchText = "skin";

			searchTXTBX.sendKeys(searchText);

			wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_SUGGESTION));

			System.out.println("Product suggestions have appeared");

			String suggestions = driver.findElement(PRODUCT_SUGGESTION).getText();

			softassert.assertTrue(suggestions.contains(searchText),
					"User was not able to type in search box, or the suggestions did not appear");

		} else {
			System.out.println("Search text box is not displayed");
			softassert.assertTrue(searchTXTBX.isDisplayed(), "Search Text box is not displayed");
		}

		softassert.assertAll();

		Reporter.log("Search text box is displayed");
		Reporter.log("User is able to type in search box and suggestions were displayed");
	}

	/**
	 * 
	 * If a user is not signed in, they should see "Sign In." On hover, "Sign In"
	 * should display a see a dropdown with My Account, Help Center, and Sign In.
	 * Clicking "Sign In" should take the user to the Login page. Clicking "My
	 * Account" should take the user to the Login page. Clicking "Help Center"
	 * should take the user to the Help Center.
	 * 
	 * @author Sandesh
	 */
	public void validateElementsWhenNotSignedInDesktop() {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);

		wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_HEADER));

//		1. If a user is not signed in, they should see "Sign In."

		String statusText = driver.findElement(HEADER_LOGIN_TITLE).getText();

		System.out.println("Header Login title says: " + statusText);

		softassert.assertTrue(statusText.contains("Hi, Sign in"),
				"When User is not signed in, Sign in text should say, 'Hi, Sign in'  instead it says " + statusText);

//		2. On hover, "Sign In" should display a see a dropdown with My Account, Help Center, and Sign In.
		action.moveToElement(driver.findElement(ACCOUNT_NAV_DROPDOWN)).perform();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<String> options = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			WebElement option = genMethods.autoIndex(driver, ACCOUNT_NAV_DROPDOWN_OPTIONS, i, 1);
			String optionText = option.getText();
			options.add(optionText);
			softassert.assertTrue(option.isDisplayed(), optionText + "option is not displayed");
		}

		softassert.assertTrue(options.contains("My Account"), "Options List does not contain option 'My Account'");
		softassert.assertTrue(options.contains("Help Center"), "Options List does not contain option 'Help Center'");
		softassert.assertTrue(options.contains("Sign In"), "Options List does not contain option 'Sign In'");

		System.out.println("User can see the following options in dropdown " + options);

//		3. Clicking "Sign In" should take the user to the Login page.

		WebElement signIn = driver.findElement(SIGNIN_OPTION);
		action.click(signIn).perform();

		try {
			wait.until(ExpectedConditions.urlContains("https://www.tommyjohn.com/account/login"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("After clicking on Sign in, Page Url is: " + currentUrl);
		} catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail(
					"Navigated to page with incorrect url, Expected url: https://www.tommyjohn.com/account/login Found: "
							+ currentUrl);
			System.out.println("After clicking on Sign in, Page Url is: " + currentUrl);
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(AccountLoginLocators.LOGIN_HEADING_TEXT));
		pageHeader = driver.findElement(AccountLoginLocators.LOGIN_HEADING_TEXT).getText();

		softassert.assertTrue(pageHeader.equals("Login"),
				"Wrong page opened after clicking on sign in option, expected page with header: 'Login'");

		driver.navigate().back();

//		4. Clicking "My Account" should take the user to the Login page.

		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAV_DROPDOWN));

		action.moveToElement(driver.findElement(ACCOUNT_NAV_DROPDOWN)).perform();

		WebElement myAccount = driver.findElement(MYACCOUNT_OPTION);
		action.click(myAccount).perform();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		try {
			wait.until(ExpectedConditions.urlContains("https://www.tommyjohn.com/account/login"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("After clicking on My Account, Page Url is: " + currentUrl);
		} catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail(
					"Navigated to page with incorrect url, Expected url: https://www.tommyjohn.com/account/login Found: "
							+ currentUrl);
			System.out.println("After clicking on My Account, Page Url is: " + currentUrl);
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.ACCOUNT_PAGE_HEADING_LOGIN));
		pageHeader = driver.findElement(AccountPageLocators.ACCOUNT_PAGE_HEADING_LOGIN).getText();

		softassert.assertTrue(pageHeader.equals("Login"),
				"Wrong page opened after clicking on My Account option, expected page with header: 'Login'");

		driver.navigate().back();

//		5. Clicking "Help Center" should take the user to the Help Center.

		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAV_DROPDOWN));

		action.moveToElement(driver.findElement(ACCOUNT_NAV_DROPDOWN)).perform();

		WebElement helpCenter = driver.findElement(HELP_CENTER);
		action.click(helpCenter).perform();

		try {
			wait.until(ExpectedConditions.urlContains("https://help.tommyjohn.com/hc/en-us"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("After clicking on Help Center, Page Url is: " + currentUrl);
		} catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail(
					"Navigated to page with incorrect url, Expected url: https://help.tommyjohn.com/hc/en-us Found: "
							+ currentUrl);
			System.out.println("After clicking on Help Center, Page Url is: " + currentUrl);
		}

		//below will fail due to help page security to detect automated browser
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy());
//
//		pageHeader = driver.findElement(HelpCenterPageLocators.PAGE_TITLE).getText();
//
//		softassert.assertTrue(pageHeader.equals("Help Center"),
//				"Wrong page opened after clicking on My Account option, expected page with header: 'Help Center'");

		softassert.assertAll();

		Reporter.log("When user is not signed in correct text is displayed in Login Header");
		Reporter.log("After hovering on Sign in, Dropdown appeared and contained correct options");
		Reporter.log("My Account option is Displayed and Navigated to correct page");
		Reporter.log("Help Center option is Displayed and Navigated to correct page");
		Reporter.log("Sign in option is Displayed and Navigated to correct page");

	}


	/**
	 * 
	 * 1. If a user is signed in, they should see "Hi, [Name]"
	 * 2. On hover, the signed in account should display My Account, Reorder and Sign Out.
	 * 3. Clicking My Account should take the user to the Account page.
	 * 4. Clicking Reorder should take the user to the Reorder page.
	 * 5. Clicking Sign Out should log user out of their account.
	 * 
	 * @author Sandesh
	 * @throws Exception 
	 */
	public void validateElementsWhenSignedInDesktop() throws Exception {

//		Sign in as a user
		signIn("testuserQA1@gmail.com", "Test@123");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String loginInfo = driver.findElement(AccountPageLocators.LOGGED_IN_ID).getText();
		System.out.println(loginInfo);
		String firstName = loginInfo.split("\n")[0].split(" ")[0];
		System.out.println(firstName);
		
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);

		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAV_DROPDOWN));

//		1. If a user is signed in, they should see "Hi, [Name]"
		String statusText = driver.findElement(HEADER_LOGIN_TITLE).getText();

		System.out.println("Header Login title says: " + statusText);

		softassert.assertTrue(statusText.contains("Hi, " + firstName),
				"When User is signed in, Sign in text should say, 'Hi, [Name]'  instead it says " + statusText);

//		2. On hover, the signed in account should display My Account, Reorder and Sign Out.
		action.moveToElement(driver.findElement(ACCOUNT_NAV_DROPDOWN)).perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(MYACCOUNT_OPTION));

		List<String> options = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			WebElement option = genMethods.autoIndex(driver, ACCOUNT_NAV_DROPDOWN_OPTIONS, i, 1);
			String optionText = option.getText();
			options.add(optionText);
			softassert.assertTrue(option.isDisplayed(), optionText + "option is not displayed");
		}

		softassert.assertTrue(options.contains("My Account"), "Options List does not contain option 'My Account'");
		softassert.assertTrue(options.contains("Reorder"), "Options List does not contain option 'Reorder'");
		softassert.assertTrue(options.contains("Sign Out"), "Options List does not contain option 'Sign Out'");

		System.out.println("User can see the following options in dropdown " + options);

//		3. Clicking Reorder should take the user to the Reorder page.

		WebElement reorder = driver.findElement(REORDER);
//		reorder.click();
		genMethods.clickButtonByJS(driver, reorder);

		try {
			wait.until(ExpectedConditions.urlContains("https://www.tommyjohn.com/account?view=reorder"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("After clicking on My Account, Page Url is: " + currentUrl);
		} catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail(
					"Navigated to page with incorrect url, Expected url: https://www.tommyjohn.com/account?view=reorder Found: "
							+ currentUrl);
			System.out.println("After clicking on Reorder, Page Url is: " + currentUrl);
		}
		

		try{
			pageHeader = driver.findElement(ReOrderPageLocators.REORDER_TEXT).getText();
		}catch (NoSuchElementException e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ReOrderPageLocators.REORDER_TEXT));
			pageHeader = driver.findElement(ReOrderPageLocators.REORDER_TEXT).getText();
		}

		softassert.assertTrue(pageHeader.equals("Reorder"),
				"Wrong page opened after clicking on Reorder option, expected page with header: 'Reorder', Found: " + pageHeader);

		driver.navigate().back();
		
//		4. Clicking My Account should take the user to the Account page.

		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAV_DROPDOWN));

		action.moveToElement(driver.findElement(ACCOUNT_NAV_DROPDOWN)).perform();
		
		WebElement myAccount = driver.findElement(MYACCOUNT_OPTION);
		genMethods.clickButtonByJS(driver, myAccount);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		try {
			wait.until(ExpectedConditions.urlContains("https://www.tommyjohn.com/account"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("After clicking on My Account, Page Url is: " + currentUrl);
		} catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail(
					"Navigated to page with incorrect url, Expected url: https://www.tommyjohn.com/account Found: "
							+ currentUrl);
			System.out.println("After clicking on My Account, Page Url is: " + currentUrl);
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(AccountPageLocators.ACCOUNT_PAGE_HEADING));
		pageHeader = driver.findElement(AccountPageLocators.ACCOUNT_PAGE_HEADING).getText();

		softassert.assertTrue(pageHeader.equals("Account"),
				"Wrong page opened after clicking on My Account option, expected page with header: 'Account'");

		driver.navigate().back();

//		5. Clicking Sign Out should log user out of their account.

		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAV_DROPDOWN));

		action.moveToElement(driver.findElement(ACCOUNT_NAV_DROPDOWN)).perform();

		WebElement signout = driver.findElement(AccountPageLocators.SIGN_OUT_LINK);
		action.click(signout).perform();

		try {
			wait.until(ExpectedConditions.urlToBe("https://www.tommyjohn.com/"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("After clicking on Help Center, Page Url is: " + currentUrl);
		} catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail("Navigated to page with incorrect url, Expected url: https://www.tommyjohn.com/ Found: "
							+ currentUrl);
			System.out.println("After clicking on Sign Out, Page Url is: " + currentUrl);
		}

		try{
			wait.until(ExpectedConditions.textToBe(ACCOUNT_NAV_DROPDOWN, "Hi, Sign in"));
			System.out.println("Successfully Signed Out");
		}catch (TimeoutException e) {
			softassert.fail("After Clicking on Sign out option, user was not successfully Signed out");
			System.out.println("After Clicking on Sign out option, user was not successfully Signed out");
		}

		softassert.assertAll();

		Reporter.log("When user is signed in correct text is displayed in Login Header");
		Reporter.log("After hovering on Sign in as a logged in user, Dropdown appeared and contained correct options");
		Reporter.log("My Account option is Displayed and Navigated to correct page");
		Reporter.log("Reorder option is Displayed and Navigated to correct page");
		Reporter.log("Sign out option is Displayed and User was signed out successfully to correct page");

	}
	
	
	public void signIn(String createdAccountEmail, String password) throws Exception {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAV_DROPDOWN));

		action.moveToElement(driver.findElement(ACCOUNT_NAV_DROPDOWN)).perform();

		WebElement signIn = driver.findElement(SIGNIN_OPTION);
		genMethods.clickButtonByJS(driver, signIn);
//		signIn.click();

		try {
			wait.until(ExpectedConditions.urlContains("https://www.tommyjohn.com/account/login"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("After clicking on Sign in, Page Url is: " + currentUrl);
		} catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail("Navigated to page with incorrect url, Expected url: https://www.tommyjohn.com/account/login Found: "
							+ currentUrl);
			System.out.println("After clicking on Sign in, Page Url is: " + currentUrl);
		}
		
		try{
			wait.until(ExpectedConditions.textToBe(AccountLoginLocators.LOGIN_HEADING_TEXT, "Login"));
			pageHeader = driver.findElement(AccountLoginLocators.LOGIN_HEADING_TEXT).getText();
			System.out.println("Current Page Header is: " + pageHeader);
		}catch (TimeoutException e) {
			softassert.fail("Wrong page opened after clicking on sign in option, expected page with header: 'Login'");
			pageHeader = driver.findElement(AccountLoginLocators.LOGIN_HEADING_TEXT).getText();
			System.out.println("Current Page Header is: " + pageHeader);
		}
		
		WebElement emailInput = driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT);
		WebElement passwordInput = driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX);
		WebElement loginSubmitBtn = driver.findElement(AccountLoginLocators.LOGIN_BUTTON);
		
		if (emailInput.isDisplayed()) {
			emailInput.clear();
			emailInput.sendKeys(createdAccountEmail);
			System.out.println("Email Entered: " + createdAccountEmail);
		}else {
			softassert.fail("Email inputbox is not present");
		}
		
		
		
		if (passwordInput.isDisplayed()) {
			passwordInput.clear();
			passwordInput.sendKeys(password);
			System.out.println("Password Entered: " + password);
		}else {
			softassert.fail("Password inputbox is not present");
		}
		
		if (!loginSubmitBtn.isDisplayed()) {
			softassert.fail("Login Button is not displayed");
		}
		
		softassert.assertAll();
		
		loginSubmitBtn.click();
		
		try{
			wait.until(ExpectedConditions.textToBe(AccountPageLocators.ACCOUNT_PAGE_HEADING, "Account"));
			System.out.println("Successfully logged in, Login Flow Completed");
		}catch (TimeoutException e) {
			pageHeader = driver.findElement(AccountPageLocators.ACCOUNT_PAGE_HEADING).getText();
			softassert.fail("After Signing In, Account Page did not show up, Page header is: " + pageHeader);
			System.out.println("After Signing In, Account Page did not show up, Page header is: " + pageHeader);
		}
		
		softassert.assertAll();
		
	}	
	
	/**
	 * 1. On hover of empty cart, color should change from grey to light grey.
	 * 2. Clicking the Cart icon prompts the inline cart.
	 */
	public void validateCartIconInteraction() {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
//		1. On hover of empty cart, color should change from grey to light grey.
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_ICON));
		
		WebElement cartIcon = driver.findElement(CART_ICON);
		
		String colorBefore = cartIcon.getCssValue("color");
		
		System.out.println(colorBefore);
		
		action.moveToElement(cartIcon).perform();
		
		String colorAfter = cartIcon.getCssValue("color");
		
		softassert.assertTrue(colorAfter.equals("rgba(115, 115, 115, 1)"), "Color of empty cart icon did not change from Grey to light grey after hovering on the icon");
		
		System.out.println(colorAfter);
		
//		2. Clicking the Cart icon prompts the inline cart.
		cartIcon.click();
		
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.INLINE_CART_TITLE));
		}catch (Exception e) {
			softassert.fail("Inline Cart did not open up after ");
		}
		
		System.out.println("Inline cart opened after clicking on cart icon");
		
		softassert.assertAll();
		
		Reporter.log("Color of Empty Cart Icon changed from Grey to light grey after hovering on it");
		Reporter.log("Inline Cart opened after Clicking on Cart Icon");
		
	}
	
	/**
	 * Used for validating Mens UW Quiz Landing Page
	 */
	public void validateMensUWQuizPage() {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MAIN_HEADER));
		
		WebElement mensOption = driver.findElement(MEN_CATEGORY);
		
		action.moveToElement(mensOption).perform();
		
		WebElement underwearOption = driver.findElement(MEN_UNDERWEAR_LINK);
		
		action.moveToElement(underwearOption).perform();
		
		WebElement underwearQuiz = driver.findElement(MEN_UNDERWEAR_QUIZ);
		
		action.click(underwearQuiz).perform();
		
		try {
			wait.until(ExpectedConditions.urlToBe("https://www.tommyjohn.com/pages/quiz?quizID=MN-UW-QZ"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("current Page url is: " + currentUrl);
		}catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail("Expected Page url is: https://www.tommyjohn.com/pages/quiz?quizID=MN-UW-QZ" + "Found: " + currentUrl);
			System.out.println("Expected Page url is: https://www.tommyjohn.com/pages/quiz?quizID=MN-UW-QZ" + "Found: " + currentUrl);
		}
		
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(QuizPageLocators.QUIZ_HERO_WELCOME_TITLE));
			String heroWelcomeTitle = driver.findElement(QuizPageLocators.QUIZ_HERO_WELCOME_TITLE).getText();
			softassert.assertTrue(heroWelcomeTitle.equals("Find Your Underwear Match"), "Hero Welcome Title is not present");
		}catch (TimeoutException e) {
			softassert.fail("Quiz Hero Welcome Title is not present");
			System.out.println("Quiz Hero Welcome Title is not present");
		}
		
		String heroWelcomeSubTitle = driver.findElement(QuizPageLocators.QUIZ_HERO_WELCOME_SUBTITLE).getText();
		softassert.assertTrue(heroWelcomeSubTitle.equals("Let’s help you find the pair that’s most comfortable for you."), "Hero Welcome Sub Title is not present");
		
		element = driver.findElement(QuizPageLocators.GET_STARTED_CTA);
		softassert.assertTrue(element.isDisplayed() && element.isEnabled(), "Get Started CTA is not present or not clickable");
		
		softassert.assertAll();
		
		System.out.println("Mens Quiz Landing Page successfully opened");
		Reporter.log("Mens Quiz Landing Page successfully opened");
		
	}
	
	/**
	 * Used for validating Womens UW Quiz Landing Page
	 */
	public void validateWomensUWQuizPage() {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MAIN_HEADER));
		
		WebElement womensOption = driver.findElement(WOMEN_CATEGORY);
		
		action.moveToElement(womensOption).perform();
		
		WebElement underwearOption = driver.findElement(WOMEN_UNDERWEAR_LINK);
		
		action.moveToElement(underwearOption).perform();
		
		WebElement underwearQuiz = driver.findElement(WOMEN_UNDERWEAR_QUIZ);
		
		action.click(underwearQuiz).perform();
		
		try {
			wait.until(ExpectedConditions.urlToBe("https://www.tommyjohn.com/pages/quiz?quizID=WN-UW-QZ"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("current Page url is: " + currentUrl);
		}catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail("Expected Page url is: https://www.tommyjohn.com/pages/quiz?quizID=WN-UW-QZ" + "Found: " + currentUrl);
			System.out.println("Expected Page url is: https://www.tommyjohn.com/pages/quiz?quizID=WN-UW-QZ" + "Found: " + currentUrl);
		}
		
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(QuizPageLocators.QUIZ_HERO_WELCOME_TITLE));
			String heroWelcomeTitle = driver.findElement(QuizPageLocators.QUIZ_HERO_WELCOME_TITLE).getText();
			softassert.assertTrue(heroWelcomeTitle.equals("Find Your Underwear Match"), "Hero Welcome Title is not present");
		}catch (TimeoutException e) {
			softassert.fail("Quiz Hero Welcome Title is not present");
			System.out.println("Quiz Hero Welcome Title is not present");
		}
		
		String heroWelcomeSubTitle = driver.findElement(QuizPageLocators.QUIZ_HERO_WELCOME_SUBTITLE).getText();
		softassert.assertTrue(heroWelcomeSubTitle.equals("Let’s help you find the pair that’s most comfortable for you."), "Hero Welcome Sub Title is not present");
		
		element = driver.findElement(QuizPageLocators.GET_STARTED_CTA);
		softassert.assertTrue(element.isDisplayed() && element.isEnabled(), "Get Started CTA is not present or not clickable");
		
		softassert.assertAll();
		
		System.out.println("Womens Quiz Landing Page successfully opened");
		Reporter.log("Womens Quiz Landing Page successfully opened");
		
	}

	/**
	 * Used for validating Womens Bra Quiz Landing Page
	 */
	public void validateWomensBraQuizPage() {
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MAIN_HEADER));
		
		WebElement womensOption = driver.findElement(WOMEN_CATEGORY);
		
		action.moveToElement(womensOption).perform();
		
		WebElement braOption = driver.findElement(WOMEN_BRA_LINK);
		
		action.moveToElement(braOption).perform();
		
		WebElement braQuiz = driver.findElement(WOMEN_BRA_QUIZ);
		
		action.click(braQuiz).perform();
		
		try {
			wait.until(ExpectedConditions.urlToBe("https://www.tommyjohn.com/pages/quiz?quizID=BR-10005-QZ"));
			currentUrl = driver.getCurrentUrl();
			System.out.println("current Page url is: " + currentUrl);
		}catch (TimeoutException e) {
			currentUrl = driver.getCurrentUrl();
			softassert.fail("Expected Page url is: https://www.tommyjohn.com/pages/quiz?quizID=BR-10005-QZ" + "Found: " + currentUrl);
			System.out.println("Expected Page url is: https://www.tommyjohn.com/pages/quiz?quizID=BR-10005-QZ" + "Found: " + currentUrl);
		}
		
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(QuizPageLocators.QUIZ_HERO_WELCOME_TITLE));
			String heroWelcomeTitle = driver.findElement(QuizPageLocators.QUIZ_HERO_WELCOME_TITLE).getText();
			softassert.assertTrue(heroWelcomeTitle.equals("Find Your Bra Style"), "Bra Page Hero Welcome Title is not present");
		}catch (TimeoutException e) {
			softassert.fail("Bra Quiz Hero Welcome Title is not present");
			System.out.println("Bra Quiz Hero Welcome Title is not present");
		}
		
		String heroWelcomeSubTitle = driver.findElement(QuizPageLocators.QUIZ_HERO_WELCOME_SUBTITLE).getText();
		softassert.assertTrue(heroWelcomeSubTitle.equals("Let's help you find the bra that's most comfortable for you."), "Hero Welcome Sub Title is not present");
		
		element = driver.findElement(QuizPageLocators.GET_STARTED_CTA);
		softassert.assertTrue(element.isDisplayed() && element.isEnabled(), "Get Started CTA is not present or not clickable");
		
		softassert.assertAll();
		
		System.out.println("Bra Quiz Landing Page successfully opened");
		Reporter.log("Bra Quiz Landing Page successfully opened");
		
	}
	
	/**
	 * Used to navigate to a certain collection page
	 * also validates, if level 0 options are hoverable and submenu(level 1) options are displayed after hovering.
	 * @param L0 nth option, for eg: 1-Men, 2-Women, 3-Explore, 4-Sale 
	 */
	public void level_0_nav(int L0) {
		
		String href = null;
		String elementText = null;
		
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
		List<WebElement> level_zero_elements_list = new ArrayList<>();
		List<String> level_zero_text_list = new ArrayList<>();
		
		List<WebElement> level_one_elements_list = new ArrayList<>();
		List<String> level_one_text_list = new ArrayList<>();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_HEADER));
		
		level_zero_elements_list = driver.findElements(LEVEL_ZERO_LINKS_LIST);
		
		for (int i = 0; i < level_zero_elements_list.size(); i++) {
			 level_zero_text_list.add(level_zero_elements_list.get(i).getText());
		}
		
		System.out.println("Number of elements in Level 0: " + level_zero_elements_list.size());
		System.out.println(level_zero_text_list);
		
		element = genMethods.autoIndex(driver, LEVEL_ZERO_SPECIFIC_OPTION, L0, 1);
		elementText = element.getText();
		System.out.println("Hovering on: " + elementText);
		action.moveToElement(element).perform();
		href = element.getAttribute("href");
		
		level_one_elements_list = genMethods.autoIndexList(driver, SPECIFIC_LEVEL_0_LEVEL_1_LINKS, L0, 1);
		
		System.out.println(elementText + ": Contains " + level_one_elements_list.size() + " elements in level 1 list");
		
//		If there are no level 1 options present for the particular level 0 option, do nothing.
		if(level_one_elements_list.size() != 0){
			
//			check if level 1 options have been displayed after hovering over a certain Level 0 option
			try {
				wait.until(ExpectedConditions.visibilityOf(genMethods.autoIndex(driver, LEVEL_1_OPTIONS_LIST_FROM_LEVEL_0, L0, 1)));
			}catch (TimeoutException e) {
				softassert.fail("Level 1 options after hovering on \"" + elementText + "\" did not come up");
				System.out.println("Level 1 options after hovering on \"" + elementText + "\" did not come up");
			}
			
			for (int i = 0; i < level_one_elements_list.size(); i++) {
				level_one_text_list.add(level_one_elements_list.get(i).getText());
			}
			System.out.println(elementText + ": level 1 List contains: " + level_one_text_list);
			
		}else {
			System.out.println(elementText + " option doesnt contain any Level 1 items");
		}
		
//		Clicking on the element from level 0
		if(level_one_elements_list.size() != 0) {
			element.click();
			System.out.println("Clicking on: " + elementText);
			
			try{
				wait.until(ExpectedConditions.urlContains(href));
			}catch (TimeoutException e) {
				currentUrl = driver.getCurrentUrl();
				softassert.fail("Expected Page did not show up, url of page navigated to: " + currentUrl);
				System.out.println("Expected Page did not show up, url of page navigated to: " + currentUrl);
			}
		
		}
		
		softassert.assertAll();
		System.out.println("Correct Page opened after clicking on the given Level 0 option: " + elementText);
		Reporter.log("Correct Page opened after clicking on the given Level 0 option: " + elementText);
	}
	
	/**
	 * Used for navigating to any element at level 1
	 * also validates, if level 1 options are hoverable and submenu(level 2) options are displayed after hovering.
	 * @param L0 nth option, for eg: 1-Men, 2-Women, 3-Explore, 4-Sale 
	 * @param L1 nth option for eg: 1-Underwear 2-Explore, 3-Undershirts etc.
	 */
	
	public void level_1_nav(int L0, int L1) {
		
		String href = null;
		String elementText = null;
		
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
		List<WebElement> level_zero_elements_list = new ArrayList<>();
		List<String> level_zero_text_list = new ArrayList<>();
		
		List<WebElement> level_one_elements_list = new ArrayList<>();
		List<String> level_one_text_list = new ArrayList<>();
		
		List<WebElement> level_two_elements_list = new ArrayList<>();
		List<String> level_two_text_list = new ArrayList<>();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_HEADER));
		
		level_zero_elements_list = driver.findElements(LEVEL_ZERO_LINKS_LIST);
		
		for (int i = 0; i < level_zero_elements_list.size(); i++) {
			 level_zero_text_list.add(level_zero_elements_list.get(i).getText());
		}
		
		System.out.println("Number of elements in Level 0: " + level_zero_elements_list.size());
		System.out.println(level_zero_text_list);
		
		element = genMethods.autoIndex(driver, LEVEL_ZERO_SPECIFIC_OPTION, L0, 1);
		elementText = element.getText();
		System.out.println("Hovering on: " + elementText);
		action.moveToElement(element).perform();
		href = element.getAttribute("href");
		
		level_one_elements_list = genMethods.autoIndexList(driver, SPECIFIC_LEVEL_0_LEVEL_1_LINKS, L0, 1);
		
		System.out.println(elementText + ": Contains " + level_one_elements_list.size() + " elements in level 1 list");
		
//		If there are no level 1 options present for the particular level 0 option, do nothing.
		if(level_one_elements_list.size() != 0){
			
//			check if level 1 options have been displayed after hovering over a certain Level 0 option
			try {
				wait.until(ExpectedConditions.visibilityOf(genMethods.autoIndex(driver, LEVEL_1_OPTIONS_LIST_FROM_LEVEL_0, L0, 1)));
			}catch (TimeoutException e) {
				softassert.fail("Level 1 options after hovering on \"" + elementText + "\" did not come up");
				System.out.println("Level 1 options after hovering on \"" + elementText + "\" did not come up");
			}
			
			for (int i = 0; i < level_one_elements_list.size(); i++) {
				level_one_text_list.add(level_one_elements_list.get(i).getText());
			}
			System.out.println(elementText + ": level 1 List contains: " + level_one_text_list);
			
		}else {
			System.out.println(elementText + " option doesnt contain any Level 1 items");
		}
		
//		Clicking on the element from level 1, L1 = L1 + 1, because for L1 options first element index starts from 2
		L1 = L1+1;
		
		if(level_one_elements_list.size() != 0) {
			element = driver.findElement(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(" + L1 +")>div a"));
			elementText = element.getText();
			href = element.getAttribute("href");
			
			System.out.println("Hovering on: " + elementText);
			action.moveToElement(element).perform();

//			Validate that after Hovering on a Level 1 option, Level 2 options are displayed 
			level_two_elements_list = driver.findElements(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child("+ L1 +") li.level-two-container"));
			System.out.println(elementText + ": Contains " + level_two_elements_list.size() + " elements in level 2 list");
			
//			If there are no level 2 options present for the particular level 1 option, do nothing.
			if(level_two_elements_list.size() != 0){
				for (int i = 0; i < level_two_elements_list.size(); i++) {
					level_two_text_list.add(level_two_elements_list.get(i).getText());
				}
				System.out.println(elementText + ": level 2 List contains: " + level_two_text_list);
				
				try {
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child("+ L1 +") li.level-two-container")));
				} catch (TimeoutException e) {
					softassert.fail("Level 2 options after hovering on \"" + elementText + "\" did not come up");
					System.out.println("Level 2 options after hovering on \"" + elementText + "\" did not come up");
				}
				
				
			}else {
				System.out.println(element.getText() + " option doesnt contain any Level 2 items");
			}
			
//			Click on the given level 1 option
			System.out.println("Clicking on: " + elementText);
			element.click();
			
			try{
				wait.until(ExpectedConditions.urlContains(href));
			}catch (TimeoutException e) {
				currentUrl = driver.getCurrentUrl();
				softassert.fail("Expected Page did not show up, url of page navigated to: " + currentUrl);
				System.out.println("Expected Page did not show up, url of page navigated to: " + currentUrl);
			}
		
		}
		
		softassert.assertAll();
		System.out.println("Correct Page opened after clicking on the given Level 1 option: " + elementText);
		Reporter.log("Correct Page opened after clicking on the given Level 1 option: " + elementText);
	}
	
	/**
	 * Used for navigating to any element at level 2 (doesn't support nav links, only nav tiles)
	 * also validates, if after clicking on level 2 option correct page opened up
	 * In progress
	 * @param L0 nth option, for eg: 1-Men, 2-Women, 3-Explore, 4-Sale 
	 * @param L1 nth option for eg: 1-Underwear 2-Explore, 3-Undershirts etc.
	 * @param L2 nth option for eg:1-Underwear packs, 2-Trunk 4", 3-Mid-length Boxer Brief 6"
	 */
	
	public void level_2_nav(int L0, int L1, int L2) {

		String href = null;
		String elementText = null;
		
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
		List<WebElement> level_zero_elements_list = new ArrayList<>();
		List<String> level_zero_text_list = new ArrayList<>();
		
		List<WebElement> level_one_elements_list = new ArrayList<>();
		List<String> level_one_text_list = new ArrayList<>();
		
		List<WebElement> level_two_elements_list = new ArrayList<>();
		List<String> level_two_text_list = new ArrayList<>();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_HEADER));
		
		level_zero_elements_list = driver.findElements(LEVEL_ZERO_LINKS_LIST);
		
		for (int i = 0; i < level_zero_elements_list.size(); i++) {
			 level_zero_text_list.add(level_zero_elements_list.get(i).getText());
		}
		
		System.out.println("Number of elements in Level 0: " + level_zero_elements_list.size());
		System.out.println(level_zero_text_list);
		
		element = genMethods.autoIndex(driver, LEVEL_ZERO_SPECIFIC_OPTION, L0, 1);
		elementText = element.getText();
		System.out.println("Hovering on: " + elementText);
		action.moveToElement(element).perform();
		href = element.getAttribute("href");
		
		level_one_elements_list = genMethods.autoIndexList(driver, SPECIFIC_LEVEL_0_LEVEL_1_LINKS, L0, 1);
		
		System.out.println(elementText + ": Contains " + level_one_elements_list.size() + " elements in level 1 list");
		
//		If there are no level 1 options present for the particular level 0 option, do nothing.
		if(level_one_elements_list.size() != 0){
			
//			check if level 1 options have been displayed after hovering over a certain Level 0 option
			try {
				wait.until(ExpectedConditions.visibilityOf(genMethods.autoIndex(driver, LEVEL_1_OPTIONS_LIST_FROM_LEVEL_0, L0, 1)));
			}catch (TimeoutException e) {
				softassert.fail("Level 1 options after hovering on \"" + elementText + "\" did not come up");
				System.out.println("Level 1 options after hovering on \"" + elementText + "\" did not come up");
			}
			
			for (int i = 0; i < level_one_elements_list.size(); i++) {
				level_one_text_list.add(level_one_elements_list.get(i).getText());
			}
			System.out.println(elementText + ": level 1 List contains: " + level_one_text_list);
			
		}else {
			System.out.println(elementText + " option doesnt contain any Level 1 items");
		}
		
//		Clicking on the element from level 1, L1 = L1 + 1, because for L1 options first element index starts from 2
		L1 = L1+1;
		
		if(level_one_elements_list.size() != 0) {
			element = driver.findElement(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(" + L1 +")>div a"));
			elementText = element.getText();
			href = element.getAttribute("href");
			
			System.out.println("Hovering on: " + elementText);
			action.moveToElement(element).perform();

//			Validate that after Hovering on a Level 1 option, Level 2 options are displayed 
			level_two_elements_list = driver.findElements(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child("+ L1 +") li.level-two-container"));
			System.out.println(elementText + ": Contains " + level_two_elements_list.size() + " elements in level 2 list");
			
//			If there are no level 2 options present for the particular level 1 option, do nothing.
			if(level_two_elements_list.size() != 0){
				for (int i = 0; i < level_two_elements_list.size(); i++) {
					level_two_text_list.add(level_two_elements_list.get(i).getText());
				}
				System.out.println(elementText + ": level 2 List contains: " + level_two_text_list);
				
				try {
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child("+ L1 +") li.level-two-container")));
				} catch (TimeoutException e) {
					softassert.fail("Level 2 options after hovering on \"" + elementText + "\" did not come up");
					System.out.println("Level 2 options after hovering on \"" + elementText + "\" did not come up");
				}
				
				
			}else {
				System.out.println(element.getText() + " option doesnt contain any Level 2 items");
			}
			
//			Click on the given level 1 option
			System.out.println("Clicking on: " + elementText);
			element.click();
			
			try{
				wait.until(ExpectedConditions.urlContains(href));
				System.out.println("Correct Page opened after clicking on the given Level 1 option");
			}catch (TimeoutException e) {
				currentUrl = driver.getCurrentUrl();
				softassert.fail("Expected Page did not show up, url of page navigated to: " + currentUrl);
				System.out.println("Expected Page did not show up, url of page navigated to: " + currentUrl);
			}
		
		}

		softassert.assertAll();
		System.out.println("Correct Page opened after clicking on the given Level 2 option: " + elementText);
		Reporter.log("Correct Page opened after clicking on the given Level 2 option: " + elementText);
		
	}
	
	/**  IN PROGRESS
	 * To Create a generic method which will navigate from level 0 > level 1 > level 2
	 * and will validate if the correct page opened after clicking on the respective options
	 * 1. Level 0 - Men, Women, Explore, Sale
	 * 2. Level 1 of Men - Underwear, Explore, Undershirts, Loungewear, Sleepwear, Featured, Sale
	 * 3. Level 1 of Women -  Underwear, Bras, Loungewear, Sleepwear, Featured, Sale
	 * 4. Level 1 of Explore - Apollo, Hammock Pouch, 360 Sport
	 * 5. Level 1 of Sale - Men's sale, Women's Sale
	 * 
	 */
	public void navigation(int L0, int L1, int L2) {

		L0 = 1;
		L1 = 1;
		L2 = 1;
		
		softassert = new SoftAssert();
		genMethods = new GeneralMethods();
		action = new Actions(driver);
		
		List<WebElement> level_zero_elements_list = new ArrayList<>();
		List<String> level_zero_text_list = new ArrayList<>();
		
		List<WebElement> level_one_elements_list = new ArrayList<>();
		List<String> level_one_text_list = new ArrayList<>();
		
		List<WebElement> level_two_elements_list = new ArrayList<>();
		List<String> level_two_text_list = new ArrayList<>();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_HEADER));
		
		level_zero_elements_list = driver.findElements(LEVEL_ZERO_LINKS_LIST);
		
		for (int i = 0; i < level_zero_elements_list.size(); i++) {
			 level_zero_text_list.add(level_zero_elements_list.get(i).getText());
		}
		
		System.out.println("Number of elements in Level 0: " + level_zero_elements_list.size());
		System.out.println(level_zero_text_list);
		
		element = genMethods.autoIndex(driver, LEVEL_ZERO_SPECIFIC_OPTION, L0, 1);
		
//		System.out.println("Clicking on " + element.getText());
		
//		Perform any action on the specific element
		action.moveToElement(element).perform();
//		element.click();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		level_one_elements_list = genMethods.autoIndexList(driver, SPECIFIC_LEVEL_0_LEVEL_1_LINKS, L0, 1);
		
		System.out.println(element.getText() + ": Contains " + level_one_elements_list.size() + " elements in level 1 list");
		
		if(level_one_elements_list.size() != 0){
			for (int i = 0; i < level_one_elements_list.size(); i++) {
				level_one_text_list.add(level_one_elements_list.get(i).getText());
			}
			System.out.println(element.getText() + ": level 1 List contains: " + level_one_text_list);
		}else {
			System.out.println(element.getText() + " option doesnt contain any Level 1 items");
		}
		
//		Perform an Action on a element from Level 1 List
//		for L1 options first element index starts from 2
		L1 = L1+1;
		String href = null;
		String elementText = null;
		
//		need to upadate autoIndex method
		if (level_one_elements_list.size() != 0) {
			element = driver.findElement(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child(" + L1 +")>div a"));
			action.moveToElement(element).perform();
			elementText = element.getText();
//			href = element.getAttribute("href");
//			System.out.println(href);
//			System.out.println("Clicking on option: " + elementText);
//			element.click();
			
//			try {
//				wait.until(ExpectedConditions.urlContains(href));
//			}catch (TimeoutException e) {
//				System.out.println("Expected page did not open after clicking on: " + elementText);
//			}
			
		}
		
//		Level 2 items for L2 options first element index starts from 3
		L2 = L2 + 2; 
		
		level_two_elements_list = driver.findElements(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child("+ L1 +") li.level-two-container"));
		
		System.out.println(element.getText() + ": Contains " + level_two_elements_list.size() + " elements in level 2 list");
		
		if(level_two_elements_list.size() != 0){
			for (int i = 0; i < level_two_elements_list.size(); i++) {
				level_two_text_list.add(level_two_elements_list.get(i).getText());
			}
			System.out.println(element.getText() + ": level 2 List contains: " + level_two_text_list);
		}else {
			System.out.println(element.getText() + " option doesnt contain any Level 2 items");
		}
		
		
//		Perform an Action on a element from Level 2 List
		
		if (level_one_elements_list.size() != 0) {
			element = driver.findElement(By.cssSelector(".nav-item__list.level-zero-list>li:nth-child("+ L0 +") ul[class^='nav-item__list level-one-list']>li[class^='level-one-container']:nth-child("+ L1 +") li.level-two-container:nth-child("+ L2 +")"));
			action.click(element).perform();
		}
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
