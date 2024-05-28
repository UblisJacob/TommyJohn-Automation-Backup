package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;


public class CheckoutPageLocators {

	//	                                       INFO
	public static By LIST_OF_ITEM_IN_PAYMENT_PAGE = By.cssSelector(".product-table>tbody>tr.product");
	public static By CART_LINK = By.cssSelector(".breadcrumb > li > a");
	public static By EXPRESS_CHECKOUT_TEXT = By.xpath("//h2[@class='aHlIb']");
	public static By PAYPAL_BUTTON = By.cssSelector(".paypal-button");
	public static By GPAY_BUTTON = By.xpath("//div[@data-testid = 'GooglePay-button']");
	public static By AMZ_PAY = By.xpath("//form[@action = 'https://payments.amazon.com/checkout/signin'] /button" );
	public static By SHOP_PAY = By.xpath("//div[@data-testid='ShopifyPay-button']" );
	public static By BREADCRUMB_LINK_CART = By.xpath("//a[contains(text(),'Cart')]");
	public static By ShipAndPaymentPage_BREADCRUMB_LINK_INFORMATION = By.xpath("//a[contains(text(),'Information')]");
	public static By BREADCRUMB_LINK_INFORMATION = By.xpath("//span[contains(text(),'Information')]");
	public static By BREADCRUMB_LINK_SHIPPING = By.xpath("(//span[contains(text(),'Shipping')])[1]");
	public static By PaymentPage_BREADCRUMB_LINK_SHIPPING = By.xpath("(//a[contains(text(),'Shipping')])[1]");
	public static By BREADCRUMB_LINK_PAYMENT = By.xpath("//span[contains(text(),'Payment')]");
	
	
	//Contact info (before logging in)
	public static By CONTACTINFO_TEXT = By.cssSelector("h2#checkout-main-header");
	public static By ALREADYHAVEANACCOUNT_TEXT = By.cssSelector(".layout-flex__item > span");
	public static By LOGIN_LINK = By.xpath("//a[contains(text(),'Log in')]");
	public static By CHECKOUT_EMAIL = By.xpath("//input[@id='email']");
	
	//Contact info (after logging in)
	public static By LOGGED_IN_EMAIL = By.cssSelector(".logged-in-customer-information__paragraph span:nth-child(2)");

	//shipping address
	public static By SHIPPINGADD_TEXT = By.xpath("//h2[contains(text(),'Shipping address')]");
	public static By FIRSTNAME_TEXTBOX = By.xpath("(//input[@name='firstName'])[1]");
	public static By LASTNAME_TEXTBOX = By.xpath("(//input[@name='lastName'])[1]");
	public static By ADDRESS1_TEXTBOX = By.xpath("//input[@id='shipping-address1']");
	public static By ADDRESS2_TEXTBOX = By.xpath("//input[@id='TextField2']");
	public static By CITY_TEXTBOX = By.xpath("//input[@id='TextField3']");
	public static By COUNTRY_DROPDOWN = By.xpath("//select[@name='countryCode']");
	public static By STATE_DROPDOWN = By.xpath("//select[@name='zone']");
	public static By PINCODE_TEXTBOX = By.xpath("//input[@id='TextField4']");
	public static By PHONE_TEXTBOX = By.xpath("(//input[@name='phone'])[1]");
	public static By REMEMBERME_CHCKBOX = By.cssSelector("#checkout_remember_me");
	public static By RETURN_TO_CART_LINK = By.xpath("//a[@class='QT4by eVFmT j6D1f janiy adBMs EP07D']");
	public static By CONTINUE_TO_SHIPPING_BUTTON = By.xpath("//span[contains(text(),'Continue to shipping')]");


	//SideBar
	public static By ORDER_SUMMARY_SECTION = By.xpath("(//section[@class='_1fragem2i _1fragemnw'])[4]");
	//public static By DISCOUNT_CODE_TEXTBOX = By.cssSelector("#checkout_reduction_code");
	public static By DISCOUNT_CODE_TEXTBOX = By.xpath("//input[@id='ReductionsInput0']");
	//public static By APPLY_DISCOUNT_BUTTON = By.cssSelector(".field__input-btn");
	public static By APPLY_DISCOUNT_BUTTON = By.xpath("(//button[@type='submit'])[3]");
	public static By SUBTOTAL = By.xpath("(//div[@class='_1qy6ue68']//span)[1]");
	public static By SHIPPING = By.xpath("(//div[@class='_1qy6ue68']//span)[2]");
	public static By APPLIED_PROMO_CODE = By.xpath("//span[@class='i8os0m3']//span");
	public static By CANCEL_PROMO_CODE = By.xpath("(//span[@class='i8os0m3']//following::button)[1]");
//	public static By CANCEL_PROMO_CODE = By.cssSelector("button.tag__button use");
	public static By TOTAL = By.xpath("//div[@class='_1x41w3p8']");

	//                                          Shipping

	public static By CONTACT_EMAIL =  By.xpath("//div[@class='nkp8r']//bdo");
	public static By SHIP_ADDRESS =  By.xpath("//div[@class='nkp8r']//address");
	public static By CHANGE_LINK_CONTACT = By.xpath("(//span[contains(text(),'Change')])[1]");
	public static By CHANGE_LINK_SHIP_TO = By.xpath("(//span[contains(text(),'Change')])[2]");
	public static By CHANGE_LINK_METHOD = By.xpath("(//span[contains(text(),'Change')])[3]");
	public static By SHIPPING_TEXT = By.cssSelector("h2#checkout-main-header");
	public static By SHIPPING_RADIO_BUTTON = By.xpath("//input[@type='radio']");
	public static By RETURN_LINK = By.xpath("(//span[@class='AjwsM'])[2]");
	public static By CONTINUE_TO_PAYMENT_BUTTON = By.xpath("//div[@class='qjCTh']//button");


	//                                           payment

	public static By METHOD =  By.xpath("//div[@class='nkp8r']//p");
	public static By PAYMENT_TEXT = By.cssSelector("#main-header");
	public static By CREDITCARD_OPTION = By.xpath("//span[contains(text(),'Credit card')]");
	public static By PAYPAL_OPTION = By.xpath("//span[contains(text(),'PayPal')]");

	public static By BILLING_TEXT = By.xpath("//h2[contains(text(),'Billing address')]");
	public static By COMPLETE_ORDER_BUTTON = By.xpath("//div[@class='qjCTh']//button");
	public static By HOWHEARD_TITLE = By.cssSelector("[data-tj-qa='how-heard-title'],.section__title.how_new_title");
	public static By HOWHEARD_SUBTITLE = By.cssSelector("[data-tj-qa='how-heard-subtitle'],.section__title.how_new_subtitle");
	public static By HOWHEARD_DROPDOWN = By.cssSelector("[data-tj-qa='how-heard-dropdown'],.make_selection_select");
	public static By ORDER_NUMBER = By.cssSelector(".os-order-number");



	//	AfterPay
	public static By AFTERPAY_OPTION = By.cssSelector("#checkout_payment_gateway_70492782637");
	//public static By AFTERPAYTEXT_WITH_INSTALLMENT = By.cssSelector("div.radio__label > label > div:nth-child(2)>p:nth-child(1)");
	//public static By AVAILABLE_BETWEEN_TEXT = By.cssSelector("div.section__content > div:nth-child(2) > fieldset > div:nth-child(9) > p > span.afterpay-text2");
	public static By AFTERPAY_INSTALLMENTS = By.cssSelector("#checkout_payment_gateway_70492782637");
	//public static By AFTERPAYLOGO_WITH_AVAILABLE = By.cssSelector("div.section__content > div:nth-child(2) > fieldset > div:nth-child(9) > p >.afterpay-link");
	public static By AFTERPAYLOGO_WITH_INSTALLMENT = By.cssSelector(".payment-icon.payment-icon--afterpay");			


}
