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


	public void VerifyGCFraudDisclosureInCheckoutPage() throws InterruptedException {
		new CheckoutPageComponents(driver).VerifyGCFraudDisclosureInCheckoutPage();

	}

	public void VerifSplitBundleProduct() throws InterruptedException {
		new CheckoutPageComponents(driver).VerifySplitBundleProduct();

	}

	public void VerifyGCFraudDisclosureInShippingPage() throws Exception
	{
		new CheckoutPageComponents(driver).VerifyGiftCardFraudDisclosureInShippingPage();

	}

	public void VerifyGCFraudDisclosureInPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).VerifyGiftCardFraudDisclosureInPaymentPage();

	}

	public void ValidatePromoCodeErrorScenarioInCheckoutPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeErrorScenarioInCheckoutPage();

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

	public void ValidateOrderProtectionInCheckoutPage() throws InterruptedException {
		new CheckoutPageComponents(driver).ValidateOrderProtectionInCheckoutPage();

	}

	public void ValidatePromoCodeErrorScenarioInPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeErrorScenarioInPaymentPage();

	}


	public void ValidatePromoCodeFIRST20InCheckoutPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeFIRST20InCheckoutPage();

	}

	public void VerifyCartIconInCheckoutPage() throws InterruptedException {
		new CheckoutPageComponents(driver).VerifyCartIconInCheckoutPage();

	}

	public void ValidateCartIconInCheckoutPage() throws InterruptedException {
		new CheckoutPageComponents(driver).ValidateCartIconInCheckoutPage();

	}

	public void ValidatePromoCodeFIRST20InShippingPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeFIRST20InShippingPage();

	}

	public void ValidatePromoCodeFIRST20InPaymentPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeFIRST20InPaymentPage();

	}

	public void Validate5for$65functionalityinCheckoutPage() throws Exception {
		new CheckoutPageComponents(driver).Validate5for$65functionalityinCheckoutPage();

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
	public void ValidateContactSectionOfCheckout() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateContactSectionOfCheckout();

	}

	public void ValidateFieldsinCheckout() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateFieldsinCheckout();

	}

	public void ValidateshippingSection() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateshippingSection();
		
	}
	
	public void Validatepaymentsection() throws Exception
	{
		new CheckoutPageComponents(driver).Validatepaymentsection();
		
	}

	public void ValidateFieldsinIPaymentSection() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateFieldsinIPaymentSection();

	}

	public void Validatetextmewithnewslettersandoffers() throws Exception
	{
		new CheckoutPageComponents(driver).Validatetextmewithnewslettersandoffers();

	}

	public void ValidateSaveMyInformationInPaymentSection() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateSaveMyInformationInPaymentSection();

	}

	public void ValidateBillingAddressFieldInPaymentSection() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateBillingAddressFieldInPaymentSection();

	}

	public void ValidateRequiredBillingAddressFieldsInPaymentSection() throws Exception
	{
		new CheckoutPageComponents(driver).ValidateRequiredBillingAddressFieldsInPaymentSection();

	}

	public void ValidateOrderPlacement() throws Exception {
		
		new CheckoutPageComponents(driver).completeorder();
		
	}
	
}
