package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class GiftCardBalancePageLocators {
	
	public static By GIFT_CARD_BALANCE_HEADER = By.cssSelector(".balance-wrapper>h2");
	
//	PHYSICAL GIFT CARD FORM
	public static By PHYSICAL_GIFT_CARD_FORM = By.cssSelector("div[class='side-by-side']>.form:nth-child(1)");
	public static By PHYSICAL_GIFT_CARD_FORM_LABEL = By.cssSelector("div[class='side-by-side']>.form:nth-child(1)>.label");
	public static By PHYSICAL_GIFT_CARD_ID_LABEL = By.cssSelector("div[for='card_number-desktop']");
	public static By PHYSICAL_GIFT_CARD_ID_FIELD= By.cssSelector("#card_number-desktop");
	public static By PHYSICAL_GIFT_CARD_PIN_LABEL = By.cssSelector("div[for='pin-desktop']");
	public static By PHYSICAL_GIFT_CARD_PIN_FIELD = By.cssSelector("#pin-desktop");
	public static By PHYSICAL_GIFT_CARD_EMAIL_LABEL = By.cssSelector("div[for='email-desktop']");
	public static By PHYSICAL_GIFT_CARD_EMAIL_FIELD = By.cssSelector("#email-desktop");
	public static By PHYSICAL_GIFT_CARD_CHECK_BALANCE_BTN = By.cssSelector("div[class='side-by-side']>.form:nth-child(1) .button");
	public static By PHYSICAL_GIFT_CART_FORM_ERROR = By.cssSelector("div[class='form'] .response-error");

//	DIGITAL/E-GIFT CARD
	public static By DIGITAL_GIFT_CARD_FORM = By.cssSelector("div[class='side-by-side']>.form:nth-child(2)");
	public static By DIGITAL_GIFT_CARD_FORM_LABEL = By.cssSelector("div[class='side-by-side']>.form:nth-child(2)>.label");
	public static By DIGITAL_GIFT_CARD_ID_LABEL = By.cssSelector("div[for='giftcard_code']");
	public static By DIGITAL_GIFT_CARD_ID_FIELD = By.cssSelector("#giftcard_code");
	public static By DIGITAL_GIFT_CARD_EMAIL_LABEL = By.cssSelector("div[for='email']");
	public static By DIGITAL_GIFT_CARD_EMAIL_FIELD = By.cssSelector("#email");
	public static By DIGITAL_GIFT_CARD_CHECK_BALANCE_BTN = By.cssSelector("div[class='side-by-side']>.form:nth-child(2) .button");
	public static By DIGITAL_GIFT_CART_FORM_ERROR = By.cssSelector("div[class='form'] .response-error");
	public static By DIGITAL_GIFT_CART_CODE_ERROR = By.cssSelector("#giftcard_code_error");
	public static By DIGITAL_GIFT_CART_EMAIL_ERROR = By.cssSelector("#email_error");

//	BALANCE PAGE
	public static By BALANCE_PAGE_HEADER = By.cssSelector(".shopify-section .title");
	public static By BACK_TO_GIFT_CARD_BALANCE = By.cssSelector(".shopify-section .link");
	public static By BALANCE_PAGE_CARD_NUMBER_LABEL = By.cssSelector("div[class='my-card-response']>.my-card-response-line:nth-child(1)>h4");
	public static By BALANCE_PAGE_CARD_NUMBER_DATA = By.cssSelector("div[class='my-card-response']>.my-card-response-line:nth-child(1)>span");
	public static By BALANCE_PAGE_ACTIVATION_DATE_LABEL = By.cssSelector("div[class='my-card-response']>.my-card-response-line:nth-child(2)>h4");
	public static By BALANCE_PAGE_ACTIVATION_DATE_DATA = By.cssSelector("div[class='my-card-response']>.my-card-response-line:nth-child(2)>span");
	public static By BALANCE_PAGE_STARTING_BALANCE_LABEL = By.cssSelector("div[class='my-card-response']>.my-card-response-line:nth-child(3)>h4");
	public static By BALANCE_PAGE_STARTING_BALANCE_DATA = By.cssSelector("div[class='my-card-response']>.my-card-response-line:nth-child(3)>span");
	public static By BALANCE_PAGE_REMAINING_BALANCE_LABEL = By.cssSelector("div[class='my-card-response']>.my-card-response-line:nth-child(4)>h4");
	public static By BALANCE_PAGE_REMAINING_BALANCE_DATA = By.cssSelector("div[class='my-card-response']>.my-card-response-line:nth-child(4)>span");
	public static By BALANCE_PAGE_START_SHOPPING_CTA_BTN = By.cssSelector("div[class='my-card-response-wrapper']+.button");
	public static By BALANCE_PAGE_START_SHOPPING_CTA_TEXT = By.cssSelector("div[class='my-card-response-wrapper']+.button>span");
}
