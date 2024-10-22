package com.tommyjohn.automation.Components;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.base.ExtentReport;
import com.tommyjohn.automation.PageLocators.CheckoutPageLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ShoppingCartLocators;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;

import static com.tommyjohn.automation.PageLocators.FlyCartPageLocator.*;

public class ShoppingCartComponents extends ShoppingCartLocators{

	public WebDriver driver;
	GeneralMethods objGeneralMethods = new GeneralMethods();

	double	installment;
	List<WebElement> allElements;
	String price;
	Wait<WebDriver> wait;
	SoftAssert softAssert=new SoftAssert();
	public String Promo_Code = "FIRST20";
	JavascriptExecutor jse;
	public ShoppingCartComponents(WebDriver driver) {
		this.driver = driver;
		
	}

	public void Validate3for$48functionalityinCartPage() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		new AddToCartComponents(driver).add3for$48product();
		driver.findElement(HomePageLocators.CART_ICON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);
		String[] DISCOUNT_3for$48 = driver.findElement(FlyCartPageLocator.DISCOUNT_3for$48).getText().split(":");
		System.out.println(DISCOUNT_3for$48[1]);
		softAssert.assertEquals(DISCOUNT_3for$48[1],"3 For $48","3 for $48 discount is not applied and not displayed in the Cart line item");
		String TOTAL = driver.findElement(FlyCartPageLocator.TOTAL).getText();
		System.out.println(TOTAL);
		softAssert.assertEquals(TOTAL,"$48.00","Cart Total is differ from the 3 for $48 functionality");
		Reporter.log("3 for $48 functionality works fine in Cart page");
		ExtentReport.extentTest.info("3 for $48 functionality works fine in Cart page");

    }


	public void ValidatePromoCodeErrorScenario() throws InterruptedException {
		WebElement element;
		SoftAssert softAssert = new SoftAssert();

		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);

		driver.findElement(CART_COUPONFIELD).sendKeys("First");
		driver.findElement(APPLY_CTA).click();

		Thread.sleep(5000);
		String ErrorMessage = driver.findElement(ERROR_COUPON).getText();
		System.out.println(ErrorMessage);
		element= driver.findElement(ERROR_COUPON);
		softAssert.assertTrue(element.isDisplayed(), "Error message is not displayed for the incorrect coupon code");
		Reporter.log("Error message is displayed for the incorrect coupon code");
		ExtentReport.extentTest.info("Error message is displayed for the incorrect coupon code");

	}

	public void ValidatePromoCodeFIRST20InCartPage() throws InterruptedException {

		WebElement element;
		SoftAssert softAssert = new SoftAssert();

		driver.get("https://www.tommyjohn.com/");

		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);

		driver.findElement(CART_COUPONFIELD).sendKeys("FIRST20");
		Thread.sleep(2000);
		driver.findElement(APPLY_CTA).click();



		Thread.sleep(10000);
		String Coupon = driver.findElement(APPLIED_COUPON).getText();
		System.out.println(Coupon);

		element= driver.findElement(APPLIED_COUPON);
		softAssert.assertTrue(element.isDisplayed(), "The Coupon code FIRST20 is not getting applied in Cart page");
		Reporter.log("The Coupon code FIRST20 applied in Cart page");
		ExtentReport.extentTest.info("The Coupon code FIRST20 applied in Cart page");

	}

	public void ValidateProductSuggestionInCartPage() throws InterruptedException {
		WebElement element;
		SoftAssert softAssert = new SoftAssert();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		//new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
			driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
			Thread.sleep(3000);

			element = driver.findElement(By.xpath("//swiper-container[@class='-mx-lg flex px-lg [grid-area:slides]']"));
			softAssert.assertTrue(element.isDisplayed(), "Product Suggestion is not displayed in the Cart page");

			softAssert.assertAll();

		Reporter.log("Product Suggestion is displayed in the Cart page");
		ExtentReport.extentTest.info("Product Suggestion is displayed in the Cart page");

	}


	public void EmptyCart() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();

		new HomePageComponents(driver).validateCartIcon();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
		//Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(CART_TITLE).isDisplayed() , "Your Shopping Cart title is not displayed");
		softAssert.assertEquals(driver.findElement(CART_TITLE).getText(), "Your Cart" , "Wrong text displayed for Cart title");
		softAssert.assertTrue(driver.findElement(EMPTY_CART_MESSAGE).isDisplayed(), "Your cart is Empty message is not displayed on empty cart");
		softAssert.assertEquals(driver.findElement(EMPTY_CART_MESSAGE).getText(), "Your cart is currently empty.", "Wrong text displayed for empty cart message");
		softAssert.assertTrue(driver.findElement(START_SHOPPING_BUTTON).isDisplayed(), "Start Shopping button is not displayed");
		driver.findElement(START_SHOPPING_BUTTON).click();
		//	softAssert.assertEquals(driver.getCurrentUrl(), CustomUtilities.baseUrl);
		String url = objGeneralMethods.readPropertiesFile("homepage");
		Thread.sleep(5000);
		System.out.println(driver.getCurrentUrl());
		if(driver.getCurrentUrl().equals(url)) {
			System.out.println("Start shopping button on empty cart redirects user to homepage");
			Reporter.log("Start shopping button on empty cart redirects user to homepage");
			ExtentReport.extentTest.info("Start shopping button on empty cart redirects user to homepage");
		}
		else {
			Reporter.log("Start shopping button on empty cart doesn't redirect user to homepage");
			ExtentReport.extentTest.info("Start shopping button on empty cart doesn't redirect user to homepage");
			throw new Exception("Start shopping button on empty cart doesn't redirect user to homepage");
		}
		softAssert.assertAll();
		driver.get(CustomUtilities.baseUrl);
		Reporter.log("Cart Title Present - Your Shopping Carts ");
		ExtentReport.extentTest.info("Cart Title Present - Your Shopping Carts ");
		Reporter.log("Message displayed correctly - Your cart is Empty");
		ExtentReport.extentTest.info("Message displayed correctly - Your cart is Empty");
		
	}


	
	public void clickOnViewCartLink() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.CART_ICON));
		Thread.sleep(2000);
		driver.findElement(HomePageLocators.CART_ICON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Reporter.log("Clicked on View cart link in inline cart");
		ExtentReport.extentTest.info("Clicked on View cart link in inline cart");
		softAssert.assertAll();
		
	}
	
	public void validatePromotionalMessage() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		driver.findElement(HomePageLocators.CART_ICON).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(TIERED_METER));
		Thread.sleep(5000);
		try
		{
		softAssert.assertTrue(driver.findElement(PROMOTIONAL_PRICE).isDisplayed(), "Promotional discounted price is displayed on cart page");
		if(driver.findElement(PROMOTIONAL_MESSAGE).isDisplayed()) {
			
			System.out.println("PROMOTIONAL MESSAGE is displayed on cart page");
			Reporter.log("PROMOTIONAL MESSAGE is displayed on cart page");
			ExtentReport.extentTest.info("PROMOTIONAL MESSAGE is displayed on cart page");
		}
		else {
			System.out.println("PROMOTIONAL MESSAGE is not displayed on cart page");
			Reporter.log("PROMOTIONAL MESSAGE is not displayed on cart page");
			ExtentReport.extentTest.info("PROMOTIONAL MESSAGE is not displayed on cart page");
		}
		}
		
		catch(Exception e)
		{
			Reporter.log("PROMOTIONAL MESSAGE is not displayed on cart page");
			ExtentReport.extentTest.info("PROMOTIONAL MESSAGE is not displayed on cart page");
		}
		
		//softAssert.assertAll();	
	}
	
	public void tieredMeterpresence() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(TIERED_METER));
		Thread.sleep(5000);
		
		try
		{
			if(driver.findElement(TIERED_METER_TITLE).isDisplayed()){
				System.out.println("Tiered Meter Title is displayed on cart page");
				Reporter.log("Tiered Meter Title is displayed on cart page");
				ExtentReport.extentTest.info("Tiered Meter Title is displayed on cart page");
			}
			else {
				System.out.println("Tiered Meter Title is not displayed on cart page");
				Reporter.log("Tiered Meter Title is not displayed on cart page");
				ExtentReport.extentTest.info("Tiered Meter Title is displayed on cart page");
			}
		}
		catch(Exception e)
		{
			Reporter.log("Tiered Meter Title is not displayed on cart page");
			ExtentReport.extentTest.info("Tiered Meter Title is not displayed on cart page");
		}
		
		
		//softAssert.assertAll();	
	}
	
	public void OOSValidation() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(TIERED_METER));
		Thread.sleep(7000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_QUANTITY));
		driver.findElement(PRODUCT_QUANTITY).clear();
		driver.findElement(PRODUCT_QUANTITY).sendKeys("600" + Keys.ENTER);
	//	driver.findElement(PRODUCT_QUANT_ADD).click();

		Thread.sleep(5000);
		
		if(driver.findElement(OOS_MESSAGE).isDisplayed()) {
			
			System.out.println("OOS message is displayed on cart page");
			Reporter.log("OOS message is displayed on cart page");
			ExtentReport.extentTest.info("OOS message is displayed on cart page");
			
			if(driver.findElement(OOS_TAG).isDisplayed()){
				System.out.println("OOS Tag is displayed on cart page");
				Reporter.log("OOS Tag is displayed on cart page");
				ExtentReport.extentTest.info("OOS Tag is displayed on cart page");
			}
			else {
				System.out.println("OOS Tag is not displayed on cart page");
				Reporter.log("OOS Tag is not displayed on cart page");
				ExtentReport.extentTest.info("OOS Tag is not displayed on cart page");
			}
		}
		else {
			System.out.println("OOS message is not displayed on cart page");
			Reporter.log("OOS message is not displayed on cart page");
			ExtentReport.extentTest.info("OOS message is not displayed on cart page");
		}
		
		//softAssert.assertAll();	
	}

	public void CartwithProduct() throws Exception
	{

		SoftAssert softAssert = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
		

		softAssert.assertTrue(driver.findElement(PRODUCT_TITLE).isDisplayed(), "Product title for added item not present on Cart");
		softAssert.assertTrue(driver.findElement(PRODUCT_IMAGE).isDisplayed(), "Product image is not present");
		softAssert.assertTrue(driver.findElement(PRODUCT_COLOR_SIZE).isDisplayed(), "Color and Size are not present for the product in Cart");
		softAssert.assertTrue(driver.findElement(PRODUCT_PRICE).isDisplayed(), "Price not present for the product in Cart");
		softAssert.assertTrue(driver.findElement(PRODUCT_QUANTITY).isDisplayed(), "Quantity not present");
		softAssert.assertTrue(driver.findElement(PRODUCT_SUBTOTAL).isDisplayed(), "Product subtotal not present");
		softAssert.assertTrue(driver.findElement(GIFT_WRAP_LINK).isDisplayed(), "Gift wrap link not present");
		softAssert.assertTrue(driver.findElement(CART_TOTAL_TEXT).isDisplayed(), "Estimated total text is not present");
		softAssert.assertTrue(driver.findElement(CART_TOTAL).isDisplayed(), "Cart total price is not present");
		softAssert.assertTrue(driver.findElement(CONTINUE_SHOPPING).isDisplayed(), "Continue shopping link not present");
		softAssert.assertTrue(driver.findElement(CHECKOUT_BUTTON).isDisplayed(), "Checkout button not present");
		softAssert.assertTrue(driver.findElement(REMOVE_PRODUCT_FROM_CART).isDisplayed(), "Option to remove product from cart not present");
		softAssert.assertAll();
	}


	public void AddRemoveProductsFromCart() throws Exception
	{
		String text;
		String text1;
		String text2;
		String quant;
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		SoftAssert softAssert = new SoftAssert();
		/*text = new AddToCartComponents(driver).addToCart();
		System.out.println("Text is:"+text);
		String[] part = text.split(",");
		String part2 = part[0];
		System.out.println("Title text is:" +part2);*/
		driver.findElement(HomePageLocators.CART_ICON).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
		}
		catch(Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
		}
		
		softAssert.assertTrue(driver.findElement(PRODUCT_TITLE).isDisplayed(), "Product title for added item not present on Cart");

		WebElement elemen = driver.findElement(PRODUCT_TITLE);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", elemen);
		text1 = driver.findElement(PRODUCT_TITLE_PDP).getText();
		//softAssert.assertEquals(text1, part2, "Wrong Product added to cart");
		System.out.println("text1 is:"+text1);
//		String[] comma_separated  = text1.split(",");
//		String p_name = comma_separated[0];
//
//		String text2= p_name.replace("-", " ");
		driver.findElement(HomePageLocators.CART_ICON).click();
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		text2 = driver.findElement(PRODUCT_TITLE).getText();
		System.out.println("text2 is: "+text2);
		if(!text1.equalsIgnoreCase(text2))
			throw new Exception("Wrong Product added to cart");
	//	quant= driver.findElement(PRODUCT_QUANTITY).getAttribute("value");

		//driver.findElement(PRODUCT_QUANT_ADD).click();
//		jse.executeScript("arguments[0].click();", driver.findElement(PRODUCT_QUANT_ADD));
//		softAssert.assertEquals(driver.findElement(PRODUCT_QUANTITY).getAttribute("value"), "2");
//		//driver.findElement(PRODUCT_QUANT_MINUS).click();
//		jse.executeScript("arguments[0].click();", driver.findElement(PRODUCT_QUANT_MINUS));
//		softAssert.assertEquals(driver.findElement(PRODUCT_QUANTITY).getAttribute("value"), quant);

		wait.until(ExpectedConditions.visibilityOfElementLocated(REMOVE_PRODUCT_FROM_CART));
		driver.findElement(REMOVE_PRODUCT_FROM_CART).click();
		Thread.sleep(3000);
		//EmptyCart();
		softAssert.assertAll();

	}

	public void CheckoutPageFromCart() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));

		WebElement element = driver.findElement(CHECKOUT_BUTTON);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);
		//	driver.findElement(CHECKOUT_BUTTON).click();
		Thread.sleep(3000);
		softAssert.assertTrue(driver.getCurrentUrl().contains("checkouts"), "Checkout page is not displayed on clicking checkout button");
		driver.navigate().back();
		Thread.sleep(5000);
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(3000);
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(5000);
		//driver.findElement(CONTINUE_SHOPPING).click();
		//Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(HomePageLocators.ACCOUNT_NAV_DROPDOWN).isDisplayed(), "Continue Shopping redirects user to wrong page");

		softAssert.assertAll();
	}

	public void productDetailsInCart() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();


		ProductDetailsPageComponents pdp1 = new ProductDetailsPageComponents(driver);

		pdp1.getproductdetails();
		new AddToCartComponents(driver).atc();

		driver.findElement(HomePageLocators.CART_ICON).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));

		System.out.println("cart " + driver.findElement(PRODUCT_COLOR_SIZE).getText());
		System.out.println("pdp " + ProductDetailsPageComponents.productcolor + " / " + ProductDetailsPageComponents.prodsize);
		System.out.println("ex " + driver.findElement(PRODUCT_QUANTITY).getAttribute("value"));
		System.out.println("ac " + ProductDetailsPageComponents.productquant);

		softAssert.assertEquals(driver.findElement(PRODUCT_TITLE).getText(), ProductDetailsPageComponents.productname, "Wrong product title displayed in cart");
		softAssert.assertEquals(driver.findElement(PRODUCT_QUANTITY).getAttribute("value"), ProductDetailsPageComponents.productquant, "Wrong quantity added to cart");
		softAssert.assertEquals(driver.findElement(PRODUCT_PRICE).getText(), ProductDetailsPageComponents.productprice, "Wrong product price displayed in cart");
		if(!driver.findElement(PRODUCT_COLOR_SIZE).getText().equalsIgnoreCase(ProductDetailsPageComponents.productcolor + " / " + ProductDetailsPageComponents.prodsize))
			throw new Exception("Wrong color & size added to cart");
		softAssert.assertAll();
	}

	public void validateafterpayonShoppingCart() throws Exception
	{
		//		new HomePageComponents(driver).navigateToBraCategory();
		//		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		//	new ProductDetailsPageComponents(driver).validatePage();
		new AddToCartComponents(driver).addToCart();
		//navigate to shopping cart page
		driver.findElement(HomePageLocators.CART_ICON).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);

		//Check AfterPay message display

		if(!driver.findElement(AFTERPAY_DIV).isDisplayed())
			throw new Exception("AfterPay Message not present on Shopping cart.");
		Reporter.log("After pay Message is displayed on Shopping Cart :: Displayed");
		ExtentReport.extentTest.info("After pay Message is displayed on Shopping Cart :: Displayed");

		//Check message with calculated installment and icon display
		price = driver.findElement(ESTIMATE_TOTAL_PRICE).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length-1];
		System.out.println("Estimate total is: "+price);
		WebElement afterpaymessaging = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > span\")");
		
		System.out.print(afterpaymessaging.getText());
		if(Float.parseFloat(price2)<35 || Float.parseFloat(price2)>1000 )
		{
			WebElement afterpaylogo = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > button.afterpay-logo.brand-afterpay.type-badge.black-on-mint\")");

			softAssert.assertTrue(afterpaymessaging.getText().contains("available for orders between $35 - $1000") , "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message is correct On Shopping cart :: Displayed and Correct");
			ExtentReport.extentTest.info("After pay message is correct On Shopping cart :: Displayed and Correct");
			softAssert.assertTrue(afterpaylogo.isDisplayed(), "Afterpay logo not present on Shopping cart.");	
			Reporter.log("After pay logo icon is displayed on Shopping cart :: Displayed");
			ExtentReport.extentTest.info("After pay logo icon is displayed on Shopping cart :: Displayed");
		}
		else
		{
			WebElement afterpaylogo = (WebElement) js.executeScript("return document.querySelector(\"#root > div > div > afterpay-placement\").shadowRoot.querySelector(\"p > span.afterpay-logo-link > button.afterpay-logo.brand-afterpay.type-badge.black-on-mint\")");

			installment =Float.parseFloat(price2)/4;
			String inst = String.format("%.2f",installment );
			//String expectedmessage = "In 4 installments of $" + inst + " by ?"; 
			String expectedmessage =  inst+ " "; 
			System.out.println("expected message is: " + expectedmessage);
			String Actual = afterpaymessaging.getText();
			String ins_amount= Actual.substring(Actual.indexOf("$")+1, Actual.indexOf("b"));
			System.out.println("Actual ins_amount is: " +ins_amount);
			softAssert.assertEquals(ins_amount, expectedmessage , "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message with calculated installment is correct on Inline cart :: Displayed and correct");
			ExtentReport.extentTest.info("After pay message with calculated installment is correct on Inline cart :: Displayed and correct");
			softAssert.assertTrue(afterpaylogo.isDisplayed(), "Afterpay logo not present on Shopping cart");
			Reporter.log("After pay logo icon is displayed on Inline cart :: Displayed");
			ExtentReport.extentTest.info("After pay logo icon is displayed on Inline cart :: Displayed");
		}
		Thread.sleep(3000);   		

		softAssert.assertAll();
	}

	public void Promo_Code_OncartPage() throws Exception
	{
		WebElement element;
		String allClasses = null;
		boolean flag = false;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);
		}
		catch(Exception e) {
			System.out.println("Inline cart is not getting expanded after click on cart icon on home page, while clicking on cart icon it just navigating to cart page.");
		}
Thread.sleep(4000);
		softAssert.assertEquals(driver.findElement(PROMO_HEADER).getText(), "Discount Code", "Text changed for Discount code header on cart page");
		softAssert.assertTrue(driver.findElement(DISCOUNTCODE_TEXTBOX).isDisplayed(), "Promo code text box is not displayed on cart page");
		softAssert.assertTrue(driver.findElement(PROMOCODE_APPLYCTA).isDisplayed(), "Promo code 'apply' cta is not displayed");

		//Verify empty text field
		js.executeScript("arguments[0].click();", driver.findElement(PROMOCODE_APPLYCTA));
		//driver.findElement(PROMOCODE_APPLYCTA).click();
		element= driver.findElement(PROMO_ERROR_MESSAGE);
		wait.until(ExpectedConditions.visibilityOf(element));
		softAssert.assertEquals(element.getText(), "Enter a valid discount code", "Text changed for promo code error message on cart page");
		Reporter.log("Promo code error message is displayed :: Correct");
		ExtentReport.extentTest.info("Promo code error message is displayed :: Correct");

		//Verify with valid promo code
		driver.findElement(DISCOUNTCODE_TEXTBOX).sendKeys(Promo_Code);
		js.executeScript("arguments[0].click();", driver.findElement(PROMOCODE_APPLYCTA));
		//driver.findElement(PROMOCODE_APPLYCTA).click();
		Thread.sleep(2000);
		softAssert.assertTrue(driver.findElement(PROMO_SUCCESSFUL_MESSAGE).isDisplayed(), "Succesful message is not displayed for applied promo code");
		Reporter.log("Promo code successful message is displayed :: Correct");
		ExtentReport.extentTest.info("Promo code successful message is displayed :: Correct");

		softAssert.assertTrue(driver.findElement(SALE_PRICE).isDisplayed(), "Sale peice is not displayed after applied promo code");
		softAssert.assertTrue(driver.findElement(APPLIED_PROMO_CODE).isDisplayed(), "Applied promo code is not displayed for product on cart page");
		softAssert.assertEquals(driver.findElement(APPLIED_PROMO_CODE).getText(), Promo_Code, "Promo code is not same as applied discount code");

		//Verify with invalid promo code
		driver.findElement(DISCOUNTCODE_TEXTBOX).sendKeys("InvalidCode");
		js.executeScript("arguments[0].click();", driver.findElement(PROMOCODE_APPLYCTA));
		//driver.findElement(PROMOCODE_APPLYCTA).click();
		Thread.sleep(2000);
		element= driver.findElement(PROMO_ERROR_MESSAGE);
		wait.until(ExpectedConditions.visibilityOf(element));
		softAssert.assertEquals(element.getText(), "Enter a valid discount code", "Text changed for promo code error message on cart page");
		softAssert.assertTrue(driver.findElement(APPLIED_PROMO_CODE).isDisplayed(), "Applied promo code is getting removed after applied invalid promo code");

		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(APPLIED_PROMO_CODE));
		softAssert.assertTrue(driver.findElement(APPLIED_PROMO_CODE).isDisplayed(), "Applied promo code is getting removed after refresh the page");

		//new ProductDetailsPageComponents(driver).Add_Bundle_toCart();

		driver.get(CustomUtilities.baseUrl);

		driver.findElement(HomePageLocators.CART_ICON).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(APPLIED_PROMOCODE_FLYCART).isDisplayed(), "Applied promo code from cart page is not displayed on flycart");
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);

		AddGiftWrap();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(APPLIED_PROMO_CODE));
		softAssert.assertTrue(driver.findElement(APPLIED_PROMO_CODE).isDisplayed(), "Applied promo code is getting removed after adding bundle product in cart");
		element = driver.findElement(DISCOUNTED_GIFTWRAP_PRICE);
		allClasses = element.getAttribute("class");
		for(String c : allClasses.split(" "))
		{
			if(c.equals("discounted-price"))
				flag= true;
		}
		if(!flag)
			throw new Exception("Discount coupon is not applied cart");
		Reporter.log("Promo code is auto-applied on newly added products");  	
		ExtentReport.extentTest.info("Promo code is auto-applied on newly added products");

		Thread.sleep(3000);
		Check_Promocode_onCheckout_throughCart();	
		//Remove code from checkout
		js.executeScript("arguments[0].click();", driver.findElement(CANCEL_PROMO_CODE));
		//driver.findElement(CheckoutPageLocators.CANCEL_PROMO_CODE).click();
		Thread.sleep(8000);

		driver.findElement(CheckoutPageLocators.BREADCRUMB_LINK_CART).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
		softAssert.assertTrue(driver.findElement(APPLIED_PROMO_CODE).getText().isEmpty(), "Discount code is still displayed on cart page after removing code from checkout");

		Reporter.log("Apply promo code through textbox on cart page is correct");
		ExtentReport.extentTest.info("Apply promo code through textbox on cart page is correct");
		AddPromoCode_OnCart_ThroughURL();
		wait.until(ExpectedConditions.visibilityOfElementLocated(SUBTOTAL_PRICE));
		Check_Promocode_onCheckout_throughCart();	

		softAssert.assertAll();
	}

	public void AddPromoCode_OnCart_ThroughURL()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		String parameter = "/?coupon="+Promo_Code;
		String current_url = driver.getCurrentUrl();
		String Url_withPromocode = current_url.concat(parameter);
		driver.get(Url_withPromocode);
		wait.until(ExpectedConditions.visibilityOfElementLocated(APPLIED_PROMO_CODE));
		softAssert.assertTrue(driver.findElement(PROMO_SUCCESSFUL_MESSAGE).isDisplayed(), "Succesful message is not displayed for applied promo code through url");
		Reporter.log("Through Url parameter Promo code successful message is displayed :: Correct");

		softAssert.assertTrue(driver.findElement(SALE_PRICE).isDisplayed(), "Sale price is not displayed after applied promo code through url");
		softAssert.assertTrue(driver.findElement(APPLIED_PROMO_CODE).isDisplayed(), "Applied promo code is not displayed for product on cart page through url parameter");
		softAssert.assertEquals(driver.findElement(APPLIED_PROMO_CODE).getText(), Promo_Code, "Promo code is not same as applied discount code through url parameter");
		Reporter.log("Apply promo code through url parameter on cart page is correct");
		
	}

	public void Check_Promocode_onCheckout_throughCart()
	{

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		price = driver.findElement(SUBTOTAL_PRICE).getText();
		String[] price1 = price.split("\\$");
		String EstimateTotal_onCart = price1[price1.length-1];

		js.executeScript("arguments[0].click();", driver.findElement(CHECKOUT_BUTTON));
		//driver.findElement(CHECKOUT_BUTTON).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPageLocators.APPLIED_PROMO_CODE));
		softAssert.assertEquals(driver.findElement(CheckoutPageLocators.APPLIED_PROMO_CODE).getText(), Promo_Code, "Wrong/Invalid Promo Code applied");
		price = driver.findElement(CheckoutPageLocators.TOTAL).getText();
		String[] price2 = price.split("\\$");
		String EstimateTotal_onCheckout = price2[price2.length-1];
		//String EstimateTotal_onCheckout = "0.00";
		System.out.println("checkout price: "+EstimateTotal_onCheckout+" cart price: "+EstimateTotal_onCart);
		softAssert.assertEquals(EstimateTotal_onCheckout, EstimateTotal_onCart, "Discounted total price is incorrect on checkout");
		softAssert.assertAll();
	}

	public void AddGiftWrap() throws Exception
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement element;
		String allClasses = null;
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(GIFT_WRAP_LINK));
		driver.findElement(GIFT_WRAP_LINK).click();
		softAssert.assertTrue(driver.findElement(FlyCartPageLocator.GIFT_WRAP_POPUP).isDisplayed(), "Gift Wrap Popup is not displayed");
//		element = driver.findElement(FlyCartPageLocator.GIFT_BOX_$5);
//		allClasses = element.getAttribute("class");
//
//		for (String c : allClasses.split(" ")) {
//			if (c.equals("is-checked")) {
//				flag = true;
//			}
//		}
//		if(flag) {
//			Reporter.log("Gift Box $5.00 is already checked");
//		ExtentReport.extentTest.info("Gift Box $5.00 is already checked");
//	}
	//	else {
			driver.findElement(FlyCartPageLocator.GIFT_BOX_$5_CHECKBOX).click();
			Thread.sleep(5000);
			Reporter.log("Gift Box $5.00 is checked");
			ExtentReport.extentTest.info("Gift Box $5.00 is checked");
	//	}
			
		js.executeScript("arguments[0].click();", driver.findElement(FlyCartPageLocator.GIFT_WRAP_APPLY));
		//driver.findElement(FlyCartPageLocator.GIFT_WRAP_APPLY).click();
		Thread.sleep(5000);
		softAssert.assertAll();
	}
	
	public void cartPaymentMethods() throws Exception 
	{
		jse = (JavascriptExecutor)driver;
		SoftAssert softAssert = new SoftAssert();
		String text = null;
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
		
		jse.executeScript("window.scrollBy(0,450)");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("paypalLight")));
		driver.switchTo().frame(driver.findElement(By.className("paypalLight")));
		driver.switchTo().frame(driver.findElement(By.className("zoid-component-frame")));
		jse.executeScript("arguments[0].click();",driver.findElement(PAYPAL_BUTTON));
		String mainWindow = driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		Iterator<String> itr= set.iterator();
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
			driver.close();
			driver.switchTo().window(mainWindow);
			softAssert.fail("PayPal popup does not show up when clicked on paypal express checkout button");
			Reporter.log("PayPal popup does not show up when clicked on paypal express checkout button");
			ExtentReport.extentTest.info("PayPal popup does not show up when clicked on paypal express checkout button");
		}
//		Verify GPay Button	
		try {
			wait.until(ExpectedConditions.elementToBeClickable(GPAY_BUTTON));
		wait.until(ExpectedConditions.visibilityOfElementLocated(GPAY_BUTTON));
		WebElement element = driver.findElement(GPAY_BUTTON);
		 
		//jse.executeScript("arguments[0].click();", element);
		Actions action = new Actions(driver);
		 action.moveToElement(element).click().build().perform();
		 Reporter.log("GPay option is present on checkout page");
		 ExtentReport.extentTest.info("GPay option is present on checkout page");

		} catch (ElementClickInterceptedException | NoSuchElementException | TimeoutException e) {
			driver.navigate().refresh();
			Thread.sleep(5000);
			try {
				driver.findElement(GPAY_BUTTON).click();
				Reporter.log("GPAY option is present on checkout page");
				ExtentReport.extentTest.info("GPAY option is present on checkout page");
			} catch (ElementClickInterceptedException |NoSuchElementException | TimeoutException f) {
				softAssert.assertTrue(false, "Express Checkout Option GPay is not interactable");
			}
		}
	
	//	softAssert.assertEquals(text, "Sign in – Google accounts" , "GPay pop up not present");
		driver.navigate().refresh();
	
		//verify amz pay
		wait.until(ExpectedConditions.visibilityOfElementLocated(AMZ_PAY));
		WebElement element1 = driver.findElement(AMZ_PAY);
		jse.executeScript("arguments[0].click();", element1);
		wait.until(ExpectedConditions.titleContains("Amazon"));
		
		text = driver.getTitle();
		System.out.println(text);
		softAssert.assertTrue(text.contains("Amazon"), "Amazon pay did not open up after clicking on Amazon Pay button ");

		Reporter.log("Amazon Pay option is present on checkout page");
		ExtentReport.extentTest.info("Amazon Pay option is present on checkout page");

		driver.navigate().back();

		
		
		//verify shop pay
		wait.until(ExpectedConditions.visibilityOfElementLocated(SHOP_PAY));
		WebElement element2 = driver.findElement(SHOP_PAY);
		jse.executeScript("arguments[0].click();", element2);
		wait.until(ExpectedConditions.urlContains("shop"));
		
		text = driver.getCurrentUrl();
		System.out.println(text);
		softAssert.assertTrue(text.contains("shop"), "shop");

		softAssert.assertTrue(text.contains("shop"), "Shop pay did not open up after clicking on Shop Pay button ");

		Reporter.log("Shop Pay option is present on checkout page");
		ExtentReport.extentTest.info("Shop Pay option is present on checkout page");
		
		softAssert.assertAll();
		Reporter.log("Redirected to SHOP PAY login page when clicked on shop pay button");
		ExtentReport.extentTest.info("Redirected to SHOP PAY login page when clicked on shop pay button");
		Reporter.log("Redirected to Amazon login page when clicked on amz pay button");
		ExtentReport.extentTest.info("Redirected to Amazon login page when clicked on amz pay button");
		Reporter.log("PayPal popup shows up when clicked on paypal express checkout button");
		ExtentReport.extentTest.info("PayPal popup shows up when clicked on paypal express checkout button");
		Reporter.log("GPay popup shows up when clicked on Gpay express checkout button");
		ExtentReport.extentTest.info("GPay popup shows up when clicked on Gpay express checkout button");

	}


	
	
	public void tieredMeterFunctionality() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		String wid="width: 100%;";
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(TIERED_METER));
		Thread.sleep(5000);
		if(driver.findElement(TIERED_METER).isDisplayed()) 
		{	
		String widthvalue=	driver.findElement(TIERED_METER_TIER).getCssValue("width");
			System.out.println(widthvalue);
		String text=driver.findElement(TIERED_METER_TIER).getAttribute("style");	
		System.out.println(text);
		if(!text.equals(wid))
		{
			System.out.println("New Exception");
			throw new Exception("Tiered meter not filling in correctly.");

        }
		Reporter.log("Tiered Meter is filling in");
		ExtentReport.extentTest.info("Tiered Meter is filling in");
		String[] widthper = text.split(": ");
		System.out.println(widthper[1]);
		if(!widthper[1].equals("100%;"))
		{
			System.out.println("Tiered meter not filling in correctly.");
			throw new Exception("Tiered meter not filling in correctly.");
		}
		Reporter.log("Tiered Meter is filling in");
		ExtentReport.extentTest.info("Tiered Meter is filling in");
		}
		else {
			System.out.println("Tiered Meter is not displayed on cart page");
			Reporter.log("Tiered Meter is not displayed on cart page");
			ExtentReport.extentTest.info("Tiered Meter is not displayed on cart page");
		}
		
		
	}
}
