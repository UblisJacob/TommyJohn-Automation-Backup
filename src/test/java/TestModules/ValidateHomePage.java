package TestModules;

import com.tommyjohn.automation.Components.HomePageComponents;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.base.ExtentReport;
import com.tommyjohn.automation.Pages.HomePage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateHomePage extends ExtentReport {
	public WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setup(String browser) {
		try {
			driver = new CustomUtilities().launchtj(browser);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void ValidateImageInHeroBanner() throws Exception {

		HomePageComponents homePage = new HomePageComponents(driver);
		homePage.ValidateImageInHeroBanner();
	}

//	@Test
	public void VerifyToggleButton() throws Exception {

		new HomePage(driver).VerifyToggleButton();
	}

//	@Test
	public void VerifyRecsInMensToggle() throws Exception {

		new HomePage(driver).VerifyRecsInMensToggle();
	}

//	@Test
	public void ValidateShopNowCTAInMensToggle() throws Exception {

		new HomePage(driver).ValidateShopNowCTAInMensToggle();
	}


//	@Test
	public void ValidateNewMarkdownSection() throws Exception {

		new HomePage(driver).ValidateNewMarkdownsSection();
	}

	@Test
	public void ValidateShopMensCTAInHeroSlider() throws Exception {

		new HomePageComponents(driver).ValidateShopMensCTAInHeroSlider();
	}

	@Test
	public void ValidateShopWomensCTAInHeroSlider() throws Exception {

		new HomePageComponents(driver).ValidateShopWomensCTAInHeroSlider();
	}

	@Test
	public void ValidateBrandTicker() throws Exception {

		new HomePageComponents(driver).ValidateBrandTicker();
	}

	@Test
	public void ValidateMenInCategorySelector() throws Exception {

		new HomePage(driver).ValidateMenInCategorySelector();
	}

	@Test
	public void ValidateWomenInCategorySelector() throws Exception {

		new HomePage(driver).ValidateWomenInCategorySelector();
	}

	//@Test
	public void ValidateShopMensInHeroBanner() throws Exception {

		new HomePageComponents(driver).ValidateShopMensInHeroBanner();
	}

	//@Test
	public void ValidateShopWomensInHeroBanner() throws Exception {

		new HomePageComponents(driver).ValidateShopWomensInHeroBanner();
	}

//	@Test
	public void ValidateLinksInCollectionSelector() throws Exception {

		new HomePage(driver).ValidateLinksInCollectionSelector();
	}

	@Test
	public void ValidateOurStoryInImageBanner() throws Exception {

		new HomePage(driver).ValidateOurStoryInImageBanner();
	}

	@Test
	public void ValidateBrandBuildingInfoCTAs() throws Exception {

		new HomePage(driver).ValidateBrandBuildingInfoCTAs();
	}


	@Test
	public void VerifyLogoAndCategoriesOnHomePage() throws Exception {

		new HomePage(driver).ValidateLogoAndCategoriesPresence();
	}
	
//	@Test
	public void VerifyAccountdropdownOnHomePage() throws Exception {

		new HomePage(driver).verifyaccountdropdown();
	}

	@Test
	public void VerifyAccountAndCartIconOnHomePage() throws Exception {

		new HomePage(driver).validateAccountIcon();
		new HomePage(driver).validateCartIcon();
	}


	
	//@Test
	public void ValidateDoNotSellMyPersonalInformationPage() throws
	Exception {

		new HomePage(driver).validateDoNotSellMyPersonalInformation();
	}

//	@Test
	public void ValidateAddingProductToCartFromInstagramWidget() throws
			Exception {

		new HomePage(driver).ValidateAddingProductToCartFromInstagramWidget();
	}

//	@Test
	public void ValidateAppropriateProductIsAddedToCartFromInstagramWidget() throws
			Exception {

		new HomePage(driver).ValidateAppropriateProductIsAddedToCartFromInstagramWidget();
	}

//	@Test
	public void ValidateSizeOverlayInInstagramWidget() throws
			Exception {

		new HomePage(driver).ValidateSizeOverlayInInstagramWidget();
	}

//	@Test
	public void verifySelectedColorSwatchIsDisplayedInTheInlineCart() throws
			Exception {

		new HomePage(driver).verifySelectedColorSwatchIsDisplayedInTheInlineCart();
	}
	
//	@Test
	public void ValidateRecommendedForYou() throws
	Exception {

		new HomePage(driver).verifyrecommendedforyou();
	}
	
//	@Test
	public void ValidateQuickShoponDYRecsHomePgae() throws
	Exception {

		new HomePage(driver).verifyQuickShopforDYRecsOnHomePage();
	}

//	@Test
	public void ValidateColorSwatchonDYRecsHomePage() throws
	Exception {

		new HomePage(driver).verifyColorSwatchforDYRecsOnHomePage();
	}

//	@Test
	public void ValidateProductTileHoverFunctionalityonDYRecsHomePage() throws
	Exception {

		new HomePage(driver).verifyRFYProductTileHoverFunctionality();
	}





	@AfterMethod(alwaysRun = true)
	void teardown () {

		driver.close();
	//	driver.quit();
	}


}
