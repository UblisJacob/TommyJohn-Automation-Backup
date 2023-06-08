package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.ProductDetailsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidatePDP {

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
		new ProductDetailsPage(driver).viewImageGallery();
		
	}
	
	@Test
	public void TC_verifyImageGallery() throws Exception {
		new ProductDetailsPage(driver).verifyImageGallery();
	}
	
	@Test
	public void TC_verifyOnImageBadging() throws Exception {
		new ProductDetailsPage(driver).verifyOnImageBadging();
		
	}
	
	@Test
	public void TC_verifySecondaryBadging() throws Exception {
		new ProductDetailsPage(driver).verifySecondaryBadging();
		
	}
	@Test
	public void TC_verifySelectedColorSwatch() throws Exception {
		new ProductDetailsPage(driver).verifySelectedColorSwatch();
		
	}
	@Test
	public void TC_verifyAvailableSizes() throws Exception {
		new ProductDetailsPage(driver).verifyAvailableSizes();
		
	}
	@Test
	public void TC_verifyStickyATC() throws Exception {
		new ProductDetailsPage(driver).verifyStickyATC();
	}
	
	@Test
	public void TC_verifyReviewRecommendations() throws Exception {
		new ProductDetailsPage(driver).verifyReviewRecommendations();
	}
	@Test
	public void TC_verifyProductNamePrice() throws Exception {
		new ProductDetailsPage(driver).verifyProductNamePrice();
	}
	@Test
	public void verifyAccordions() throws Exception {
		new ProductDetailsPage(driver).verifyAccordions();
	}
	@Test
	public void verifyLowStockInventory() throws Exception {
		new ProductDetailsPage(driver).verifyLowStockInventory();
	}
	@Test
	public void verifyReviewRecommendationsSection() throws Exception {
		new ProductDetailsPage(driver).verifyReviewRecommendationsSection();
	}
	
	@Test
	public void verifyQuestionForm() throws Exception {
		new ProductDetailsPage(driver).verifyQuestionForm();
	}
	@Test
	public void verifyReviewForm() throws Exception {
		new ProductDetailsPage(driver).verifyReviewForm();
	}
	@Test
	public void verifyReviewFormErrorMessages() throws Exception {
		new ProductDetailsPage(driver).verifyReviewFormErrorMessages();
	}
	@Test
	public void verifyAddToCart() throws Exception {
		new ProductDetailsPage(driver).verifyAddToCart();
	}
}
  