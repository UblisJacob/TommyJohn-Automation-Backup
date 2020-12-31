package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.AccountPageComponents;

public class AccountPage 
{
	WebDriver driver;
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
	}
  
	public void Verify_AccountPage() throws Exception
	{
		new AccountPageComponents(driver).Validate_LoggedContentsOn_AccountPage();
	}

}
