package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

//import com.tommyjohn.automation.Components.BlogPageComponents;
import com.tommyjohn.automation.Components.HomePageComponents;
import com.tommyjohn.automation.Components.ProductDetailsPageComponents;

public class HomePage extends HomePageComponents {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void logoandcategoriespresence() throws Exception
	{
		ValidateLogoAndCategoriesPresence();
	}

	public void verifyFooterSection() throws Exception
	{
		validateFooterShopSection();
		validateFooterPromotionsSection();
		validateFooterAccountSection();
		validateFooterSupportSection();
		validateFooterCompanySection();
		validateFooterSocialLinks();
		validateFooterCopyrightCCPASection();
		validateFooterNewsletter();
	}
	

	public void verifyaccountdropdown() throws Exception
	{

		validateaccountdropdown();
	}

	public void verifycart() throws Exception
	{
		validateCartIcon();
	}

	public void verifyaccounticon() throws Exception
	{
		validateAccountIcon();

	}
	public void validateSearch() throws Exception
	{	

		validateSearchFunctionality();

	}
	public void validateSearchPM(String searchText) throws Exception
	{
		
		validateSearchFunction(searchText);

	}
	public void validateSearchFunctionEnterKeyword(String searchText) throws Exception
	{
		
		validateSearchFunctionEnter(searchText);

	}
	
	

	public void validateDoNotSellMyPersonalInformation() throws Exception
	{
		new HomePageComponents(driver).validate_DoNotSellMyPersonalInfo();

	}

	public void verifyrecommendedforyou() throws Exception {

		new ProductDetailsPageComponents(driver).verifyRecommendedForU();
	
	}

	public void verifyQuickShopforDYRecsOnHomePage() throws Exception {

		new HomePageComponents(driver).VerifyQuickShopOnDYRecs();

	}

	public void verifyColorSwatchforDYRecsOnHomePage() throws Exception {

		new HomePageComponents(driver).MethodToCheckColorSwatchOnDYRecs();

	}
	
	public void verifyRFYProductTileHoverFunctionality() throws Exception
	{
		new HomePageComponents(driver).RFY_ProductTileHoverFunctionality();
	}

	//new
	public void validateNavigationToMensLoungewearCategory() throws Exception
	{
		new HomePageComponents(driver).navigateToMensLoungewearCategory();
	}

	public void ValidateAddingProductToCartFromInstagramWidget() throws Exception {
		new HomePageComponents(driver).ValidateAddingProductToCartFromInstagramWidget();
	}

	public void ValidateAppropriateProductIsAddedToCartFromInstagramWidget() throws Exception {
		new HomePageComponents(driver).ValidateAppropriateProductIsAddedToCartFromInstagramWidget();
	}

	public void ValidateSizeOverlayInInstagramWidget() throws Exception {
		new HomePageComponents(driver).ValidateSizeOverlayInInstagramWidget();
	}


	public void verifySelectedColorSwatchIsDisplayedInTheInlineCart() throws Exception {
		new HomePageComponents(driver).verifySelectedColorSwatchIsDisplayedInTheInlineCart();
	}

	public void VerifyPopularCategoriesSectionInSearchDropdown() throws InterruptedException {
		new HomePageComponents(driver).VerifyPopularCategoriesSectionInSearchDropdown();
	}

	public void VerifyCategoriesInPopularCategoriesSection() throws InterruptedException {
		new HomePageComponents(driver).VerifyCategoriesInPopularCategoriesSection();
	}

	public void VerifyPopularTermsSectionInSearchDropdown() throws InterruptedException {
		new HomePageComponents(driver).VerifyPopularTermsSectionInSearchDropdown();
	}

	public void VerifyCategoriesInPopularTermsSection() throws InterruptedException {
		new HomePageComponents(driver).VerifyCategoriesInPopularTermsSection();
	}

	public void ValidateNoResultPageOnSearch() throws InterruptedException {
		new HomePageComponents(driver).ValidateNoResultPageOnSearch();
	}

//	public void validateBlogPage() throws Exception
//	{
//		new BlogPageComponents(driver).validateBlog();
//	}
//	
}
