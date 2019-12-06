package com.tommyjohn.automation.Pages;


import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.ContactUsComponent;
import com.tommyjohn.automation.Components.HomePageComponents;

public class ContactUsPage  {

	WebDriver driver;
	public ContactUsPage(WebDriver driver) {
		this.driver=driver;

	}
	public void VerifyContactUsFlow() throws Exception
	{
		new HomePageComponents(driver).navigateToContactUsPage();
		Thread.sleep(3000);
		new ContactUsComponent(driver).ContactUsFlow();
        Thread.sleep(3000);
	}
	
	public void ContactUsFieldValidation_page() throws Exception
	{
		new HomePageComponents(driver).navigateToContactUsPage();
		Thread.sleep(3000);
		new ContactUsComponent(driver).ContactUsFieldValidation();
		Thread.sleep(3000);
	}
}
