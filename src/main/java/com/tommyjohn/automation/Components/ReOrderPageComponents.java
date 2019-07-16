package com.tommyjohn.automation.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.FlyCartPageLocator;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.ProductDetailsPageLocators;
import com.tommyjohn.automation.PageLocators.ReOrderPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ReOrderPageComponents extends ReOrderPageLocators{
	WebDriver driver;
	public Actions action;
	WebElement element;
	String text;

	public ReOrderPageComponents(WebDriver driver) {

		this.driver = driver;
	}

	public void ReorderPagevalidation() throws Exception
	{

		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.ACCOUNT_ICON));
		driver.findElement(HomePageLocators.ACCOUNT_ICON).click();
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).sendKeys("ashish@tommyjohnwear.com");
		driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(CustomUtilities.properties.getProperty("password"));
		driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();
		Thread.sleep(3000);

		//action = new Actions(driver);
		//element = driver.findElement(HomePageLocators.ACCOUNT_ICON);
		//action.moveToElement(element).perform();
		//action.moveToElement(element).click(driver.findElement(HomePageLocators.REORDER)).build().perform();
		//driver.findElement(HomePageLocators.REORDER).click();
		
		driver.navigate().to(driver.getCurrentUrl()+"?view=reorder");
		Thread.sleep(3000);

		softAssert.assertEquals(driver.findElement(REORDER_TEXT).getText(), "Reorder", "Reorder title is not present on Reorder page");
		softAssert.assertTrue(driver.findElement(PRODUCT_IMAGE).isDisplayed(), "Product Images not present on reorder page");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_DETAILS).isDisplayed(), "Reorder item details link is not present");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_TITLE).isDisplayed(), "Reorder item title is not present");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_VARIANT).isDisplayed(), "Reorder item variant is not present");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_PRICE).isDisplayed(), "Reorder item price is not present");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_ATC).isDisplayed(), "Add to cart option is not present on reorder page");
		softAssert.assertTrue(driver.findElement(REORDER_ITEM_WRITEREVIEW).isDisplayed(), "Write a review option is not present on reorder page");

		softAssert.assertAll();
	}

	public void reorderitem() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		ReorderPagevalidation();
		text = driver.findElement(REORDER_ITEM_TITLE).getText();
		driver.findElement(REORDER_ITEM_ATC).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlyCartPageLocator.FIRST_PRODUCT_TITLE_IN_CART));
		softAssert.assertEquals(driver.findElement(FlyCartPageLocator.FIRST_PRODUCT_TITLE_IN_CART).getText(), text , "Wrong product added to cart");
		driver.findElement(FlyCartPageLocator.CHECKOUT_BUTTON).click();
		softAssert.assertAll();
	}

	public void orderdetails() throws Exception
	{

		SoftAssert softAssert = new SoftAssert();
		ReorderPagevalidation();
		driver.findElement(REORDER_ITEM_DETAILS).click();
		Thread.sleep(3000);
		softAssert.assertTrue(driver.findElement(ORDERDETAILS_TITLE).isDisplayed(), "Order details page didnt opened");
		softAssert.assertAll();		
	}
	
	public void reorderpagetopdp() throws Exception
	{
		SoftAssert softAssert = new SoftAssert();
		ReorderPagevalidation();
		Thread.sleep(3000);
		text = driver.findElement(REORDER_ITEM_TITLE).getText();
		driver.findElement(PRODUCT_IMAGE).click();
		Thread.sleep(3000);
		
		softAssert.assertEquals(driver.findElement(ProductDetailsPageLocators.PRODUCT_TITLE).getText(), text , "Wrong product page opened from reorder page");
		softAssert.assertAll();
	}
	
	
}

