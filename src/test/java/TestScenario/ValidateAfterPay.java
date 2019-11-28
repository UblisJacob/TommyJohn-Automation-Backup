package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tommyjohn.automation.Pages.AfterPay;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateAfterPay {

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
	public void TC_ValidateAfterPayOnPDP() throws Exception
	{
		new AfterPay(driver).AfterPayOnPDP();
	}
	
	@Test
	public void TC_ValidateAfterPayOnInlineCart() throws Exception
	{
		new AfterPay(driver).AfterPayOnInlineCart();
	}
	
	@Test
	public void TC_ValidateAfterPayOnShoppingCart() throws Exception
	{
		new AfterPay(driver).AfterPayOnShoppingCart();
	}
	
	@Test
	public void TC_ValidateAfterPayOnCheckoutPage() throws Exception
	{
		new AfterPay(driver).AfterPayOnCheckoutPage();
	}
	
	@Test
	public void TC_ValidateAfterPayWithGiftCard() throws Exception
	{
		new AfterPay(driver).AfterPayOnGiftCardPage();
	}


	@AfterMethod(alwaysRun = true)
	void teardown () {
		
		driver.close();
		driver.quit();
	}

}