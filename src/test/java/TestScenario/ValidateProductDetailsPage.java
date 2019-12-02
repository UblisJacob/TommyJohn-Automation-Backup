package TestScenario;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateProductDetailsPage {

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
	public void TC_verifyProductDetailsPage() throws Exception {	
		new ProductDetailsPage(driver).verifyProductDetailsPage();
	}
	@Test
	public void TC_verifyReviewsSectionOnPDP() throws Exception {	
		new ProductDetailsPage(driver).verifyReviewsSectionOnPDP();
	}
	@Test
	public void TC_verifyWriteAReviewFunctionalityOnPDP() throws Exception {	
		new ProductDetailsPage(driver).verifyWriteReviewsFunctionalityOnPDP();
	}
	@Test
	public void TC_verifyErrorMessagesForWriteReviewFieldsOnPDP() throws Exception {	
		new ProductDetailsPage(driver).verifyReviewsFieldErrorOnPDP();
	}
	@Test
	public void TC_VerifyBundlePDP() throws Exception {	
		new ProductDetailsPage(driver).verifyBundlePDP();
	}
	
	@Test
	public void TC_VerifyJoinTheWaitlistFunctionality() throws Exception {	
		new ProductDetailsPage(driver).verifyjointhewaitlist();
	}
	
	@Test
	public void TC_VerifyRecommendedForYouSection() throws Exception {	
		new ProductDetailsPage(driver).verifyrecommendedforyou();
	}
	
	@Test
	public void TC_VerifyMysterPackPDP() throws Exception {	
		new ProductDetailsPage(driver).verifymysterypack();
	}
	

	@AfterMethod(alwaysRun = true)
	void teardown () {
		
		driver.close();
		driver.quit();
	}
}