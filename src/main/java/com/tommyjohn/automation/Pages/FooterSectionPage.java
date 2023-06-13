package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.FooterSectionComponents;

public class FooterSectionPage extends FooterSectionComponents {

	public FooterSectionPage(WebDriver driver) {
		super(driver);

	}

	public void verifyFooterSectionLinks() throws Exception {
		validateFooterShopSection();
		validateFooterPromotionsSection();
		validateFooterAccountSection();
		validateFooterSupportSection();
		validateFooterCompanySection();
	}

	public void verifyFooterSocialLinks() throws Exception {
		validateFooterSocialLinks();
	}

	public void verifyFooterCCPASection() throws Exception {
		validateFooterCopyrightCCPASection();
	}

	public void verifyFooterEmailSignUp() throws Exception {
		validateFooterNewsletter();
	}

	public void verifyFooterFeedback() throws Exception {
		validateFeedbackSection();
	}

}
