package com.tommyjohn.automation.Components;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ShoppingCartLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ShoppingCartComponents extends ShoppingCartLocators{

	public WebDriver driver;
	
	
	double	installment;
	List<WebElement> allElements;
	String price;
	SoftAssert softAssert=new SoftAssert();

	public ShoppingCartComponents(WebDriver driver) {
		this.driver = driver;
	}
	
	public void EmptyCart() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		
		new HomePageComponents(driver).validateCartIcon();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(CART_TITLE).isDisplayed() , "Your Shopping Cart title is not displayed");
		softAssert.assertEquals(driver.findElement(CART_TITLE).getText(), "Your Shopping Cart" , "Wrong text displayed for Cart title");
		softAssert.assertTrue(driver.findElement(EMPTY_CART_MESSAGE).isDisplayed(), "Your cart is Empty message is not displayed on empty cart");
		softAssert.assertEquals(driver.findElement(EMPTY_CART_MESSAGE).getText(), "Your cart is empty", "Wrong text displayed for empty cart message");
		softAssert.assertTrue(driver.findElement(START_SHOPPING_BUTTON).isDisplayed(), "Start Shopping button is not displayed");
		driver.findElement(START_SHOPPING_BUTTON).click();
	//	softAssert.assertEquals(driver.getCurrentUrl(), CustomUtilities.baseUrl);
		
		softAssert.assertAll();
		driver.get(CustomUtilities.baseUrl);
		Reporter.log("Cart Title Present - Your Shopping Cart ");
		Reporter.log("Message displayed correctly - Your cart is Empty");
		Reporter.log("Start shopping button on empty cart redirects user to homepage");
	}
	
	public void CartwithProduct() throws Exception
	{
		
		SoftAssert softAssert = new SoftAssert();
		
		
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
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
		String quant;
		
		SoftAssert softAssert = new SoftAssert();
		
		
		text = new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
		
		softAssert.assertTrue(driver.findElement(PRODUCT_TITLE).isDisplayed(), "Product title for added item not present on Cart");
		text1 = driver.findElement(PRODUCT_TITLE).getText();
		softAssert.assertEquals(text1, text, "Wrong Product added to cart");
		quant= driver.findElement(PRODUCT_QUANTITY).getAttribute("value");
		
		
		driver.findElement(PRODUCT_QUANT_ADD).click();
		softAssert.assertEquals(driver.findElement(PRODUCT_QUANTITY).getAttribute("value"), "2");
		driver.findElement(PRODUCT_QUANT_MINUS).click();
		softAssert.assertEquals(driver.findElement(PRODUCT_QUANTITY).getAttribute("value"), quant);
		
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(REMOVE_PRODUCT_FROM_CART));
		driver.findElement(REMOVE_PRODUCT_FROM_CART).click();
		Thread.sleep(3000);
		EmptyCart();
		softAssert.assertAll();
			
	}
	
	public void CheckoutPageFromCart() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		new AddToCartComponents(driver).addToCart();
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
	
		driver.findElement(CHECKOUT_BUTTON).click();
	Thread.sleep(3000);
		softAssert.assertTrue(driver.getCurrentUrl().contains("checkouts"), "Checkout page is not displayed on clicking checkout button");
		driver.navigate().back();
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(3000);
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);
		driver.findElement(CONTINUE_SHOPPING).click();
		Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(HomePageLocators.ACCOUNT_ICON).isDisplayed(), "Continue Shopping redirects user to wrong page");
		
		softAssert.assertAll();
	}
	
	public void productDetailsInCart() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		
		
		ProductDetailsPageComponents pdp1 = new ProductDetailsPageComponents(driver);

		pdp1.getproductdetails();
		new AddToCartComponents(driver).atc();
		
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
		
		System.out.println("cart " + driver.findElement(PRODUCT_COLOR_SIZE).getText());
		System.out.println("pdp " + pdp1.productcolor + " / " + pdp1.prodsize);
		System.out.println("ex " + driver.findElement(PRODUCT_QUANTITY).getText());
		System.out.println("ac " + pdp1.productquant);
		
		
		softAssert.assertEquals(driver.findElement(PRODUCT_TITLE).getText(), pdp1.productname, "Wrong product title displayed in cart");
		softAssert.assertEquals(driver.findElement(PRODUCT_QUANTITY).getAttribute("value"), pdp1.productquant, "Wrong quantity added to cart");
		softAssert.assertEquals(driver.findElement(PRODUCT_PRICE).getText(), pdp1.productprice, "Wrong product price displayed in cart");
		softAssert.assertEquals(driver.findElement(PRODUCT_COLOR_SIZE).getText(), pdp1.productcolor + " / " + pdp1.prodsize, "Wrong color & size added to cart");
		
		softAssert.assertAll();
	}
	
	public void validateafterpayonShoppingCart() throws Exception
	{
		
	
	
		//product add to cart
		new HomePageComponents(driver).navigateToBraCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		new ProductDetailsPageComponents(driver).validatePage();

        //navigate to shopping cart page
		driver.findElement(HomePageLocators.CART_ICON).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
		Thread.sleep(3000);
		
		//Check AfterPay message display

				if(!driver.findElement(AFTERPAY_DIV).isDisplayed())
					throw new Exception("AfterPay Message not present on Shopping cart.");
				Reporter.log("After pay Message is displayed on Shopping Cart :: Displayed");

		//Check message with calculated installment and icon display
		price = driver.findElement(ESTIMATE_TOTAL_PRICE).getText();
		String[] price1 = price.split("\\$");
		String price2 = price1[price1.length-1];
		System.out.println("Estimate total is: "+price);
		if(Float.parseFloat(price2)<35 || Float.parseFloat(price2)>1000 )
		{
			softAssert.assertEquals(driver.findElement(AFTERPAYTEXT_WITH_AVAILABLE).getText(), "available for orders between $35 - $1000" , "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message is correct On Shopping cart :: Displayed and Correct");
			softAssert.assertTrue(driver.findElement(AFTERPAYLOGO_WITH_AVAILABLE_MSG).isDisplayed(), "Afterpay logo not present on Shopping cart.");	
			Reporter.log("After pay logo icon is displayed on Shopping cart :: Displayed");
		}
		else
		{

			installment =Float.parseFloat(price2)/4;
			String inst = String.format("%.2f",installment );
			String expectedmessage = "or 4 interest-free installments of $" + inst + " by"; 
			System.out.println("expected message is: " + expectedmessage);
			System.out.println("Actual message is: " +driver.findElement(AFTERPAYTEXT_WITH_INSTALLMENT).getText());
			softAssert.assertEquals(driver.findElement(AFTERPAYTEXT_WITH_INSTALLMENT).getText(), expectedmessage , "Wrong afterpay message displayed for product price");
			Reporter.log("After pay message with calculated installment is correct on Inline cart :: Displayed and correct");
			softAssert.assertTrue(driver.findElement(AFTERPAYLOGO_WITH_INSTALLMENT_MSG).isDisplayed(), "Afterpay logo not present on Shopping cart");
			Reporter.log("After pay logo icon is displayed on Inline cart :: Displayed");
		}
        Thread.sleep(3000);
//      //Gift Card Added To Cart
//      		new GiftCardPageComponents(driver).AddGiftCardTocart();
//      		Thread.sleep(3000);
      		
//      		//navigate to shopping cart
//      		wait = new WebDriverWait(driver, 20);
//    		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.VIEW_CART_LINK));
//    		driver.findElement(FlyCartPageLocator.VIEW_CART_LINK).click();
//    		Thread.sleep(3000);
//    		
//    		//Check gift cart with normal product in Shopping cart
//    		allElements = driver.findElements(LIST_OF_ITEMS_IN_SHOPPING_CART);
//    		System.out.println("Total item in cart is: "+allElements.size());
//    		for(int i=1; i<=allElements.size();i++)
//    		{
//    			String title = driver.findElement(By.cssSelector(".cart.container > div.cart__outer > .cart__items > div > article.line-item:nth-child("+i+") > .line-item__summary > a")).getText();
//    			System.out.println("product title in fly cart is: " +title);
//    			if(title.equalsIgnoreCase("E-Gift Card"))
//    			{
//    				try{
//    					System.out.println("Verifying for AfterPay Message .......");
//    					if(driver.findElement(AFTERPAY_DIV).isDisplayed())
//    						throw new Exception("AfterPay Message is displayed on Shopping cart with Gift Card.");
//    					Reporter.log("AfterPay Message is displayed on Shopping cart with Gift Card");
//    				}
//    				catch(NoSuchElementException e){
//    					System.out.println("After Pay Message is not displayed on Shopping cart with Gift Card.");
//    					Reporter.log("After Pay Message is not displayed on Shopping cart with Gift Card");
//    				}
//    			}
//    			
//    		}
      		
		
		softAssert.assertAll();
	}
}
