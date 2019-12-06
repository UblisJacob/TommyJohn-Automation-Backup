package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class LoyaltyOptInLocators {

	public static By ACCOUNTPAGE_SWELLSTAUS = By.cssSelector(".swell-account-rewards > h2 > span");
	public static By ACCOUNTPAGE_SWELLPOINTS = By.cssSelector(".swell-account-rewards > p > span");
	public static By ACCOUNTPAGE_SWELLREDEEMBTN = By.cssSelector(".swell-link-redeem");
	public static By ACCOUNTPAGE_SWELLHISTORY = By.cssSelector(".swell-link-history");
	
	public static By REWARDS_POPUP_TITLE = By.cssSelector(".modal-title");
	public static By ACCOUNTPAGE_SWELLJOINNOWMSG = By.cssSelector(".swell-account-rewards > h2");
	public static By REWARDS_POPUP_CLOSE = By.cssSelector(".close");
	
	public static By JOIN_SWELL = By.cssSelector(".swell-link-login");
	public static By SWELLBOX_ICON = By.cssSelector("div.list-box> .swell-icon");
	public static By SWELLBOX_HEADER = By.cssSelector(".work-box-holder > li:nth-child(2) >div> h3");
	public static By SWELLBOX_DESCRIPTION = By.cssSelector(".work-box-holder > li:nth-child(3) >div> div.list-content> p");
	
	
	public static By EARNMORE_HEADING = By.cssSelector(".earn-more > .swell-section-heading");
	public static By TIER1_NAME = By.xpath("//li[@data-tier-id = '1218'] /div/div/div[@class='name']");
	public static By TIER2_NAME = By.xpath("//li[@data-tier-id = '1219'] /div/div/div[@class='name']");
	public static By TIER3_NAME = By.xpath("//li[@data-tier-id = '1220'] /div/div/div[@class='name']");
	
	public static By TIER1_DETAILS = By.xpath("//li[@data-tier-id = '1218'] /div/ul/li");
	public static By TIER2_DETAILS = By.xpath("//li[@data-tier-id = '1219'] /div/ul/li");
	public static By TIER3_DETAILS = By.xpath("//li[@data-tier-id = '1220'] /div/ul/li");
	
	public static By WAYTOEARNMORE_HEADING = By.cssSelector(".way-to-earn > .swell-section-heading > h3");
	public static By NOTMEMBER_POINTS_CREATEACCOUNT = By.cssSelector("div[data-campaign-id ='446052'] > .swell-campaign-value > span");
	public static By NOTMEMBER_CREATEACCOUNT_TEXT = By.cssSelector("div[data-campaign-id ='446052'] > .swell-campaign-type > span");
	
	public static By NOTMEMBER_POINTS_MAKEPURCHASE = By.cssSelector("div[data-campaign-id ='446053'] > .swell-campaign-value > span");
	public static By NOTMEMBER_MAKEPURCHASE_TEXT = By.cssSelector("div[data-campaign-id ='446053'] > .swell-campaign-type > span");
	
	public static By NOTMEMBER_POINTS_FACEBOOK = By.cssSelector("div[data-campaign-id ='456588'] > .swell-campaign-value > span");
	public static By NOTMEMBER_FACEBOOK_TEXT = By.cssSelector("div[data-campaign-id ='456588'] > .swell-campaign-type > span");
	
	public static By NOTMEMBER_POINTS_BIRTHDAY = By.cssSelector("div[data-campaign-id ='459457'] > .swell-campaign-value > span");
	public static By NOTMEMBER_BIRTHDAY_TEXT = By.cssSelector("div[data-campaign-id ='459457'] > .swell-campaign-type > span");
	
	public static By NOTMEMBER_POINTS_INSTA = By.cssSelector("div[data-campaign-id ='456584'] > .swell-campaign-value > span");
	public static By NOTMEMBER_INSTA_TEXT = By.cssSelector("div[data-campaign-id ='456584'] > .swell-campaign-type > span");
	
	public static By NOTMEMBER_POINTS_REVIEW = By.cssSelector("div[data-campaign-id ='456587'] > .swell-campaign-value > span");
	public static By NOTMEMBER_REVIEW_TEXT = By.cssSelector("div[data-campaign-id ='456587'] > .swell-campaign-type > span");
	
	public static By REFERRAL_BANNER_IMAGE = By.cssSelector(".referral-banner-image");
	public static By SWELL_REFERRAL_HEADING = By.cssSelector(".swell-referral-heading");
	public static By SWELL_REFERRAL_SUBHEADING = By.cssSelector(".swell-referral-form-header-details");
	public static By SWELL_REFERRAL_EMAIl = By.cssSelector(".swell-referral-form-list-field-input");
	public static By SWELL_REFERRAL_SUBMIT_BTN = By.cssSelector(".swell-referral-form-list-submit");
	public static By SWELL_REDEMPTION_HEADING = By.cssSelector(".swell-redemption-section > .swell-section-heading >h3");
	public static By SWELL_REDEMPTION_JOIN_NOW = By.cssSelector(".redeem-join");
	
	public static By SUBMIT_EMAIL = By.cssSelector("#swell-referral-refer-submit");
	
	public static By CUSTOMERNAME = By.cssSelector(".customer-name");
	public static By POINTS = By.cssSelector(".swell-point-balance");
	
	public static By CURRENT_STATUS = By.cssSelector(".swell-tier-status.silver > span");
	
	public static By REDEEM_REWARDS_HEADING = By.cssSelector(".order-summary__section--swell > h3");
	public static By SWELL_POINTS_BALANCE = By.className("swell-point-balance");
	
	public static By SWELL_REDEEM_BTN = By.id("swell-checkout-redeem-btn");
	public static By SWELL_OPTIONS_DROPDOWN = By.id("swell-checkout-redemption-options");
	
	public static By SWELL_ERROR_MESSAGE = By.cssSelector("#swell-checkout-error-message-amount");
	
	
}
