package com.tommyjohn.automation.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.CollectionPageComponent;
import com.tommyjohn.automation.Components.HomePageComponents;
import com.tommyjohn.automation.commonMethods.ExcelUtils;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
	
	

public class CollectionPage extends CollectionPageComponent{

	GeneralMethods objGeneralMethods = new GeneralMethods();
	ExcelUtils objExcelUtils = new ExcelUtils();
	public CollectionPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	//Mega collections

	public void validateproductimageonAllpanties() throws Exception
	{
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		Verify_ProductTile_HoverFunctionality();
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		validateProductImageOnCollectionPage();
	}
	
	public void validateproductimageonMegaCollectionPLP() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		Verify_ProductTile_HoverFunctionality();
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		validateProductImageOnPLP();
	}
//	public void validateproductimageonMegaCollectionPLP() throws Exception
//	{
//		
//		String url = objExcelUtils.readExcelValue("C:\\Users\\Annada Behera\\eclipse-workspace\\TJ_Automation\\src\\main\\resources\\PLP_url.xlsx", "Mega_PLP", "Mega_Collection");
//		new HomePageComponents(driver).navigateToPLP(url);
//		validateProductImageOnPLP();
//	}
//	public void validateproductnameonMegaCollectionPLP() throws Exception
//	{
//		String url = objExcelUtils.readExcelValue("C:\\Users\\Annada Behera\\eclipse-workspace\\TJ_Automation\\src\\main\\resources\\PLP_url.xlsx", "Mega_PLP", "Mega_Collection");
//		new HomePageComponents(driver).navigateToPLP(url);
//		validateProductHeadingTextOnPLP();
//	}
//	public void validateproductreviewstarsonMegaCollectionPLP() throws Exception
//	{
//		String url = objExcelUtils.readExcelValue("C:\\Users\\Annada Behera\\eclipse-workspace\\TJ_Automation\\src\\main\\resources\\PLP_url.xlsx", "Mega_PLP", "Mega_Collection");
//		new HomePageComponents(driver).navigateToPLP(url);
//		validateProductReviewStarsOnPLP();
//	}
//
//	public void validatefilterandsortonMegaCollectionPLP() throws Exception
//	{
//		String url = objExcelUtils.readExcelValue("C:\\Users\\Annada Behera\\eclipse-workspace\\TJ_Automation\\src\\main\\resources\\PLP_url.xlsx", "Mega_PLP", "Mega_Collection");
//		new HomePageComponents(driver).navigateToPLP(url);
//		methodToCheckAllFilters();
//	}
//	public void validateColorSwatcheonMegaCollectionPLP() throws Exception
//	{
//		String url = objExcelUtils.readExcelValue("C:\\Users\\Annada Behera\\eclipse-workspace\\TJ_Automation\\src\\main\\resources\\PLP_url.xlsx", "Mega_PLP", "Mega_Collection");
//		new HomePageComponents(driver).navigateToPLP(url);
//		MethodToCheckColorSwatchChanging();
//	}
	

	public void validateproductnameonAllpanties() throws Exception
	{
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateproductreviewstarsonAllpanties() throws Exception
	{
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		validateProductReviewStarsOnCollectionPage();
	}

	public void validatefilterandsortonAllpanties() throws Exception
	{
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		methodToCheckAllFilters();
	}

	public void validatestylecarouselonAllpanties() throws Exception
	{
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		methodToCheckCorosalsOnTopOfPageIsActiveWhenRelatedStyleIsChecked();
	}
	public void validateColorSwatcheonAllpanties() throws Exception
	{
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		MethodToCheckColorSwatchChanging();
	}
	
	public void validateproductimageonAllUnderwear() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		Verify_ProductTile_HoverFunctionality();
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		validateProductImageOnCollectionPage();
	}

	public void validateproductnameonAllUnderwear() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateproductreviewstarsonAllUnderwear() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		validateProductReviewStarsOnCollectionPage();
	}

	public void validatefilterandsortonAllUnderwear() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		methodToCheckAllFilters();
	}

	public void validatestylecarouselonAllUnderwear() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		methodToCheckCorosalsOnTopOfPageIsActiveWhenRelatedStyleIsChecked();
	}
	public void validateColorSwatcheonAllUnderwear() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		MethodToCheckColorSwatchChanging();
	}
	
	public void validateproductimageonAllUndershirts() throws Exception {

		new HomePageComponents(driver).navigateToAllUnderShirtsInMensCategory();
		Verify_ProductTile_HoverFunctionality();
		new HomePageComponents(driver).navigateToAllUnderShirtsInMensCategory();
		validateProductImageOnCollectionPage();
	}

	public void validateproductnameonAllUndershirts() throws Exception 
	{
		new HomePageComponents(driver).navigateToAllUnderShirtsInMensCategory();
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateproductreviewstarsonAllUndershirts() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderShirtsInMensCategory();
		validateProductReviewStarsOnCollectionPage();
	}

	public void validatefilterandsortonAllUndershirts() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderShirtsInMensCategory();
		methodToCheckAllFilters();
	}

	public void validatestylecarouselonAllUndershirts() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderShirtsInMensCategory();
		methodToCheckCorosalsOnTopOfPageIsActiveWhenRelatedStyleIsChecked();
	}
	public void validateColorSwatcheonAllUndershirts() throws Exception
	{
		new HomePageComponents(driver).navigateToAllUnderShirtsInMensCategory();
		MethodToCheckColorSwatchChanging();
	}

	public void validateproductimageonAllWomensCollection() throws Exception {

		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		Verify_ProductTile_HoverFunctionality();
		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		validateProductImageOnCollectionPage();
	}

	public void validateproductnameonAllWomensCollection() throws Exception 
	{
		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateproductreviewstarsonAllWomensCollection() throws Exception
	{
		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		validateProductReviewStarsOnCollectionPage();
	}

	public void validatefilterandsortonAllWomensCollection() throws Exception
	{
		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		methodToCheckAllFilters();
	}

	public void validatestylecarouselonAllWomensCollection() throws Exception
	{
		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		methodToCheckCorosalsOnTopOfPageIsActiveWhenRelatedStyleIsChecked();
	}
	public void validateColorSwatcheonAllWomensCollection() throws Exception
	{
		new HomePageComponents(driver).navigateToAllWomensCollectionInWomensCategory();
		MethodToCheckColorSwatchChanging();
	}

	//Single Collections

	public void validateproductimageonOnSecondSkin() throws Exception {

		new HomePageComponents(driver).navigateToSecondSkinInMenCategory();
		//Verify_ProductTile_HoverFunctionality();
		validateProductImageOnPLP();
	}

	public void validateproductnameonOnSecondSkin() throws Exception 
	{
		new HomePageComponents(driver).navigateToSecondSkinInMenCategory();
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateproductreviewstarsonOnSecondSkin() throws Exception
	{
		new HomePageComponents(driver).navigateToSecondSkinInMenCategory();
		validateProductReviewStarsOnCollectionPage();
	}

	public void validatefilterandsortonOnSecondSkin() throws Exception
	{
		new HomePageComponents(driver).navigateToSecondSkinInMenCategory();
		methodToCheckAllFilters();
	}
	public void validateColorSwatcheonSecondSkin() throws Exception
	{
		new HomePageComponents(driver).navigateToSecondSkinInMenCategory();
		MethodToCheckColorSwatchChanging();
	}

	public void validateproductimageonOnCoolCotton() throws Exception {

		new HomePageComponents(driver).navigateToCoolCottonInMensCollection();
		Verify_ProductTile_HoverFunctionality();
		new HomePageComponents(driver).navigateToCoolCottonInMensCollection();
		validateProductImageOnCollectionPage();
	}

	public void validateproductnameonOnCoolCotton() throws Exception 
	{
		new HomePageComponents(driver).navigateToCoolCottonInMensCollection();
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateproductreviewstarsonOnCoolCotton() throws Exception
	{
		new HomePageComponents(driver).navigateToCoolCottonInMensCollection();
		validateProductReviewStarsOnCollectionPage();
	}

	public void validatefilterandsortonOnCoolCotton() throws Exception
	{
		new HomePageComponents(driver).navigateToCoolCottonInMensCollection();
		methodToCheckAllFilters();
	}
	public void validateColorSwatcheonCoolCotton() throws Exception
	{
		new HomePageComponents(driver).navigateToCoolCottonInMensCollection();
		MethodToCheckColorSwatchChanging();
	}
	
	public void validateproductimageonOnCottonBasics() throws Exception {

		new HomePageComponents(driver).navigateToCottonBasicsInMensCategory();
	//	Verify_ProductTile_HoverFunctionality();
		validateProductImageOnCollectionPage();
	}

	public void validateproductnameonOnCottonBasics() throws Exception 
	{
		new HomePageComponents(driver).navigateToCottonBasicsInMensCategory();
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateproductreviewstarsonOnCottonBasics() throws Exception
	{
		new HomePageComponents(driver).navigateToCottonBasicsInMensCategory();
		validateProductReviewStarsOnCollectionPage();
	}

	public void validatefilterandsortonOnCottonBasics() throws Exception
	{
		new HomePageComponents(driver).navigateToCottonBasicsInMensCategory();
		methodToCheckAllFilters();
	}
	public void validateColorSwatcheonCottonBasics() throws Exception
	{
		new HomePageComponents(driver).navigateToCottonBasicsInMensCategory();
		MethodToCheckColorSwatchChanging();
	}
	
	public void validateproductimageonOnPacks() throws Exception {

		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
		Verify_ProductTile_HoverFunctionality();
		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
		validateProductImageOnCollectionPage();
	}

	public void validateproductnameonOnPacks() throws Exception 
	{
		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateproductreviewstarsonOnPacks() throws Exception
	{
		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
		validateProductReviewStarsOnCollectionPage();
	}

	public void validatefilterandsortonOnPacks() throws Exception
	{
		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
		methodToCheckAllFilters();
	}
	
	public void validateSortFilterOnGCPLP() throws Exception
	{
		new HomePageComponents(driver).NavigateToGCPLP();
		methodToCheckAllFilters();
	}
	public void validateProductReviewStarsOnGCPLP() throws Exception
	{
		new HomePageComponents(driver).NavigateToGCPLP();
		validateReviewStarOnGCPLP();
	}
	public void validateColorSwatcheonGCPLP() throws Exception
	{
		new HomePageComponents(driver).NavigateToGCPLP();
		MethodToCheckColorSwatchChanging();
	}
	public void validateProductNameOnGCPLP() throws Exception
	{
		new HomePageComponents(driver).NavigateToGCPLP();
		validateProductHeadingTextOnCollectionPage();
	}

	// All collection URLs
	public void validateallcollectionurls() throws Exception
	{
		validateallurls();
	}

	public void validateallplpsareloading() throws Exception {
		validateallurls();
	}

	public void validateproductgrid() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToNewArrivalsInMenCategory();
		validateProductGrid();
	}
	
	public void validateBreadcrumbLinks() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		validatePLPBreadcrumbLinks();
	}
	
	public void validateCategoryPageHeader() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		validatePLPHeader();
	}
	
	public void validateCategorySectionHeaders() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToNewArrivalsInMenCategory();
		validatePLPSectionHeaders();
	}

	public void validateInlineBanner() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToBraInWomenCategory();
		validatePLPInlineBanner();
	}

	public void ValidateOnClickingTheInLineBanner() throws InterruptedException {// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToWomensUnderwear();
		new CollectionPageComponent(driver).ValidateOnClickingTheInLineBanner();
	}
	
	public void validateFilters() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToMenssleepLoungeCategory();
		methodToCheckAllFilters();
	}
	
	public void validateSortBy() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).NavigateToAllLoungewearInMenCategory();
		methodToSortByPrice();
	}
	
	public void validateOnImageAndSecondaryBadging() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).NavigateToAllSaleInSaleCategory();
		validateOnImageAndSecondaryBadges();
	}
	
	public void validateSEOCopy() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToBoxerBriefInMenCategory();
		validateSEO();
	}
	
	public void validateProductTile() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToMensUWCAT();
		validateProductHeadingTextOnCollectionPage();
		Verify_ProductTile_HoverFunctionality();
		validateProductImageOnPLP();
		MethodToCheckColorSwatchChanging();
		validateProductReviewStarsOnCollectionPage();
		
	}
	
	public void validateQuickshopFunctionality() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToNewArrivalsInMenCategory();
		QuickShopOnMegaCollection();
		
		
	}
	
	public void validateSingleTileProduct() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).NavigateToplpwithsingleproduct();
		validateSingleTile();
	}
	
	public void validateStyleCarousels() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		validateStyleCarousel();
	}
	
	public void validateMultiVariantSizeInQuickshop() throws Exception {
		// TODO Auto-generated method stub
		new HomePageComponents(driver).NavigateToLoungewearSetsInWomenCategory();
		QuickShopOnMegaCollection();
	}
  
  
   public void validateFiltersSearch() throws Exception {
                // TODO Auto-generated method stub
                methodToCheckFiltersSearch();
   }
      public void validateOnImageBadgeSearch() throws Exception {
                
                //validateOnImageBadging();
                validateOnImageAndSecondaryBadges();
                
        }
     
      public void validateProductTileSearch() throws Exception {
                // TODO Auto-generated method stub
    	  validateProductHeadingTextOnCollectionPage();
  		validateProductImageOnPLP();
  		Verify_ProductTile_HoverFunctionality();
  		MethodToCheckColorSwatchChanging();
  		validateProductReviewStarsOnCollectionPage();
        }
     
      public void validateQuickshopFunctionalityOnSearchPLP() throws Exception {
                // TODO Auto-generated method stub
    	  QuickShopOnMegaCollection();
    	  
                
               
        }
     
     public void validateSingleTileProductSearch() throws Exception {
                // TODO Auto-generated method stub
                validateSingleTileSearch();
        }

	public void validateSortBySearch() throws Exception {
		// TODO Auto-generated method stub
		validateSortBy();
	}

	public void ValidateProductsOnFiltering() throws InterruptedException {

		new CollectionPageComponent(driver).ValidateProductsOnFiltering();
	}

	public void ValidateSizeFiltersCountInAllFiltersDropdownAndInSizeDropdown()  {

		new CollectionPageComponent(driver).ValidateSizeFiltersCountInAllFiltersDropdownAndInSizeDropdown();
	}

	public void ValidateSizeFiltersNameInAllFiltersDropdownAndInSizeDropdown()  {

		new CollectionPageComponent(driver).ValidateSizeFiltersNameInAllFiltersDropdownAndInSizeDropdown();
	}

	public void ValidateColorFiltersCountInAllFiltersDropdownAndInColorDropdown()  {

		new CollectionPageComponent(driver).ValidateColorFiltersCountInAllFiltersDropdownAndInColorDropdown();
	}

	public void ValidateColorFiltersNameInAllFiltersDropdownAndInColorDropdown()  {

		new CollectionPageComponent(driver).ValidateColorFiltersNameInAllFiltersDropdownAndInColorDropdown();
	}

	public void ValidateCategoryFiltersCountInAllFiltersDropdownAndInCategoryDropdown()  {

		new CollectionPageComponent(driver).ValidateCategoryFiltersCountInAllFiltersDropdownAndInCategoryDropdown();
	}

	public void ValidateCategoryFiltersNameInAllFiltersDropdownAndInCategoryDropdown()  {

		new CollectionPageComponent(driver).ValidateCategoryFiltersNameInAllFiltersDropdownAndInCategoryDropdown();
	}

	public void ValidateCollectionFiltersCountInAllFiltersDropdownAndInCollectionDropdown()  {

		new CollectionPageComponent(driver).ValidateCollectionFiltersCountInAllFiltersDropdownAndInCollectionDropdown();
	}

	public void ValidateCollectionFiltersNameInAllFiltersDropdownAndInCollectionDropdown()  {

		new CollectionPageComponent(driver).ValidateCollectionFiltersNameInAllFiltersDropdownAndInCollectionDropdown();
	}


}



//	public void validateProductImage() throws Exception
//	{
//		validateProductImageOnCollectionPage();
//	}
//
//	public void validateProductHeadingText() throws Exception
//	{
//		validateProductHeadingTextOnCollectionPage();
//	}
//
//	public void validateProductReviewStars() throws Exception
//	{
//		validateProductReviewStarsOnCollectionPage();
//	}
//
//	public void validateProductImageOnSingleCollectionPage() throws Exception
//	{
//		validateProductImageForSingleCollection();
//	}
//
//	public void validateProductHeadingTextOnSingleCollectionPage() throws Exception
//	{
//		validateProductHeadlineTextForSingleCollection();
//	}
//	
//	public void validateProductReviewStarsOnSingleCollectionPage() throws Exception
//	{
//		validateProductReviewStarsForSingleCollection();
//	}

