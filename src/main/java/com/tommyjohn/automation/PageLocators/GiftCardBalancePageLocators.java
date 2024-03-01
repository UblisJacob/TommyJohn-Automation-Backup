package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class GiftCardBalancePageLocators {
	
	public static By GIFT_CARD_BALANCE_HEADER = By.cssSelector(".balance-wrapper>h2");
	
//	PHYSICAL GIFT CARD FORM
	public static By PHYSICAL_GIFT_CARD_FORM = By.cssSelector("div[class='side-by-side']>.form:nth-child(1)");
	public static By PHYSICAL_GIFT_CARD_FORM_LABEL = By.cssSelector("[data-tj-qa='pgc-heading'],div[class='side-by-side']>.form:nth-child(1)>.label");
	public static By PHYSICAL_GIFT_CARD_ID_LABEL = By.cssSelector("[data-tj-qa='desktop-card-number']>div,div[for='card_number-desktop']");
	public static By PHYSICAL_GIFT_CARD_ID_FIELD= By.cssSelector("[data-tj-qa='desktop-card-number']>input,#card_number-desktop");
	public static By PHYSICAL_GIFT_CARD_PIN_LABEL = By.cssSelector("[data-tj-qa='desktop-card-pin']>div,div[for='pin-desktop']");
	public static By PHYSICAL_GIFT_CARD_PIN_FIELD = By.cssSelector("[data-tj-qa='desktop-card-pin']>input,#pin-desktop");
	public static By PHYSICAL_GIFT_CARD_EMAIL_LABEL = By.cssSelector("[data-tj-qa='desktop-cardholder-email']>div,div[for='email-desktop']");
	public static By PHYSICAL_GIFT_CARD_EMAIL_FIELD = By.cssSelector("[data-tj-qa='desktop-cardholder-email']>input,#email-desktop");
	public static By PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN = By.cssSelector("form#form-aria-labelledby-physicalGiftCard-desktop>[data-tj-qa='pgc-check-balance-button'] , div[class='side-by-side']>.form:nth-child(1) .button");
	public static By PHYSICAL_GIFT_CART_FORM_ERROR = By.cssSelector("[data-tj-qa='card-number-error'],div[class='form'] .response-error");

//	DIGITAL/E-GIFT CARD
	public static By DIGITAL_GIFT_CARD_FORM = By.cssSelector("div[class='side-by-side']>.form:nth-child(2)");
	public static By DIGITAL_GIFT_CARD_FORM_LABEL = By.cssSelector("[data-tj-qa='egc-balance-heading'],div[class='side-by-side']>.form:nth-child(2)>.label");
	public static By DIGITAL_GIFT_CARD_ID_LABEL = By.xpath("(//div[@data-tj-qa='balance-card-number']//div[@for='giftcard_code'])[2]");
	public static By DIGITAL_GIFT_CARD_ID_FIELD = By.xpath("(//input[@id='giftcard_code'])[2]");
	public static By DIGITAL_GIFT_CARD_EMAIL_LABEL = By.xpath("(//div[@data-tj-qa='balance-email']//div[@for='email'])[2]");
	public static By DIGITAL_GIFT_CARD_EMAIL_FIELD = By.xpath("(//input[@id=\"email\"])[2]");
	public static By DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN = By.xpath("(//div[@data-tj-qa='egc-check-balance-button'])[2]");
	public static By DIGITAL_GIFT_CART_FORM_ERROR = By.cssSelector("[data-tj-qa='card-number-error'],div[class='form'] .response-error");
	public static By DIGITAL_GIFT_CART_CODE_ERROR = By.cssSelector("[data-tj-qa='balance-card-number']>span,#giftcard_code_error");
	public static By DIGITAL_GIFT_CART_EMAIL_ERROR = By.cssSelector("[data-tj-qa='balance-email']>span,#email_error");

//	BALANCE PAGE
	public static By BALANCE_PAGE_HEADER = By.cssSelector(".shopify-section .title");
	public static By BACK_TO_GIFT_CARD_BALANCE = By.cssSelector("[data-tj-qa='back-to-gift-card-balance-button'],.shopify-section .link");
	public static By BALANCE_PAGE_CARD_NUMBER_LABEL = By.cssSelector("[data-tj-qa='card-number-text'],div[class='my-card-response']>.my-card-response-line:nth-child(1)>h4");
	public static By BALANCE_PAGE_CARD_NUMBER_DATA = By.cssSelector("[data-tj-qa='card-card-number-value'],div[class='my-card-response']>.my-card-response-line:nth-child(1)>span");
	public static By BALANCE_PAGE_ACTIVATION_DATE_LABEL = By.cssSelector("[data-tj-qa='gift-card-activation-date-text'],div[class='my-card-response']>.my-card-response-line:nth-child(2)>h4");
	public static By BALANCE_PAGE_ACTIVATION_DATE_DATA = By.cssSelector("[data-tj-qa='gift-card-activation-date-value'],div[class='my-card-response']>.my-card-response-line:nth-child(2)>span");
	public static By BALANCE_PAGE_STARTING_BALANCE_LABEL = By.cssSelector("[data-tj-qa='gift-card-starting-balance-text'],div[class='my-card-response']>.my-card-response-line:nth-child(3)>h4");
	public static By BALANCE_PAGE_STARTING_BALANCE_DATA = By.cssSelector("[data-tj-qa='gift-card-starting-balance-value'],div[class='my-card-response']>.my-card-response-line:nth-child(3)>span");
	public static By BALANCE_PAGE_REMAINING_BALANCE_LABEL = By.cssSelector("[data-tj-qa='gift-card-remaining-balance-text'],div[class='my-card-response']>.my-card-response-line:nth-child(4)>h4");
	public static By BALANCE_PAGE_REMAINING_BALANCE_DATA = By.cssSelector("[data-tj-qa='gift-card-remaining-balance-value'],div[class='my-card-response']>.my-card-response-line:nth-child(4)>span");
	public static By BALANCE_PAGE_START_SHOPPING_CTA_BTN = By.cssSelector("[data-tj-qa='balance-check-start-shopping-btn'],div[class='my-card-response-wrapper']+.button");
	public static By BALANCE_PAGE_START_SHOPPING_CTA_TEXT = By.cssSelector("[data-tj-qa='balance-check-start-shopping-btn'],div[class='my-card-response-wrapper']+.button>span");
}
