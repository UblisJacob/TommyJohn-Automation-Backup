package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Pages.BlogPage;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;
public class ValidateBlogPages{
	
	WebDriver driver;
	GeneralMethods objGeneralMethods = new GeneralMethods();

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
	public void ViewBlogAndArticle() throws Exception {
		new BlogPage(driver).validateBlogPage();
	}

	
	@AfterMethod(alwaysRun = true)
	void teardown() {

		 driver.close();
		driver.quit();
	}
}