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
	public void TC_verifyHeroModule() throws Exception {
		 new SanityModulePage(driver).verifyHeroModule();
		 
	 }
	@Test
	public void TC_verifyFixedBannerModule() throws Exception {
		 new SanityModulePage(driver).verifyFixedBannerModule();
		 
	 }
	
	@Test
	public void TC_verifyThreeTileModule() throws Exception {
		 new SanityModulePage(driver).verifyThreeTileModule();
		 
	 }
	@Test
	public void TC_verifyImagecarousel() throws Exception {
		 new SanityModulePage(driver).verifyImagecarousel();
		 
	 }
	@Test
	public void TC_verifyFixedBannerModuleLinks() throws Exception {
		 new SanityModulePage(driver).verifyFixedBannerModuleLinks();
		 
	 }
	@Test
	public void TC_verifySideBySideModule() throws Exception {
		 new SanityModulePage(driver).verifySideBySideModule();
		 
	 }
	
	@AfterMethod(alwaysRun = true)
	void teardown () {

		driver.quit();
	}
	
	
	
}