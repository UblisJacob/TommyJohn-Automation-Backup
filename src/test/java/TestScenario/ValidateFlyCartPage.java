package TestScenario;

import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.FlyCartPage;

public class ValidateFlyCartPage {
	
//	@Test
//	public void TC_VerifyAllFieldsAndTextOnEmptyFlyCart() throws Exception {
//		new FlyCartPage().verifyAllFields();
//	}
	@Test
	public void TC_VerifyAllFieldsAndTextOnNonEmptyFlyCart() throws Exception {
		new FlyCartPage().verifyAllFieldsOnNonEmptyFlyCart();
	}

}
