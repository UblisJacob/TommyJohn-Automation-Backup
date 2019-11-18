package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class HomePageLocators{

	public static By HOMEPAGE_LOGO = By.cssSelector("a.header-logo");
	public static By NEWARRIVALS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(1) > a");
	public static By MEN_IN_NEWARRIVALS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(1) > div > ul > li:nth-child(1) > div");

	public static By MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > a");
	public static By NEW_ARRIVALS_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(1) > ul > li:nth-child(1) > a");
	public static By ALL_UNDERWEAR_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(2) > ul > li:nth-child(1) > a");
	public static By TANK_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(3) > ul > li:nth-child(5) > a");
	public static By DRESS_SHIRTS_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(5) > ul > li:nth-child(3) > a");
	public static By ALL_CLOTHING_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(5) > ul > li:nth-child(1) > a");	
	public static By WHATS_NEW_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(1) > div");
	//public static By SECOND_SKIN_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(5) > ul > li:nth-child(3) > a");
	public static By ALL_UNDERSHIRTS_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(3) > ul > li:nth-child(1) > a");

	public static By WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > a");
	public static By WHATS_NEW_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(1) > div");
	public static By NEW_ARRIVALS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(1) > ul > li:nth-child(1) > a");
	public static By ALL_BRAS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(3) > ul > li:nth-child(1) > a");
	public static By BOYSHORT_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(3) > ul > li:nth-child(2) > a");
//	public static By ALL_WOMENS_COLLECTIONS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(4) > ul > li:nth-child(1) > a");
	public static By ALL_CLOTHING_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(5) > ul > li:nth-child(1) > a");

	public static By UNDERWEAR_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(4) > a");
	public static By MEN_IN_UNDERWEAR_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(4) > div > ul > li:nth-child(1) > div");

	//	public static By SHOPBYCOLLECTION = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > a");
	//public static By MEN_IN_SHOPBYCOLLECTION = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > div > ul > li:nth-child(1) > div");
	//	public static By COTTON_BASICS_SHOPBYCOLLECTION = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > div > ul > li:nth-child(1) > ul > li:nth-child(9) > a");
	public static By BRAS = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > a");
	public static By PACKS = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > a");
	public static By MEN_IN_PACKS = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > div > ul > li:nth-child(1) > div");
	public static By ALL_MENS_PACKS_IN_PACKS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > div > ul > li:nth-child(1) > ul > li:nth-child(1) > a");
	public static By ALL_WOMENS_PACKS_IN_PACKS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(6) > div > ul > li:nth-child(2) > ul > li:nth-child(1) > a");
	//	public static By COOL_COTTON_IN_MENS_COLLECTIONS = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(5) > ul > li:nth-child(4) > a");

	public static By REWARDS = By.cssSelector(".header-desktop-left > ul > li:nth-child(7) > a");

	public static By SEARCH_ICON = By.id("searchMenuBtn");
	public static By SEARCH_ICON_FORM_TAG = By.cssSelector(".header-search-form");

	public static By HELP_ICON = By.cssSelector("i.icon-help");
	public static By HELP_ICON_HELP_OPTION = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(1) > a");
	public static By CHAT_WITH_US = By.cssSelector(".header-nav-dropdown > ul >li:nth-child(2) > a");
	public static By CHAT_WITH_US_IFRAME = By.cssSelector(".zopim:nth-child(2) > iframe");
	//public static By CHAT_WITH_US_POPUP = By.cssSelector(".desktop.sf_large.scroll_top.scroll_bottom > div.scrollable_inner.jx_ui_Widget > div:nth-child(2) > a");
	public static By CHAT_WITH_US_POPUP = By.cssSelector(".jx_ui_Widget");
	public static By CHAT_WITH_US_POPUP_MINIMIZE = By.cssSelector(".meshim_widget_widgets_titleBar_MinimizeButton > div");

	//public static By CHAT_WITH_US_POPUP_MINIMIZE = By.cssSelector(".button_container.last_child.jx_ui_Widget > div > div");
	public static By CALL_US_OPTION = By.cssSelector(".header-nav-dropdown > ul > li:nth-child(3) > a");
	public static By EMAIL_US = By.cssSelector(".header-nav-dropdown > ul > li:nth-child(4) > a");

	public static By ACCOUNT_ICON = By.xpath("//i[@class='icon-account']"); //cssSelector("i.icon-account");
	public static By REORDER = By.cssSelector("header-desktop-right > li:nth-child(3) >div > ul > li:nth-child(2) > a ");
	//	public static By CART_ICON = By.cssSelector("i.icon-shopping-cart");
	public static By CART_ICON = By.cssSelector(".header-main__inner.container > ul > li:nth-child(4) > button > i");
	public static By CART_HEADING_TEXT = By.cssSelector(".inline-cart__col1 > h3");

	public static By ABOUT_LINK = By.linkText("About");
	public static By PRESS_LINK = By.linkText("Press");
	public static By MILITARY_DISCOUNT_LINK = By.linkText("Military Discount");
	public static By TEACHER_DISCOUNT_LINK = By.linkText("Student & Teacher Discount");
	public static By JOBS_LINK = By.linkText("Jobs");
	public static By PRIVACY_POLICY_LINK = By.linkText("Privacy Policy");
	public static By TERMS_AND_CONDITION_LINK = By.linkText("Terms & Conditions");
	public static By BLOG_LINK = By.linkText("Blog");

	public static By HELP_LINK = By.linkText("Help");
	public static By CONTACT_US_LINK = By.linkText("Contact Us");
	public static By RETAILERS_LINK = By.linkText("Retailers");
	public static By KING_OF_PURSSIA_STORE_LINK = By.linkText("King of Prussia Store");
	public static By CHARLOTTE_STORE_LINK = By.linkText("Charlotte Store");
	public static By TOMMY_JOHN_STORES = By.linkText("Tommy John Stores");
	public static By STORE_LOCATOR_LINK = By.linkText("Store Locator");
	public static By SHIPPING_AND_RETURNS_LINK = By.linkText("Shipping & Return Policy");
	public static By NUMBER_LINK = By.linkText("1-800-708-3490");
	public static By LOYALTY_LINK = By.linkText("Loyalty");

	public static By TOMMYJOHN_REWARDS_LINK = By.linkText("Tommy John Rewards");
	public static By GIFT_CARD_LINK = By.linkText("Gift Cards");
	public static By FABRIC_GUIDE_LINK = By.linkText("Fabric Guide");

	public static By FACEBOOK_LINK = By.linkText("Facebook");
	public static By TWITTER_LINK = By.linkText("Twitter");
	public static By PINTEREST_LINK = By.linkText("Pinterest");
	public static By INSTAGRAM_LINK = By.linkText("Instagram");
	public static By YOUTUBE_LINK = By.linkText("YouTube");
	public static By GOOGLE_PLUS_LINK = By.linkText("Google+");

	public static By SECOND_SKIN_IN_MEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(6) > ul > li:nth-child(2) > a");
	public static By COOL_COTTON_IN_MENS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(6) > ul > li:nth-child(3) > a");
	public static By COTTON_BASICS_IN_MENS_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > div > ul > li:nth-child(6) > ul > li:nth-child(8) > a");
	//public static By SHOPBYCOLLECTION = By.cssSelector(".header-desktop-left > ul > li:nth-child(2) > a");
	
	
	public static By GIFT_GUIDE = By.cssSelector(".header-desktop-left >ul >li:nth-child(7) >a");
	public static By GIFTCARDS = By.cssSelector(".header-desktop-left >ul >li:nth-child(7) > div > ul > li:nth-child(3) > ul >li >a");
	
	
	public static By BRAS_TITLE = By.cssSelector(".header-desktop-left > ul > li:nth-child(5) > div > ul > li:nth-child(1) > div");
	public static By ALL_PANTIES_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(3) > ul > li:nth-child(1) > a");
	public static By ALL_WOMENS_COLLECTIONS_IN_WOMEN_CATEGORY = By.cssSelector(".header-desktop-left > ul > li:nth-child(3) > div > ul > li:nth-child(5) > ul > li:nth-child(1) > a");
}
