package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.AddToCartPage;
import com.tommyjohn.automation.Pages.BundlePDPPage;
import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateBundlePDP {
	
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
	public void TC_ViewImageGallery() throws Exception {
		new BundlePDPPage(driver).viewImageGallery();
		
	}
	
	@Test
	public void TC_verifyImageGallery() throws Exception {
		new BundlePDPPage(driver).verifyImageGallery();
	}
	
	@Test
	public void TC_verifyOnImageBadging() throws Exception {
		new BundlePDPPage(driver).verifyOnImageBadging();
		
	}
	
	@Test
	public void TC_verifySecondaryBadging() throws Exception {
		new BundlePDPPage(driver).verifySecondaryBadging();
		
	}
	@Test
	public void TC_verifySelectedColorSwatch() throws Exception {
		new BundlePDPPage(driver).verifySelectedColorSwatch();
		
	}
	@Test
	public void TC_verifyAvailableSizes() throws Exception {
		new BundlePDPPage(driver).verifyAvailableSizes();
		
	}
	
	@Test
	public void TC_verifyReviewRecommendations() throws Exception {
		new BundlePDPPage(driver).verifyReviewRecommendations();
	}
	@Test
	public void TC_verifyProductNamePrice() throws Exception {
		new BundlePDPPage(driver).verifyProductNamePrice();
	}
	@Test
	public void verifyAccordions() throws Exception {
		new BundlePDPPage(driver).verifyAccordions();
	}

	@Test
	public void verifyReviewRecommendationsSection() throws Exception {
		new BundlePDPPage(driver).verifyReviewRecommendationsSection();
	}
	
	
	@Test
	public void verifyAddToCart() throws Exception {
		new BundlePDPPage(driver).verifyAddToCart();
	}
	
	
	@AfterMethod(alwaysRun = true)
	void teardown () {

		//driver.close();
		driver.quit();
	
	}
}