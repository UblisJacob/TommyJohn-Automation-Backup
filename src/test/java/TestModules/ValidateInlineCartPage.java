package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Pages.FlyCartPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateInlineCartPage extends ExtentReport {

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
	public void TC_ValidateEmptyFlyCart() throws Exception 
	{
		new FlyCartPage(driver).verifyAllFields(); 
	}

	@Test 
	public void TC_ValidateTextAndLinksonFlyCart() throws Exception
	{
		new FlyCartPage(driver).verifyAllFieldsOnNonEmptyFlyCart(); 
	}

	@Test 
	public void TC_ValidateFlyCartwithProductsInIt() throws Exception
	{
		new FlyCartPage(driver).validateAllFieldsOnNonEmptyFlyCart(); 
	}

	@Test
	public void TC_ValidateFlyCartOpenedWithQSandPDP() throws Exception {

		new FlyCartPage(driver).VerifyFlyCartOpenWithQSAndPDP();
	}
	
	@Test
	public void validateOOSonInlinecart() throws Exception
	{
		new FlyCartPage(driver).validateOOSonInlinecart(); 
	}

	@Test
	public void TC_verifyPromotionalMessagingPresence() throws Exception
	{
		new FlyCartPage(driver).validatePromotionMessaging();
	}
	@Test
	public void TC_verifyTieredMeter() throws Exception
	{
		
		new FlyCartPage(driver).validateTieredMeter();
	}
	
	
	@AfterMethod(alwaysRun = true)
	void tearDown() {

		//this.driver.close();
		this.driver.quit();

	}
}

