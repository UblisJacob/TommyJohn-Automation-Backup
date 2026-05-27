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
	public void ValidateMailIsAdded() throws Exception
	{
		new GiftCardPage(driver).ValidateMailIsAdded();
	}

	@Test
	public void ValidateGiftDetailsInCartPage() throws Exception
	{
		new GiftCardPage(driver).ValidateGiftDetailsInCartPage();
	}

	@Test
	public void ValidateGiftDetailsInCheckoutPage() throws Exception
	{
		new GiftCardPage(driver).ValidateGiftDetailsInCartPage();
	}

	@Test
	public void ValidateIwantToSendThisAsAGiftRadioButton() throws Exception
	{
		new GiftCardPage(driver).ValidateIwantToSendThisAsAGiftRadioButton();
	}

	@Test
	public void ValidateFraudDisclosureInGCPDP() throws Exception
	{
		new GiftCardPage(driver).ValidateFraudDisclosureInGCPDP();
	}

	@Test
	public void ValidateFieldForGiftCard() throws Exception
	{
		new GiftCardPage(driver).verifyfieldsforGCPage();
	}

	//@Test
	public void ValidateErrorScenariosForGiftCard() throws Exception
	{
		new GiftCardPage(driver).verifyerrormessagesongiftcard();
	}

	//@Test
	public void VerifyPGCard() throws Exception
	{
		new GiftCardPage(driver).verifyPGCard(); 
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
