package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.GiftCardBalancePage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateGiftCardBalance {
	
	String validPhysicalGCID = "1534174744485637";
	String validPhysicalGCPin = "1234";
	String validPhysicalGCHolderEmail = "neha.mahamulkar@tommyjohnwear.com";
	String validDigitalGCID = "9876543234567876";
	String validDigitalGCHolderEmail = "kritika.bhat@tommyjohnwear.com";
	
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
	public void VerifyPhysicalAndDigitalFormPresence() throws Exception {
		new GiftCardBalancePage(driver).verifyPhysicalAndDigitalFormPresence();
	}
	
	@Test
	public void VerifyPhysicalGiftCardFormInteraction() throws Exception {
		new GiftCardBalancePage(driver).verifyPhysicalGiftCardFormInteraction(validPhysicalGCID, validPhysicalGCPin, validPhysicalGCHolderEmail);
	}
	
	@Test
	public void VerifyDigitalGiftCardFormInteraction() throws Exception {
		new GiftCardBalancePage(driver).verifyDigitalGiftCardFormInteraction(validPhysicalGCID, validPhysicalGCHolderEmail);
	}
	
	@AfterMethod(alwaysRun = true)
	void teardown() {
		driver.close();
		driver.quit();
	}

}
