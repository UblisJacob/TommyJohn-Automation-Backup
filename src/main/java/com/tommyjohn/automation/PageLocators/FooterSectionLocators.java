package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class FooterSectionLocators {

//	FOOTER SHOP SECTION
	public static By SHOP_WOMEN = By.cssSelector("a[data-tj-qa='footer-shop-women']");
	public static By SHOP_MEN = By.cssSelector("a[data-tj-qa='footer-shop-men']");
	public static By FABRIC_GUIDE_LINK = By.cssSelector("a[data-tj-qa='footer-fabric-guide']");
	public static By GIFT_CARD_LINK = By.cssSelector("a[data-tj-qa='footer-gift-cards']");
	
//	FOOTER PROMOTIONS SECTION
	public static By SITEWIDE_PROMOTION_LINK = By.cssSelector("a[data-tj-qa='footer-sitewide-promotion']");
	public static By MILITARY_DISCOUNT_LINK = By.cssSelector("a[data-tj-qa='footer-military-discount']");
	public static By FIRST_RESPONDER = By.cssSelector("a[data-tj-qa='footer-first-responder']");
	public static By GOVERNMENT_EMPLOYEE_DISCOUNT = By.cssSelector("a[data-tj-qa='footer-government-employee-discount']");
	public static By TEACHER_DISCOUNT_LINK = By.cssSelector("a[data-tj-qa='footer-teacher-discount']");
	public static By REFERRAL_LINK = By.cssSelector("a[data-tj-qa='footer-give-$,-get-$']");
	
	public static By LEAVE_SITE_POPUP = By.cssSelector(".LeaveSiteModalContainer.active .LeaveSiteModalContent");
	public static By LEAVE_SITE_POPUP_OK_BTN = By.cssSelector(".LeaveSiteModalContainer.active button[data-leave-site='ok']");
	public static By LEAVE_SITE_POPUP_CANCEL_BTN = By.cssSelector(".LeaveSiteModalContainer.active button[data-leave-site='cancel']");
	
//	FOOTER ACCOUNT SECTION
	public static By SIGN_IN_FOOTER_LINK = By.cssSelector("a[data-tj-qa='footer-sign-in']");
	public static By ORDER_HISTORY = By.cssSelector("a[data-tj-qa='footer-order-history']");
	
//	FOOTER SUPPORT SECTION
	public static By CONTACT_US_LINK = By.cssSelector("a[data-tj-qa='footer-contact-&-faq']");
	public static By RETURNS_AND_EXCHANGES = By.cssSelector("a[data-tj-qa='footer-returns-&-exchanges']");
	public static By SHIPPING_AND_RETURNS_LINK = By.cssSelector("a[data-tj-qa='footer-shipping-&-return-policy']");
	public static By GIFT_CARD_BALANCE_CHECK = By.cssSelector("a[data-tj-qa='footer-check-gift-card-balance']");
	public static By TOMMY_JOHN_STORES = By.cssSelector("a[data-tj-qa='footer-tommy-john-stores']");
	public static By STORE_LOCATOR_LINK = By.cssSelector("a[data-tj-qa='footer-store-locator']");
	public static By RETAILERS_LINK = By.cssSelector("a[data-tj-qa='footer-retailers']");
	public static By AFFILIATES_LINK = By.cssSelector("a[data-tj-qa='footer-affiliates']");
	
//	FOOTER COMPANY SECTION
	public static By ABOUT_LINK = By.cssSelector("a[data-tj-qa='footer-about']");
	public static By CAREERS_LINK = By.cssSelector("a[data-tj-qa='footer-careers']");
	public static By PRESS_LINK = By.cssSelector("a[data-tj-qa='footer-press']");
	public static By BLOG_LINK = By.cssSelector("a[data-tj-qa='footer-blog']");
	public static By REVIEWS_LINK = By.cssSelector("a[data-tj-qa='footer-reviews']");
	
//	FOOTER SOCIAL
	public static By FACEBOOK_LINK = By.cssSelector("a[data-tj-qa='footer-facebook']");
	public static By TWITTER_LINK = By.cssSelector("a[data-tj-qa='footer-twitter']");
	public static By PINTEREST_LINK = By.cssSelector("a[data-tj-qa='footer-pinterest']");
	public static By INSTAGRAM_LINK = By.cssSelector("a[data-tj-qa='footer-instagram']");
	public static By YOUTUBE_LINK = By.cssSelector("a[data-tj-qa='footer-youtube']");

	public static By FACEBOOK_ICON = By.cssSelector("a[data-tj-qa='footer-facebook']>svg");
	public static By TWITTER_ICON = By.cssSelector("a[data-tj-qa='footer-twitter']>svg");
	public static By PINTEREST_ICON = By.cssSelector("a[data-tj-qa='footer-pinterest']>svg");
	public static By INSTAGRAM_ICON = By.cssSelector("a[data-tj-qa='footer-instagram']>svg");
	public static By YOUTUBE_ICON = By.cssSelector("a[data-tj-qa='footer-youtube']>svg");
	
//	FOOTER CCPA SECTION
	public static By PRIVACY_POLICY_LINK = By.cssSelector("a[data-tj-qa='footer-privacy-policy']");
	public static By TERMS_AND_CONDITION_LINK = By.cssSelector("a[data-tj-qa='footer-terms-&-conditions']");
	public static By DO_NOT_SELL_PERSONAL_INFO_LINK = By.cssSelector("a[data-tj-qa='footer-ccpa']");
	public static By CA_SUPPLY_CHAINS_ACT_LINK = By.cssSelector("a[data-tj-qa='footer-transparency-sca']");
	
//	FOOTER NEWSLETTER/EMAIL SIGN UP SECTION
	public static By NEWSLETTER_TITLE = By.cssSelector(".desktop-footer-newsletter div[data-tj-qa='footer-newsletter-title']");
	public static By NEWSLETTER_MEN_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-mens']");
	public static By NEWSLETTER_WOMEN_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-women']");
	public static By NEWSLETTER_BOTH_RADIO_BUTTON = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-both']");
	public static By NEWSLETTER_INPUT_TEXTBOX = By.cssSelector(".desktop-footer-newsletter input[data-tj-qa='footer-newsletter-email']");
	public static By NEWSLETTER_SIGN_UP_BUTTON = By.cssSelector(".desktop-footer-newsletter button[data-tj-qa='footer-newsletter-sign-up']");
	public static By NEWSLETTER_FORM_SUCCESS_MESSAGE = By.cssSelector(".desktop-footer-newsletter span[data-tj-qa='footer-newsletter-success']");
	public static By NEWSLETTER_FORM_SUCCESS_MESSAGE_VISIBLE = By.cssSelector(".desktop-footer-newsletter div[style='display: inline-flex;']");
	public static By NEWSLETTER_FORM_FAIL_MESSAGE = By.cssSelector(".desktop-footer-newsletter span[data-tj-qa='footer-newsletter-error']");
	public static By NEWSLETTER_FORM_FAIL_MESSAGE_VISIBLE = By.cssSelector(".desktop-footer-newsletter div[style='display: block;']");
	
//	FOOTER FEEDBACK SECTION
	public static By GIVE_FEEDBACK_TITLE = By.cssSelector("div[data-tj-qa='footer-feedback-title']");
	public static By GIVE_FEEDBACK_CTA = By.cssSelector(".footer-feedback_btn");

}
