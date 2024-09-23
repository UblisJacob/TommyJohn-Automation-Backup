package TestModules;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tommyjohn.automation.Pages.NavigationElementsPage;
import com.tommyjohn.automation.utils.CustomUtilities;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ValidateNavigationElements {
	
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

	@Test(description = "validates if user is able to see four links on left side of TJ logo")
	public void ValidateNavLinks() {
		new NavigationElementsPage(driver).validateNavLinks();
	}

	@Test
	public void ValidateLogoAndRightSideLinks() {
		new NavigationElementsPage(driver).validateLogoAndRightSideLinks();
	}

	@Test
	public void ValidateSearchBar() throws InterruptedException {
		new NavigationElementsPage(driver).validateSearchBar();
	}
	
	//@Test
	public void ValidateElementsWhenNotSignedIn() {
		new NavigationElementsPage(driver).validateElementsWhenNotSignedIn();
	}

	//@Test
	public void ValidateElementsWhenSignedIn() throws Exception {
		new NavigationElementsPage(driver).validateElementsWhenSignedIn();
	}
	
	@Test
	public void ValidateCartIcon() {
		new NavigationElementsPage(driver).validateCartIcon();
	}
	
	@Test(description = "Validates Level 0, Level 1 and Level 2 Navigation")
	@Severity(SeverityLevel.BLOCKER)
	public void ValidateNavigation() {
		new NavigationElementsPage(driver).validateNavigation();
	}
	
//	@Test(description = "validates if user is able to see four links on left side of TJ logo")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Validates if user is able to see upto four links on left side of TJ logo")
	public void ValidateAllQuizLandingPages() {
		new NavigationElementsPage(driver).validateAllQuizLandingPages();
	}

	@AfterMethod(alwaysRun = true)
	void teardown()
	{
		driver.close();
		driver.quit();
	}

}
