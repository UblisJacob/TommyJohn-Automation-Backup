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
	public static By ShipAndPaymentPage_BREADCRUMB_LINK_INFORMATION = By.xpath("(//nav[@class='_1fragem1y _1fragemlo']//div)[2]");
	public static By BREADCRUMB_LINK_INFORMATION = By.xpath("(//nav[@aria-label='Breadcrumb']//div)[3]");
	public static By BREADCRUMB_LINK_SHIPPING = By.xpath("(//nav[@aria-label='Breadcrumb']//div)[4]");
	public static By Informationpage_LINK_SHIPPING = By.xpath("//li[@class='_5uqybw0 _1fragemlo _1fragem28 _1fragem78']//span[contains(text(),'Shipping')]");
	public static By PaymentPage_BREADCRUMB_LINK_SHIPPING = By.xpath("//li[@class='_5uqybw0 _1fragemlo _1fragem28 _1fragem78']//a[contains(text(),'Shipping')]");
	public static By ShippingPage_BREADCRUMB_LINK_SHIPPING = By.xpath("//li[@class='_5uqybw0 _1fragemlo _1fragem28 _1fragem78']//strong[contains(text(),'Shipping')]");
	public static By BREADCRUMB_LINK_PAYMENT = By.xpath("(//nav[@aria-label='Breadcrumb']//div)[5]");
	public static By PaymentPage_BREADCRUMB_LINK_PAYMENT = By.xpath("//li[@class='_5uqybw0 _1fragemlo _1fragem28 _1fragem78']//strong[contains(text(),'Payment')]");
	
	
	//Contact info (before logging in)
	public static By CONTACTINFO_TEXT = By.cssSelector("h2#checkout-main-header");
	public static By ALREADYHAVEANACCOUNT_TEXT = By.cssSelector(".layout-flex__item > span");
	public static By LOGIN_LINK = By.xpath("//a[contains(text(),'Log in')]");
	public static By CHECKOUT_EMAIL = By.xpath("//input[@id='email']");
	
	//Contact info (after logging in)
	public static By LOGGED_IN_EMAIL = By.cssSelector(".logged-in-customer-information__paragraph span:nth-child(2)");

	//shipping address
	public static By SHIPPINGADD_TEXT = By.xpath("//h2[contains(text(),'Shipping address')]");
	public static By EMAIL_TEXTBOX = By.xpath("//input[@name='email']");
	public static By FIRSTNAME_TEXTBOX = By.xpath("(//input[@name='firstName'])[1]");
	public static By LASTNAME_TEXTBOX = By.xpath("(//input[@name='lastName'])[1]");
	public static By ADDRESS1_TEXTBOX = By.xpath("//input[@id='shipping-address1']");
	public static By ADDRESS1_TEXTBOX_PAYMENTPAGE = By.xpath("//input[@id='billing-address1']");
	public static By ADDRESS2_TEXTBOX = By.xpath("(//input[@name='address2'])[1]");
	public static By CITY_TEXTBOX = By.xpath("(//input[@name='city'])[1]");
	public static By COUNTRY_DROPDOWN = By.xpath("//select[@name='countryCode']");
	public static By STATE_DROPDOWN = By.xpath("//select[@name='zone']");
	public static By PINCODE_TEXTBOX = By.xpath("(//input[@name='postalCode'])[1]");
	public static By PHONE_TEXTBOX = By.xpath("(//input[@name='phone'])[1]");
	public static By REMEMBERME_CHCKBOX = By.cssSelector("#checkout_remember_me");
	public static By RETURN_TO_CART_LINK = By.xpath("//span[contains(text(),'Return to cart')]");
	public static By CONTINUE_TO_SHIPPING_BUTTON = By.xpath("(//button[@type='submit'])[3]");
	public static By TEXTMEWITHNEWSLETTERSANDOFFERSCHECKBOX = By.xpath("//input[@name='sms_marketing_opt_in']");
	public static By PHONENUMBERFIELD = By.xpath("(//input[@name='phone'])[1]");
	public static By ADDRESS_SUGGESTIONS = By.xpath("(//span[@class='a8x1wu2 a8x1wu1 _1fragemod _1fragem1t _1fragemkk _1fragemka a8x1wu9 a8x1wuj a8x1wuh _1fragem1y a8x1wun a8x1wul a8x1wuy'])[5]");

	//Error Messages in InformationPage
	public static By EMAIL_ERROR = By.xpath("//p[@id='error-for-email']");
	public static By FIRSTNAME_ERROR = By.xpath("//p[contains(text(),'Enter a first name')]");
	public static By LASTNAME_ERROR = By.xpath("//p[contains(text(),'Enter a last name')]");
	public static By ADDRESS_ERROR = By.xpath("//p[contains(text(),'Enter an address')]");
	public static By CITY_ERROR  = By.xpath("//p[contains(text(),'Enter a city')]");
	public static By ZIPCODE_ERROR  = By.xpath("//p[contains(text(),'Enter a ZIP / postal code')]");


	//SideBar
	public static By ORDER_SUMMARY_SECTION = By.xpath("//div[@class='_4QenE']");
	//public static By DISCOUNT_CODE_TEXTBOX = By.cssSelector("#checkout_reduction_code");
	public static By DISCOUNT_CODE_TEXTBOX = By.xpath("(//input[@name='reductions'])[2]");
	//public static By APPLY_DISCOUNT_BUTTON = By.cssSelector(".field__input-btn");
	public static By APPLY_DISCOUNT_BUTTON = By.xpath("(//button[@type='submit'])[5]");
	public static By SUBTOTAL = By.xpath("(//span[contains(text(),'Subtotal')])[2]");
	public static By SHIPPING = By.xpath("(//span[contains(text(),'Shipping')])[3]");
	public static By APPLIED_PROMO_CODE = By.xpath("(//span[@class='i8os0m3']//span)[2]");
	public static By INCORRECT_PROMO_CODE_ERROR_MESSAGE = By.xpath("//p[@id='error-for-ReductionsInput0']");
	public static By CANCEL_PROMO_CODE = By.xpath("(//button[@aria-label='Remove FIRST20'])[2]");
	public static By CANCEL_PROMO_CODE1 = By.xpath("(//button[@aria-label='Remove TECHTESTORDER'])[2]");
//	public static By CANCEL_PROMO_CODE = By.cssSelector("button.tag__button use");
	public static By TOTAL = By.xpath("(//strong[contains(text(),'Total')])[2]");
	public static By INFO_DISCOUNT_3for$48 = By.xpath("(//p[contains(text(),'3 FOR $48 (-$6.00)')])[4]");
	public static By INFO_DISCOUNT_3for$48_1 = By.xpath("(//p[contains(text(),'3 FOR $48 (-$2.00)')])[4]");
	public static By LINEITEM_TOTAL_3for$48 = By.xpath("(//p[contains(text(),'$16.00')])[4]");

	public static By SHIPPINGPAGE_LINEITEM_TOTAL_3for$48 = By.xpath("(//p[@class='_1x52f9s1 _1x52f9s0 _1fragemlj _1x52f9sv _1x52f9su _1fragemnw'])[12]");
	public static By PAYMENTPAGE_LINEITEM_TOTAL_3for$48 = By.xpath("(//p[@class='_1x52f9s1 _1x52f9s0 _1fragemlj _1x52f9sv _1x52f9su _1fragemnw'])[9]");

	//                                          Shipping

	public static By CONTACT_EMAIL =  By.xpath("//span[contains(text(),'Contact')]//following::div//bdo");
	public static By SHIP_ADDRESS =  By.xpath("//span[contains(text(),'Ship to')]//following::div//address");
	public static By CHANGE_LINK_CONTACT = By.xpath("(//span[contains(text(),'Change')])[2]");
	public static By CHANGE_LINK_SHIP_TO = By.xpath("(//span[contains(text(),'Change')])[3]");
	public static By CHANGE_LINK_METHOD = By.xpath("(//span[contains(text(),'Change')])[4]");
	public static By SHIPPING_TEXT = By.cssSelector("h2#checkout-main-header");
	public static By SHIPPING_RADIO_BUTTON = By.xpath("//input[@type='radio']");
	public static By RETURN_LINK = By.xpath("//span[contains(text(),'Return to information')]");
	public static By CONTINUE_TO_PAYMENT_BUTTON = By.xpath("//div[@class='MV9Am']//button");
	public static By FREE_SHIPPING = By.xpath("(//span[contains(text(),'Free')])[2]");


	//                                           payment

	public static By METHOD =  By.xpath("(//span[contains(text(),'Shipping method')]//following::p)[1]");
	public static By PAYMENT_TEXT = By.cssSelector("#main-header");
	public static By CREDITCARD_OPTION = By.xpath("//span[contains(text(),'Credit card')]");
	public static By PAYPAL_OPTION = By.xpath("//span[contains(text(),'PayPal')]");

	public static By USEDIFFERENTBILLINGADDRESS = By.xpath("//input[@id='billing_address_selector-custom']");

	public static By COUNTRYREGIONFIELD = By.xpath("//select[@name='countryCode']");
	public static By FIRSTNAME = By.xpath("(//input[@name='firstName'])[1]");
	public static By LASTNAME = By.xpath("(//input[@name='lastName'])[1]");
	public static By ADDRESS = By.xpath("(//input[@name='address1'])[1]");
	public static By CITY = By.xpath("(//input[@name='city'])[1]");
	public static By STATE = By.xpath("//select[@name='zone']");
	public static By ZIPCODE = By.xpath("(//input[@name='postalCode'])[1]");

	public static By PHONE = By.xpath("(//input[@name='phone'])[1]");
	public static By SAVE_MY_INFORMATION = By.xpath("//input[@id='RememberMe-RememberMeCheckbox']");

	public static By BILLING_TEXT = By.xpath("//h2[contains(text(),'Billing address')]");
	public static By COMPLETE_ORDER_BUTTON = By.xpath("//div[@class='MV9Am']//button");
	public static By HOWHEARD_TITLE = By.cssSelector("[data-tj-qa='how-heard-title'],.section__title.how_new_title");
	public static By HOWHEARD_SUBTITLE = By.cssSelector("[data-tj-qa='how-heard-subtitle'],.section__title.how_new_subtitle");
	public static By HOWHEARD_DROPDOWN = By.cssSelector("[data-tj-qa='how-heard-dropdown'],.make_selection_select");
	public static By ORDER_NUMBER = By.cssSelector(".os-order-number");

	//CreditCardFields
	public static By CARDNUMBER = By.xpath("(//label[contains(text(),'Card number')]//following::input)[1]");
	public static By CARDNAME_CLEAR =  By.xpath("//button[@aria-label='Clear']");
	public static By EXPIRY =  By.xpath("//input[@data-current-field='expiry']");
	public static By SECURITYCODE =  By.xpath("//input[@data-current-field='verification_value']");



	//CardFieldErrors
	public static By CARDNUMBER_ERROR = By.xpath("//p[@id='error-for-number']");
	public static By CARDNAME_ERROR = By.xpath("//p[@id='error-for-name']");
	public static By EXPIRY_ERROR = By.xpath("//p[@id='error-for-expiry']");
	public static By SECURITYCODE_ERROR = By.xpath("//p[@id='error-for-verification_value']");



	//	AfterPay
	public static By AFTERPAY_OPTION = By.cssSelector("#checkout_payment_gateway_70492782637");
	//public static By AFTERPAYTEXT_WITH_INSTALLMENT = By.cssSelector("div.radio__label > label > div:nth-child(2)>p:nth-child(1)");
	//public static By AVAILABLE_BETWEEN_TEXT = By.cssSelector("div.section__content > div:nth-child(2) > fieldset > div:nth-child(9) > p > span.afterpay-text2");
	public static By AFTERPAY_INSTALLMENTS = By.cssSelector("#checkout_payment_gateway_70492782637");
	//public static By AFTERPAYLOGO_WITH_AVAILABLE = By.cssSelector("div.section__content > div:nth-child(2) > fieldset > div:nth-child(9) > p >.afterpay-link");
	public static By AFTERPAYLOGO_WITH_INSTALLMENT = By.cssSelector(".payment-icon.payment-icon--afterpay");			


}
