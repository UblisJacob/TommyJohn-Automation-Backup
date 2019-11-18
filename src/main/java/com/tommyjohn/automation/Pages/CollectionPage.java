package com.tommyjohn.automation.Pages;

import org.openqa.selenium.WebDriver;

import com.tommyjohn.automation.Components.CollectionPageComponent;
import com.tommyjohn.automation.Components.HomePageComponents;

public class CollectionPage extends CollectionPageComponent{

	public CollectionPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	//Mega collections

	public void validateproductimageonAllpanties() throws Exception
	{
		new HomePageComponents(driver).navigateToAllPantiesInWomenCategory();
		validateProductImageOnCollectionPage();
	}

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

	public void validateproductimageonAllUnderwear() throws Exception
	{
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

	public void validateproductimageonAllUndershirts() throws Exception {

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

	public void validateproductimageonAllWomensCollection() throws Exception {

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


	//Single Collections

	public void validateproductimageonOnSecondSkin() throws Exception {

		new HomePageComponents(driver).navigateToSecondSkinInMenCategory();
		validateProductImageOnCollectionPage();
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

	public void validateproductimageonOnCoolCotton() throws Exception {

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
	
	public void validateproductimageonOnCottonBasics() throws Exception {

		new HomePageComponents(driver).navigateToCottonBasicsInMensCategory();
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
	
	public void validateproductimageonOnPacks() throws Exception {

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
////--------------------------validate mega collections------------------------
//	
//	public void validateAllClothingOnCollectionPage() throws Exception {
//		validateMegaCollectionAllClothingInWomensCategory();
//	}
//	
//	public void validateWomensAllPantiesOnCollectionPage() throws Exception {
//		validateMegaCollectionAllPantiesInWomensCategory();
//	}
//	public void validateMensAllUnderwearOnCollectionPage() throws Exception {
//		validateMegaCollectionAllUnderwearInMensCategory();
//	}
//	public void validateMensAllUnderShirtsOnCollectionPage() throws Exception {
//		validateMegaCollectionAllUndershirtsInMensCategory();
//	}
//	public void validateMensAllCollectionsOnCollectionPage() throws Exception {
//		validateMegaCollectionAllCollectionsInMensCategory();
//	}
//	
//	
////---------------------------validate single collections---------------------------
//	
//	public void validateSecondSkinOnSingleCollectionPage() throws Exception {
//		validateSingleCollectionSecondSkinInMenCategory();
//	}
//	
//	public void validateCoolCottonOnSingleCollectionPage() throws Exception {
//		validateSingleCollectionCoolCottonInMenCategory();
//	}
//	public void validatePacksOnSingleCollectionPage() throws Exception {
//		validateSingleCollectionPacksInWomenCategory();
//	}
//	
//	public void validateCottonBasicsOnSingleCollectionPage() throws Exception {
//		validateSingleCollectionCottonBasicsInShopByCollectionsCategory();
//	}
//
//}
