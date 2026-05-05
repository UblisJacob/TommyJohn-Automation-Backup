package TestModules;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.Pages.QuizPage;
import com.tommyjohn.automation.Pages.SanityModulePage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateSanityModules {
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
	public void VerifyHeroModule() throws Exception {
		 new SanityModulePage(driver).verifyHeroModule();
		 
	 }
	@Test
	public void VerifyFixedBannerModule() throws Exception {
		 new SanityModulePage(driver).verifyFixedBannerModule();
		 
	 }
	
	@Test
	public void VerifyThreeTileModule() throws Exception {
		 new SanityModulePage(driver).verifyThreeTileModule();
		 
	 }
	@Test
	public void VerifyImagecarousel() throws Exception {
		 new SanityModulePage(driver).verifyImagecarousel();
		 
	 }
//	@Test
	public void VerifyFixedBannerModuleLinks() throws Exception {
		 new SanityModulePage(driver).verifyFixedBannerModuleLinks();
		 
	 }

	 //@Test
	public void VerifySideBySideModule() throws Exception {
		 new SanityModulePage(driver).verifySideBySideModule();
		 
	 }
	
	@AfterMethod(alwaysRun = true)
	void teardown () {

//		driver.close();
//		driver.quit();
		if(driver != null)
		{
			driver.quit();
		}
	}
	
	
	
}