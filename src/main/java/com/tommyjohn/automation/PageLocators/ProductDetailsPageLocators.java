package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ProductDetailsPageLocators {

	public static By FIRST_PRODUCT_REVIEW_STARS = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > a > div");

	public static By PRODUCT_TITLE = By.cssSelector(".product-info__inner-top > div > .product-info__title-wrap > .product-info__title");
//	public static By PRODUCT_PRICE = By.cssSelector("div.product-info__inner-top > span > span:nth-child(2)");
	public static By PRODUCT_PRICE = By.cssSelector("div.multi-pdp-title-wrap.desktop-view > div.product-info__price-wrap >span > span:nth-child(2)");
//	public static By RATING_STARS = By.cssSelector("div.yotpo-bottomline.star-clickable");
	//public static By RATING_STARS = By.cssSelector("div.yotpo-bottomline > span");
		public static By RATING_STARS = By.cssSelector("div.multi-pdp-title-wrap.desktop-view > div.product-info__price-wrap > div.yotpo-star-wrapper > div > div > span");

	public static By RATING_NUMBER = By.cssSelector("div.multi-pdp-title-wrap.desktop-view > div.product-info__price-wrap > div.yotpo-star-wrapper > div > div > a");
	public static By RATING_AND_REVIEWS_BOX = By.cssSelector(".yotpo-regular-box.yotpo-bottomline.yotpo-bottomline-3-boxes");
	public static By FIRST_BOX_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-bottomline-box-1.yotpo-stars-and-sum-reviews");
	public static By STAR_RATINGS = By.cssSelector(".yotpo-stars-and-sum-reviews > span > span.sr-only");
	public static By BE_THE_FIRST_TO_WRITE_REVIEW = By.cssSelector(".yotpo-first-review-content > input");
	public static By WRITE_REVIEW_BUTTON = By.cssSelector(".yotpo-label.yotpo-small-box > div:nth-child(2)");
	public static By WRITE_REVIEW_HEADING = By.cssSelector("#pdpReviews > div > div > form > div");
	public static By WRITE_REVIEW_HEADING1 = By.cssSelector(".y-label.yotpo-header-title");
	public static By REVIEW_STARS_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-bottomline-box-1 > span:nth-child(1)");
	public static By COLOR_LISTS = By.cssSelector(".product-option__color-swatches-wrapper > ul >li");
	public static By FIRST_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(1) > label");
	public static By SECOND_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(2) > label");
	public static By THIRD_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(3) > label");
	public static By COLOR_TEXT = By.xpath("//div[@class= 'multi-pdp-info__header'] /span[text()]");
	//public static By SIZE_TEXT = By.cssSelector(".product-info__header > span:nth-child(2)");
	
	public static By SIZE_DROPDOWN = By.cssSelector(".option-dropdown__option >div>a");
	public static By ALL_SIZE_LIST = By.cssSelector(".select--options__list >li");
	
	public static By SIZE_TEXT = By.cssSelector(".select--options__label");
	//public static By ALL_SIZE_LIST = By.cssSelector(".product-option__variants.product-option__variants-size > ul > li");
	
	
	public static By ALL_COLOR_LIST = By.cssSelector(".product-option__color-swatches-wrapper > ul >li");
	public static By PRODUCT_DETAILS = By.cssSelector("div.product-accordion.container> ul > li:nth-child(1) > div:nth-child(1) > a");
	public static By SIZEGUIDE = By.cssSelector(".product-info__header--desktop > button");
	public static By SIZE_GUIDE_BUTTON = By.cssSelector(".product-details__controls--inner > button:nth-child(2)");
	public static By ADD_TO_CART_BUTTON = By.cssSelector("button.product-info__atc.button.button--red.button--full.js-loop-atc");
	public static By JOIN_THE_WAITLIST = By.cssSelector("button.product-info__atc.button.button--red.button--full.js-loop-atc.email-when-in-stock");
	//public static By QUANTITY = By.cssSelector(".qty-selector.desktop>#updatedQty.pdp.prodQty");
	public static By QUANTITY = By.cssSelector(".product-info__atc");
	public static By PLUS_BUTTON = By.cssSelector(".pdp.plusBtn");
	public static By UPDATED_QUANTITY = By.cssSelector(".pdp.updatedQty");
	public static By MINUS_BUTTON = By.cssSelector(".pdp.minusBtn");
	public static By PAIR_GUARANTEE = By.cssSelector("div.product-accordion.container> ul > li:nth-child(2) > div:nth-child(1) > a");
	public static By SHIPING_AND_RETURNS = By.cssSelector("div.product-accordion.container> ul > li:nth-child(3) > div:nth-child(1) > a");
	public static By CHECKOUT_BUTTON = By.cssSelector(".inline-cart__action-btns > a");
	//public static By COLOR_AND_SIZE = By.cssSelector("div.product-info__variant-select > div:nth-child(1) > div.product-info__header.hide > span");
	
	//afterpay
	
	public static By AFTERPAY = By.cssSelector(".afterpay-text1");
	public static By AFTERPAY_TEXT = By.cssSelector(".afterpay-text1");
	public static By AFTERPAY_LOGO = By.cssSelector(".afterpay-pdp-container > p > span > .afterpay-logo");
	
	
	
	

	
	
}
