package com.tommyjohn.automation.Components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.LoyaltyOptInLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class LoyaltyOptInComponents extends LoyaltyOptInLocators {

	public WebDriver driver;
	String testemail;
	String password;
	String currentUrl;
	WebElement element;
	public Actions actions;
	JavascriptExecutor executor = (JavascriptExecutor)driver;

	public LoyaltyOptInComponents(WebDriver driver) {
		this.driver = driver;
	}

	public void loyaltyOptinfromCA() throws Exception
	{


		JavascriptExecutor executor = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		actions = new Actions(driver);
		testemail = "testaccount" + System.currentTimeMillis() +"@gmail.com";
		password = CustomUtilities.properties.getProperty("password");

		WebElement element = driver.findElement((HomePageLocators.ACCOUNT_ICON));

		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_BTN).click();
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_EMAIL).clear();
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_EMAIL).sendKeys(testemail);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_PASSWORD).clear();
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_PASSWORD).sendKeys(password);

		driver.findElement(AccountLoginLocators.CREATEACCOUNT_BUTTON).click();
		Thread.sleep(15000);
		//driver.findElement(AccountLoginLocators.SWELLOPTIN_CHCKBOX).click();

		softAssert.assertEquals(driver.findElement(ACCOUNTPAGE_SWELLSTAUS).getText(), "Silver" , "Wrong status assigned for loyalty optin");
		softAssert.assertEquals(driver.findElement(ACCOUNTPAGE_SWELLPOINTS).getText(), "200", "Points not correctly assigned for loyalty optin");

		driver.findElement(ACCOUNTPAGE_SWELLREDEEMBTN).click();
		Thread.sleep(4000);
		softAssert.assertEquals(driver.getTitle() , "Loyalty" , "Wrong page opened after clicking on redeem now");

		driver.navigate().back();
		Thread.sleep(7000);
		WebElement elementi = driver.findElement(ACCOUNTPAGE_SWELLHISTORY);
		executor.executeScript("arguments[0].click();", elementi);

		Thread.sleep(7000);
		WebElement el = driver.findElement(By.className("yui3-cssreset"));
		driver.switchTo().frame(el);

		softAssert.assertEquals(driver.findElement(REWARDS_POPUP_TITLE).getText(), "My Rewards" , "Rewards history not shown after clicking on Rewards History button on Account page");

		driver.findElement(REWARDS_POPUP_CLOSE).click();
		driver.switchTo().defaultContent();
		softAssert.assertAll();

	}

	public void loyaltyOptinfromAccountPage() throws Exception
	{

		SoftAssert softAssert = new SoftAssert();
		actions = new Actions(driver);
		testemail = "testaccount" + System.currentTimeMillis() +"@gmail.com";
		password = CustomUtilities.properties.getProperty("password");

		WebElement element = driver.findElement((HomePageLocators.ACCOUNT_ICON));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_BTN).click();
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_EMAIL).clear();
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_EMAIL).sendKeys(testemail);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_PASSWORD).clear();
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_PASSWORD).sendKeys(password);

		driver.findElement(AccountLoginLocators.SWELLOPTIN_CHCKBOX).click();

		driver.findElement(AccountLoginLocators.CREATEACCOUNT_BUTTON).click();
		Thread.sleep(7000);


		softAssert.assertEquals(driver.findElement(ACCOUNTPAGE_SWELLJOINNOWMSG).getText(), "Join the rewards program today!", "Wrong status displayed for loyalty optin");

		WebElement elem = driver.findElement(ACCOUNTPAGE_SWELLHISTORY);
		executor.executeScript("arguments[0].click();", elem);
		Thread.sleep(4000);
		System.out.println(driver.getTitle());
		softAssert.assertEquals(driver.getTitle() , "Loyalty" , "Wrong page opened after clicking on redeem now");

		driver.navigate().back();
		Thread.sleep(5000);
		WebElement ele = driver.findElement(JOIN_SWELL);
		executor.executeScript("arguments[0].click();", ele);

		Thread.sleep(15000);
		System.out.println(driver.findElement(ACCOUNTPAGE_SWELLSTAUS).getText());

		softAssert.assertEquals(driver.findElement(ACCOUNTPAGE_SWELLSTAUS).getText(), "Silver" , "Wrong status assigned for loyalty optin");
		softAssert.assertEquals(driver.findElement(ACCOUNTPAGE_SWELLPOINTS).getText(), "200", "Points not correctly assigned for loyalty optin");

		driver.findElement(ACCOUNTPAGE_SWELLREDEEMBTN).click();
		Thread.sleep(5000);
		softAssert.assertEquals(driver.getTitle() , "Loyalty" , "Wrong page opened after clicking on redeem now");

		driver.navigate().back();
		Thread.sleep(5000);

		WebElement elementi = driver.findElement(ACCOUNTPAGE_SWELLHISTORY);
		executor.executeScript("arguments[0].click();", elementi);
		Thread.sleep(5000);

		WebElement el = driver.findElement(By.className("yui3-cssreset"));
		driver.switchTo().frame(el);

		softAssert.assertEquals(driver.findElement(REWARDS_POPUP_TITLE).getText(), "My Rewards" , "Rewards history not shown after clicking on Rewards History button on Account page");

		driver.findElement(REWARDS_POPUP_CLOSE).click();
		driver.switchTo().defaultContent();
		softAssert.assertAll();

	}


	public void loyaltyOptinfromLoyaltyPage() throws Exception

	{
		SoftAssert softAssert = new SoftAssert();
		actions = new Actions(driver);
		testemail = "testaccount" + System.currentTimeMillis() +"@gmail.com";
		password = CustomUtilities.properties.getProperty("password");

		WebElement element = driver.findElement((HomePageLocators.ACCOUNT_ICON));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_BTN).click();
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_EMAIL).clear();
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_EMAIL).sendKeys(testemail);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_PASSWORD).clear();
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_PASSWORD).sendKeys(password);

		driver.findElement(AccountLoginLocators.SWELLOPTIN_CHCKBOX).click();

		driver.findElement(AccountLoginLocators.CREATEACCOUNT_BUTTON).click();
		Thread.sleep(7000);

		driver.findElement(ACCOUNTPAGE_SWELLHISTORY).click();
		Thread.sleep(8000);
		softAssert.assertEquals(driver.getTitle() , "Loyalty" , "Wrong page opened after clicking on redeem now");

		WebElement ele = driver.findElement(JOIN_SWELL);
		executor.executeScript("arguments[0].click();", ele);
		Thread.sleep(9000);

		element = driver.findElement(HomePageLocators.ACCOUNT_ICON);
		actions.moveToElement(element).perform();
		driver.findElement(AccountLoginLocators.MYACCOUNT_OPTION_IN_ACCOUNT_ICON).click();

		Thread.sleep(15000);
		softAssert.assertEquals(driver.findElement(ACCOUNTPAGE_SWELLSTAUS).getText(), "Silver" , "Wrong status assigned for loyalty optin");
		softAssert.assertEquals(driver.findElement(ACCOUNTPAGE_SWELLPOINTS).getText(), "200", "Points not correctly assigned for loyalty optin");
		softAssert.assertAll();	
	}

	public void LoyaltyPageValidationforGuestUser() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();

		driver.findElement(HomePageLocators.TOMMYJOHN_REWARDS_LINK).click();
		Thread.sleep(6000);
		System.out.println(driver.findElement(SWELLBOX_DESCRIPTION).getText());

		softAssert.assertTrue(driver.findElement(JOIN_SWELL).isEnabled(), "Join Now button on loyalty page is not enables/displayed");
		softAssert.assertTrue(driver.findElement(SWELLBOX_ICON).isDisplayed(), "Swell work box icon is not displayed");
		softAssert.assertEquals(driver.findElement(SWELLBOX_HEADER).getText(), "Earn Points" , "Swell work box header is not correct");
		softAssert.assertEquals(driver.findElement(SWELLBOX_DESCRIPTION).getText(), "Redeem points\nfor exclusive discounts."  , "Swell box description is not correct");

		softAssert.assertEquals(driver.findElement(EARNMORE_HEADING).getText(), "TIERS AS EASY AS CAKE" , "TIERS AS EASY AS CAKE heading not correct");
		softAssert.assertEquals(driver.findElement(TIER1_NAME).getText(), "Silver" , "Silver status not correctly displayed");
		softAssert.assertEquals(driver.findElement(TIER2_NAME).getText(), "Gold" , "Gold status not correctly displayed");
		softAssert.assertEquals(driver.findElement(TIER3_NAME).getText(), "Platinum" , "Platinum status not correctly displayed");

		softAssert.assertTrue(driver.findElement(TIER1_DETAILS).isDisplayed(), "Details for Silver status not displayed");
		softAssert.assertTrue(driver.findElement(TIER2_DETAILS).isDisplayed(), "Details for Gold status not displayed");
		softAssert.assertTrue(driver.findElement(TIER3_DETAILS).isDisplayed(), "Details for Platinum status not displayed");

		WebElement elem = driver.findElement(WAYTOEARNMORE_HEADING);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(NOTMEMBER_POINTS_CREATEACCOUNT));

		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_CREATEACCOUNT).getText(), "200 Points" , "Text change for points on creating account");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_MAKEPURCHASE).getText(), "5 Points Per $1" , "Text change for points on making purchase");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_FACEBOOK).getText(), "25 Points" , "Text change for points on facebook");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_BIRTHDAY).getText(), "200 Points" , "Text change for points on birthday");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_INSTA).getText(), "25 Points" , "Text change for points on insta");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_REVIEW).getText(), "50 Points" , "Text change for points on review");

		softAssert.assertEquals(driver.findElement(NOTMEMBER_CREATEACCOUNT_TEXT).getText(), "Create An Account" , "Text change for create account");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_MAKEPURCHASE_TEXT).getText(), "Make A Purchase" , "Text change for make purchase");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_FACEBOOK_TEXT).getText(), "Follow On Facebook" , "Text change for facebook");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_BIRTHDAY_TEXT).getText(), "Tell Us Your Birthday" , "Text change for birthday");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_INSTA_TEXT).getText(), "Follow Us On Instagram" , "Text change for Insta");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_REVIEW_TEXT).getText(), "Leave A Review" , "Text change for Review");

		softAssert.assertTrue(driver.findElement(REFERRAL_BANNER_IMAGE).isDisplayed(), "Referral banner image is not displayed");
		softAssert.assertEquals(driver.findElement(SWELL_REFERRAL_HEADING).getText(), "Give $15 , Get $15" , "Swell referral heading not correct");
		softAssert.assertEquals(driver.findElement(SWELL_REFERRAL_SUBHEADING).getText(), "Please submit your email below to register." , "Swell referral sub heading not correct");
		driver.findElement(SWELL_REFERRAL_EMAIl).sendKeys("vijeta@tommyjohnwear.com");
		softAssert.assertTrue(driver.findElement(SWELL_REFERRAL_SUBMIT_BTN).isEnabled(), "Swell referral submit button not enabled");


		softAssert.assertEquals(driver.findElement(SWELL_REDEMPTION_HEADING).getText(), "REDEEM FOR DISCOUNTS" , " Swell redemption section heading not correct");
		//		WebElement ele = driver.findElement(SWELL_REDEMPTION_JOIN_NOW);
		//		executor.executeScript("arguments[0].click();", ele);
		driver.findElement(SWELL_REDEMPTION_JOIN_NOW).click();
		Thread.sleep(15000);
		softAssert.assertEquals(driver.getTitle(), "Account" , "User not redirected to login page after clicking on Join Now");


		softAssert.assertAll();

	}


	public void LoyaltyPageValidationForRegUser() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		actions = new Actions(driver);
		testemail = "Testaccount" + System.currentTimeMillis() +"@gmail.com";
		password = CustomUtilities.properties.getProperty("password");

		WebElement element = driver.findElement((HomePageLocators.ACCOUNT_ICON));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_BTN).click();
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_EMAIL).clear();
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_EMAIL).sendKeys(testemail);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_PASSWORD).clear();
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CREATEACCOUNT_PASSWORD).sendKeys(password);

		driver.findElement(AccountLoginLocators.CREATEACCOUNT_BUTTON).click();
		Thread.sleep(7000);

		driver.findElement(HomePageLocators.TOMMYJOHN_REWARDS_LINK).click();
		Thread.sleep(20000);

		System.out.println(driver.findElement(CUSTOMERNAME).getText() + "@gmail.com");
		softAssert.assertEquals(driver.findElement(CUSTOMERNAME).getText() + "@gmail.com", testemail , "Customer name not correct on loyalty page");
		softAssert.assertEquals(driver.findElement(POINTS).getText(), "200" , "Points not correctly refelcted" );
		softAssert.assertTrue(driver.findElement(ACCOUNTPAGE_SWELLREDEEMBTN).isEnabled(), "Redeem now button not enabled");
		softAssert.assertTrue(driver.findElement(ACCOUNTPAGE_SWELLHISTORY).isEnabled(), "Rewards History button not enabled");

		WebElement elementi = driver.findElement(ACCOUNTPAGE_SWELLHISTORY);
		executor.executeScript("arguments[0].click();", elementi);
		Thread.sleep(5000);

		WebElement el = driver.findElement(By.className("yui3-cssreset"));
		driver.switchTo().frame(el);

		softAssert.assertEquals(driver.findElement(REWARDS_POPUP_TITLE).getText(), "My Rewards" , "Rewards history not shown after clicking on Rewards History button on Account page");

		driver.findElement(REWARDS_POPUP_CLOSE).click();
		driver.switchTo().defaultContent();
		Thread.sleep(3000);

		softAssert.assertEquals(driver.findElement(EARNMORE_HEADING).getText(), "TIERS AS EASY AS CAKE" , "TIERS AS EASY AS CAKE heading not correct");
		System.out.println(driver.findElement(CURRENT_STATUS).getText());
		softAssert.assertEquals(driver.findElement(CURRENT_STATUS).getText(), "CURRENT STATUS" , "Silver is not reflected as current status on loyalty page");

		softAssert.assertEquals(driver.findElement(SWELL_REDEMPTION_HEADING).getText(), "REDEEM FOR DISCOUNTS" , " Swell redemption section heading not correct");


		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_CREATEACCOUNT).getText(), "200 Points" , "Text change for points on creating account");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_MAKEPURCHASE).getText(), "5 Points Per $1" , "Text change for points on making purchase");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_FACEBOOK).getText(), "25 Points" , "Text change for points on facebook");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_BIRTHDAY).getText(), "200 Points" , "Text change for points on birthday");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_INSTA).getText(), "25 Points" , "Text change for points on insta");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_POINTS_REVIEW).getText(), "50 Points" , "Text change for points on review");

		softAssert.assertEquals(driver.findElement(NOTMEMBER_CREATEACCOUNT_TEXT).getText(), "Create An Account" , "Text change for create account");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_MAKEPURCHASE_TEXT).getText(), "Make A Purchase" , "Text change for make purchase");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_FACEBOOK_TEXT).getText(), "Follow On Facebook" , "Text change for facebook");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_BIRTHDAY_TEXT).getText(), "Tell Us Your Birthday" , "Text change for birthday");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_INSTA_TEXT).getText(), "Follow Us On Instagram" , "Text change for Insta");
		softAssert.assertEquals(driver.findElement(NOTMEMBER_REVIEW_TEXT).getText(), "Leave A Review" , "Text change for Review");

		softAssert.assertEquals(driver.findElement(SWELL_REFERRAL_HEADING).getText(), "Give $15 , Get $15" , "Swell referral heading not correct");
		softAssert.assertEquals(driver.findElement(SWELL_REFERRAL_SUBHEADING).getText(), "Now, Enter your friend’s email." , "Swell referral sub heading not correct");
		driver.findElement(SWELL_REFERRAL_EMAIl).sendKeys("vijeta@tommyjohnwear.com");
		softAssert.assertTrue(driver.findElement(SWELL_REFERRAL_SUBMIT_BTN).isEnabled(), "Swell referral submit button not enabled");


		softAssert.assertAll();

	
	}
	
	
	public void SwellPointValidation() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		
		
		new CheckoutPageComponents(driver).registeredusercheckoutflow();
		softAssert.assertEquals(driver.findElement(REDEEM_REWARDS_HEADING).getText(), "Redeem Reward Points Here" , "Redeem rewards title not correct on checkout page");
		softAssert.assertTrue(driver.findElement(SWELL_POINTS_BALANCE).isDisplayed(), "Point balance not present on checkout page");
		
		Select opt = new Select(driver.findElement(SWELL_OPTIONS_DROPDOWN));
		opt.selectByValue("294462");
		opt.selectByValue("294461");
		opt.selectByValue("289351");
		
		driver.findElement(SWELL_REDEEM_BTN).click();
		Thread.sleep(4000);
		softAssert.assertTrue(driver.findElement(SWELL_ERROR_MESSAGE).isDisplayed(), "Error message not displayed after selecting option greater than available balance");
		softAssert.assertAll();
		
		
		
	}
}

