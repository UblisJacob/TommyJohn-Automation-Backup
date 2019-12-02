package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.tommyjohn.automation.Pages.HomePage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateHomePage {
	public WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		try {
			driver=	new CustomUtilities().launchtj();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void TC_verifyLogoAndCategoriesOnHomePage() throws Exception {
		
		new HomePage(driver).ValidateLogoAndCategoriesPresence();
	}
	@Test
	public void TC_verifyFooterLinksOnHomePage() throws Exception {
		
		new HomePage(driver).validateFooterLinks();
	}
	@Test
	public void TC_verifyHelpOnHomePage() throws Exception {
		
		new HomePage(driver).verifyhelp();
	}

	@Test
	public void TC_verifyAccountAndCartIconOnHomePage() throws Exception {
		
		new HomePage(driver).validateAccountIcon();
		new HomePage(driver).validateCartIcon();
	}
	
	@Test
	public void TC_ValidateSearchPage() throws Exception {
		
		new HomePage(driver).validateSearchIcon();
	}
	
	@AfterMethod(alwaysRun = true)
	void teardown () {
		
		driver.close();
		driver.quit();
	}


}
