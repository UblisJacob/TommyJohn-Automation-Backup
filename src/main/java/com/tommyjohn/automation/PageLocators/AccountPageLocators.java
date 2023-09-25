package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class AccountPageLocators 

{
	
//	ACCOUNT PAGE
	public static By ACCOUNT_PAGE_HEADING =By.cssSelector("h1[data-tj-qa='account-login-title'], .account-logged-in-header");
	public static By ACCOUNT_PAGE_HEADING_LOGIN =By.cssSelector("h2[data-tj-qa='account-login-title']");
	public static By SIGN_OUT_LINK = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(5) > a");
	public static By LOGIN_INFORMATION_TITLE = By.cssSelector("div[data-tj-qa='account-login-info'] h2");
//	public static By LOGGED_IN_ID = By.cssSelector(".account-logged-content>div:nth-child(1)>div>div>.account-row-column>p");
	public static By LOGGED_IN_ID = By.cssSelector("div[data-tj-qa='account-login-info'] p");
	public static By ADDRESS_BOOK_TITLE= By.cssSelector(".account-logged-content>div:nth-child(2)>h2, div[data-tj-qa='address-book-title']>h2");
	public static By DEFAULT_SHIPPING_ADDRESS_TITLE= By.cssSelector("h2[data-tj-qa='default-address']");
//	.account-logged-content>div:nth-child(2)>div>div>.account-default-shipping-address
	public static By ADDRESS_CONTENT = By.cssSelector(".account-logged-content>div:nth-child(2)>div>div>p, div[data-tj-qa='address-body']>p");
//	public static By ORDER_HISTORY_TITLE= By.cssSelector(".account-logged-content>div:nth-child(3)>h2");
	public static By ORDER_HISTORY_TITLE= By.cssSelector("h2[data-tj-qa='order-history-title']");
//	public static By ORDER_HISTORY_TABLE_TITLE_HEADING = By.cssSelector(".account-logged-content>div:nth-child(3)>.account-orders-table>table>thead>tr>th");
	public static By ORDER_HISTORY_TABLE_TITLE_HEADING = By.cssSelector("div[data-tj-qa='account-orders-table'] th");
//	public static By FIRST_ORDER_NUMBER= By.cssSelector(".account-logged-content>div:nth-child(3)>.account-orders-table>table>tbody>tr:nth-child(1)>td:nth-child(1)>a");
	public static By FIRST_ORDER_NUMBER= By.cssSelector("div[data-tj-qa='account-orders-table'] tbody>tr:nth-child(1)>td:nth-child(1)");
	public static By ACCOUNT_CREATE_SUCCESS_MSG = By.cssSelector(".new-customer.account-create-success");
	public static By MANAGE_ADDRESSES = By.cssSelector("a[data-tj-qa='account-manage-addresses']");
	public static By MANAGE_ADDRESS_BOOK_HEADING = By.cssSelector("h1[data-tj-qa='address-book-heading']");
	public static By ADDITIONAL_ADDRESSES_TITLE = By.cssSelector("h2[data-tj-qa='additional addresses-title']");
	public static By RETURN_TO_ACCOUNT_PAGE_LINK = By.cssSelector("a[data-tj-qa='return-link']");
	public static By ORDER_NUMBER_HEADING = By.cssSelector("h1[data-tj-qa='account-order-history-order-number']");
	public static By ORDER_DETAIL = By.cssSelector("td[data-tj-qa='account-orders-details']");
	public static By DEFAULT_ADDRESS = By.cssSelector("div[data-tj-qa='default-address-text']");
	public static By ADDITIONAL_ADDRESSES_INFO = By.cssSelector("div[data-tj-qa='additional addresses-info']");
	
//	ORDER HISTORY SECTION
	public static By ORDER_HISTORY_TABLE = By.cssSelector("[data-tj-qa='account-orders-table']");
	public static By ORDER_LINK = By.cssSelector("[data-tj-qa='account-order']");
	
//	ORDER PAGE
	public static By RETURN_TO_ACCOUNT_PAGE = By.cssSelector("[data-tj-qa='account-order-history-returnPage']");
	public static By ORDER_NUMBER_HEADER = By.cssSelector("[data-tj-qa='account-order-history-order-number']");
	public static By ORDER_DATE_HEADER = By.cssSelector("[data-tj-qa='account-order-history-order-date']");
	public static By ORDER_SHIPPING_INFO_HEADER = By.cssSelector("[data-tj-qa='account-order-history-shipping-info-header']");
	public static By ORDER_SHIPPING_INFO_DETAILS = By.cssSelector("[data-tj-qa='account-order-history-shipping-info-details']+p");
	public static By ORDER_SHIPPING_METHOD_HEADER = By.cssSelector("[data-tj-qa='account-order-history-shipping-method-header']");
	public static By ORDER_SHIPPING_METHOD_DETAILS = By.cssSelector("[data-tj-qa='account-order-history-shippin-method-details']");
	public static By TRACK_ORDER_CTA = By.cssSelector("[data-tj-qa='account-order-history-track-your-order']");
	public static By ITEMS_ORDERED_HEADER = By.cssSelector("[data-tj-qa='account-order-history-items-ordered-header']");
	public static By LINE_ITEM_IMAGE = By.cssSelector("[data-tj-qa='account-order-history-line-item-image']");
	public static By LINE_ITEM_NAME = By.cssSelector("[data-tj-qa='account-order-history-line-item-name']");
	public static By LINE_ITEM_COLOR_SIZE = By.cssSelector("[data-tj-qa='account-order-history-line-item-color-size']");
	public static By LINE_ITEM_QTY = By.cssSelector("[data-tj-qa='account-order-history-line-item-qty']");
	public static By LINE_ITEM_PRICE = By.cssSelector("[data-tj-qa='account-order-history-line-item-price']");
	public static By ORDER_SUBTOTAL_TEXT = By.cssSelector("[data-tj-qa='account-order-history-subtotal-text']");
	public static By ORDER_SUBTOTAL_PRICE = By.cssSelector("[data-tj-qa='account-order-history-subtotal-price']");
	public static By ORDER_SUBTOTAL_DISCOUNT_CODE = By.cssSelector("[data-tj-qa='account-order-history-discount-code']");
	public static By ORDER_SUBTOTAL_DISCOUNT_CODE_PRICE = By.cssSelector("[data-tj-qa='account-order-history-discount-code-price']");
	public static By ORDER_SUBTOTAL_SHIPPING_PRICE_TEXT = By.cssSelector("[data-tj-qa='account-order-history-shipping-price-text']");
	public static By ORDER_SUBTOTAL_SHIPPING_PRICE_PRICE = By.cssSelector("[data-tj-qa='account-order-history-shipping-price']");
	public static By ORDER_SUBTOTAL_TAX_TEXT = By.cssSelector("[data-tj-qa='account-order-history-tax-text']");
	public static By ORDER_SUBTOTAL_TAX_NUMBER = By.cssSelector("[data-tj-qa='account-order-history-tax']");
	public static By ORDER_SUBTOTAL_TOTAL_TEXT = By.cssSelector("[data-tj-qa='account-order-history-total-text']");
	public static By ORDER_SUBTOTAL_TOTAL_NUMBER = By.cssSelector("[data-tj-qa='account-order-history-total-price']");
	
//	Address Page
	public static By ADD_ADDRESS_BTN = By.cssSelector("a[data-tj-qa='add-address-btn']");
	public static By EDIT_ADDRESS_LINK = By.cssSelector("button[data-tj-qa='account-edit-address']");
	public static By EDIT_ADDRESS_HEADER = By.cssSelector("h2[data-tj-qa='account-edit-address-header']");
	public static By EDIT_ADDRESS_FIRST_NAME_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-first-name-header']");
	public static By EDIT_ADDRESS_FIRST_NAME_INPUT = By.cssSelector("[data-tj-qa='account-edit-address-first-name-input']");
	public static By EDIT_ADDRESS_LAST_NAME_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-last-name-header']");
	public static By EDIT_ADDRESS_LAST_NAME_INPUT = By.cssSelector("[data-tj-qa='account-edit-address-last-name-input']");
	public static By EDIT_ADDRESS_COMPANY_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-company-header']");
	public static By EDIT_ADDRESS_COMPANY_INPUT = By.cssSelector("[data-tj-qa='account-edit-address-company-input']");
	public static By EDIT_ADDRESS_ADDRESS1_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-address1-header']");
	public static By EDIT_ADDRESS_ADDRESS1_INPUT = By.cssSelector("[data-tj-qa='account-edit-address-address1-input']");
	public static By EDIT_ADDRESS_ADDRESS2_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-address2-header']");
	public static By EDIT_ADDRESS_ADDRESS2_INPUT = By.cssSelector("[data-tj-qa='account-edit-address-address2-input']");
	public static By EDIT_ADDRESS_CITY_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-city-header']");
	public static By EDIT_ADDRESS_CITY_INPUT = By.cssSelector("[data-tj-qa='account-edit-address-city-input']");
	public static By EDIT_ADDRESS_COUNTRY_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-country-header']");
	public static By EDIT_ADDRESS_COUNTRY_DROPDOWN = By.cssSelector("[data-tj-qa='account-edit-address-country-dropdown']");
	public static By EDIT_ADDRESS_ZIPCODE_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-postal-or-zipcode-header']");
	public static By EDIT_ADDRESS_ZIPCODE_INPUT = By.cssSelector("[data-tj-qa='account-edit-address-postal-or-zipcode-input']");
	public static By EDIT_ADDRESS_PHONE_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-phone-header']");
	public static By EDIT_ADDRESS_PHONE_INPUT = By.cssSelector("[data-tj-qa='account-edit-address-phone-input']");
	public static By EDIT_ADDRESS_DEFAULT_CHECKBOX = By.cssSelector("input[type='checkbox']:has(+ [data-tj-qa='account-edit-address-default-address-input'])");
	public static By EDIT_ADDRESS_DEFAULT_HEADER = By.cssSelector("[data-tj-qa='account-edit-address-default-address-input']");
	public static By EDIT_ADDRESS_UPDATE_ADDRESS_LINK = By.cssSelector("[data-tj-qa='account-edit-address-update-address']");
	public static By EDIT_ADDRESS_UPDATE_CANCEL_LINK = By.cssSelector("[data-tj-qa='account-edit-address-cancel']");
	public static By DELETE_ADDRESS = By.cssSelector("[data-tj-qa='account-delete-address']");
	
	
	
}
