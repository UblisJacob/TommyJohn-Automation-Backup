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
	public void TC_verifySearchAndHelpOnHomePage() throws Exception {
		
		new HomePage(driver).verifysearchandhelp();
	}

	@Test
	public void TC_verifyAccountAndCartIconOnHomePage() throws Exception {
		
		new HomePage(driver).validateAccountIcon();
		new HomePage(driver).validateCartIcon();
	}
	
	@AfterMethod(alwaysRun = true)
	void teardown () {
		
		driver.close();
		driver.quit();
	}


}
