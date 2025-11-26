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
	public void VerifyFooterSectionLinks() throws Exception {
		new FooterSectionPage(driver).verifyFooterSectionLinks();
	}

	@Test
		public void VerifyFooterSocialLinks() throws Exception {
		new FooterSectionPage(driver).verifyFooterSocialLinks();
	}

	@Test
	public void VerifyFooterCCPASection() throws Exception {
		new FooterSectionPage(driver).verifyFooterCCPASection();
	}

	@Test
	public void VerifyFooterEmailSignUp() throws Exception {
		new FooterSectionPage(driver).verifyFooterEmailSignUp();
	}

	@Test
	public void VerifyCustomerHelpCode() throws Exception {
		new FooterSectionPage(driver).VerifyCustomerHelpCode();
	}

//	@Test
	public void VerifyFooterFeedback() throws Exception {
		new FooterSectionPage(driver).verifyFooterFeedback();
	}

	@AfterMethod(alwaysRun = true)
	void tearDown() {
//		driver.close();
//		driver.quit();
		if(driver != null)
		{
			driver.quit();
		}
	}
}
