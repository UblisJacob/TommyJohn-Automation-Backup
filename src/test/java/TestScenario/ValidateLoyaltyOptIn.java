package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.LoyaltyOptInPages;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateLoyaltyOptIn {
	public WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		try {
			driver=	new CustomUtilities().launchtj();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void TC_verifyLoyaltyOptinFromCreateAccountPage() throws Exception {
		new LoyaltyOptInPages(driver).Loyaltyoptinfromcreateaccount();

	}
	@Test
	public void TC_verifyLoyaltyOptinFromAccountPage() throws Exception {
		new LoyaltyOptInPages(driver).Loyaltyoptinfromaccountpage();

	}
	@Test
	public void TC_verifyLoyaltyOptinFromLoyaltyPage() throws Exception {
		new LoyaltyOptInPages(driver).Loyaltyoptinfromloyaltypage();

	}


	@Test
	public void TC_verifyLoyaltyPageforGuestUser() throws Exception {
		new LoyaltyOptInPages(driver).verifyloyaltypageGuest();

	}

	@Test
	public void TC_verifyLoyaltyPageforRegisteredUser() throws Exception {
		new LoyaltyOptInPages(driver).verifyloyaltypageregistered();

	}
	
	@Test
	public void TC_verifySwellFunctionalityOnCheckout() throws Exception {
		new LoyaltyOptInPages(driver).verifyswell();

	}


	@AfterMethod(alwaysRun = true)
	void teardown () {

		driver.close();
		driver.quit();
	}



}
