package com.tommyjohn.automation.Components;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class FlyCartComponents extends FlyCartPageLocator{
	public WebDriver driver;
	Properties properties = CustomUtilities.properties;
	WebElement element;
	List<WebElement> allElements;


	public void verifyAllTextsAndFieldsOnEmptyFlyCart() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String text = null;
		
		
		new HomePageComponents(driver).validateCartIcon();
		driver.findElement(HomePageLocators.CART_ICON).click();
		
		// check for continue shopping button text and is displayed, enabled or not		
		softAssert.assertTrue(driver.findElement(INLINE_CART_CLOSE_BUTTON).isDisplayed(), "Inline cart close button is not displayed");
		softAssert.assertTrue(driver.findElement(INLINE_CART_CLOSE_BUTTON).isEnabled(), "Inline cart close button is not enable");
		text = driver.findElement(INLINE_CART_CLOSE_BUTTON).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("continueShoppingButton")), "Text for inline cart close button is changed");	
		Reporter.log("Continue Shopping button is Displayed :: Enabled :: Text is correct ");
		
		// check for inline cart link
		softAssert.assertTrue(driver.findElement(VIEW_CART_LINK).isDisplayed(), "View cart link is not displayed");
		softAssert.assertTrue(driver.findElement(VIEW_CART_LINK).isEnabled(), "View cart link is not enable");
		text = driver.findElement(VIEW_CART_LINK).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("viewCart")), "Text for view cart link is changed");	
		Reporter.log("View Cart link is Displayed :: Enabled :: Text is correct ");
		
		// check inline cart title
		softAssert.assertTrue(driver.findElement(INLINE_CART_TITLE).isDisplayed(), "Inline cart title is not displayed");
		text = driver.findElement(INLINE_CART_TITLE).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("inlineCartTitle")), "Text for inline cart title is changed");
		Reporter.log("Inline Cart Title is Displayed :: Text is correct ");
		
		// check for shipping message text
		softAssert.assertTrue(driver.findElement(SHIPING_MESSAGE_TEXT).isDisplayed(), "Shipping message text is not displayed");
		Reporter.log("Shipping message text is Displayed ");
		
		// check for shipping message meter
		softAssert.assertTrue(driver.findElement(SHIPING_MESSAGE_METER).isDisplayed(), "Shipping message meter is not displayed");
		Reporter.log("Shipping message meter is Displayed");
		// check for empty cart text
		softAssert.assertTrue(driver.findElement(INLINE_CART_EMPTY_TEXT).isDisplayed(), "Shipping 'Your cart is empty' is not displayed");
		text = driver.findElement(INLINE_CART_EMPTY_TEXT).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("inlineCartEmptyText")), "Expected text is "+properties.get("inlineCartEmptyText")+" but actual is "+text);
		Reporter.log("'Inline cart is empty' text is Displayed");
		
		// check for recomonded product heading
//		softAssert.assertTrue(driver.findElement(RECOMMENDED_PRODUCT_HEADING).isDisplayed(), "Recommended product heading is not displayed");
//		text = driver.findElement(RECOMMENDED_PRODUCT_HEADING).getText();
//		System.out.println("Text :: "+text);
//		softAssert.assertTrue(text.equals(properties.get("recommendedProductHeading")), "Expected text is "+properties.get("recommendedProductHeading")+" but actual is "+text);
//		Reporter.log("Recommended product heading is Displayed");
		
		// check for recomonded products present or not
//		softAssert.assertTrue(driver.findElement(RECOMMENDED_PRODUCTS).isDisplayed(), "Recommended products are not displayed");
//		allElements = driver.findElements(RECOMMENDED_PRODUCTS_LIST);
//		System.out.println("allelements size :: "+allElements.size());
//		softAssert.assertTrue(allElements.size() > 0, "Recommended products is not present");
//		Reporter.log("Recommended products are present in recommended product portione");
		
		// add any item to cart
//		driver.findElement(RECOMMENDED_PRODUCTS).click();
		
		driver.get(CustomUtilities.baseUrl);
		softAssert.assertAll();
	}
	
	public void verifyAllTextsAndFieldsOnNonEmptyFlyCart() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String text = null;
		
		// first add any product to cart
		new AddToCartComponents(driver).addToCart();
				
//		new HomePageComponents().validateCartIcon();
		driver.findElement(HomePageLocators.CART_ICON).click();
		
		// check for continue shopping button text and is displayed, enabled or not		
		softAssert.assertTrue(driver.findElement(INLINE_CART_CLOSE_BUTTON).isDisplayed(), "Inline cart close button is not displayed");
		softAssert.assertTrue(driver.findElement(INLINE_CART_CLOSE_BUTTON).isEnabled(), "Inline cart close button is not enable");
		text = driver.findElement(INLINE_CART_CLOSE_BUTTON).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("continueShoppingButton")), "Text for inline cart close button is changed");	
		Reporter.log("Continue Shopping button is Displayed :: Enabled :: Text is correct ");
		
		// check for inline cart link
		softAssert.assertTrue(driver.findElement(VIEW_CART_LINK).isDisplayed(), "View cart link is not displayed");
		softAssert.assertTrue(driver.findElement(VIEW_CART_LINK).isEnabled(), "View cart link is not enable");
		text = driver.findElement(VIEW_CART_LINK).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("viewCart")), "Text for view cart link is changed");	
		Reporter.log("View Cart link is Displayed :: Enabled :: Text is correct ");
		
		// check inline cart title
		softAssert.assertTrue(driver.findElement(INLINE_CART_TITLE).isDisplayed(), "Inline cart title is not displayed");
		text = driver.findElement(INLINE_CART_TITLE).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("inlineCartTitle")), "Text for inline cart title is changed");
		Reporter.log("Inline Cart Title is Displayed :: Text is correct ");
		
		// check for shipping message text
		softAssert.assertTrue(driver.findElement(SHIPING_MESSAGE_TEXT).isDisplayed(), "Shipping message text is not displayed");
		Reporter.log("Shipping message text is Displayed ");
		
		// check for shipping message meter
		softAssert.assertTrue(driver.findElement(SHIPING_MESSAGE_METER).isDisplayed(), "Shipping message meter is not displayed");
		Reporter.log("Shipping message meter is Displayed");
		
		element = driver.findElement(SHIPING_MESSAGE_METER);
		int width = element.getSize().getWidth();
		System.out.println("width :: "+element.getSize().getWidth());
		softAssert.assertTrue(width > 0, "Element is added but, shipping message meter is showing empty");
		
		Reporter.log("Shipping message meter is displayed correctly");
		allElements = driver.findElements(LIST_OF_ITEMS_IN_FLYCART);
		
		// check all fields for all items in flycart
		for(int i=1; i<=allElements.size();i++) {
			// check for product image
			softAssert.assertTrue(driver.findElement(By.cssSelector("div.inline-cart__col1 > div:nth-child(4) > article:nth-child("+i+") > div.line-item__image")).isDisplayed(), "First item image is not displayed");
			
			// check for product heading
			softAssert.assertTrue(driver.findElement(By.cssSelector("div.inline-cart__col1 > div:nth-child(4) > article:nth-child("+i+") > div.line-item__summary > a")).isDisplayed(), "First item heading is not displayed");	

			// check for remove item from cart button
			softAssert.assertTrue(driver.findElement(By.cssSelector("div.inline-cart__col1 > div:nth-child(4) > article:nth-child("+i+") > div.line-item__summary > button")).isDisplayed(), "First item remove button is not displayed");
			
			// check for line item option
			softAssert.assertTrue(driver.findElement(By.cssSelector("div.inline-cart__col1 > div:nth-child(4) > article:nth-child("+i+") > div.line-item__summary > div.line-item__option")).isDisplayed(), "Line item option is not displayed");
			
			// check for price
			softAssert.assertTrue(driver.findElement(By.cssSelector("div.inline-cart__col1 > div:nth-child(4) > article:nth-child("+i+") > div.line-item__summary > div.line-item__price > span")).isDisplayed(), "Price is not displayed");
			
			// check for plus minus and quantity
			softAssert.assertTrue(driver.findElement(QUANTITY_PLUS_MINUS_BUTTON).isDisplayed(), "plus or minus button is not displayed");
			
			// check for second price displayed
			softAssert.assertTrue(driver.findElement(By.cssSelector("article:nth-child("+i+") > div.line-item__summary > div.line-item__total > div.line-item__subtotal > span")).isDisplayed(), "plus or minus button is not displayed");
					
		}
		Reporter.log("Image, link text, price, plus-minus button is displayed for all items in cart");
		// check for Add Gift Wrap button
		softAssert.assertTrue(driver.findElement(ADD_GIFT_WRAP_BUTTON).isDisplayed(), "Add Gift Wrap button is not displayed");

		text = driver.findElement(ESTIMATE_TOTAL).getText();
		System.out.println("Estimate total text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("totalEstimateText")), "Expected text is "+properties.get("totalEstimateText")+" but actual is "+text);
		text = driver.findElement(ESTIMATE_TOTAL_SMALL).getText();
		System.out.println("Estimate total small text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("totalEstimateSmallText")), "Expected text is "+properties.get("totalEstimateSmallText")+" but actual is "+text);

		softAssert.assertTrue(driver.findElement(ESTIMATE_TOTAL).isDisplayed(), "Estimate total amount is not displayed");
		
		// check for checkout button
		softAssert.assertTrue(driver.findElement(CHECKOUT_BUTTON).isDisplayed(), "Checkout button is not displayed");
		
		softAssert.assertAll();
		driver.get(CustomUtilities.baseUrl);
		
	}
	

	public void validateNonEmptyFlyCart() throws Exception {
		String text1 = null;
		String text2 = null;
		
		SoftAssert softAssert = new SoftAssert();
		
		// first add any product to cart
		new AddToCartComponents(driver).addToCart();
			
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(3000);

		driver.findElement(INLINE_CART_CLOSE_BUTTON).click();
		Thread.sleep(2000);
		text1 = driver.getCurrentUrl();
		softAssert.assertTrue(text1.equals(CustomUtilities.baseUrl), "Continue shopping button is not working correctly");

		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(2000);
		
		driver.findElement(VIEW_CART_LINK).click();
		Thread.sleep(3000);
		
		softAssert.assertTrue(driver.getCurrentUrl().equals(properties.get("viewCartPage")), "Wrong page opened after cicked on View Cart link");
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(2000);
		
		// check product heading for available items in cart
		text1 = driver.findElement(FIRST_PRODUCT_TITLE_IN_CART).getText();
		System.out.println("Text1 :: "+text1);
		driver.findElement(FIRST_PRODUCT_TITLE_IN_CART).click();
		Thread.sleep(3000);
		text2 = driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText();
		System.out.println("Text2 :: "+text2);
		softAssert.assertTrue(text1.equals(text2), "Wrong page opened after clicked on cart product");
		
		driver.findElement(HomePageLocators.CART_ICON).click();
		
		// check for plus minus button
		String quantity = driver.findElement(QUANTITY).getAttribute("data-item-qty");
		System.out.println("quantity :: "+quantity);
		driver.findElement(PLUS_BUTTON).click();
		Thread.sleep(3000);
		String quantity1 = driver.findElement(QUANTITY).getAttribute("data-item-qty");
		System.out.println("quantity1 :: "+quantity1);
		// check quantity added by 1 or not 
		if(Integer.parseInt(quantity1)!=Integer.parseInt(quantity)+1) 
			throw new Exception("Quantity not increased by 1");

		Reporter.log("Quantity increased by 1");

		driver.findElement(MINUS_BUTTON).click();
		Thread.sleep(2000);
		
		String quantity2 = driver.findElement(QUANTITY).getAttribute("data-item-qty");
		System.out.println("quantity2 :: "+quantity2);
		if(Integer.parseInt(quantity2)!=Integer.parseInt(quantity1)-1)
			throw new Exception("Quantity not decreased by 1");
		Reporter.log("Quantity decreased by 1");

		// check for checkout button
		driver.findElement(CHECKOUT_BUTTON).click();
		Thread.sleep(2000);
		driver.get(CustomUtilities.baseUrl);
		Thread.sleep(3000);
		Reporter.log("Checkout button is Displayed :: Clickable");
		
		// check for cross close button near text heading link to remove item from cart
		driver.findElement(HomePageLocators.CART_ICON).click();
		Thread.sleep(2000);
		
		allElements = driver.findElements(LIST_OF_ITEMS_IN_FLYCART);
		System.out.println("List of items after click :: "+allElements.size());
		int list1 = allElements.size();
		
		driver.findElement(CROSS_CLOSE_BUTTON).click();
		Thread.sleep(2000);
		
		allElements = driver.findElements(LIST_OF_ITEMS_IN_FLYCART);
		System.out.println("List of items after click :: "+allElements.size());
		int list2 = allElements.size();
		
		softAssert.assertFalse(list1 == list2, "Item is not removed from cart");
		
		Reporter.log("Item is able to remove from cart");
	
		softAssert.assertAll();
	}

	public FlyCartComponents(WebDriver driver) {
		this.driver = driver;
	}
}
