package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.FlyCartComponents;
import com.tommyjohn.automation.Components.ProductDetailsPageComponents;

public class AfterPay {
	
	WebDriver driver;

	public AfterPay(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}

	public void AfterPayOnPDP() throws Exception
	{
		new ProductDetailsPageComponents(driver).validateafterpayonpdp();
	}

	public void AfterPayOnInlineCart() throws Exception
	{
		new FlyCartComponents(driver).validateafterpayoninlinecart();
	}
}
