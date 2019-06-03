package com.tommyjohn.automation.Pages;

import com.tommyjohn.automation.Components.FlyCartComponents;

public class FlyCartPage extends FlyCartComponents {

	public void verifyAllFields() throws Exception {
		verifyAllTextsAndFieldsOnEmptyFlyCart();
	}
	
	public void verifyAllFieldsOnNonEmptyFlyCart() throws Exception {
		verifyAllTextsAndFieldsOnNonEmptyFlyCart();
	}

}
