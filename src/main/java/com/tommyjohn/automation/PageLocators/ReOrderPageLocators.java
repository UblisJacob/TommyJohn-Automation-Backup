package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReOrderPageLocators {
	
	public static By REORDER_TEXT = By.cssSelector(".account-logged-in-header--reorder > h1");
	public static By PRODUCT_IMAGE = By.cssSelector(".reorder-item__img-link");
	public static By PRODUCT_IMAGE_GENERIC = By.cssSelector(".account-reorder-grid .reorder-item:nth-child(1) img");
	public static By REORDER_ITEMS_LIST = By.cssSelector(".reorder-item");
	public static By REORDER_ITEM_DETAILS = By.cssSelector(".reorder-item__details");
	public static By REORDER_ITEM_DETAILS_GENERIC = By.cssSelector(".account-reorder-grid .reorder-item:nth-child(1)>.reorder-item__details");
	public static By REORDER_ITEM_TITLE = By.cssSelector(".reorder-item__title");
	public static By REORDER_ITEM_TITLE_GENERIC = By.cssSelector("(//button[text()='Add to Cart'])//..//span[@class='text-link reorder-item__title']");
	public static By REORDER_ITEM_VARIANT = By.cssSelector(".reorder-item__variant");
	public static By REORDER_ITEM_PRICE = By.cssSelector(".reorder-item__price");
	public static By REORDER_ITEM_ATC = By.cssSelector(".reorder-item > button");
	public static By ReORDER_ADDTOACRT = By.xpath("//button[contains(text(),'Add to Cart')]");
	public static By REORDER_ITEM_WRITEREVIEW = By.cssSelector(".reorder-item__review-link");
	public static By REORDER_ITEM_WRITEREVIEW_GENERIC = By.cssSelector(".account-reorder-grid .reorder-item:nth-child(1) .reorder-item__review-link");
	public static By ORDERDETAILS_TITLE = By.cssSelector("h1[data-tj-qa='account-order-history-order-number'],.account-logged-in-row > h1");
//	public static By ORDER_DETAILS_TITLE = By.cssSelector("h1[data-tj-qa='account-order-history-order-number']");
	public static By HEADER_REORDER_LINK = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(2) > a");
	@FindBy(xpath = "//span[@class='text-link reorder-item__title']")
	public List<WebElement> ReOrderItems;

	
	
	
	
	

}
