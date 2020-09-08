package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.AfterPay;
import com.tommyjohn.automation.Pages.ContactUsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateContactUsPage {
	WebDriver driver ;

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
	public void TC_ContactUs_HelpPageValidation() throws Exception
	{
		new ContactUsPage(driver).ContactUsFieldValidation_page();
	}
	@Test
	public void TC_VerifyContactUsFlow() throws Exception
	{
		new ContactUsPage(driver).VerifyContactUsFlow();
	}
	
	@AfterMethod(alwaysRun = true)
	void teardown () {
		
		driver.close();
		driver.quit();
	}
}
