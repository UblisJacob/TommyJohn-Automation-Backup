package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.tommyjohn.automation.PageLocators.FooterSectionLocators;
import com.tommyjohn.automation.PageLocators.GiftCardBalancePageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;
import commonMethods.GeneralMethods;


public class GiftCardBalancePageComponents extends GiftCardBalancePageLocators {

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
	GeneralMethods genMethods = new GeneralMethods();

	public GiftCardBalancePageComponents(WebDriver driver) {

		this.driver = driver;

		wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
				.withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);

	}

	/**
	 * Validates if both Physical and E-Gift Card Forms and its fields are present
	 * on Gift Card balance Page
	 * @throws Exception 
	 */
	public void validateFormAndItsFields() throws Exception {

		genMethods.waitTillPageLoad(driver);

		genMethods.scrollElementIntoView(driver, FooterSectionLocators.GIFT_CARD_BALANCE_CHECK);

		genMethods.clickButtonByJS(driver, FooterSectionLocators.GIFT_CARD_BALANCE_CHECK);

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(GIFT_CARD_BALANCE_HEADER, 0));

		String headerText = driver.findElement(GIFT_CARD_BALANCE_HEADER).getText();
		String pageUrl = driver.getCurrentUrl();

		Assert.assertTrue(pageUrl.equals("https://www.tommyjohn.com/pages/my-card-balance"),
				"Incorrect page opened after clicking on 'Check Gift Card Balance'");
		softassert.assertTrue(headerText.equals("Gift Card Balance"), "Gift Card balance page header text has changed");

//		PHYSICAL GIFT CARD FORM FIELD VALIDATIONS
		element = driver.findElement(PHYSICAL_GIFT_CARD_FORM);
		softassert.assertTrue(element.isDisplayed(), "Physical Gift Card Form is Not Visible");

		element = driver.findElement(PHYSICAL_GIFT_CARD_ID_LABEL);
		softassert.assertTrue(element.isDisplayed(), "Physical Gift Card 'Enter 16 Digit Card #' Label is Not Visible");

		element = driver.findElement(PHYSICAL_GIFT_CARD_ID_FIELD);
		softassert.assertTrue(element.isEnabled(), "Physical Gift Card 'Enter 16 Digit Card #' Field is Not Enabled");

		element = driver.findElement(PHYSICAL_GIFT_CARD_PIN_LABEL);
		softassert.assertTrue(element.isDisplayed(), "Physical Gift Card 'PIN' Label is Not Visible");

		element = driver.findElement(PHYSICAL_GIFT_CARD_PIN_FIELD);
		softassert.assertTrue(element.isEnabled(), "Physical Gift Card 'PIN' Field is Not Enabled");

		element = driver.findElement(PHYSICAL_GIFT_CARD_EMAIL_LABEL);
		softassert.assertTrue(element.isDisplayed(), "Physical Gift Card 'Cardholder's Email' Label is Not Visible");

		element = driver.findElement(PHYSICAL_GIFT_CARD_EMAIL_FIELD);
		softassert.assertTrue(element.isEnabled(), "Physical Gift Card 'Cardholder's Email' Field is Not Enabled");

		element = driver.findElement(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN);
		softassert.assertTrue(element.isDisplayed() && element.isEnabled(),
				"Physical Gift Card 'Check Balance' Button is either not Visible or not Enabled");

//		DIGITAL GIFT CARD FORM FIELD VALIDATIONS
		element = driver.findElement(DIGITAL_GIFT_CARD_FORM);
		softassert.assertTrue(element.isDisplayed(), "Digital Gift Card Form is Not Visible");

		element = driver.findElement(DIGITAL_GIFT_CARD_ID_LABEL);
		softassert.assertTrue(element.isDisplayed(),
				"Digital Gift Card 'Enter 16 Digit Card #' Label is Not Displayed");

		element = driver.findElement(DIGITAL_GIFT_CARD_ID_FIELD);
		softassert.assertTrue(element.isEnabled(), "Digital Gift Card 'Enter 16 Digit Card #' Field is Not Enabled");

		element = driver.findElement(DIGITAL_GIFT_CARD_EMAIL_LABEL);
		softassert.assertTrue(element.isDisplayed(), "Digital Gift Card 'Cardholder's Email' Label is Not Displayed");

		element = driver.findElement(DIGITAL_GIFT_CARD_EMAIL_FIELD);
		softassert.assertTrue(element.isEnabled(), "Digital Gift Card 'Cardholder's Email' Field is Not Enabled");

		element = driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN);
		softassert.assertTrue(element.isDisplayed() && element.isEnabled(),
				"Physical Gift Card 'Check Balance' Button is either not Visible or not Enabled");

		softassert.assertAll();

		System.out.println("Physical Gift Card Form and all of its necessary fields are present");
		System.out.println("Digital Gift Card Form and all of its necessary fields are present");
		Reporter.log("Physical Gift Card Form and all of its necessary fields are present");
		Reporter.log("Digital Gift Card Form and all of its necessary fields are present");

	}

	/**
	 * POSITIVE VALIDATION OF CHECK BALANCE Functionality of Physical GC's 1.
	 * Whether user is redirected to 'Balance Results' page if details are correct
	 * after clicking on Check balance 2. A link at the top of the page labeled
	 * "Back to Gift Card Balance" directs the user back to the intial page. 3. User
	 * will see the activation number, activation date, and remaining balance. 4.
	 * CTA "Start Shopping" directs to the homepage.
	 * 
	 * @param validGCID
	 * @param validGCPin
	 * @param validCardHolderEmail
	 * @throws Exception 
	 */
	public void physicalGiftCardFormValidation(String validGCID, String validGCPin, String validCardHolderEmail) throws Exception {

		genMethods.waitTillPageLoad(driver);

		genMethods.scrollElementIntoView(driver, FooterSectionLocators.GIFT_CARD_BALANCE_CHECK);

		genMethods.clickButtonByJS(driver, FooterSectionLocators.GIFT_CARD_BALANCE_CHECK);

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(GIFT_CARD_BALANCE_HEADER, 0));

//		Negative Validation
		invalidPhysicalGiftCardValidation(validGCID, validGCPin, validCardHolderEmail);

//		Positive Validation
		driver.findElement(PHYSICAL_GIFT_CARD_ID_FIELD).sendKeys(validGCID);
		driver.findElement(PHYSICAL_GIFT_CARD_PIN_FIELD).sendKeys(validGCPin);
		driver.findElement(PHYSICAL_GIFT_CARD_EMAIL_FIELD).sendKeys(validCardHolderEmail);
		driver.findElement(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN).click();

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(BALANCE_PAGE_HEADER, 0));

//		Check if user is redirected to My Balance Results Page

		Assert.assertTrue(genMethods.verifyElementExists(driver, BALANCE_PAGE_HEADER),
				"User is not redirected to My Balance Results Page");

//		User will see the card/activation number, activation date, and remaining balance.

		element = driver.findElement(BALANCE_PAGE_CARD_NUMBER_DATA);
		Assert.assertTrue(validGCID.equals(element.getText()), "Activation Number is not same as entered by user");
		softassert.assertTrue(element.isDisplayed(), "Card/Activation Number is Not displayed");

		element = driver.findElement(BALANCE_PAGE_ACTIVATION_DATE_DATA);
		softassert.assertTrue(element.isDisplayed(), "Activation Data is Not displayed");

		element = driver.findElement(BALANCE_PAGE_REMAINING_BALANCE_DATA);
		softassert.assertTrue(element.isDisplayed(), "Remaining Balance is Not displayed");

		element = driver.findElement(BALANCE_PAGE_START_SHOPPING_CTA_BTN);
		softassert.assertTrue(element.isDisplayed() && element.isEnabled(),
				"Start Shopping CTA is Either Not displayed or not Enabled");

		softassert.assertAll();
		System.out.println("Card Number, Activation Date and Remaining Balance is present on Balance Page");
		Reporter.log("Card Number, Activation Date and Remaining Balance is present on Balance Page");
		
//		"Back to Gift Card Balance" directs the user back to the initial page.
		String expbacktoGCLink = "https://tommyjohn.com/pages/my-card-balance";
		String actualbacktoGCLink = driver.findElement(BACK_TO_GIFT_CARD_BALANCE).getAttribute("href");

		softassert.assertTrue(actualbacktoGCLink.contains(expbacktoGCLink),
				"Invalid Link attached to 'Back to Gift Card Balance'");
		
		genMethods.clickButtonByJS(driver, BACK_TO_GIFT_CARD_BALANCE);
		
		Thread.sleep(3000);
		
		boolean b = genMethods.verifyElementExists(driver, GIFT_CARD_BALANCE_HEADER);
		Assert.assertTrue(b, "After clicking on Back To Gift Card Link user was not navigated to correct page");

//		CTA "Start Shopping" directs to the homepage.
		wait.until(ExpectedConditions.elementToBeClickable(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN));
		
		driver.findElement(PHYSICAL_GIFT_CARD_ID_FIELD).sendKeys(validGCID);
		driver.findElement(PHYSICAL_GIFT_CARD_PIN_FIELD).sendKeys(validGCPin);
		driver.findElement(PHYSICAL_GIFT_CARD_EMAIL_FIELD).sendKeys(validCardHolderEmail);
		driver.findElement(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN).click();

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(BALANCE_PAGE_HEADER, 0));
		
//		Fail
		driver.findElement(BALANCE_PAGE_START_SHOPPING_CTA_BTN).click();

		String expectedPageTitle = "Tommy John | No Adjustment Needed";
		String expectedURL = "https://www.tommyjohn.com/";

		wait.until(ExpectedConditions.titleIs(expectedPageTitle));

		Assert.assertTrue(driver.getCurrentUrl().equals(expectedURL),
				"After Clicking on Start Shopping CTA user was not redirected to HomePage");

		System.out.println("User is successfully redirected to Homepage after clickin on 'Start Shopping' CTA");

	}

	/**
	 * Negative Validation 1. If user omits or fills out information incorrectly, an
	 * error message will appear for that specific field. 2. If a user submits an
	 * invalid card number or email, a message would appear above the form.
	 * 
	 * @param validGCID
	 * @param validGCPin
	 * @param validCardHolderEmail
	 * @throws InterruptedException 
	 */
	private void invalidPhysicalGiftCardValidation(String validGCID, String validGCPin, String validCardHolderEmail) throws InterruptedException {

//		If user omits or fills out information incorrectly, an error message will appear for that specific field.
//		Unable to validate the above requirement as functionality is not present for Physical GC form

//		If a user submits an invalid card number or email, a message would appear above the form.
//		NEGATIVE VALIDATION, USER ENTERS INVALID CARD NUMBER
		String invalidGCID = "1534174744485639";
		driver.findElement(PHYSICAL_GIFT_CARD_ID_FIELD).sendKeys(invalidGCID);
		driver.findElement(PHYSICAL_GIFT_CARD_PIN_FIELD).sendKeys(validGCPin);
		driver.findElement(PHYSICAL_GIFT_CARD_EMAIL_FIELD).sendKeys(validCardHolderEmail);
		driver.findElement(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN).click();

		String expectedErrorText = "We don't recognize this information, "
				+ "please make sure you have entered the correct card number, "
				+ "pin and email address. You may need to activate your Gift Card here.";

		wait.until(ExpectedConditions.textToBe(PHYSICAL_GIFT_CART_FORM_ERROR, expectedErrorText));

		System.out.println("Error Message appeared after entering invalid card number");
		Reporter.log("Error Message appeared after entering invalid card number");

		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN));

//		NEGATIVE VALIDATION, USER ENTERS INVALID PIN
		String invalidGCPin = "1235";
		driver.findElement(PHYSICAL_GIFT_CARD_ID_FIELD).sendKeys(validGCID);
		driver.findElement(PHYSICAL_GIFT_CARD_PIN_FIELD).sendKeys(invalidGCPin);
		driver.findElement(PHYSICAL_GIFT_CARD_EMAIL_FIELD).sendKeys(validCardHolderEmail);
		
		try{
			driver.findElement(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		}catch (ElementClickInterceptedException e) {
			Thread.sleep(3000);
			driver.findElement(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		}
		
//		driver.findElement(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN).click();

		wait.until(ExpectedConditions.textToBe(PHYSICAL_GIFT_CART_FORM_ERROR, expectedErrorText));

		System.out.println("Error Message appeared after entering invalid Gift Card Pin");
		Reporter.log("Error Message appeared after entering invalid Gift Card Pin");
		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN));

//		NEGATIVE VALIDATION, USER ENTERS INVALID PIN
		String invalidCardHolderEmail = "neha.maha@tommyjohnwear.com";
		driver.findElement(PHYSICAL_GIFT_CARD_ID_FIELD).sendKeys(validGCID);
		driver.findElement(PHYSICAL_GIFT_CARD_PIN_FIELD).sendKeys(validGCPin);
		driver.findElement(PHYSICAL_GIFT_CARD_EMAIL_FIELD).sendKeys(invalidCardHolderEmail);
		driver.findElement(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN).click();

		wait.until(ExpectedConditions.textToBe(PHYSICAL_GIFT_CART_FORM_ERROR, expectedErrorText));

		System.out.println("Error Message appeared after entering invalid Gift Card Pin");
		Reporter.log("Error Message appeared after entering invalid Gift Card Pin");

		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN));

	}

	
	/**
	 * 1. When a user fills out their information to a digital gift card, & clicks "Check Balance" direct the user to Shopify's Balance Results page.
	 * 2. A link at the top of the page labeled "Back to Gift Card Balance" directs the user back to the intial page. 
	 * 3. User will see a gift card with the gift card number & balance. 
	 * 4. 2 CTAs would appear to the right of the card. "Start Shopping" directs to the homepage, "Print" would print the page.
	 * 5. User can also land on page from initial email.
	 * @param validGCID
	 * @param validCardHolderEmail
	 * @throws Exception 
	 * 
	 */
	public void digitalGiftCardFormValidation(String validGCID, String validCardHolderEmail) throws Exception {

		genMethods.waitTillPageLoad(driver);

		genMethods.scrollElementIntoView(driver, FooterSectionLocators.GIFT_CARD_BALANCE_CHECK);

		genMethods.clickButtonByJS(driver, FooterSectionLocators.GIFT_CARD_BALANCE_CHECK);

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(GIFT_CARD_BALANCE_HEADER, 0));

		invalidDigitalGiftCardValidation(validGCID, validCardHolderEmail);
		
//		POSITIVE VALIDATION
		driver.findElement(DIGITAL_GIFT_CARD_ID_FIELD).sendKeys(validGCID);
		driver.findElement(DIGITAL_GIFT_CARD_EMAIL_FIELD).sendKeys(validCardHolderEmail);
		driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(BALANCE_PAGE_HEADER, 0));
		
//		User will see the card/activation number, activation date, and remaining balance.

		element = driver.findElement(BALANCE_PAGE_CARD_NUMBER_DATA);
		softassert.assertTrue(element.isDisplayed(), "Card/Activation Number is Not displayed");

		element = driver.findElement(BALANCE_PAGE_ACTIVATION_DATE_DATA);
		softassert.assertTrue(element.isDisplayed(), "Activation Data is Not displayed");

		element = driver.findElement(BALANCE_PAGE_REMAINING_BALANCE_DATA);
		softassert.assertTrue(element.isDisplayed(), "Remaining Balance is Not displayed");

		element = driver.findElement(BALANCE_PAGE_START_SHOPPING_CTA_BTN);
		softassert.assertTrue(element.isDisplayed() && element.isEnabled(),
				"Start Shopping CTA is Either Not displayed or not Enabled");
		
		softassert.assertAll();
		System.out.println("Card Number, Activation Date and Remaining Balance is present on Balance Page");
		Reporter.log("Card Number, Activation Date and Remaining Balance is present on Balance Page");
		
//		"Back to Gift Card Balance" directs the user back to the intial page.
		String expbacktoGCLink = "https://tommyjohn.com/pages/my-card-balance";
		String actualbacktoGCLink = driver.findElement(BACK_TO_GIFT_CARD_BALANCE).getAttribute("href");

		softassert.assertTrue(actualbacktoGCLink.contains(expbacktoGCLink),
				"Invalid Link attached to 'Back to Gift Card Balance'");
		
		driver.findElement(BACK_TO_GIFT_CARD_BALANCE).click();
		
//		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(HomePageLocators.HOMEPAGE_LOGO, 0));
		Thread.sleep(3000);
		
		boolean b = genMethods.verifyElementExists(driver, GIFT_CARD_BALANCE_HEADER);
		Assert.assertTrue(b, "After clicking on Back To Gift Card Link user was not navigated to correct page");

		
//		CTA "Start Shopping" directs to the homepage.
		wait.until(ExpectedConditions.elementToBeClickable(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN));
		
		driver.findElement(DIGITAL_GIFT_CARD_ID_FIELD).sendKeys(validGCID);
		driver.findElement(DIGITAL_GIFT_CARD_EMAIL_FIELD).sendKeys(validCardHolderEmail);
		driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();

		driver.findElement(BALANCE_PAGE_START_SHOPPING_CTA_BTN).click();

		String expectedPageTitle = "Tommy John | No Adjustment Needed";
		String expectedURL = "https://www.tommyjohn.com/";

		wait.until(ExpectedConditions.titleIs(expectedPageTitle));

		Assert.assertTrue(driver.getCurrentUrl().equals(expectedURL),
				"After Clicking on Start Shopping CTA user was not redirected to HomePage");

		System.out.println("User is successfully redirected to Homepage after clickin on 'Start Shopping' CTA");

	}

	/**
	 * Negative Validation
	 * 
	 *1. If user omits or fills out information in incorrect format, an error message will appear for that specific field. 
	 *2. If a user submits an invalid card number or email, a message would appear above the form.
	 * 
	 * @param validGCID
	 * @param validGCPin
	 * @param validCardHolderEmail
	 * @throws InterruptedException 
	 */
	private void invalidDigitalGiftCardValidation(String validGCID, String validCardHolderEmail) throws InterruptedException {
		
//		1. If user fills valid email but omits out GCID Field, error message should come up on that specific field
		driver.findElement(DIGITAL_GIFT_CARD_EMAIL_FIELD).sendKeys(validCardHolderEmail);
		driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		
		String expectedCardErrorText = "Please enter a valid card number.";
		String actualCardErrorText = driver.findElement(DIGITAL_GIFT_CART_CODE_ERROR).getText();
		
		softassert.assertTrue(actualCardErrorText.equals(expectedCardErrorText), "Error Message for did not appear for GCID field or Error Text has changed");
		
		System.out.println("Valid Error Message appeared after omitting card number field");
		Reporter.log("Valid Error Message appeared after omitting card number field");

		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN));
		
//		2. If user fills valid GCID but omits out Email Field, error message should come up on that specific field
		driver.findElement(DIGITAL_GIFT_CARD_ID_FIELD).sendKeys(validGCID);
		driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		
		String expectedEmailErrorText = "Please enter a valid email address.";
		String actualEmailErrorText = driver.findElement(DIGITAL_GIFT_CART_EMAIL_ERROR).getText();
		
		softassert.assertTrue(actualEmailErrorText.equals(expectedEmailErrorText), "Error Message for did not appear for Email field or Error Text has changed");
		System.out.println("Valid Error Message appeared after entering valid GCID and omitting Email field");
		Reporter.log("Valid Error Message appeared after entering valid GCID and omitting Email field");

		driver.navigate().refresh();
		
		wait.until(ExpectedConditions.elementToBeClickable(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN));
		
//		3. If user omits out GCID and Email Field, error message should come up on that specific field
		
//		Getting an exception here as page doesnt sometimes fully load, and click gets intercepted by other element, hence needed try catch and sleep
		try{
			driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		}catch (ElementClickInterceptedException e) {
			Thread.sleep(5000);
			driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		}
		
		actualCardErrorText = driver.findElement(DIGITAL_GIFT_CART_CODE_ERROR).getText();
		actualEmailErrorText = driver.findElement(DIGITAL_GIFT_CART_EMAIL_ERROR).getText();
		
		softassert.assertTrue(actualCardErrorText.equals(expectedCardErrorText), "Error Message did not appear for GCID field or Error Text has changed");
		softassert.assertTrue(actualEmailErrorText.equals(expectedEmailErrorText), "Error Message did not appear for Email field or Error Text has changed");
		
		System.out.println("Valid Error Message appeared after omitting both fields");
		Reporter.log("Valid Error Message appeared after omitting both fields");

		driver.navigate().refresh();
		
		wait.until(ExpectedConditions.elementToBeClickable(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN));
		
//		4. If a user submits an invalid card number and valid email, a message would appear above the form.
		
		String invalidGCID = "9876543234567877";
		String invalidCardHolderEmail = "kritika.bhat@tomjohn.com";
		
		driver.findElement(DIGITAL_GIFT_CARD_ID_FIELD).sendKeys(invalidGCID);
		driver.findElement(DIGITAL_GIFT_CARD_EMAIL_FIELD).sendKeys(validCardHolderEmail);
		driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		
		Thread.sleep(1000);
		
		String expectedFormErrorText = "We don't recognize this information, "
				+ "please make sure you have entered the correct card number and email address.";
		String actualFormErrorText = driver.findElement(DIGITAL_GIFT_CART_FORM_ERROR).getText();
		
		softassert.assertTrue(actualFormErrorText.equals(expectedFormErrorText), "Error Message did not appear for Form or Error Text has changed");
		
		System.out.println("Valid Error Message appeared after entering invalid GCID and Valid Email field");
		Reporter.log("Valid Error Message appeared after entering invalid GCID and Valid Email field");

		driver.navigate().refresh();
		
		wait.until(ExpectedConditions.elementToBeClickable(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN));
		
//		5. If a user submits an valid GCID and invalid email, a message would appear above the form.
		
		driver.findElement(DIGITAL_GIFT_CARD_ID_FIELD).sendKeys(validGCID);
		driver.findElement(DIGITAL_GIFT_CARD_EMAIL_FIELD).sendKeys(invalidCardHolderEmail);
		driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		
		Thread.sleep(1000);
		
		actualFormErrorText = driver.findElement(DIGITAL_GIFT_CART_FORM_ERROR).getText();
		
		softassert.assertTrue(actualCardErrorText.equals(expectedCardErrorText), "Error Message did not appear for Form or Error Text has changed");
		
		System.out.println("Valid Error Message appeared after entering valid GCID and inValid Email field");
		Reporter.log("Valid Error Message appeared after entering valid GCID and inValid Email field");

		driver.navigate().refresh();
		
		wait.until(ExpectedConditions.elementToBeClickable(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN));
		
//		4. If a user submits an invalid GCID and invalid email, a message would appear above the form.
		
		driver.findElement(DIGITAL_GIFT_CARD_ID_FIELD).sendKeys(invalidGCID);
		driver.findElement(DIGITAL_GIFT_CARD_EMAIL_FIELD).sendKeys(invalidCardHolderEmail);
		driver.findElement(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN).click();
		
		Thread.sleep(1000);
		
		actualFormErrorText = driver.findElement(DIGITAL_GIFT_CART_FORM_ERROR).getText();
		
		softassert.assertTrue(actualCardErrorText.equals(expectedCardErrorText), "Error Message did not appear for Form or Error Text has changed");
		
		System.out.println("Valid Error Message appeared after entering invalid GCID and invalid Email field");
		Reporter.log("Valid Error Message appeared after entering invalid GCID and invalid Email field");

		driver.navigate().refresh();
		
		wait.until(ExpectedConditions.elementToBeClickable(DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN));
		
		softassert.assertAll();
		
	}

}
