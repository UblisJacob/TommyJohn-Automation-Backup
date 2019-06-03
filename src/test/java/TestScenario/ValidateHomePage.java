package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.tommyjohn.automation.Pages.HomePage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateHomePage {
	public WebDriver driver;
	
//	@BeforeTest(alwaysRun = true)
//	void setup() {
//		try {
//			CustomUtilities.launchtj();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}
	
	@Test
	public void TC_verifyLogoAndCategoriesOnHomePage() throws Exception {
		
		new HomePage().ValidateLogoAndCategoriesPresence();
	}
	@Test
	public void TC_verifyFooterLinksOnHomePage() throws Exception {
		
		new HomePage().validateFooterLinks();
	}
	@Test
	public void TC_verifySearchAndHelpOnHomePage() throws Exception {
		
		new HomePage().verifysearchandhelp();
	}

	@Test
	public void TC_verifyAccountAndCartIconOnHomePage() throws Exception {
		
		new HomePage().validateAccountIcon();
		new HomePage().validateCartIcon();
	}
	
//	@AfterSuite(alwaysRun = true)
//	void tearDown() {
//		try {
//			CustomUtilities.teardown();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}


}
