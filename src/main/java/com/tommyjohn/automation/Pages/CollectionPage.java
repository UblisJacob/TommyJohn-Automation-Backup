package com.tommyjohn.automation.Pages;

import com.tommyjohn.automation.Components.CollectionPageComponent;

public class CollectionPage extends CollectionPageComponent{

	public void validateProductImage() throws Exception
	{
		validateProductImageOnCollectionPage();
	}

	public void validateProductHeadingText() throws Exception
	{
		validateProductHeadingTextOnCollectionPage();
	}

	public void validateProductReviewStars() throws Exception
	{
		validateProductReviewStarsOnCollectionPage();
	}

	public void validateProductImageOnSingleCollectionPage() throws Exception
	{
		validateProductImageForSingleCollection();
	}

	public void validateProductHeadingTextOnSingleCollectionPage() throws Exception
	{
		validateProductHeadlineTextForSingleCollection();
	}
	
	public void validateProductReviewStarsOnSingleCollectionPage() throws Exception
	{
		validateProductReviewStarsForSingleCollection();
	}
//--------------------------validate mega collections------------------------
	
	public void validateAllClothingOnCollectionPage() throws Exception {
		validateMegaCollectionAllClothingInWomensCategory();
	}
	
	public void validateWomensAllPantiesOnCollectionPage() throws Exception {
		validateMegaCollectionAllPantiesInWomensCategory();
	}
	public void validateMensAllUnderwearOnCollectionPage() throws Exception {
		validateMegaCollectionAllUnderwearInMensCategory();
	}
	public void validateMensAllUnderShirtsOnCollectionPage() throws Exception {
		validateMegaCollectionAllUndershirtsInMensCategory();
	}
	public void validateMensAllCollectionsOnCollectionPage() throws Exception {
		validateMegaCollectionAllCollectionsInMensCategory();
	}
	
	
//---------------------------validate single collections---------------------------
	
	public void validateSecondSkinOnSingleCollectionPage() throws Exception {
		validateSingleCollectionSecondSkinInMenCategory();
	}
	
	public void validateCoolCottonOnSingleCollectionPage() throws Exception {
		validateSingleCollectionCoolCottonInMenCategory();
	}
	public void validatePacksOnSingleCollectionPage() throws Exception {
		validateSingleCollectionPacksInWomenCategory();
	}
	
	public void validateCottonBasicsOnSingleCollectionPage() throws Exception {
		validateSingleCollectionCottonBasicsInShopByCollectionsCategory();
	}

}
