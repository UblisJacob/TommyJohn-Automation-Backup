package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class StoreLocatorPageLocators
{
	//Store Locator layout
		public static By OURSTORES_HEADING = By.cssSelector(".stores__intro-header");
		public static By STORES_INTRO_CONTENT = By.cssSelector(".stores__intro-content");
		public static By STORES_INDIVIDUAL_TITLE = By.cssSelector(".stores__individual-title");
		public static By STORES_INDIVIDUAL_CONTENT = By.cssSelector(".stores__individual-content");
		public static By MORE_INFO_LINK = By.cssSelector(".stores__individual-link");
		public static By FIND_A_RETAILER_TITLE = By.cssSelector(".bh-sl-heading");
		public static By USER_LOCATION_INPUT = By.cssSelector(".user-location-input");
		public static By FIND_A_STORE_BUTTON =By.cssSelector(".form-input>button:nth-child(3)");
		public static By GET_DIRECTION_BUTTON = By.cssSelector("#scasl-direction > a");
		public static By MAP = By.cssSelector("#bh-sl-map > div > div > div:nth-child(1) > div:nth-child(3)");
		public static By MAP_TEXT = By.cssSelector("#bh-sl-map > div > div >div:nth-child(11)> div:nth-child(1) > div:nth-child(1)");
		public static By SATELLITE_TEXT = By.cssSelector("#bh-sl-map > div > div >div:nth-child(11)> div:nth-child(2) > div:nth-child(1)");

}
