package com.tommyjohn.automation.Components;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
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

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.CA_SupplyChainsActPageLocators;
import com.tommyjohn.automation.PageLocators.DoNotSellMyInfoPageLocators;
import com.tommyjohn.automation.PageLocators.FooterSectionLocators;
import com.tommyjohn.automation.PageLocators.PrivacyPolicyPageLocators;
import com.tommyjohn.automation.PageLocators.TermsAndConditionsPageLocators;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;

public class FooterSectionComponents extends FooterSectionLocators {

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

	public FooterSectionComponents(WebDriver driver) {

		this.driver = driver;

//		wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
//				.withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);

	}

	public void validateFooterShopSection() throws Exception {
		softassert = new SoftAssert();

		System.out.println("\n---Checking Footer Shop Section Links---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		Thread.sleep(3000);

//		Scroll to bottom of page for footer section to be visible
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		Check Shop Women
		System.out.println("***Validating Shop Women Footer***");

		text = driver.findElement(SHOP_WOMEN).getText();

		softassert.assertTrue(text.equals("Shop Women"), "Shop Women link text is changed");

		objGeneralMethods.clickButtonByJS(driver, SHOP_WOMEN);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.titleContains("Women's All"));

		softassert.assertTrue(driver.getCurrentUrl().contains("https://www.tommyjohn.com/collections/womens-all"),
				"Wrong page opened after Shop Women footer link clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out
				.println("Shop Women Footer Link Text is Correct and Correct Webpage opened after clicking on link\n");

		Reporter.log("Shop Women footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Shop Women footer link is Displayed :: Clickable");

//		Check Shop Men
		System.out.println("***Validating Shop Men Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		text = driver.findElement(SHOP_MEN).getText();
		softassert.assertTrue(text.equals("Shop Men"), "Shop Men link text is changed");

		objGeneralMethods.clickButtonByJS(driver, SHOP_MEN);

		wait.until(ExpectedConditions.titleContains("Men's All"));

		softassert.assertTrue(driver.getCurrentUrl().contains("https://www.tommyjohn.com/collections/mens-all"),
				"Wrong page opened after Shop Men Footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Shop Men Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Shop Men footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Shop Men footer link is Displayed :: Clickable");

//		Check Fabric Guide Footer
		System.out.println("***Validating Fabric Guide Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		text = driver.findElement(FABRIC_GUIDE_LINK).getText();
		softassert.assertTrue(text.equals("Fabric Guide"), "Fabric Guide link text is changed");

		objGeneralMethods.clickButtonByJS(driver, FABRIC_GUIDE_LINK);

		wait.until(ExpectedConditions.titleContains("Fabric Guide"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/fabric-guide"),
				"Wrong page opened after Fabric Guide Footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Fabric Guide Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Fabric Guide footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Fabric Guide footer link is Displayed :: Clickable");

//		Check Gift Cards Footer
		System.out.println("***Validating Gift Cards Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		text = driver.findElement(GIFT_CARD_LINK).getText();
		softassert.assertTrue(text.equals("Gift Cards"), "Gift Cards link text is changed");

		objGeneralMethods.clickButtonByJS(driver, GIFT_CARD_LINK);

		wait.until(ExpectedConditions.titleContains("Gift Cards"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/giftcards"),
				"Wrong page opened after Gift Cards Footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Gift Cards Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Gift Cards footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Gift Cards footer link is Displayed :: Clickable");
		
	}

	/**
	 * vValidates if all links in Promotion Section are present and they navigate to
	 * correct page
	 * 
	 * @throws Exception
	 */
	public void validateFooterPromotionsSection() throws Exception {

		softassert = new SoftAssert();
		System.out.println("\n---Checking Footer Promotions Section---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

//		Thread.sleep(3000);
		objGeneralMethods.waitTillPageLoad(driver);

//		Check Sitewide Promotion Link
		System.out.println("***Validating Sitewide Promotion Footer***");

//		Scroll to bottom of page for footer section to be visible
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		linkText = driver.findElement(SITEWIDE_PROMOTION_LINK).getText();

		softassert.assertTrue(linkText.equals("Sitewide Promotion"), "Sitewide Promotion link text is changed");

		objGeneralMethods.clickButtonByJS(driver, SITEWIDE_PROMOTION_LINK);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.titleContains("Sitewide Promotion"));

		softassert.assertTrue(
				driver.getCurrentUrl().contains("https://www.tommyjohn.com/pages/sitewide-promotion-disclaimer"),
				"Wrong page opened after Sitewide Promotion footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out.println(
				"Sitewide Promotion Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Sitewide Promotion footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Sitewide Promotion footer link is Displayed :: Clickable");

//		Check Military Discount
		System.out.println("***Validating Military Discount Footer***");
//		Go back to Homepage and wait for load
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(MILITARY_DISCOUNT_LINK).getText();
		softassert.assertTrue(linkText.equals("Military Discount"), "Military Discount link text is changed");

		objGeneralMethods.clickButtonByJS(driver, MILITARY_DISCOUNT_LINK);

		Thread.sleep(2000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Military Discount Link text is Correct and Popup opened after clicking on link\n");
		Reporter.log("Military Discount footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Military Discount footer link is Displayed :: Clickable");
		
//		Check First Responder link
		System.out.println("***Validating First Responder Footer***");

		linkText = driver.findElement(FIRST_RESPONDER).getText();

		softassert.assertTrue(linkText.equals("First Responder"), "First Responder link text is changed");

		objGeneralMethods.clickButtonByJS(driver, FIRST_RESPONDER);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("First Responder Link text is Correct and Popup opened after clicking on link\n");

		Reporter.log("First Responder footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("First Responder footer link is Displayed :: Clickable");
		
//		Check Government Employee Discount link
		System.out.println("***Validating Government Employee Discount Footer***");

		linkText = driver.findElement(GOVERNMENT_EMPLOYEE_DISCOUNT).getText();

		softassert.assertTrue(linkText.equals("Government Employee Discount"),
				"Government Employee Discount link text is changed");

		objGeneralMethods.clickButtonByJS(driver, GOVERNMENT_EMPLOYEE_DISCOUNT);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out
				.println("Government Employee Discount Link text is Correct and Popup opened after clicking on link\n");

		Reporter.log("Government Employee Discount footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Government Employee Discount footer link is Displayed :: Clickable");
		
//		Check Teacher Discount link
		System.out.println("***Validating Teacher Discount Footer***");

		linkText = driver.findElement(TEACHER_DISCOUNT_LINK).getText();

		softassert.assertTrue(linkText.equals("Teacher Discount"), "Teacher Discount link text is changed");

		objGeneralMethods.clickButtonByJS(driver, TEACHER_DISCOUNT_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Teacher Discount Link text is Correct and Popup opened after clicking on link\n");

		Reporter.log("Teacher Discount footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Teacher Discount footer link is Displayed :: Clickable");
		
//		Check Referral Link

		System.out.println("***Validating Referral Footer***");

		linkText = driver.findElement(REFERRAL_LINK).getText();

		softassert.assertTrue(linkText.contains("Give $"), "Give $25, Get $25 link text is changed");

		objGeneralMethods.clickButtonByJS(driver, REFERRAL_LINK);

		wait.until(ExpectedConditions.titleContains("Refer a Friend"));

		softassert.assertTrue(driver.getCurrentUrl().contains("https://www.tommyjohn.com/pages/refer"),
				"Wrong page opened after Give $25, Get $25 footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println(
				"Give $25, Get $25 Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Give $25, Get $25 footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Give $25, Get $25 footer link is Displayed :: Clickable");
	}

	/**
	 * Validates if all links in Account Section are present and they navigate to
	 * correct page
	 * 
	 * @throws Exception
	 */
	public void validateFooterAccountSection() throws Exception {
		softassert = new SoftAssert();

		System.out.println("\n---Checking Footer Account Section Links---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		Thread.sleep(3000);

//		Scroll to bottom of page for footer section to be visible
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		Check Sign In link
		System.out.println("***Validating Sign In Footer***");

		linkText = driver.findElement(SIGN_IN_FOOTER_LINK).getText();

		softassert.assertTrue(linkText.equals("Sign In"), "Sign In link text is changed");

		objGeneralMethods.clickButtonByJS(driver, SIGN_IN_FOOTER_LINK);
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.titleContains("Account"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/account/login?return_url=%2Faccount"),
				"Wrong page opened after Sign In footer link clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out.println("Sign In Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Sign In footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Sign In footer link is Displayed :: Clickable");
		
//		Check Order History link
		System.out.println("***Validating Order History Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(ORDER_HISTORY).getText();
		System.out.println("Order History Link Text is : " + linkText);

		softassert.assertTrue(linkText.equals("Order History"), "Order History Link text is changed");

		objGeneralMethods.clickButtonByJS(driver, ORDER_HISTORY);

		wait.until(ExpectedConditions.titleContains("Account"));

		softassert.assertTrue(
				driver.getCurrentUrl()
						.equalsIgnoreCase("https://www.tommyjohn.com/account/login?return_url=%2Faccount"),
				"Wrong page opened after Order History footer link clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out.println(
				"Order History Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Sign In footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Sign In footer link is Displayed :: Clickable");
	}

	/**
	 * Validates if all links in Support Section are present and they navigate to
	 * correct page
	 * 
	 * @throws Exception
	 */
	public void validateFooterSupportSection() throws Exception {
		softassert = new SoftAssert();
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		System.out.println("\n---Checking Footer Support Section Links---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		Thread.sleep(3000);

//		Scroll to bottom of page for footer section to be visible
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		Check Contact Us link
		System.out.println("***Validating Contact & FAQ Footer***");

		linkText = driver.findElement(CONTACT_US_LINK).getText();

		softassert.assertTrue(linkText.equals("Contact & FAQ"), "Contact Us link text is changed");

		objGeneralMethods.clickButtonByJS(driver, CONTACT_US_LINK);

		wait.until(ExpectedConditions.urlContains("help"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us"),
				"Wrong page opened after Contact Us footer link clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out
				.println("Contact Us Footer Link text is correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Contact Us footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Contact Us footer link is Displayed :: Clickable");
		/**
		 * // Check Returns & Exchanges Link System.out.println("***Validating Returns &
		 * Exchanges Footer***"); driver.navigate().back();
		 * wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));
		 * 
		 * linkText = driver.findElement(RETURNS_AND_EXCHANGES).getText();
		 * softassert.assertTrue(linkText.equals("Returns & Exchanges"), "Returns and
		 * Exchanges link text is changed");
		 * 
		 * objGeneralMethods.clickButtonByJS(RETURNS_AND_EXCHANGES);
		 * 
		 * wait.until(ExpectedConditions.titleContains("Returns"));
		 * 
		 * softassert.assertTrue( driver.getCurrentUrl().equalsIgnoreCase(
		 * "https://help.tommyjohn.com/hc/en-us/sections/360000065123-Returns-Exchanges"),
		 * "Wrong page opened after Returns And Exchanges footer link clicked");
		 * 
		 * softassert.assertAll();
		 * 
		 * System.out.println("Returns and Exchange Footer Link text is correct and
		 * Correct webpage opened after clicking on link\n"); Reporter.log("Returns and
		 * Exchange footer link is Displayed :: Clickable");
		 * 
		 * 
		 * // Check Shipping and Returns System.out.println("***Validating Shipping and
		 * Returns Footer***"); driver.navigate().back();
		 * wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));
		 * 
		 * linkText = driver.findElement(SHIPPING_AND_RETURNS_LINK).getText();
		 * softassert.assertTrue(linkText.equals("Shipping & Return Policy"), "Shipping
		 * and Return Policy Link text is changed");
		 * 
		 * objGeneralMethods.clickButtonByJS(SHIPPING_AND_RETURNS_LINK);
		 * 
		 * wait.until(ExpectedConditions.titleContains("Shipping"));
		 * 
		 * softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(
		 * "https://help.tommyjohn.com/hc/en-us/articles/360000507746-Shipping-Returns"),
		 * "Wrong page opened after Shipping and Returns Footer link clicked");
		 * 
		 * softassert.assertAll();
		 * 
		 * System.out.println ("Shipping and Returns Link text is correct and Correct
		 * webpage opened after clicking on link\n"); Reporter.log("Shipping and Returns
		 * footer link is Displayed :: Clickable");
		 **/

//		Check Gift card Balance Page
		System.out.println("***Validating Gift Card Balance Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(GIFT_CARD_BALANCE_CHECK).getText();
		softassert.assertTrue(linkText.equals("Check Gift Card Balance"),
				"Check Gift Card Balance link text is changed");

		objGeneralMethods.clickButtonByJS(driver, GIFT_CARD_BALANCE_CHECK);

		wait.until(ExpectedConditions.titleContains("My Card Balance"));

		softassert.assertTrue(
				driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/my-card-balance"),
				"Wrong page opened after Check Gift Card Balance Footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println(
				"Check Gift Card Balance Link text is correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Check Gift Card Balance footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Check Gift Card Balance footer link is Displayed :: Clickable");
		
//		Check Tommy John Stores
		System.out.println("***Validating Tommy John Stores Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(TOMMY_JOHN_STORES).getText();
		softassert.assertTrue(linkText.equals("Tommy John Stores"), "Tommy John Stores link text is changed");

		objGeneralMethods.clickButtonByJS(driver, TOMMY_JOHN_STORES);

		wait.until(ExpectedConditions.titleContains("Store Directory"));

		softassert.assertTrue(
				driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/store-directory"),
				"Wrong page opened after Tommy John Stores Footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out
				.println("Tommy John Stores Link text is correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Tommy John Stores footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Tommy John Stores footer link is Displayed :: Clickable");
		
//		Check Store Locator
		System.out.println("***Validating Store Locator Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(STORE_LOCATOR_LINK).getText();
		softassert.assertTrue(linkText.equals("Store Locator"), "Store Locator link text is changed");

		objGeneralMethods.clickButtonByJS(driver, STORE_LOCATOR_LINK);

		wait.until(ExpectedConditions.titleContains("Store Locator"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/store-locator"),
				"Wrong page opened after Store Locator Footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println(
				"Store Locator Footer Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Store Locator footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Store Locator footer link is Displayed :: Clickable");
		
//		Check Retailers Footer
		System.out.println("***Validating Retailers Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(RETAILERS_LINK).getText();
		softassert.assertTrue(linkText.equals("Retailers"), "Retailers link text is changed");

		objGeneralMethods.clickButtonByJS(driver, RETAILERS_LINK);

		wait.until(ExpectedConditions.titleContains("Retailers"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/retailers"),
				"Wrong page opened after Retailers Footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Retailers Footer Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Retailers footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Retailers footer link is Displayed :: Clickable");
		
//		Check Affiliates Footer
		System.out.println("***Validating Affiliates Footer***");
		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(AFFILIATES_LINK).getText();
		softassert.assertTrue(linkText.equals("Affiliates"), "Affiliates link text is changed");

		objGeneralMethods.clickButtonByJS(driver, AFFILIATES_LINK);

		wait.until(ExpectedConditions.titleContains("Affiliate"));

		softassert.assertTrue(
				driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/affiliate-landing"),
				"Wrong page opened after Affiliates Footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out
				.println("Affiliates Footer Link text is Correct and Correct webpage opened after clicking on link\n");
		Reporter.log("Affiliates footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Affiliates footer link is Displayed :: Clickable");
		
	}

	/**
	 * Validates if all links in Company Section are present and they navigate to
	 * correct page
	 * 
	 * @throws Exception
	 */
	public void validateFooterCompanySection() throws Exception {

		softassert = new SoftAssert();
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		System.out.println("\n---Checking Footer Company Section Links---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		objGeneralMethods.waitTillPageLoad(driver);

		objGeneralMethods.scrollElementIntoView(driver, ABOUT_LINK);

//		Scroll to bottom of page for footer section to be visible
//		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

//		Check About Link
		System.out.println("***Validating About Footer***");

		linkText = driver.findElement(ABOUT_LINK).getText();

		softassert.assertTrue(linkText.equals("About"), "About link text is changed");

		objGeneralMethods.clickButtonByJS(driver, ABOUT_LINK);

		wait.until(ExpectedConditions.titleContains("About Us"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/about-us"),
				"Wrong page opened after About footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out.println("About Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("About footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("About footer link is Displayed :: Clickable");
		
//		Check Careers
		System.out.println("***Validating Careers Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(CAREERS_LINK).getText();

		softassert.assertTrue(linkText.equals("Careers"), "Careers Link text is changed");

		objGeneralMethods.clickButtonByJS(driver, CAREERS_LINK);

		wait.until(ExpectedConditions.titleContains("Careers"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/careers"),
				"Wrong page opened after Careers footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out.println("Careers Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Careers footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Careers footer link is Displayed :: Clickable");
		
//		Check Press
		System.out.println("***Validating Press Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(PRESS_LINK).getText();

		softassert.assertTrue(linkText.equals("Press"), "Press Link text is changed");

		objGeneralMethods.clickButtonByJS(driver, PRESS_LINK);

		wait.until(ExpectedConditions.titleContains("Press"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/press"),
				"Wrong page opened after Press footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out.println("Press Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Press footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Press footer link is Displayed :: Clickable");
		
//		Check Press
		System.out.println("***Validating Blog Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(BLOG_LINK).getText();

		softassert.assertTrue(linkText.equals("Blog"), "Blog Link text is changed");

		objGeneralMethods.clickButtonByJS(driver, BLOG_LINK);

		wait.until(ExpectedConditions.titleContains("News"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/blogs/news"),
				"Wrong page opened after Blog footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out.println("Blog Footer Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Blog footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Blog footer link is Displayed :: Clickable");
		
//		Check Reviews
		System.out.println("***Validating Reviews Footer***");

		driver.navigate().back();
		wait.until(ExpectedConditions.titleIs("Tommy John | No Adjustment Needed"));

		linkText = driver.findElement(REVIEWS_LINK).getText();

		softassert.assertTrue(linkText.equals("Reviews"), "Reviews Link text is changed");

		objGeneralMethods.clickButtonByJS(driver, REVIEWS_LINK);

		wait.until(ExpectedConditions.titleContains("Reviews"));

		softassert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.tommyjohn.com/pages/reviews"),
				"Wrong page opened after Reviews footer link was clicked");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}
		
		System.out.println("Reviews Link text is Correct and Correct webpage opened after clicking on link\n");

		Reporter.log("Reviews footer link is Displayed :: Clickable");
		ExtentReport.extentTest.info("Reviews footer link is Displayed :: Clickable");

	}

	/**
	 * Validates Social Media Icons in footer
	 * 
	 * @throws Exception
	 */
	public void validateFooterSocialLinks() throws Exception {
		String hrefLink;
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		System.out.println("\n---Checking CopyRight Seciton---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

//		Scroll the page at bottom to test all the footer Social Icons
		objGeneralMethods.scrollElementIntoView(driver, FACEBOOK_ICON);

//		Check Facebook Link
		System.out.println("***Validating Facebook Link***");
		softassert = new SoftAssert();

		element = driver.findElement(FACEBOOK_ICON);
		hrefLink = driver.findElement(FACEBOOK_LINK).getAttribute("href");

		objGeneralMethods.clickButtonByJS(driver, FACEBOOK_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertTrue(element.isDisplayed(), "Facebook Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://www.facebook.com/TommyJohnWear"),
				"Facebook Link is not Correct");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Facebook Icon is Present and Link is correct\n");
		Reporter.log("Facebook Footer Icon is Displayed :: Clickable");
		ExtentReport.extentTest.info("Reviews footer link is Displayed :: Clickable");

//		Check Twitter Link
		System.out.println("***Validating Twitter Link***");
		softassert = new SoftAssert();

		element = driver.findElement(TWITTER_ICON);
		hrefLink = driver.findElement(TWITTER_LINK).getAttribute("href");

		objGeneralMethods.clickButtonByJS(driver, TWITTER_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertTrue(element.isDisplayed(), "Twitter Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://twitter.com/TommyJohn"), "Twitter Link is not Correct");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Twitter Icon is Present and Link is correct\n");
		Reporter.log("Twitter Footer Icon is Displayed :: Clickable");
		ExtentReport.extentTest.info("Twitter Footer Icon is Displayed :: Clickable");

//		Check Pinterest Link
		System.out.println("***Validating Pinterest Link***");
		softassert = new SoftAssert();

		element = driver.findElement(PINTEREST_ICON);
		hrefLink = driver.findElement(PINTEREST_LINK).getAttribute("href");

		objGeneralMethods.clickButtonByJS(driver, PINTEREST_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertTrue(element.isDisplayed(), "Pinterest Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://www.pinterest.com/tommyjohnwear/"),
				"Pinterest Link is not Correct");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Pinterest Icon is Present and Link is correct\n");
		Reporter.log("Pinterest Footer Icon is Displayed :: Clickable");
		ExtentReport.extentTest.info("Pinterest Footer Icon is Displayed :: Clickable");

//		Check Instagram Link
		System.out.println("***Validating Instagram Link***");
		softassert = new SoftAssert();

		element = driver.findElement(INSTAGRAM_ICON);
		hrefLink = driver.findElement(INSTAGRAM_LINK).getAttribute("href");

		objGeneralMethods.clickButtonByJS(driver, INSTAGRAM_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertTrue(element.isDisplayed(), "Instagram Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://www.instagram.com/tommyjohnwear/"),
				"Pinterest Link is not Correct");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Instagram Icon is Present and Link is correct\n");
		Reporter.log("Instagram Footer Icon is Displayed :: Clickable");
		ExtentReport.extentTest.info("Instagram Footer Icon is Displayed :: Clickable");

//		Check Youtube Link
		System.out.println("***Validating Youtube Link***");
		softassert = new SoftAssert();

		element = driver.findElement(YOUTUBE_ICON);
		hrefLink = driver.findElement(YOUTUBE_LINK).getAttribute("href");

		objGeneralMethods.clickButtonByJS(driver, YOUTUBE_LINK);

		Thread.sleep(1000);

		list = driver.findElements(LEAVE_SITE_POPUP);

		Assert.assertTrue(list.size() > 0, "Leave Site Popup has not appeared");

		objGeneralMethods.clickButtonByJS(driver, LEAVE_SITE_POPUP_CANCEL_BTN);

		softassert.assertTrue(element.isDisplayed(), "Youtube Icon is not Displayed");
		softassert.assertTrue(hrefLink.equals("https://www.youtube.com/user/tommyjohnwear"),
				"Pinterest Link is not Correct");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Youtube Icon is Present and Link is correct\n");
		Reporter.log("Youtube Footer Icon is Displayed :: Clickable");
		ExtentReport.extentTest.info("Youtube Footer Icon is Displayed :: Clickable");

	}

	/**
	 * Validates the links - Privacy Policy, T&C, Do Not Sell My info, CA
	 * Transparency, CopyRight
	 * 
	 * @throws Exception
	 */
	public void validateFooterCopyrightCCPASection() throws Exception {

		String pageHeader;
		String pageURL;
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		System.out.println("\n---Checking CopyRight Seciton---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		objGeneralMethods.waitTillPageLoad(driver);

		objGeneralMethods.scrollElementIntoView(driver, PRIVACY_POLICY_LINK);

//		Checking Privacy Policy
		System.out.println("***Validating Privacy Policy***");

		softassert = new SoftAssert();

		element = driver.findElement(PRIVACY_POLICY_LINK);
		linkText = driver.findElement(PRIVACY_POLICY_LINK).getText();

		Assert.assertTrue(element.isDisplayed(), "Privacy Policy Link is not Displayed");
		softassert.assertTrue(linkText.equals("Privacy Policy"), "Privacy Policy Link Text is Incorrect");

		objGeneralMethods.clickButtonByJS(driver, element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(PrivacyPolicyPageLocators.PAGE_HEADER, 0));

		pageURL = driver.getCurrentUrl();
		pageHeader = driver.findElement(PrivacyPolicyPageLocators.PAGE_HEADER).getText();

		softassert.assertTrue(pageURL.equals("https://www.tommyjohn.com/pages/tommy-john-website-privacy-policy"),
				"Wrong Page opened after clicking on Privacy Policy Link ");
		softassert.assertTrue(pageHeader.equals("Tommy John Website Privacy Policy"), "Page Header doesn't match");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Privacy Policy Link is Displayed and Corresponding Page opened after clicking on Link\n");
		Reporter.log("Privacy Policy Link is Displayed and Corresponding Page opened after clicking on Link");
		ExtentReport.extentTest.info("Privacy Policy Link is Displayed and Corresponding Page opened after clicking on Link");

//		Checking Terms and Conditions
		driver.navigate().back();
		System.out.println("***Validating Terms and Conditions***");

		softassert = new SoftAssert();

		element = driver.findElement(TERMS_AND_CONDITION_LINK);
		linkText = driver.findElement(TERMS_AND_CONDITION_LINK).getText();

		Assert.assertTrue(element.isDisplayed(), "Terms and Conditions Link is not Displayed");
		softassert.assertTrue(linkText.equals("Terms & Conditions"), "Terms and Conditions Link Text is Incorrect");

		objGeneralMethods.clickButtonByJS(driver, element);

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(TermsAndConditionsPageLocators.PAGE_HEADER, 0));

		pageURL = driver.getCurrentUrl();
		pageHeader = driver.findElement(TermsAndConditionsPageLocators.PAGE_HEADER).getText();

		softassert.assertTrue(pageURL.equals("https://www.tommyjohn.com/pages/tommy-john-terms-and-conditions"),
				"Wrong Page opened after clicking on Terms & Conditions Link ");
		softassert.assertTrue(pageHeader.equals("Tommy John Terms & Conditions"), "Page Header doesn't match");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Tommy John Terms & Conditions Link is Displayed and Corresponding Page opened after clicking on Link\n");
		Reporter.log("Tommy John Terms & Conditions Link is Displayed and Corresponding Page opened after clicking on Link");
		ExtentReport.extentTest.info("Tommy John Terms & Conditions Link is Displayed and Corresponding Page opened after clicking on Link");

//		Checking Do Not Sell My Information
		driver.navigate().back();
		System.out.println("***Validating Do Not Sell My Information***");

		softassert = new SoftAssert();

		element = driver.findElement(DO_NOT_SELL_PERSONAL_INFO_LINK);
		linkText = driver.findElement(DO_NOT_SELL_PERSONAL_INFO_LINK).getText();

		Assert.assertTrue(element.isDisplayed(), "Do Not Sell My Information Link is not Displayed");
		softassert.assertTrue(linkText.equals("Do Not Sell My Information"),
				"Do Not Sell My Information Link Text is Incorrect");

		objGeneralMethods.clickButtonByJS(driver, element);

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(DoNotSellMyInfoPageLocators.PAGE_HEADER, 0));

		pageURL = driver.getCurrentUrl();
		pageHeader = driver.findElement(DoNotSellMyInfoPageLocators.PAGE_HEADER).getText();

		softassert.assertTrue(pageURL.equals("https://www.tommyjohn.com/pages/do-not-sell-my-personal-information"),
				"Wrong Page opened after clicking on Do Not Sell My Information Link ");
		softassert.assertTrue(
				pageHeader.contains(
						"For California residents"),
				"Page Header doesn't match");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Do Not Sell My Information Link is Displayed and Corresponding Page opened after clicking on Link\n");
		Reporter.log("Do Not Sell My Information Link is Displayed and Corresponding Page opened after clicking on Link");
		ExtentReport.extentTest.info("Do Not Sell My Information Link is Displayed and Corresponding Page opened after clicking on Link");

//		Checking CA Transparency in Supply Chains Act
		driver.navigate().back();
		System.out.println("***Validating CA Transparency in Supply Chains Act***");

		softassert = new SoftAssert();

		element = driver.findElement(CA_SUPPLY_CHAINS_ACT_LINK);
		linkText = driver.findElement(CA_SUPPLY_CHAINS_ACT_LINK).getText();

		Assert.assertTrue(element.isDisplayed(), "CA Transparency in Supply Chains Act Link is not Displayed");
		softassert.assertTrue(linkText.equals("CA Transparency in Supply Chains Act"),
				"CA Transparency in Supply Chains Act Link Text is Incorrect");

		objGeneralMethods.clickButtonByJS(driver, element);

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(CA_SupplyChainsActPageLocators.PAGE_HEADER, 0));

		pageURL = driver.getCurrentUrl();
		pageHeader = driver.findElement(CA_SupplyChainsActPageLocators.PAGE_HEADER).getText();

		softassert.assertTrue(pageURL.equals("https://www.tommyjohn.com/pages/california-supply-chains-act"),
				"Wrong Page opened after clicking on CA Transparency in Supply Chains Act");
		softassert.assertTrue(pageHeader.equals("California Supply Chains Act"), "Page Header doesn't match");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("CA Transparency in Supply Chains Act Link is Displayed and Corresponding Page opened after clicking on Link\n");
		Reporter.log("CA Transparency in Supply Chains Act Link is Displayed and Corresponding Page opened after clicking on Link");
		ExtentReport.extentTest.info("CA Transparency in Supply Chains Act Link is Displayed and Corresponding Page opened after clicking on Link");
		
	}

	/**
	 * Validates Sign Up for Email and Feedback Section
	 * 
	 * @throws Exception
	 */
	public void validateFooterNewsletter() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		System.out.println("\n---Checking Newsletter Seciton---");

		if (!driver.getCurrentUrl().equals("https://www.tommyjohn.com")) {
			driver.navigate().to("https://www.tommyjohn.com");
		}

		objGeneralMethods.waitTillPageLoad(driver);

		objGeneralMethods.scrollElementIntoView(driver, NEWSLETTER_TITLE);

		// Verify Footer Newsletter section
		System.out.println("***Validating Newsletter Email Sign Up***");

		softassert = new SoftAssert();

		softassert.assertTrue(driver.findElement(NEWSLETTER_TITLE).isDisplayed(),
				"Newsletter Title is not displayed on footer");
		softassert.assertTrue(
				driver.findElement(NEWSLETTER_MEN_RADIO_BUTTON).isDisplayed()
						&& driver.findElement(NEWSLETTER_MEN_RADIO_BUTTON).isEnabled(),
				"Newsletter Radio button 'Men' is either not Displayed or not Enabled on footer");
		softassert.assertTrue(
				driver.findElement(NEWSLETTER_WOMEN_RADIO_BUTTON).isDisplayed()
						&& driver.findElement(NEWSLETTER_WOMEN_RADIO_BUTTON).isEnabled(),
				"Newsletter Radio button 'Women' is either not Displayed or not Enabled on footer");
		softassert.assertTrue(
				driver.findElement(NEWSLETTER_BOTH_RADIO_BUTTON).isDisplayed()
						&& driver.findElement(NEWSLETTER_BOTH_RADIO_BUTTON).isEnabled(),
				"Newsletter Radio button 'Both' is either not Displayed or not Enabled on footer");
		softassert.assertTrue(driver.findElement(NEWSLETTER_INPUT_TEXTBOX).isDisplayed(),
				"Newsletter Email Input Text Box is not displayed on footer");
		softassert.assertTrue(driver.findElement(NEWSLETTER_SIGN_UP_BUTTON).isDisplayed(),
				"Newsletter Sign up button is not displayed on footer");

		softassert.assertTrue(driver.findElement(NEWSLETTER_BOTH_RADIO_BUTTON).isSelected(),
				"Radio Button 'Both' is not preselected for user");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
//		Negative Validation (User does not enter email)
		wait.until(ExpectedConditions.elementToBeClickable(NEWSLETTER_SIGN_UP_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(NEWSLETTER_SIGN_UP_BUTTON));
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(NEWSLETTER_FORM_FAIL_MESSAGE_VISIBLE, 0));

		softassert.assertTrue(driver.findElement(NEWSLETTER_FORM_FAIL_MESSAGE).isDisplayed(),
				"Error message is not displayed ");
		softassert.assertTrue(
				driver.findElement(NEWSLETTER_FORM_FAIL_MESSAGE).getText().equals("Please enter a valid email !"),
				"Incorrect message is displayed ");

//		Positive Validation
		String testemail = "testaccount" + System.currentTimeMillis() + "@gmail.com";
		driver.findElement(NEWSLETTER_INPUT_TEXTBOX).sendKeys(testemail);
		driver.findElement(NEWSLETTER_SIGN_UP_BUTTON).click();

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(NEWSLETTER_FORM_SUCCESS_MESSAGE_VISIBLE, 0));

		softassert.assertTrue(driver.findElement(NEWSLETTER_FORM_SUCCESS_MESSAGE).isDisplayed(),
				"Success message is not displayed after newsletter form submission");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Newsletter Email Sign Up functionality is working and Success Message is Displayed to user\n");
		Reporter.log("Newsletter Email Sign Up functionality is working and Success Message is Displayed to user");
		ExtentReport.extentTest.info("Newsletter Email Sign Up functionality is working and Success Message is Displayed to user");

	}

//		Validation of Give Feedback Section
	public void validateFeedbackSection() {

		JavascriptExecutor jse = (JavascriptExecutor)driver;

		wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		System.out.println("***Validating Feedback Section***");

		softassert = new SoftAssert();

		element = driver.findElement(GIVE_FEEDBACK_TITLE);

		Assert.assertTrue(driver.findElement(GIVE_FEEDBACK_CTA).isDisplayed(), "Give Feedback button is not present");
		softassert.assertTrue(element.isDisplayed(), "Feedback title is not present");
		softassert.assertTrue(element.getText().equals("Your feedback matters to us"), "Feedback title does not match");
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(GIVE_FEEDBACK_CTA));
		jse.executeScript("arguments[0].click();", driver.findElement(GIVE_FEEDBACK_CTA));


		wait.until(ExpectedConditions.titleContains("Survey"));

		//String feedbackPageHeader_TXT = driver.findElement(By.cssSelector(".comment-text")).getText();
		String Feedback_page_url = "https://selfserve.decipherinc.com/survey/selfserve/";

		//softassert.assertTrue(feedbackPageHeader_TXT.equals(
		//		"Thank you for taking our survey - your insights help us make sure we are building the best possible shopping experience for you. If you need any immediate assistance please call us 1-800-708-3490."),
		//		"Survey Page Header doesnt match");
		softassert.assertTrue(driver.getCurrentUrl().contains(Feedback_page_url),
				"Wrong page opened after click on Give Feedback CTA on footer");

		try{
			softassert.assertAll();
		}catch (AssertionError e) {
			ExtentReport.extentTest.fail(e);
			softassert.assertAll();
		}

		System.out.println("Give Feedback Section is present and corresponding page opened after clicking on 'Give Feedback' button");
		Reporter.log("Give Feedback Section is present and corresponding page opened after clicking on 'Give Feedback' button");
		ExtentReport.extentTest.info("Give Feedback Section is present and corresponding page opened after clicking on 'Give Feedback' button");
	}

}
