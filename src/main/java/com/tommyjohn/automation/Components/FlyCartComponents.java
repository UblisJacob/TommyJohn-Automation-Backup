package com.tommyjohn.automation.Components;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class FlyCartComponents extends FlyCartPageLocator{
	public static WebDriver driver = CustomUtilities.driver;
	public Properties properties = CustomUtilities.properties;
	WebElement element;
	List<WebElement> allElements;


	public void verifyAllTextsAndFieldsOnEmptyFlyCart() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String text = null;
		
		
		new HomePageComponents().validateCartIcon();
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
		softAssert.assertTrue(driver.findElement(RECOMMENDED_PRODUCT_HEADING).isDisplayed(), "Recommended product heading is not displayed");
		text = driver.findElement(RECOMMENDED_PRODUCT_HEADING).getText();
		System.out.println("Text :: "+text);
		softAssert.assertTrue(text.equals(properties.get("recommendedProductHeading")), "Expected text is "+properties.get("recommendedProductHeading")+" but actual is "+text);
		Reporter.log("Recommended product heading is Displayed");
		
		// check for recomonded products present or not
		softAssert.assertTrue(driver.findElement(RECOMMENDED_PRODUCTS).isDisplayed(), "Recommended products are not displayed");
		allElements = driver.findElements(RECOMMENDED_PRODUCTS_LIST);
		System.out.println("allelements size :: "+allElements.size());
		softAssert.assertTrue(allElements.size() > 0, "Recommended products is not present");
		Reporter.log("Recommended products are present in recommended product portione");
		
		// add any item to cart
		driver.findElement(RECOMMENDED_PRODUCTS).click();
		
		driver.get(CustomUtilities.baseUrl);
		softAssert.assertAll();
	}
	
	public void verifyAllTextsAndFieldsOnNonEmptyFlyCart() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		String text = null;
		
		new ProductDetailsPageComponents().validateProductDetailsPage();
		
//		new HomePageComponents().validateCartIcon();
//		driver.findElement(HomePageLocators.CART_ICON).click();
		
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
		Point location = element.getLocation();
		System.out.println("location :: "+location+" width :: "+element.getSize().getWidth());

	}
}
