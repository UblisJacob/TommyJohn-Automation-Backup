package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.CheckoutPageComponents;
import com.tommyjohn.automation.Components.FlyCartComponents;
//import com.tommyjohn.automation.Components.GiftCardPageComponents;
import com.tommyjohn.automation.Components.ProductDetailsPageComponents;
import com.tommyjohn.automation.Components.ShoppingCartComponents;

public class AfterPay {
	
	WebDriver driver;

	public AfterPay(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}

	public void AfterPayOnPDP() throws Exception
	{
		new ProductDetailsPageComponents(driver).validateafterpayonpdp();
	}

	public void AfterPayOnInlineCart() throws Exception
	{
		new FlyCartComponents(driver).validateafterpayoninlinecart();
	}
	
	public void AfterPayOnShoppingCart() throws Exception
	{
		new ShoppingCartComponents(driver).validateafterpayonShoppingCart();
	}
	
	public void AfterPayOnCheckoutPage() throws Exception
	{
		new CheckoutPageComponents(driver).AfterpayonCheckout();
	}
	
//	public void AfterPayOnGiftCardPage() throws Exception
//	{
//		new GiftCardPageComponents(driver).CheckAfterPayMessageonGC();
//	}
}

