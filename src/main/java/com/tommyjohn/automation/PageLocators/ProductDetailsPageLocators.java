package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ProductDetailsPageLocators {

	public static By FIRST_PRODUCT_REVIEW_STARS = By.cssSelector("div.mega-collection-grid__row:nth-child(3) > div:nth-child(3) > a > div");

	public static By PRODUCT_TITLE = By.cssSelector(".product-info__inner-top > div > .product-info__title-wrap > .product-info__title");
	//	public static By PRODUCT_PRICE = By.cssSelector("div.product-info__inner-top > span > span:nth-child(2)");
	public static By PRODUCT_PRICE = By.cssSelector("div.multi-pdp-title-wrap.desktop-view > div.product-info__price-wrap >span > span:nth-child(2)");


	public static By AFTERPAY_DIV = By.cssSelector(".multi-pdp-title-wrap.desktop-view>span>.afterpay-paragraph");


	public static By COLOR_LISTS = By.cssSelector(".product-option__color-swatches-wrapper > ul >li");
	public static By FIRST_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(1) > label");
	public static By SECOND_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(2) > label");
	public static By THIRD_COLOR = By.cssSelector(".product-option__color-swatches-wrapper > ul > li:nth-child(3) > label");
	public static By COLOR_TEXT = By.xpath("//div[@title = 'Color'] /span");       //("//div[@class= 'multi-pdp-info__header'] /span]");
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

	public static By AFTERPAY_PDP = By.cssSelector("div.desktop-view > .afterpay-pdp-container > p > span");
	public static By AFTERPAY_PDP_AFTERMESSAGE_LOGO = By.cssSelector("div.desktop-view > .afterpay-pdp-container > p > span.afterpay-logo-link");
	public static By AFTERPAY_PDP_BEFOREMESSAGE_LOGO = By.cssSelector("div.desktop-view > .afterpay-pdp-container > p> img ");



	//Reviews section


	public static By RATING_STARS = By.cssSelector("div.multi-pdp-title-wrap.desktop-view > div.product-info__price-wrap > div.yotpo-star-wrapper > div > div > span.yotpo-stars");
	public static By RATING_NUMBER = By.cssSelector("div.multi-pdp-title-wrap.desktop-view > div.product-info__price-wrap > div.yotpo-star-wrapper > div > div > a");
	public static By RATING_AND_REVIEWS_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container ");
	
	public static By FIRST_BOX_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-rating");
	public static By STARS_IN_FIRST_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-rating > .yotpo-stars");
	public static By AVG_RATING_IN_FIRST_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-rating > p> .yotpo-review-bottomline-average_rating");
	public static By TOTAL_REVIEWS_IN_FIRST_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-rating > p> .yotpo-review-bottomline-total_reviews");

	public static By SECOND_BOX_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2) > ul.yotpo-revew-bottomline-list");
	public static By RATING_LABEL_IN_SECOND_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2) > ul.yotpo-revew-bottomline-list > li > .yotpo-review-bottomline-individual-ratings > .yotpo-review-bottomline-ratings-label");
	public static By RATING_METER_IN_SECOND_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2) > ul.yotpo-revew-bottomline-list > li:nth-child(3) > .yotpo-review-bottomline-individual-ratings > .yotpo-review-bottomline-individual-ratings__meter");
	public static By RATING_COUNT_IN_SECOND_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div:nth-child(2) > ul.yotpo-revew-bottomline-list > li:nth-child(5) > .yotpo-review-bottomline-individual-ratings > .yotpo-review-bottomline-individual-ratings__count");
	
	public static By THIRD_BOX_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-customer-say-container");
	public static By FIT_GRAPH_IN_THIRD_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-customer-say-container > .size-guide-yatpo-fit-graph > ul > li.selected");
	public static By FIT_STATUS_IN_THIRD_BOX = By.cssSelector(".yotpo-reviews-widget >div> div.yotpo-review-bottomline-container >div > div.yotpo-review-bottomline-customer-say-container > .yotpo-review-bottomline-customer-say > .yotpo-review-bottomline-fit");
	
	public static By STAR_RATINGS = By.cssSelector(".yotpo-stars-and-sum-reviews > span > span.sr-only");
	
	public static By WRITE_REVIEW_BUTTON = By.cssSelector(".write-review-button");
	public static By WRITE_REVIEW_HEADING = By.cssSelector("#pdpReviews > div > div > form > div");
	public static By WRITE_REVIEW_HEADING1 = By.cssSelector(".y-label.yotpo-header-title");
	public static By REVIEW_STARS_IN_RATING_AND_REVIEWS = By.cssSelector(".yotpo-bottomline-box-1 > span:nth-child(1)");
	public static By REVIEWS_TITLE = By.cssSelector(".review-title");
	
	public static By REVIEWER_NAME = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > .reviewer-name-type > .reviewer-user-name");
	public static By REVIEWER_TYPE = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > .reviewer-name-type > .reviewer-user-type");
	public static By USER_REVIEW_FIT = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > p:nth-child(4)");
	public static By USER_REVIEW_DATE = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > p:nth-child(2)");
	public static By USER_REVIEW_LOCATION = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > div.yotpo-review-buyer-info > p:nth-child(3)");
	
	public static By USER_REVIEW_STARS = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review-header> p>.yotpo-review-stars");
	public static By USER_REVIEW_TITLE = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review-header >.yotpo-review-title");
	public static By USER_REVIEW_MAIN_CONTENT = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review-content");
	public static By USER_REVIEW_WAS_THIS_HELPFUL_TEXT = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review__feedback-wrapper > div > .yotpo-review-feedback-text");
	public static By USER_REVIEW_THUMBS_UP = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review__feedback-wrapper > div > .yotpo-icon-vote-btn > .yotpo-icon-thumbs-up");
	public static By USER_REVIEW_THUMBS_DOWN = By.cssSelector(".yotpo-reviews-widget > ul.review-list > li.review-item> .yotpo-review-box > .yotpo-review-main-content >.yotpo-review__feedback-wrapper > div > .yotpo-icon-vote-btn > .yotpo-icon-thumbs-down");
	
	public static By NO_REVIEWS_TEXT = By.cssSelector(".yotpo-no-reviews-container > .yotpo-header");
	public static By NO_REVIEWS_SUBTEXT = By.cssSelector(".yotpo-no-reviews-container > .yotpo-subtext");
	public static By NO_REVIEW_WRITE_REVIEW = By.cssSelector("div.yotpo-no-reviews-container > div.no-review-write-button");
	
	public static By WRITE_REVIEW_TITLE = By.cssSelector(".yotpo_write-review-header > h3");
	public static By NAME_TEXTBOX = By.cssSelector("#yotpo_input_review_name");
	public static By EMAIL_TEXTBOX = By.cssSelector("#yotpo_input_review_email");
	public static By LOCATION_TEXTBOX = By.cssSelector("#yotpo_input_review_location");
	public static By FIT_SELECTION = By.cssSelector("label[for='yotpo_input__field_option_3']");
	public static By STAR_SELECTION = By.cssSelector("label[for='yotpo-form-input__review_score__4']");
	public static By HEADLINE_TEXTBOX = By.cssSelector("#yotpo_input_review_headline");
	public static By REVIEW_TEXTBOX = By.cssSelector("#yotpo_input_review_content");
	public static By SUBMIT_BTN = By.cssSelector(".yotpo-submit");
	
	public static By NAME = By.xpath("//label[@for = 'yotpo_input_review_name']");
	public static By EMAIL = By.xpath("//label[@for = 'yotpo_input_review_email']");
	public static By LOCATION = By.xpath("//label[@for = 'yotpo_input_review_location']");
	public static By FIT = By.cssSelector(".yotpo-custom-tag-field > span");
	public static By HEADLINE = By.xpath("//label[@for = 'yotpo_input_review_headline']");
	public static By REVIEW = By.xpath("//label[@for = 'yotpo_input_review_content']");
	public static By OVERALLRATING = By.cssSelector(".overall-rating-text");
	
	public static By NAME_ERROR = By.cssSelector("input#yotpo_input_review_name  ~ span.error-message");
	public static By EMAIL_ERROR = By.cssSelector("input#yotpo_input_review_email  ~ span.error-message");
	public static By HEADLINE_ERROR = By.cssSelector("input#yotpo_input_review_headline  ~ span.error-message");
	public static By REVIEW_ERROR = By.cssSelector("#yotpo_input_review_content  ~ span.error-message");
	
	
	
	
	
}
