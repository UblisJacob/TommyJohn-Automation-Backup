package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.GiftCardPageComponents;

public class GiftCardPage extends GiftCardPageComponents {

	public GiftCardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void egiftCardPageCheck() throws Exception {
		new GiftCardPageComponents(driver).GoToOnGiftCardPage();
	}

	public void ValidateWishlistCTAIsNotAvailableInGiftcardPDP() throws Exception {
		new GiftCardPageComponents(driver).ValidateWishlistCTAIsNotAvailableInGiftcardPDP();
	}

	public void ValidateMailIsAdded() throws Exception {
		new GiftCardPageComponents(driver).ValidateMailIsAdded();
	}


	public void ValidateIwantToSendThisAsAGiftRadioButton() throws Exception {
		new GiftCardPageComponents(driver).ValidateIwantToSendThisAsAGiftRadioButton();
	}

	public void ValidateGiftDetailsInCartPage() throws Exception {
		new GiftCardPageComponents(driver).ValidateGiftDetailsInCartPage();
	}

	public void ValidateGiftDetailsInCheckoutPage() throws Exception {
		new GiftCardPageComponents(driver).ValidateGiftDetailsInCheckoutPage();
	}

	public void ValidateFraudDisclosureInGCPDP() throws Exception {
		new GiftCardPageComponents(driver).ValidateFraudDisclosureInGCPDP();
	}


	public void verifyfieldsforGCPage() throws Exception{
		new GiftCardPageComponents(driver).verifyFieldsforGC();
	}

	public void verifyerrormessagesongiftcard() throws Exception{
		new GiftCardPageComponents(driver).verifyErrorMessagesforGC();

	}
	public void verifyPGCard() throws Exception {
		new GiftCardPageComponents(driver).verifyPGCard();
	}

}
