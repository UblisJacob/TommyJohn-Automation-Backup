package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Pages.AddToCartPage;
import com.tommyjohn.automation.Pages.BundlePDPPage;
import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateBundlePDP extends ExtentReport {
	
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
	public void VerifyImageGallery() throws Exception {
		new BundlePDPPage(driver).verifyImageGallery();
	}
	
//	@Test
	public void VerifyOnImageBadging() throws Exception {
		new BundlePDPPage(driver).verifyOnImageBadging();
		
	}

	@Test
	public void VerifySecondaryBadging() throws Exception {
		new BundlePDPPage(driver).verifySecondaryBadging();
		
	}
	@Test
	public void VerifySelectedColorSwatch() throws Exception {
		new BundlePDPPage(driver).verifySelectedColorSwatch();
		
	}
	@Test
	public void VerifyAvailableSizes() throws Exception {
		new BundlePDPPage(driver).verifyAvailableSizes();
		
	}
	
	@Test
	public void VerifyReviewRecommendations() throws Exception {
		new BundlePDPPage(driver).verifyReviewRecommendations();
	}
	@Test
	public void VerifyProductNamePrice() throws Exception {
		new BundlePDPPage(driver).verifyProductNamePrice();
	}
	@Test
	public void VerifyAccordions() throws Exception {
		new BundlePDPPage(driver).verifyAccordions();
	}

	@Test
		public void VerifyAddToCart() throws Exception {
		new BundlePDPPage(driver).verifyAddToCart();
	}

	@Test
	public void ValidateLearnMoreLink() throws Exception {
		new BundlePDPPage(driver).ValidateLearnMoreLink();
	}
		
	@AfterMethod(alwaysRun = true)
	void teardown () {

//		driver.close();
//		driver.quit();
		if(driver != null)
		{
			driver.quit();
		}
	}
}