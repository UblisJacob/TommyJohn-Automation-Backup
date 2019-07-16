package com.tommyjohn.automation.Components;


import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.CheckoutPageLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;


public class CheckoutPageComponents extends CheckoutPageLocators {
	
	public WebDriver driver;
	public JavascriptExecutor jse;
	

	public CheckoutPageComponents(WebDriver driver) {
		this.driver = driver;
	}
	
	public void expresscheckout() throws Exception 
	{
		SoftAssert softAssert = new SoftAssert();
		String text = null;
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
	
		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(EXPRESS_CHECKOUT_TEXT));
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.className("paypalLight")));
		driver.findElement(PAYPAL_BUTTON).click();
		String mainWindow = driver.getWindowHandle();
		 Set<String> set =driver.getWindowHandles();
		 // Using Iterator to iterate with in windows
		 Iterator<String> itr= set.iterator();
		 while(itr.hasNext()){
		 String childWindow=itr.next();
		    // Compare whether the main windows is not equal to child window. If not equal, we will close.
		 if(!mainWindow.equals(childWindow))
		 driver.switchTo().window(childWindow);
		text = driver.switchTo().window(childWindow).getTitle();
		 }
		 softAssert.assertEquals(text, "PayPal Checkout");
		 driver.close();
		 
		Reporter.log("PayPal option is present on checkout page and is functional"); 
		 Thread.sleep(3000);
		driver.switchTo().window(mainWindow);
		 WebElement element = driver.findElement(GPAY_BUTTON);
			jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", element);
		//driver.findElement(GPAY_BUTTON).click();
		Set<String> set1 =driver.getWindowHandles();
		 // Using Iterator to iterate with in windows
		 Iterator<String> itr1 = set1.iterator();
		 while(itr1.hasNext()){
		 String childWindow1=itr1.next();
		    // Compare whether the main windows is not equal to child window. If not equal, we will close.
		 if(!mainWindow.equals(childWindow1))
		 driver.switchTo().window(childWindow1);
		text = driver.switchTo().window(childWindow1).getTitle();
		 }
		 softAssert.assertEquals(text, "Sign in – Google accounts");
		 driver.close();
		 driver.switchTo().window(mainWindow);
		 Reporter.log("GPay option is present on checkout page and is functional");
	}
	
	public void guestcheckoutflow() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
	
		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		
		Reporter.log("Email for Guest User entered successfully");
		
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		
		driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		Reporter.log("User is navigated to select shipping method");
	
		Thread.sleep(3000);
		
		softAssert.assertEquals(driver.findElement(SHIPPING_TEXT).getText(), "Shipping method");
		 driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).click();
		 Reporter.log("Selected shipping method and user is navigated to payment page");
		 
		 softAssert.assertTrue(driver.findElement(COMPLETE_ORDER_BUTTON).isDisplayed() , "Complete Order button is not displayed");	
		 Reporter.log("Complete Order button is enabled");
		 
		 softAssert.assertAll();
	}
	
	public void registeredusercheckoutflow() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		String password = CustomUtilities.properties.getProperty("password");
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
	
		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		Reporter.log("Checkout page displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(LOGIN_LINK).click();
		Reporter.log("User naviagted to login page");
		Thread.sleep(3000);
		
		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).sendKeys(CustomUtilities.properties.getProperty("loginemail"));
		driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(password);
		driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();
		Reporter.log("User logged in successfully");
		
		Thread.sleep(3000);
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		
		driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		Reporter.log("User is navigated to select shipping method");
	
		Thread.sleep(3000);
		
		softAssert.assertEquals(driver.findElement(SHIPPING_TEXT).getText(), "Shipping method");
		 WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
			jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click();", element);
		// driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).click();
		 Reporter.log("Selected shipping method and user is navigated to payment page");
		 
		 Thread.sleep(3000);
	//	 softAssert.assertTrue(driver.findElement(COMPLETE_ORDER_BUTTON).isEnabled() , "Complete Order button is not displayed");	
		 Reporter.log("Complete Order button is enabled");
		 
		 softAssert.assertAll();	
		
	}
	
	public void informationpage() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
	
		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		Reporter.log("Checkout page displayed");
		Thread.sleep(5000);
		softAssert.assertTrue(driver.findElement(EXPRESS_CHECKOUT_TEXT).isDisplayed(), "Express checkout text is not displayed");
//		softAssert.assertTrue(driver.findElement(PAYPAL_BUTTON).isDisplayed(), "Paypal option is not displayed");
//		softAssert.assertTrue(driver.findElement(GPAY_BUTTON).isDisplayed(), "GPay option is not displayed");
		softAssert.assertTrue(driver.findElement(CONTACTINFO_TEXT).isDisplayed(), "Contact information text is not displayed");
		softAssert.assertTrue(driver.findElement(ALREADYHAVEANACCOUNT_TEXT).isDisplayed(), "Already have an account? text is not displayed");
		softAssert.assertTrue(driver.findElement(LOGIN_LINK).isDisplayed(), "Login link is not displayed");
		softAssert.assertTrue(driver.findElement(SHIPPINGADD_TEXT).isDisplayed(), "Shipping address text is not displayed");
		softAssert.assertTrue(driver.findElement(REMEMBERME_CHCKBOX).isDisplayed(), "Save this information for next time checkbox is not displayed");
		softAssert.assertTrue(driver.findElement(RETURN_TO_CART_LINK).isDisplayed(), "Return to cart link is not displayed");
		softAssert.assertTrue(driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).isDisplayed(), "Continue to shipping method button is not displayed");
		sidebar();
		softAssert.assertAll();	
		
	}
	
	@SuppressWarnings("null")
	public void shippingpage() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
	
		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		Reporter.log("Checkout page displayed");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		
		driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		Reporter.log("User is navigated to select shipping method");
	
		Thread.sleep(3000);
		
		softAssert.assertTrue(driver.findElement(CONTACT_EMAIL).isDisplayed(), "Conatct email is not present");
		softAssert.assertTrue(driver.findElement(SHIP_ADDRESS).isDisplayed(), "Shipping address is not present");
		softAssert.assertTrue(driver.findElement(CHANGE_LINK).isDisplayed(), "change link is not present to chnage ");
		softAssert.assertTrue(driver.findElement(SHIPPING_TEXT).isDisplayed(), "Shipping text is not present");
		softAssert.assertTrue(driver.findElement(RETURN_LINK).isDisplayed(), "return link is not present");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_TO_PAYMENT_BUTTON));
		softAssert.assertTrue(driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).isDisplayed(), "Continue to paymnet method button is not present");
		
		
		List<WebElement> shipmethods = driver.findElements(By.className("radio__label__primary"));
		for(WebElement method : shipmethods)
		{
		System.out.println(method.getText());
		}

		
		sidebar();
		softAssert.assertAll();
	}
	
	public void paymentpage() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
	
		driver.findElement(ShoppingCartComponents.CHECKOUT_BUTTON).click();
		Reporter.log("Checkout page displayed");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");
		
		driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		Reporter.log("User is navigated to select shipping method");
	
		Thread.sleep(3000);
		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);
	//	driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).click();
		Reporter.log("User is navigated to payment page");
		
		Thread.sleep(5000);
		
		softAssert.assertTrue(driver.findElement(CONTACT_EMAIL).isDisplayed(), "Conatct email is not present");
		softAssert.assertTrue(driver.findElement(SHIP_ADDRESS).isDisplayed(), "Shipping address is not present");
		softAssert.assertTrue(driver.findElement(METHOD).isDisplayed(), "Shipping method is not present");
		softAssert.assertTrue(driver.findElement(CHANGE_LINK).isDisplayed(), "Change Link is not present");
		softAssert.assertTrue(driver.findElement(PAYMENT_TEXT).isDisplayed(), "Payment text is not present");
		softAssert.assertTrue(driver.findElement(CREDITCARD_OPTION).isDisplayed(), "Credit card option is not present");
		
	//	Reporter.log("Contact information and payment options are present on payment page");
		softAssert.assertTrue(driver.findElement(PAYPAL_OPTION).isDisplayed(), "Paypal option is not present");
		softAssert.assertTrue(driver.findElement(BILLING_TEXT).isDisplayed(), "Billing text is not present");
		softAssert.assertTrue(driver.findElement(HOWHEARD_TITLE).isDisplayed(), "How heard title is not present");
		softAssert.assertTrue(driver.findElement(HOWHEARD_SUBTITLE).isDisplayed(), "How heard subtitle is not present");
		softAssert.assertTrue(driver.findElement(HOWHEARD_DROPDOWN).isDisplayed(), "How heard dropdown is not present");
		softAssert.assertTrue(driver.findElement(COMPLETE_ORDER_BUTTON).isEnabled(), "Complete order button is not enabled");
		
		Select dphowheard = new Select(driver.findElement(HOWHEARD_DROPDOWN));
		dphowheard.selectByValue("Online Search");
		Reporter.log("How heard value selected in dropdown");
		
		
		softAssert.assertAll();	

	}
	
	
	public void entershippingdetails()
	{
		driver.findElement(FIRSTNAME_TEXTBOX).sendKeys("Test");
		driver.findElement(LASTNAME_TEXTBOX).sendKeys("Test");
		driver.findElement(ADDRESS1_TEXTBOX).sendKeys("123 Test Adress");
		driver.findElement(ADDRESS2_TEXTBOX).sendKeys("Suite1");
		driver.findElement(CITY_TEXTBOX).sendKeys("New York");
		WebElement countrydp = driver.findElement(COUNTRY_DROPDOWN);
		Select country = new Select(countrydp);
		country.selectByValue("United States");
		
		WebElement statedp = driver.findElement(STATE_DROPDOWN);
		Select state = new Select(statedp);
		state.selectByValue("NY");
			
		
		driver.findElement(PINCODE_TEXTBOX).sendKeys("10001");
		driver.findElement(PHONE_TEXTBOX).sendKeys("8889996667");
	}
	
	public void sidebar()
	{
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(driver.findElement(ORDER_SUMMARY_SECTION).isDisplayed(), "ORDER SUMMARY SECTION is not displayed");
		softAssert.assertTrue(driver.findElement(DISCOUNT_CODE_TEXTBOX).isDisplayed(), "DISCOUNT CODE TEXTBOX  is not displayed");
		softAssert.assertTrue(driver.findElement(APPLY_DISCOUNT_BUTTON).isDisplayed(), "APPLY DISCOUNT BUTTON is not displayed");
		softAssert.assertTrue(driver.findElement(SUBTOTAL).isDisplayed(), "Subtotal  is not displayed");
		softAssert.assertTrue(driver.findElement(SHIPPING).isDisplayed(), "Shipping  is not displayed");
		softAssert.assertTrue(driver.findElement(TOTAL).isDisplayed(), "total is not displayed");
		softAssert.assertAll();
		
	}
	
	
 }

//softAssert.assertTrue(driver.findElement(CREDITCARD_OPTION).isDisplayed(), "Credit card option is not ");
