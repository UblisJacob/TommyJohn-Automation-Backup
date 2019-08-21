package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.GiftCardPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateGiftCardFunctionality{

	WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		try {
			driver = new CustomUtilities().launchtj();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void ValidateGiftCardFlow() throws Exception
	{
		new GiftCardPage(driver).giftCardPageCheck();
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

	@AfterMethod(alwaysRun = true)
	void teardown () {

		driver.close();
		driver.quit();
	}

}
