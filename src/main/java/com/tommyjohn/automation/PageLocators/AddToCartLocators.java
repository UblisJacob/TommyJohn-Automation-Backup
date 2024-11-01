package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class AddToCartLocators {
	
	public static By ALL_SIZE_LIST = By.cssSelector(".product-option__color-swatches-wrapper>ul>li"); //(".product-option__variants.product-option__variants-size > ul > li")
	public static By ADD_TO_CART_BUTTON = By.xpath("//button[@id='ProductForm-Add']");
	public static By EMAIL_WHEN_IN_STOCK_BUTTON = By.cssSelector(".jtwl_header>span");
	public static By QUANTITY = By.cssSelector("#updatedQty.pdp.prodQty");
	public static By PLUS_BUTTON = By.cssSelector(".qty-selector.desktop> button#plusBtn");
	public static By UPDATED_QUANTITY = By.cssSelector("#updatedQty.pdp.prodQty");
	public static By MINUS_BUTTON = By.cssSelector(".qty-selector.desktop> button#minusBtn");
	public static By PAIR_GUARANTEE_LINK = By.cssSelector(".product-info__inner-bottom > div:nth-child(1) > button");
	public static By SHIPING_AND_RETURNS_LINK = By.cssSelector(".product-info__inner-bottom > div:nth-child(2) > button");
	public static By CHECKOUT_BUTTON = By.cssSelector(".inline-cart__action-btns > a:nth-child(3)");

	public static By EMAIL_WHEN_IN_STOCK_BUTTON_CLOSE = By.cssSelector(".close_jtwl");
	public static By INLINE_CART_CLOSE = By.xpath("//button[@aria-label='Close cart']");
	
	public static By JOIN_THE_WAITLIST = By.cssSelector(".multiPdp__atc-container>button");
	public static By INLINE_CART_TITLE = By.xpath("//h2[@class='text-heading-lg font-medium leading-none sm:text-heading']");
	
	


}
