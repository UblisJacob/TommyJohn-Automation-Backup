package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class ContactUsPageLocators 
{
	public static By HELP_PAGETITLE = By.cssSelector(".help_text >a");
	public static By HELP_QUERY = By.cssSelector("#query");
	public static By HELP_QUICK_SEARCH_TOPICS = By.cssSelector(".quick-search");

	public static By CONTACT_INFO_TITLE = By.cssSelector("#contact-info>h2");
	public static By TIMING_TEXT1 = By.cssSelector("#contact-info>p");
	public static By TIMING_TEXT2 = By.cssSelector("#contact-info>p:nth-child(3)");
	public static By EMAIL_US_LINK = By.cssSelector("#contact-info>p:nth-child(6)>a>u");
	public static By CHAT_LINK = By.cssSelector("#contact-info>p:nth-child(8)>a>u");
	public static By CALL_US_NUMBER =By.cssSelector("#contact-info>p:nth-child(9)>a>u");
	public static By TEXT_US_AT =By.cssSelector("#contact-info>p:nth-child(10)>a>u");
	public static By HELP_CATEGORY = By.cssSelector(".category-grid");
	
	
	
	public static By MAIL_TO =By.cssSelector(".contact-information>p:nth-child(2)>a");
	public static By CALL_US_AT_TEXT = By.cssSelector(".contact-information>p:nth-child(3)");
	//public static By CALL_US_NUMBER =By.cssSelector(".contact-information>p:nth-child(3)>a");
//	public static By TEXT_US_AT =By.cssSelector(".contact-information>p:nth-child(4)");
	public static By LIVE_CHAT_WITH_US =By.cssSelector(".contact-information>p:nth-child(5)>a");
	public static By FOR_FURTHER_INFO_TEXT=By.cssSelector(".contact-information>p:nth-child(7)");
	
	public static By HELP_LINK = By.cssSelector(".contact-information>ul>li:nth-child(1)>a");
	public static By SHIPPING_RETURNS_POLICY = By.cssSelector(".contact-information>ul>li:nth-child(2)>a");
	public static By WHOLESALE_INQUIRIES = By.cssSelector(".contact-information>ul>li:nth-child(3)>a");
	public static By NAME_TEXT=By.cssSelector(".contact-form--wrapper>form>div:nth-child(3)>label");
	public static By EMAILUS_TITLE = By.cssSelector(".page-title");
	public static By NAME_TEXTBOX = By.name("name");
	public static By EMAIL_ADDRESS_TEXT = By.cssSelector(".contact-form--wrapper>form>div:nth-child(4)>label");
	public static By EMAIL_ADDRESS_TEXTBOX = By.name("email");
	public static By SUBJECT=By.cssSelector(".contact-form--wrapper>form>div:nth-child(5)>label");
	public static By SUBJECT_LIST = By.cssSelector("div.contact-form--wrapper > form > div:nth-child(5) > div > select > option");
	public static By MESSAGE=By.cssSelector(".contact-form--wrapper>form>div:nth-child(6)>label");
	public static By MESSAGE_TEXTAREA=By.cssSelector(".contact-form--textarea");
	public static By SEND_MESSAGE_BUTTON = By.cssSelector(".contact-form--submit");
	public static By SUCCESS_TITLE= By.cssSelector(".contact-form--success>h2");
	public static By SUCCESS_MESSAGE = By.cssSelector(".contact-form--success>p");
	public static By CHAT_WITH_US_IFRAME = By.cssSelector(".zopim:nth-child(2) > iframe");
	public static By CHAT_WITH_US_POPUP = By.cssSelector(".jx_ui_Widget");
	public static By CHAT_WITH_US_POPUP_MINIMIZE = By.cssSelector(".meshim_widget_widgets_titleBar_MinimizeButton > div");


	
}
