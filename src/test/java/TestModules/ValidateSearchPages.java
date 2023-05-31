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
import com.tommyjohn.automation.utils.CustomUtilities;


public class ValidateSearchPages {

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
	
	/*@Test
	public void TC_LoadSearch() throws Exception {
		new HomePage(driver).validateSearchPM();
	}*/	
	
	/*@Test
	public void TC_Search_InteractWithFilters() throws Exception {
		new HomePage(driver).validateSearchPM();
		new CollectionPage(driver).validateFiltersSearch();
	}*/
	
	/*@Test public void TC_Search_InteractWithSortBy() throws Exception { 
		new HomePage(driver).validateSearchPM();
		new CollectionPage(driver).validateSortBySearch();
	}*/
	
	@Test public void TC_Search_InteractWithSortBy() throws Exception { 
		new HomePage(driver).validateSearchPM();
		new CollectionPage(driver).validateOnImageBadgeSearch();
	}
	
	
	

	@AfterMethod(alwaysRun = true)
	void teardown() {

		// driver.close();
		driver.quit();
	}
}
