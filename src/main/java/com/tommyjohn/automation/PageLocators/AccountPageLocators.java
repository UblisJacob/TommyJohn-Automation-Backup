package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class AccountPageLocators 
{
	public static By ACCOUNT_PAGE_HEADING =By.cssSelector(".account-logged-in-header");
	public static By LOGIN_INFORMATION_TITLE = By.cssSelector(".account-logged-content>div:nth-child(1)>div>h2");
	public static By LOGGED_IN_ID = By.cssSelector(".account-logged-content>div:nth-child(1)>div>div>.account-row-column>p");
	public static By ADDRESS_BOOK_TITLE= By.cssSelector(".account-logged-content>div:nth-child(2)>h2");
	public static By DEFAULT_SHIPPING_ADDRESS_TITLE= By.cssSelector(".account-logged-content>div:nth-child(2)>div>div>.account-default-shipping-address");
	public static By ADDRESS_CONTENT = By.cssSelector(".account-logged-content>div:nth-child(2)>div>div>p");
	public static By ORDER_HISTORY_TITLE= By.cssSelector(".account-logged-content>div:nth-child(3)>h2");
	public static By ORDER_HISTORY_TABLE_TITLE_HEADING = By.cssSelector(".account-logged-content>div:nth-child(3)>.account-orders-table>table>thead>tr>th");
	public static By FIRST_ORDER_NUMBER= By.cssSelector(".account-logged-content>div:nth-child(3)>.account-orders-table>table>tbody>tr:nth-child(1)>td:nth-child(1)>a");
	
	
	
}
