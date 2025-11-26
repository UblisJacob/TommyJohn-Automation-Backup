package TestModules;

import TestScenario.Retry;
import com.tommyjohn.automation.Components.HomePageComponents;
import com.tommyjohn.automation.Components.ImagesComponents;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.ExtentReport;
import com.tommyjohn.automation.Components.AddToCartComponents;
import com.tommyjohn.automation.Components.ProductDetailsPageComponents;
import com.tommyjohn.automation.Pages.AccountLoginPage;
import com.tommyjohn.automation.Pages.CollectionPage;
import com.tommyjohn.automation.Pages.HomePage;
import com.tommyjohn.automation.Pages.ShoppingCartPage;
import com.tommyjohn.automation.commonMethods.GeneralMethods;
import com.tommyjohn.automation.utils.CustomUtilities;

public class ValidateImages extends ExtentReport{

    WebDriver driver;
    GeneralMethods objGeneralMethods = new GeneralMethods();
    String searchText="";

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(String browser, ITestContext context) {
        try {
            driver = new CustomUtilities().launchtj(browser);
            context.setAttribute("driver", driver);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void ValidateImageInHomePage() throws Exception {
        ImagesComponents Image = new ImagesComponents(driver);
        Image.VerifyAltText();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        if (driver != null) {
            driver.quit();
        }
    }

}
