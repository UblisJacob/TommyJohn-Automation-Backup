package com.tommyjohn.automation.Components;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.tommyjohn.automation.PageLocators.HomePageLocators;
import com.tommyjohn.automation.PageLocators.StoreLocatorPageLocators;
import com.tommyjohn.automation.utils.CustomUtilities;

public class StoreLocatorComponent extends StoreLocatorPageLocators
{
	public WebDriver driver;
	public String currentUrl1;
	public String currentUrl2;
	boolean flag = false;
	public static WebElement element;
	public Actions action;
	List<WebElement> allElements;
	Properties properties = CustomUtilities.properties;
	String text;
	
	public StoreLocatorComponent(WebDriver driver) {
		
		this.driver = driver;
	}
	public void StoreLocatorLayout() throws Exception
	{
		SoftAssert softassert = new SoftAssert();
		 // navigate to Store Locator  page
				text = driver.findElement(HomePageLocators.STORE_LOCATOR_LINK).getText();
				if(!text.equals("Store Locator"))
					throw new Exception("Store Locator link text is changed");	
				driver.findElement(HomePageLocators.STORE_LOCATOR_LINK).click();
				Thread.sleep(3000);
				if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/store-locator")) 
					System.out.println("Store Locator footer link is Clickable and correct.");
				else
					throw new Exception("Wrong page opened after Store Locator footer link clicked");
				Reporter.log("Store Locator footer link is Displayed :: Clickable");
				
		 //Field validation
			softassert.assertTrue(driver.findElement(OURSTORES_HEADING).isDisplayed(), "'Our Stores' heading is not displayed on Store locator page.");
			softassert.assertEquals(driver.findElement(OURSTORES_HEADING).getText(),properties.getProperty("StoreLocatorHeading"),"Text changed for 'Our Stores' heading on store Locator page." );
			Reporter.log("Store Intro Heading is Displayed :: Correct.");
			
			softassert.assertTrue(driver.findElement(STORES_INTRO_CONTENT).isDisplayed(), "Stores intro content is not displayed on Store locator page.");
			System.out.println("Store intro content is: "+driver.findElement(STORES_INTRO_CONTENT).getText());
			softassert.assertEquals(driver.findElement(STORES_INTRO_CONTENT).getText(),properties.getProperty("StoreIntroContent"),"Text changed for 'Store intro content' on store Locator page." );
			Reporter.log("Store Intro Heading is Displayed :: Correct.");
			
			softassert.assertTrue(driver.findElement(STORES_INDIVIDUAL_TITLE).isDisplayed(), "Stores individual title is not displayed.");
	         allElements= driver.findElements(STORES_INDIVIDUAL_TITLE);
	        int count = allElements.size();
	        System.out.println("Total count of store individual title is: "+count);
	        Reporter.log("Stores Individual Title is Displayed .");
	
	        softassert.assertTrue(driver.findElement(STORES_INDIVIDUAL_CONTENT).isDisplayed(), "Stores individual Content is not displayed.");
	         allElements= driver.findElements(STORES_INDIVIDUAL_CONTENT);
	        count = allElements.size();
	        System.out.println("Total count of store individual Content is: "+count);
	        Reporter.log("Stores Individual Content is Displayed .");
	        
	        //validate 'More Info' link
	        softassert.assertTrue(driver.findElement(MORE_INFO_LINK).isDisplayed(), "'More Info' link is not displayed.");
			text = driver.findElement(MORE_INFO_LINK).getText();
			if(!text.equals(properties.getProperty("MoreInfoText")))
				throw new Exception("Text changed for 'More Info' on stores individual section.");	
			driver.findElement(MORE_INFO_LINK).click();
			Thread.sleep(3000);
			if(driver.getCurrentUrl().equalsIgnoreCase("https://tommyjohn.com/pages/store-directory")) 
				driver.navigate().back();
			else
				throw new Exception("Wrong page opened after More Info link clicked");
			Reporter.log("More Info link is Displayed :: Clickable.");
			Thread.sleep(3000);
			softassert.assertTrue(driver.findElement(FIND_A_RETAILER_TITLE).isDisplayed(), "Find a Retailer Heading is not displayed.");
			softassert.assertEquals(driver.findElement(FIND_A_RETAILER_TITLE).getText(),properties.getProperty("FindARetailerHeading"),"Text changed for 'Find A Retailer' on store Locator page." );
			Reporter.log("Find A Retailer Heading is Displayed :: Correct.");
			
			softassert.assertTrue(driver.findElement(FIND_A_STORE_BUTTON).isDisplayed(), "Find a Store button is not displayed.");
			softassert.assertEquals(driver.findElement(FIND_A_STORE_BUTTON).getText(),properties.getProperty("FindAStoreButton"),"Text changed for 'Find A Store' button on store Locator page." );
			Reporter.log("Find A Store Button is Displayed :: Correct.");
                        
                        //Scroll down
			JavascriptExecutor jse=(JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)");			

			softassert.assertTrue(driver.findElement(MAP).isDisplayed(), "Map is not displayed.");
			Reporter.log("Map is displayed on store locator page.");
			
                        softassert.assertTrue(driver.findElement(MAP_TEXT).isDisplayed(), "Map button is not displayed on Find a retailer section.");
			System.out.println("Map button text is: "+driver.findElement(MAP_TEXT).getText());
			softassert.assertEquals(driver.findElement(MAP_TEXT).getText(),"Map","Text changed for 'Map' button on Find a retailer section." );
			Reporter.log("Map Button is Displayed.");
			
			softassert.assertTrue(driver.findElement(SATELLITE_TEXT).isDisplayed(), "Satellite button is not displayed on Find a retailer section.");
			System.out.println("Satellite button text is: "+driver.findElement(SATELLITE_TEXT).getText());
			softassert.assertEquals(driver.findElement(SATELLITE_TEXT).getText(),"Satellite","Text changed for 'Satellite' button on Find a retailer section." );
			Reporter.log("Satellite Button is Displayed.");
			
			//validate User Location Input box 
			softassert.assertTrue(driver.findElement(USER_LOCATION_INPUT).isDisplayed(), "User location input box is not displayed.");
			driver.findElement(USER_LOCATION_INPUT).clear();
			Thread.sleep(2000);
			driver.findElement(USER_LOCATION_INPUT).sendKeys("New York, NY, USA");
			Thread.sleep(2000);
			driver.findElement(USER_LOCATION_INPUT).sendKeys(Keys.ENTER);
            System.out.println("User location input box is Displayed :: Clickable.");
			Reporter.log("User location input box is Displayed :: Clickable.");
			
			//validate location list
			driver.findElement(FIND_A_STORE_BUTTON).click();
			Thread.sleep(3000);
			text = driver.findElement(By.cssSelector(".bh-sl-loc-list>ul>li:nth-child(1)>div>div>div:nth-child(5)>span:nth-child(1)")).getText();
			System.out.println("First displayed city location is: "+text);
			if(!text.contains("New York"))
				throw new Exception("Location list details are not according to entered location.");
			Reporter.log("Fetched location list details are displayed :: Correct");
			
			//validate Get Directions button
			softassert.assertTrue(driver.findElement(GET_DIRECTION_BUTTON).isDisplayed(), "Get Directions button is not displayed.");
			System.out.println("Get Direction text is: "+driver.findElement(GET_DIRECTION_BUTTON).getText());
			softassert.assertEquals(driver.findElement(GET_DIRECTION_BUTTON).getText(),properties.getProperty("GetDirectionsButton"),"Text changed for 'Get Directions' button on store Locator page." );
			driver.findElement(GET_DIRECTION_BUTTON).click();
			Thread.sleep(3000);
					//check google map
			Set<String> handles = null;
			List<String> list = null;
			handles = driver.getWindowHandles();
			list = new ArrayList<String>(handles);
			driver.switchTo().window(list.get(1));
			System.out.println("url on click Get directions button is: "+driver.getCurrentUrl());
			if(!driver.getCurrentUrl().contains("www.google.com/maps"))
				throw new Exception("Wrong page opened after 'Get Direction' link clicked");
			driver.switchTo().window(list.get(0));
			Reporter.log("'Get Directions' Button is Displayed :: Clickable.");
			softassert.assertAll();			
	}
}
