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
	 @Test public void ValidateLoadCategoryPage() throws Exception { new
	 CollectionPage(driver).validateallplpsareloading(); }
	 
	 @Test public void ValidateViewProductGrid() throws Exception { new
	 CollectionPage(driver).validateproductgrid(); }
	 
	 
	 @Test public void ValidateInteractWithBreadcrumbs() throws Exception { new
	 CollectionPage(driver).validateBreadcrumbLinks(); }
	
	 @Test public void ValidateViewH1() throws Exception { new
	 CollectionPage(driver).validateCategoryPageHeader(); }
	 
	
	 @Test public void ValidateViewSectionHeaders() throws Exception { new
	 CollectionPage(driver).validateCategorySectionHeaders(); }



	 @Test public void ValidateInteractWithInlineBanner() throws Exception { new
	 CollectionPage(driver).validateInlineBanner(); }
	 


	 @Test public void ValidateInteractWithFilters() throws Exception { new
	 CollectionPage(driver).validateFilters(); }
	 

	
	// @Test public void TC_InteractWithSortBy() throws Exception { new
	// CollectionPage(driver).validateSortBy(); }
	 

	
	 @Test public void ValidateViewOnImageAndSecondaryBadging() throws Exception { new
	 CollectionPage(driver).validateOnImageAndSecondaryBadging(); }
	 

	
	 @Test public void ValidateSEOCopy() throws Exception { new
	 CollectionPage(driver).validateSEOCopy(); }
	 

	
//	 @Test
	 public void TC_InteractWithProductTile() throws Exception { new
	 CollectionPage(driver).validateProductTile(); }
	 

	
	@Test public void ValidateInteractWithQuickShop() throws Exception { new
	  CollectionPage(driver).validateQuickshopFunctionality(); }
	 

	@Test
	public void ValidateViewSingleTile() throws Exception {
		new CollectionPage(driver).validateSingleTileProduct();
	}

	@Test
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
	
	
	

	@AfterMethod(alwaysRun = true)
	void teardown() {

		 driver.close();
		driver.quit();
	}
}
