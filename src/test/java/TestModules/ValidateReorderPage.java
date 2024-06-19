package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.ReOrderPage;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateReorderPage {

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
	public void ValidateDetailsOnReOrderPage() throws Exception {
		new ReOrderPage(driver).validateReOrderPage();
	}

	@Test
	public void ValidateUserIsAbleToReOrderItemFromReOrderPage() throws Exception {
		new ReOrderPage(driver).reOrderItem();
	}

	@Test
	public void ValidateUserIsRedirectedToCorrectPDPFromReOrderPage() throws Exception {
		new ReOrderPage(driver).reOrderToPDP();
	}

	@Test
	public void ValidateUserIsRedirectedToCorrectOrderDetailsPageFromReOrderPage() throws Exception {
		new ReOrderPage(driver).orderDetails();
	}

	@Test
	public void ValidateReviewsPageFromReOrderPage() throws Exception {
		new ReOrderPage(driver).reviewPage();
	}

	@AfterMethod(alwaysRun = true)
	void teardown() {
		driver.close();
		driver.quit();
	}

}
