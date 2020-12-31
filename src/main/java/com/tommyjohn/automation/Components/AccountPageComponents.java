package com.tommyjohn.automation.Components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.AccountLoginLocators;
import com.tommyjohn.automation.PageLocators.AccountPageLocators;
import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class AccountPageComponents extends AccountPageLocators
{
	WebDriver driver;
	public Actions actions;
	String currentUrl;
	SoftAssert softassert = new SoftAssert();
	List<WebElement> list = new ArrayList<WebElement>();
	String text;
	public AccountPageComponents(WebDriver driver)
	{
		this.driver=driver;
	}

	public void NavigateTo_AccountPage() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocators.ACCOUNT_NAV_DROPDOWN));
		driver.findElement(HomePageLocators.ACCOUNT_NAV_DROPDOWN).click();
		Thread.sleep(3000);
		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).clear();
		driver.findElement(AccountLoginLocators.CUSTOMER_EMAIL_INPUT).sendKeys("vijeta@tommyjohnwear.com");
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.CUSTOMER_PASSWORD_INPUTBOX).sendKeys(CustomUtilities.properties.getProperty("password"));
		Thread.sleep(2000);
		driver.findElement(AccountLoginLocators.LOGIN_BUTTON).click();
		Thread.sleep(5000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_PAGE_HEADING));
		currentUrl = driver.getCurrentUrl();
		if(!currentUrl.equals(CustomUtilities.properties.getProperty("accountpage")))
			throw new Exception("account page is not displayed after successful login");
		Reporter.log("User navigates to account page after successful login");
				
	}
	
	public void Validate_LoggedContentsOn_AccountPage() throws Exception
	{
		NavigateTo_AccountPage();
		softassert.assertTrue(driver.findElement(LOGIN_INFORMATION_TITLE).isDisplayed(), "Login Information title is not displayed on account page");
		softassert.assertTrue(driver.findElement(LOGGED_IN_ID).isDisplayed(), "Logged-in id is not displayed on 'Login Information' section");
		
		softassert.assertTrue(driver.findElement(ADDRESS_BOOK_TITLE).isDisplayed(), "Address Book title is not displayed on account page");
		softassert.assertTrue(driver.findElement(DEFAULT_SHIPPING_ADDRESS_TITLE).isDisplayed(), "Default address shipping title is not displayed inside 'Address Book' section");
		softassert.assertTrue(driver.findElement(ADDRESS_CONTENT).isDisplayed(), "Default address shipping contents are not displayed for logged-in user");
		
		softassert.assertTrue(driver.findElement(ORDER_HISTORY_TITLE).isDisplayed(), "Order History title is not displayed on account page");
		list= driver.findElements(ORDER_HISTORY_TABLE_TITLE_HEADING);
		softassert.assertTrue(list.size()>0, "Order history table has no columns on account page");
		softassert.assertTrue(driver.findElement(FIRST_ORDER_NUMBER).isDisplayed(), "Order number is not displayed on Order History table");
		Reporter.log("Account page contents for logged-in user are Displayed :: Correct");
		
		text= driver.findElement(FIRST_ORDER_NUMBER).getText();
		driver.findElement(FIRST_ORDER_NUMBER).click();
		Thread.sleep(5000);
		if(!driver.getCurrentUrl().contains("https://tommyjohn.com/account/orders/"))
			throw new Exception("Wrong page opened after click on order number from account page");
		String text1= driver.findElement(By.cssSelector("header.account-logged-in-row>h1")).getText();
		String OrderNumber= text1.substring(text1.indexOf("#")+1, text1.indexOf("-")-1);
		System.out.println("actual number is: "+ OrderNumber+" and expected is: "+text);
		softassert.assertEquals(text, OrderNumber,"Tracking order number is incorrect");
		Reporter.log("Order number is clickable and navigates correctly for track the order");
						
		softassert.assertAll();
		
	}
}

