package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tommyjohn.automation.Pages.MixAndMatchPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateMixAndMatchPage 
{
	WebDriver driver;
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		try {
			driver = new CustomUtilities().launchtj();

		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	@Test
	public void TC_ValidateFieldsOfMixAndMatch() throws Exception
	{
		new MixAndMatchPage(driver).ValidateFieldsOfMixAndMatch();
	}

	@AfterMethod(alwaysRun = true)
	void teardown () {

		driver.close();
		driver.quit();
	}

}
