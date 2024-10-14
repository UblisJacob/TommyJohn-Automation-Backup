package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.BundlePDPComponent;

public class BundlePDPPage extends BundlePDPComponent{
	WebDriver driver;

	public BundlePDPPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void viewImageGallery() throws Exception {
		new BundlePDPComponent(driver).viewImageGallery();
	}
	
	public void verifyOnImageBadging() throws Exception {
		new BundlePDPComponent(driver).verifyOnImageBadging();
	}
	
	public void verifySecondaryBadging() throws Exception {
		new BundlePDPComponent(driver).verifySecondaryBadging();
	}
		
	public void verifySelectedColorSwatch() throws Exception {
		new BundlePDPComponent(driver).verifySelectedColorSwatch();
	}
	public void verifyAvailableSizes() throws Exception {
		new BundlePDPComponent(driver).verifyAvailableSizes();
	}
	public void verifyStickyATC() throws Exception {
		new BundlePDPComponent(driver).verifyStickyATC();
	}
	
	public void verifyReviewRecommendations() throws Exception  {
		new BundlePDPComponent(driver).verifyReviewRecommendations();
	}
	public void verifyProductNamePrice() throws Exception  {
		new BundlePDPComponent(driver).verifyProductNamePrice();
	}
	public void verifyAccordions() throws Exception  {
		new BundlePDPComponent(driver).verifyAccordions();
	}
	public void verifyLowStockInventory() throws Exception  {
		new BundlePDPComponent(driver).verifyLowStockInventory();
	}
	

	public void verifyAddToCart() throws Exception  {
		new BundlePDPComponent(driver).verifyAddToCart();
	}

	public void ValidateLearnMoreLink() throws Exception  {
		new BundlePDPComponent(driver).ValidateLearnMoreLink();
	}
}