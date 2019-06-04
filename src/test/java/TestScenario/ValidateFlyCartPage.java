package TestScenario;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.FlyCartPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateFlyCartPage {
	
	@BeforeMethod(alwaysRun = true)
	void setup() {
		try {
			CustomUtilities.launchtj();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	@Test
	public void TC_VerifyAllFieldsAndTextOnEmptyFlyCart() throws Exception {
		new FlyCartPage().verifyAllFields();
	}
	@Test
	public void TC_VerifyAllFieldsAndTextOnNonEmptyFlyCart() throws Exception {
		new FlyCartPage().verifyAllFieldsOnNonEmptyFlyCart();
	}

	@Test
	public void TC_ValidateAllFieldsOnNonEmptyFlyCart() throws Exception {
		new FlyCartPage().validateAllFieldsOnNonEmptyFlyCart();
	}

	@AfterMethod(alwaysRun = true)
	void tearDown() {
		try {
			CustomUtilities.teardown();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
