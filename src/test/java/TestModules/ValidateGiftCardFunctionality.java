package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Pages.GiftCardPage;
import com.tommyjohn.automation.Pages.GiftCard_Page;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateGiftCardFunctionality extends ExtentReport{

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
	public void ValidateEGiftCardFlow() throws Exception
	{
		new GiftCardPage(driver).egiftCardPageCheck(); 
	}

	@Test
	public void FieldValidationsForGiftCard() throws Exception
	{
		new GiftCardPage(driver).verifyfieldsforGCPage();
	}

	@Test
	public void ValidateErrorScenariosForGiftCard() throws Exception
	{
		new GiftCardPage(driver).verifyerrormessagesongiftcard();
	}

	//@Test
	public void TC_verifyPGCard() throws Exception
	{
		new GiftCardPage(driver).verifyPGCard(); 
	}
	
	@AfterMethod(alwaysRun = true)
	void teardown () {

		//driver.close();
		driver.quit();
	}

}
