package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class FlyCartPageLocator {
	public static By INLINE_CART_CLOSE_BUTTON = By.cssSelector("button.inline-cart__close-btn");
	public static By VIEW_CART_LINK = By.cssSelector("a.inline-cart__cart-link");
	public static By INLINE_CART_TITLE = By.cssSelector(".inline-cart__title");
	public static By SHIPING_MESSAGE_TEXT = By.cssSelector(".shipping-message__text");
	public static By SHIPING_MESSAGE_METER = By.cssSelector(".shipping-message__meter > span");
	public static By INLINE_CART_EMPTY_TEXT = By.cssSelector(".inline-cart__empty");
	public static By RECOMMENDED_PRODUCT_HEADING = By.cssSelector(".recommended-products__heading");
	public static By RECOMMENDED_PRODUCTS = By.cssSelector(".recommended-products");
	public static By RECOMMENDED_PRODUCTS_LIST = By.cssSelector(".recommended-products > a");
	
}