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

		@Test
		public void TC_LoadCategoryPage() throws
		Exception { new CollectionPage(driver).validateallplpsareloading(); }
		
		
		@Test
		public void TC_ViewProductGrid() throws
		Exception { new CollectionPage(driver).validateproductgrid(); }
		
		@Test
		public void TC_01_CategoryPage() throws Exception
		{
			new HomePage(driver).validateNavigationToMensLoungewearCategory();
		}
		
		@Test
		public void TC_02_CategoryPage() throws Exception
		{
			new HomePage(driver).validateNavigationToMensLoungewearCategory();
			new CollectionPage(driver).validateProductsInMensLoungewearCategory();
		}
		
		@Test
		public void TC_03_CategoryPage() throws Exception
		{
			new HomePage(driver).validateNavigationToMensLoungewearCategory();
			new CollectionPage(driver).validateProductsInMensLoungewearCategory();
		}
		
		@Test
		public void TC_04_CategoryPage() throws Exception
		{
			new HomePage(driver).validateNavigationToMensLoungewearCategory();
			new CollectionPage(driver).validatePLPBreadcrumbLinks();
		}
		
		@Test
		public void TC_05_CategoryPage() throws Exception
		{
			new HomePage(driver).validateNavigationToMensLoungewearCategory();
			new CollectionPage(driver).validatePLPInlineBanner();
		}
		
		@AfterMethod(alwaysRun = true)
		void teardown () {

			//driver.close();
			driver.quit();
		}
	}
