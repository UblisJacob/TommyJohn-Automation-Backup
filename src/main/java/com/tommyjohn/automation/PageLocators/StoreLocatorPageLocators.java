package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class StoreLocatorPageLocators
{
	//Store Locator layout
		public static By OURSTORES_HEADING = By.cssSelector("[data-tj-qa='store-header-title'],.stores__intro-header");
		public static By STORES_INTRO_CONTENT = By.cssSelector("[data-tj-qa='store-header-subtitle'],.stores__intro-content");
		public static By STORES_INDIVIDUAL_TITLE = By.cssSelector("[data-tj-qa='store-address-1']>h1,.stores__individual-title");
		public static By STORES_INDIVIDUAL_CONTENT = By.cssSelector("[data-tj-qa='store-address-1']>h2,.stores__individual-content");
		public static By STORE_GET_DIRECTION_LINK = By.cssSelector("[data-tj-qa='store-address-1']>div>a,.storeDirection");
		public static By FIND_A_RETAILER_TITLE = By.cssSelector("[data-tj-qa='find-store'],.bh-sl-heading");
		public static By USER_LOCATION_INPUT = By.cssSelector("[data-tj-qa='address-input'],.user-location-input");
		public static By FIND_A_STORE_BUTTON =By.cssSelector(".form-input>div>button:nth-child(3)");
		public static By GET_DIRECTION_BUTTON = By.cssSelector("#scasl-direction > a");
		public static By MAP = By.cssSelector(".gm-style>div>.gm-style-moc");
		public static By MAP_TEXT = By.cssSelector(".gm-style-mtc>button[title='Show street map']"	);
		public static By SATELLITE_TEXT = By.cssSelector(".gm-style-mtc>button[title='Show satellite imagery']");
		
		public static By FILTER_TAG_TITLE = By.cssSelector(".form-input>.scasl-tag>label");
		public static By FILTER_TAG_STORE_NAME = By.cssSelector("#scasl-tags>.scasl-tags");

}
