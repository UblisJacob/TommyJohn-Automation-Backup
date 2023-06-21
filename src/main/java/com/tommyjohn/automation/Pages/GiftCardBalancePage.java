package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.GiftCardBalancePageComponents;


public class GiftCardBalancePage extends GiftCardBalancePageComponents{
	
	public GiftCardBalancePage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validates if both Physical and E-Gift Card Forms are present on Gift Card balance Page
	 * @throws Exception 
	 */
	public void verifyPhysicalAndDigitalFormPresence() throws Exception {
		validateFormAndItsFields();
	}
	
	/**
	 * Fills out form for Physical Gift Card and checks related functionalities
	 * @throws Exception 
	 */
	public void verifyPhysicalGiftCardFormInteraction(String validGCID, String validGCPin, String validCardHolderEmail) throws Exception {
		physicalGiftCardFormValidation(validGCID, validGCPin, validCardHolderEmail);
		
	}
	
	public void verifyDigitalGiftCardFormInteraction(String validGCID, String validCardHolderEmail) throws Exception {
		digitalGiftCardFormValidation(validGCID, validCardHolderEmail);
	}
	

}
