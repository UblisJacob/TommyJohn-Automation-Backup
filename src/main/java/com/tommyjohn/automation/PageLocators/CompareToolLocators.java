package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class CompareToolLocators {

	public static By COMPARE_CTA = By.cssSelector(".product-grid-controls>button.product-grid-controls__button--compare");
	public static By COMPARE_BAR_STATUS = By.cssSelector(".comparison-bar>p");
	public static By COMPARED_ITEM_NUMBER = By.cssSelector(".comparison-bar>p>span");
	public static By COMPARE_NOW = By.cssSelector(".comparison-bar__controls>button.comparison-bar__button--compare");
	public static By CLEAR_ALL =By.cssSelector(".comparison-bar__controls>button.comparison-bar__button--clear");
	public static By COMPARE_MODAL = By.cssSelector("#shopify-section-comparison-tool>section");
	public static By MODAL_CLOSE =By.cssSelector("#shopify-section-comparison-tool>section>.close-modal>.icon-close");
	public static By COMPARE_ITEMS_TITLE = By.cssSelector("#shopify-section-comparison-tool>section>.close-modal>span");
	public static By TOTAL_IMG_IN_OVERLAY = By.cssSelector("#shopify-section-comparison-tool > section > div > div > div:nth-child(1) > div > div > div > a > img");
	
	
	
}
