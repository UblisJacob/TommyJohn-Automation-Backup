package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class TommyJohnStoresPageLocators {
	
	public static By STORE_PAGE_HEADER = By.cssSelector(".intro__heading");
	public static By STORE_PAGE_HEADER_CONTENT = By.cssSelector(".intro__content p");
	public static By STORE_LIST = By.cssSelector(".stores-section__container>div");
	
//	Below locators are dynamic, need to change index
	public static By STORE_LIST_FIRST_STORE_CONTAINER = By.cssSelector(".stores-section__container>div:nth-child(1)");
	public static By STORE_NAME = By.cssSelector(".stores-section__container>div:nth-child(1)>div>h2");
	public static By STORE_ADDRESS = By.cssSelector(".stores-section__container>div:nth-child(1)>div>div:nth-child(3)>p");
	public static By STORE_PHONE = By.cssSelector(".stores-section__container>div:nth-child(1)>div>div:nth-child(4)>p");
	public static By STORE_HOURS = By.cssSelector(".stores-section__container>div:nth-child(1)>div>div:nth-child(4)>p:nth-child(2)");
	public static By STORE_GET_DIRECTIONS = By.cssSelector(".stores-section__container>div:nth-child(1)>div>div+a");
	
//	Google Maps Page
	public static By DESTINATION_FIELD = By.cssSelector("#sb_ifc51>input");
	
	

}
