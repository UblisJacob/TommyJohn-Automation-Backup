package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.AddToCartComponents;
import com.tommyjohn.automation.Components.FlyCartComponents;
import com.tommyjohn.automation.Components.ShoppingCartComponents;
import com.tommyjohn.automation.commonMethods.GeneralMethods;

public class FlyCartPage extends FlyCartComponents {
	GeneralMethods objGeneralMethods = new GeneralMethods();
	String searchText="";

	public FlyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void verifyAllFields() throws Exception {
		new FlyCartComponents(driver).verifyAllTextsAndFieldsOnEmptyFlyCart();
	}

	public void verifyAllFieldsOnNonEmptyFlyCart() throws Exception {
		new FlyCartComponents(driver).verifyAllTextsAndFieldsOnNonEmptyFlyCart();
	}

	public void validateAllFieldsOnNonEmptyFlyCart() throws Exception {
		new FlyCartComponents(driver).validateNonEmptyFlyCart();
	}

	public void verifygiftWrap() throws Exception {
		new FlyCartComponents(driver).verifygiftWrap();
	}

	public void validateFlyCartOpenedWithQSandPDP() throws Exception {
		new FlyCartComponents(driver).VerifyFlyCartOpenWithQSAndPDP();
	}

	public void validateOOSonInlinecart() throws Exception {
		new FlyCartComponents(driver).validateOOSonInlinecart();
	}
	
	public void validatePromotionMessaging() throws Exception {
		String accountEmail = objGeneralMethods.readPropertiesFile("loginemail");
		String password = objGeneralMethods.readPropertiesFile("password");
		new AccountLoginPage(driver).loginAccount(accountEmail, password);
		searchText = objGeneralMethods.readPropertiesFile("searchSleepwearSets");
		new HomePage(driver).validateSearchFunctionEnterKeyword(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
		new AddToCartComponents(driver).addToCart();
		new FlyCartComponents(driver).validatePromotionalMessage();
	}
	public void validateTieredMeter() throws Exception {
		String accountEmail = objGeneralMethods.readPropertiesFile("loginemail");
		String password = objGeneralMethods.readPropertiesFile("password");
		new AccountLoginPage(driver).loginAccount(accountEmail, password);
		new AddToCartComponents(driver).addsingleproductToCart();
		new FlyCartComponents(driver).tieredMeterPresence();
		new ShoppingCartComponents(driver).tieredMeterFunctionality();
	}

	public void ValidateAddingProductToCartFromInlineCartSuggestion() throws Exception {
		new FlyCartComponents(driver).ValidateAddingProductToCartFromInlineCartSuggestions();

	}

	public void ValidateAppropriateIsProductIsToCartFromInlineCartSuggestion() throws Exception {
		new FlyCartComponents(driver).ValidateAppropriateProductIsAddedToCartFromInLineCartSuggestion();

	}

	public void ValidateQuickShopATCIsAvailableInSuggestionProducts() throws Exception {
		new FlyCartComponents(driver).verifyATCInInlineCartSuggestion();

	}

	public void ValidateQuickShopSizeOverlayIsDisplayedInSuggestionProducts() throws Exception {
		new FlyCartComponents(driver).verifyQuickShopSizeOverlayIsDisplayed();

	}

	public void ValidateSelectedColorSwatchIsDisplayedInTheInlineCart() throws Exception {
		new FlyCartComponents(driver).verifySelectedColorSwatchIsDisplayedInTheInlineCart();

	}

	public void ValidateRemovingItemsFromInlineCart() throws Exception {
		new FlyCartComponents(driver).ValidateRemovingItemsFromInlineCart();

	}

	public void ValidatePageGreyedOutOnAddingProducts() throws Exception {
		new FlyCartComponents(driver).ValidatePageGreyedOutOnAddingProducts();

	}
}
