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

	public void ValidatePromoCodeFIRST20InInformationPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeFIRST20InInformationPage();

	}

	public void ValidatePromoCodeFIRST20InShippingPage() throws Exception
	{
		new CheckoutPageComponents(driver).ValidatePromoCodeFIRST20InShippingPage();

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
