package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.CheckoutPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateOrderPlacement {

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
	public void ValidateOrderPlacementFlow() throws Exception
	{
		new CheckoutPage(driver).ValidateOrderPlacement();
	}
	
	@AfterMethod(alwaysRun = true)
	void tearDown() {

		//this.driver.close();
		this.driver.quit();

	}

}
