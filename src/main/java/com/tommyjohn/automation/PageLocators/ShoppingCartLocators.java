package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ShoppingCartLocators {

	public static By CART_TITLE = By.cssSelector(".cart__title");
	public static By EMPTY_CART_MESSAGE = By.cssSelector(".cart__empty > h3");
	public static By START_SHOPPING_BUTTON = By.cssSelector(".cart__empty > a");
	
	public static By REMOVE_PRODUCT_FROM_CART = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > button");
	
	public static By PRODUCT_IMAGE = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__image > img");
	public static By PRODUCT_TITLE = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > a");
	public static By PRODUCT_COLOR_SIZE = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div");
	public static By PRODUCT_PRICE = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__price > span");
	public static By PRODUCT_SUBTOTAL = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__total > div.line-item__subtotal >span");
	public static By PRODUCT_QUANTITY = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__total > div > #updatedQty");
	public static By PRODUCT_QUANT_MINUS = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__total > div > #minusBtn");
	public static By PRODUCT_QUANT_ADD = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__items > div > article.line-item > .line-item__summary > div.line-item__total > div > #plusBtn");
	public static By CART_TOTAL_TEXT = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__summary > .inline-cart__summary > div > span");
	public static By CART_TOTAL = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__summary > .inline-cart__summary > div > span:nth-child(2)");
	public static By CHECKOUT_BUTTON = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__summary > a");
	public static By CONTINUE_SHOPPING = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__summary > a.cart__continue");
	public static By GIFT_WRAP_LINK = By.cssSelector(".main-content > .cart.container > div.cart__outer > .cart__summary > div > a");
	
}
