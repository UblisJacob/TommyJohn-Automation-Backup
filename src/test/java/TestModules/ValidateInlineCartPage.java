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
	public void ValidateEmptyFlyCart() throws Exception
	{
		new FlyCartPage(driver).verifyAllFields(); 
	}

	@Test 
	public void ValidateTextAndLinksonFlyCart() throws Exception
	{
		new FlyCartPage(driver).verifyAllFieldsOnNonEmptyFlyCart(); 
	}

	@Test 
	public void ValidateFlyCartwithProductsInIt() throws Exception
	{
		new FlyCartPage(driver).validateAllFieldsOnNonEmptyFlyCart(); 
	}

	@Test
	public void ValidateFlyCartOpenedWithQSandPDP() throws Exception {

		new FlyCartPage(driver).VerifyFlyCartOpenWithQSAndPDP();
	}
	
	@Test
	public void ValidateOOSonInlinecart() throws Exception
	{
		new FlyCartPage(driver).validateOOSonInlinecart(); 
	}

	@Test
	public void VerifyPromotionalMessagingPresence() throws Exception
	{
		new FlyCartPage(driver).validatePromotionMessaging();
	}
	@Test
	public void VerifyTieredMeter() throws Exception
	{
		
		new FlyCartPage(driver).validateTieredMeter();
	}

	@Test
	public void ValidateUserAbletoAddProductFromInlineCartSuggestion() throws Exception
	{

		new FlyCartPage(driver).ValidateAddingProductToCartFromInlineCartSuggestion();
	}

	@Test
	public void ValidateAppropriateProductIsToCartFromInlineCartSuggestion() throws Exception
	{

		new FlyCartPage(driver).ValidateAppropriateIsProductIsToCartFromInlineCartSuggestion();
	}

	@Test
	public void ValidateQuickShopATCIsAvailableInSuggestionProducts() throws Exception
	{

		new FlyCartPage(driver).ValidateQuickShopATCIsAvailableInSuggestionProducts();
	}

	@Test
	public void ValidateSizeOverlayIsDisplayedInTheInlineCartSuggestionProducts() throws Exception
	{

		new FlyCartPage(driver).ValidateSizeOverlayIsDisplayedInTheInlineCartSuggestionProducts();
	}
	
	
	@AfterMethod(alwaysRun = true)
	void tearDown() {

		this.driver.close();
		this.driver.quit();
	}
}

