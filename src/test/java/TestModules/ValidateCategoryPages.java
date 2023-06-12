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

	
	 @Test public void TC_LoadCategoryPage() throws Exception { new
	 CollectionPage(driver).validateallplpsareloading(); }
	 
	 @Test public void TC_ViewProductGrid() throws Exception { new
	 CollectionPage(driver).validateproductgrid(); }
	 
	 
	 @Test public void TC_InteractWithBreadcrumbs() throws Exception { new
	 CollectionPage(driver).validateBreadcrumbLinks(); }
	
	 @Test public void TC_ViewH1() throws Exception { new
	 CollectionPage(driver).validateCategoryPageHeader(); }
	 
	
	 @Test public void TC_ViewSectionHeaders() throws Exception { new
	 CollectionPage(driver).validateCategorySectionHeaders(); }
	 

	
	 @Test public void TC_InteractWithInlineBanner() throws Exception { new
	 CollectionPage(driver).validateInlineBanner(); }
	 

	
	 @Test public void TC_InteractWithFilters() throws Exception { new
	 CollectionPage(driver).validateFilters(); }
	 

	
	 @Test public void TC_InteractWithSortBy() throws Exception { new
	 CollectionPage(driver).validateSortBy(); }
	 

	
	 @Test public void TC_ViewOnImageAndSecondaryBadging() throws Exception { new
	 CollectionPage(driver).validateOnImageAndSecondaryBadging(); }
	 

	
	 @Test public void TC_SEOCopy() throws Exception { new
	 CollectionPage(driver).validateSEOCopy(); }
	 

	
	 @Test public void TC_InteractWithProductTile() throws Exception { new
	 CollectionPage(driver).validateProductTile(); }
	 

	
	@Test public void TC_InteractWithQuickShop() throws Exception { new
	  CollectionPage(driver).validateQuickshopFunctionality(); }
	 

	@Test
	public void TC_ViewSingleTile() throws Exception {
		new CollectionPage(driver).validateSingleTileProduct();
	}

	@Test
	public void TC_InteractWithStyleCarousel() throws Exception {
		new CollectionPage(driver).validateStyleCarousels();
	
	}
	
	@Test
	public void TC_MultipleSizeVariants() throws Exception {
		new CollectionPage(driver).validateMultiVariantSizeInQuickshop();
	}
	
	@Test
	public void TC_MultipleSizeVariants() throws Exception {
		new CollectionPage(driver).validateMultiVariantSizeInQuickshop();
	}
	

	@AfterMethod(alwaysRun = true)
	void teardown() {

		// driver.close();
		driver.quit();
	}
}
