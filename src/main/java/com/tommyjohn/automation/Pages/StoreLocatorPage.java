package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.StoreLocatorComponent;

public class StoreLocatorPage 
{
	WebDriver driver;

	public StoreLocatorPage(WebDriver driver) {
		this.driver=driver;
		
	}
	public void verifyStoreLocatorLayout() throws Exception
	{
		new StoreLocatorComponent(driver).StoreLocatorLayout();	
	}
}
