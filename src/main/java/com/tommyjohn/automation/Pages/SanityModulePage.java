package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.SanityModuleComponents;

public class SanityModulePage extends SanityModuleComponents {
	
	public SanityModulePage(WebDriver driver) {
		super(driver);
		
		// TODO Auto-generated constructor stub
	}
	public void verifyHeroModule() throws Exception {
		
		new SanityModuleComponents(driver).verifyHeroModule();
	}
	public void verifyFixedBannerModule() throws Exception {
		
		new SanityModuleComponents(driver).verifyFixedBannerModule();
	}
	public void verifyThreeTileModule() throws Exception {
		
		new SanityModuleComponents(driver).verifyThreeTileModule();
	}
	public void verifyImagecarousel() throws Exception {
		
		new SanityModuleComponents(driver).verifyImagecarousel();
	}
	public void verifyFixedBannerModuleLinks() throws Exception {
		
		new SanityModuleComponents(driver).verifyFixedBannerModuleLinks();
	}
	public void verifySideBySideModule() throws Exception {
		
		new SanityModuleComponents(driver).verifySideBySideModule();
	}
	
}
