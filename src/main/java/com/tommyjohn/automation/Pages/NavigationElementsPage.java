package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.NavigationElementComponents;

public class NavigationElementsPage extends NavigationElementComponents{
	
	public NavigationElementsPage(WebDriver driver) {
		super(driver);
		
	}

	/**
	 * This TC validates User is able to see up to four links to the left of the Tommy John logo.
	 */
	public void validateNavLinks() {
		validateNavLinksDesktop();
	}
	
	/**
	 * User should be able to see the Tommy John logo in the middle of the header.
	 * User should see search, account and cart elements.
	 */
	public void validateLogoAndRightSideLinks() {
		validateLogoAndRightSideLinksDesktop();
	}
	
	/**
	 * User should be able to click and type in the search bar.
	 * @throws InterruptedException 
	 */
	public void validateSearchBar() throws InterruptedException {
		validateSearchBarDesktop();
	}
	
	/**
	 * 
	 * If a user is not signed in, they should see "Sign In." 
	 * On hover, "Sign In" should display a see a dropdown with My Account, Help Center, and Sign In.
	 * Clicking "Sign In" should take the user to the Login page. 
	 * Clicking "My Account" should take the user to the Login page.
	 * Clicking "Help Center" should take the user to the Help Center.
	 */
	public void validateElementsWhenNotSignedIn(){
		validateElementsWhenNotSignedInDesktop();
	}
	
	/**
	 * 
	 * If a user is not signed in, they should see "Sign In." 
	 * On hover, "Sign In" should display a see a dropdown with My Account, Help Center, and Sign In.
	 * Clicking "Sign In" should take the user to the Login page. 
	 * Clicking "My Account" should take the user to the Login page.
	 * Clicking "Help Center" should take the user to the Help Center.
	 * @throws Exception 
	 */
	public void validateElementsWhenSignedIn() throws Exception{
		validateElementsWhenSignedInDesktop();
	}
	
	public void validateCartIcon() throws InterruptedException {
		validateCartIconInteraction();
	}
	
	/**
	 * Generic Method for navigation and Validation
	 */
	public void validateNavigation() {
		level_0_nav(2);
//		level_1_nav(1, 2);
//		level_2_nav(1, 2, 2);
//		level_2_nav_links(6);
//		navigateShopAllLinks(2, 2);
	}
	
	/**
	 * Validate Mens Quiz Page
	 */
	public void validateAllQuizLandingPages() {
		validateMensUWQuizPage();
		validateWomensUWQuizPage();
		validateWomensBraQuizPage();
		
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
