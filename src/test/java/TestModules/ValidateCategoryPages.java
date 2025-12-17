package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateCategoryPages {

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

//Not worked exception

	@Test public void ValidateCollectionDoorways() throws Exception { new
			CollectionPage(driver).ValidateCollectionDoorways(); }

	@Test public void VerifyFilterPill() throws Exception { new
			CollectionPage(driver).VerifyFilterPill(); }

//	@Test
	public void ValidateDyPlaceholderBannerInPLP() throws Exception { new
			CollectionPage(driver).ValidateDyPlaceholderBannerInPLP(); }

//	@Test
	public void ValidateShopNowCTAinDyPlaceholderBanner() throws Exception { new
			CollectionPage(driver).ValidateShopNowCTAinDyPlaceholderBanner(); }


	@Test public void ValidateFunctionalityOfCollectionDoorways() throws Exception { new
			CollectionPage(driver).ValidateFunctionalityOfCollectionDoorways(); }


	 @Test
		 public void ValidateLoadCategoryPage() throws Exception { new
	 CollectionPage(driver).validateallplpsareloading(); }

	@Test public void ValidateStarRatingIsFunctional() throws Exception { new
			CollectionPage(driver).ValidateStarRatingIsFunctional(); }

	//@Test
	public void ValidateRecsInPLP() throws Exception { new
			CollectionPage(driver).ValidateRecsInPLP(); }
	 
	 @Test public void ValidateViewProductGrid() throws Exception { new
	 CollectionPage(driver).validateproductgrid(); }

	@Test
	public void ValidateMultipacksToggle() throws Exception {
	new CollectionPage(driver).ValidateMultipacksCheckBox(); }

	@Test
		public void ValidateHammockPouchCheckBox() throws Exception {
			new CollectionPage(driver).ValidateHammockPouchToggleButton(); }
	 
	 
	 @Test
	 public void ValidateInteractWithBreadcrumbs() throws Exception { new
	 CollectionPage(driver).validateBreadcrumbLinks(); }
	
	 @Test public void ValidateViewH1() throws Exception { new
	 CollectionPage(driver).validateCategoryPageHeader(); }
	 
	
	 @Test public void ValidateViewSectionHeaders() throws Exception { new
	 CollectionPage(driver).validateCategorySectionHeaders(); }



	@Test
	public void ValidateInteractWithInlineBanner() throws Exception { new
	CollectionPage(driver).validateInlineBanner(); }



	 @Test public void ValidateInteractWithFilters() throws Exception { new
	 CollectionPage(driver).validateFilters(); }

	@Test public void ValidateHammockPouchIconInFilters() throws Exception { new
			CollectionPage(driver).ValidateHammockPouchIconInFilters(); }
	
	 @Test
	 public void ValidateInteractWithSortBy() throws Exception {
		new CollectionPage(driver).validateSortBy(); }
	 

	
	 @Test
	 public void ValidateViewOnImageAndSecondaryBadging() throws Exception { new
	 CollectionPage(driver).validateOnImageAndSecondaryBadging(); }
	 

	
//	 @Test
	 public void ValidateSEOCopy() throws Exception { new
	 CollectionPage(driver).validateSEOCopy(); }
	 

	
	 @Test
	 public void ValidateInteractWithProductTile() throws Exception { new
	 CollectionPage(driver).validateProductTile(); }
	 

	
	@Test
	public void ValidateInteractWithQuickShop() throws Exception { new
	CollectionPage(driver).validateQuickshopFunctionality(); }

	@Test
	public void validateCloseQuickshop() throws Exception { new
			CollectionPage(driver).validateCloseQuickshop(); }

	@Test
	public void validateLearnMoreLinkQuickshop() throws Exception { new
			CollectionPage(driver).validateLearnMoreInQuickshop(); }
	 

	@Test
	public void ValidateViewSingleTile() throws Exception {
		new CollectionPage(driver).validateSingleTileProduct();
	}

//	@Test
	public void ValidateInteractWithStyleCarousel() throws Exception {
		new CollectionPage(driver).validateStyleCarousels();
	
	}
	
	@Test
	public void ValidateMultipleSizeVariants() throws Exception {
		new CollectionPage(driver).validateMultiVariantSizeInQuickshop();
	}

	@Test
	public void ValidateProductsOnFiltering() throws Exception {
		new CollectionPage(driver).ValidateProductsOnFiltering();
	}

//	@Test
	public void ValidateSizeFiltersCountInAllFiltersDropdownAndInSizeDropdown() throws Exception {
		new CollectionPage(driver).ValidateSizeFiltersCountInAllFiltersDropdownAndInSizeDropdown();
	}

//	@Test
	public void ValidateSizeFiltersNameInAllFiltersDropdownAndInSizeDropdown() throws Exception {
		new CollectionPage(driver).ValidateSizeFiltersNameInAllFiltersDropdownAndInSizeDropdown();
	}

//	@Test
	public void ValidateColorFiltersCountInAllFiltersDropdownAndInColorDropdown() throws Exception {
		new CollectionPage(driver).ValidateColorFiltersCountInAllFiltersDropdownAndInColorDropdown();
	}

//	@Test
	public void ValidateColorFiltersNameInAllFiltersDropdownAndInColorDropdown() throws Exception {
		new CollectionPage(driver).ValidateColorFiltersNameInAllFiltersDropdownAndInColorDropdown();
	}

//	@Test
	public void ValidateCategoryFiltersCountInAllFiltersDropdownAndInCategoryDropdown() throws Exception {
		new CollectionPage(driver).ValidateCategoryFiltersCountInAllFiltersDropdownAndInCategoryDropdown();
	}

//	@Test
	public void ValidateCategoryFiltersNameInAllFiltersDropdownAndInCategoryDropdown() throws Exception {
		new CollectionPage(driver).ValidateCategoryFiltersNameInAllFiltersDropdownAndInCategoryDropdown();
	}

//	@Test
	public void ValidateCollectionFiltersCountInAllFiltersDropdownAndInCollectionDropdown() throws Exception {
		new CollectionPage(driver).ValidateCollectionFiltersCountInAllFiltersDropdownAndInCollectionDropdown();
	}

//	@Test
	public void ValidateCollectionFiltersNameInAllFiltersDropdownAndInCollectionDropdown() throws Exception {
		new CollectionPage(driver).ValidateCollectionFiltersNameInAllFiltersDropdownAndInCollectionDropdown();
	}

	@Test
	public void ValidateOnClickingTheInLineBanner() throws Exception {
		new CollectionPage(driver).ValidateOnClickingTheInLineBanner();
	}

	@AfterMethod(alwaysRun = true)
	void teardown() {

//		driver.close();
//		driver.quit();
		if(driver != null)
		{
			driver.quit();
		}
        CustomUtilities.DriverManager.setDriver(null);
	}
}
