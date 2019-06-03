package TestScenario;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateProductDetailsPage {
	
//	@BeforeTest(alwaysRun = true)
//	void setup() {
//		try {
//			CustomUtilities.launchtj();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}
	
	@Test
	public void TC_verifyProductDetailsPage() throws Exception {	
		new ProductDetailsPage().verifyProductDetailsPage();
	}
	
//	@AfterSuite(alwaysRun = true)
//	void tearDown() {
//		try {
//			CustomUtilities.teardown();
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}
}