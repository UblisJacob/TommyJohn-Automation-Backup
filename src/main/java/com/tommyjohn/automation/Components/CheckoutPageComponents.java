package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.interactions.Actions;

import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.CheckoutPageLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class CheckoutPageComponents extends CheckoutPageLocators {

	public WebDriver driver;
	public JavascriptExecutor jse;
	Wait<WebDriver> wait;
	List<WebElement> allElements;
	WebElement element;
	double installment;
	String price;
	String text;
	SoftAssert softAssert;
	//String Promo_Code = "TJHUSTLE";

	public CheckoutPageComponents(WebDriver driver) {
		this.driver = driver;
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
	}

	public void expresscheckout() throws Exception {
		jse = (JavascriptExecutor)driver;
		softAssert = new SoftAssert();
		String text = null;

//		Adds a product to cart
		new AddToCartComponents(driver).addToCart();
		
		wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		
		
		wait.until(ExpectedConditions.elementToBeClickable(FlyCartPageLocator.CHECKOUT_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(FlyCartPageLocator.CHECKOUT_BUTTON));
		

//		Wait for Checkout page
		wait.until(ExpectedConditions.visibilityOfElementLocated(EXPRESS_CHECKOUT_TEXT));
		
//		Verify Paypal Button
		driver.switchTo().frame(driver.findElement(By.className("paypalLight")));

		String mainWindow = driver.getWindowHandle();

		jse.executeScript("arguments[0].click();", driver.findElement(PAYPAL_BUTTON));
		Reporter.log("PayPal option is present on checkout page");
		
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			// Compare whether the main windows ID is not equal to child window ID. If not equal,
			// we will switch.
			if (!mainWindow.equals(childWindow))
				driver.switchTo().window(childWindow);
//			text = driver.switchTo().window(childWindow).getTitle();
			text = driver.getTitle();
		}
		
		if (text.contains("PayPal")) {
			Thread.sleep(1000);
			driver.close();
			driver.switchTo().window(mainWindow);
		}else {
			Thread.sleep(1000);
			driver.close();
			//driver.switchTo().window(mainWindow);
			softAssert.fail("PayPal popup does not show up when clicked on paypal express checkout button");
			Reporter.log("PayPal popup does not show up when clicked on paypal express checkout button");
		}
		
//		Verify GPay Button
		try {
			wait.until(ExpectedConditions.elementToBeClickable(GPAY_BUTTON));
			jse.executeScript("arguments[0].click();", driver.findElement(GPAY_BUTTON));
			
			Reporter.log("GPay option is present on checkout page");

		} catch (ElementClickInterceptedException | NoSuchElementException | TimeoutException e) {
			driver.navigate().refresh();
			Thread.sleep(5000);
			try {
				jse.executeScript("arguments[0].click();", driver.findElement(GPAY_BUTTON));
				Reporter.log("GPAY option is present on checkout page");
			} catch (ElementClickInterceptedException |NoSuchElementException | TimeoutException f) {
				softAssert.assertTrue(false, "Express Checkout Option GPay is not interactable");
			}
		}
		
//		Gpay Validation
//		we can try clicking on some other button if it gives exception we can conclude because of popup unable to do so, hence popup is present
		
		driver.navigate().refresh();
		
//		Verify Amazon Pay
		wait.until(ExpectedConditions.visibilityOfElementLocated(AMZ_PAY));
		jse.executeScript("arguments[0].click();", driver.findElement(AMZ_PAY));
	
		wait.until(ExpectedConditions.titleContains("Amazon"));
		
//		Thread.sleep(3900);
		text = driver.getTitle();
		softAssert.assertTrue(text.contains("Amazon"), "Amazon pay did not open up after clicking on Amazon Pay button ");

		Reporter.log("Amazon Pay option is present on checkout page");

		driver.navigate().back();
		
//		Verify Shop pay
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHOP_PAY));
		jse.executeScript("arguments[0].click();", driver.findElement(SHOP_PAY));
		
		wait.until(ExpectedConditions.urlContains("shop"));
		
//		Thread.sleep(3900);
		text = driver.getCurrentUrl();
		softAssert.assertTrue(text.contains("shop"), "Shop pay did not open up after clicking on Shop Pay button ");

		Reporter.log("Shop Pay option is present on checkout page");
		
		softAssert.assertAll();
		
		Reporter.log("Redirected to SHOP PAY login page when clicked on shop pay button");
		Reporter.log("Redirected to Amazon login page when clicked on amz pay button");
		Reporter.log("PayPal popup shows up when clicked on paypal express checkout button");
		Reporter.log("GPay popup shows up when clicked on Gpay express checkout button");

	}

	public void CheckBreadcrumbs() {
		softAssert.assertTrue(driver.findElement(BREADCRUMB_LINK_CART).isDisplayed(),
				"Cart link is not displayed on breadcrumb");
		softAssert.assertTrue(driver.findElement(BREADCRUMB_LINK_INFORMATION).isDisplayed(),
				"Information link is not displayed on breadcrumb");
		softAssert.assertTrue(driver.findElement(BREADCRUMB_LINK_SHIPPING).isDisplayed(),
				"Shipping link is not displayed on breadcrumb");
		softAssert.assertTrue(driver.findElement(BREADCRUMB_LINK_PAYMENT).isDisplayed(),
				"Payment link is not displayed on breadcrumb");
		softAssert.assertAll();
		Reporter.log("Validated Breadcrumbs on checkout pages");
	}

	public void guestcheckoutflow() throws Exception {
		softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
//		Add Product to Cart
		new AddToCartComponents(driver).addToCart();
		
		wait.until(ExpectedConditions.elementToBeClickable(HomePageLocators.CART_ICON));
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		
		
		wait.until(ExpectedConditions.elementToBeClickable(FlyCartPageLocator.CHECKOUT_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(FlyCartPageLocator.CHECKOUT_BUTTON));
		
		
//		On Information Page
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_EMAIL));
		
		driver.findElement(CHECKOUT_EMAIL).clear();
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));

		Reporter.log("Email for Guest User entered successfully");
		text = driver.findElement(SHIPPINGADD_TEXT).getText();
		softAssert.assertTrue(text.contains("Shipping address"),"Shipping address text not present");
		
		entershippingdetails();
		
		Reporter.log("Shipping Address Details entered");

//		Click on Continue to Shipping button
		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_TO_SHIPPING_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(CONTINUE_TO_SHIPPING_BUTTON));
		
		Reporter.log("User is navigated to select shipping method");
		
//		On Shipping Page
		Thread.sleep(4000);
		wait.until(ExpectedConditions.textToBe(SHIPPING_TEXT, "Shipping method"));
		text = driver.findElement(SHIPPING_TEXT).getText();
		softAssert.assertTrue(text.contains("Shipping method"),"Shipping method text not present");
		
		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_TO_PAYMENT_BUTTON));
		jse.executeScript("arguments[0].click();", driver.findElement(CONTINUE_TO_PAYMENT_BUTTON));
		Reporter.log("Selected shipping method and user is navigated to payment page");
		
//		On Payment Page
		wait.until(ExpectedConditions.elementToBeClickable(COMPLETE_ORDER_BUTTON));
		element = driver.findElement(COMPLETE_ORDER_BUTTON);
		softAssert.assertTrue(element.isDisplayed(), "Pay Now/Complete Order Button is not present");
		Reporter.log("Complete Order/Pay Now button is enabled");

		softAssert.assertAll();
	}

	public void registeredusercheckoutflow() throws Exception {
		softAssert = new SoftAssert();
		String password = CustomUtilities.properties.getProperty("password");
		new AddToCartComponents(driver).addToCart();

		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		driver.findElement(FlyCartComponents.CHECKOUT_BUTTON).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));

		Reporter.log("Checkout page displayed");

		driver.findElement(LOGIN_LINK).click();
		Reporter.log("User naviagted to login page");
		Thread.sleep(3000);

		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT)
				.sendKeys(CustomUtilities.properties.getProperty("loginemail"));
		driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(password);
		driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();
		Reporter.log("User logged in successfully");

		Thread.sleep(6000);

		entershippingdetails();

		Reporter.log("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);

		Reporter.log("User is navigated to select shipping method");

		Thread.sleep(8000);

		softAssert.assertEquals(driver.findElement(SHIPPING_TEXT).getText(), "Shipping method");
		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);

		// driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).click();
		Reporter.log("Selected shipping method and user is navigated to payment page");
		Thread.sleep(5000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_ORDER_BUTTON));
		softAssert.assertTrue(driver.findElement(COMPLETE_ORDER_BUTTON).isDisplayed(),
				"Complete Order button is not displayed");

		Reporter.log("Complete Order button is displayed");
		softAssert.assertAll();
	}

	public void informationpage() throws Exception {
		softAssert = new SoftAssert();
		jse = (JavascriptExecutor)driver;
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		try {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
//		}
//		catch(Exception e)
//		{
//			wait.until(ExpectedConditions.visibilityOfElementLocated(ShoppingCartComponents.CART_TITLE));
//		}

		
		jse.executeScript("arguments[0].click();", driver.findElement(FlyCartComponents.CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(EXPRESS_CHECKOUT_TEXT));
		CheckBreadcrumbs();
		softAssert.assertTrue(driver.findElement(EXPRESS_CHECKOUT_TEXT).isDisplayed(),
				"Express checkout text is not displayed");
		softAssert.assertTrue(driver.findElement(CONTACTINFO_TEXT).isDisplayed(),
				"Contact information text is not displayed");
		softAssert.assertTrue(driver.findElement(ALREADYHAVEANACCOUNT_TEXT).isDisplayed(),
				"Already have an account? text is not displayed");
		softAssert.assertTrue(driver.findElement(LOGIN_LINK).isDisplayed(), "Login link is not displayed");
		softAssert.assertTrue(driver.findElement(SHIPPINGADD_TEXT).isDisplayed(),
				"Shipping address text is not displayed");
		// softAssert.assertTrue(driver.findElement(REMEMBERME_CHCKBOX).isDisplayed(),
		// "Save this information for next time checkbox is not displayed");
		softAssert.assertTrue(driver.findElement(RETURN_TO_CART_LINK).isDisplayed(),
				"Return to cart link is not displayed");
		softAssert.assertTrue(driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).isDisplayed(),
				"Continue to shipping method button is not displayed");
		sidebar();
		CheckPromoCodeApplication("FIRST20");
		softAssert.assertAll();

	}

	public void shippingpage() throws Exception {
		softAssert = new SoftAssert();
		new AddToCartComponents(driver).addToCart();
		
		driver.findElement(HomePageLocators.CART_ICON).click();

		WebElement checkout_btn = driver.findElement(FlyCartComponents.CHECKOUT_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkout_btn);

		Reporter.log("Checkout page displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTINFO_TEXT));
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);
		Reporter.log("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_EMAIL));
		
		CheckBreadcrumbs();

		softAssert.assertTrue(driver.findElement(CONTACT_EMAIL).isDisplayed(), "Conatct email is not present");
		softAssert.assertTrue(driver.findElement(SHIP_ADDRESS).isDisplayed(), "Shipping address is not present");
		softAssert.assertTrue(driver.findElement(CHANGE_LINK_CONTACT).isDisplayed(), "Contact change link is not present to chnage ");
		softAssert.assertTrue(driver.findElement(CHANGE_LINK_SHIP_TO).isDisplayed(), "Ship To change link is not present to chnage ");
		softAssert.assertTrue(driver.findElement(SHIPPING_TEXT).isDisplayed(), "Shipping text is not present");
		
		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_TO_PAYMENT_BUTTON));
		softAssert.assertTrue(driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).isDisplayed(), "Continue to paymnet method button is not present");
		softAssert.assertTrue(driver.findElement(RETURN_LINK).isDisplayed(), "return link is not present");
		
		List<WebElement> shipmethods = driver.findElements(By.className("radio__label__primary"));
		for (WebElement method : shipmethods) {
			wait.until(ExpectedConditions.textToBePresentInElement(method, "Arrive"));
			System.out.println(method.getText());
		}
		
		sidebar();
		
		CheckPromoCodeApplication("FIRST20");
		
		softAssert.assertAll();
	}

	public void paymentpage() throws Exception {
		jse = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();
		
		new AddToCartComponents(driver).addToCart();
		
		
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));

		
		jse.executeScript("arguments[0].click();", driver.findElement(FlyCartComponents.CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		Thread.sleep(5000);
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));

		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);

		Reporter.log("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse.executeScript("arguments[0].click();", element);
		Reporter.log("User is navigated to payment page");
		Thread.sleep(10000);

		sidebar();
		softAssert.assertTrue(driver.findElement(CONTACT_EMAIL).isDisplayed(), "Conatct email is not present");
		softAssert.assertTrue(driver.findElement(SHIP_ADDRESS).isDisplayed(), "Shipping address is not present");
		softAssert.assertTrue(driver.findElement(METHOD).isDisplayed(), "Shipping method is not present");
		// WebDriverWait waits = new WebDriverWait(driver, 60);
		// try {
		// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("_hjRemoteVarsFrame")));
		// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bx-close-xsvg")));
		// driver.findElement(By.cssSelector(".bx-close-xsvg")).click();
		// driver.switchTo().defaultContent();
		// }
		// catch (ElementNotVisibleException e)
		// {
		// System.out.println("Overlay not displayed");
		// }
		//
		// wait.until(ExpectedConditions.visibilityOfElementLocated(CREDITCARD_OPTION));
		CheckBreadcrumbs();
		softAssert.assertTrue(driver.findElement(CHANGE_LINK_CONTACT).isDisplayed(),
				"Contact Change Link is not present");
		softAssert.assertTrue(driver.findElement(CHANGE_LINK_SHIP_TO).isDisplayed(),
				"Ship to Change Link is not present");
		softAssert.assertTrue(driver.findElement(CHANGE_LINK_METHOD).isDisplayed(),
				"Method Change Link is not present");
		softAssert.assertTrue(driver.findElement(CREDITCARD_OPTION).isDisplayed(), "Credit card option is not present");
		softAssert.assertTrue(driver.findElement(PAYPAL_OPTION).isDisplayed(), "Paypal option is not present");
		softAssert.assertTrue(driver.findElement(BILLING_TEXT).isDisplayed(), "Billing text is not present");
		softAssert.assertTrue(driver.findElement(HOWHEARD_TITLE).isDisplayed(), "How heard title is not present");
		softAssert.assertTrue(driver.findElement(HOWHEARD_SUBTITLE).isDisplayed(), "How heard subtitle is not present");
		softAssert.assertTrue(driver.findElement(HOWHEARD_DROPDOWN).isDisplayed(), "How heard dropdown is not present");
		softAssert.assertTrue(driver.findElement(COMPLETE_ORDER_BUTTON).isEnabled(), "Complete order button is not enabled");
		
		CheckPromoCodeApplication("FIRST20");
		Select dphowheard = new Select(driver.findElement(HOWHEARD_DROPDOWN));
		dphowheard.selectByValue("Online Search");
		Reporter.log("How heard values are displayed:: Clickable");
		softAssert.assertAll();

	}

	public void entershippingdetails() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(FIRSTNAME_TEXTBOX));
		
		driver.findElement(FIRSTNAME_TEXTBOX).clear();
		driver.findElement(FIRSTNAME_TEXTBOX).sendKeys("Vijeta");
		driver.findElement(LASTNAME_TEXTBOX).clear();
		driver.findElement(LASTNAME_TEXTBOX).sendKeys("Juneja");
		driver.findElement(ADDRESS1_TEXTBOX).clear();
		driver.findElement(ADDRESS1_TEXTBOX).sendKeys("100 Broadway Test");
		Thread.sleep(2000);
		driver.findElement(ADDRESS2_TEXTBOX).clear();
		driver.findElement(ADDRESS2_TEXTBOX).sendKeys("8th floor");
		driver.findElement(CITY_TEXTBOX).clear();
		driver.findElement(CITY_TEXTBOX).sendKeys("New York");
		WebElement countrydp = driver.findElement(COUNTRY_DROPDOWN);
		Select country = new Select(countrydp);
		country.selectByValue("United States");

		WebElement statedp = driver.findElement(STATE_DROPDOWN);
		Select state = new Select(statedp);
		state.selectByValue("NY");

		driver.findElement(PINCODE_TEXTBOX).clear();
		driver.findElement(PINCODE_TEXTBOX).sendKeys("10005");
		Thread.sleep(2000);
		driver.findElement(PHONE_TEXTBOX).clear();
		driver.findElement(PHONE_TEXTBOX).sendKeys("8889996667");
	}

	public void sidebar() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(driver.findElement(ORDER_SUMMARY_SECTION).isDisplayed(), "ORDER SUMMARY SECTION is not displayed");
		softAssert.assertTrue(driver.findElement(DISCOUNT_CODE_TEXTBOX).isDisplayed(), "DISCOUNT CODE TEXTBOX is not displayed");
		softAssert.assertTrue(driver.findElement(APPLY_DISCOUNT_BUTTON).isDisplayed(), "APPLY DISCOUNT BUTTON is not displayed");
		softAssert.assertTrue(driver.findElement(SUBTOTAL).isDisplayed(), "Subtotal is not displayed");
		softAssert.assertTrue(driver.findElement(SHIPPING).isDisplayed(), "Shipping is not displayed");
		softAssert.assertTrue(driver.findElement(TOTAL).isDisplayed(), "total price is not displayed");
		softAssert.assertAll();

	}

	public void CheckPromoCodeApplication(String promocode) throws Exception {
		//Promo_Code = "FIRST20";
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(promocode);
		driver.findElement(APPLY_DISCOUNT_BUTTON).click();
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(APPLIED_PROMO_CODE, 0));
		softAssert.assertEquals(driver.findElement(APPLIED_PROMO_CODE).getText(), promocode, "Wrong/Invalid Promo Code applied");
		driver.findElement(CANCEL_PROMO_CODE).click();
//		driver.findElement(CANCEL_PROMO_CODE).click();
		Thread.sleep(4000);
		softAssert.assertAll();
		Reporter.log("Validated Promo Code application functionality");

	}

	public void AfterpayonCheckout() throws Exception {
		softAssert = new SoftAssert();
	
		guestcheckoutflow();
		
		// Check AfterPay message is display
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AFTERPAY_OPTION));
		if (!driver.findElement(AFTERPAY_OPTION).isDisplayed())
			throw new Exception("After pay option is not displayed on checkout page.");
		Reporter.log("After pay Message is displayed on Checkout page :: Displayed");

		// Check message with calculated installment and icon display
		price = driver.findElement(TOTAL).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length - 1];
		System.out.println("Estimate total is: " + price);
		if (Float.parseFloat(price2) < 35 || Float.parseFloat(price2) > 1000) {
			try {
				boolean present = driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT).isDisplayed();
			}
			catch(Exception e)
			{
				Reporter.log("afterpay logo is not displaayed on checkout if amount is not afterpay criteria : correct");
			}
		} else {
			softAssert.assertTrue(driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT).isDisplayed(), "Afterpay logo is not correct");
			driver.findElement(AFTERPAY_INSTALLMENTS).click();
			driver.findElement(COMPLETE_ORDER_BUTTON).click();
			Thread.sleep(4000);
			softAssert.assertTrue(driver.getCurrentUrl().contains("afterpay") , "user is not navigated to afterpay portal for payment" );
		}

		softAssert.assertAll();
	}

	public void CheckAfterPayForAddressOtherThanUS() throws Exception {
		// add product to cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();

		// Click on Checkout button in inline cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.CHECKOUT_BUTTON));
		driver.findElement(FlyCartPageLocator.CHECKOUT_BUTTON).click();
		Thread.sleep(5000);

		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("checkoutemail"));
		entershippingAddressOtherThanUS();
		Thread.sleep(3000);

		// navigate to checkout page
		driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		Thread.sleep(5000);
		System.out.println("payment page");
		Thread.sleep(10000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_TO_PAYMENT_BUTTON));
		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		if (!element.isDisplayed())
			throw new Exception("Continue to payment button is not displayed.");
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		Thread.sleep(10000);

		// Check after pay for address other than US
		try {
			System.out.println("Verifying for AfterPay Message .......");
			if (!driver.findElement(AFTERPAY_OPTION).getAttribute("style").equalsIgnoreCase("display: none;"))
				throw new Exception("AfterPay Message is displayed on Checkout page for address other than US.");
			Reporter.log("AfterPay Message is displayed on Checkout page for address other than US");
		} catch (Exception e) {
			System.out.println("After Pay Message is not displayed on checkout page for address other than US.");
			Reporter.log("After Pay Message is not displayed on Checkout page for address other than US.");
		}
	}

	public void entershippingAddressOtherThanUS() {
		driver.findElement(ADDRESS1_TEXTBOX).sendKeys("123 Test Adress");
		driver.findElement(ADDRESS2_TEXTBOX).sendKeys("Suite1");
		driver.findElement(CITY_TEXTBOX).sendKeys("Perth");
		WebElement countrydp = driver.findElement(COUNTRY_DROPDOWN);
		Select country = new Select(countrydp);
		country.selectByValue("Australia");
		WebElement statedp = driver.findElement(STATE_DROPDOWN);
		Select state = new Select(statedp);
		state.selectByValue("WA");
		driver.findElement(PINCODE_TEXTBOX).sendKeys("6000");
		driver.findElement(PHONE_TEXTBOX).sendKeys("8889996667");
	}

	public void AfterPayMessageByModifyingPromoCode() throws Exception {
		AfterpayonCheckout();
		jse = (JavascriptExecutor) driver;
		String Promo_Code = "TJHUSTLE";
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(Promo_Code);
		driver.findElement(APPLY_DISCOUNT_BUTTON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(APPLIED_PROMO_CODE));
		softAssert.assertEquals(driver.findElement(APPLIED_PROMO_CODE).getText(), Promo_Code,
				"Wrong/Invalid Promo Code applied");
		WebElement cancel_code = driver.findElement(CANCEL_PROMO_CODE);
		CheckAfterPayInstallment();
		jse.executeScript("window.scrollTo(0, 0)");
		jse.executeScript("arguments[0].click();", cancel_code);
		
		Thread.sleep(5000);
		driver.findElement(BREADCRUMB_LINK_SHIPPING).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(CONTINUE_TO_PAYMENT_BUTTON));
		Thread.sleep(3000);
		driver.findElement(DISCOUNT_CODE_TEXTBOX).sendKeys(Promo_Code);
		driver.findElement(APPLY_DISCOUNT_BUTTON).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(APPLIED_PROMO_CODE));

		driver.findElement(CONTINUE_TO_PAYMENT_BUTTON).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(APPLIED_PROMO_CODE));
		cancel_code = driver.findElement(CANCEL_PROMO_CODE);
		jse.executeScript("arguments[0].click();", cancel_code);
		Thread.sleep(8000);
		CheckAfterPayInstallment();
		Reporter.log("AfterPay installment amount displayed :: Correct with promo code change");
		softAssert.assertAll();
	}

	public void CheckAfterPayInstallment() throws Exception {
		jse = (JavascriptExecutor) driver;
		

		// Check message with calculated installment and icon display
		price = driver.findElement(TOTAL).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length - 1];
		System.out.println("Estimate total is: " + price);
		if (Float.parseFloat(price2) < 35 || Float.parseFloat(price2) > 1000) {
			try {
				boolean present = driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT).isDisplayed();
			}
			catch(Exception e)
			{
				Reporter.log("afterpay logo is not displaayed on checkout if amount is not afterpay criteria : correct");
			}
		} else {
			softAssert.assertTrue(driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT).isDisplayed(), "Afterpay logo is not correct");
			driver.findElement(AFTERPAY_INSTALLMENTS).click();
			driver.findElement(COMPLETE_ORDER_BUTTON).click();
			Thread.sleep(4000);
			softAssert.assertTrue(driver.getCurrentUrl().contains("afterpay") , "user is not navigated to afterpay portal for payment" );
		}

		softAssert.assertAll();
	}

	public void navigatetocheckoutpage() throws Exception {
		new CollectionPageComponent(driver).AddproductToCartByQuickShop();
		driver.findElement(HomePageLocators.CART_ICON).click();
		driver.findElement(FlyCartComponents.CHECKOUT_BUTTON).click();
	}

	public void completeorder() throws Exception {
		
		jse = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		new AddToCartComponents(driver).addToCart();	
		
		jse.executeScript("arguments[0].click();", driver.findElement(HomePageLocators.CART_ICON));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));

		jse.executeScript("arguments[0].click();", driver.findElement(FlyCartComponents.CHECKOUT_BUTTON));
		Reporter.log("Checkout page displayed");
		Thread.sleep(5000);
		driver.findElement(CHECKOUT_EMAIL).sendKeys(CustomUtilities.properties.getProperty("orderplacementemail"));

		softAssert.assertEquals(driver.findElement(SHIPPINGADD_TEXT).getText(), "Shipping address");
		entershippingdetails();
		Reporter.log("Shipping Address Details entered");

		// driver.findElement(CONTINUE_TO_SHIPPING_BUTTON).click();
		WebElement Continue_shipping = driver.findElement(CONTINUE_TO_SHIPPING_BUTTON);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continue_shipping);

		Reporter.log("User is navigated to select shipping method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHIPPING_RADIO_BUTTON));
		jse.executeScript("window.scrollBy(0,100)");
		Thread.sleep(3000);
		WebElement element = driver.findElement(CONTINUE_TO_PAYMENT_BUTTON);
		jse.executeScript("arguments[0].click();", element);
		Reporter.log("User is navigated to payment page");
		Thread.sleep(10000);
		CheckPromoCodeApplication("TEST_ORDER_TECH");
		wait.until(ExpectedConditions.elementToBeClickable(COMPLETE_ORDER_BUTTON));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Select dphowheard = new Select(driver.findElement(HOWHEARD_DROPDOWN));
		dphowheard.selectByValue("Online Search");
		Reporter.log("How heard values are displayed:: Clickable");
		
		jse.executeScript("arguments[0].click();",  driver.findElement(COMPLETE_ORDER_BUTTON));
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(10000);
		System.out.println(driver.getCurrentUrl());
		wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_NUMBER));
		System.out.println(driver.getCurrentUrl());
		Reporter.log("Order Number - " + driver.findElement(ORDER_NUMBER).getText());
		
		softAssert.assertAll();
		
	}

}
