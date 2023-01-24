package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.utils.CustomUtilities;


public class ValidatePLP_SingleCollectionPage{

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
	public void TC_ValidateProductImageOnSocks() throws
	Exception { new CollectionPage(driver).validateProductImageonOnSocks();
	}
	
	@Test
	public void TC_ValidateProductNameOnSocks() throws
	Exception { new CollectionPage(driver).validateproductnameonOnSocks();
	}
	
	@Test
	public void TC_ValidateProductReviewstarsOnSocks() throws
	Exception { new CollectionPage(driver).validateproductreviewstarsonOnSocks();
	}
	
	@Test
	public void TC_ValidatefilterandsortOnSocks() throws
	Exception { new CollectionPage(driver).validatefilterandsortonOnSocks();
	}
	
	@Test
	public void TC_ValidateColorSwatchonOnSocks() throws
	Exception { new CollectionPage(driver).validateColorSwatcheonSocks();
	}*/
	
	@Test
	public void TC_ValidatePriceOnPLP() throws
	Exception { new CollectionPage(driver).validatePriceOnPLP();
	}
	
	/*@Test
	public void TC_ValidateFilterPillOnPLPP() throws
	Exception { new CollectionPage(driver).validateFilterPillOnPLP();
	}*/
	
	/*@Test
	public void TC_validateCollectionCTAOnPLP() throws
	Exception { new CollectionPage(driver).validateCollectionCTAOnPLP();
	}*/
	
	
	
}
