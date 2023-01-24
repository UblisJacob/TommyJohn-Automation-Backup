package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateSingleCollectionPages_QA {
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
	public void TC_ValidateFiltersAndSortingOnAllWomensPacksInPacksCategory() throws
	Exception { new CollectionPage(driver).validatefilterandsortonOnPacks(); }
	
	@Test
	public void TC_ValidateProductImageOnSecondSkinInMenCategory() throws
	Exception { new CollectionPage(driver).validateproductimageonOnSecondSkin();
	}

	@Test
	public void TC_ValidateProductNameOnSecondSkinInMenCategory() throws
	Exception { new CollectionPage(driver).validateproductnameonOnSecondSkin(); }

	@Test
	public void TC_ValidateProductReviewStarsOnSecondSkinInMenCategory()
			throws Exception { new
		CollectionPage(driver).validateproductreviewstarsonOnSecondSkin(); }
	

	@AfterMethod(alwaysRun = true)
	void teardown () {

		//driver.close();
		driver.quit();
	}

}

