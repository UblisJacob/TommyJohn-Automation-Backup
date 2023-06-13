package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.FooterSectionPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateFooterSection {

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
	public void TC_VerifyFooterSectionLinks() throws Exception {
		new FooterSectionPage(driver).verifyFooterSectionLinks();
	}
	
	@Test
	public void TC_VerifyFooterSocialLinks() throws Exception {
		new FooterSectionPage(driver).verifyFooterSocialLinks();
	}
	
	@Test
	public void TC_VerifyFooterCCPASection() throws Exception {
		new FooterSectionPage(driver).verifyFooterCCPASection();
	}

	@Test
	public void TC_VerifyFooterEmailSignUp() throws Exception {
		new FooterSectionPage(driver).verifyFooterEmailSignUp();
	}
	
	@Test
	public void TC_verifyFooterFeedback() throws Exception {
		new FooterSectionPage(driver).verifyFooterFeedback();
	}

	@AfterMethod(alwaysRun = true)
	void tearDown() {

		driver.quit();

	}
}
