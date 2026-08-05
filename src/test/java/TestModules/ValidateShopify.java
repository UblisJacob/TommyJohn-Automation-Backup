package TestModules;

import com.base.ExtentReport;
import com.tommyjohn.automation.Components.HomePageComponents;
import com.tommyjohn.automation.Components.ShopifyComponents;
import com.tommyjohn.automation.utils.CustomUtilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ValidateShopify extends ExtentReport {

    public WebDriver driver;

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
    public void ValidateShopifyLogin() throws Exception {
        ShopifyComponents shopify = new ShopifyComponents(driver);
        shopify.ValidateShopifyAccountLogin("ublisjacobn@unitedtechno.com","Ublisjacob@shopify20");
    }

}
