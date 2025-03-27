package com.tommyjohn.automation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tommyjohn.automation.Components.CollectionPageComponent;
import com.tommyjohn.automation.Components.HomePageComponents;
import com.tommyjohn.automation.Components.ProductDetailsPageComponents;
//import com.tommyjohn.automation.Components.SizeGuideComponents;
import com.tommyjohn.automation.Components.SizeGuideComponents;

public class ProductDetailsPage extends ProductDetailsPageComponents{
	WebDriver driver;

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void ValidatesectionsinPDP() throws Exception {


		new ProductDetailsPageComponents(driver).ValidatesectionsinPDP();

	}

	public void ValidateDisabledATC() throws Exception {


		new ProductDetailsPageComponents(driver).ValidateDisabledATC();

	}

	public void ValidateTabPriceAndAddToCartPrice() throws InterruptedException {


		new ProductDetailsPageComponents(driver).ValidateTabPriceAndAddToCartPrice();

	}

	public void ValidateClickingOnRatingsTierMeter() throws Exception {


		new ProductDetailsPageComponents(driver).ValidateClickingOnRatingsTierMeter();

	}

	public void VerifyPromoBannerIsAvailableinPDP() throws Exception {


		new ProductDetailsPageComponents(driver).VerifyPromoBannerIsAvailableinPDP();

	}

	public void verifyProductDetailsPage() throws Exception {


		new ProductDetailsPageComponents(driver).validateProductDetailsPage();
	}
	public void verifyReviewsSectionOnPDP() throws Exception {


		new ProductDetailsPageComponents(driver).verifyReviewsDisplay();
	}
	public void verifyWriteReviewsFunctionalityOnPDP() throws Exception {


		new ProductDetailsPageComponents(driver).verifyWriteReview();
	
	}
	public void verifyReviewsFieldErrorOnPDP() throws Exception {

		new ProductDetailsPageComponents(driver).verifyWriteReviewErrorMsgs();
	
	}
	public void verifyBundlePDP() throws Exception {
		new SizeGuideComponents(driver).navigateToBundlePDP();
		new ProductDetailsPageComponents(driver).verifybundlepages();
	
	}
	
	public void verifyjointhewaitlist() throws Exception {

		new ProductDetailsPageComponents(driver).VerifyJoinTheWaitlist();
	
	}

	public void verifyrecommendedforyou() throws Exception {
		
		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(5000);
		new ProductDetailsPageComponents(driver).verifyRecommendedForU();
		
	}
	
	public void verifymysterypack() throws Exception {
		
		new ProductDetailsPageComponents(driver).verifyMysteryPacks();
		
	}
	
	public void VerifyATC_ScrollingCTA() throws Exception
	{
		new ProductDetailsPageComponents(driver).ATC_Scrolling_CTA();
	}
	
	public void VerifySortByReviews() throws Exception
	{
		new ProductDetailsPageComponents(driver).Sort_By_Reviews();
	}
	
	public void VerifyAskAQuestion() throws Exception {
		new ProductDetailsPageComponents(driver).Check_Ask_Question();
	}
	
	public void VerifySelectedSwatchFromPLP() throws Exception
	{
		new ProductDetailsPageComponents(driver).Check_Selected_Swatch();
	}
	
	public void verifyQuicShopforDYRecsOnPDP() throws Exception {

		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(5000);
		new ProductDetailsPageComponents(driver).VerifyQuickShopOnRecommendedforYou();
	}

	public void verifyColorSwatchforDYRecsOnPDP() throws Exception {

		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(5000);
		new ProductDetailsPageComponents(driver).MethodToCheckColorSwatchOnDYRecs();
	}

	public void verifyProductTileHoverFunctionalityforDYRecsOnPDP() throws Exception {

		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		new CollectionPageComponent(driver).navigateToProductDetailsPage();
		Thread.sleep(5000);
		new HomePageComponents(driver).RFY_ProductTileHoverFunctionality();
	}
	public void verifyPreselectedSizeOnBraPDP() throws Exception {

		new HomePageComponents(driver).navigateToBraCategory();
		Thread.sleep(3000);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,100)");
		element=driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > a"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", element);
		WebElement Triangle_Bralette = driver.findElement(By.cssSelector("div.mega-collection-filters > div:nth-child(2) > ul > li>label>input#option-style-triangle-bralettes"));
		jse.executeScript("arguments[0].click();", Triangle_Bralette);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,150)");
		Check_Preselected_Size();
	}

	public void verifyPreselectedSizeOnBoxerBriefPDP() throws Exception {

		new HomePageComponents(driver).navigateToBoxerBriefInMenCategory();
		Check_Preselected_Size();
	}
	public void verifyPreselectedSizeOnMensUndershirtPDP() throws Exception {

		new HomePageComponents(driver).navigateToAllUnderShirtsInMensCategory();
		Check_Preselected_Size();
	}

	public void verifyPreselectedSizeOnBundlePDP() throws Exception {

		new HomePageComponents(driver).navigateToLoungeWearPacksInWomenPacksCategory();
		Check_Preselected_Size();
	}

	public void verifyLowInventoryMessageOnMensUnderwearPDP() throws Exception {

		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
		ErrorMessagewithMaxQTYonPDP();
	}
		public void verifyImageGallery() throws Exception {
		new ProductDetailsPageComponents(driver).verifyImageGallery();
	}
		
	public void navigateToBraCategory() throws Exception {

		new HomePageComponents(driver).navigateToBraCategory();
	
	}
	
	public void navigateToAllUnderwearInMenCategory() throws Exception {

		new HomePageComponents(driver).navigateToAllUnderwearInMenCategory();
	
	}

	public void navigateToMensAll() throws Exception {

		new HomePageComponents(driver).navigateToMensAll();

	}



	public void navigateToSecondSkinSleepCrewNeckPocket() throws Exception {

		new HomePageComponents(driver).navigateToSecondSkinSleepCrewNeckPocket();

	}
	
	public void navigateToNewArrivalsInWomenCategory() throws Exception {

		new HomePageComponents(driver).navigateToNewArrivalsInWomenCategory();
	
	}
	public void navigateToAllMensPacksInPacksCategory() throws Exception {

		new HomePageComponents(driver).navigateToAllMensPacksInPacksCategory();
	
	}

	public void navigateToEveryDayEssentials() throws Exception {

		new HomePageComponents(driver).navigateToEveryDayEssentials();

	}

	public void navigateToExploreWomensCategory() throws Exception {

		new HomePageComponents(driver).navigateToExploreWomensCategory();

	}

	public void navigateToAllWomensPacksInPacksCategory() throws Exception {

		new HomePageComponents(driver).navigateToAllWomensPacksInPacksCategory();
	}

	public void NavigateToAllSaleInSaleCategory() throws Exception {

		new HomePageComponents(driver).NavigateToAllSaleInSaleCategory();
	}



	public void ValidateRecs() throws Exception {
		new ProductDetailsPageComponents(driver).ValidateRecs();
	}
		
	public void viewImageGallery() throws Exception {
		new ProductDetailsPageComponents(driver).viewImageGallery();
	}
	
	public void verifyOnImageBadging() throws Exception {
		new ProductDetailsPageComponents(driver).verifyOnImageBadging();
	}
	
	public void verifySecondaryBadging() throws Exception {
		new ProductDetailsPageComponents(driver).verifySecondaryBadging();
	}
		
	public void verifySelectedColorSwatch() throws Exception {
		new ProductDetailsPageComponents(driver).verifySelectedColorSwatch();
	}
	public void verifyAvailableSizes() throws Exception {
		new ProductDetailsPageComponents(driver).verifyAvailableSizes();
	}
	public void verifyStickyATC() throws Exception {
		new ProductDetailsPageComponents(driver).verifyStickyATC();
	}

	public void verifyStickySelectASize() throws Exception {
		new ProductDetailsPageComponents(driver).verifyStickySelectASize();
	}
	
	public void verifyReviewRecommendations() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyReviewRecommendations();
	}
	public void verifyProductNamePrice() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyProductNamePrice();
	}
	public void verifyAccordions() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyAccordions();
	}
	public void verifyLowStockInventory() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyLowStockInventory();
	}
	public void verifyReviewRecommendationsSection() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyReviewRecommendationsSection();
	}
	public void verifyQuestionForm() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyQuestionForm();
	}
	public void verifyReviewForm() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyReviewForm();
	}
	public void verifyReviewFormErrorMessages() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyReviewFormErrorMessages();
	}
	public void verifyAddToCart() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyAddToCart();
	}
	public void verifySizeCharts() throws Exception  {
		new ProductDetailsPageComponents(driver).verifySizeCharts();
	}
	public void verifySizeChartTextBralette() throws Exception  {
		new ProductDetailsPageComponents(driver).verifySizeChartTextBralette();
	}
	public void verifySizeChartTextBras() throws Exception  {
		new ProductDetailsPageComponents(driver).verifySizeChartTextBras();
	}
	public void verifyBundlePDPs() throws Exception  {
		new ProductDetailsPageComponents(driver).verifyBundlePDPs();
	}

	public void ValidateAddingProductToCartFromInstagramWidget() throws Exception {
		new ProductDetailsPageComponents(driver).ValidateAddingProductToCartFromInstagramWidget();
	}

	public void ValidateAppropriateProductIsAddedToCartFromInstagramWidget() throws Exception {
		new ProductDetailsPageComponents(driver).ValidateAppropriateProductIsAddedToCartFromInstagramWidget();
	}

	public void ValidateSizeOverlayInInstagramWidget() throws Exception {
		new ProductDetailsPageComponents(driver).ValidateSizeOverlayInInstagramWidget();
	}


	public void verifySelectedColorSwatchIsDisplayedInTheInlineCart() throws Exception {
		new ProductDetailsPageComponents(driver).verifySelectedColorSwatchIsDisplayedInTheInlineCart();
	}



	
	}