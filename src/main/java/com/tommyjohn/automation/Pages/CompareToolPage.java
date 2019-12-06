package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.CompareToolComponent;
import com.tommyjohn.automation.Components.HomePageComponents;

public class CompareToolPage 
{
	WebDriver driver;
	public CompareToolPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void validateCompareTool() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		new CompareToolComponent(driver).CompareToolOnMensUnderwearCollection();
	}
}
