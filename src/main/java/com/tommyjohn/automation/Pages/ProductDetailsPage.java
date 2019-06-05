package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.ProductDetailsPageComponents;

public class ProductDetailsPage extends ProductDetailsPageComponents{
	WebDriver driver;
	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void verifyProductDetailsPage() throws Exception {
		
		
		new ProductDetailsPageComponents(driver).validateProductDetailsPage();
	}
}
