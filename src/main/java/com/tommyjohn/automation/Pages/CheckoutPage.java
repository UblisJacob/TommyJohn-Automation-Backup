package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.CheckoutPageComponents;

public class CheckoutPage extends CheckoutPageComponents {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void ValidateExpressCheckoutOptions() throws Exception
	{
		new CheckoutPageComponents(driver).expresscheckout();
	}


	public void VerifyGCFraudDisclosureInInformationPage() throws Exception
	{
		new CheckoutPageComponents(driver).VerifyGiftCardFraudDisclosureInInformationPage();

	}

	public void ValidatePromoCodeErrorScenarioInInformationPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeErrorScenarioInInformationPage();

	}

	public void ValidatePromoCodeNotAppliedForFinalSaleProducts() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoNotAppliedForFinalSaleProducts();

	}

	public void validateFreeShipping() throws Exception
	{
		new CheckoutPageComponents(driver).validateFreeShipping();

	}

	public void validateNonFreeShipping() throws Exception
	{
		new CheckoutPageComponents(driver).validateNonFreeShipping();

	}


	public void ValidatePromoCodeErrorScenarioInShippingPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeErrorScenarioInShippingPage();

	}

	public void ValidatePromoCodeErrorScenarioInPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeErrorScenarioInPaymentPage();

	}


	public void ValidatePromoCodeFIRST20InInformationPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeFIRST20InInformationPage();

	}

	public void ValidatePromoCodeFIRST20InShippingPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeFIRST20InShippingPage();

	}

	public void ValidatePromoCodeFIRST20InPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeFIRST20InPaymentPage();

	}


	public void Validate3for$48functionalityinInformationPage() throws Exception {
		new CheckoutPageComponents(driver).Validate3for$48functionalityinInformationPage();

	}

	public void Validate3for$48functionalityinShippingPage() throws Exception {
		new CheckoutPageComponents(driver).Validate3for$48functionalityinShippingPage();

	}

	public void Validate3for$48functionalityinPaymentPage() throws Exception {
		new CheckoutPageComponents(driver).Validate3for$48functionalityinPaymentPage();

	}
	
	public void Validateguestcheckout() throws Exception
	{
		new CheckoutPageComponents(driver).guestcheckoutflow();
		
	}
	
	public void Validateregisterusercheckout() throws Exception
	{
		new CheckoutPageComponents(driver).registeredusercheckoutflow();
		
	}
	public void Validateinformationpage() throws Exception
	{
		new CheckoutPageComponents(driver).informationpage();

	}

	public void ValidateFieldsinInformationPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateRequiredFieldsinInformationpage();

	}

	public void Validateshippingpage() throws Exception
	{
		new CheckoutPageComponents(driver).shippingpage();
		
	}
	
	public void Validatepaymentpage() throws Exception
	{
		new CheckoutPageComponents(driver).paymentpage();
		
	}

	public void ValidateFieldsinIPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateRequiredFieldsinPaymentpage();

	}

	public void Validatetextmewithnewslettersandoffers() throws Exception
	{
		new CheckoutPageComponents(driver).Validatetextmewithnewslettersandoffers();

	}

	public void ValidateSaveMyInformationInPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateSaveMyInformationInPaymentPage();

	}

	public void ValidateBillingAddressFieldInPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateBillingAddressFieldInPaymentPage();

	}

	public void ValidateRequiredBillingAddressFieldsInPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateRequiredBillingAddressFieldsInPaymentPage();

	}

	public void ValidateOrderPlacement() throws Exception {
		
		new CheckoutPageComponents(driver).completeorder();
		
	}
	
}
