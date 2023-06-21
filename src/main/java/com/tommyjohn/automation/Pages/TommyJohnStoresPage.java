package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.TommyJohnStoresComponents;

public class TommyJohnStoresPage extends TommyJohnStoresComponents{
	
	public TommyJohnStoresPage(WebDriver driver) {
		super(driver);
		
	}
	
	public void validateTommyJohnStoresPage() throws InterruptedException {
		validateTJStoresPageComponentsAndInteractions();
		
	}

}
