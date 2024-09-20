package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Components.CollectionPageComponent;
import com.tommyjohn.automation.Components.HomePageComponents;
import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.Pages.HomePage;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;


public class ValidateSearchPages {

	WebDriver driver;
	GeneralMethods objGeneralMethods = new GeneralMethods();
	String searchText="";

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
	public void VerifyLoadSearch() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchPM(searchText);
	}

	@Test
	public void ValidateNoResultPageOnSearch() throws InterruptedException {
		new HomePage(driver).ValidateNoResultPageOnSearch();
	}

//	@Test
//	public void VerifyPopularCategoriesSectionInSearchDropdown() throws InterruptedException{
//		new HomePage(driver).VerifyPopularCategoriesSectionInSearchDropdown();
//	}
//
//	@Test
//	public void VerifyCategoriesInPopularCategoriesSection() throws InterruptedException {
//		new HomePage(driver).VerifyCategoriesInPopularCategoriesSection();
//	}
//
//	@Test
//	public void VerifyPopularTermsSectionInSearchDropdown() throws InterruptedException {
//		new HomePage(driver).VerifyPopularTermsSectionInSearchDropdown();
//	}
//
//	@Test
//	public void VerifyCategoriesInPopularTermsSection() throws InterruptedException {
//		new HomePage(driver).VerifyCategoriesInPopularTermsSection();
//	}
	
//	@Test
	public void VerifySearchInteractWithFilters() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchFunctionEnter(searchText);
		new CollectionPage(driver).validateFiltersSearch();
	}
	
//	@Test
	public void VerifySearchInteractWithSortBy() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchFunctionEnter(searchText);
		new CollectionPage(driver).validateSortBySearch();
	}
	
	@Test public void VerifySearchViewOnImageAndSecondaryBadging() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchAir");
		new HomePage(driver).validateSearchPM(searchText);
		new CollectionPage(driver).validateOnImageBadgeSearch();
	}
	
	@Test public void VerifySearchInteractWithQuickShop() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchSleepwearSets");
		new HomePage(driver).validateSearchPM(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
	}
	
//	@Test
	public void ValidateSearchProductTile() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchPM(searchText);
		new CollectionPage(driver).validateProductTileSearch();
	}
	
	@Test
	public void ValidateSearchSingleProductTile() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchUnderwear");
		new HomePage(driver).validateSearchFunctionEnter(searchText);
		new CollectionPage(driver).validateSingleTileProductSearch();
	}
	
//	@Test
	public void VerifySearchSearchFieldInNavigation() throws Exception {
		new HomePage(driver).validateSearchFunctionality();
	}
	
	@Test
	public void VerifySearchMultipleSizeVariants() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchSleepwearSets");
		new HomePage(driver).validateSearchFunctionEnterKeyword(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
	}
	
	
	
	
	

	@AfterMethod(alwaysRun = true)
	void teardown() {

		driver.close();
		driver.quit();
	}
}
