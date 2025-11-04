package TestModules;

import com.tommyjohn.automation.Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Pages.AddToCartPage;
import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class 	ValidatePDP extends ExtentReport{

	WebDriver driver;
 	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setup(String browser) {
		try {
				driver = new CustomUtilities().launchtj(browser);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
	}

//	@Test
	public void ValidateAddingProductToCartFromInstagramWidget() throws
			Exception {

		new ProductDetailsPage(driver).ValidateAddingProductToCartFromInstagramWidget();
	}

//	@Test
	public void ValidateAppropriateProductIsAddedToCartFromInstagramWidget() throws
			Exception {

		new ProductDetailsPage(driver).ValidateAppropriateProductIsAddedToCartFromInstagramWidget();
	}

//	@Test
	public void ValidateSizeOverlayInInstagramWidget() throws
			Exception {

		new ProductDetailsPage(driver).ValidateSizeOverlayInInstagramWidget();
	}

//	@Test
	public void verifySelectedColorSwatchIsDisplayedInTheInlineCart() throws
			Exception {

		new ProductDetailsPage(driver).verifySelectedColorSwatchIsDisplayedInTheInlineCart();
	}

	@Test
	public void VerifyComparisonModule() throws Exception {
		new ProductDetailsPage(driver).VerifyComparisonModule();
	}

	@Test
	public void VerifyAddToWishlist() throws Exception {
		new ProductDetailsPage(driver).VerifyAddToWishlist();
	}

	@Test
	public void ValidateGuestUserAddToWishlist() throws Exception {
		new ProductDetailsPage(driver).VerifyAddToWishlist();
	}

	@Test
	public void VerifyProductAddOnIsDispalyed() throws Exception {
		new ProductDetailsPage(driver).VerifyProductAddOn();
	}

	@Test
	public void ValidateComparisonModule() throws Exception {
		new ProductDetailsPage(driver).ValidateComparisonModule();
	}


	@Test
	public void VerifyHowToMeasureSection() throws Exception {
		new ProductDetailsPage(driver).VerifyHowToMeasureSection();

	}

	@Test
	public void VerifyProductSets() throws Exception {
		new ProductDetailsPage(driver).VerifyProductSets();

	}


	@Test
	public void ValidateAppropriateResultForSelectingBandSizeAndCupVolume() throws Exception {
		new ProductDetailsPage(driver).ValidateAppropriateResultForSelectingBandSizeAndCupVolume();
	}

	@Test
	public void ValidateSizeChartDetailsForBrasandBrallets() throws Exception {
		new ProductDetailsPage(driver).ValidateSizeChartDetailsForBrasandBrallets();

	}

	@Test
	public void ValidateReadAllReviewsLinkInReviewsSummary() throws Exception {
		new ProductDetailsPage(driver).ValidateReadAllReviewsLinkInReviewsSummary();
	}

	@Test
	public void ValidateWriteAReviewLink() throws Exception {
		new ProductDetailsPage(driver).ValidateWriteAReviewLink();

	}

	@Test
	public void VerifyShopPay() throws Exception {
		new ProductDetailsPage(driver).VerifyShopPayIsDisplayed();

	}

	@Test
	public void ValidateReviewSummaryLink() throws Exception {
		new ProductDetailsPage(driver).ValidateReviewSummaryLink();

	}

	@Test
	public void ValidateReviewsSummaryCTAINReviewsSection() throws Exception {
		new ProductDetailsPage(driver).ValidateReviewsSummaryCTAINReviewsSection();

	}

	@Test
	public void ValidateDisabledATC() throws Exception {
		new ProductDetailsPage(driver).ValidateDisabledATC();

	}

	@Test
	public void ValidatesectionsinPDP() throws Exception {
		new ProductDetailsPage(driver).ValidatesectionsinPDP();

	}

	@Test
	public void ValidateTabPriceAndAddToCartPrice() throws Exception {
		new ProductDetailsPage(driver).ValidateTabPriceAndAddToCartPrice();

	}

	@Test
	public void ValidateClickingOnRatingsTierMeter() throws Exception {
		new ProductDetailsPage(driver).ValidateClickingOnRatingsTierMeter();

	}

	@Test
	public void VerifyPromoBannerIsAvailableinPDP() throws Exception {
		new ProductDetailsPage(driver).VerifyPromoBannerIsAvailableinPDP();

	}


	@Test
	public void ValidateRecs() throws Exception {
		ChromeOptions chromeOptions = new ChromeOptions();
	//	chromeOptions.addArguments("--headless");
		new ProductDetailsPage(driver).ValidateRecs();
	}

	@Test
	public void ViewImageGallery() throws Exception {
		new ProductDetailsPage(driver).viewImageGallery();
		
	}
	
	
	@Test
	public void VerifyOnImageBadging() throws Exception {
		new ProductDetailsPage(driver).verifyOnImageBadging();
		
	}
	
	@Test
	public void VerifySecondaryBadging() throws Exception {
		new ProductDetailsPage(driver).verifySecondaryBadging();
		
	}
	@Test
	public void VerifySelectedColorSwatch() throws Exception {
		new ProductDetailsPage(driver).verifySelectedColorSwatch();
		
	}
	@Test
	public void VerifyAvailableSizes() throws Exception {
		new ProductDetailsPage(driver).verifyAvailableSizes();
		
	}
	@Test
	public void VerifyStickyATC() throws Exception {
		new ProductDetailsPage(driver).verifyStickyATC();
	}

	@Test
	public void VerifyStickySelectASize() throws Exception {
		new ProductDetailsPage(driver).verifyStickySelectASize();
	}
	
	@Test
	public void VerifyReviewRecommendations() throws Exception {
		new ProductDetailsPage(driver).verifyReviewRecommendations();
	}
	@Test
		public void VerifyProductNamePrice() throws Exception {
		new ProductDetailsPage(driver).verifyProductNamePrice();
	}
	@Test
	public void VerifyAccordions() throws Exception {
		new ProductDetailsPage(driver).verifyAccordions();
	}

	@Test
	public void VerifyDetailsInFitMaterialAccordion() throws Exception {
		new ProductDetailsPage(driver).VerifyDetailsInFitMaterialAccordion();
	}


	@Test
	public void VerifyLowStockInventory() throws Exception {
		new ProductDetailsPage(driver).verifyLowStockInventory();
	}
	@Test
	public void VerifyReviewRecommendationsSection() throws Exception {
		new ProductDetailsPage(driver).verifyReviewRecommendationsSection();
	}
	
	@Test
	public void VerifyQuestionForm() throws Exception {
		new ProductDetailsPage(driver).verifyQuestionForm();
	}
	@Test
	public void VerifyReviewForm() throws Exception {
		new ProductDetailsPage(driver).verifyReviewForm();
	}

	@Test
	public void VerifyReviewFormErrorMessages() throws Exception {
		new ProductDetailsPage(driver).verifyReviewFormErrorMessages();
	}
	@Test
	public void VerifyAddToCart() throws Exception {
		new ProductDetailsPage(driver).verifyAddToCart();
	}
	
	@Test
	public void VerifySizeCharts() throws Exception {
		new ProductDetailsPage(driver).verifySizeCharts();
	}
	@Test
	public void VerifySizeChartTextBralette() throws Exception {
		new ProductDetailsPage(driver).verifySizeChartTextBralette();
	}
	@Test
	public void VerifySizeChartTextBras() throws Exception {
		new ProductDetailsPage(driver).verifySizeChartTextBras();
	}
	@Test
	public void VerifyBundlePDPs() throws Exception {
		new ProductDetailsPage(driver).verifyBundlePDPs();
	}
	
	
	@AfterMethod(alwaysRun = true)
	void teardown () {

		driver.close();
//		driver.quit();
	
	}
}