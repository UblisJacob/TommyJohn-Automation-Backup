package com.tommyjohn.automation.Components;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.tommyjohn.automation.PageLocators.CollectionPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class HomePageComponents extends HomePageLocators {

	public WebDriver driver;
	public static String baseUrl = CustomUtilities.baseUrl;
	public String currentUrl1;
	public String currentUrl2;
	boolean flag = false;
	public static WebElement element;
	public static WebElement ele1;
	public Actions action;
	JavascriptExecutor executor = (JavascriptExecutor)driver;


	String text;

	public HomePageComponents(WebDriver driver) {

		this.driver = driver;
	}

	//Verify logo and all category
	public void ValidateLogoAndCategoriesPresence() throws Exception
	{
		action = new Actions(driver);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if(driver.findElement(HOMEPAGE_LOGO).isDisplayed())
		{

			currentUrl1 = driver.getCurrentUrl();
			WebElement element = driver.findElement(HOMEPAGE_LOGO);
			
			executor.executeScript("arguments[0].click();", element);		
			String currentUrl2 = driver.getCurrentUrl();
			if(!currentUrl1.equals(currentUrl2))
				throw new Exception("HomePage logo not working correctly");
			Reporter.log("TommyJohnLogo is displayed and clickable :: home page opens on clicking logo");
		}
		else
			throw new Exception("HomePage logo is not present");

		//Verify new arrivals
//		if(driver.findElement(NEWARRIVALS_CATEGORY).isDisplayed()) {
//
//			element = driver.findElement(NEWARRIVALS_CATEGORY);
//			action.moveToElement(element).perform();
//			//			if(!driver.findElement(MEN_IN_NEWARRIVALS_CATEGORY).isEnabled())
//			//				throw new Exception("New Arrivals category not hoverable"); 
//		}
//		else
//			throw new Exception("New Arrivals category is not present");
//		Reporter.log("New Arrivals category is Displayed :: Hoverable");
//		Thread.sleep(3000);

		// Verify Men category
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(MEN_CATEGORY));
		if(!driver.findElement(MEN_CATEGORY).isDisplayed())
			throw new Exception("Men category is not present");	

		element = driver.findElement(MEN_CATEGORY);
		action.moveToElement(element).perform();
		if(!driver.findElement(FIRST_lEVEL1_CATEGORY).isDisplayed())
			throw new Exception("Men Category is not hoverable");
		Thread.sleep(3000);
		Reporter.log("Men category is displayed :: Hoverable");
    	Thread.sleep(3000);


		// Verify Women category
    	wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.presenceOfElementLocated(WOMEN_CATEGORY));
		if(!driver.findElement(WOMEN_CATEGORY).isDisplayed())
			throw new Exception("Women category is not displayed");
		element = driver.findElement(WOMEN_CATEGORY);
		action.moveToElement(element).perform();
		if(!driver.findElement(FIRST_lEVEL1_CATEGORY).isDisplayed())
			throw new Exception("Men Category is not hoverable");
		Reporter.log("Women category is Displayed :: Hoverable :: Clickable");
		Thread.sleep(3000);
		
		// Verify Final Sale category
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(FINAL_SALE));
		if(!driver.findElement(FINAL_SALE).isDisplayed())
			throw new Exception("Final Sale category is not displayed");
		element = driver.findElement(FINAL_SALE);
		action.moveToElement(element).perform();
	//	if(!driver.findElement(FIRST_lEVEL1_CATEGORY).isDisplayed())
		//	throw new Exception("Final Sale category is not hoverable");
		Reporter.log("Final Sale category is Displayed :: Hoverable :: clickable");
	}

		// Verify Underwear category
//		if(!driver.findElement(UNDERWEAR_CATEGORY).isDisplayed())
//			throw new Exception("Underwear category is not displayed");
//		element = driver.findElement(UNDERWEAR_CATEGORY);
//		action.moveToElement(element).perform();
//		if(!driver.findElement(MEN_IN_UNDERWEAR_CATEGORY).isDisplayed())
//			throw new Exception("Underwear Category is not hoverable");
//		Reporter.log("Underwear category is Displayed :: Hoverable");

//		Thread.sleep(3000);
		// Verify Shop by Bras category
//		if(!driver.findElement(BRAS).isDisplayed())
//			throw new Exception("Bras category is not displayed");
//		element = driver.findElement(BRAS);
//		action.moveToElement(element).perform();
//		if(!driver.findElement(BRAS_TITLE).isDisplayed())
//			throw new Exception("Bras category is not hoverable");
//		Reporter.log("Bras category is Displayed :: Hoverable");
//
//		Thread.sleep(3000);

		// Verify Packs category
//		if(!driver.findElement(PACKS).isDisplayed())
//			throw new Exception("Packs category is not displayed");
//		element = driver.findElement(PACKS);
//		action.moveToElement(element).perform();
//		if(!driver.findElement(MEN_IN_PACKS).isDisplayed())
//			throw new Exception("Packs category is not hoverable");
//		Reporter.log("Packs category is Displayed :: Hoverable");
//

		// Verify Gift Guide category
//		if(!driver.findElement(GIFT_GUIDE).isDisplayed())
//			throw new Exception("Gift guide category is not displayed");
//		element = driver.findElement(GIFT_GUIDE);
//		action.moveToElement(element).perform();
//		if(!driver.findElement(GIFTCARDS).isDisplayed())
//			throw new Exception("Gift Guide category is not hoverable");
//		Reporter.log("Gift Guide category is Displayed :: Hoverable");
//	}

	
	public void validateSearchFunction() throws Exception {

		SoftAssert softAssert = new SoftAssert();
		action = new Actions(driver);
	
		WebElement element = driver.findElement(SEARCH_ICON);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		driver.findElement(SEARCH_ICON).sendKeys("tees");
		
		  WebElement search = driver.findElement(SEARCH_ICON);
		  action.moveToElement(driver.findElement(SEARCH_ICON)).build().perform();
		  Thread.sleep(3000);
		  softAssert.assertTrue(driver.findElement(POPULAR_SEARCHES).isDisplayed(),
		  "Popular searches section not displayed ");
		  softAssert.assertTrue(driver.findElement(PRODUCT_MATCHES).isDisplayed(),
		  "Product matches section is not displayed");
		  
		  WebElement pm = driver.findElement(PRODUCT_MATCHES);
		  executor.executeScript("arguments[0].click();", pm);
		 
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 30);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(
		 * SEARCHPAGE_PRODUCT_NAME)); //Thread.sleep(8000);
		 * driver.findElement(SEARCHPAGE_PRODUCT_NAME).click();
		 */	
		  Thread.sleep(8000);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.PRODUCT_DETAILS));
		if(driver.findElement(ProductDetailsPageLocators.PRODUCT_DETAILS).getText().contains("Pack"))
		new ProductDetailsPageComponents(driver).verifybundlepages();
		else
		new ProductDetailsPageComponents(driver).validatePage();
		
		softAssert.assertAll();
	
	}
	

	public void validateSearchFunctionality() throws Exception {

		SoftAssert softAssert = new SoftAssert();
	
		action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,50);
		if(!driver.findElement(SEARCH_ICON).isDisplayed())
			throw new Exception("Search option is not present");
		WebElement element = driver.findElement(SEARCH_ICON);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(SEARCH_ICON).sendKeys("tees" + Keys.ENTER);
		
		//autowidget not working on headless
		/*
		 * action.moveToElement(driver.findElement(SEARCH_ICON)).build().perform();
		 * 
		 * 
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(POPULAR_SEARCHES));
		 * softAssert.assertTrue(driver.findElement(POPULAR_SEARCHES).isDisplayed(),
		 * "Popular searches section not displayed ");
		 * softAssert.assertTrue(driver.findElement(PRODUCT_MATCHES).isDisplayed(),
		 * "Product matches section is not displayed");
		 * 
		 * WebElement ps= driver.findElement(POPULAR_SEARCHES);
		 * executor.executeScript("arguments[0].click();", ps);
		 */
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_IMAGE));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_REVIEWS));
	
		
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_TITLE).isDisplayed(), "Search Page title is not displayed on search page");
		//softAssert.assertTrue(driver.findElement(SEARCHPAGE_DESC).isDisplayed(), "Showing results description not displayed on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_ITEMCOUNT).isDisplayed(), "Item count not displayed on search page");
		//softAssert.assertTrue(driver.findElement(SEARCHPAGE_FILTER).isDisplayed(), "Filter section not present on search page");
		
	//check filters
		
		
		
//		List<WebElement> products = driver.findElements(SEARCHPAGE_PRODUCTS);
//		int count = products.size();
//		
//		softAssert.assertEquals(driver.findElement(SEARCHPAGE_ITEMCOUNT).getText(), count + " Items" , "Number of products displayed does not match the count displayed on search page");
		
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_IMAGE).isDisplayed(), "Product image not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_NAME).isDisplayed(), "Product name not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_REVIEWS).isDisplayed(), "Product reviews not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_SWATCHES).isDisplayed(), "Product swatches not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_PRICE).isDisplayed(), "Product price not present on search page");
		softAssert.assertTrue(driver.findElement(SEARCHPAGE_PRODUCT_BADGE).isDisplayed(), "Product badge not present on search page");
		String productname = driver.findElement(By.cssSelector(".product-meta__title")).getText();
		
		WebElement img  = driver.findElement(SEARCHPAGE_PRODUCT_IMAGE);
		executor.executeScript("arguments[0].click();", img );
		//driver.findElement(SEARCHPAGE_PRODUCT_IMAGE).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.ADD_TO_CART_BUTTON));
	//	softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).is(), productname , "correct PDP page not displayed after clicking on image of product on search page" );
		
		driver.navigate().back();
		executor.executeScript("window.scrollBy(0,100)", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_NAME));
		WebElement pname  = driver.findElement(SEARCHPAGE_PRODUCT_NAME);
		executor.executeScript("arguments[0].click();", pname );
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.ADD_TO_CART_BUTTON));
		//softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText(), productname , "correct PDP page not displayed after clicking on name of product on search page" );
		
		driver.navigate().back();
		executor.executeScript("window.scrollBy(0,100)", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_REVIEWS));
		WebElement previews  = driver.findElement(SEARCHPAGE_PRODUCT_REVIEWS);
		executor.executeScript("arguments[0].click();", previews );
		wait.until(ExpectedConditions.visibilityOfElementLocated(ProductDetailsPageLocators.ADD_TO_CART_BUTTON));
		//softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText(), productname , "correct PDP page not displayed after clicking on reviews of product on search page" );
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCHPAGE_PRODUCT_NAME));
		new CollectionPageComponent(driver).MethodToCheckColorSwatchChanging();
		
		softAssert.assertAll();
		
	}



	public void validateaccountdropdown() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		element = driver.findElement(ACCOUNT_ICON);
		action = new Actions(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_ICON));
		if(!driver.findElement(ACCOUNT_ICON).isDisplayed())
			throw new Exception("Account icon is not present");

		// check sign in option
		driver.get(baseUrl);
		wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_ICON));
		element = driver.findElement(ACCOUNT_ICON);
		action.moveToElement(element).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(SIGNIN_OPTION));
		WebElement signIn = driver.findElement(SIGNIN_OPTION);
		action.moveToElement(signIn).perform();
		
		
		driver.findElement(SIGNIN_OPTION).click();
		Thread.sleep(3000);
		currentUrl2 = driver.getCurrentUrl();
		if(!currentUrl2.startsWith("https://tommyjohn.com/account/login"))
			throw new Exception("Wrong page opened after clicked on sin in option");
		Reporter.log("sign in is Displayed :: Clickable");
		
		//check help
		driver.get(baseUrl);
		wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_ICON));
		element = driver.findElement(ACCOUNT_ICON);
		action.moveToElement(element).perform();
		if(!driver.findElement(HELP_ICON_HELP_OPTION).isDisplayed())
			throw new Exception("Help Center option not displayed");
		Reporter.log("Help center option is Displayed ");
		driver.findElement(HELP_ICON_HELP_OPTION).click();
		Thread.sleep(3000);
	//	driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(3000);
		currentUrl2 = driver.getCurrentUrl();
		if(!currentUrl2.equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us"))
			throw new Exception("Wrong page opened after clicked on help option");
		Reporter.log("Help option is Displayed :: Clickable");
		
		// check my account option
	driver.get(baseUrl);
	wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_ICON));
Thread.sleep(2000);
element = driver.findElement(ACCOUNT_ICON);
	action.moveToElement(element).perform();
	if(!driver.findElement(MYACCOUNT_OPTION).isDisplayed())
		throw new Exception("My account option not displayed");
	Reporter.log("My account option is Displayed ");
	driver.findElement(MYACCOUNT_OPTION).click();
	Thread.sleep(3000);
	currentUrl2 = driver.getCurrentUrl();
	if(!currentUrl2.startsWith("https://tommyjohn.com/account"))
		throw new Exception("Wrong page opened after clicked on my account option");
	Reporter.log("My account is Displayed :: Clickable");
	
	
		

		// check chat with us option
//		driver.get(baseUrl);
//		Thread.sleep(3000);
//		element = driver.findElement(HELP_ICON);
//		action.moveToElement(element).perform();
//		if(!driver.findElement(CHAT_WITH_US).isDisplayed())
//			throw new Exception("Chat With Us Option is not present");
//		driver.findElement(CHAT_WITH_US).click();
//		Thread.sleep(6000);
//		// switch to another frame
//		element = driver.findElement(CHAT_WITH_US_IFRAME);
//		driver.switchTo().frame(element);
//		element = driver.findElement(CHAT_WITH_US_POPUP);
//		if(!element.isEnabled())
//			throw new Exception("Chat With Us Popup is not opened");
//				if(element.isEnabled()) {
//					//			if(!driver.findElement(CHAT_WITH_US_POPUP_MINIMIZE).isDisplayed())
//					//				throw new Exception("Chat With Us Popup Minimize button is not present");
//		
//					WebElement element = driver.findElement(CHAT_WITH_US_POPUP_MINIMIZE);
//					JavascriptExecutor executor = (JavascriptExecutor)driver;
//					executor.executeScript("arguments[0].click();", element);
//					
//				}
//				else
//					throw new Exception("Chat With Us Popup is not opened");
//		Reporter.log("Chat With Us Popup is Displayed :: Minimize Button working");
//
//		driver.switchTo().defaultContent();
//		// Validating Call Us option
//		driver.get(baseUrl);
//		element = driver.findElement(HELP_ICON);
//		action.moveToElement(element).perform();
//
//		if(!driver.findElement(CALL_US_OPTION).isDisplayed())
//			throw new Exception("Call US Option is not present");
//
//		text = driver.findElement(CALL_US_OPTION).getText();
//		if(!text.equals("Call Us: 1-800-708-3490"))
//			throw new Exception("Text or Number for Call Us changed");
//		
//		if(!driver.findElement(TEXT_US_OPTION).isDisplayed())
//			throw new Exception("TEXT US Option is not present");
//		
//		text = driver.findElement(TEXT_US_OPTION).getText();
//		if(!text.equals("Text Us: 1-855-976-4933"))
//			throw new Exception("Text or Number for Text Us changed");
//		
//
//		// valaditing Email Us option
//		driver.get(baseUrl);
//		element = driver.findElement(HELP_ICON);
//		action.moveToElement(element).perform();
//		if(!driver.findElement(EMAIL_US).isDisplayed())
//			throw new Exception("Email Us option is not present");
//		driver.findElement(EMAIL_US).click();
//		Thread.sleep(4000);
//		if(!driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/contact-us"))
//			throw new Exception("Wrong page opened after clicked on Emails Us");
//		Reporter.log("Call Us option and Email Us is Displayed :: Clickable");
}


	// check Account/Login icon
	public void validateAccountIcon() throws Exception {
		if(!driver.findElement(ACCOUNT_ICON).isDisplayed())
			throw new Exception("Account Icon is not present");
		driver.findElement(ACCOUNT_ICON).click();
		Thread.sleep(5000);
		if(!driver.getCurrentUrl().startsWith("https://tommyjohn.com/account/login"))
			throw new Exception("Wrong page opened after clicked on Account Icon");
		Reporter.log("Account Icon is Displayed :: Clickable");

	}
	public void validateCartIcon() throws Exception {
		// Check for Cart icon
		
		driver.navigate().to(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(CART_ICON));
		driver.findElement(CART_ICON).click();
		Thread.sleep(2000);
		if(!driver.findElement(CART_HEADING_TEXT).isDisplayed())
			throw new Exception("Mini Cart is not opened");
		driver.get(baseUrl);
		Reporter.log("Cart Icon is Displayed :: Clickable :: Mini Cart is Opened");	
	}

	// validate homepage links
	public void validateFooterLinks() throws Exception {
		Set<String> handles = null;
		List<String> list = null;

		driver.get(baseUrl);
		// scroll the page at bottom to test all the footer links
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// check About link
		text = driver.findElement(ABOUT_LINK).getText();
		if(!text.equals("About"))
			throw new Exception("About footer link text is changed");	
		driver.findElement(ABOUT_LINK).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/about-us")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after About footer link clicked");
		Reporter.log("About link is Displayed :: Clickable");
		Thread.sleep(4000);
		// check Press link
		// scroll the page at bottom to test all the footer links
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(PRESS_LINK).getText();
		if(!text.equals("Press"))
			throw new Exception("Press link text is changed");	
		driver.findElement(PRESS_LINK).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/press")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Press footer link clicked");
		Reporter.log("Press footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check military discount link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(MILITARY_DISCOUNT_LINK).getText();
		if(!text.equals("Military Discount"))
			throw new Exception("Military Discount link text is changed");	
		driver.findElement(MILITARY_DISCOUNT_LINK).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.sheerid.com/")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Military Discount footer link clicked");
		Reporter.log("Military Discount footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Teacher Discount  link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(TEACHER_DISCOUNT_LINK).getText();
		if(!text.equals("Student & Teacher Discount"))
			throw new Exception("Teacher Discount link text is changed");	
		driver.findElement(TEACHER_DISCOUNT_LINK).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://offers.sheerid.com/tommy-john-landing-page/")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Teacher Discount footer link clicked");
		Reporter.log("Teacher Discount footer link is Displayed :: Clickable");
		Thread.sleep(5000);
		
		// check  Healthcare  link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(HEALTHCARE_DISC_LINK).getText();
		if(!text.equals("Healthcare Hero Discount"))
			throw new Exception("Healthcare Hero Discount link text is changed");	
		driver.findElement(HEALTHCARE_DISC_LINK).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://services.sheerid.com/verify/5eb057b19c1d181bd389bf0d/")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Healthcare Hero Discount footer link clicked");
		Reporter.log("Healthcare Hero Discount footer link is Displayed :: Clickable");
		Thread.sleep(5000);
		

		// check  Jobs  link
		// scroll the page at bottom to test all the footer links
	//			((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		text = driver.findElement(JOBS_LINK).getText();
//		if(!text.equals("Jobs"))
//			throw new Exception("Jobs link text is changed");	
//		driver.findElement(JOBS_LINK).click();
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
//		Thread.sleep(5000);
//		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.theresumator.com/apply")) 
//			driver.navigate().back();
//		else
//			throw new Exception("Wrong page opened after Jobs footer link clicked");
//		Reporter.log("Jobs footer link is Displayed :: Clickable");
//		Thread.sleep(5000);

//		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		// check  Privacy Policy  link
//		text = driver.findElement(PRIVACY_POLICY_LINK).getText();
//		if(!text.equals("Privacy Policy"))
//			throw new Exception("Privacy Policy link text is changed");	
//		driver.findElement(PRIVACY_POLICY_LINK).click();
//		Thread.sleep(7000);
//		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/tommy-john-website-privacy-policy")) 
//			driver.navigate().back();
//		else
//			throw new Exception("Wrong page opened after Privacy Policy footer link clicked");
//		Reporter.log("Privacy Policy footer link is Displayed :: Clickable");
//		Thread.sleep(5000);
		// check  Terms & Conditions  link
		// scroll the page at bottom to test all the footer links
//				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		text = driver.findElement(TERMS_AND_CONDITION_LINK).getText();
//		if(!text.equals("Terms & Conditions"))
//			throw new Exception("Terms & Conditions link text is changed");	
//		driver.findElement(TERMS_AND_CONDITION_LINK).click();
//		Thread.sleep(5000);
//		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/tommy-john-terms-and-conditions")) 
//			driver.navigate().back();
//		else
//			throw new Exception("Wrong page opened after Terms & Conditions footer link clicked");
//		Reporter.log("Terms & Conditions footer link is Displayed :: Clickable");
//		Thread.sleep(5000);

		// check  Terms & Conditions  link
		// scroll the page at bottom to test all the footer links
//				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		text = driver.findElement(BLOG_LINK).getText();
//		if(!text.equals("Blog"))
//			throw new Exception("Terms & Conditions link text is changed");	
//		driver.findElement(BLOG_LINK).click();
//		Thread.sleep(5000);
//		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/blogs/news")) 
//			driver.navigate().back();
//		else
//			throw new Exception("Wrong page opened after Blog footer link clicked");
//		Reporter.log("Blog footer link is Displayed :: Clickable");
//		Thread.sleep(5000);

		// check  Help  link
//		text = driver.findElement(HELP_LINK).getText();
//		if(!text.equals("Help"))
//			throw new Exception("Help link text is changed");	
//		driver.findElement(HELP_LINK).click();
//		Thread.sleep(5000);
//		if(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us")) 
//			driver.navigate().back();
		
//		else
//			throw new Exception("Wrong page opened after Help footer link clicked");
//		Reporter.log("Help footer link is Displayed :: Clickable");
//		Thread.sleep(5000);
		
		// check  Contact Us  link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(CONTACT_US_LINK).getText();
		if(!text.equals("Contact & FAQ"))
			throw new Exception("Contact Us link text is changed");	
		driver.findElement(CONTACT_US_LINK).click();
		//Thread.sleep(3000);
		//driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Contact Us footer link clicked");
		Reporter.log("Contact Us footer link is Displayed :: Clickable");
		Thread.sleep(5000);
		
		// check  Retailers  link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(RETAILERS_LINK).getText();
		if(!text.equals("Retailers"))
			throw new Exception("Retailers link text is changed");	
		driver.findElement(RETAILERS_LINK).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/retailers")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Retailers footer link clicked");
		Reporter.log("Retailers footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check  King of Prussia Store  link
		//		text = driver.findElement(KING_OF_PURSSIA_STORE_LINK).getText();
		//		if(!text.equals("King of Prussia Store"))
		//			throw new Exception("King of Prussia Store link text is changed");	
		//		driver.findElement(KING_OF_PURSSIA_STORE_LINK).click();
		//		Thread.sleep(3000);
		//		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/king-of-prussia")) 
		//			driver.navigate().back();
		//		else
		//			throw new Exception("Wrong page opened after King of Prussia Store footer link clicked");
		//		Reporter.log("King of Prussia Store footer link is Displayed :: Clickable");


		// Check  Tommy John Store link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(TOMMY_JOHN_STORES).getText();
		if(!text.equals("Tommy John Stores"))
			throw new Exception("Tommy John Stores link text is changed");	
		driver.findElement(TOMMY_JOHN_STORES).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/store-directory")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Tommy John Stores footer link clicked");
		Reporter.log("Tommy John Stores footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Charlotte Store Store  link
		//		text = driver.findElement(CHARLOTTE_STORE_LINK).getText();
		//		if(!text.equals("Charlotte Store"))
		//			throw new Exception("Charlotte Store link text is changed");	
		//		driver.findElement(CHARLOTTE_STORE_LINK).click();
		//		Thread.sleep(3000);
		//		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/south-park-landing-page")) 
		//			driver.navigate().back();
		//		else
		//			throw new Exception("Wrong page opened after Charlotte Store footer link clicked");
		//		Reporter.log("Charlotte Store footer link is Displayed :: Clickable");

		// check Store Locator  link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(STORE_LOCATOR_LINK).getText();
		if(!text.equals("Store Locator"))
			throw new Exception("Store Locator link text is changed");	
		driver.findElement(STORE_LOCATOR_LINK).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/store-locator")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Store Locator footer link clicked");
		Reporter.log("Store Locator footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Shipping & Returns  link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(SHIPPING_AND_RETURNS_LINK).getText();
		if(!text.equals("Shipping & Return Policy"))
			throw new Exception("Shipping & Returns link text is changed");	
		driver.findElement(SHIPPING_AND_RETURNS_LINK).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us/articles/360000507746-Shipping-Returns")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Shipping & Returns footer link clicked");
		Reporter.log("Shipping & Returns footer link is Displayed :: Clickable");
		Thread.sleep(5000);


		// check 1-800-708-3490  link
//		text = driver.findElement(NUMBER_LINK).getText();
//		if(!text.equals("1-800-708-3490"))
//			throw new Exception("1-800-708-3490 link text is changed");	
//		Reporter.log("1-800-708-3490 footer link is Displayed");
//		Thread.sleep(5000);

		// check Loyalty  link
		// scroll the page at bottom to test all the footer links
//				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		text = driver.findElement(LOYALTY_LINK).getText();
//		if(!text.equals("Loyalty"))
//			throw new Exception("Loyalty link text is changed");	
//		driver.findElement(LOYALTY_LINK).click();
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
//		Thread.sleep(5000);
//		if(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us/sections/360008463991-Loyalty-Rewards")) 
//			driver.navigate().back();
//		else
//			throw new Exception("Wrong page opened after Loyalty footer link clicked");
//		Reporter.log("Loyalty footer link is Displayed :: Clickable");
//		Thread.sleep(5000);

		// check Tommy John Rewards  link
//		text = driver.findElement(TOMMYJOHN_REWARDS_LINK).getText();
//		if(!text.equals("Tommy John Rewards"))
//			throw new Exception("Tommy John Rewards link text is changed");	
//		driver.findElement(TOMMYJOHN_REWARDS_LINK).click();
//		Thread.sleep(3000);
//		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/loyalty")) 
//			driver.navigate().back();
//		else
//			throw new Exception("Wrong page opened after Tommy John Rewards footer link clicked");
//		Reporter.log("Tommy John Rewards footer link is Displayed :: Clickable");
//		Thread.sleep(5000);
		
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// check Gift Cards  link
		text = driver.findElement(GIFT_CARD_LINK).getText();
		if(!text.equals("Gift Cards"))
			throw new Exception("Gift Cards link text is changed");	
		driver.findElement(GIFT_CARD_LINK).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/products/e-gift-card")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Gift Cards footer link clicked");
		Reporter.log("Gift Cards footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Fabric Guide  link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(FABRIC_GUIDE_LINK).getText();
		if(!text.equals("Fabric Guide"))
			throw new Exception("Fabric Guide link text is changed");	
		driver.findElement(FABRIC_GUIDE_LINK).click();
		Thread.sleep(3000);
		if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/fabric-guide")) 
			driver.navigate().back();
		else
			throw new Exception("Wrong page opened after Fabric Guide footer link clicked");
		Reporter.log("Fabric Guide footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Facebook link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(FACEBOOK_LINK).getText();
		if(!text.equals("Facebook"))
			throw new Exception("Facebook link text changed");
		driver.findElement(FACEBOOK_LINK).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(3000);
		/*
		 * handles = driver.getWindowHandles(); list = new ArrayList<String>(handles);
		 * driver.switchTo().window(list.get(1));
		 */		if(!driver.getCurrentUrl().equals("https://www.facebook.com/TommyJohnWear"))
			throw new Exception("Wrong page opened after clicked on Facebook link");
		//driver.switchTo().window(list.get(0));
		 else
			 driver.navigate().back();
		Reporter.log("Facebook footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Twitter link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(TWITTER_LINK).getText();
		if(!text.equals("Twitter"))
			throw new Exception("Twitter link text changed");
		driver.findElement(TWITTER_LINK).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(3000);
		/*
		 * handles = driver.getWindowHandles(); list = new ArrayList<String>(handles);
		 * driver.switchTo().window(list.get(2));
		 */		if(!driver.getCurrentUrl().equals("https://twitter.com/TommyJohn"))
			throw new Exception("Wrong page opened after clicked on Twitter link");
	//	driver.switchTo().window(list.get(0));
		 else
			 driver.navigate().back();
		Reporter.log("Twitter footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Pinterest link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(PINTEREST_LINK).getText();
		if(!text.equals("Pinterest"))
			throw new Exception("Pinterest link text changed");
		driver.findElement(PINTEREST_LINK).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(3000);
		/*
		 * handles = driver.getWindowHandles(); list = new ArrayList<String>(handles);
		 * driver.switchTo().window(list.get(3));
		 */
		if(!driver.getCurrentUrl().equals("https://www.pinterest.com/tommyjohnwear/"))
			throw new Exception("Wrong page opened after clicked on Pinterest link");
		//driver.switchTo().window(list.get(0));
		else
			 driver.navigate().back();
		Reporter.log("Pinterest footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Instagram link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(INSTAGRAM_LINK).getText();
		if(!text.equals("Instagram"))
			throw new Exception("Instagram link text changed");
		driver.findElement(INSTAGRAM_LINK).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(3000);
		/*
		 * handles = driver.getWindowHandles(); list = new ArrayList<String>(handles);
		 * driver.switchTo().window(list.get(4));
		 */
		if(!driver.getCurrentUrl().equals("https://www.instagram.com/tommyjohnwear/"))
			throw new Exception("Wrong page opened after clicked on Instagram link");
		//driver.switchTo().window(list.get(0));
		else
			 driver.navigate().back();
		Reporter.log("Instagram footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check YouTube link
		// scroll the page at bottom to test all the footer links
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		text = driver.findElement(YOUTUBE_LINK).getText();
		if(!text.equals("YouTube"))
			throw new Exception("YouTube link text changed");
		driver.findElement(YOUTUBE_LINK).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".LeaveSiteButtons > a")).click();
		Thread.sleep(3000);
		/*
		 * handles = driver.getWindowHandles(); list = new ArrayList<String>(handles);
		 * driver.switchTo().window(list.get(5));
		 */		if(!driver.getCurrentUrl().equals("https://www.youtube.com/user/tommyjohnwear"))
			throw new Exception("Wrong page opened after clicked on YouTube link");
		//driver.switchTo().window(list.get(0));
		 else
			 driver.navigate().back();
		Reporter.log("YouTube footer link is Displayed :: Clickable");
		Thread.sleep(5000);

		// check Google+ link
		//		text = driver.findElement(GOOGLE_PLUS_LINK).getText();
		//		if(!text.equals("Google+"))
		//			throw new Exception("Google+ link text changed");
		//		driver.findElement(GOOGLE_PLUS_LINK).click();
		//		Thread.sleep(3000);
		//		handles = driver.getWindowHandles();
		//		list = new ArrayList<String>(handles);
		//		driver.switchTo().window(list.get(6));
		//		if(!driver.getCurrentUrl().contains("https://accounts.google.com"))
		//			throw new Exception("Wrong page opened after clicked on Google+ link");
		//		driver.switchTo().window(list.get(0));
		//		Reporter.log("Google+ footer link is Displayed :: Clickable");

	}


	// navigate new arrival in men category
	public void navigateToNewArrivalsInMenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-new-arrivals-1");
	}

	// navigate to all underwear in men category 
	public void navigateToAllUnderwearInMenCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/mens-underwear");
	Thread.sleep(5000);
		
	}
	
	//navigate to Button down shirts
		public void navigateToButtonDownShirtInMenCategory() throws Exception {
			
		driver.get("https://tommyjohn.com/collections/mens-shirts");
		}

	// navigate to Pants and shorts in Men's category
			public void navigateToPantsAndShortsInMensCategory() throws Exception {
			driver.get("https://tommyjohn.com/collections/pants-and-shorts");

			}

	// navigate to Hoodies & Jackets in Men's category
					public void navigateToHoodiesandJacketsInMensCategory() throws Exception {
						driver.get("https://tommyjohn.com/collections/jackets-outerwear-1");

					}

	// navigate to Camisole in womens category
					public void navigateToCamisoleInwomensCategory() throws Exception {
					driver.get("https://tommyjohn.com/collections/womens-camisoles");

					}	
					
					public void navigateToDressesInwomensCategory() throws Exception {
						driver.get("https://tommyjohn.com/collections/womens-dresses");

						}

	// navigate to Tops & Tees in womens category
					public void navigateToTopsAndTeesInwomensCategory() throws Exception {
					driver.get("https://tommyjohn.com/collections/womens-tops-tees");

					}		
	// navigate to Hoodies and Jackets in womens category
					public void navigateToHoodiesandJacketsInwomensCategory() throws Exception {
						driver.get("https://tommyjohn.com/collections/womens-sweatshirts-jackets");

					}		
	// navigate to Pants & Shorts in womens category
					public void navigateToPantsandShortsInwomensCategory() throws Exception {
					driver.get("https://tommyjohn.com/collections/womens-pants-shorts");

					}	

	// navigate to tank in men category
	public void navigateToTankInMenCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/mens-tanks");
	}

	// navigate to dress shirts in men category
	public void navigateToDressShirtsInMenCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/mens-shirts");
	}

	// navigate to all mens collections in men category
	public void navigateToAllMensCollectionsInMenCategory() throws Exception {
driver.get("https://tommyjohn.com/collections/mens");
	}

	// navigate to New Arrivals in Women category
	public void navigateToNewArrivalsInWomenCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/womens-new-arrivals");
	}

	// navigate to All Panties in Women category
	public void navigateToAllPantiesnInWomenCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/womens-underwear");
	}

	// navigate to Tees and Polos in Women category
	public void navigateToTeesAndPolosInWomenCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-tops-tees");
	}

	// navigate to All Womens Collections in Women category
	public void navigateToAllWomensCollectionsInWomenCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/all-womens");
	}

	// navigate to All Mens Packs in Packs category
	public void navigateToAllMensPacksInPacksCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/mens-underwear-packs");
	}

	// navigate to All Womens Packs in Packs category
	public void navigateToAllWomensPacksInPacksCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-underwear-packs");
		Thread.sleep(5000);
	}

	// navigate to single collection cool cotton
	public void navigateToCoolCottonInMensCollection() throws Exception {
		driver.get("https://tommyjohn.com/collections/cool-cotton");
	}

	// navigate to All Clothing in womens categories
	public void navigateToAllClothingInWomensCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-all-clothing");
	}

	// navigate to second skin in men catagory
	public void navigateToSecondSkinInMenCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/mens-second-skin");
	Thread.sleep(5000);

	}
	// navigate to all panties in women catagory
	public void navigateToAllPantiesInWomenCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/womens-underwear");
	Thread.sleep(5000);
	}

	// navigate to cotton basics in shop by collections catagory
	public void navigateToCottonBasicsInMensCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/mens-cotton-basics");

	}

	// navigate to cotton basics in shop by collections catagory
	public void navigateToAllUnderShirtsInMensCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/mens-undershirts");
	}

	// navigate to cotton basics in shop by collections catagory
	public void navigateToAllWomensCollectionInWomensCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/all-womens");
	Thread.sleep(5000);
		
		}

	// navigate to Gift Card category
	public void navigateToGiftCard() throws Exception {
driver.get("https://tommyjohn.com/products/e-gift-card");

	}
	
	//navigate to physical GC
	public void navigateToPhysicalGC()
	{
		driver.get("https://tommyjohn.com/collections/gift-cards/products/gift-card");
	}
	// navigate to Bra category
	public void navigateToBraCategory() throws Exception {
	driver.get("https://tommyjohn.com/collections/bras");	

	}
	
	// navigate to Triangle Bralette category
		public void navigateToTriangleBraletteCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/bra-womens-styles-triangle-bralette");

		}
		
		// navigate to Scoop Bralette category
				public void navigateToScoopBraletteCategory() throws Exception {
				driver.get("https://tommyjohn.com/collections/bra-womens-styles-scoop-bralette");

				}

	// navigate to all panties in women catagory
	public void navigateToMysteryPacksinPacksCategory() throws Exception {

driver.get("https://tommyjohn.com/search?keywords=mystery+packs");
	}
	
	
	
	// navigate to Contact Us Page
		public void navigateToHelpPage() throws Exception {
			
			driver.get(("https://help.tommyjohn.com/hc/en-us"));
//			text = driver.findElement(CONTACT_US_LINK).getText();
//			if(!text.equals("Contact Us"))
//				throw new Exception("Contact Us link text is changed");	
//			driver.findElement(CONTACT_US_LINK).click();
//			Thread.sleep(5000);
//			if(driver.getCurrentUrl().equalsIgnoreCase("https://help.tommyjohn.com/hc/en-us")) 
//			{
//				Thread.sleep(5000);
//				driver.findElement(By.cssSelector("#contact-info>p:nth-child(5)>a")).click();
//				Thread.sleep(5000);
//				if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/contact-us")) 
//					System.out.println("Email link on help page is Displayed :: Clickable");
//				else
//					throw new Exception("Wrong page opened after Email link on help page link clicked");
//			}
//			
//			else {
//			if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/contact-us")) 
//				//driver.navigate().back();
//				System.out.println("Contact Us footer link is Displayed :: Clickable");
//			else
//				throw new Exception("Wrong page opened after Contact Us footer link clicked");
//			Reporter.log("Contact Us footer link is Displayed :: Clickable");
//		}							
		}
	
	// navigate to mix & match in Bra Category
	public void navigateToMixAndMatchInBrasCategory() throws Exception {
		driver.get("https://tommyjohn.com/collections/womens-mix-and-match");
	}

	public void navigateToHisNHerInPacks() throws Exception{
		
		driver.get("https://tommyjohn.com/collections/his-hers-rose-gold-packs");

	}
	
	//navigate to bottom in men category
    public void navigateToBottomInMensCategory(){
		
		driver.get("https://tommyjohn.com/collections/mens-lounge-bottoms");

	}
    //navigate to packs in men category
    public void nevigateToAllMensPacksInPacksCategory() 
    {
	driver.get("https://tommyjohn.com/collections/clothing-packs");

	}

  //navigate to lounge wear packs in women category
    public void navigateToLoungeWearPacksInWomenPacksCategory(){
		
		driver.get("https://tommyjohn.com/collections/womens-lounge-sleep-packs");

	}

  //navigate to Boxer Brief in men category
    public void navigateToBoxerBriefInMenCategory(){
		driver.get("https://tommyjohn.com/collections/underwear-mens-styles-boxer-brief");

	}
    //navigate to Mens underwear quiz page
    public void navigateToMensQuizPage()
    {
    	driver.get("https://tommyjohn.com/pages/underwear-quiz?quizID=MN-UW-QZ");
    	
    }

  //navigate to Womens underwear quiz page
    public void navigateToWomensQuizPage()
    {
    	driver.get("https://tommyjohn.com/pages/underwear-quiz?quizID=WN-UW-QZ");
    }


}
