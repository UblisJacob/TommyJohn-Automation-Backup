package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class AccountPageLocators 

{
//	public static By ACCOUNT_PAGE_HEADING =By.cssSelector(".account-logged-in-header");
	public static By ACCOUNT_PAGE_HEADING =By.cssSelector("h1[data-tj-qa='account-login-title']");
	public static By SIGN_OUT_LINK = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(3) > a");
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
	public static By EDIT_ADDRESS_LINK = By.cssSelector("button[data-tj-qa='account-edit-address']");
	public static By EDIT_ADDRESS_HEADER = By.cssSelector("h2[data-tj-qa='account-edit-address-header']");
	
	
	// Add Address
	public static By ADD_ADDRESS_BTN = By.cssSelector("a[data-tj-qa='add-address-btn']");
	
	
	
}
