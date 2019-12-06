package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.LoyaltyOptInComponents;

public class LoyaltyOptInPages extends LoyaltyOptInComponents {

	public LoyaltyOptInPages(WebDriver driver) {
		super(driver);
	}
	
	public void Loyaltyoptinfromcreateaccount() throws Exception
	{
		loyaltyOptinfromCA();
	}
	
	public void Loyaltyoptinfromaccountpage() throws Exception
	{
		loyaltyOptinfromAccountPage();
	}
	
	public void Loyaltyoptinfromloyaltypage() throws Exception
	{
		loyaltyOptinfromLoyaltyPage();
	}
	
	public void verifyloyaltypageGuest() throws Exception
	{
		LoyaltyPageValidationforGuestUser();
	}
	
	public void verifyloyaltypageregistered() throws Exception
	
	{
		LoyaltyPageValidationForRegUser();
	}
	
	public void verifyswell() throws Exception
	
	{
		SwellPointValidation();
	}
}
