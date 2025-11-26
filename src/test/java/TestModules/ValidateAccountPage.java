package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Pages.AccountPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateAccountPage extends ExtentReport {
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
		public void Validate_AccountPage() throws Exception {
		new AccountPage(driver).Verify_AccountPage();
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
