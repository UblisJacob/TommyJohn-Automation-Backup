package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.AddToCartComponents;
import com.tommyjohn.automation.Components.ProductDetailsPageComponents;
import com.tommyjohn.automation.Components.ShoppingCartComponents;

public class ShoppingCartPage extends ShoppingCartComponents{
	
	WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void ValidateEmptyCart() throws Exception
	{
		new ShoppingCartComponents(driver).EmptyCart();
	}
	
	public void ValidateViewCartLink() throws Exception
	{
		
		new ShoppingCartComponents(driver).clickOnViewCartLink();
	}
	
	public void ValidateShoppingCartWithProducts() throws Exception
	{
		new AddToCartComponents(driver).addToCart();
		new ShoppingCartComponents(driver).CartwithProduct();
	}
	
	public void ValidateAddRemoveProducts() throws Exception
	{
		new AddToCartComponents(driver).addToCart();
		new ShoppingCartComponents(driver).AddRemoveProductsFromCart();
	}
	
	public void validatePromotion() throws Exception
	{
		new AddToCartComponents(driver).addsingleproductToCart();
		new ShoppingCartComponents(driver).validatePromotionalMessage();
	}
	
	public void validateCartPaymentMethods() throws Exception
	{
		new ShoppingCartComponents(driver).cartPaymentMethods();
	}
	public void verifyCheckoutfromCart() throws Exception
	{
		new AddToCartComponents(driver).addToCart();
		new ShoppingCartComponents(driver).CheckoutPageFromCart();
	}
	
	public void verifyproductinCart() throws Exception
	{
		new AddToCartComponents(driver).addToCart();
		new ShoppingCartComponents(driver).productDetailsInCart();
	}
	
	public void VerifyPromoCodeOnCart() throws Exception
	{
		new AddToCartComponents(driver).addToCart();
		new ShoppingCartComponents(driver).Promo_Code_OncartPage();
	}


}
