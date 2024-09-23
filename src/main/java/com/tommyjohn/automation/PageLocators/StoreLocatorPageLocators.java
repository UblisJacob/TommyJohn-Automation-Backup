package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class StoreLocatorPageLocators
{
	//Store Locator layout
		public static By OURSTORES_HEADING = By.xpath("//h2[@class='text-display-sm text-accent leading-none']");
		public static By STORES_INTRO_CONTENT = By.xpath("(//h2[@class='text-display-sm text-accent leading-none']//following::p)[1]");
		public static By STORES_INDIVIDUAL_TITLE = By.xpath("//div[@class='mt-sm']//h2");
		public static By STORES_INDIVIDUAL_CONTENT = By.xpath("//div[@class='mt-sm']//p");
		public static By STORE_GET_DIRECTION_LINK = By.xpath("(//div[@id='scasl-direction']//a)[1]");
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
