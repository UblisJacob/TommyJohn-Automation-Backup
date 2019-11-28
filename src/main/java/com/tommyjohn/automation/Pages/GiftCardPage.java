package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.GiftCardPageComponents;

public class GiftCardPage extends GiftCardPageComponents {

	public GiftCardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void giftCardPageCheck() throws Exception {
		new GiftCardPageComponents(driver).GoToOnGiftCardPage();
	}

	public void verifyfieldsforGCPage() throws Exception{
		new GiftCardPageComponents(driver).verifyFieldsforGC();
	}

	public void verifyerrormessagesongiftcard() throws Exception{
		new GiftCardPageComponents(driver).verifyErrorMessagesforGC();

	}

}
