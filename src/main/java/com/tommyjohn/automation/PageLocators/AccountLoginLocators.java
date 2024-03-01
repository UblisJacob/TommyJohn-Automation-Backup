package com.tommyjohn.automation.PageLocators;

import org.openqa.selenium.By;

public class AccountLoginLocators {
	
	//on account login page
	
	public static By CREATEACCOUNT_TEXT1 = By.cssSelector(".account-logged-out-row.two-tiles.second-row-mobile.on-load > h2");
	public static By CREATEACCOUNT_TITLE = By.cssSelector("h1[data-tj-qa='no-account-title'], #shopify-section-account-benefits .account-row-title");
	public static By CREATEACCOUNT_TEXT2 = By.cssSelector(".account-registration > p");
	public static By CREATEACCOUNT_BTN = By.cssSelector("button[data-tj-qa='account-create-account-btn']");
	public static By LOGIN_HEADING_LOGO = By.cssSelector("#Account-fill");
	public static By EMAIL_LABEL1 = By.cssSelector("label[data-tj-qa='account-email-title'], #customer_login > div:nth-child(4) > label");
	public static By CUSTOMER_EMAIL_INPUT = By.cssSelector("input[data-tj-qa='account-email-input'], #customer-email");
	public static By PASSWORD_LABEL1 = By.cssSelector("#customer_login > div:nth-child(5) > label, label[data-tj-qa='account-password-title']");
	public static By CUSTOMER_PASSWORD_INPUTBOX = By.cssSelector("#customer-password, input[data-tj-qa='account-password-input']");
	public static By LOGIN_HEADING = By.cssSelector("h1[data-tj-qa='account-login-title'] , h2[data-tj-qa='account-login-title']");
	public static By LOGIN_BUTTON = By.cssSelector("button[data-tj-qa='account-signup-login']");
	public static By ERROR_MSG_USERNAME = By.cssSelector(".form-note.is-error, div[data-tj-qa='invalid-account-username']");
	public static By ERROR_MSG_PASSWORD = By.cssSelector(".form-note.is-error, div[data-tj-qa='invalid-account-password']");
	
	
	//on create account page
	public static By CREATEACCOUNT_TEXT = By.cssSelector(".account-row-title.register-headline, h1[data-tj-qa='create-account-title']");
	public static By ALREADYHAVEANACCOUNT_LINK = By.cssSelector(".account-registration > form > a, a[data-tj-qa='create-account-back-to-login']");
	public static By CREATEACCOUNT_EMAIL  = By.cssSelector("input[data-tj-qa='create-account-email-input']");
	public static By CREATEACCOUNT_PASSWORD= By.cssSelector("input[data-tj-qa='create-account-password-input']");
	public static By CREATEACCOUNT_BUTTON = By.cssSelector("button[data-tj-qa='create-account-submit']");
	public static By EMAIL_LABEL = By.cssSelector("#create_customer > div:nth-child(7) > label, label[data-tj-qa='create-account-email-text']");
	public static By PASSWORD_LABEL = By.cssSelector("#create_customer > div:nth-child(8) > label, label[data-tj-qa='create-account-password-text']");
	public static By MARKETING_LABEL = By.cssSelector(".account-checkbox-label, label[data-tj-qa='accept-marketing-text']");
	public static By MARKETING_CHCKBOX = By.cssSelector("input[data-tj-qa='accept-marketing-checkbox']");
	public static By CREATEACCOUNT_DISCLAIMER = By.cssSelector(".disclaimer.gdpr_Privacy_Policy");
	public static By PRIVACYPOLICY_LINK = By.cssSelector(".disclaimer.gdpr_Privacy_Policy > a.privacyPolicy_Textlink");
	public static By TERMS_LINK = By.cssSelector(".disclaimer.gdpr_Privacy_Policy> a:nth-child(2)");
	public static By ERRORMESSAGES = By.cssSelector(".error-container-create-account");
	public static By MYACCOUNT_OPTION_IN_ACCOUNT_ICON = By.cssSelector("div.header-main__inner.container > ul > li:nth-child(3) > div > ul > li:nth-child(1) > a");
	public static By LOGOUT_OPTION_IN_ACCOUNT_ICON = By.cssSelector(".header-nav-dropdown > ul> li:nth-child(3) >a");
	public static By CUSTOMER_EMAIL_MESSAGE = By.cssSelector("#customer_login > div:nth-child(5)");
	public static By CUSTOMER_PASSWORD_MESSAGE = By.cssSelector("#customer_login > div:nth-child(7)");
//			public static By CREATEACCOUNT_TEXT1 = By.cssSelector
	
	
//	on recover password page
	public static By RECOVER_PASSWORD_CONTAINER = By.cssSelector(".recover-password-container");
	public static By BACK_TO_lOGIN_LINK = By.cssSelector(".recover-password-container>a, a[data-tj-qa='back-to-login']");
	public static By FORGOT_YOUR_PASSWORD_LINK = By.cssSelector("#customer_login > a, a[data-tj-qa='account-forget-password']");
	public static By RECOVER_YOUR_PASSWORD_HEADING = By.cssSelector("#recover-password-form-validate > div > h2, h2[data-tj-qa='reset-password-title']");
	public static By PLEAZE_ENTER_YOUR_EMAIL_HEADING = By.cssSelector("#recover-password-form-validate > div > p, p[data-tj-qa='reset-password-label']");
	public static By EMAIL_ADDRESS_LABEL = By.cssSelector(".form-row.input-group > label, label[data-tj-qa='forgot-password-email-text']");
	public static By INPUT_BOX_FOR_EMAIL = By.cssSelector("input[data-tj-qa='forgot-password-email-input']");
	public static By SUBMIT_BUTTON = By.cssSelector("input[data-tj-qa='forgot-password-email-submit']");
//	public static By SUBMIT_BUTTON = By.cssSelector("#recover-password-form-new > div:nth-child(4) > input");
	public static By ERROR_MESSAGE = By.cssSelector("li[data-tj-qa='forgot-password-email-invalid']");
	public static By EMAIL_RECIEVED_SUCCESS_MESSAGE = By.cssSelector("div.account-reset-password-sent.reset-success > p");
	public static By LINK_IN_ERROR_MESSAGE_FROM_RECOVER_PASSWORD = By.cssSelector("div.input-group > ul > li > a");
	
	
//	On Create New Password Page
	public static By CREATE_NEW_PASSWORD_HEADING = By.cssSelector(".account-row-title.account-text-center");
	public static By CREATE_NEW_PASSWORD_SUB_HEADING = By.cssSelector(".account-row-title.account-text-center+p");
	public static By CREATE_NEW_PASSWORD_LABEL = By.cssSelector(".account-logged-out-row form .input-group:nth-child(3)>label");
	public static By CREATE_NEW_PASSWORD_INPUT = By.cssSelector(".account-logged-out-row form .input-group:nth-child(3)>input");
	public static By CONFIRM_NEW_PASSWORD_LABEL = By.cssSelector(".account-logged-out-row form .input-group:nth-child(4)>label");
	public static By CONFIRM_NEW_PASSWORD_INPUT = By.cssSelector(".account-logged-out-row form .input-group:nth-child(4)>input");
	public static By CHANGE_PASSWORD_BTN = By.cssSelector(".account-logged-out-row form .input-group:nth-child(5)>button");
	public static By BACK_TO_LOGIN = By.cssSelector("a[aria-label='Back to Login']");
	
	
	
}
