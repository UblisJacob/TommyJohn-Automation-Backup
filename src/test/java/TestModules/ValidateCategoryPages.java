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

	
	/* @Test 
	 public void TC_LoadCategoryPage() throws Exception { 
		 new CollectionPage(driver).validateallplpsareloading(); 
	}*/
	 

	/*
	 * @Test public void TC_ViewProductGrid() throws Exception { new
	 * CollectionPage(driver).validateproductgrid(); }
	 */

	
	 @Test 
	 public void TC_01_CategoryPage() throws Exception { 
		 new CollectionPage(driver).navigateToAnyCollectionPage(); 
	 }
	 

	
	 @Test 
	 public void TC_02_CategoryPage() throws Exception { 
		 new CollectionPage(driver).navigateToAnyCollectionPage();
		 //new HomePage(driver).validateNavigationToMensLoungewearCategory();
		 new CollectionPage(driver).validatePLPBreadcrumbLinks(); 
		 }
	 

	
	/* @Test public void TC_04_CategoryPage() throws Exception { new
	 HomePage(driver).validateNavigationToMensLoungewearCategory(); new
	 CollectionPage(driver).validatePLPInlineBanner(); }*/
	 

	/*@Test
	public void TC_05_CategoryPage() throws Exception {
		new HomePage(driver).validateNavigationToMensLoungewearCategory();
		new CollectionPage(driver).methodToCheckAllFilters();
	}*/

	/*
	 * @Test public void TC_06_CategoryPage() throws Exception { new
	 * HomePage(driver).navigateToAllUnderwearInMenCategory(); new
	 * CollectionPage(driver).validateSectionHeaders(); }
	 */

	/*
	 * @Test public void TC_07_CategoryPage() throws Exception { new
	 * HomePage(driver).navigateToAllUnderwearInMenCategory(); new
	 * CollectionPage(driver).validateSortBy(); }
	 */

	/*@Test
	public void TC_08_CategoryPage() throws Exception {
		new HomePage(driver).navigateToAllUnderwearInMenCategory();
		new CollectionPage(driver).validateCategoryDescription();
	}*/
	
	/*@Test
	public void TC_09_CategoryPage() throws Exception {
		new HomePage(driver).navigateToAllUnderwearInMenCategory();
		new CollectionPage(driver).validateOnImageBadging();
	}*/
	
	/*@Test
	public void TC_10_CategoryPage() throws Exception {
		new HomePage(driver).navigateToAllPantiesnInWomenCategory();
		new CollectionPage(driver).validateSecondaryBadging();
	}*/
	
	/*@Test
	public void TC_11_CategoryPage() throws Exception {
		new HomePage(driver).validateNavigationToMensLoungewearCategory();
		new CollectionPage(driver).validateProductImageOnPLP();
		new HomePage(driver).validateNavigationToMensLoungewearCategory();
		new CollectionPage(driver).validateProductHeadingTextOnCollectionPage();
		new CollectionPage(driver).validateProductReviewStarsOnCollectionPage();
		new CollectionPage(driver).MethodToCheckColorSwatchChangingOnPLP();
		new CollectionPage(driver).Verify_ProductTile_HoverFunctionality();
		
	}*/
	
	@AfterMethod(alwaysRun = true)
	void teardown() {

		// driver.close();
		driver.quit();
	}
}
