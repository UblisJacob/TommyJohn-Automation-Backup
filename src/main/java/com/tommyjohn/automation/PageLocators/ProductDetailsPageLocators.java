package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ProductDetailsPageLocators {

	public static By FIRST_PRODUCT_REVIEW_STARS = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > a > div");

	public static By PRODUCT_TITLE = By.cssSelector(".product-info__title");
	public static By PRODUCT_PRICE = By.cssSelector("div.product-info__inner-top > span > span:nth-child(2)");
//	public static By RATING_STARS = By.cssSelector("div.yotpo-bottomline.star-clickable");
	public static By RATING_STARS = By.cssSelector("div.yotpo-bottomline > span");
	public static By RATING_NUMBER = By.cssSelector("div.yotpo-bottomline > a");
	public static By RATING_AND_REVIEWS_BOX = By.cssSelector(".yotpo-regular-box.yotpo-bottomline.yotpo-bottomline-3-boxes");
	public static By FIRST_BOX_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-bottomline-box-1.yotpo-stars-and-sum-reviews");
	public static By STAR_RATINGS = By.cssSelector(".yotpo-stars-and-sum-reviews > span > span.sr-only");
	public static By BE_THE_FIRST_TO_WRITE_REVIEW = By.cssSelector(".yotpo-first-review-content > input");
	public static By WRITE_REVIEW_BUTTON = By.cssSelector(".yotpo-label.yotpo-small-box > div:nth-child(2)");
	public static By WRITE_REVIEW_HEADING = By.cssSelector("#pdpReviews > div > div > form > div");
	public static By WRITE_REVIEW_HEADING1 = By.cssSelector(".y-label.yotpo-header-title");
	public static By REVIEW_STARS_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-bottomline-box-1 > span:nth-child(1)");
	public static By COLOR_LISTS = By.cssSelector(".product-option__variants.product-option__variants-color.hide > ul >li");
	public static By FIRST_COLOR = By.cssSelector(".product-option__variants.product-option__variants-color > ul > li:nth-child(1) > label");
	public static By SECOND_COLOR = By.cssSelector(".product-option__variants.product-option__variants-color > ul > li:nth-child(2) > label");
	public static By THIRD_COLOR = By.cssSelector(".product-option__variants.product-option__variants-color > ul > li:nth-child(3) > label");
	public static By COLOR_TEXT = By.cssSelector(".product-info__header > span:nth-child(1)");
	public static By SIZE_TEXT = By.cssSelector(".product-info__header > span:nth-child(2)");
	public static By ALL_SIZE_LIST = By.cssSelector(".product-option__variants.product-option__variants-size > ul > li");
	public static By ALL_COLOR_LIST = By.cssSelector(".product-option__variants.product-option__variants-color > ul > li");
	public static By PRODUCT_DETAILS_BUTTON = By.cssSelector(".product-details__controls--inner > button:nth-child(1)");
	public static By WHATS_MY_SIZE = By.cssSelector(".product-info__header--desktop > button");
	public static By SIZE_GUIDE_BUTTON = By.cssSelector(".product-details__controls--inner > button:nth-child(2)");
	public static By ADD_TO_CART_BUTTON = By.cssSelector("button.product-info__atc.button.button--red.button--full.js-loop-atc");
	public static By EMAIL_WHEN_IN_STOCK_BUTTON = By.cssSelector("button.product-info__atc.button.button--red.button--full.js-loop-atc.email-when-in-stock");
	public static By QUANTITY = By.cssSelector("#updatedQty.pdp.prodQty");
	public static By PLUS_BUTTON = By.cssSelector(".qty-selector.desktop> button#plusBtn");
	public static By UPDATED_QUANTITY = By.cssSelector("#updatedQty.pdp.prodQty");
	public static By MINUS_BUTTON = By.cssSelector(".qty-selector.desktop> button#minusBtn");
	public static By PAIR_GUARANTEE_LINK = By.cssSelector(".product-info__inner-bottom > div:nth-child(1) > button");
	public static By SHIPING_AND_RETURNS_LINK = By.cssSelector(".product-info__inner-bottom > div:nth-child(2) > button");
	public static By CHECKOUT_BUTTON = By.cssSelector(".inline-cart__action-btns > a:nth-child(3)");
	public static By COLOR_AND_SIZE = By.cssSelector("div.product-info__variant-select > div:nth-child(1) > div.product-info__header.hide > span");
	
	
	
	

	
	
}
