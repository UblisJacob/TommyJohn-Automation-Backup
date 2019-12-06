package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.AfterPay;
import com.tommyjohn.automation.Pages.ContactUsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateContactUsPage {
	WebDriver driver ;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		try {
			driver = new CustomUtilities().launchtj();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void TC_ContactUsFieldValidation() throws Exception
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
