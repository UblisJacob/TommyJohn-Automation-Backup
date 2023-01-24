package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateMegaCollectionPages_QA {

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
	public void TC_ValidateProductImageOnMegaCollectionPLP() throws
	Exception
	{
		new CollectionPage(driver).validateproductimageonMegaCollectionPLP();

	}*/
	
	/*@Test 
	public void TC_ValidateProductNameOnMegaCollectionPLP() throws
	Exception 
	{ 
		new CollectionPage(driver).validateproductnameonMegaCollectionPLP();
	}*/
	
	@Test 
	public void TC_ValidateProductReviewStarsOnMegaCollectionPLP()
			throws Exception
	{ new
		CollectionPage(driver).validateproductreviewstarsonMegaCollectionPLP(); }
	
	@Test 
	public void TC_ValidateFiltersAndSortingOnMegaCollectionPLP()
			throws Exception
	{ new
		CollectionPage(driver).validatefilterandsortonMegaCollectionPLP(); }
	
	@Test
	public void TC_ValidateColorSwatchOnMegaCollectionPLP() throws
	Exception
	{
		new CollectionPage(driver).MethodToCheckColorSwatchChangingOnPLP();

	}


	@AfterMethod(alwaysRun = true)
	void teardown () {

		//driver.close();
		driver.quit();
	}

}
