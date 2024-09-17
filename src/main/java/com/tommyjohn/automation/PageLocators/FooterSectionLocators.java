package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class FooterSectionLocators {

//	FOOTER SHOP SECTION
	public static By SHOP_WOMEN = By.xpath("//div[@id='quick-links-tabpanel-1']//a[contains(text(),'Shop Women')]");
	public static By SHOP_MEN = By.xpath("//div[@id='quick-links-tabpanel-1']//a[contains(text(),'Shop Men')]");
	public static By FABRIC_GUIDE_LINK = By.xpath("a[data-tj-qa='footer-fabric-guide']");
	public static By SALE = By.xpath("//div[@id='quick-links-tabpanel-1']//a[contains(text(),'Sale')]");
	public static By GIFT_CARD_LINK = By.xpath("//div[@id='quick-links-tabpanel-1']//a[contains(text(),'Gift Cards')]");
	
//	FOOTER PROMOTIONS SECTION
	public static By SITEWIDE_PROMOTION_LINK = By.xpath("//div[@id='quick-links-tabpanel-2']//a[contains(text(),'Sitewide Promotion')]");
	public static By MILITARY_DISCOUNT_LINK = By.xpath("//div[@id='quick-links-tabpanel-2']//a[contains(text(),'Military Discount')]");
	public static By FIRST_RESPONDER = By.xpath("//div[@id='quick-links-tabpanel-2']//a[contains(text(),'First Responder')]");
	public static By GOVERNMENT_EMPLOYEE_DISCOUNT = By.xpath("//div[@id='quick-links-tabpanel-2']//a[contains(text(),'Government Employee Discount')]");
	public static By TEACHER_DISCOUNT_LINK = By.xpath("//div[@id='quick-links-tabpanel-2']//a[contains(text(),'Teacher Discount')]");
	public static By REFERRAL_LINK = By.cssSelector("a[data-tj-qa='footer-refer-a-friend']");
	
	public static By LEAVE_SITE_POPUP = By.cssSelector(".LeaveSiteModalContainer.active .LeaveSiteModalContent");
	public static By LEAVE_SITE_POPUP_OK_BTN = By.cssSelector(".LeaveSiteModalContainer.active button[data-leave-site='ok']");
	public static By LEAVE_SITE_POPUP_CANCEL_BTN = By.cssSelector(".LeaveSiteModalContainer.active button[data-leave-site='cancel']");
	
//	FOOTER ACCOUNT SECTION
	public static By SIGN_IN_FOOTER_LINK = By.xpath("//a[contains(text(),'Sign In')]");
	public static By ORDER_HISTORY = By.xpath("//a[contains(text(),'Order History')]");
	
//	FOOTER SUPPORT SECTION
	public static By CONTACT_US_LINK = By.xpath("//a[contains(text(),'Contact & FAQ')]");
	public static By RETURNS_AND_EXCHANGES = By.cssSelector("a[data-tj-qa='footer-returns-&-exchanges']");
	public static By SHIPPING_AND_RETURNS_LINK = By.cssSelector("a[data-tj-qa='footer-shipping-&-return-policy']");
	public static By GIFT_CARD_BALANCE_CHECK = By.cssSelector("a[data-tj-qa='footer-check-gift-card-balance']");
	public static By TOMMY_JOHN_STORES = By.cssSelector("a[data-tj-qa='footer-tommy-john-stores']");
	public static By STORE_LOCATOR_LINK = By.xpath("//a[contains(text(),'Store Locator')]");
	public static By RETAILERS_LINK = By.xpath("//a[contains(text(),'Retailers')]");
	public static By AFFILIATES_LINK = By.xpath("//a[contains(text(),'Affiliates')]");
	
//	FOOTER COMPANY SECTION
	public static By ABOUT_LINK = By.xpath("//a[contains(text(),'Our Story')]");
	public static By CAREERS_LINK = By.xpath("//a[contains(text(),'Careers')]");
	public static By PRESS_LINK = By.cssSelector("a[data-tj-qa='footer-press']");
	public static By BLOG_LINK = By.xpath("//a[contains(text(),'Blog')]");
	public static By REVIEWS_LINK = By.cssSelector("a[data-tj-qa='footer-reviews']");
	
//	FOOTER SOCIAL
	public static By FACEBOOK_LINK = By.cssSelector("a[data-tj-qa='footer-facebook']");
	public static By TWITTER_LINK = By.cssSelector("a[data-tj-qa='footer-twitter']");
	public static By PINTEREST_LINK = By.cssSelector("a[data-tj-qa='footer-pinterest']");
	public static By INSTAGRAM_LINK = By.cssSelector("a[data-tj-qa='footer-instagram']");
	public static By YOUTUBE_LINK = By.cssSelector("a[data-tj-qa='footer-youtube']");

	public static By FACEBOOK_ICON = By.xpath("//span[@class='icon icon-facebook block']");
	public static By TWITTER_ICON = By.xpath("//span[@class='icon icon-twitter block']");
	public static By PINTEREST_ICON = By.xpath("//span[@class='icon icon-pinterest block']");
	public static By INSTAGRAM_ICON = By.xpath("//span[@class='icon icon-instagram block']");
	public static By YOUTUBE_ICON = By.xpath("//span[@class='icon icon-youtube block']");
	
//	FOOTER CCPA SECTION
	public static By PRIVACY_POLICY_LINK = By.xpath("//a[contains(text(),'Privacy Policy')]");
	public static By TERMS_AND_CONDITION_LINK = By.xpath("//a[contains(text(),'Terms & Conditions')]");
	public static By DO_NOT_SELL_PERSONAL_INFO_LINK = By.cssSelector("a[data-tj-qa='footer-ccpa']");
	public static By CA_SUPPLY_CHAINS_ACT_LINK = By.xpath("//a[contains(text(),'CA Transparency in Supply Chains Act')]");
	
//	FOOTER NEWSLETTER/EMAIL SIGN UP SECTION
	public static By NEWSLETTER_TITLE = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-email']");
	public static By NEWSLETTER_MEN_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-mens']");
	public static By NEWSLETTER_WOMEN_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-women']");
	public static By NEWSLETTER_BOTH_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-both']");
	public static By NEWSLETTER_INPUT_TEXTBOX = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-email']");
	public static By NEWSLETTER_SIGN_UP_BUTTON = By.cssSelector(".desktop-footer-newsletter button[data-tj-qa='footer-newsletter-sign-up']");
	public static By NEWSLETTER_FORM_SUCCESS_MESSAGE = By.cssSelector(".desktop-footer-newsletter span[data-tj-qa='footer-newsletter-success']");
	public static By NEWSLETTER_FORM_SUCCESS_MESSAGE_VISIBLE = By.cssSelector(".desktop-footer-newsletter div[style='display: inline-flex;']");
	public static By NEWSLETTER_FORM_FAIL_MESSAGE = By.cssSelector(".desktop-footer-newsletter span[data-tj-qa='footer-newsletter-error']");
	public static By NEWSLETTER_FORM_FAIL_MESSAGE_VISIBLE = By.cssSelector(".desktop-footer-newsletter div[style='display: block;']");

// FOOTER Sign up & save on your first order

	public static By SIGNUP_TITLE = By.xpath("//h2[contains(text(),'Sign up & save on your first order')]");
	public static By SIGNUP_TEXTBOX = By.xpath("//input[@id='NewsletterForm-Email']");
	public static By SIGNUP_ARROW = By.xpath("//span[@class='icon icon-arrow-right']");
	public static By SUCCESSMESSAGE = By.xpath("//p[@class='mt-md']");






//	FOOTER FEEDBACK SECTION
	public static By GIVE_FEEDBACK_TITLE = By.cssSelector("div[data-tj-qa='footer-feedback-title']");
	public static By GIVE_FEEDBACK_CTA = By.cssSelector(".footer-feedback_btn");

}
