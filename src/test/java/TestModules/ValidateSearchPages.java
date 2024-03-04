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
	public void TC_LoadSearch() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchPM(searchText);
	}	
	
	@Test
	public void TC_Search_InteractWithFilters() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchFunctionEnter(searchText);
		new CollectionPage(driver).validateFiltersSearch();
	}
	
	@Test public void TC_Search_InteractWithSortBy() throws Exception { 
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchFunctionEnter(searchText);
		new CollectionPage(driver).validateSortBySearch();
	}
	
	@Test public void TC_Search_ViewOnImageAndSecondaryBadging() throws Exception { 
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchPM(searchText);
		new CollectionPage(driver).validateOnImageBadgeSearch();
	}
	
	@Test public void TC_Search_InteractWithQuickShop() throws Exception { 
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchPM(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
	}
	
	@Test
	public void TC_Search_ValidateProductTile() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchFeatured");
		new HomePage(driver).validateSearchPM(searchText);
		new CollectionPage(driver).validateProductTileSearch();
	}
	
	@Test
	public void TC_Search_ValidateSingleProductTile() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchUnderwear");
		new HomePage(driver).validateSearchFunctionEnter(searchText);
		new CollectionPage(driver).validateSingleTileProductSearch();
	}
	
	@Test
	public void TC_Search_SearchFieldInNavigation() throws Exception {
		new HomePage(driver).validateSearchFunctionality();
	}
	
	@Test
	public void TC_Search_MultipleSizeVariants() throws Exception {
		searchText = objGeneralMethods.readPropertiesFile("searchSleepwearSets");
		new HomePage(driver).validateSearchFunctionEnterKeyword(searchText);
		new CollectionPage(driver).validateQuickshopFunctionalityOnSearchPLP();
	}
	
	
	
	
	

	@AfterMethod(alwaysRun = true)
	void teardown() {

		//driver.close();
		driver.quit();
	}
}
